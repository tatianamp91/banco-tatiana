package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.Date;
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
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Usuarios;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class ConsignacionesVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Consignaciones> consignaciones;
	private InputText txtNumCue;
	private InputText txtCliId;
	private InputText txtTipoDoc;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	private InputText txtValor;
	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	private boolean consig;
	
	public void accion_limpiar(){
		txtNumCue.setValue("");
		txtCliId.setValue("");
		txtTipoDoc.setValue("");
		txtNombre.setValue("");
		txtDireccion.setValue("");
		txtTelefono.setValue("");
		txtEmail.setValue("");
		txtValor.setValue("");
		btnCrear.setDisabled(true);
		consignaciones = null;
		consig = false;
	}
	
	public void consultarTxtNumCue(){
		try{
			Cuentas cuenta = delegadoDeNegocio.consultarCuenta(Long.parseLong(txtNumCue.getValue().toString()));
			if(cuenta != null){
				txtCliId.setValue(cuenta.getClientes().getCliId());
				txtTipoDoc.setValue(cuenta.getClientes().getTiposDocumentos().getTdocNombre());
				txtNombre.setValue(cuenta.getClientes().getCliNombre());
				txtDireccion.setValue(cuenta.getClientes().getCliDireccion());
				txtTelefono.setValue(cuenta.getClientes().getCliTelefono());
				txtEmail.setValue(cuenta.getClientes().getCliMail());
				txtValor.setValue("");
				btnCrear.setDisabled(false);
				consultarConsignaciones();
			} else {
				Utilidades.addErrorMessage("No existe la cuenta");
				btnCrear.setDisabled(true);
			}		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void consultarConsignaciones() {
		try{
			Cuentas cuenta = delegadoDeNegocio.consultarCuenta(Long.parseLong(txtNumCue.getValue().toString()));
			if(cuenta != null){
				consignaciones = delegadoDeNegocio.consultarConsignacionesCuenta(cuenta);
				consig = true;
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
		
	public void accion_consignar() throws Exception {
		try{			
			Consignaciones consignacion = new Consignaciones();
			
			ConsignacionesId id = new ConsignacionesId();
			Long idConsignacion = delegadoDeNegocio.getConsecutivoConsignaciones("SEQ_CONSIGNACIONES");
			Cuentas cuen = delegadoDeNegocio.consultarCuenta(Long.parseLong(txtNumCue.getValue().toString()));
			id.setConCodigo(idConsignacion);
			id.setCuentas(cuen);	
			consignacion.setId(id);
			
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        long cedula = (long) httpSession.getAttribute("usuario");
			
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(cedula);
			consignacion.setUsuarios(usuario);
			consignacion.setConValor(Double.parseDouble(txtValor.getValue().toString()));
			consignacion.setConDescripcion("Consignación");
			consignacion.setConFecha(new Date());
			
			delegadoDeNegocio.consignacion(consignacion);
			Utilidades.addInfoMessage(("La consignación se realizo correctamente"));
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
	public List<Consignaciones> getConsignaciones() {
		return consignaciones;
	}
	public void setConsignaciones(List<Consignaciones> consignaciones) {
		this.consignaciones = consignaciones;
	}
	public InputText getTxtNumCue() {
		return txtNumCue;
	}
	public void setTxtNumCue(InputText txtNumCue) {
		this.txtNumCue = txtNumCue;
	}
	public InputText getTxtCliId() {
		return txtCliId;
	}
	public void setTxtCliId(InputText txtCliId) {
		this.txtCliId = txtCliId;
	}
	public InputText getTxtTipoDoc() {
		return txtTipoDoc;
	}
	public void setTxtTipoDoc(InputText txtTipoDoc) {
		this.txtTipoDoc = txtTipoDoc;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtDireccion() {
		return txtDireccion;
	}
	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}
	public InputText getTxtTelefono() {
		return txtTelefono;
	}
	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	public InputText getTxtEmail() {
		return txtEmail;
	}
	public void setTxtEmail(InputText txtEmail) {
		this.txtEmail = txtEmail;
	}
	public InputText getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
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
	public boolean isConsig() {
		return consig;
	}
	public void setConsig(boolean consig) {
		this.consig = consig;
	}
}