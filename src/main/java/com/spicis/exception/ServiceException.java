package com.spicis.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private String msg;

    public ServiceException(String msg) {
        this.msg = msg;
    }
}
