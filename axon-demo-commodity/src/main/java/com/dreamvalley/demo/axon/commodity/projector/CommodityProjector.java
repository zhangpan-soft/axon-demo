package com.dreamvalley.demo.axon.commodity.projector;

import com.dreamvalley.demo.axon.commodity.entity.Commodity;
import com.dreamvalley.demo.axon.commodity.event.CommodityCreatedEvent;
import com.dreamvalley.demo.axon.commodity.repository.CommodityRepository;
import com.dreamvalley.demo.axon.core.command.commodity.CommodityFindByIdCommand;
import com.dreamvalley.demo.axon.core.dto.commodity.CommodityDto;
import com.dreamvalley.demo.axon.core.event.commodity.CommodityStockSubSucceedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zhangpan
 */
@Component
@Slf4j
public class CommodityProjector {
    @Resource
    private CommodityRepository commodityRepository;

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(CommodityCreatedEvent event, MetaData metaData){
        log.info("CommodityProjector:CommodityCreatedEvent:{},metaData:{}",event,metaData);
        commodityRepository.insert(Commodity.builder(event).build());
    }

    @EventHandler
    @Transactional(rollbackFor = Exception.class)
    public void on(CommodityStockSubSucceedEvent event, MetaData metaData){
        log.info("CommodityProjector:CommodityStockSubSucceedEvent:{},metaData:{}",event,metaData);
        Commodity commodity = commodityRepository.getOne(event.getId());
        commodity.setStock(commodity.getStock()-event.getNum());
    }

    @QueryHandler
    public CommodityDto on(CommodityFindByIdCommand command){
        Optional<Commodity> optional = commodityRepository.findById(command.getId());
        return optional.map(Commodity::buildCommodityDto).orElse(null);
    }
}
