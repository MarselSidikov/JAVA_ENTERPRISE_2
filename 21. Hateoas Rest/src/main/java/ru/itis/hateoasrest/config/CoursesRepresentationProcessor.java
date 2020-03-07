package ru.itis.hateoasrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.itis.hateoasrest.controllers.CoursesController;
import ru.itis.hateoasrest.models.Course;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CoursesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Course>> {

    @Autowired
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Course> process(EntityModel<Course> model) {
        // получаем модель
        Course course = model.getContent();
        // если модель - черновик
        if (course.getState().equals("Draft")) {
            // разрешаю ей публиковаться
            model.add(linkTo(methodOn(CoursesController.class).publish(course.getId())).withRel("publish"));
        }

        if (course.getState().equals("Published")) {
            model.add(links.linkToItemResource(Course.class, course.getId()).withRel("delete"));
        }
        return model;
    }
}
