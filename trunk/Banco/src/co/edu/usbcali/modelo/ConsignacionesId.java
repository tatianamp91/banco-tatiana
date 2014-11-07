package co.edu.usbcali.modelo;

/**
 * ConsignacionesId entity. @author MyEclipse Persistence Tools
 */

public class ConsignacionesId implements java.io.Serializable {

	// Fields

	private Long conCodigo;
	private Cuentas cuentas;

	// Constructors

	/** default constructor */
	public ConsignacionesId() {
	}

	/** full constructor */
	public ConsignacionesId(Long conCodigo, Cuentas cuentas) {
		this.conCodigo = conCodigo;
		this.cuentas = cuentas;
	}

	// Property accessors

	public Long getConCodigo() {
		return this.conCodigo;
	}

	public void setConCodigo(Long conCodigo) {
		this.conCodigo = conCodigo;
	}

	public Cuentas getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(Cuentas cuentas) {
		this.cuentas = cuentas;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ConsignacionesId))
			return false;
		ConsignacionesId castOther = (ConsignacionesId) other;

		return ((this.getConCodigo() == castOther.getConCodigo()) || (this
				.getConCodigo() != null && castOther.getConCodigo() != null && this
				.getConCodigo().equals(castOther.getConCodigo())))
				&& ((this.getCuentas() == castOther.getCuentas()) || (this
						.getCuentas() != null && castOther.getCuentas() != null && this
						.getCuentas().equals(castOther.getCuentas())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getConCodigo() == null ? 0 : this.getConCodigo().hashCode());
		result = 37 * result
				+ (getCuentas() == null ? 0 : this.getCuentas().hashCode());
		return result;
	}

}