
package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Actores.Academia;
import domain.Actores.Actor;
import domain.Actores.Alumno;
import security.Authority;
import security.UserAccount;
import security.UserAccountService;
import services.AcademiaService;
import services.ActorService;
import services.AlumnoService;

@Controller
@RequestMapping("/actor")
public class ActorController {

	///Servicios
	private final UserAccountService	userAccountService;
	private final AlumnoService			alumnoService;
	private final AcademiaService		academiaService;
	private final ActorService			actorService;


	@Autowired
	public ActorController(final UserAccountService userAccountService, final ActorService actorService, final AlumnoService alumnoService, final AcademiaService academiaService) {
		this.userAccountService = userAccountService;
		this.actorService = actorService;
		this.academiaService = academiaService;
		this.alumnoService = alumnoService;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		UserAccount userAccount;
		final Collection<Authority> authorities;

		userAccount = this.userAccountService.create();

		authorities = Authority.listAuthorities();

		result = new ModelAndView("actor/create");
		result.addObject("authoritiesElement", authorities);
		result.addObject("userAccount", userAccount);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "next")
	public ModelAndView createUserAccount(@Valid final UserAccount userAccount, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createUserAccount(userAccount, "actor.create.commit.error");
			return result;
		}

		try {
			///Verificamos que ninún usuario más tenga ese usuario
			final UserAccount user = this.userAccountService.findByUsername(userAccount.getUsername());

			if (user != null) {
				result = this.createUserAccount(userAccount, "actor.create.commit.user.username");
				return result;
			}

			final Iterator<Authority> ite = userAccount.getAuthorities().iterator();

			final Authority authority = ite.next();
			final String authorityname = authority.getAuthority();

			if (Authority.ACADEMIA.equalsIgnoreCase(authorityname))
				result = this.createUserAcademy(userAccount);
			else
				result = this.createUserStudent(userAccount);
		} catch (final Exception e) {
			result = this.createUserAccount(userAccount, "actor.create.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/createAcademy", method = RequestMethod.POST, params = "save")
	public ModelAndView createAcademy(@Valid final Academia academia, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createUserAcademy(academia.getUserAccount(), "actor.commit.academy.comercialname");
			return result;
		}
		try {
			final Actor actor = this.actorService.findByCorreo(academia.getCorreo());
			if (actor != null)
				result = this.createUserAcademy(academia.getUserAccount(), "actor.create.error.correo");
			else {
				final UserAccount userAccount = this.encryptedUser(academia.getUserAccount());
				academia.setUserAccount(userAccount);
				this.academiaService.saveAcademia(academia);
				result = new ModelAndView("redirect:/");
			}
		} catch (final Exception e) {
			result = this.createUserAcademy(academia.getUserAccount(), "actor.create.commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/createStudent", method = RequestMethod.POST, params = "save")
	public ModelAndView createStudent(@Valid final Alumno alumno, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			binding.getAllErrors().forEach(error -> System.out.println(error.toString()));
			result = this.createUserStudent(alumno.getUserAccount(), "actor.create.commit.error");
			return result;
		}
		try {
			final Actor actor = this.actorService.findByCorreo(alumno.getCorreo());
			if (actor != null)
				result = this.createUserStudent(alumno.getUserAccount(), "actor.create.error.correo");
			else {
				final UserAccount userAccount = this.encryptedUser(alumno.getUserAccount());
				alumno.setUserAccount(userAccount);
				this.alumnoService.saveAlumno(alumno);
				result = new ModelAndView("redirect:/");
			}
		} catch (final Exception e) {
			result = this.createUserStudent(alumno.getUserAccount(), "actor.create.commit.error");
			System.out.println(e.getMessage());
		}

		return result;
	}

	///Metodos auxiliares
	protected ModelAndView createUserAccount(final UserAccount userAccount, final String mensaje) {
		final ModelAndView result;
		final Collection<Authority> authorities;

		authorities = Authority.listAuthorities();

		result = new ModelAndView("actor/create");

		result.addObject("userAccount", userAccount);
		result.addObject("authoritiesElement", authorities);
		result.addObject("mensaje", mensaje);

		return result;
	}
	protected ModelAndView createUserAcademy(final UserAccount userAccount) {
		ModelAndView result;
		Academia academia;

		result = new ModelAndView("actor/createAcademy");

		academia = new Academia();
		academia.setUserAccount(userAccount);

		result.addObject("academy", academia);

		return result;
	}
	
	protected ModelAndView createTutorial(final UserAccount userAccount) {
		ModelAndView result;
		Academia academia;

		result = new ModelAndView("actor/createTutorial");

		academia = new Academia();
		academia.setUserAccount(userAccount);

		result.addObject("academy", academia);

		return result;
	}

	protected ModelAndView createUserAcademy(final UserAccount userAccount, final String mensaje) {
		ModelAndView result;
		Academia academia;

		result = new ModelAndView("actor/createAcademy");

		academia = new Academia();
		academia.setUserAccount(userAccount);

		result.addObject("academy", academia);
		result.addObject("mensaje", mensaje);

		return result;
	}

	protected ModelAndView createUserStudent(final UserAccount userAccount) {
		ModelAndView result;
		Alumno alumno;

		result = new ModelAndView("actor/createStudent");

		alumno = new Alumno();
		alumno.setUserAccount(userAccount);

		result.addObject("student", alumno);

		return result;
	}

	protected ModelAndView createUserStudent(final UserAccount userAccount, final String mensaje) {
		ModelAndView result;
		Alumno alumno;

		result = new ModelAndView("actor/createStudent");

		alumno = new Alumno();
		alumno.setUserAccount(userAccount);

		result.addObject("student", alumno);
		result.addObject("mensaje", mensaje);
		return result;
	}

	private UserAccount encryptedUser(final UserAccount userAccount) {
		final String encodedPassword = this.encodePassword(userAccount.getPassword());

		userAccount.setPassword(encodedPassword);

		return userAccount;
	}

	private String encodePassword(final String password) {
		try {

			// Crear un objeto MessageDigest con el algoritmo MD5
			final MessageDigest md = MessageDigest.getInstance("MD5");

			// Obtener los bytes del password
			final byte[] passwordBytes = password.getBytes();

			// Calcular el hash MD5
			final byte[] digest = md.digest(passwordBytes);

			// Convertir el hash en una cadena hexadecimal
			final StringBuilder hexString = new StringBuilder();
			for (final byte b : digest)
				hexString.append(String.format("%02x", b));
			return hexString.toString();

		} catch (final NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null; // Manejar el error apropiadamente en tu aplicación
		}

	}

}
