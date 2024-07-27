
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Comentario;
import repositories.ComentarioRepository;

@Service
@Transactional
public class ComentarioService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private ComentarioRepository comentarioRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public ComentarioService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Collection<Comentario> findAll() {
		Collection<Comentario> result;

		Assert.notNull(this.comentarioRepository);
		result = this.comentarioRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comentario findById(final int id) {
		Assert.isTrue(id != 0);

		Comentario result;

		result = this.comentarioRepository.findById(id);

		return result;
	}

	public Comentario findOne(final int announcementId) {
		Comentario result;

		result = this.comentarioRepository.findOne(announcementId);

		return result;
	}

	// Other business methods -------------------------------------------------
	public Collection<Comentario> findAllActive() {
		Collection<Comentario> result;
		Date currentMoment;

		currentMoment = new Date();
		result = this.comentarioRepository.findAllActive(currentMoment);
		Assert.notNull(result);

		return result;
	}
}
