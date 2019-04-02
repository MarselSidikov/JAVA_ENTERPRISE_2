package ru.itis.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.itis.models.Course;
import ru.itis.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // TODO: можно и java-классом
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = User.builder()
                .firstName("Марсель")
                .lastName("Сидиков")
                .build();

        Course java = Course.builder()
                .title("Разработка корпоративных приложений")
                .description("Работаем")
                .build();

        Course js = Course.builder()
                .title("Разработка на JS")
                .description("Чуть меньше работаем.")
                .build();

        Set<Course> courses = new HashSet<>();
        courses.add(js);
        courses.add(java);
        user.setCourses(courses);

        session.save(user);

        user.setFirstName("Максим");
        session.save(user);
        transaction.commit();
        session.close();
    }
}
