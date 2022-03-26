package com.dreamvalley.demo.axon.order.aggregate;

import com.dreamvalley.demo.axon.core.base.aggregate.BaseAggregate;
import com.dreamvalley.demo.axon.order.command.OrderCancelCommand;
import com.dreamvalley.demo.axon.order.command.OrderCreateCommand;
import com.dreamvalley.demo.axon.order.command.OrderStatusChangeCommand;
import com.dreamvalley.demo.axon.order.enums.OrderStatus;
import com.dreamvalley.demo.axon.order.event.OrderCanceledEvent;
import com.dreamvalley.demo.axon.order.event.OrderCreatedEvent;
import com.dreamvalley.demo.axon.order.event.OrderStatusChangedEvent;
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
 * 订单聚合对象
 *
 * @author zhangpan
 */
@Data
@Aggregate
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class OrderAggregate implements BaseAggregate<Long> {
    @AggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Integer status;
    private String remark;
    private Long totalAmount;
    private Long userId;
    private Long commodityId;

    @CommandHandler
    public OrderAggregate(OrderCreateCommand command, MetaData metaData){
        log.info("OrderCreateCommand:{},MetaData:{}",command,metaData);
        AggregateLifecycle.apply(OrderCreatedEvent.builder(command).build(),metaData);
    }


    @CommandHandler
    public void handle(OrderStatusChangeCommand command, MetaData metaData){
        log.info("OrderStatusChangeCommand:{},MetaData:{}",command,metaData);
        AggregateLifecycle.apply(OrderStatusChangedEvent.builder(command).build(),metaData);
    }

    @CommandHandler
    public void handle(OrderCancelCommand command, MetaData metaData){
        log.info("OrderFailCommand:{},MetaData:{}",command,metaData);
        AggregateLifecycle.apply(OrderCanceledEvent.builder(command).build(),metaData);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event,MetaData metaData){
        log.info("OrderCreatedEvent:{},MetaData:{}",event,metaData);
        this.id = event.getId();
        this.createdTime = event.getCreatedTime();
        this.updatedTime = event.getCreatedTime();
        this.status = OrderStatus.CREATE.getCode();
        this.remark = event.getRemark();
        this.totalAmount = event.getTotalAmount();
        this.userId = event.getUserId();
        this.commodityId = event.getCommodityId();
    }

    @EventSourcingHandler
    public void on(OrderStatusChangedEvent event,MetaData metaData){
        log.info("OrderStatusChangedEvent:{},MetaData:{}",event,metaData);
        this.status = event.getStatus();
        this.remark = event.getRemark();
        this.updatedTime = LocalDateTime.now();
    }

    @EventSourcingHandler
    public void on(OrderCanceledEvent event,MetaData metaData){
        log.info("OrderStatusChangedEvent:{},MetaData:{}",event,metaData);
        this.remark = event.getRemark();
        this.status = OrderStatus.CANCEL.getCode();
        this.updatedTime = event.getCreatedTime();
    }
}
