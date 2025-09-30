package com.gabriel.cursos.services;

import com.gabriel.cursos.dto.CourseResponseDTO;
import com.gabriel.cursos.entity.ProfessorEntity;
import com.gabriel.cursos.repository.CourseRepository;
import com.gabriel.cursos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchCourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public SearchCourseService(CourseRepository courseRepository, ProfessorRepository professorRepository) {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    public Optional<ProfessorEntity> execute(Long professorId) {
        return professorRepository.findById(professorId);
    }

    //TODO: RECUPERAR O PROFESSOR E OS CURSOS, DENTRO DO ARRAY;
}
