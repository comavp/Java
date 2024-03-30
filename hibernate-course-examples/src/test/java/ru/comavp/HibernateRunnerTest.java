package ru.comavp;

import org.junit.jupiter.api.Test;
import ru.comavp.entity.Birthday;
import ru.comavp.entity.User;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class HibernateRunnerTest {


//    @Test
//    void checkGetReflectionApi() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        Class<User> userClass = User.class;
//        Constructor<User> constructor = userClass.getConstructor();
//        User user = constructor.newInstance();
//
//        for (var field: user.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            field.set(user, resultSet.getString(field.getName()));
//        }
//    }

//    @Test
//    void checkReflectionApi() throws SQLException, IllegalAccessException {
//        User user = User.builder()
//                .userName("comavp@mail.ru")
//                .firstName("Anton")
//                .lastName("Plankin")
//                .birthDate(new Birthday(LocalDate.of(1993, 10, 19)))
//                .build();
//
//        String sql = """
//                insert
//                into
//                %s
//                (%s)
//                values
//                (%s)
//                """;
//        String tableName = Optional.ofNullable(user.getClass().getAnnotation(Table.class))
//                .map(tableAnnotation -> tableAnnotation.schema() + "." + tableAnnotation.name())
//                .orElse(user.getClass().getName());
//        String columnNames = Stream.of(user.getClass().getDeclaredFields())
//                .map(field -> Optional.ofNullable(field.getAnnotation(Column.class))
//                        .map(Column::name)
//                        .orElse(field.getName()))
//                .collect(Collectors.joining(", "));
//        String columnValues = Stream.of(user.getClass().getDeclaredFields())
//                .map(field -> "?")
//                .collect(Collectors.joining(", "));
//
//        System.out.println(sql.formatted(tableName, columnNames, columnValues));
//
//        Connection connection = null;
//        PreparedStatement preparedStatement = connection.prepareStatement(sql.formatted(tableName, columnNames, columnValues));
//        int ind = 0;
//        for (Field declaredField: user.getClass().getDeclaredFields()) {
//            declaredField.setAccessible(true);
//            preparedStatement.setObject(ind, declaredField.get(user));
//        }
//    }
}