package org.lab5.exeptions;

public class UnknownTypeException extends RuntimeException{
    public UnknownTypeException() {
        super();
    }

    public UnknownTypeException(String message) {
        super(message);
    }
}
