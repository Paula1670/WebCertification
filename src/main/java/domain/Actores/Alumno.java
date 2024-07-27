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

import domain.Solicitud;
import domain.DataType.TarjetaDeCredito;

/**
 *
 * @author alejandro
 */
@Entity
@Access(AccessType.PROPERTY)
public class Alumno extends Actor {

	public Alumno() {
		super();
	}


	private TarjetaDeCredito tarjetaDeCredito;


	public void setTarjetaDeCredito(final TarjetaDeCredito tarjetaDeCredito) {
		this.tarjetaDeCredito = tarjetaDeCredito;
	}

	public TarjetaDeCredito getTarjetaDeCredito() {
		return this.tarjetaDeCredito;
	}


	//Relaciones --------------------------------------
	private Collection<Solicitud> solicitudes;


	public void setSolicitudes(final Collection<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	@OneToMany(mappedBy = "alumno")
	public Collection<Solicitud> getSolicitudes() {
		return this.solicitudes;
	}

}
