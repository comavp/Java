package ru.comavp.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;
import lombok.Data;
import lombok.NoArgsConstructor;

@JGlobalMap
@Data
@NoArgsConstructor
public class SecondUserDto {

    private long id;
    private String email;
}
