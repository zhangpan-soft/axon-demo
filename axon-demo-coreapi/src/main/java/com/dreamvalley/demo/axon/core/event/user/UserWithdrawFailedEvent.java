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
public class UserWithdrawFailedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Long balanceAmount;
    private String remark;

    public static UserWithdrawFailedEventBuilder builder() {
        return new UserWithdrawFailedEventBuilder();
    }

    public static UserWithdrawFailedEventBuilder builder(UserWithdrewCommand command) {
        return new UserWithdrawFailedEventBuilder(command);
    }


    public static final class UserWithdrawFailedEventBuilder {
        private final UserWithdrawFailedEvent userWithdrawEvent;

        private UserWithdrawFailedEventBuilder() {
            userWithdrawEvent = new UserWithdrawFailedEvent();
        }

        public UserWithdrawFailedEventBuilder(UserWithdrewCommand command) {
            userWithdrawEvent = new UserWithdrawFailedEvent();
            userWithdrawEvent.setId(command.getId());
            userWithdrawEvent.setBalanceAmount(command.getBalanceAmount());
            userWithdrawEvent.setCreatedTime(LocalDateTime.now());
        }

        public UserWithdrawFailedEventBuilder id(Long id) {
            userWithdrawEvent.setId(id);
            return this;
        }

        public UserWithdrawFailedEventBuilder createdTime(LocalDateTime createdTime) {
            userWithdrawEvent.setCreatedTime(createdTime);
            return this;
        }

        public UserWithdrawFailedEventBuilder balanceAmount(Long balanceAmount) {
            userWithdrawEvent.setBalanceAmount(balanceAmount);
            return this;
        }

        public UserWithdrawFailedEventBuilder remark(String remark){
            userWithdrawEvent.setRemark(remark);
            return this;
        }

        public UserWithdrawFailedEvent build() {
            return userWithdrawEvent;
        }
    }
}
