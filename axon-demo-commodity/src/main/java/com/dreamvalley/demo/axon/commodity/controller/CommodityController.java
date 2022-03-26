package com.dreamvalley.demo.axon.commodity.controller;

import com.dreamvalley.demo.axon.commodity.command.CommodityCreateCommand;
import com.dreamvalley.demo.axon.core.base.enums.Status;
import com.dreamvalley.demo.axon.core.base.response.Result;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityFindByIdCommand;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockSubCommand;
import com.dreamvalley.demo.axon.core.dto.commodity.CommodityDto;
import com.dreamvalley.demo.axon.core.utils.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author zhangpan
 */
@Slf4j
@RestController
@RequestMapping("commodity")
public class CommodityController {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;
    @PutMapping("create")
    public Result<Object> create(@RequestBody CommodityCreateCommand command){
        command.setId(IdWorker.getNextId());
        return Result.ok(commandGateway.sendAndWait(command, MetaData.emptyInstance()));
    }

    @PutMapping("subStock")
    public Result<Object> subStock(@RequestBody CommodityStockSubCommand command){
        return Result.ok(commandGateway.sendAndWait(command,MetaData.emptyInstance()));
    }

    @PostMapping("findById")
    public Result<CommodityDto> findById(@RequestBody CommodityFindByIdCommand command){
        CompletableFuture<CommodityDto> future = queryGateway.query(command, CommodityDto.class);
        try {
            return Result.ok(future.get());
        } catch (Exception e){
            log.error("findById:",e);
            return Result.fail(Status.SYSTEM,null);
        }
    }

}
