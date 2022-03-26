package com.dreamvalley.demo.axon.user.event;

import com.dreamvalley.demo.axon.core.base.event.AbstractEvent;
import com.dreamvalley.demo.axon.core.base.event.BaseEvent;
import com.dreamvalley.demo.axon.user.command.UserCreateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 用户创建事件
 *
 * @author zhangpan
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreatedEvent extends AbstractEvent<Long> implements BaseEvent<Long> {
    private String username;


    public static UserCreatedEventBuilder builder() {
        return new UserCreatedEventBuilder();
    }

    public static UserCreatedEventBuilder builder(UserCreateCommand command) {
        return new UserCreatedEventBuilder(command);
    }


    public static final class UserCreatedEventBuilder {
        private final UserCreatedEvent userCreatedEvent;

        private UserCreatedEventBuilder() {
            userCreatedEvent = new UserCreatedEvent();
        }

        public UserCreatedEventBuilder(UserCreateCommand command) {
            userCreatedEvent = new UserCreatedEvent();
            userCreatedEvent.username = command.getUsername();
            userCreatedEvent.setCreatedTime(LocalDateTime.now());
            userCreatedEvent.setId(command.getId());
        }


        public UserCreatedEventBuilder id(Long id) {
            userCreatedEvent.setId(id);
            return this;
        }

        public UserCreatedEventBuilder createdTime(LocalDateTime createdTime) {
            userCreatedEvent.setCreatedTime(createdTime);
            return this;
        }

        public UserCreatedEventBuilder username(String username) {
            userCreatedEvent.setUsername(username);
            return this;
        }

        public UserCreatedEvent build() {
            return userCreatedEvent;
        }
    }
}
