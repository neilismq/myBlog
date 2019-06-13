package com.bj.zzq.utils;



import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zhaozhiqiang
 * @Date: 2019/6/4
 * @Description:
 */
public class CommonResponse<T> implements Serializable {
    private String code;
    private String message;
    private T body;

    public CommonResponse setCode(String code) {
        this.code = code;
        return this;
    }

    public CommonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResponse setBody(T body) {
        this.body = body;
        return this;
    }

    public static <T> CommonResponse newInstance(String code, String message, T body) {
        return new CommonResponse(code, message, body);
    }

    public static CommonResponse success() {
        return new CommonResponse("200", "操作成功", null);
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse("300", message, null);
    }

    public CommonResponse(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public CommonResponse(){

    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

}
