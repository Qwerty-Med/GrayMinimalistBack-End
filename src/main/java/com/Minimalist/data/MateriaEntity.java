package com.Minimalist.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materias")
public class MateriaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesor_id")
    @JsonBackReference
    private ProfesorEntity profesor;

    @ManyToMany
    @JoinTable(
            name = "materia_estudiante",
            joinColumns = @JoinColumn(name = "materia_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<EstudianteEntity> estudiantes;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EvaluacionEntity> evaluaciones;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    @JsonBackReference
    private DirectivaEntity directiva;
}

