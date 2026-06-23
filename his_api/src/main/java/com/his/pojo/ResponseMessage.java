package com.his.pojo;

import org.springframework.http.HttpStatus;

public class ResponseMessage<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseMessage(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 请求成功（有数据）
    public static <T> ResponseMessage<T> success(T data) {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success!", data);
    }

    // 请求成功（无数据）
    public static <T> ResponseMessage<T> success() {
        return new ResponseMessage<>(HttpStatus.OK.value(), "success!", null);
    }

    // 请求失败（仅错误信息）
    public static <T> ResponseMessage<T> error(String message) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), message, null);
    }

    // 请求失败（带数据）
    public static <T> ResponseMessage<T> error(String message, T data) {
        return new ResponseMessage<>(HttpStatus.BAD_REQUEST.value(), message, data);
    }

    // 自定义错误码
    public static <T> ResponseMessage<T> error(int code, String message) {
        return new ResponseMessage<>(code, message, null);
    }

    // 自定义错误码 + 数据
    public static <T> ResponseMessage<T> error(int code, String message, T data) {
        return new ResponseMessage<>(code, message, data);
    }

    // getter 和 setter
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
