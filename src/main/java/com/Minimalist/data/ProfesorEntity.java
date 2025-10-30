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
@Table(name = "profesor")
public class ProfesorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private long telefono;
    private String direccion;
    private String correo;
    private String materias;


}
