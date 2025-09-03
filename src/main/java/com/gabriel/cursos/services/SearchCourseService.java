package com.gabriel.cursos.services;

import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchCourseService {

    private final CourseRepository courseRepository;
    public SearchCourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<CourseEntity> execute(String filterName, String filterCategory) {
        return this.courseRepository.findByNameOrCategoryContainingIgnoreCase(filterName, String.valueOf(Optional.of(filterCategory)), Pageable.ofSize(10));
    }
}
