package com.example.mybankapplication.exception;

import java.io.Serial;

public class DataAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DataAlreadyExistsException(String msg) {
        super(msg);
    }
}
