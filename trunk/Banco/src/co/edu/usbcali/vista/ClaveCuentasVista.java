package co.edu.usbcali.vista;

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
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class ClaveCuentasVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuentas> cuentas;
	private Long numCuenta;
	private Long cliId;
	private InputText txtNumCue;
	private String pswClaveActual;
	private String pswClave;
	private boolean showDialog;
	private CommandButton btnModificar;
	private CommandButton btnCancelar;
	
	public void showDialog(){
		showDialog = true;
	}
	
	public void accion_cambiar_clave() throws Exception{
		try{
			Cuentas cuenta = delegadoDeNegocio.consultarCuenta(numCuenta);
			if(cuenta != null){
				if(!pswClaveActual.equals(cuenta.getCueClave())){
					throw new Exception("La clave actual es incorrecta");
				}
				cuenta.setCueClave(pswClave);
				delegadoDeNegocio.modificarCuenta(cuenta);
				Utilidades.addInfoMessage(("Se cambio la clave de la cuenta correctamente"));
				showDialog = false;
			}
		}catch(Exception e){
			Utilidades.addErrorMessage((e.getMessage()));
		}
	}
	
	public void estado(){
		if(cuentas != null){
			for (Cuentas cuenta : cuentas) {
				if(cuenta.getCueActiva().trim().equals("S")){
					cuenta.setCueActiva("Activa");
				}else{
					cuenta.setCueActiva("Inactiva");
				}
			}
		}
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}


	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}


	public List<Cuentas> getCuentas() {
		try{
			if(cuentas == null){
		        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		        long idCli = (long) httpSession.getAttribute("cliente");
				cliId = idCli;
				Clientes cliente = delegadoDeNegocio.consultarCliente(cliId);
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				estado();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage((e.getMessage()));
		}
		return cuentas;
	}

	public void setCuentas(List<Cuentas> cuentas) {
		this.cuentas = cuentas;
	}
	public Long getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}
	public Long getCliId() {
		return cliId;
	}
	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}
	public InputText getTxtNumCue() {
		return txtNumCue;
	}
	public void setTxtNumCue(InputText txtNumCue) {
		this.txtNumCue = txtNumCue;
	}
	public String getPswClave() {
		return pswClave;
	}
	public void setPswClave(String pswClave) {
		this.pswClave = pswClave;
	}
	public boolean isShowDialog() {
		return showDialog;
	}
	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public String getPswClaveActual() {
		return pswClaveActual;
	}
	public void setPswClaveActual(String pswClaveActual) {
		this.pswClaveActual = pswClaveActual;
	}
	public CommandButton getBtnCancelar() {
		return btnCancelar;
	}
	public void setBtnCancelar(CommandButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
}