package ru.comavp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.comavp.converter.BirthdayConverter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users", schema = "hibernate_course_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userName;
    private String firstName;
    private String lastName;
    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birth_date")
    private Birthday birthDate;
    @Enumerated(EnumType.STRING)
    private Role role;
}
