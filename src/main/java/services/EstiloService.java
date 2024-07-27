
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Estilo;
import repositories.EstiloRepository;

@Service
@Transactional
public class EstiloService {

	/// Repositorio propio
	EstiloRepository estiloRepository;


	/// Servicio apoyo

	@Autowired
	public EstiloService(final EstiloRepository estiloRepository) {
		this.estiloRepository = estiloRepository;
	}

	public Estilo create() {
		Estilo result;

		result = new Estilo();

		return result;
	}

	public Collection<Estilo> findAll() {
		Collection<Estilo> result;

		result = this.estiloRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Estilo findById(final int id) {
		Assert.isTrue(id != 0);

		Estilo result;

		result = this.estiloRepository.findById(id);

		return result;
	}

	public Estilo findByNombre(final String nombre) {
		Assert.hasText(nombre);
		Estilo result;

		result = this.estiloRepository.findByNombre(nombre);

		Assert.notNull(result);

		return result;
	}

	public Estilo save(final Estilo estilo) {
		Assert.notNull(estilo);
		Estilo result;
		result = this.estiloRepository.save(estilo);

		return result;
	}

	public void delete(final Estilo estilo) {
		Assert.notNull(estilo);
		Assert.isTrue(estilo.getId() != 0);
		Assert.isTrue(this.estiloRepository.exists(estilo.getId()));

		this.estiloRepository.delete(estilo);
	}
}
