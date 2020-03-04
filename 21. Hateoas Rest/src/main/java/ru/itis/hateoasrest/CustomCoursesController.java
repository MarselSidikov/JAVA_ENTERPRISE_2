package ru.itis.hateoasrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.itis.hateoasrest.models.Course;
import ru.itis.hateoasrest.repositories.CoursesRepository;

@RepositoryRestController
public class CustomCoursesController {

    @Autowired
    private CoursesRepository coursesRepository;

    @PutMapping("/courses/{course-id}/publish")
    public ResponseEntity<?> publish(@PathVariable("course-id") Long courseId) {
        Course course = coursesRepository.getOne(courseId);
        course.publish();
        coursesRepository.save(course);
        return ResponseEntity.ok().build();
    }
}
