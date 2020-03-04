package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Course;

public interface CoursesRepository extends JpaRepository<Course, Long> {
}
