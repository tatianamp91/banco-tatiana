package co.edu.usbcali.modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Clientes entity. @author MyEclipse Persistence Tools
 */

public class Clientes implements java.io.Serializable {

	// Fields

	private Long cliId;
	private TiposDocumentos tiposDocumentos;
	private String cliNombre;
	private String cliDireccion;
	private String cliTelefono;
	private String cliMail;
	private Set cuentases = new HashSet(0);

	// Constructors

	/** default constructor */
	public Clientes() {
	}

	/** minimal constructor */
	public Clientes(Long cliId, TiposDocumentos tiposDocumentos,
			String cliNombre, String cliDireccion, String cliTelefono) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
	}

	/** full constructor */
	public Clientes(Long cliId, TiposDocumentos tiposDocumentos,
			String cliNombre, String cliDireccion, String cliTelefono,
			String cliMail, Set cuentases) {
		this.cliId = cliId;
		this.tiposDocumentos = tiposDocumentos;
		this.cliNombre = cliNombre;
		this.cliDireccion = cliDireccion;
		this.cliTelefono = cliTelefono;
		this.cliMail = cliMail;
		this.cuentases = cuentases;
	}

	// Property accessors

	public Long getCliId() {
		return this.cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public TiposDocumentos getTiposDocumentos() {
		return this.tiposDocumentos;
	}

	public void setTiposDocumentos(TiposDocumentos tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}

	public String getCliNombre() {
		return this.cliNombre;
	}

	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}

	public String getCliDireccion() {
		return this.cliDireccion;
	}

	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}

	public String getCliTelefono() {
		return this.cliTelefono;
	}

	public void setCliTelefono(String cliTelefono) {
		this.cliTelefono = cliTelefono;
	}

	public String getCliMail() {
		return this.cliMail;
	}

	public void setCliMail(String cliMail) {
		this.cliMail = cliMail;
	}

	public Set getCuentases() {
		return this.cuentases;
	}

	public void setCuentases(Set cuentases) {
		this.cuentases = cuentases;
	}

}