package com.dreamvalley.demo.axon.order.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 订单
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCancelCommand implements BaseCommand<Long> {
    private Long id;
    private LocalDateTime createdTime;
    private String remark;
}
