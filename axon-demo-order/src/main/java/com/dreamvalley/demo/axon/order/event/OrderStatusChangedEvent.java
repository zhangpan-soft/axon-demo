package com.dreamvalley.demo.axon.order.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.order.command.OrderStatusChangeCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 订单状态修改事件
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer status;
    private String remark;


    public static OrderStatusChangedEventBuilder builder() {
        return new OrderStatusChangedEventBuilder();
    }

    public static OrderStatusChangedEventBuilder builder(OrderStatusChangeCommand command) {
        return new OrderStatusChangedEventBuilder(command);
    }

    public static final class OrderStatusChangedEventBuilder {
        private OrderStatusChangedEvent orderStatusChangedEvent;

        private OrderStatusChangedEventBuilder() {
            orderStatusChangedEvent = new OrderStatusChangedEvent();
        }

        public OrderStatusChangedEventBuilder(OrderStatusChangeCommand command) {
            orderStatusChangedEvent = new OrderStatusChangedEvent();
            orderStatusChangedEvent.setId(command.getId());
            orderStatusChangedEvent.setStatus(command.getStatus());
            orderStatusChangedEvent.setRemark(command.getRemark());
            orderStatusChangedEvent.setCreatedTime(LocalDateTime.now());
        }

        public OrderStatusChangedEventBuilder id(Long id) {
            orderStatusChangedEvent.setId(id);
            return this;
        }

        public OrderStatusChangedEventBuilder createdTime(LocalDateTime createdTime) {
            orderStatusChangedEvent.setCreatedTime(createdTime);
            return this;
        }

        public OrderStatusChangedEventBuilder status(Integer status) {
            orderStatusChangedEvent.setStatus(status);
            return this;
        }

        public OrderStatusChangedEventBuilder remark(String remark) {
            orderStatusChangedEvent.setRemark(remark);
            return this;
        }

        public OrderStatusChangedEvent build() {
            return orderStatusChangedEvent;
        }
    }
}
