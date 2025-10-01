package com.gabriel.cursos.controller;

import com.gabriel.cursos.dto.ProfessorRecord;
import com.gabriel.cursos.entity.ProfessorEntity;
import com.gabriel.cursos.services.TeacherAuthenticationService;
import com.gabriel.cursos.services.TeacherRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/courses")
public class AuthenticationController {

    @Autowired
    private TeacherAuthenticationService teacherAuthenticationService;
    @Autowired
    private TeacherRegisterService teacherRegisterService;

    //Create
    @PostMapping("/teacher/register")
    public ResponseEntity<Object> create (@RequestBody ProfessorEntity professor) {
        try {
            var result = this.teacherRegisterService.execute(professor);
            return ResponseEntity.created(URI.create("/courses/auth")).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    //Login
    @PostMapping("/auth")
    public ResponseEntity<Object> create(@RequestBody ProfessorRecord professorRecord) {
        try {
            var result = this.teacherAuthenticationService.execute(professorRecord);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
