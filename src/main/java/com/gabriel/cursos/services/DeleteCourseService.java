package com.gabriel.cursos.services;

import com.gabriel.cursos.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteCourseService {

    private final CourseRepository courseRepository;

    public DeleteCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void deleteCourse(Long idCourse) {
        this.courseRepository.deleteById(idCourse);
    }
}
