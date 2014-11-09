package co.edu.usbcali.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.modelo.Usuarios;
import co.edu.usbcali.utilidades.Utilidades;

@ManagedBean
@SessionScoped
public class UsuariosVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Usuarios> usuarios; 
	private InputText txtCedula;
	private List<SelectItem> tiposUsuarios;
	private SelectOneMenu tipoUsuario;
	private InputText txtNombre;
	private InputText txtLogin;
	private String pswClave;
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
		
	public void consultarTxtCedula() throws Exception{
		try{		
			Long cedula = Long.parseLong(txtCedula.getValue().toString());			
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(cedula);
			if(usuario != null){
				tipoUsuario.setValue(usuario.getTiposUsuarios().getTusuCodigo());
				txtNombre.setValue(usuario.getUsuNombre());
				txtLogin.setValue(usuario.getUsuLogin());
				pswClave = usuario.getUsuClave();
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnEliminar.setDisabled(false);				
			}else{
				tipoUsuario.setValue("");
				txtNombre.setValue("");
				txtLogin.setValue("");
				pswClave = "";
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnEliminar.setDisabled(true);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	public void accion_limpiar(){
		txtCedula.setValue("");
		tipoUsuario.setValue("");
		txtNombre.setValue("");
		txtLogin.setValue("");
		pswClave = "";
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		usuarios = null;
	}
	
	public void accion_crear() throws Exception{
		try{
			Usuarios usuario = new Usuarios();			
			usuario.setUsuCedula(Long.parseLong(txtCedula.getValue().toString()));	
			
			Long idTipoUsu = Long.parseLong(tipoUsuario.getValue().toString());
			TiposUsuarios tipoUsu = delegadoDeNegocio.consultarTipoUsuario(idTipoUsu);
			
			usuario.setTiposUsuarios(tipoUsu);
			usuario.setUsuNombre(txtNombre.getValue().toString());
			usuario.setUsuLogin(txtLogin.getValue().toString().toLowerCase());
			usuario.setUsuClave(pswClave);
			
			delegadoDeNegocio.crearUsuario(usuario);
			Utilidades.addInfoMessage(("El usuario se creó correctamente"));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_modificar() throws Exception{
		try{
			Long cedula = Long.parseLong(txtCedula.getValue().toString());
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(cedula);
			if(usuario != null){			
				Long idTipoUsu = Long.parseLong(tipoUsuario.getValue().toString());
				TiposUsuarios tipoUsu = delegadoDeNegocio.consultarTipoUsuario(idTipoUsu);
				
				usuario.setTiposUsuarios(tipoUsu);
				usuario.setUsuNombre(txtNombre.getValue().toString());
				usuario.setUsuLogin(txtLogin.getValue().toString().toLowerCase());
				usuario.setUsuClave(pswClave);
				
				delegadoDeNegocio.modificarUsuario(usuario);
				Utilidades.addInfoMessage(("El usuario se modificó correctamente"));
				accion_limpiar();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_eliminar() throws Exception{
		try{
			Long cedula = Long.parseLong(txtCedula.getValue().toString());
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(cedula);
			if(usuario != null){						
				delegadoDeNegocio.eliminarUsuario(usuario);
				Utilidades.addInfoMessage(("El usuario se eliminó correctamente"));
				accion_limpiar();
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

	public List<Usuarios> getUsuarios() {
		try{
			if(usuarios == null){
				usuarios = delegadoDeNegocio.consultarUsuarios();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public InputText getTxtCedula() {
		return txtCedula;
	}

	public void setTxtCedula(InputText txtCedula) {
		this.txtCedula = txtCedula;
	}

	public List<SelectItem> getTiposUsuarios() {
		try{
			tiposUsuarios = new ArrayList<SelectItem>();
			List<TiposUsuarios> tipoUsu = delegadoDeNegocio.consultarTiposUsuarios();
			if(tipoUsu != null){
				for (TiposUsuarios tipoUsuario : tipoUsu) {
					SelectItem selectItem = new SelectItem(tipoUsuario.getTusuCodigo(), tipoUsuario.getTusuNombre());
					tiposUsuarios.add(selectItem);
				}
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return tiposUsuarios;
	}

	public void setTiposUsuarios(List<SelectItem> tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}

	public SelectOneMenu getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(SelectOneMenu tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(InputText txtLogin) {
		this.txtLogin = txtLogin;
	}

	public String getPswClave() {
		return pswClave;
	}

	public void setPswClave(String pswClave) {
		this.pswClave = pswClave;
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