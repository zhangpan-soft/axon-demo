package com.dreamvalley.demo.axon.user.controller;

import com.dreamvalley.demo.axon.core.base.response.Result;
import com.dreamvalley.demo.axon.core.utils.IdWorker;
import com.dreamvalley.demo.axon.user.command.UserCreateCommand;
import com.dreamvalley.demo.axon.user.command.UserDepositCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用户控制器
 *
 * @author zhangpan
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private CommandGateway commandGateway;

    @PutMapping("create")
    public Result<Object> create(@RequestBody UserCreateCommand command){
        command.setId(IdWorker.getNextId());
        Object o = commandGateway.sendAndWait(command, MetaData.emptyInstance().andIfNotPresent("traceId",()->UUID.randomUUID().toString()));
        return Result.ok(o);
    }

    @PutMapping("deposit")
    public Result<Object> deposit(@RequestBody UserDepositCommand command){
        return Result.ok(commandGateway.sendAndWait(command,MetaData.emptyInstance()));
    }
}
