package com.dreamvalley.demo.axon.core.exceptions;

import com.dreamvalley.demo.axon.core.base.enums.Status;
import com.dreamvalley.demo.axon.core.base.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;

/**
 * 全局异常处理
 * @author zhangpan
 * @date 2021/6/17/017 11:29
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Value("${report.ding.url:'https://oapi.dingtalk.com/robot/send?access_token=8bfcb12f95f67285afa9a0e5764eb96a3503cbd669f94d6ec993a4745bd58183'}")
    private String dingReportUrl;

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public Result mediaTypeNotAcceptable(HttpServletRequest request, HttpServletResponse response,HttpMediaTypeNotSupportedException e) {
        log.error("MediaTypeNotAcceptableException:",e);
        HttpStatus status = getStatus(request);
        log.debug("status:{}",status);
        response.setStatus(HttpStatus.OK.value());
        return Result.fail(Status.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result<Object> methodNotSupported(HttpServletRequest request,HttpServletResponse response,HttpRequestMethodNotSupportedException e){
        log.error("HttpRequestMethodNotSupportedException:",e);
        HttpStatus status = getStatus(request);
        response.setStatus(HttpStatus.OK.value());
        return Result.fail(Status.BAD_REQUEST,e.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseBody
    public Result responseStatusException(HttpServletRequest request, HttpServletResponse response,ResponseStatusException ex){
        log.error("ResponseStatusException:",ex);
        HttpStatus httpStatus = ex.getStatus();
        Status status = null;
        try {
            response.setStatus(httpStatus.value());
            status = Status.valueOf(httpStatus.name());
        } catch (Exception e) {
            response.setStatus(HttpStatus.OK.value());
            status = Status.UNKNOWN;
        }
        return Result.fail(status);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public Result businessException(BaseException e,HttpServletResponse response){
        log.error("BaseException:",e);
        response.setStatus(HttpStatus.OK.value());
        return Result.fail(e.getStatus());
    }

    @ExceptionHandler(ExecutionException.class)
    @ResponseBody
    public Result executionException(ExecutionException e,HttpServletResponse response){
        log.error("ExecutionException:",e);
        response.setStatus(HttpStatus.OK.value());
        if (e.getCause() instanceof BaseException){
            return Result.fail(((BaseException) e.getCause()).getStatus(),e.getCause().getMessage());
        }
        return Result.fail();
    }

    private Result<Void> paramsErrorResult(HttpServletResponse response,FieldError fieldError){
        response.setStatus(HttpStatus.OK.value());
        if (fieldError==null){
            return Result.fail(Status.REQUEST_PARAM_ERROR);
        }
        return Result.fail(Status.REQUEST_PARAM_ERROR,fieldError.getDefaultMessage(),null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> methodArgumentNotValidException(MethodArgumentNotValidException e,HttpServletResponse response){
        log.error("MethodArgumentNotValidException:",e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return paramsErrorResult(response,fieldError);
    }

    @ExceptionHandler(BindException.class)
    public Result<Void> bindException(BindException e,HttpServletResponse response){
        log.error("BaseException:",e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        return paramsErrorResult(response,fieldError);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exception(Exception e,HttpServletResponse response){
        response.setStatus(HttpStatus.OK.value());
        log.error("其他异常:",e);
        return Result.fail(Status.SYSTEM);
    }
}
