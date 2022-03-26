package com.dreamvalley.demo.axon.order.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.order.command.OrderCreateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 订单创建事件
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer status;
    private String remark;
    private Long totalAmount;
    private Long userId;
    private Long commodityId;
    private Integer commodityNum;


    public static OrderCreatedEventBuilder builder() {
        return new OrderCreatedEventBuilder();
    }

    public static OrderCreatedEventBuilder builder(OrderCreateCommand command) {
        return new OrderCreatedEventBuilder(command);
    }

    public static final class OrderCreatedEventBuilder {
        private final OrderCreatedEvent orderCreatedEvent;

        private OrderCreatedEventBuilder() {
            orderCreatedEvent = new OrderCreatedEvent();
        }

        public OrderCreatedEventBuilder(OrderCreateCommand command) {
            orderCreatedEvent = new OrderCreatedEvent();
            orderCreatedEvent.setRemark(command.getRemark());
            orderCreatedEvent.setStatus(command.getStatus());
            orderCreatedEvent.setTotalAmount(command.getTotalAmount());
            orderCreatedEvent.setCreatedTime(LocalDateTime.now());
            orderCreatedEvent.setId(command.getId());
            orderCreatedEvent.setUserId(command.getUserId());
            orderCreatedEvent.setCommodityId(command.getCommodityId());
            orderCreatedEvent.setCommodityNum(command.getCommodityNum());
        }


        public OrderCreatedEventBuilder id(Long id) {
            orderCreatedEvent.setId(id);
            return this;
        }

        public OrderCreatedEventBuilder createdTime(LocalDateTime createdTime) {
            orderCreatedEvent.setCreatedTime(createdTime);
            return this;
        }

        public OrderCreatedEventBuilder status(Integer status) {
            orderCreatedEvent.setStatus(status);
            return this;
        }

        public OrderCreatedEventBuilder remark(String remark) {
            orderCreatedEvent.setRemark(remark);
            return this;
        }

        public OrderCreatedEventBuilder totalAmount(Long totalAmount) {
            orderCreatedEvent.setTotalAmount(totalAmount);
            return this;
        }

        public OrderCreatedEventBuilder commodityNum(Integer commodityNum){
            orderCreatedEvent.setCommodityNum(commodityNum);
            return this;
        }

        public OrderCreatedEvent build() {
            return orderCreatedEvent;
        }
    }
}
