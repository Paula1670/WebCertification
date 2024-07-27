
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actores.Actor;
import repositories.ActorRepository;
import security.UserAccount;
import security.UserAccountService;

@Service
@Transactional
public class ActorService {

	/// Repositorio propio
	private final ActorRepository		actorRepository;

	/// Servicios de apoyo
	private final UserAccountService	userAccountService;


	@Autowired
	public ActorService(final ActorRepository actorRepository, final UserAccountService userAccountService) {
		this.actorRepository = actorRepository;
		this.userAccountService = userAccountService;
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;

		result = this.actorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Actor findById(final int actorId) {
		Assert.isTrue(actorId != 0);

		Actor result;

		result = this.actorRepository.findById(actorId);

		return result;
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Actor result;
		result = this.actorRepository.save(actor);

		return result;
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));

		this.actorRepository.delete(actor);
	}

	// Otros métodos

	public UserAccount findUserAccount(final Actor actor) {
		Assert.notNull(actor);
		UserAccount result;

		result = this.userAccountService.findByActor(actor);

		return result;
	}

	public Collection<Actor> findByNombre(final String nombre) {
		Assert.hasText(nombre);
		final Collection<Actor> result = this.actorRepository.findByNombre(nombre);
		Assert.notEmpty(result);

		return result;
	}

	public Collection<Actor> findByNombreyApellidos(final String nombre, final String apellidos) {
		Assert.hasText(nombre);
		Assert.hasText(apellidos);

		final Collection<Actor> result = this.actorRepository.findByNombreyApellidos(nombre, apellidos);
		Assert.notEmpty(result);
		return result;
	}

	public Collection<Actor> findByCodigoPostal(final String codigoPostal) {
		Assert.hasText(codigoPostal);

		final Collection<Actor> result = this.actorRepository.findByCodigoPostal(codigoPostal);

		Assert.notEmpty(result);
		return result;
	}

	public Actor findByCorreo(final String correo) {
		Assert.hasText(correo);
		Actor result;

		result = this.actorRepository.findByCorreo(correo);

		return result;
	}

}
