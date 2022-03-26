package com.dreamvalley.demo.axon.commodity.repository;

import com.dreamvalley.demo.axon.commodity.entity.Commodity;
import com.dreamvalley.demo.axon.core.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends BaseRepository<Commodity,Long> {

}
