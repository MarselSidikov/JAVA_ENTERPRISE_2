package ru.itis.hateoasrest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
