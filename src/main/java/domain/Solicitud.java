/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import domain.Actores.Alumno;
import domain.Enumeraciones.SolicitudEstado;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Solicitud extends DomainEntity {

	public Solicitud() {
		super();
	}


	private Date			fecha;
	private SolicitudEstado	estado;


	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}

	public void setEstado(final SolicitudEstado estado) {
		this.estado = estado;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getFecha() {
		return this.fecha;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public SolicitudEstado getEstado() {
		return this.estado;
	}


	//Relaciones ----------------------------------------------
	private Alumno	alumno;
	private Curso	curso;


	public void setAlumno(final Alumno alumno) {
		this.alumno = alumno;
	}

	public void setCurso(final Curso curso) {
		this.curso = curso;
	}

	@ManyToOne(optional = false)
	public Alumno getAlumno() {
		return this.alumno;
	}

	@ManyToOne(optional = false)
	public Curso getCurso() {
		return this.curso;
	}

}
