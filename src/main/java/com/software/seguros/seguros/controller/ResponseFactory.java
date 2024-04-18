package com.software.seguros.seguros.controller;

import com.software.seguros.seguros.enums.Codigo;
import com.software.seguros.seguros.exceptions.SegurosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/** Daniel Nacher 2024-03-26 */

public class ResponseFactory {

    public static ResponseEntity<?> createResponseEntity(Map<String, Object> body, String errormessage, HttpStatus httpStatus) {
        body.put("status", httpStatus.value());
        if(errormessage!=null) {
            body.put("error", errormessage);
        }
        return new ResponseEntity<>(body, httpStatus);
    }

    public static ResponseEntity<?> handleErrorCodes(Map<String, Object> body, final Codigo error, SegurosException ex) {
        if(error == null && ex == null) {
            return createResponseEntity(body, "Unexpected Error", HttpStatus.BAD_REQUEST);
        } else if(ex!= null){
            return createResponseEntity(body, ex.getMessage(), ex.getCode());
        } else {
            return createResponseEntity(body, error.getError(), error.getHttpStatus());
        }
    }
}
