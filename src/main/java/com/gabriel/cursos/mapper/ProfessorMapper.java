package com.gabriel.cursos.mapper;

import com.gabriel.cursos.dto.CourseResponseDTO;
import com.gabriel.cursos.dto.CourseResponseGetDTO;
import com.gabriel.cursos.dto.ProfessorResponseDTO;
import com.gabriel.cursos.entity.ProfessorEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProfessorMapper {
    public static ProfessorResponseDTO toDTO(ProfessorEntity professor) {
        List<CourseResponseGetDTO> courseDTOs = professor.getCourses().stream()
                .map(course -> new CourseResponseGetDTO(course.getId(), course.getName(), course.getCategory()))
                .collect(Collectors.toList());

        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getName(),
                professor.getEmail(),
                courseDTOs
        );
    }
}
