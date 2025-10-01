package com.gabriel.cursos.services;

import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.entity.ProfessorEntity;
import com.gabriel.cursos.repository.CourseRepository;
import com.gabriel.cursos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public CourseEntity execute (CourseEntity course, Long professorId) {
        ProfessorEntity professor = this.professorRepository.findById(professorId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        // 2. Vincula a entidade do professor ao curso.
        course.setProfessor(professor);

        return this.courseRepository.save(course);
    }
}
