package com.ebench.exception;

public class BadReqException extends RuntimeException{

    public BadReqException(String message){
        super(message);
    }
}
