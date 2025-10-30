package com.Minimalist.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluaciones")
public class EvaluacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;; // Ej: Parcial, Final, Taller
    private Double nota;


    private String materia;


    private String estudiante;
}
