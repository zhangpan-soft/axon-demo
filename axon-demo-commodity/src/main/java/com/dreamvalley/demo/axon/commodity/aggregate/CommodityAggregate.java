package com.dreamvalley.demo.axon.commodity.aggregate;

import com.dreamvalley.demo.axon.commodity.command.CommodityCreateCommand;
import com.dreamvalley.demo.axon.commodity.event.CommodityCreatedEvent;
import com.dreamvalley.demo.axon.commodity.event.CommodityStockAddFailedEvent;
import com.dreamvalley.demo.axon.commodity.event.CommodityStockAddSucceedEvent;
import com.dreamvalley.demo.axon.core.base.aggregate.BaseAggregate;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockAddCommand;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockSubCommand;
import com.dreamvalley.demo.axon.core.event.commodity.CommodityStockSubFailedEvent;
import com.dreamvalley.demo.axon.core.event.commodity.CommodityStockSubSucceedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;

/**
 * 商品域,聚合对象
 *
 * @author zhangpan
 */
@Data
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class CommodityAggregate implements BaseAggregate<Long> {
    @AggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String name;
    private Long price;
    private Integer stock;
    private String status;

    @CommandHandler
    public CommodityAggregate(CommodityCreateCommand command, MetaData metaData) {
        log.info("CommodityCreateCommand:{},metaData:{}", command, metaData);
        AggregateLifecycle.apply(CommodityCreatedEvent.builder(command).build(), metaData);
    }

    @CommandHandler
    public void handle(CommodityStockSubCommand command, MetaData metaData) {
        log.info("CommodityStockSubCommand:{},metaData:{}", command, metaData);
        if (command.getNum() < 0) {
            AggregateLifecycle.apply(CommodityStockSubFailedEvent.builder(command).remark("请求参数异常").build(), metaData);
        } else if (command.getNum() > this.stock) {
            AggregateLifecycle.apply(CommodityStockSubFailedEvent.builder(command).remark("库存不足").build(), metaData);
        } else {
            AggregateLifecycle.apply(CommodityStockSubSucceedEvent
                            .builder()
                            .id(command.getId())
                            .createdTime(LocalDateTime.now())
                            .num(command.getNum())
                            .orderId(command.getOrderId())
                            .orderTotalAmount(command.getOrderTotalAmount())
                            .userId(command.getUserId())
                            .build(),
                    metaData);
        }
    }

    @CommandHandler
    public void handle(CommodityStockAddCommand command,MetaData metaData){
        log.info("CommodityStockSubCommand:{},metaData:{}", command, metaData);
        if (command.getNum() < 0) {
            AggregateLifecycle.apply(CommodityStockAddFailedEvent.builder(command).remark("请求参数异常").build(), metaData);
        } else {
            AggregateLifecycle.apply(CommodityStockAddSucceedEvent.builder(command).remark("").build(),metaData);
        }
    }

    @EventSourcingHandler
    public void on(CommodityCreatedEvent event, MetaData metaData) {
        log.info("CommodityCreatedEvent:{},metaData:{}", event, metaData);
        this.id = event.getId();
        this.createdTime = event.getCreatedTime();
        this.updatedTime = event.getCreatedTime();
        this.name = event.getName();
        this.price = event.getPrice();
        this.stock = event.getStock();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(CommodityStockSubFailedEvent event, MetaData metaData) {
        log.info("CommodityStockSubFailedEvent:{},metaData:{}", event, metaData);
    }

    @EventSourcingHandler
    public void on(CommodityStockSubSucceedEvent event, MetaData metaData) {
        log.info("CommodityStockSubSucceedEvent:{},metaData:{}", event, metaData);
        this.stock -= event.getNum();
        this.updatedTime = LocalDateTime.now();
    }

    @EventSourcingHandler
    public void on(CommodityStockAddFailedEvent event,MetaData metaData){
        log.info("CommodityStockAddFailedEvent:{},metaData:{}", event, metaData);
    }

    @EventSourcingHandler
    public void on(CommodityStockAddSucceedEvent event,MetaData metaData){
        log.info("CommodityStockAddSucceedEvent:{},metaData:{}", event, metaData);
        this.stock+=event.getNum();
        this.updatedTime = LocalDateTime.now();
    }


}
