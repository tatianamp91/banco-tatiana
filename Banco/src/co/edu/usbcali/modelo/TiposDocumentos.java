package co.edu.usbcali.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * TiposDocumentos entity. @author MyEclipse Persistence Tools
 */

public class TiposDocumentos implements java.io.Serializable {

	// Fields

	private Long tdocCodigo;
	private String tdocNombre;
	private Set clienteses = new HashSet(0);

	// Constructors

	/** default constructor */
	public TiposDocumentos() {
	}

	/** minimal constructor */
	public TiposDocumentos(Long tdocCodigo, String tdocNombre) {
		this.tdocCodigo = tdocCodigo;
		this.tdocNombre = tdocNombre;
	}

	/** full constructor */
	public TiposDocumentos(Long tdocCodigo, String tdocNombre, Set clienteses) {
		this.tdocCodigo = tdocCodigo;
		this.tdocNombre = tdocNombre;
		this.clienteses = clienteses;
	}

	// Property accessors

	public Long getTdocCodigo() {
		return this.tdocCodigo;
	}

	public void setTdocCodigo(Long tdocCodigo) {
		this.tdocCodigo = tdocCodigo;
	}

	public String getTdocNombre() {
		return this.tdocNombre;
	}

	public void setTdocNombre(String tdocNombre) {
		this.tdocNombre = tdocNombre;
	}

	public Set getClienteses() {
		return this.clienteses;
	}

	public void setClienteses(Set clienteses) {
		this.clienteses = clienteses;
	}

}