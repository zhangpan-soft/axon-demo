package com.dreamvalley.demo.axon.order.enums;

import lombok.Getter;

/**
 * @author zhangpan
 */
@Getter
public enum OrderStatus {
    /**
     *
     */
    CREATE(0,"创建中"),
    CANCEL(-1,"订单取消"),
    PAYED(1,"支付中"),
    FINISH(2,""),
;
    private int code;
    private String msg;
    OrderStatus(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
