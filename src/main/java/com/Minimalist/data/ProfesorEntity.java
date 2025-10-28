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
@Table(name = "profesores")
public class ProfesorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private Long telefono;
    private String direccion;
    private String correo;


    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<MateriaEntity> materias;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    @JsonBackReference
    private DirectivaEntity directiva;
}
