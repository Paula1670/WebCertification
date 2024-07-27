
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actores.Academia;
import repositories.AcademiaRepository;

@Service
@Transactional
public class AcademiaService {

	@Autowired
	private AcademiaRepository academiaRepository;


	public List<Academia> getAllAcademias() {
		return this.academiaRepository.findAll();
	}

	public Academia getAcademiaById(final Long id) {
		return this.academiaRepository.findOne(id);
	}

	public Academia saveAcademia(final Academia academia) {
		return this.academiaRepository.save(academia);
	}

	public void deleteAcademia(final Long id) {
		this.academiaRepository.delete(id);
	}

	public Academia findByCursoId(final int id) {
		Assert.isTrue(id != 0);
		Academia result;

		result = this.academiaRepository.findByCursoId(id);

		Assert.notNull(result);

		return result;
	}
}
