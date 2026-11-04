package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CambioEstadoCursoDTO {
    @NotNull(message = "El nuevo estado es obligatorio")
    private Estado nuevoEstado;
}
