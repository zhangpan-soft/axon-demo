package com.dreamvalley.demo.axon.commodity.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockAddCommand;
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
public class CommodityStockAddSucceedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer num;
    private String remark;


    public static CommodityStockAddSucceedEventBuilder builder() {
        return new CommodityStockAddSucceedEventBuilder();
    }

    public static CommodityStockAddSucceedEventBuilder builder(CommodityStockAddCommand command) {
        return new CommodityStockAddSucceedEventBuilder(command);
    }


    public static final class CommodityStockAddSucceedEventBuilder {
        private CommodityStockAddSucceedEvent commodityStockAddSucceedEvent;

        private CommodityStockAddSucceedEventBuilder() {
            commodityStockAddSucceedEvent = new CommodityStockAddSucceedEvent();
        }

        public CommodityStockAddSucceedEventBuilder(CommodityStockAddCommand command) {
            commodityStockAddSucceedEvent = new CommodityStockAddSucceedEvent();
            commodityStockAddSucceedEvent.setNum(command.getNum());
            commodityStockAddSucceedEvent.setCreatedTime(LocalDateTime.now());
            commodityStockAddSucceedEvent.setId(command.getId());
        }

        public CommodityStockAddSucceedEventBuilder num(Integer num) {
            commodityStockAddSucceedEvent.setNum(num);
            return this;
        }

        public CommodityStockAddSucceedEventBuilder remark(String remark) {
            commodityStockAddSucceedEvent.setRemark(remark);
            return this;
        }

        public CommodityStockAddSucceedEventBuilder id(Long id) {
            commodityStockAddSucceedEvent.setId(id);
            return this;
        }

        public CommodityStockAddSucceedEventBuilder createdTime(LocalDateTime createdTime) {
            commodityStockAddSucceedEvent.setCreatedTime(createdTime);
            return this;
        }

        public CommodityStockAddSucceedEvent build() {
            return commodityStockAddSucceedEvent;
        }
    }
}
