package mx.edu.utez.MT09_Luis_Jimenez_CA2.repository;

import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estadoin;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface inscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByCorreo(String correo);
    List<Inscripcion> findByCursoId(Long cursoId);
    List<Inscripcion> findByEstado(Estadoin estado);
}
