package com.gavas.exception;

import lombok.Getter;

@Getter
public class DataAccessFailException extends RuntimeException {
    private String message;

    public DataAccessFailException(String message) {
        super();
        this.message = message;
    }
}
