package com.dreamvalley.demo.axon.commodity.entity;

import com.dreamvalley.demo.axon.commodity.event.CommodityCreatedEvent;
import com.dreamvalley.demo.axon.core.dto.commodity.CommodityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Commodity {
    @Id
    private Long id;
    private String reversion;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String name;
    private Long price;
    private Integer stock;
    private String status;

    @Transient
    public static CommodityBuilder builder() {
        return new CommodityBuilder();
    }

    @Transient
    public static CommodityBuilder builder(CommodityCreatedEvent event) {
        return new CommodityBuilder(event);
    }

    @Transient
    public CommodityDto buildCommodityDto() {
        CommodityDto commodityDto = new CommodityDto();
        BeanUtils.copyProperties(this,commodityDto);
        return commodityDto;
    }

    public static final class CommodityBuilder {
        private Commodity commodity;

        private CommodityBuilder() {
            commodity = new Commodity();
        }

        public CommodityBuilder(CommodityCreatedEvent event) {
            commodity = new Commodity();
            commodity.id = event.getId();
            commodity.createdTime = event.getCreatedTime();
            commodity.updatedTime = event.getCreatedTime();
            commodity.name = event.getName();
            commodity.price = event.getPrice();
            commodity.stock = event.getStock();
            commodity.status = event.getStatus();
        }


        public CommodityBuilder id(Long id) {
            commodity.setId(id);
            return this;
        }

        public CommodityBuilder reversion(String reversion) {
            commodity.setReversion(reversion);
            return this;
        }

        public CommodityBuilder createdTime(LocalDateTime createdTime) {
            commodity.setCreatedTime(createdTime);
            return this;
        }

        public CommodityBuilder updatedTime(LocalDateTime updatedTime) {
            commodity.setUpdatedTime(updatedTime);
            return this;
        }

        public CommodityBuilder name(String name) {
            commodity.setName(name);
            return this;
        }

        public CommodityBuilder price(Long price) {
            commodity.setPrice(price);
            return this;
        }

        public CommodityBuilder stock(Integer stock) {
            commodity.setStock(stock);
            return this;
        }

        public CommodityBuilder status(String status) {
            commodity.setStatus(status);
            return this;
        }

        public Commodity build() {
            return commodity;
        }
    }
}
