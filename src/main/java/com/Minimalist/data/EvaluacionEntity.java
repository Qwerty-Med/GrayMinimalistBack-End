package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "evaluacion")
public class EvaluacionEntity {
    @Id
    private Long id;

    private String resultado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;
}
