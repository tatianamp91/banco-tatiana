package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.TiposDocumentos;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class ClientesVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Clientes> clientes; 
	private InputText txtId;
	private List<SelectItem> tiposDocumentos;
	private SelectOneMenu tipoDocumento;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
	
	
	public void consultarTxtId() throws Exception{
		try{		
			Long id = Long.parseLong(txtId.getValue().toString());
			
			Clientes cliente = delegadoDeNegocio.consultarCliente(id);
			if(cliente != null){
				tipoDocumento.setValue(cliente.getTiposDocumentos().getTdocCodigo());
				txtNombre.setValue(cliente.getCliNombre());
				txtDireccion.setValue(cliente.getCliDireccion());
				txtTelefono.setValue(cliente.getCliTelefono());
				txtEmail.setValue(cliente.getCliMail());
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnEliminar.setDisabled(false);				
			}else{
				tipoDocumento.setValue("");
				txtNombre.setValue("");
				txtDireccion.setValue("");
				txtTelefono.setValue("");
				txtEmail.setValue("");
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnEliminar.setDisabled(true);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	public void accion_limpiar(){
		txtId.setValue("");
		tipoDocumento.setValue("");
		txtNombre.setValue("");
		txtDireccion.setValue("");
		txtTelefono.setValue("");
		txtEmail.setValue("");
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		clientes = null;
	}
	
	public void accion_crear() throws Exception{
		try{
			Clientes cliente = new Clientes();
			
			cliente.setCliId(Long.parseLong(txtId.getValue().toString()));
			
			Long idTipoDoc = Long.parseLong(tipoDocumento.getValue().toString());
			TiposDocumentos tipoDoc = delegadoDeNegocio.consultarTipoDocumento(idTipoDoc);
			
			cliente.setTiposDocumentos(tipoDoc);
			cliente.setCliNombre(txtNombre.getValue().toString());
			cliente.setCliDireccion(txtDireccion.getValue().toString());
			cliente.setCliTelefono(txtTelefono.getValue().toString());
			cliente.setCliMail(txtEmail.getValue().toString());
			
			delegadoDeNegocio.crearCliente(cliente);
			Utilidades.addInfoMessage(("El cliente se creó correctamente"));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_modificar() throws Exception{
		try{
			Long id = Long.parseLong(txtId.getValue().toString());
			Clientes cliente = delegadoDeNegocio.consultarCliente(id);
			if(cliente != null){
				Long idTipoDoc = Long.parseLong(tipoDocumento.getValue().toString());
				TiposDocumentos tipoDoc = delegadoDeNegocio.consultarTipoDocumento(idTipoDoc);
				
				cliente.setTiposDocumentos(tipoDoc);
				cliente.setCliNombre(txtNombre.getValue().toString());
				cliente.setCliDireccion(txtDireccion.getValue().toString());
				cliente.setCliTelefono(txtTelefono.getValue().toString());
				cliente.setCliMail(txtEmail.getValue().toString());
				
				delegadoDeNegocio.modificarCliente(cliente);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El cliente se modificó correctamente"));
				accion_limpiar();	
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
	
	public void accion_eliminar() throws Exception{
		try{
			Long id = Long.parseLong(txtId.getValue().toString());
			Clientes cliente = delegadoDeNegocio.consultarCliente(id);
			if(cliente != null){		
				delegadoDeNegocio.eliminarCliente(cliente);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("El cliente se eliminó correctamente"));
				accion_limpiar();	
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
	
	public List<Clientes> getClientes() throws Exception {
		try{		
			if(clientes == null){
				clientes = delegadoDeNegocio.consultarClientes();
			}
		}catch(Exception e){
			throw new Exception(e);
		}
		return clientes;
	}
	public void setClientes(List<Clientes> clientes) {
		this.clientes = clientes;
	}
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	public InputText getTxtId() {
		return txtId;
	}
	public void setTxtId(InputText txtId) {
		this.txtId = txtId;
	}
	public List<SelectItem> getTiposDocumentos() throws Exception {
		try{		
			if(tiposDocumentos == null){
				tiposDocumentos = new ArrayList<SelectItem>();
				List<TiposDocumentos> tipos = delegadoDeNegocio.consultarTiposDocumentos();
				if(tipos != null){
					for (TiposDocumentos tipoDocumento : tipos) {
						SelectItem selectItem = new SelectItem(tipoDocumento.getTdocCodigo(), tipoDocumento.getTdocNombre());
						tiposDocumentos.add(selectItem);
					}
				}
			}
		}catch(Exception e){
			throw new Exception(e);
		}
		return tiposDocumentos;
	}
	public void setTiposDocumentos(List<SelectItem> tiposDocumentos) {
		this.tiposDocumentos = tiposDocumentos;
	}
	public SelectOneMenu getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(SelectOneMenu tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public CommandButton getBtnEliminar() {
		return btnEliminar;
	}
	public void setBtnEliminar(CommandButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
}