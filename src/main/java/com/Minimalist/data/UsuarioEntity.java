package com.Minimalist.data;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;

    @OneToOne(mappedBy = "usuario")
    private ProfesorEntity profesor;

    @OneToOne(mappedBy = "usuario")
    private EstudianteEntity estudiante;

    @OneToOne(mappedBy = "usuario")
    private DirectivaEntity directiva;
}
