package com.shente.cams.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author TCW
 * 统一的返回格式
 */
@Getter
@NoArgsConstructor
public class Result {

    /**
     * 状态码
     */
    private int code;

    /**
     * 附加信息
     */
    private String errMsg;


    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    public Result(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    public Result(int code, String errMsg, Object data) {
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }

    /**
     * 基本的成功回调
     * @return
     */
    public static Object baseSuccess(){
        return new Result(0,"OK");
    }

    /**
     * 带回调内容的成功
     * @param data data数据
     */
    public static Object baseSuccess(Object data){
        return new Result(0,"OK",data);
    }

    /**
     * 错误回调
     * @param code 错误状态码
     * @param errMsg 错误信息
     */
    public static Object baseError(Integer code,String errMsg){
        return new Result(code,errMsg);
    }
}
