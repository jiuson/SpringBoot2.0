package com.yipinketang.app.exception;

import lombok.Data;

@Data
public class ControllerException extends RuntimeException {

    private Integer errorCode;

    public ControllerException(String message, Integer errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
