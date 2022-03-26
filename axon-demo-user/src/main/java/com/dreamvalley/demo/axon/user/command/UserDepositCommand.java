package com.dreamvalley.demo.axon.user.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 充值命令
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDepositCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private Long balanceAmount;


}
