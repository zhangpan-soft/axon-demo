package com.dreamvalley.demo.axon.core.event.commodity;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockSubCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommodityStockSubFailedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer num;
    private String remark;
    private Long userId;
    private Long orderId;

    public static CommodityStockSubFailedEventBuilder builder() {
        return new CommodityStockSubFailedEventBuilder();
    }

    public static CommodityStockSubFailedEventBuilder builder(CommodityStockSubCommand command) {
        return new CommodityStockSubFailedEventBuilder(command);
    }

    public static final class CommodityStockSubFailedEventBuilder {
        private final CommodityStockSubFailedEvent commodityStockSubFailedEvent;

        private CommodityStockSubFailedEventBuilder() {
            commodityStockSubFailedEvent = new CommodityStockSubFailedEvent();
        }

        public CommodityStockSubFailedEventBuilder(CommodityStockSubCommand command) {
            commodityStockSubFailedEvent = new CommodityStockSubFailedEvent();
            commodityStockSubFailedEvent.num = command.getNum();
            commodityStockSubFailedEvent.setId(command.getId());
            commodityStockSubFailedEvent.setCreatedTime(LocalDateTime.now());
            commodityStockSubFailedEvent.setId(command.getId());
            commodityStockSubFailedEvent.setUserId(command.getUserId());
            commodityStockSubFailedEvent.setOrderId(command.getOrderId());
        }


        public CommodityStockSubFailedEventBuilder id(Long id) {
            commodityStockSubFailedEvent.setId(id);
            return this;
        }

        public CommodityStockSubFailedEventBuilder createdTime(LocalDateTime createdTime) {
            commodityStockSubFailedEvent.setCreatedTime(createdTime);
            return this;
        }

        public CommodityStockSubFailedEventBuilder num(Integer num) {
            commodityStockSubFailedEvent.setNum(num);
            return this;
        }

        public CommodityStockSubFailedEventBuilder remark(String remark) {
            commodityStockSubFailedEvent.setRemark(remark);
            return this;
        }

        public CommodityStockSubFailedEventBuilder userId(Long userId) {
            commodityStockSubFailedEvent.setUserId(userId);
            return this;
        }

        public CommodityStockSubFailedEventBuilder orderId(Long orderId) {
            commodityStockSubFailedEvent.setOrderId(orderId);
            return this;
        }

        public CommodityStockSubFailedEvent build() {
            return commodityStockSubFailedEvent;
        }
    }
}
