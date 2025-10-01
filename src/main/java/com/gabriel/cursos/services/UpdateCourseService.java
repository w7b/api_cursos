package com.gabriel.cursos.services;

import com.gabriel.cursos.dto.CourseResponseDTO;
import com.gabriel.cursos.dto.UpdateDTO;
import com.gabriel.cursos.entity.Activate;
import com.gabriel.cursos.mapper.CourseMapper;
import com.gabriel.cursos.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateCourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public UpdateCourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public CourseResponseDTO updateById (UpdateDTO updateDTO, Long idCourse) {
        var courseEntity = this.courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("Course not found"));

        Optional.ofNullable(updateDTO.category()).ifPresent(courseEntity::setCategory);
        Optional.ofNullable(updateDTO.name()).ifPresent(courseEntity::setName);

        var updateCourseEntity = this.courseRepository.saveAndFlush(courseEntity);
        return this.courseMapper.convertToDto(updateCourseEntity);
    }

    @Transactional
    public CourseResponseDTO isActivate (Long idCourse) {
        var courseEntity = this.courseRepository.findById(idCourse).orElseThrow(() -> new RuntimeException("Course not found"));

        courseEntity.setActivate(courseEntity.getActivate() == Activate.ENABLED ? Activate.DISABLE : Activate.ENABLED);

        var updateCourseEntity = this.courseRepository.saveAndFlush(courseEntity);
        return this.courseMapper.convertToDto(updateCourseEntity);
    }
}
