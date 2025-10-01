package com.gabriel.cursos.mapper;

import com.gabriel.cursos.dto.CourseResponseDTO;
import com.gabriel.cursos.entity.CourseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public CourseResponseDTO convertToDto(CourseEntity courseEntity) {
        String professorName = (courseEntity.getProfessor() != null) ? courseEntity.getProfessor().getName() : "Não atribuído";

        return new CourseResponseDTO(
                courseEntity.getId(),
                courseEntity.getName(),
                courseEntity.getCategory(),
                professorName,
                courseEntity.getActivate().name()
        );
    }
}
