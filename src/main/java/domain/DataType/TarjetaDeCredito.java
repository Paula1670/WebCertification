/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package domain.DataType;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * @author alejandro
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class TarjetaDeCredito {

	private String	titular;
	private String	marca;
	private String	numero;
	private int		mesCaducidad;
	private int		anioCaducidad;
	private int		cvv;


	public void setTitular(final String titular) {
		this.titular = titular;
	}

	public void setMarca(final String marca) {
		this.marca = marca;
	}

	public void setNumero(final String numero) {
		this.numero = numero;
	}

	public void setMesCaducidad(final int mesCaduucidad) {
		this.mesCaducidad = mesCaduucidad;
	}

	public void setAnioCaducidad(final int anioCaducidad) {
		this.anioCaducidad = anioCaducidad;
	}

	public void setCvv(final int cvv) {
		this.cvv = cvv;
	}

	@NotBlank
	public String getTitular() {
		return this.titular;
	}

	@NotBlank
	public String getMarca() {
		return this.marca;
	}

	@NotNull
	@CreditCardNumber
	public String getNumero() {
		return this.numero;
	}

	@Range(min = 1, max = 12)
	public int getMesCaducidad() {
		return this.mesCaducidad;
	}

	@Min(2024)
	public int getAnioCaducidad() {
		return this.anioCaducidad;
	}

	@Range(min = 100, max = 999)
	public int getCvv() {
		return this.cvv;
	}

}
