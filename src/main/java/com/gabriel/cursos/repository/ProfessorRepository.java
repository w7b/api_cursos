package com.gabriel.cursos.repository;

import com.gabriel.cursos.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {

    Optional<ProfessorEntity> findByName(String name);

    Optional<ProfessorEntity> findByEmail (String email);

}
