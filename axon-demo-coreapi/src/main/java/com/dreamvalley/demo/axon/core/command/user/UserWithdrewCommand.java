package com.dreamvalley.demo.axon.core.command.user;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 提现/消费命令
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithdrewCommand implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private Long balanceAmount;



    public static UserWithdrewCommandBuilder builder() {
        return new UserWithdrewCommandBuilder();
    }

    public static final class UserWithdrewCommandBuilder {
        private final UserWithdrewCommand userWithdrewCommand;

        private UserWithdrewCommandBuilder() {
            userWithdrewCommand = new UserWithdrewCommand();
        }


        public UserWithdrewCommandBuilder id(Long id) {
            userWithdrewCommand.setId(id);
            return this;
        }

        public UserWithdrewCommandBuilder createdTime(LocalDateTime createdTime) {
            userWithdrewCommand.setCreatedTime(createdTime);
            return this;
        }

        public UserWithdrewCommandBuilder balanceAmount(Long balanceAmount) {
            userWithdrewCommand.setBalanceAmount(balanceAmount);
            return this;
        }

        public UserWithdrewCommand build() {
            return userWithdrewCommand;
        }
    }
}
