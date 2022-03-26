package com.dreamvalley.demo.axon.user.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.user.command.UserDepositCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * 充值事件
 * @author zhangpan
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDepositedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private Long balanceAmount;

    public static UserDepositedEventBuilder builder() {
        return new UserDepositedEventBuilder();
    }

    public static UserDepositedEventBuilder builder(UserDepositCommand command) {
        return new UserDepositedEventBuilder(command);
    }

    public static final class UserDepositedEventBuilder {
        private final UserDepositedEvent userDepositedEvent;

        private UserDepositedEventBuilder() {
            userDepositedEvent = new UserDepositedEvent();
        }

        public UserDepositedEventBuilder(UserDepositCommand command) {
            userDepositedEvent = new UserDepositedEvent();
            userDepositedEvent.balanceAmount = command.getBalanceAmount();
            userDepositedEvent.setCreatedTime(LocalDateTime.now());
            userDepositedEvent.setId(command.getId());
        }


        public UserDepositedEventBuilder id(Long id) {
            userDepositedEvent.setId(id);
            return this;
        }

        public UserDepositedEventBuilder createdTime(LocalDateTime createdTime) {
            userDepositedEvent.setCreatedTime(createdTime);
            return this;
        }

        public UserDepositedEventBuilder balanceAmount(Long balanceAmount) {
            userDepositedEvent.setBalanceAmount(balanceAmount);
            return this;
        }

        public UserDepositedEvent build() {
            return userDepositedEvent;
        }
    }
}
