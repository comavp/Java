package ru.comavp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.comavp.entity.User;

import java.sql.SQLException;

import static ru.comavp.util.HibernateUtil.buildSessionFactory;

public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) throws SQLException {
        User user = User.builder()
                .userName("inan@gmail.com")
                .firstName("Ivan")
                .lastName("Ivanov")
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
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}