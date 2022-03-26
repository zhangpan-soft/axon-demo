package com.dreamvalley.demo.axon.user.command;

import com.dreamvalley.demo.axon.core.base.command.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;

/**
 * 创建用户命令
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateCommand  implements BaseCommand<Long> {
    @TargetAggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private String username;


    public static UserCreateCommandBuilder builder() {
        return new UserCreateCommandBuilder();
    }

    public static final class UserCreateCommandBuilder {
        private final UserCreateCommand userCreateCommand;

        private UserCreateCommandBuilder() {
            userCreateCommand = new UserCreateCommand();
        }


        public UserCreateCommandBuilder id(Long id) {
            userCreateCommand.setId(id);
            return this;
        }

        public UserCreateCommandBuilder createdTime(LocalDateTime createdTime) {
            userCreateCommand.setCreatedTime(createdTime);
            return this;
        }

        public UserCreateCommandBuilder username(String username) {
            userCreateCommand.setUsername(username);
            return this;
        }

        public UserCreateCommand build() {
            return userCreateCommand;
        }
    }
}
