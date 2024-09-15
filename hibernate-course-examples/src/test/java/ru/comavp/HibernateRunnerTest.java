package ru.comavp;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.comavp.entity.Company;
import ru.comavp.util.HibernateUtil;

@Slf4j
class HibernateRunnerTest {

    @Test
    void oneToMany() {
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        var company = session.get(Company.class, 5);
        log.info("Company: {}", company);

        session.getTransaction().commit();
    }

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
//
//    @Test
//    void checkReflectionApi() throws SQLException, IllegalAccessException {
//        User user = User.builder()
//                .userName("comavp@mail.ru")
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