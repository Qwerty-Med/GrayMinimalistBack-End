package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estudiante")
public class EstudianteEntity {
    @Id
    private Long id;
    private String nombre;
    private String apellido;
    private int telefono;
    private String direccion;


    @ManyToMany
    @JoinTable(
            name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<MateriaEntity> materias;

    @OneToMany(mappedBy = "estudiante")
    private List<EncuestaEntity> encuestas;

    @OneToMany(mappedBy = "estudiante")
    private List<ComentarioEntity> comentario;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private PAEEntity pae;


    @ManyToMany
    @JoinTable(
            name = "estudiante_charla",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "charla_id")
    )
    private List<CharlaIAEntity> charlas;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionEntity> evaluaciones;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    private DirectivaEntity directiva;
}
