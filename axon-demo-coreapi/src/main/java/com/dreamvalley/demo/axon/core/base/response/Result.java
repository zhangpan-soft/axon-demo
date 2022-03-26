package com.dreamvalley.demo.axon.core.base.response;

import com.dreamvalley.demo.axon.core.base.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统一返回体
 *
 * @author zhangpan
 * @date 2021/6/10/010 14:27
 */
@Data
@ToString
public class Result<T> implements Serializable, IResult<T> {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("timestamp")
    private  LocalDateTime timestamp;
    @JsonProperty("data")
    private T data;

    public Result() {
        this(Status.OK);
    }

    public Result(Status status, String msg, T data) {
        this.code = status.getCode();
        this.msg = msg;
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public Result(Status status, T data) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.timestamp = LocalDateTime.now();
        this.data = data;
    }

    public Result(Status status) {
        this(status, null);
    }

    /**
     * 成功
     *
     * @return {@link Result}
     */
    public static Result<Void> ok() {
        return new Result<Void>(Status.OK);
    }

    /**
     * 成功
     *
     * @param data 数据
     * @return {@link Result}
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(Status.OK, data);
    }

    /**
     * 成功
     *
     * @param msg  提示
     * @param data 数据
     * @return {@link Result}
     */
    public static <T> Result<T> ok(String msg, T data) {
        return new Result<T>(Status.OK, msg, data);
    }

    /**
     * 未知失败
     *
     * @return {@link Result}
     */
    public static Result<Void> fail() {
        return new Result<>(Status.UNKNOWN);
    }

    /**
     * 失败
     *
     * @param status 状态码
     * @return {@link Result}
     */
    public static Result<Void> fail(Status status) {
        return new Result<>(status);
    }

    /**
     * 失败
     *
     * @param status 状态码
     * @param data   异常|错误数据
     * @return {@link Result}
     */
    public static <T> Result<T> fail(Status status, T data) {
        return new Result<T>(status, data);
    }

    /**
     * 失败
     *
     * @param status 错误码
     * @param msg    错误描述
     * @param data   异常|错误数据
     * @return {@link Result}
     */
    public static <T> Result<T> fail(Status status, String msg, T data) {
        if (StringUtils.isEmpty(msg)){
            return fail(status,data);
        }
        return new Result<T>(status, msg, data);
    }
}
