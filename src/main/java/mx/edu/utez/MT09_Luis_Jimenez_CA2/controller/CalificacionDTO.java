package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CalificacionDTO {
    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 0, message = "La calificación mínima es 0")
    @Max(value = 10, message = "La calificación máxima es 10")
    private Double calificacion;
}