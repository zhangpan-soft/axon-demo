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
public class CommodityStockAddFailedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Integer num;
    private String remark;


    public static CommodityStockAddFailedEventBuilder builder() {
        return new CommodityStockAddFailedEventBuilder();
    }

    public static CommodityStockAddFailedEventBuilder builder(CommodityStockAddCommand command) {
        return new CommodityStockAddFailedEventBuilder(command);
    }


    public static final class CommodityStockAddFailedEventBuilder {
        private CommodityStockAddFailedEvent commodityStockAddFailedEvent;

        private CommodityStockAddFailedEventBuilder() {
            commodityStockAddFailedEvent = new CommodityStockAddFailedEvent();
        }

        public CommodityStockAddFailedEventBuilder(CommodityStockAddCommand command) {
            commodityStockAddFailedEvent = new CommodityStockAddFailedEvent();
            commodityStockAddFailedEvent.setNum(command.getNum());
            commodityStockAddFailedEvent.setCreatedTime(LocalDateTime.now());
            commodityStockAddFailedEvent.setId(command.getId());
        }

        public CommodityStockAddFailedEventBuilder num(Integer num) {
            commodityStockAddFailedEvent.setNum(num);
            return this;
        }

        public CommodityStockAddFailedEventBuilder remark(String remark) {
            commodityStockAddFailedEvent.setRemark(remark);
            return this;
        }

        public CommodityStockAddFailedEventBuilder id(Long id) {
            commodityStockAddFailedEvent.setId(id);
            return this;
        }

        public CommodityStockAddFailedEventBuilder createdTime(LocalDateTime createdTime) {
            commodityStockAddFailedEvent.setCreatedTime(createdTime);
            return this;
        }

        public CommodityStockAddFailedEvent build() {
            return commodityStockAddFailedEvent;
        }
    }
}
