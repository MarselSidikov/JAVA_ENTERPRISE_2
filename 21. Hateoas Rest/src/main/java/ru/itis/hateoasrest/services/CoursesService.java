package ru.itis.hateoasrest.services;

import ru.itis.hateoasrest.models.Course;

public interface CoursesService {
    Course publish(Long courseId);
}
