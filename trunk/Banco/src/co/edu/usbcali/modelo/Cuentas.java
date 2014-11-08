package co.edu.usbcali.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Cuentas entity. @author MyEclipse Persistence Tools
 */

public class Cuentas implements java.io.Serializable {

	// Fields

	private Long cueNumero;
	private Clientes clientes;
	private Double cueSaldo;
	private String cueActiva;
	private String cueClave;
	private Set retiroses = new HashSet(0);
	private Set consignacioneses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cuentas() {
	}

	/** minimal constructor */
	public Cuentas(Long cueNumero, Clientes clientes, Double cueSaldo,
			String cueActiva, String cueClave) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
	}

	/** full constructor */
	public Cuentas(Long cueNumero, Clientes clientes, Double cueSaldo,
			String cueActiva, String cueClave, Set retiroses,
			Set consignacioneses) {
		this.cueNumero = cueNumero;
		this.clientes = clientes;
		this.cueSaldo = cueSaldo;
		this.cueActiva = cueActiva;
		this.cueClave = cueClave;
		this.retiroses = retiroses;
		this.consignacioneses = consignacioneses;
	}

	// Property accessors

	public Long getCueNumero() {
		return this.cueNumero;
	}

	public void setCueNumero(Long cueNumero) {
		this.cueNumero = cueNumero;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Double getCueSaldo() {
		return this.cueSaldo;
	}

	public void setCueSaldo(Double cueSaldo) {
		this.cueSaldo = cueSaldo;
	}

	public String getCueActiva() {
		return this.cueActiva;
	}

	public void setCueActiva(String cueActiva) {
		this.cueActiva = cueActiva;
	}

	public String getCueClave() {
		return this.cueClave;
	}

	public void setCueClave(String cueClave) {
		this.cueClave = cueClave;
	}

	public Set getRetiroses() {
		return this.retiroses;
	}

	public void setRetiroses(Set retiroses) {
		this.retiroses = retiroses;
	}

	public Set getConsignacioneses() {
		return this.consignacioneses;
	}

	public void setConsignacioneses(Set consignacioneses) {
		this.consignacioneses = consignacioneses;
	}

}