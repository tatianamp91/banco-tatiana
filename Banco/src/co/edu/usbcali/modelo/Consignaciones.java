package co.edu.usbcali.modelo;

import java.util.Date;

/**
 * Consignaciones entity. @author MyEclipse Persistence Tools
 */

public class Consignaciones implements java.io.Serializable {

	// Fields

	private ConsignacionesId id;
	private Usuarios usuarios;
	private Double conValor;
	private Date conFecha;
	private String conDescripcion;

	// Constructors

	/** default constructor */
	public Consignaciones() {
	}

	/** minimal constructor */
	public Consignaciones(ConsignacionesId id, Double conValor, Date conFecha,
			String conDescripcion) {
		this.id = id;
		this.conValor = conValor;
		this.conFecha = conFecha;
		this.conDescripcion = conDescripcion;
	}

	/** full constructor */
	public Consignaciones(ConsignacionesId id, Usuarios usuarios,
			Double conValor, Date conFecha, String conDescripcion) {
		this.id = id;
		this.usuarios = usuarios;
		this.conValor = conValor;
		this.conFecha = conFecha;
		this.conDescripcion = conDescripcion;
	}

	// Property accessors

	public ConsignacionesId getId() {
		return this.id;
	}

	public void setId(ConsignacionesId id) {
		this.id = id;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Double getConValor() {
		return this.conValor;
	}

	public void setConValor(Double conValor) {
		this.conValor = conValor;
	}

	public Date getConFecha() {
		return this.conFecha;
	}

	public void setConFecha(Date conFecha) {
		this.conFecha = conFecha;
	}

	public String getConDescripcion() {
		return this.conDescripcion;
	}

	public void setConDescripcion(String conDescripcion) {
		this.conDescripcion = conDescripcion;
	}

}