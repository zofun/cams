package com.shente.cams.dto;

import lombok.Getter;

/**
 * @author TCW
 * 统一的返回格式
 */
@Getter
public class Result {

    /**
     * 状态码
     */
    private int code;

    /**
     * 附加信息
     */
    private String errMsg;


    /**
     * 数据
     */
    private Object data;


    private Result setResult(int code, String errMsg, Object data) {
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
        return this;
    }

    public Result success(Object object){
        return new Result().setResult(0,"OK",object);
    }

    public Result fail(Object object,String errMsg,int code){
        return new Result().setResult(code,errMsg,object);
    }
    public Result fail(String errMsg,int code){
        return new Result().setResult(code,errMsg,null);
    }



}
