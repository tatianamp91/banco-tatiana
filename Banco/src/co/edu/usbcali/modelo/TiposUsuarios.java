package co.edu.usbcali.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * TiposUsuarios entity. @author MyEclipse Persistence Tools
 */

public class TiposUsuarios implements java.io.Serializable {

	// Fields

	private Long tusuCodigo;
	private String tusuNombre;
	private Set usuarioses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TiposUsuarios() {
	}

	/** minimal constructor */
	public TiposUsuarios(Long tusuCodigo, String tusuNombre) {
		this.tusuCodigo = tusuCodigo;
		this.tusuNombre = tusuNombre;
	}

	/** full constructor */
	public TiposUsuarios(Long tusuCodigo, String tusuNombre, Set usuarioses) {
		this.tusuCodigo = tusuCodigo;
		this.tusuNombre = tusuNombre;
		this.usuarioses = usuarioses;
	}

	// Property accessors

	public Long getTusuCodigo() {
		return this.tusuCodigo;
	}

	public void setTusuCodigo(Long tusuCodigo) {
		this.tusuCodigo = tusuCodigo;
	}

	public String getTusuNombre() {
		return this.tusuNombre;
	}

	public void setTusuNombre(String tusuNombre) {
		this.tusuNombre = tusuNombre;
	}

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

}