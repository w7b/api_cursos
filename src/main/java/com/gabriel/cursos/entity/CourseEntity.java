package com.gabriel.cursos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity(name = "_cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    @Enumerated(EnumType.STRING)
    private Activate activate = Activate.ENABLED;

    @CreationTimestamp
    private Instant created_at;
    @UpdateTimestamp
    private Instant updated_at;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;
}
