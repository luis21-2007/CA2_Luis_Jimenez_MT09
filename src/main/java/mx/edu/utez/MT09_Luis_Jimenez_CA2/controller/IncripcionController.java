package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Inscripcion;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class IncripcionController {

    private final InscripcionService inscripcionService;

    public IncripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }


    @PostMapping
    public ResponseEntity<Inscripcion> inscribir(@Valid @RequestBody IncripcionDTO dto) {
        return new ResponseEntity<>(inscripcionService.inscribirParticipante(dto), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Inscripcion> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.cancelarInscripcion(id));
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Inscripcion>> consultarPorCorreo(@PathVariable String correo) {
        return ResponseEntity
    }

}