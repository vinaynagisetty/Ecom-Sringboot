package com.vinay.nagisetty.SpringbootEmbarkx.exceptions;

public class APIException extends RuntimeException {
    private static final Long Uuid=1L;

    public APIException() {
    }

    public APIException(String message) {
        super(message);
    }
}
