package com.dreamvalley.demo.axon.core.exceptions;

import com.dreamvalley.demo.axon.core.base.enums.Status;
import lombok.Data;

/**
 * 基本异常类
 *
 * @author zhangpan
 * @date 2021/6/10/010 15:26
 */
@Data
public class BaseException extends RuntimeException {

    private Status status;

    private String    message;
    private Throwable e;

    public BaseException(Status status) {
        super(status.getMsg());
        this.status = status;
        this.message = status.getMsg();
    }

    public BaseException(Status status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BaseException(Status status, Throwable cause) {
        super(status.getMsg(), cause);
        this.status = status;
        this.e = cause;
        this.message = status.getMsg();
    }

    public BaseException(Status status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
        this.e = cause;
    }

    public BaseException(){
        super();
    }
    public static BaseException of(Status status) {
        return new BaseException(status);
    }



    public static BaseException of(Status status, String message) {
        return new BaseException(status, message);
    }

    public static BaseException of(Status status, Throwable cause) {
        return new BaseException(status, cause);
    }

    public static BaseException of(Status status, String message, Throwable cause) {
        return new BaseException(status, message, cause);
    }

    public static BaseException of(Status status, Throwable cause, String msg) {
        return new BaseException(status, msg, cause);
    }
}
