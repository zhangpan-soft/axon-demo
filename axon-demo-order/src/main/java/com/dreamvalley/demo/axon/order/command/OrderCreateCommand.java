package com.dreamvalley.demo.axon.order.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 订单创建命令
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private Integer status;
    private String remark;
    private Long totalAmount;
    private Long userId;
    private Long commodityId;
    private Integer commodityNum;
}
