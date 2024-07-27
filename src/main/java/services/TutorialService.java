
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Tutorial;
import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	private final TutorialRepository tutorialRepository;


	@Autowired
	public TutorialService(final TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
	}

	public Tutorial create() {
		Tutorial tutorial;
		tutorial = new Tutorial();
		return tutorial;
	}

	public Tutorial save(final Tutorial tutorial) {
		assert tutorial != null;
		return this.tutorialRepository.save(tutorial);
	}

	public Tutorial findById(final int id) {
		Assert.isTrue(id > 0, "El ID debe de ser mayor que cero!");

		Tutorial result;
		result = this.tutorialRepository.findById(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tutorial findByTutorial(final String tutorial) {
		Tutorial result;

		result = this.tutorialRepository.findByTutorial(tutorial);
		Assert.notNull(result);

		return result;
	}

	public Tutorial findByDescripcion(final String descripcion) {
		Tutorial result;

		result = this.tutorialRepository.findByDescripcion(descripcion);
		Assert.notNull(result);

		return result;
	}

	public Tutorial findByVideo(final String video) {
		Tutorial result;

		result = this.tutorialRepository.findByVideo(video);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tutorial> findAllByVisualizaciones(final int visualizaciones) {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAllByVisualizaciones(visualizaciones);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tutorial> findAllByAcademiaId(final int id) {
		Collection<Tutorial> result = null;
		result = this.tutorialRepository.findAll();// TODO: esto hay que cambiarlo
		//result = this.tutorialRepository.findAllByAcademiaId(id);
		Assert.notNull(result);

		return result;
	}
	
	public Collection<Tutorial> findAllByVisualizacionesBetween(final int minVisualizaciones, final int maxVisualizaciones) {
		Collection<Tutorial> result;

		result = this.tutorialRepository.findAllByVisualizacionesBetween(minVisualizaciones, maxVisualizaciones);
		Assert.notNull(result);

		return result;
	}

	public Tutorial update(final Tutorial tutorial) {
		Assert.notNull(tutorial);

		if (this.tutorialRepository.exists(tutorial.getId()))
			return this.tutorialRepository.save(tutorial);
		else
			throw new IllegalArgumentException("El tutorial con ID " + tutorial.getId() + " no existe!");
	}

	public void delete(final Tutorial tutorial) {
		Assert.notNull(tutorial);

		if (this.tutorialRepository.exists(tutorial.getId()))
			this.tutorialRepository.delete(tutorial);
		else
			throw new IllegalArgumentException("El tutorial con ID " + tutorial.getId() + " no existe!");
	}

	public void deleteById(final int id) {
		Assert.isTrue(id > 0, "El ID debe de ser mayor que cero!");

		if (this.tutorialRepository.exists(id))
			this.tutorialRepository.delete(id);
		else
			throw new IllegalArgumentException("El tutorial con ID " + id + " no existe!");
	}
}
