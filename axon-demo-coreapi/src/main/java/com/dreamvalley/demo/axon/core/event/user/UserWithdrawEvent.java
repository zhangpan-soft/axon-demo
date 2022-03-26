package com.dreamvalley.demo.axon.core.event.user;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.core.command.user.UserWithdrewCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 提现/消费 事件
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithdrawEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Long balanceAmount;

    public static UserWithdrawEventBuilder builder() {
        return new UserWithdrawEventBuilder();
    }

    public static UserWithdrawEventBuilder builder(UserWithdrewCommand command) {
        return new UserWithdrawEventBuilder(command);
    }


    public static final class UserWithdrawEventBuilder {
        private final UserWithdrawEvent userWithdrawEvent;

        private UserWithdrawEventBuilder() {
            userWithdrawEvent = new UserWithdrawEvent();
        }

        public UserWithdrawEventBuilder(UserWithdrewCommand command) {
            userWithdrawEvent = new UserWithdrawEvent();
            userWithdrawEvent.setId(command.getId());
            userWithdrawEvent.setBalanceAmount(command.getBalanceAmount());
            userWithdrawEvent.setCreatedTime(LocalDateTime.now());
        }

        public UserWithdrawEventBuilder id(Long id) {
            userWithdrawEvent.setId(id);
            return this;
        }

        public UserWithdrawEventBuilder createdTime(LocalDateTime createdTime) {
            userWithdrawEvent.setCreatedTime(createdTime);
            return this;
        }

        public UserWithdrawEventBuilder balanceAmount(Long balanceAmount) {
            userWithdrawEvent.setBalanceAmount(balanceAmount);
            return this;
        }

        public UserWithdrawEvent build() {
            return userWithdrawEvent;
        }
    }
}
