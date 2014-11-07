package co.edu.usbcali.modelo;

import java.util.Date;

/**
 * Retiros entity. @author MyEclipse Persistence Tools
 */

public class Retiros implements java.io.Serializable {

	// Fields

	private RetirosId id;
	private Usuarios usuarios;
	private Double retValor;
	private Date retFecha;
	private String retDescripcion;

	// Constructors

	/** default constructor */
	public Retiros() {
	}

	/** minimal constructor */
	public Retiros(RetirosId id, Double retValor, Date retFecha,
			String retDescripcion) {
		this.id = id;
		this.retValor = retValor;
		this.retFecha = retFecha;
		this.retDescripcion = retDescripcion;
	}

	/** full constructor */
	public Retiros(RetirosId id, Usuarios usuarios, Double retValor,
			Date retFecha, String retDescripcion) {
		this.id = id;
		this.usuarios = usuarios;
		this.retValor = retValor;
		this.retFecha = retFecha;
		this.retDescripcion = retDescripcion;
	}

	// Property accessors

	public RetirosId getId() {
		return this.id;
	}

	public void setId(RetirosId id) {
		this.id = id;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Double getRetValor() {
		return this.retValor;
	}

	public void setRetValor(Double retValor) {
		this.retValor = retValor;
	}

	public Date getRetFecha() {
		return this.retFecha;
	}

	public void setRetFecha(Date retFecha) {
		this.retFecha = retFecha;
	}

	public String getRetDescripcion() {
		return this.retDescripcion;
	}

	public void setRetDescripcion(String retDescripcion) {
		this.retDescripcion = retDescripcion;
	}

}