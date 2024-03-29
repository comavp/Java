package ru.comavp;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.comavp.converter.BirthdayConverter;
import ru.comavp.entity.Birthday;
import ru.comavp.entity.Role;
import ru.comavp.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
//        BlockingDeque<Connection> pool = null;
//        Connection connection = pool.take();
//        Connection connection = DriverManager.getConnection("db.url", "db.username", "db.password");

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAttributeConverter(new BirthdayConverter());
        configuration.registerTypeOverride(new JsonBinaryType());
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .userName("ripper123@google.com")
                    .firstName("Jon")
                    .lastName("Richer")
                    .birthDate(new Birthday(LocalDate.of(1993, 10, 19)))
                    .role(Role.ADMIN)
                    .info("""
                            {
                                "name": "Jon",
                                "age": "32"
                            }
                            """)
                    .build();
            session.save(user);

            session.getTransaction().commit();

            System.out.println("User created");
        }
    }
}