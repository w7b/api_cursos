package com.gabriel.cursos.mapper;

import com.gabriel.cursos.dto.UpdateDTO;
import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.entity.ProfessorEntity;

public class UpdateMapper {

    public static UpdateDTO toDTO(ProfessorEntity professor) {
        CourseEntity course = professor.getCourses().get(0);
         return new UpdateDTO(professor.getName(), course.getCategory());

    }
}
