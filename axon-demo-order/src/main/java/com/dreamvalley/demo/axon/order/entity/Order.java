package com.dreamvalley.demo.axon.order.entity;

import com.dreamvalley.demo.axon.order.event.OrderCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "`order`")
public class Order {
    @Id
    private Long id;
    private String reversion;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Integer status;
    private String remark;
    private Long totalAmount;


    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static OrderBuilder builder(OrderCreatedEvent event) {
        return new OrderBuilder(event);
    }

    public static final class OrderBuilder {
        private Order order;

        private OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder(OrderCreatedEvent event) {
            order = new Order();
            order.id = event.getId();
            order.createdTime = event.getCreatedTime();
            order.updatedTime = event.getCreatedTime();
            order.status = event.getStatus();
            order.remark = event.getRemark();
            order.totalAmount = event.getTotalAmount();
        }

        public OrderBuilder id(Long id) {
            order.setId(id);
            return this;
        }

        public OrderBuilder reversion(String reversion) {
            order.setReversion(reversion);
            return this;
        }

        public OrderBuilder createdTime(LocalDateTime createdTime) {
            order.setCreatedTime(createdTime);
            return this;
        }

        public OrderBuilder updatedTime(LocalDateTime updatedTime) {
            order.setUpdatedTime(updatedTime);
            return this;
        }

        public OrderBuilder status(Integer status) {
            order.setStatus(status);
            return this;
        }

        public OrderBuilder remark(String remark) {
            order.setRemark(remark);
            return this;
        }

        public OrderBuilder totalAmount(Long totalAmount) {
            order.setTotalAmount(totalAmount);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
