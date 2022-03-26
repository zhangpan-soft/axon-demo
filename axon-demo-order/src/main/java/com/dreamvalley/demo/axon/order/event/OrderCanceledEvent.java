package com.dreamvalley.demo.axon.order.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.order.command.OrderCancelCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCanceledEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private String remark;


    public static OrderCanceledEventBuilder builder() {
        return new OrderCanceledEventBuilder();
    }

    public static OrderCanceledEventBuilder builder(OrderCancelCommand command) {
        return new OrderCanceledEventBuilder(command);
    }

    public static final class OrderCanceledEventBuilder {
        private final OrderCanceledEvent orderCanceledEvent;

        private OrderCanceledEventBuilder() {
            orderCanceledEvent = new OrderCanceledEvent();
        }

        public OrderCanceledEventBuilder(OrderCancelCommand command) {
            orderCanceledEvent = new OrderCanceledEvent();
            orderCanceledEvent.setRemark(command.getRemark());
            orderCanceledEvent.setId(command.getId());
            orderCanceledEvent.setCreatedTime(LocalDateTime.now());
        }


        public OrderCanceledEventBuilder id(Long id) {
            orderCanceledEvent.setId(id);
            return this;
        }

        public OrderCanceledEventBuilder createdTime(LocalDateTime createdTime) {
            orderCanceledEvent.setCreatedTime(createdTime);
            return this;
        }

        public OrderCanceledEventBuilder remark(String remark) {
            orderCanceledEvent.setRemark(remark);
            return this;
        }

        public OrderCanceledEvent build() {
            return orderCanceledEvent;
        }
    }
}
