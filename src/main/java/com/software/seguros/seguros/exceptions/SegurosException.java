package com.software.seguros.seguros.exceptions;

public class SegurosException extends RuntimeException{

    private int code;

    public SegurosException(String message) {
        super(message);
    }

    public SegurosException(int code, String message) {
        super(message);
        this.code = code;
    }

}
