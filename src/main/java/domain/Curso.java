/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Enumeraciones.CursoNivel;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Curso extends DomainEntity {

	public Curso() {
		super();
	}


	private String		titulo;
	private Date		fechaInicio;
	private Date		fechaFin;
	private String		diaSemana;
	private int			hora;
	private int			minuto;
	private CursoNivel	nivel;


	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setDiaSemana(final String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public void setHora(final int hora) {
		this.hora = hora;
	}

	public void setMinuto(final int minuto) {
		this.minuto = minuto;
	}

	public void setNivel(final CursoNivel nivel) {
		this.nivel = nivel;
	}

	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	@NotBlank
	public String getDiaSemana() {
		return this.diaSemana;
	}

	@Range(min = 0, max = 23)
	public int getHora() {
		return this.hora;
	}

	@Range(min = 0, max = 59)
	public int getMinuto() {
		return this.minuto;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public CursoNivel getNivel() {
		return this.nivel;
	}


	///Relaciones -----------------------------------------
	private Estilo					estilo;
	private Collection<Solicitud>	solicitudes;


	public void setEstilo(final Estilo estilo) {
		this.estilo = estilo;
	}

	public void setSolicitudes(final Collection<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	@ManyToOne(optional = false)
	public Estilo getEstilo() {
		return this.estilo;
	}

	@OneToMany(mappedBy = "curso")
	public Collection<Solicitud> getSolicitudes() {
		return this.solicitudes;
	}

}
