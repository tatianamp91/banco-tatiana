package co.edu.usbcali.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */

public class Usuarios implements java.io.Serializable {

	// Fields

	private Long usuCedula;
	private TiposUsuarios tiposUsuarios;
	private String usuNombre;
	private String usuLogin;
	private String usuClave;
	private Set retiroses = new HashSet(0);
	private Set consignacioneses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(Long usuCedula, String usuNombre, String usuLogin,
			String usuClave) {
		this.usuCedula = usuCedula;
		this.usuNombre = usuNombre;
		this.usuLogin = usuLogin;
		this.usuClave = usuClave;
	}

	/** full constructor */
	public Usuarios(Long usuCedula, TiposUsuarios tiposUsuarios,
			String usuNombre, String usuLogin, String usuClave, Set retiroses,
			Set consignacioneses) {
		this.usuCedula = usuCedula;
		this.tiposUsuarios = tiposUsuarios;
		this.usuNombre = usuNombre;
		this.usuLogin = usuLogin;
		this.usuClave = usuClave;
		this.retiroses = retiroses;
		this.consignacioneses = consignacioneses;
	}

	// Property accessors

	public Long getUsuCedula() {
		return this.usuCedula;
	}

	public void setUsuCedula(Long usuCedula) {
		this.usuCedula = usuCedula;
	}

	public TiposUsuarios getTiposUsuarios() {
		return this.tiposUsuarios;
	}

	public void setTiposUsuarios(TiposUsuarios tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}

	public String getUsuNombre() {
		return this.usuNombre;
	}

	public void setUsuNombre(String usuNombre) {
		this.usuNombre = usuNombre;
	}

	public String getUsuLogin() {
		return this.usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuClave() {
		return this.usuClave;
	}

	public void setUsuClave(String usuClave) {
		this.usuClave = usuClave;
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