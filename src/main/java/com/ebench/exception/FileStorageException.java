package com.ebench.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FileStorageException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
