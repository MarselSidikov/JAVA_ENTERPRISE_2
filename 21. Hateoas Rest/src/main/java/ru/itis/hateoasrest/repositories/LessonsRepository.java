package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Lesson;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
}
