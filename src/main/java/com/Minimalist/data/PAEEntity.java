package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pae")
public class PAEEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado; // "Activo", "En seguimiento", "Finalizado", etc.

    // Relación directa con el estudiante que participa en el PAE
    @OneToOne
    @MapsId
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;

    // Relación con el centro académico (directiva) que administra el PAE
    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    private DirectivaEntity centroAcademico;
}
