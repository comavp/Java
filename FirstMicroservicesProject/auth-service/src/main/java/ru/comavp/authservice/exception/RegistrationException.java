package ru.comavp.authservice.exception;

public class RegistrationException extends RuntimeException {

    public RegistrationException(final String message) {
        super(message);
    }
}
