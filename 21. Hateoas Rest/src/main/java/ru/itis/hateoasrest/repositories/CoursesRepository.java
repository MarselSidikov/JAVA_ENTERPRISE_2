package ru.itis.hateoasrest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.hateoasrest.models.Course;

import java.util.List;

@RepositoryRestResource
public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    @RestResource(path = "published", rel = "findAllPublished")
    @Query("from Course course where course.state = 'Published'")
    Page<Course> findAllPublished(Pageable pageable);

    List<Course> findAllByTitle(String title);
}
