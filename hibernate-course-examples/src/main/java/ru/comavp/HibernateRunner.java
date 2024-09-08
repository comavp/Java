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
                .name("Amazon")
                .build();
        User user = User.builder()
                .userName("inan123@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Jack")
                        .lastName("Ivanov")
                        .birthDate(new Birthday(LocalDate.of(1968, 3, 11)))
                        .build())
                .role(Role.USER)
                .company(company)
                .build();

        try (SessionFactory sessionFactory = buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

//            session.saveOrUpdate(company);
//            session.saveOrUpdate(user);
            User userFromDb = session.get(User.class, 11L);
            log.info("User: {}", userFromDb);

            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Exception occurred", e);
            throw e;
        }
    }
}