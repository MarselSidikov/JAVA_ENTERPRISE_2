package ru.itis.hateoasrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoasrest.services.CoursesService;

@RepositoryRestController
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/courses/{course-id}/publish", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> publish(@PathVariable("course-id") Long courseId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        coursesService.publish(courseId)));
    }
}
