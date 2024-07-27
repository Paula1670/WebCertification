
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Actores.Administrador;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

	@Query("select a from Administrador a where a.id=:id")
	public Administrador findById(@Param("id") int id);

	@Query("select a from Administrador a where a.nombre=:nombre")
	public Administrador findByNombre(@Param("nombre") String nombre);
}
