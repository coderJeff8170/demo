package com.semanticsquare.basics;

public class InvalidDataException extends Exception {

    public InvalidDataException(Exception e) {
        super("caused by: " + e.getMessage());
        this.initCause(e);
    }
}
