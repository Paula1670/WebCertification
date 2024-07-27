
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import domain.Curso;
import domain.Enumeraciones.CursoNivel;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

	@Query("select c from Curso c where c.estilo.id = ?1")
	Collection<Curso> findByEstiloId(int EstiloId);

	@Query("select c from Curso c where c.titulo like %?1%")
	Collection<Curso> findByTituloContaining(String titulo);

	@Query("select c from Curso c where c.diaSemana = ?1")
	Collection<Curso> findByDiaSemana(String diaSemana);

	@Query("select c from Curso c where c.nivel = ?1")
	Collection<Curso> findByNivel(CursoNivel nivel);

	@Query("select c from Curso c where c.id=:id")
	public Curso findById(@Param("id") int id);

	@Query("select distinct c from Academia a join a.cursos c where a.id=:id")
	public Collection<Curso> findAllByAcademiaId(@Param("id") int id);

}
