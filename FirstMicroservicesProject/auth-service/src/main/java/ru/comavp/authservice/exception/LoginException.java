package ru.comavp.authservice.exception;

public class LoginException extends RuntimeException {

    public LoginException(final String message) {
        super(message);
    }
}
