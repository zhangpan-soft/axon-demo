package com.dreamvalley.demo.axon.commodity.event;

import com.dreamvalley.demo.axon.commodity.command.CommodityCreateCommand;
import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 商品创建事件
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityCreatedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private String name;
    private Long price;
    private Integer stock;
    private String status;


    public static CommodityCreatedEventBuilder builder() {
        return new CommodityCreatedEventBuilder();
    }

    public static CommodityCreatedEventBuilder builder(CommodityCreateCommand command) {
        return new CommodityCreatedEventBuilder(command);
    }

    public static final class CommodityCreatedEventBuilder {
        private final CommodityCreatedEvent commodityCreatedEvent;

        private CommodityCreatedEventBuilder() {
            commodityCreatedEvent = new CommodityCreatedEvent();
        }

        public CommodityCreatedEventBuilder(CommodityCreateCommand command) {
            commodityCreatedEvent = new CommodityCreatedEvent();
            commodityCreatedEvent.setId(command.getId());
            commodityCreatedEvent.setCreatedTime(LocalDateTime.now());
            commodityCreatedEvent.setName(command.getName());
            commodityCreatedEvent.setPrice(command.getPrice());
            commodityCreatedEvent.setStatus(command.getStatus());
            commodityCreatedEvent.setStock(command.getStock());
        }


        public CommodityCreatedEventBuilder id(Long id) {
            commodityCreatedEvent.setId(id);
            return this;
        }

        public CommodityCreatedEventBuilder createdTime(LocalDateTime createdTime) {
            commodityCreatedEvent.setCreatedTime(createdTime);
            return this;
        }

        public CommodityCreatedEventBuilder name(String name) {
            commodityCreatedEvent.setName(name);
            return this;
        }

        public CommodityCreatedEventBuilder price(Long price) {
            commodityCreatedEvent.setPrice(price);
            return this;
        }

        public CommodityCreatedEventBuilder stock(Integer stock) {
            commodityCreatedEvent.setStock(stock);
            return this;
        }

        public CommodityCreatedEventBuilder status(String status) {
            commodityCreatedEvent.setStatus(status);
            return this;
        }

        public CommodityCreatedEvent build() {
            return commodityCreatedEvent;
        }
    }
}
