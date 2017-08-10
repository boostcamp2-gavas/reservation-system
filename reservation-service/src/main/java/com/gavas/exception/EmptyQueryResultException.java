package com.gavas.exception;

import lombok.Getter;

@Getter
public class EmptyQueryResultException extends RuntimeException {
    private String message;

    public EmptyQueryResultException(String message) {
        super();
        this.message = message + "에 해당되는 값이 없습니다";
    }
}
