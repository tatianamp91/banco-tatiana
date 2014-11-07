package co.edu.usbcali.modelo;

/**
 * RetirosId entity. @author MyEclipse Persistence Tools
 */

public class RetirosId implements java.io.Serializable {

	// Fields

	private Long retCodigo;
	private Cuentas cuentas;

	// Constructors

	/** default constructor */
	public RetirosId() {
	}

	/** full constructor */
	public RetirosId(Long retCodigo, Cuentas cuentas) {
		this.retCodigo = retCodigo;
		this.cuentas = cuentas;
	}

	// Property accessors

	public Long getRetCodigo() {
		return this.retCodigo;
	}

	public void setRetCodigo(Long retCodigo) {
		this.retCodigo = retCodigo;
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
		if (!(other instanceof RetirosId))
			return false;
		RetirosId castOther = (RetirosId) other;

		return ((this.getRetCodigo() == castOther.getRetCodigo()) || (this
				.getRetCodigo() != null && castOther.getRetCodigo() != null && this
				.getRetCodigo().equals(castOther.getRetCodigo())))
				&& ((this.getCuentas() == castOther.getCuentas()) || (this
						.getCuentas() != null && castOther.getCuentas() != null && this
						.getCuentas().equals(castOther.getCuentas())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getRetCodigo() == null ? 0 : this.getRetCodigo().hashCode());
		result = 37 * result
				+ (getCuentas() == null ? 0 : this.getCuentas().hashCode());
		return result;
	}

}