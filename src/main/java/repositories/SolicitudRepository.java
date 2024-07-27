
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Solicitud;
import domain.Enumeraciones.SolicitudEstado;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	@Query("SELECT s FROM Solicitud s WHERE s.id=:id")
	public Solicitud findById(@Param("id") int id);

	@Query("SELECT s FROM Solicitud s WHERE s.estado = :estado")
	public Collection<Solicitud> findAllByEstado(@Param("estado") SolicitudEstado estado);

	@Query("SELECT s FROM Solicitud s WHERE s.alumno.id = :alumnoID")
	public Collection<Solicitud> findAllByAlumnoId(@Param("alumnoID") int alumnoID);

	@Query("SELECT s FROM Solicitud s WHERE s.curso.id = :cursoID")
	public Collection<Solicitud> findAllByCursoId(@Param("cursoID") int cursoID);

	@Query("SELECT s FROM Solicitud s WHERE s.alumno.id = :alumnoID AND s.curso.id = :cursoID")
	public Collection<Solicitud> findAllByAlumnoAndCursoId(@Param("alumnoID") int alumnoID, @Param("cursoID") int cursoID);

	@Query("SELECT s FROM Solicitud s WHERE s.fecha = :fecha")
	public Collection<Solicitud> findAllByDate(@Param("fecha") Date fecha);
}
