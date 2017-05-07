package main.exception;

/**
 * Created by thimira on 07/05/17.
 */
public class InvalidMessageFormatException extends Exception{

    public InvalidMessageFormatException() {
        super();
    }

    public InvalidMessageFormatException(String message) {
        super(message);
    }
}
