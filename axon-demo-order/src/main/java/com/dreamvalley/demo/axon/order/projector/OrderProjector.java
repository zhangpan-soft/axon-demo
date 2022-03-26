package com.dreamvalley.demo.axon.order.projector;

import com.dreamvalley.demo.axon.order.entity.Order;
import com.dreamvalley.demo.axon.order.enums.OrderStatus;
import com.dreamvalley.demo.axon.order.event.OrderCanceledEvent;
import com.dreamvalley.demo.axon.order.event.OrderCreatedEvent;
import com.dreamvalley.demo.axon.order.event.OrderStatusChangedEvent;
import com.dreamvalley.demo.axon.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhangpan
 */
@Component
@Slf4j
public class OrderProjector {
    @Resource
    private OrderRepository orderRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(OrderCreatedEvent event, MetaData metaData){
        log.info("OrderProjector:OrderCreatedEvent:{},metaData:{}",event,metaData);
        orderRepository.insert(Order.builder(event).build());
    }

    @Transactional(rollbackFor = Exception.class)
    @EventHandler
    public void on(OrderStatusChangedEvent event, MetaData metaData){
        log.info("OrderProjector:OrderStatusChangedEvent:{},metaData:{}",event,metaData);
        orderRepository.update(Order.builder().id(event.getId()).remark(event.getRemark()).status(event.getStatus()).build());
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(OrderCanceledEvent event, MetaData metaData){
        log.info("OrderProjector:OrderCanceledEvent:{},metaData:{}",event,metaData);
        orderRepository.update(Order.builder().id(event.getId()).remark(event.getRemark()).status(OrderStatus.CANCEL.getCode()).build());
    }
}
