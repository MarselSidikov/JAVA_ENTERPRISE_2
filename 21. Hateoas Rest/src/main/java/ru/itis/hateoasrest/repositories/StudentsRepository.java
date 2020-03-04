package ru.itis.hateoasrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.hateoasrest.models.Student;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
