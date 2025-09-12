package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pae")
public class PAEEntity {
    @Id
    private Long id;

    private String estado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;
}
