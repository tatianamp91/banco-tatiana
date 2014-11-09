package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class TransaccionesVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Consignaciones> consignaciones;
	private List<Retiros> retiros;
	private List<Cuentas> cuentas;
	private List<Long> numerosCuentas;
	private String txtNombre;
	private String txtSaldo;
	private Long numCuenta;
	private Long cliId;
	private CommandButton btnLimpiar;
	
	public void accion_limpiar(){
		consignaciones = null;
		cuentas = null;
		numCuenta = null;
	}
	
	public void consultarTransacciones() {
		try{
			Cuentas cuenta = delegadoDeNegocio.consultarCuenta(numCuenta);
			if(cuenta != null){
				consignaciones = delegadoDeNegocio.consultarConsignacionesCuenta(cuenta);
				retiros = delegadoDeNegocio.consultarRetirosCuenta(cuenta);
				txtSaldo = cuenta.getCueSaldo().toString();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
					
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<Consignaciones> getConsignaciones() {
		return consignaciones;
	}

	public void setConsignaciones(List<Consignaciones> consignaciones) {
		this.consignaciones = consignaciones;
	}
	

	public List<Cuentas> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuentas> cuentas) {
		this.cuentas = cuentas;
	}

	public Long getNumCuenta() {
		return numCuenta;
	}

	public void setnumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
	
	public List<Long> getNumerosCuentas() {
		try{
			if(numerosCuentas == null){
				cliId = 101234L; // tomar usuario de sesion
				Clientes cliente = delegadoDeNegocio.consultarCliente(cliId);
				txtNombre = cliente.getCliNombre();
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				if(cuentas != null){
					numerosCuentas = new ArrayList<Long>();
					for (Cuentas cuenta : cuentas) {
						numerosCuentas.add(cuenta.getCueNumero()); 
					}
				}
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return numerosCuentas;
	}

	public void setNumerosCuentas(List<Long> numerosCuentas) {
		this.numerosCuentas = numerosCuentas;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}

	public String getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public List<Retiros> getRetiros() {
		return retiros;
	}

	public void setRetiros(List<Retiros> retiros) {
		this.retiros = retiros;
	}

	public String getTxtSaldo() {
		return txtSaldo;
	}

	public void setTxtSaldo(String txtSaldo) {
		this.txtSaldo = txtSaldo;
	}	
}