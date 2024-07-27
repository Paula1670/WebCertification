
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Estilo;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {

	@Query("select e from Estilo e where e.id=:id")
	public Estilo findById(@Param("id") int id);

	@Query("select e from Estilo e where e.nombre=:nombre")
	public Estilo findByNombre(@Param("nombre") String nombre);

}
