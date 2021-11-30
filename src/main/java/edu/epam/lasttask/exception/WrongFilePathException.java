package edu.epam.lasttask.exception;

public class WrongFilePathException extends Exception{
    public WrongFilePathException() {
    }

    public WrongFilePathException(String message) {
        super(message);
    }

    public WrongFilePathException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongFilePathException(Throwable cause) {
        super(cause);
    }
}
