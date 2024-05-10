package ru.comavp;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import org.junit.jupiter.api.Test;
import ru.comavp.dto.FourthUserDto;
import ru.comavp.dto.SecondUserDto;
import ru.comavp.dto.ThirdUserDto;
import ru.comavp.dto.UserDto;
import ru.comavp.entity.User;

import java.time.LocalDate;

import static com.googlecode.jmapper.api.JMapperAPI.attribute;
import static com.googlecode.jmapper.api.JMapperAPI.mappedClass;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JMapperTest {

    @Test
    public void mappingUsingJMapperApi() {
        JMapperAPI jMapperAPI = new JMapperAPI()
                .add(mappedClass(UserDto.class)
                        .add(attribute("id").value("id"))
                        .add(attribute("username").value("email"))
                );

        JMapper<UserDto, User> userMapper = new JMapper<>(UserDto.class, User.class, jMapperAPI);
        User user = new User(123L, "test@gmail.com", LocalDate.now());
        UserDto userDto = userMapper.getDestination(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getUsername());

        System.out.println(user);
        System.out.println(userDto);
    }

    @Test
    public void mappingUsingAnnotation() {
        JMapper<SecondUserDto, User> userMapper = new JMapper<>(SecondUserDto.class, User.class);
        User user = new User(123L, "test@gmail.com", LocalDate.now());
        SecondUserDto userDto = userMapper.getDestination(user);

        System.out.println(user);
        System.out.println(userDto);
    }

    @Test
    public void mappingUsingXml() {
        JMapper<ThirdUserDto, User> userMapper = new JMapper<>(ThirdUserDto.class, User.class, "jmapper_config.xml");
        User user = new User(123L, "test@gmail.com", LocalDate.now());
        ThirdUserDto userDto = userMapper.getDestination(user);

        System.out.println(user);
        System.out.println(userDto);
    }

    @Test
    public void mappingUsingCustomConversions() {
        JMapper<FourthUserDto, User> userMapper = new JMapper<>(FourthUserDto.class, User.class);
        User user = new User(123L,"test@test.com", LocalDate.of(1980,8,20));
        FourthUserDto userDto = userMapper.getDestination(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getUserName());
        assertTrue(userDto.getAge() > 0);

        System.out.println(user);
        System.out.println(userDto);
    }
}
