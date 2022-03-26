package com.dreamvalley.demo.axon.commodity.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 商品创建命令
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityCreateCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private String name;
    private Long price;
    private Integer stock;
    private String status;
}
