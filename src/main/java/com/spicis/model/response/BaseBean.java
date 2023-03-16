package com.spicis.model.response;

import lombok.Data;

@Data
public class BaseBean<T> {

    private Integer code;

    private String errMsg;

    private T data;

    public BaseBean() {

    }

    public BaseBean(int code, String errMsg, T data) {
        this.code = code;
        this.errMsg = errMsg;
        this.data = data;
    }
}
