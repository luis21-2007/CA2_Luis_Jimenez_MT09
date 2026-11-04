package mx.edu.utez.MT09_Luis_Jimenez_CA2.service;

import mx.edu.utez.MT09_Luis_Jimenez_CA2.controller.IncripcionDTO;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.exception.BadRequestException;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.exception.ResourceNotFoundException;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Curso;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estado;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estadoin;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Inscripcion;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.repository.cursoRepository;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.repository.inscripcionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionService {

    private final inscripcionRepository inscripcionRepository;
    private final cursoRepository cursoRepository;

    public InscripcionService(inscripcionRepository inscripcionRepository, cursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.cursoRepository = cursoRepository;
    }


    public Inscripcion inscribirParticipante(IncripcionDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCurso_id())
                .orElseThrow(() -> new ResourceNotFoundException("El curso con ID " + dto.getCurso_id() + " no existe"));

        if (curso.getEstado() != Estado.DISPONIBLE) {
            throw new BadRequestException("Solo se permite la inscripción en cursos con estado DISPONIBLE");
        }

        Inscripcion inscripcion = Inscripcion.builder()
                .nombreParticipante(dto.getNombre())
                .edadParticipante(dto.getEdadParticipacion())
                .correo(dto.getCorreo())
                .fechaInscripcion(LocalDate.now())
                .estado(Estadoin.ACTIVA)
                .calificacion(null)
                .curso(curso)
                .build();

        return inscripcionRepository.save(inscripcion);
    }


    public Inscripcion cancelarInscripcion(Long id) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La inscripción con ID " + id + " no existe"));

        if (inscripcion.getEstado() != Estadoin.ACTIVA) {
            throw new BadRequestException("Únicamente se pueden cancelar inscripciones en estado ACTIVA");
        }

        if (inscripcion.getCurso().getFechaInicio() != null && LocalDate.now().isAfter(inscripcion.getCurso().getFechaInicio())) {
            throw new BadRequestException("No se puede cancelar una inscripción si el curso ya ha comenzado");
        }

        inscripcion.setEstado(Estadoin.CANCELADA);
        return inscripcionRepository.save(inscripcion);
    }
    public List<Inscripcion> consultarPorCorreo(String correo) {
        return inscripcionRepository.findByCorreo(correo);
    }


    public List<Inscripcion> consultarPorCurso(Long cursoId) {
        if (!cursoRepository.existsById(cursoId)) {
            throw new ResourceNotFoundException("El curso con ID " + cursoId + " no existe");
        }
        return inscripcionRepository.findByCursoId(cursoId);
    }


    public Inscripcion consultarDetalle(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La inscripción con ID " + id + " no existe"));
    }

    public List<Inscripcion> consultarPorEstado(Estadoin estado) {
        return inscripcionRepository.findByEstado(estado);
    }

    public Inscripcion registrarCalificacion(Long id, Double calificacion) {
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La inscripción con ID " + id + " no existe"));

        if (inscripcion.getCurso().getEstado() != Estado.FINALIZADO) {
            throw new BadRequestException("El curso asociado debe estar en estado FINALIZADO para asignar calificaciones");
        }

        if (inscripcion.getEstado() != Estadoin.ACTIVA) {
            throw new BadRequestException("Solo se pueden calificar inscripciones en estado ACTIVA");
        }

        inscripcion.setCalificacion(calificacion);
        inscripcion.setEstado(Estadoin.COMPLETADA);
        return inscripcionRepository.save(inscripcion);
    }
}