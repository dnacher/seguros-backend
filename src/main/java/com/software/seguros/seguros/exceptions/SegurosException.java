package com.software.seguros.seguros.exceptions;

import org.springframework.http.HttpStatus;

public class SegurosException extends RuntimeException{

    private HttpStatus code;

    public SegurosException(String message) {
        super(message);
    }

    public SegurosException(HttpStatus code, String message) {
        super(message);
        this.code = code;
    }

    public HttpStatus getCode() {
        return code;
    }
}
