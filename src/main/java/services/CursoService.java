
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Curso;
import domain.Enumeraciones.CursoNivel;
import repositories.CursoRepository;

@Service
@Transactional
public class CursoService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CursoRepository cursoRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public CursoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Curso create() {
		Curso result;

		result = new Curso();

		return result;
	}

	public void delete(final Curso curso) {
		Assert.notNull(curso);
		Assert.isTrue(curso.getId() != 0);

		this.cursoRepository.delete(curso);
	}

	// Other business methods -------------------------------------------------
	public Collection<Curso> findAll() {
		Collection<Curso> result;

		Assert.notNull(this.cursoRepository);
		result = this.cursoRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Curso findById(final int id) {
		Assert.isTrue(id != 0);

		Curso result;

		result = this.cursoRepository.findById(id);

		return result;
	}

	public Collection<Curso> findByDiaSemana(final String diaSemana) {
		Collection<Curso> result;

		result = this.cursoRepository.findByDiaSemana(diaSemana);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByTitulo(final String titulo) {
		Collection<Curso> result;

		result = this.cursoRepository.findByTituloContaining(titulo);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByNivelCurso(final CursoNivel nivel) {
		Collection<Curso> result;

		result = this.cursoRepository.findByNivel(nivel);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findByEstilo(final int estiloId) {
		Collection<Curso> result;

		result = this.cursoRepository.findByEstiloId(estiloId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Curso> findAllByAcademiaId(final int id) {
		Assert.isTrue(id != 0);

		Collection<Curso> result;

		result = this.cursoRepository.findAllByAcademiaId(id);
		Assert.notNull(result);

		return result;
	}

}
