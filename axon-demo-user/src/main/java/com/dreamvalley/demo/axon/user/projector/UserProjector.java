package com.dreamvalley.demo.axon.user.projector;

import com.dreamvalley.demo.axon.user.entity.User;
import com.dreamvalley.demo.axon.user.event.UserCreatedEvent;
import com.dreamvalley.demo.axon.user.event.UserDepositedEvent;
import com.dreamvalley.demo.axon.user.event.UserWithdrawEvent;
import com.dreamvalley.demo.axon.user.repository.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 事件物化视图handle
 *
 * @author zhangpan
 */
@Component
public class UserProjector {
    @Resource
    private UserRepository userRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public User on(UserCreatedEvent event){
        return userRepository.insert(User.builder(event).build());
    }

    @QueryHandler(queryName = "findOne")
    public User findOne(User user){
        return userRepository.findOne(Example.of(user)).orElse(null);
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(UserDepositedEvent event){
        User one = userRepository.getOne(event.getId());
        one.setBalanceAmount(one.getBalanceAmount()+event.getBalanceAmount());
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(UserWithdrawEvent event){
        User one = userRepository.getOne(event.getId());
        one.setBalanceAmount(one.getBalanceAmount()- event.getBalanceAmount());
    }
}
