package ru.comavp.authservice.model;

import lombok.Value;

@Value
public class User {

    String clientId;
    String clientSecret;
}
