/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Comentario extends DomainEntity {

	public Comentario() {
		super();
	}


	private Date	fechaRealizacion;
	private String	texto;


	public void setFechaRealizacion(final Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	@NotBlank
	@Length(min = 0, max = 140)
	public String getTexto() {
		return this.texto;
	}

}
