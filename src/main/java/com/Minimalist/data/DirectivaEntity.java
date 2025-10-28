package com.Minimalist.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "directiva")
public class DirectivaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String cargo;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProfesorEntity> profesores;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EstudianteEntity> estudiantes;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MateriaEntity> materias;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CharlaIAEntity> charlas;

    @OneToMany(mappedBy = "directiva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EncuestaEntity> encuestas;
}
