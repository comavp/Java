package ru.comavp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.comavp.entity.Birthday;
import ru.comavp.entity.PersonalInfo;
import ru.comavp.entity.Role;
import ru.comavp.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import static ru.comavp.util.HibernateUtil.buildSessionFactory;

public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) throws SQLException {
        Date date = new Date();
        User user = User.builder()
                .userName("inan123@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("C")
                        .lastName("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(1968, 3, 11)))
                        .build())
                .role(Role.ADMIN)
                .firstDate(date)
                .secondDate(date)
                .thirdDate(date)
                .build();

        log.info("User entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = buildSessionFactory()) {
            Session session = sessionFactory.openSession();
            try (session) {
                Transaction transaction = session.beginTransaction();
                log.trace("Transaction is created, {}", transaction);

                session.saveOrUpdate(user);
                log.trace("User is persistent state: {}, session {}", user, session);

                session.getTransaction().commit();
            }
            log.warn("User in detached state: {}, session is closed {}", user, session);
            try (Session secondSession = sessionFactory.openSession()) {
                PersonalInfo id = PersonalInfo.builder()
                        .firstName("Ivan")
                        .lastName("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(1968, 3, 11)))
                        .build();

                User secondUser = secondSession.get(User.class, id);
                log.info("User {} was found by EmbeddedId", secondUser);
            }
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}