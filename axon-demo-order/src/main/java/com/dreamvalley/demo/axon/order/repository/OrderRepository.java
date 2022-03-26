package com.dreamvalley.demo.axon.order.repository;

import com.dreamvalley.demo.axon.core.base.repository.BaseRepository;
import com.dreamvalley.demo.axon.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order,Long> {

}
