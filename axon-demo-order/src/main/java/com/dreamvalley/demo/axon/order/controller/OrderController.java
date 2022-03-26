package com.dreamvalley.demo.axon.order.controller;

import com.dreamvalley.demo.axon.core.base.enums.Status;
import com.dreamvalley.demo.axon.core.base.response.Result;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityFindByIdCommand;
import com.dreamvalley.demo.axon.core.dto.commodity.CommodityDto;
import com.dreamvalley.demo.axon.core.utils.IdWorker;
import com.dreamvalley.demo.axon.order.command.OrderCreateCommand;
import com.dreamvalley.demo.axon.order.enums.OrderStatus;
import com.dreamvalley.demo.axon.order.request.OrderCreateRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;

@RequestMapping("order")
@RestController
public class OrderController {
    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 创建订单
     * @param request
     * @return
     */
    @PutMapping("create")
    public Result<?> create(@RequestBody OrderCreateRequest request){
        MetaData metaData = MetaData.emptyInstance();
        HttpHeaders headers = new HttpHeaders();
        RequestEntity<CommodityFindByIdCommand> requestEntity = new RequestEntity<>(new CommodityFindByIdCommand(request.getCommodityId()),headers,HttpMethod.POST, URI.create("http://localhost:8010/commodity/findById"));
        ResponseEntity<Result<CommodityDto>> responseEntity = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Result<CommodityDto>>() {});
        Result<CommodityDto> result = responseEntity.getBody();
        if (0 !=result.getCode()){
            return result;
        }
        CommodityDto commodityDto = result.getData();
        if (commodityDto==null){
            return Result.fail(Status.DATA_NOT_EXISTS,null);
        }
        Long totalAmount = commodityDto.getPrice()* request.getCommodityNum();
        OrderCreateCommand orderCreateCommand = new OrderCreateCommand(
                IdWorker.getNextId(),
                LocalDateTime.now(),
                OrderStatus.CREATE.getCode(),
                "订单创建中",
                totalAmount,
                request.getUserId(),
                request.getCommodityId(),
                request.getCommodityNum());
        return Result.ok(commandGateway.sendAndWait(orderCreateCommand, metaData));
    }
}
