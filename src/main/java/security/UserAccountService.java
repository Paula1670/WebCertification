
package security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actores.Actor;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	private final UserAccountRepository userAccountRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	@Autowired
	public UserAccountService(final UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	// Simple CRUD methods ----------------------------------------------------
	public UserAccount create() {
		UserAccount result;

		result = new UserAccount();

		return result;

	}
	public UserAccount findByActor(final Actor actor) {
		Assert.notNull(actor);

		UserAccount result;

		result = this.userAccountRepository.findByActorId(actor.getId());

		return result;
	}

	// Other business methods -------------------------------------------------
	public UserAccount findByUsername(final String username) {
		Assert.hasText(username);
		UserAccount result;

		result = this.userAccountRepository.findByUsername(username);

		return result;
	}
}
