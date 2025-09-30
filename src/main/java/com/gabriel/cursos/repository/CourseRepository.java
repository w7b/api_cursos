package com.gabriel.cursos.repository;

import com.gabriel.cursos.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    Optional<CourseEntity> findById(Long id);

    List<CourseEntity> findByNameContainingIgnoreCase(String name);
}
