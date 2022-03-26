package com.dreamvalley.demo.axon.user.entity;

import com.dreamvalley.demo.axon.core.base.entity.BaseEntity;
import com.dreamvalley.demo.axon.user.event.UserCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author zhangpan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
@Entity
public class User implements BaseEntity<Long> {
    @Id
    private Long id;
    @Column
    private String reversion;
    @Column
    private LocalDateTime createdTime;
    @Column
    private LocalDateTime updatedTime;
    @Column
    private String username;
    @Column
    private Long balanceAmount;
    @Column(name = "`status`")
    private String status;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static UserBuilder builder(User user) {
        return new UserBuilder(user);
    }

    public static UserBuilder builder(UserCreatedEvent event){
        return new UserBuilder(event);
    }

    public static final class UserBuilder {
        private final User user;

        private UserBuilder() {
            user = new User();
        }

        private UserBuilder(User user) {
            this.user = user;
        }

        private UserBuilder(UserCreatedEvent event){
            this.user = new User();
            this.user.setId(event.getId());
            this.user.setUsername(event.getUsername());
            this.user.setStatus("1");
            this.user.setBalanceAmount(0L);
            this.user.setCreatedTime(LocalDateTime.now());
            this.user.setUpdatedTime(LocalDateTime.now());
        }

        public UserBuilder id(Long id) {
            user.setId(id);
            return this;
        }

        public UserBuilder reversion(String reversion) {
            user.setReversion(reversion);
            return this;
        }

        public UserBuilder createdTime(LocalDateTime createdTime) {
            user.setCreatedTime(createdTime);
            return this;
        }

        public UserBuilder updatedTime(LocalDateTime updatedTime) {
            user.setUpdatedTime(updatedTime);
            return this;
        }

        public UserBuilder username(String username) {
            user.setUsername(username);
            return this;
        }

        public UserBuilder balanceAmount(Long balanceAmount) {
            user.setBalanceAmount(balanceAmount);
            return this;
        }

        public UserBuilder status(String status) {
            user.setStatus(status);
            return this;
        }

        public User build() {
            return user;
        }
    }
}
