package com.copyrightinserter.exceptions;

public class AlreadyInsertedException extends Exception {
    private static final long serialVersionUID = 3214328549254991000L;

    public AlreadyInsertedException(String message) {
        super(message);
    }
}
