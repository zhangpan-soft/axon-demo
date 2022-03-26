package com.dreamvalley.demo.axon.core.feign;

import com.dreamvalley.demo.axon.core.base.response.Result;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityStockSubCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "axon-demo-commodity")
public interface CommodityFeign {

    @PutMapping("commodity/subStock")
    Result<?> subStock(@RequestBody CommodityStockSubCommand command);
}
