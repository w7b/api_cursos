package com.gabriel.cursos.dto;

import java.time.Instant;

public record CourseResponseDTO(
        Long id,
        String name,
        String category,
        String professorName,
        String activate
) {}
