package mx.edu.utez.MT09_Luis_Jimenez_CA2.repository;

import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Curso;
import mx.edu.utez.MT09_Luis_Jimenez_CA2.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByEstado(Estado estado);
}
