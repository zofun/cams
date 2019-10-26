package com.shente.cams.exception;

import com.shente.cams.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author TCW
 * 全局异常捕捉处理
 */
@RestControllerAdvice
public class ExceptionsHandler {
    private final Logger logger =LoggerFactory.getLogger(ExceptionsHandler.class);

    /**
     * 基本异常
     */
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        logger.error(e.getMessage(), e);
        return new Result().fail(e.getMessage(),"Error", 500);
    }

    /**
     * 请求路径无法找到异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result notFoundException() {
        return new Result().fail("Not found", 404);
    }

    /**
     * 请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result httpRequestMethodNotSupportedException() {
        return new Result().fail("Method not allowed", 405);
    }

    @ExceptionHandler(NullPointerException.class)
    public Result NullPointerException(){return new Result().fail("nullPointerException",500);}

    /**
     * 请求参数异常
     */
    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class, MissingServletRequestPartException.class, BindException.class})
    public Result parameterException() {
        return new Result().fail("Parameter error", 403);
    }

    /**
     * 上传文件过大异常
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result maxUploadSizeExceededException() {
        return new Result().fail("File is too large", 403);
    }

    /**
     * 服务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return new Result().fail(e.getData(), e.getMessage(), e.getCode());
    }
}
