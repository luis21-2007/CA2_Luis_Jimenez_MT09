package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncripcionDTO {
    @NotBlank(message = "El nombre debe de contener algo")
    private String nombre;
    @NotNull(message = "El curso debe de existir")
    private Long curso_id;
    @Min(value = 1, message = "cha pon la edad real")
    private int edadParticipacion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechainscripcion;
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;


}
