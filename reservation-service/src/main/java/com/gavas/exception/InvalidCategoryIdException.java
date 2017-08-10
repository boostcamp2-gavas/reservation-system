package com.gavas.exception;

import lombok.Getter;

@Getter
public class InvalidCategoryIdException extends RuntimeException {
    private String message;
    public InvalidCategoryIdException(String message) {
        super();
        this.message = message;
    }
}
