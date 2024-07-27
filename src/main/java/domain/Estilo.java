/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Estilo extends DomainEntity {

	public Estilo() {
		super();
	}


	private String				nombre;
	private String				descripcion;
	private Collection<String>	imagenes;
	private Collection<String>	videos;


	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImagenes(final Collection<String> imagenes) {
		this.imagenes = imagenes;
	}

	public void setVideos(final Collection<String> videos) {
		this.videos = videos;
	}

	@NotBlank
	public String getNombre() {
		return this.nombre;
	}

	@NotBlank
	public String getDescripcion() {
		return this.descripcion;
	}

	@ElementCollection
	public Collection<String> getImagenes() {
		return this.imagenes;
	}

	@ElementCollection
	public Collection<String> getVideos() {
		return this.videos;
	}

}
