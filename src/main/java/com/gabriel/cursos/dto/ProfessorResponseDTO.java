package com.gabriel.cursos.dto;

import java.util.List;

public record ProfessorResponseDTO(
        Long id,
        String professor,
        String email,
        List<CourseResponseGetDTO> courses
) {

}
