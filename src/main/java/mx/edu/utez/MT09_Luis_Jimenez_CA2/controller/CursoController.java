package mx.edu.utez.MT09_Luis_Jimenez_CA2.controller;

import jakarta.validation.Valid;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Curso;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }


    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@Valid @RequestBody CursoDTO dto) {
        return new ResponseEntity<>(cursoService.registrarCurso(dto), HttpStatus.CREATED);
    }


    @GetMapping("/disponibles")
    public ResponseEntity<List<Curso>> listarDisponibles() {
        return ResponseEntity.ok(cursoService.listarDisponibles());
    }


    @GetMapping
    public ResponseEntity<List<Curso>> listarTodos() {
        return ResponseEntity.ok(cursoService.listarTodos());
    }


    @PatchMapping("/{id}/estado")
    public ResponseEntity<Curso> cambiarEstado(
            @PathVariable Long id,
            @Valid @RequestBody CambioEstadoCursoDTO dto) {
        return ResponseEntity.ok(cursoService.cambiarEstado(id, dto.getNuevoEstado()));
    }
}