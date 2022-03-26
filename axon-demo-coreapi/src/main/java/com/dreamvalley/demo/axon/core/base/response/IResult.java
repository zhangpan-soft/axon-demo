package com.dreamvalley.demo.axon.core.base.response;

import java.io.Serializable;

/**
 * 定义抽象返回体
 * @author zhangpan
 */
public interface IResult<T> extends Serializable {

    /**
     * 设置状态码
     * @param code 状态码
     */
    void setCode(Integer code);

    /**
     * 获取状态码
     * @return {@link Integer}
     */
    Integer getCode();

    /**
     * 设置提示信息
     * @param msg 提示信息
     */
    void setMsg(String msg);

    /**
     * 获取提示信息
     * @return {@link String}
     */
    String getMsg();

    /**
     * 设置返回内容
     * @param data 业务内容
     */
    void setData(T data);

    /**
     * 返回业务内容
     * @return {@link T}
     */
    T getData();
}
