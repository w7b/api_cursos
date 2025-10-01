package com.gabriel.cursos.controller;

import com.gabriel.cursos.dto.CourseResponseDTO;
import com.gabriel.cursos.dto.ProfessorResponseDTO;
import com.gabriel.cursos.dto.UpdateDTO;
import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.mapper.CourseMapper;
import com.gabriel.cursos.mapper.ProfessorMapper;
import com.gabriel.cursos.services.CreateCourseService;
import com.gabriel.cursos.services.DeleteCourseService;
import com.gabriel.cursos.services.SearchCourseService;
import com.gabriel.cursos.services.UpdateCourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CreateCourseService createCourseService;
    private final SearchCourseService searchCourseService;
    private final UpdateCourseService updateCourseService;
    private final DeleteCourseService deleteCourseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CoursesController(CreateCourseService createCourseService, SearchCourseService searchCourseService, UpdateCourseService updateCourseService, DeleteCourseService deleteCourseService, CourseMapper courseMapper) {
        this.createCourseService = createCourseService;
        this.searchCourseService = searchCourseService;
        this.updateCourseService = updateCourseService;
        this.deleteCourseService = deleteCourseService;
        this.courseMapper = courseMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseEntity course, HttpServletRequest request) {
        Long professorId = Long.parseLong(String.valueOf(request.getAttribute("professor_id")));
        System.out.println(professorId);
        var createdCourse = this.createCourseService.execute(course, professorId);
        var courseDTO = courseMapper.convertToDto(createdCourse);

        return ResponseEntity.ok().body(courseDTO);
    }

    @GetMapping
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<ProfessorResponseDTO> findAll(HttpServletRequest request) {
        try {
            Long professorId = Long.parseLong(String.valueOf(request.getAttribute("professor_id")));

            var professorOptional = this.searchCourseService.execute(professorId);

            if (professorOptional.isPresent()) {
                ProfessorResponseDTO professorDTO = ProfessorMapper.toDTO(professorOptional.get());
                return ResponseEntity.ok().body(professorDTO);
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{idCourse}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<CourseResponseDTO> updateCourse(@RequestBody UpdateDTO updateDTO, @PathVariable Long idCourse) {
        var result = this.updateCourseService.updateById(updateDTO, idCourse);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idCourse}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long idCourse) {
        this.deleteCourseService.deleteCourse(idCourse);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/status/{idCourse}")
    @PreAuthorize("hasRole('PROFESSOR')")
    public ResponseEntity<Object> activateCourse(@PathVariable Long idCourse) {
        var updateCourse = this.updateCourseService.isActivate(idCourse);
        return ResponseEntity.ok(updateCourse);
    }

}
