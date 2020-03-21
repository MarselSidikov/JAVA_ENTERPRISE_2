package ru.itis.hateoasrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoasrest.models.Course;
import ru.itis.hateoasrest.services.CoursesService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class CoursesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoursesService coursesService;

    @BeforeEach
    public void setUp() {
        when(coursesService.publish(1L)).thenReturn(publishedCourse());
    }

    @Test
    public void coursePublishTest() throws Exception {
        mockMvc.perform(put("/courses/1/publish")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(publishedCourse().getTitle()))
                .andExpect(jsonPath("$.description").value(publishedCourse().getDescription()))
                .andExpect(jsonPath("$.state").value(publishedCourse().getState()))
                .andDo(document("publish_course", responseFields(
                        fieldWithPath("title").description("Название курса"),
                        fieldWithPath("description").description("Описание курса"),
                        fieldWithPath("state").description("Состояние курса")
                )));
    }

    private Course publishedCourse() {
        return Course.builder()
                .id(1L)
                .description("Deep in Java")
                .state("PUBLISHED")
                .title("JAVA")
                .build();
    }
}
