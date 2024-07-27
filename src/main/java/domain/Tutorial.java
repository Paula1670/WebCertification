/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	public Tutorial() {
		super();
	}


	private String	tutorial;
	private String	descripcion;
	private int		visualizaciones;
	private String	video;


	public void setTutorial(final String tutorial) {
		this.tutorial = tutorial;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public void setVisualizaciones(final int visualizaciones) {
		this.visualizaciones = visualizaciones;
	}

	public void setVideo(final String video) {
		this.video = video;
	}

	@NotBlank
	public String getTutorial() {
		return this.tutorial;
	}

	@NotBlank
	public String getDescripcion() {
		return this.descripcion;
	}

	@Min(0)
	public int getVisualizaciones() {
		return this.visualizaciones;
	}

	@NotBlank
	@URL
	public String getVideo() {
		return this.video;
	}

}
