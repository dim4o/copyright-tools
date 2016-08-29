package com.copyrightinserter.exceptions;

public class InvalidCommandException extends Exception{
    private static final long serialVersionUID = -2516923745569890809L;

    public InvalidCommandException() {
        super();
    }

    public InvalidCommandException(String message) {
        super(message);
    }

}
