package com.gabriel.cursos.controller;

import com.gabriel.cursos.dto.UpdateDTO;
import com.gabriel.cursos.entity.CourseEntity;
import com.gabriel.cursos.services.CreateCourseService;
import com.gabriel.cursos.services.DeleteCourseService;
import com.gabriel.cursos.services.SearchCourseService;
import com.gabriel.cursos.services.UpdateCourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class Controller {

    private final CreateCourseService createCourseService;
    private final SearchCourseService searchCourseService;
    private final UpdateCourseService updateCourseService;
    private final DeleteCourseService deleteCourseService;

    public Controller(CreateCourseService createCourseService, SearchCourseService searchCourseService, UpdateCourseService updateCourseService, DeleteCourseService deleteCourseService) {
        this.createCourseService = createCourseService;
        this.searchCourseService = searchCourseService;
        this.updateCourseService = updateCourseService;
        this.deleteCourseService = deleteCourseService;
    }

    @PostMapping
    public ResponseEntity<Object> createCourse(@RequestBody CourseEntity course) {
        var result = this.createCourseService.execute(course);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<Page<CourseEntity>> findAll(@RequestParam String filterName, String filterCategory ) {
        var result = this.searchCourseService.execute(filterName, filterCategory);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/{idCourse}")
    public ResponseEntity<Object> updateCourse(@RequestBody UpdateDTO updateDTO, @PathVariable Long idCourse) {
        var result = this.updateCourseService.updateById(updateDTO, idCourse);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{idCourse}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long idCourse) {
        this.deleteCourseService.deleteCourse(idCourse);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{idCourse}/activate")
    public ResponseEntity<Object> activateCourse(@PathVariable Long idCourse) {
        var updateCourse = this.updateCourseService.isActivate(idCourse);
        return ResponseEntity.ok(updateCourse);
    }

}
