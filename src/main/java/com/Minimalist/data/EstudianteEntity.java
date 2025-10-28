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
@Table(name = "estudiante")
public class EstudianteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Long telefono;
    private String direccion;
    private String correo;


    @ManyToMany
    @JoinTable(
            name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<MateriaEntity> materias;

    @OneToMany(mappedBy = "estudiante")
    @JsonManagedReference
    private List<EncuestaEntity> encuestas;

    @OneToMany(mappedBy = "estudiante")
    @JsonManagedReference
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
    @JsonManagedReference
    private List<EvaluacionEntity> evaluaciones;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    @JsonBackReference
    private DirectivaEntity directiva;
}
