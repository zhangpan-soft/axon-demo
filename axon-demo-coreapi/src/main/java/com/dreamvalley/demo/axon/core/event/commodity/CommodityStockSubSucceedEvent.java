package com.dreamvalley.demo.axon.core.event.commodity;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
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
public class CommodityStockSubSucceedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer num;
    private Long userId;
    private Long orderId;
    private Long orderTotalAmount;


    public static CommodityStockSubSucceedEventBuilder builder() {
        return new CommodityStockSubSucceedEventBuilder();
    }

    public static final class CommodityStockSubSucceedEventBuilder {
        private final CommodityStockSubSucceedEvent commodityStockSubSucceedEvent;

        private CommodityStockSubSucceedEventBuilder() {
            commodityStockSubSucceedEvent = new CommodityStockSubSucceedEvent();
        }


        public CommodityStockSubSucceedEventBuilder id(Long id) {
            commodityStockSubSucceedEvent.setId(id);
            return this;
        }

        public CommodityStockSubSucceedEventBuilder createdTime(LocalDateTime createdTime) {
            commodityStockSubSucceedEvent.setCreatedTime(createdTime);
            return this;
        }

        public CommodityStockSubSucceedEventBuilder num(Integer num) {
            commodityStockSubSucceedEvent.setNum(num);
            return this;
        }


        public CommodityStockSubSucceedEventBuilder userId(Long userId) {
            commodityStockSubSucceedEvent.setUserId(userId);
            return this;
        }

        public CommodityStockSubSucceedEventBuilder orderId(Long orderId) {
            commodityStockSubSucceedEvent.setOrderId(orderId);
            return this;
        }

        public CommodityStockSubSucceedEventBuilder orderTotalAmount(Long orderTotalAmount){
            commodityStockSubSucceedEvent.setOrderTotalAmount(orderTotalAmount);
            return this;
        }

        public CommodityStockSubSucceedEvent build() {
            return commodityStockSubSucceedEvent;
        }
    }
}
