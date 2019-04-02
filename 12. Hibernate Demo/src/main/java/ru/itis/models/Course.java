package ru.itis.models;

import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"students", "lessons"})
public class Course {
    private Long id;
    private String title;
    private String description;

    private Set<User> students;
    private List<Lesson> lessons;
}
