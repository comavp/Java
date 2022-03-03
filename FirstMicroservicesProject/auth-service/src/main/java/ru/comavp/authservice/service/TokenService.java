package ru.comavp.authservice.service;

public interface TokenService {

    String generateToken(String clientId);
}
