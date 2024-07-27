/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain.Actores;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

import domain.Curso;
import domain.Tutorial;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Academia extends Actor {

	private String nombreComercial;


	public Academia() {
		super();
	}

	public void setNombreComercial(final String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	@NotBlank
	public String getNombreComercial() {
		return this.nombreComercial;
	}


	//Relaciones ------------------------------------------
	private Collection<Curso>		cursos;
	private Collection<Tutorial>	tutoriales;


	public void setCursos(final Collection<Curso> cursos) {
		this.cursos = cursos;
	}

	public void setTutoriales(final Collection<Tutorial> tutoriales) {
		this.tutoriales = tutoriales;
	}

	@OneToMany
	public Collection<Curso> getCursos() {
		return this.cursos;
	}

	@OneToMany
	public Collection<Tutorial> getTutoriales() {
		return this.tutoriales;
	}

	//TODO: Revisar si Son necesarios
	public void addCurso(final Curso curso) {
		this.cursos.add(curso);
	}

	public void removeCurso(final Curso curso) {
		this.cursos.remove(curso);
	}

	public void addTutorial(final Tutorial tutorial) {
		this.tutoriales.add(tutorial);
	}

	public void removeTutorial(final Tutorial tutorial) {
		this.tutoriales.remove(tutorial);
	}

}
