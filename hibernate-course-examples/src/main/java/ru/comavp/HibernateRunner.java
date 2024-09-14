package ru.comavp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.comavp.entity.*;

import java.sql.SQLException;
import java.time.LocalDate;

import static ru.comavp.util.HibernateUtil.buildSessionFactory;

public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) throws SQLException {
        Company company = Company.builder()
                .name("Apple")
                .build();
        User user = User.builder()
                .userName("inan123@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Bill")
                        .lastName("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(1968, 3, 11)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();

        try (SessionFactory sessionFactory = buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Long id = (Long) session.save(user);
            User savedUser = session.get(User.class, id);
            User userFromDb = session.get(User.class, 11L);
            session.evict(userFromDb);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}