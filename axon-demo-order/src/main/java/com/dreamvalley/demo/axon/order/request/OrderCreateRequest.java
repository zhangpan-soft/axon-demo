package com.dreamvalley.demo.axon.order.request;

import lombok.Data;

/**
 * 订单创建请求
 * @author zhangpan
 */
@Data
public class OrderCreateRequest {
    private Long commodityId;
    private Integer commodityNum;
    private Long userId;
}
