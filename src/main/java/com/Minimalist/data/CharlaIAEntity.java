package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charlaIA")
public class CharlaIAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tema;
    private LocalDate fecha;

    @ManyToMany(mappedBy = "charlas")
    private List<EstudianteEntity> asistentes;
}
