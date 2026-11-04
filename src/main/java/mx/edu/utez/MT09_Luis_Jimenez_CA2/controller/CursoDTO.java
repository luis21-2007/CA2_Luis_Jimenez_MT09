package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    @NotBlank
    private String nombre;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechainicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private  LocalDate fechafin;
    @NotBlank
    private String descripcion;
}
