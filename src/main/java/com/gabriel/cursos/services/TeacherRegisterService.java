package com.gabriel.cursos.services;

import com.gabriel.cursos.entity.ProfessorEntity;
import com.gabriel.cursos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherRegisterService {

    @Autowired
    private ProfessorRepository professorRepository;

    public ProfessorEntity execute (ProfessorEntity professor) {
        professorRepository.findByEmail(professor.getEmail()).ifPresent((user -> {
                throw new UsernameNotFoundException("Professor ja existe.");
        }));

        return this.professorRepository.save(professor);
    }
}
