package mx.edu.utez.MT09_Luis_Jimenez_CA2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "cursos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private LocalDate fechaInicio;

    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;



}