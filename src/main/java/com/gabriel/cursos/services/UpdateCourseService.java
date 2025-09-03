package com.gabriel.cursos.services;

import com.gabriel.cursos.dto.UpdateDTO;
import com.gabriel.cursos.entity.Activate;
import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UpdateCourseService {

    private final CourseRepository courseRepository;

    public UpdateCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity updateById (UpdateDTO updateDTO, Long idCourse) {
        var course = this.courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("Course not found"));

        Optional.ofNullable(updateDTO.category()).ifPresent(course::setCategory);
        Optional.ofNullable(updateDTO.name()).ifPresent(course::setName);

        return this.courseRepository.saveAndFlush(course);
    }

    @Transactional
    public CourseEntity isActivate (Long idCourse) {
        var course = this.courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("Course not found"));

        course.setActivate(course.getActivate() == Activate.ENABLED ? Activate.DISABLE : Activate.ENABLED);
        return course;
    }
}
