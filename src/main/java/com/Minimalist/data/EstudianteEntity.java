package com.Minimalist.data;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
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
    private String materias;
    private boolean pae;



}
