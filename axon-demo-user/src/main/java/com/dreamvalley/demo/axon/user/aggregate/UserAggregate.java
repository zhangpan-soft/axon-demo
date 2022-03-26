package com.dreamvalley.demo.axon.user.aggregate;

import com.dreamvalley.demo.axon.core.base.aggregate.BaseAggregate;
import com.dreamvalley.demo.axon.core.base.enums.Status;
import com.dreamvalley.demo.axon.core.command.user.UserWithdrewCommand;
import com.dreamvalley.demo.axon.core.exceptions.BaseException;
import com.dreamvalley.demo.axon.core.utils.SpringContextHolder;
import com.dreamvalley.demo.axon.user.command.UserCreateCommand;
import com.dreamvalley.demo.axon.user.command.UserDepositCommand;
import com.dreamvalley.demo.axon.user.entity.User;
import com.dreamvalley.demo.axon.user.event.UserCreatedEvent;
import com.dreamvalley.demo.axon.user.event.UserDepositedEvent;
import com.dreamvalley.demo.axon.user.event.UserWithdrawEvent;
import com.dreamvalley.demo.axon.user.event.UserWithdrawFailedEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;


/**
 * 用户聚合对象
 *
 * @author zhangpan
 */
@Data
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserAggregate implements BaseAggregate<Long> {
    @AggregateIdentifier
    private Long id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private Long balanceAmount;
    private String status;
    private String username;

    @CommandHandler
    public UserAggregate(UserCreateCommand command, MetaData metaData) {
        CompletableFuture<User> userCompletableFuture = SpringContextHolder.getQueryGateway().query("findOne", User.builder().username(command.getUsername()).build(), User.class);
        User user = null;
        try {
            user = userCompletableFuture.get();
        } catch (Exception e) {
            throw BaseException.of(Status.SYSTEM);
        }
        if (user != null) {
            throw BaseException.of(Status.USER_ALREADY_EXIST);
        }

        AggregateLifecycle.apply(UserCreatedEvent.builder(command).build(), metaData);
    }

    @CommandHandler
    public void handle(UserDepositCommand command, MetaData metaData) {
        log.info("UserDepositCommand:{},metaData:{}", command, metaData);
        AggregateLifecycle.apply(UserDepositedEvent.builder(command).build(), metaData);
    }

    @CommandHandler
    public void handle(UserWithdrewCommand command, MetaData metaData){
        log.info("UserWithdrewCommand:{},metaData:{}", command, metaData);
        if (command.getBalanceAmount()<=0){
            AggregateLifecycle.apply(UserWithdrawFailedEvent.builder(command).remark("参数不对").build(),metaData);
        }else if (this.balanceAmount<command.getBalanceAmount()){
            AggregateLifecycle.apply(UserWithdrawFailedEvent.builder(command).remark("余额不足").build(),metaData);
        }else {
            AggregateLifecycle.apply(UserWithdrawEvent.builder(command).build(),metaData);
        }

    }

    @EventSourcingHandler
    public void on(UserWithdrawEvent event,MetaData metaData){
        log.info("UserWithdrawEvent:{},metaData:{}", event, metaData);
        this.balanceAmount -= event.getBalanceAmount();
        this.updatedTime = LocalDateTime.now();
    }

    @EventSourcingHandler
    public void on(UserDepositedEvent event, MetaData metaData) {
        log.info("UserDepositedEvent:{},metaData:{}", event, metaData);
        this.balanceAmount += event.getBalanceAmount();
        this.updatedTime = LocalDateTime.now();
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent event, MetaData metaData) {
        log.info("UserCreatedEvent:{},metaData:{}", event, metaData);
        this.setId(event.getId());
        this.balanceAmount = 0L;
        this.status = "1";
        this.username = event.getUsername();
        this.setCreatedTime(LocalDateTime.now());
        this.setUpdatedTime(LocalDateTime.now());
    }

    @EventSourcingHandler
    public void on(UserWithdrawFailedEvent event,MetaData metaData){
        log.info("UserCreatedEvent:{},metaData:{}", event, metaData);
    }
}
