package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "materias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id")
    private ProfesorEntity profesor;

    @ManyToMany
    @JoinTable(
            name = "materia_estudiante",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<EstudianteEntity> estudiantes;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionEntity> evaluaciones;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    private DirectivaEntity directiva;
}

