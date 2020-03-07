package ru.itis.hateoasrest.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.PersistentEntityResourceProcessor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import ru.itis.hateoasrest.models.Course;
import ru.itis.hateoasrest.services.CoursesService;

@Slf4j
@RepositoryRestController
public class CoursesController {

//    private final Logger log = LoggerFactory.getLogger(CoursesController.class);

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/courses/{course-id}/publish", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> publish(@PathVariable("course-id") Long courseId) {
        log.info(courseId.toString());
        return ResponseEntity.ok(
                new EntityModel<>(
                        coursesService.publish(courseId)));
    }
}
