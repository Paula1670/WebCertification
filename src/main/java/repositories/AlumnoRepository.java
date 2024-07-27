package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Actores.Alumno;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}
