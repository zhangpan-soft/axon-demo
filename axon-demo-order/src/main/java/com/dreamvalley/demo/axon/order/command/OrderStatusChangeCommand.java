package com.dreamvalley.demo.axon.order.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 订单状态变更命令
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangeCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private Integer status;
    private String remark;

    public static OrderStatusChangeCommandBuilder builder() {
        return new OrderStatusChangeCommandBuilder();
    }


    public static final class OrderStatusChangeCommandBuilder {
        private OrderStatusChangeCommand orderStatusChangeCommand;

        private OrderStatusChangeCommandBuilder() {
            orderStatusChangeCommand = new OrderStatusChangeCommand();
        }

        public OrderStatusChangeCommandBuilder id(Long id) {
            orderStatusChangeCommand.setId(id);
            return this;
        }

        public OrderStatusChangeCommandBuilder createdTime(LocalDateTime createdTime) {
            orderStatusChangeCommand.setCreatedTime(createdTime);
            return this;
        }

        public OrderStatusChangeCommandBuilder status(Integer status) {
            orderStatusChangeCommand.setStatus(status);
            return this;
        }

        public OrderStatusChangeCommandBuilder remark(String remark) {
            orderStatusChangeCommand.setRemark(remark);
            return this;
        }

        public OrderStatusChangeCommand build() {
            return orderStatusChangeCommand;
        }
    }
}
