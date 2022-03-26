package com.dreamvalley.demo.axon.core.command.commodity;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityStockSubCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private Integer num;
    private Long orderId;
    private Long userId;
    private Long orderTotalAmount;


    public static CommodityStockSubCommandBuilder builder() {
        return new CommodityStockSubCommandBuilder();
    }

    public static final class CommodityStockSubCommandBuilder {
        private CommodityStockSubCommand commodityStockSubCommand;

        private CommodityStockSubCommandBuilder() {
            commodityStockSubCommand = new CommodityStockSubCommand();
        }

        public CommodityStockSubCommandBuilder id(Long id) {
            commodityStockSubCommand.setId(id);
            return this;
        }

        public CommodityStockSubCommandBuilder createdTime(LocalDateTime createdTime) {
            commodityStockSubCommand.setCreatedTime(createdTime);
            return this;
        }

        public CommodityStockSubCommandBuilder num(Integer num) {
            commodityStockSubCommand.setNum(num);
            return this;
        }

        public CommodityStockSubCommandBuilder userId(Long userId){
            commodityStockSubCommand.setUserId(userId);
            return this;
        }

        public CommodityStockSubCommandBuilder orderId(Long orderId){
            commodityStockSubCommand.setOrderId(orderId);
            return this;
        }

        public CommodityStockSubCommandBuilder orderTotalAmount(Long orderTotalAmount){
            commodityStockSubCommand.setOrderTotalAmount(orderTotalAmount);
            return this;
        }

        public CommodityStockSubCommand build() {
            return commodityStockSubCommand;
        }
    }
}
