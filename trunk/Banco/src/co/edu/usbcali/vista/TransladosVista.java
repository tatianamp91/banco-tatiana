package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Usuarios;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class TransladosVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private Long cliId;
	private List<Cuentas> cuentas;
	private List<Long> numerosCuentasOrigen;
	private InputText saldoOrigen;
	private List<Long> numerosCuentasDestino;
	private InputText saldoDestino;
	private Long numCuentaOrigen;
	private Long numCuentaDestino;
	private InputText txtValor;
	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	
	public void accion_limpiar(){
		consultarCuentaOrigen();
		consultarCuentaDestino();
		txtValor.setValue("");
	}
	
	public void consultarCuentaOrigen() {
		try{
			Cuentas cuentaOrig = delegadoDeNegocio.consultarCuenta(numCuentaOrigen);
			saldoOrigen.setValue(cuentaOrig.getCueSaldo());
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void consultarCuentaDestino() {
		try{
			Cuentas cuentaDes = delegadoDeNegocio.consultarCuenta(numCuentaDestino);
			saldoDestino.setValue(cuentaDes.getCueSaldo());
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
		
	public void accion_translado() throws Exception {
		try{			
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(30L);
			Double valorTranslado = Double.parseDouble(txtValor.getValue().toString());
			Cuentas cuentaOrigen = delegadoDeNegocio.consultarCuenta(numCuentaOrigen);
			Cuentas cuentaDestino = delegadoDeNegocio.consultarCuenta(numCuentaDestino);
			
			delegadoDeNegocio.translado(usuario, valorTranslado, cuentaOrigen, cuentaDestino);
			Utilidades.addInfoMessage(("El translado se realizo correctamente"));
			accion_limpiar();		
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

	public List<Cuentas> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuentas> cuentas) {
		this.cuentas = cuentas;
	}

	public CommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public InputText getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
	}

	public List<Long> getNumerosCuentasOrigen() {
		try{
			if(numerosCuentasOrigen == null){
				HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		        long idCli = (long) httpSession.getAttribute("cliente");
				cliId = idCli;
				Clientes cliente = delegadoDeNegocio.consultarCliente(cliId);
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				if(cuentas != null){
					numerosCuentasOrigen = new ArrayList<Long>();
					for (Cuentas cuenta : cuentas) {
						numerosCuentasOrigen.add(cuenta.getCueNumero()); 
					}
				}
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return numerosCuentasOrigen;
	}
	
	public void setNumerosCuentasOrigen(List<Long> numerosCuentasOrigen) {
		this.numerosCuentasOrigen = numerosCuentasOrigen;
	}
	public List<Long> getNumerosCuentasDestino() {
		try{
			if(numerosCuentasDestino == null){
				if(cuentas != null){
					numerosCuentasDestino = new ArrayList<Long>();
					for (Cuentas cuenta : cuentas) {
						numerosCuentasDestino.add(cuenta.getCueNumero()); 
					}
				}
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return numerosCuentasDestino;
	}
	public void setNumerosCuentasDestino(List<Long> numerosCuentasDestino) {
		this.numerosCuentasDestino = numerosCuentasDestino;
	}
	public Long getCliId() {
		return cliId;
	}
	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}
	public Long getNumCuentaOrigen() {
		return numCuentaOrigen;
	}
	public void setNumCuentaOrigen(Long numCuentaOrigen) {
		this.numCuentaOrigen = numCuentaOrigen;
	}
	public Long getNumCuentaDestino() {
		return numCuentaDestino;
	}
	public void setNumCuentaDestino(Long numCuentaDestino) {
		this.numCuentaDestino = numCuentaDestino;
	}
	public InputText getSaldoOrigen() {
		return saldoOrigen;
	}
	public void setSaldoOrigen(InputText saldoOrigen) {
		this.saldoOrigen = saldoOrigen;
	}
	public InputText getSaldoDestino() {
		return saldoDestino;
	}
	public void setSaldoDestino(InputText saldoDestino) {
		this.saldoDestino = saldoDestino;
	}
}