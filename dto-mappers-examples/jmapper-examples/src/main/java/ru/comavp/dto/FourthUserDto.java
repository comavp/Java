package ru.comavp.dto;

import com.googlecode.jmapper.annotations.JMap;
import com.googlecode.jmapper.annotations.JMapConversion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
public class FourthUserDto {

    @JMap
    private long id;
    @JMap("email")
    private String userName;
    @JMap("birthDate")
    private int age;

    @JMapConversion(from = {"birthDate"}, to = {"age"})
    public int conversion(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
