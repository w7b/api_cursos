package com.gabriel.cursos.services;

import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseService {

    private final CourseRepository courseRepository;

    public CreateCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity execute (CourseEntity courseEntity) {
        return this.courseRepository.save(courseEntity);


    }
}
