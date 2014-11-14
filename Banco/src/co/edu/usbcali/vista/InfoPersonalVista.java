package co.edu.usbcali.vista;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class InfoPersonalVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;

	private Long cliId;
	private String txtId;
	private String txtTipoDoc;
	private String txtNombre;
	private String txtDireccion;
	private String txtTelefono;
	private String txtEmail;
	private CommandButton btnModificar;
	
	@PostConstruct
	public void consultarCliente(){
		try{
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        long idCli = (long) httpSession.getAttribute("cliente");
			cliId = idCli;
			Clientes cliente = delegadoDeNegocio.consultarCliente(cliId);
			if(cliente != null){
				txtId = cliente.getCliId().toString();
				txtTipoDoc = cliente.getTiposDocumentos().getTdocNombre().toString();
				txtNombre = cliente.getCliNombre().toString();
				txtDireccion = cliente.getCliDireccion().toString();
				txtTelefono = cliente.getCliTelefono().toString();
				txtEmail = cliente.getCliMail().toString();;			
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_modificar() throws Exception{
		try{
			cliId = Long.parseLong(txtId);
			Clientes cliente = delegadoDeNegocio.consultarCliente(cliId);
			if(cliente != null){			
				cliente.setCliNombre(txtNombre);
				cliente.setCliDireccion(txtDireccion);
				cliente.setCliTelefono(txtTelefono);
				cliente.setCliMail(txtEmail);
				
				delegadoDeNegocio.modificarCliente(cliente);
				Utilidades.addInfoMessage(("La información se modificó correctamente"));
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

	public Long getCliId() {
		return cliId;
	}

	public void setCliId(Long cliId) {
		this.cliId = cliId;
	}

	public String getTxtId() {
		return txtId;
	}

	public void setTxtId(String txtId) {
		this.txtId = txtId;
	}

	public String getTxtTipoDoc() {
		return txtTipoDoc;
	}

	public void setTxtTipoDoc(String txtTipoDoc) {
		this.txtTipoDoc = txtTipoDoc;
	}

	public String getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}

	public String getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public String getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public String getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail = txtEmail;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
}