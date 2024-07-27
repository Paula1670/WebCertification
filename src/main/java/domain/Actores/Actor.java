
package domain.Actores;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import domain.Comentario;
import domain.DomainEntity;
import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {

	private String	nombre;
	private String	apellidos;
	private String	correo;
	private String	telefono;
	private String	codigoPostal;


	public Actor() {
		super();
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(final String apellidos) {
		this.apellidos = apellidos;
	}

	public void setCorreo(final String correo) {
		this.correo = correo;
	}

	public void setTelefono(final String telefono) {
		this.telefono = telefono;
	}

	public void setCodigoPostal(final String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	@NotBlank
	public String getApellidos() {
		return this.apellidos;
	}

	@Email
	public String getCorreo() {
		return this.correo;
	}

	@NotBlank
	@Pattern(regexp = "^(\\+\\d+\\s+)?(\\(\\d+\\)\\s+)?\\d{4,}")
	public String getTelefono() {
		return this.telefono;
	}

	@NotBlank
	@Pattern(regexp = "\\w{5}")
	public String getCodigoPostal() {
		return this.codigoPostal;
	}


	//Relaciones -------------------------------------------------------
	private UserAccount				userAccount;

	//TODO: Revisar las relaciones
	private Collection<Comentario>	comentarios;
	private Collection<Actor>		suscriptores;
	private Collection<Actor>		seguidores;


	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public void setSuscriptores(final Collection<Actor> suscriptores) {
		this.suscriptores = suscriptores;
	}

	public void setSeguidores(final Collection<Actor> seguidores) {
		this.seguidores = seguidores;
	}

	public void setComentarios(final Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@NotNull
	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	@OneToMany
	public Collection<Comentario> getComentarios() {
		return this.comentarios;
	}

	@ManyToMany
	public Collection<Actor> getSuscriptores() {
		return this.suscriptores;
	}

	@ManyToMany
	public Collection<Actor> getSeguidores() {
		return this.seguidores;
	}

	//TODO: Revisar si es necesario realizar add y remove de suscriptores y seguidores

}
