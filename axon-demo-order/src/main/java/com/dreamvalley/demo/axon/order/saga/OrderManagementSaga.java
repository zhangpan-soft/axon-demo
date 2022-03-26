package com.dreamvalley.demo.axon.order.saga;


import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockAddCommand;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockSubCommand;
import com.dreamvalley.demo.axon.core.command.user.UserWithdrewCommand;
import com.dreamvalley.demo.axon.core.event.commodity.CommodityStockSubFailedEvent;
import com.dreamvalley.demo.axon.core.event.commodity.CommodityStockSubSucceedEvent;
import com.dreamvalley.demo.axon.core.event.user.UserWithdrawEvent;
import com.dreamvalley.demo.axon.core.event.user.UserWithdrawFailedEvent;
import com.dreamvalley.demo.axon.core.utils.SpringContextHolder;
import com.dreamvalley.demo.axon.order.command.OrderCancelCommand;
import com.dreamvalley.demo.axon.order.command.OrderStatusChangeCommand;
import com.dreamvalley.demo.axon.order.enums.OrderStatus;
import com.dreamvalley.demo.axon.order.event.OrderCreatedEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 事件管理器/流程控制器/事件编排器
 *
 * @author zhangpan
 */
@Saga
@Data
@Slf4j
public class OrderManagementSaga {


    /**
     * 监听创建订单事件
     *
     * @param event
     * @param metaData
     */
    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(OrderCreatedEvent event, MetaData metaData) {
        log.info("OrderManagementSaga:OrderCreatedEvent:{},metaData:{}", event, metaData);
        // 发送扣库存命令
        SagaLifecycle.associateWith("id", event.getCommodityId());
        SagaLifecycle.associateWith("orderId",event.getId());
        SpringContextHolder.getCommandGateway().send(
                CommodityStockSubCommand
                        .builder()
                        .id(event.getCommodityId())
                        .num(event.getCommodityNum())
                        .orderId(event.getCommodityId())
                        .createdTime(LocalDateTime.now())
                        .orderTotalAmount(event.getTotalAmount())
                        .userId(event.getUserId())
                        .build(),
                metaData);
    }


    /**
     * 监听扣库存成功命令
     *
     * @param event
     * @param metaData
     */
    @SagaEventHandler(associationProperty = "id")
    public void handle(CommodityStockSubSucceedEvent event, MetaData metaData) {
        log.info("OrderManagementSaga:CommodityStockSubSucceedEvent:{},metaData:{}", event, metaData);
        // 发送扣余额命令
        SagaLifecycle.associateWith("id", event.getUserId());
        SagaLifecycle.associateWith("orderId",event.getOrderId());
        SpringContextHolder.getCommandGateway().send(UserWithdrewCommand
                        .builder()
                        .id(event.getUserId())
                        .createdTime(LocalDateTime.now())
                        .balanceAmount(event.getOrderTotalAmount())
                        .build(),
                metaData.mergedWith(new HashMap<String, Object>() {{
                    this.put("orderId", event.getOrderId());
                    this.put("commodityNum", event.getNum());
                    this.put("commodityId", event.getId());
                }}));
    }

    /**
     * 监听扣款成功命令,订单完成
     *
     * @param event
     * @param metaData
     */
    @SagaEventHandler(associationProperty = "id")
    @EndSaga
    public void handle(UserWithdrawEvent event, MetaData metaData) {
        log.info("OrderManagementSaga:UserWithdrawFailedEvent:{},metaData:{}", event, metaData);
        Long orderId = (Long) metaData.get("orderId");
        // 发送订单完成命令
        SpringContextHolder.getCommandGateway().send(OrderStatusChangeCommand.builder().id(orderId).createdTime(LocalDateTime.now()).status(OrderStatus.FINISH.getCode()).build(), metaData);
    }

    /**
     * 监听扣款失败命令,订单取消,回滚.
     *
     * @param event
     * @param metaData
     */
    @SagaEventHandler(associationProperty = "id")
    @EndSaga
    public void handle(UserWithdrawFailedEvent event, MetaData metaData) {
        log.info("OrderManagementSaga:UserWithdrawFailedEvent:{},metaData:{}", event, metaData);
        // 回滚库存
        Long commodityId = (Long) metaData.get("commodityId");
        Long orderId = (Long) metaData.get("orderId");
        Integer commodityNum = (Integer) metaData.get("commodityNum");
        CommodityStockAddCommand commodityStockAddCommand = new CommodityStockAddCommand();
        commodityStockAddCommand.setId(commodityId);
        commodityStockAddCommand.setNum(commodityNum);
        commodityStockAddCommand.setCreatedTime(LocalDateTime.now());
        SpringContextHolder.getCommandGateway().send(commodityStockAddCommand, metaData);
        // 取消订单
        SpringContextHolder.getCommandGateway().send(
                OrderCancelCommand
                        .builder()
                        .id(orderId)
                        .remark(event.getRemark())
                        .createdTime(LocalDateTime.now())
                        .build(),
                metaData);
    }

    /**
     * 监听扣库存失败事件,订单取消
     *
     * @param event
     * @param metaData
     */
    @SagaEventHandler(associationProperty = "id")
    @EndSaga
    public void handle(CommodityStockSubFailedEvent event, MetaData metaData) {

        log.info("OrderManagementSaga:CommodityStockSubFailedEvent:{},metaData:{}", event, metaData);
        // 发送取消订单命令
        SpringContextHolder.getCommandGateway().send(
                OrderCancelCommand
                        .builder()
                        .id(event.getOrderId())
                        .remark(event.getRemark())
                        .createdTime(LocalDateTime.now())
                        .build(),
                metaData);
    }

}
