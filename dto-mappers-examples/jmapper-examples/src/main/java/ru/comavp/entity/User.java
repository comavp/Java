package ru.comavp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {

    private long id;
    private String email;
    private LocalDate birthDate;
}
