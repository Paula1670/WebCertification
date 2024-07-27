
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("SELECT t FROM Tutorial t WHERE t.id=:id")
	public Tutorial findById(@Param("id") int id);

	@Query("SELECT t FROM Tutorial t WHERE t.tutorial = :tutorial")
	public Tutorial findByTutorial(@Param("tutorial") String tutorial);

	@Query("SELECT t FROM Tutorial t WHERE t.descripcion = :descripcion")
	public Tutorial findByDescripcion(@Param("descripcion") String descripcion);

	@Query("SELECT t FROM Tutorial t WHERE t.video = :video")
	public Tutorial findByVideo(@Param("video") String video);

	@Query("SELECT t FROM Tutorial t WHERE t.visualizaciones = :visualizaciones")
	public Collection<Tutorial> findAllByVisualizaciones(@Param("visualizaciones") int visualizaciones);
	
	//@Query("SELECT t FROM Tutorial t JOIN t.academia a WHERE a.id = :id")
	//public Collection<Tutorial> findAllByAcademiaId(@Param("id") int id);

	@Query("SELECT t FROM Tutorial t WHERE t.visualizaciones >= :minVisualizaciones AND t.visualizaciones <= :maxVisualizaciones")
	public Collection<Tutorial> findAllByVisualizacionesBetween(@Param("minVisualizaciones") int minVisualizaciones, @Param("maxVisualizaciones") int maxVisualizaciones);
}
