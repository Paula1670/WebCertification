
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Actores.Academia;

@Repository
public interface AcademiaRepository extends JpaRepository<Academia, Long> {

	@Query("select a from Academia a join a.cursos c where c.id=:id")
	public Academia findByCursoId(@Param("id") int id);
}
