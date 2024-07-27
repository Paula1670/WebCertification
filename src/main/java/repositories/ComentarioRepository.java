
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {

	@Query("select c from Comentario c where c.fechaRealizacion = ?1")
	Collection<Comentario> findAllActive(Date currentMoment);

	@Query("select c from Comentario c where c.id=:id")
	public Comentario findById(@Param("id") int id);
}
