package com.dreamvalley.demo.axon.core.command.commodity;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateVersion;

import java.time.LocalDateTime;

/**
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommodityStockAddCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    @TargetAggregateVersion
    private String reversion;
    private Integer num;
}
