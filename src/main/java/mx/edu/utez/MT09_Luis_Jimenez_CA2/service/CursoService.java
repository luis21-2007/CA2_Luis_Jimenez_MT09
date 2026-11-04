package mx.edu.utez.MT09_Luis_Jimenez_CA2.service;

import mx.edu.utez.MT09_Luis_Jimenez_CA2.controller.CursoDTO;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.exception.BadRequestException;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.exception.ResourceNotFoundException;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Curso;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estado;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.repository.cursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CursoService {

    private final cursoRepository cursoRepository;

    public CursoService(cursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // 10 pts - Registrar curso
    public Curso registrarCurso(CursoDTO dto) {
        Curso curso = Curso.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .fechaInicio(dto.getFechainicio())
                .fechaFin(dto.getFechafin())
                .estado(Estado.DISPONIBLE)
                .build();
        return cursoRepository.save(curso);
    }


    public List<Curso> listarDisponibles() {
        return cursoRepository.findByEstado(Estado.DISPONIBLE);
    }


    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }


    public Curso cambiarEstado(Long id, Estado
            nuevoEstado) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El curso con ID " + id + " no existe"));

        if (curso.getEstado() == Estado.FINALIZADO) {
            throw new BadRequestException("No se puede cambiar el estado de un curso FINALIZADO");
        }

        if (curso.getEstado() == Estado.DISPONIBLE && nuevoEstado == Estado.EN_CURSO) {
            curso.setEstado(Estado.EN_CURSO);
        } else if (curso.getEstado() == Estado.EN_CURSO && nuevoEstado == Estado.FINALIZADO) {
            curso.setEstado(Estado.FINALIZADO);
        } else {
            throw new BadRequestException("Transición de estado no permitida. Solo se permite DISPONIBLE -> EN_CURSO ó EN_CURSO -> FINALIZADO");
        }

        return cursoRepository.save(curso);
    }
}