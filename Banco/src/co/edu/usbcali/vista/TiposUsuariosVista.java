package co.edu.usbcali.vista;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class TiposUsuariosVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TiposUsuarios> tiposUsuarios;
	private InputText txtIdTipoUsu;
	private InputText txtNombre;
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
	
	
	public void consultarTxtIdTipoUsu() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoUsu.getValue().toString());
			TiposUsuarios tipoUsuario = delegadoDeNegocio.consultarTipoUsuario(id);
			if(tipoUsuario != null){
				txtNombre.setValue(tipoUsuario.getTusuNombre());
				btnCrear.setDisabled(true);
				btnModificar.setDisabled(false);
				btnEliminar.setDisabled(false);	
			}else{
				txtNombre.setValue("");
				btnCrear.setDisabled(false);
				btnModificar.setDisabled(true);
				btnEliminar.setDisabled(true);
			}			
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	public void accion_limpiar(){
		txtIdTipoUsu.setValue("");
		txtNombre.setValue("");
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		tiposUsuarios = null;
	}
	
	public void accion_crear() throws Exception{
		try{
			TiposUsuarios tipoUsuario = new TiposUsuarios();
			tipoUsuario.setTusuCodigo(Long.parseLong(txtIdTipoUsu.getValue().toString()));
			tipoUsuario.setTusuNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.crearTipoUsuario(tipoUsuario);
			Utilidades.addInfoMessage(("El tipo de usuario se creó correctamente"));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_modificar() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoUsu.getValue().toString());
			TiposUsuarios tipoUsuario = delegadoDeNegocio.consultarTipoUsuario(id);
			if(tipoUsuario != null){
				tipoUsuario.setTusuNombre(txtNombre.getValue().toString());
				
				delegadoDeNegocio.modificarTipoUsuario(tipoUsuario);
				Utilidades.addInfoMessage(("El tipo de usuario se modificó correctamente"));
				accion_limpiar();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_eliminar() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoUsu.getValue().toString());
			TiposUsuarios tipoUsuario = delegadoDeNegocio.consultarTipoUsuario(id);
			if(tipoUsuario != null){			
				delegadoDeNegocio.eliminarTipoUsuario(tipoUsuario);
				Utilidades.addInfoMessage(("El tipo de usuario se eliminó correctamente"));
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
	
	public List<TiposUsuarios> getTiposUsuarios() {
		try{
			if(tiposUsuarios == null){
				tiposUsuarios = delegadoDeNegocio.consultarTiposUsuarios();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return tiposUsuarios;
	}

	public void setTiposUsuarios(List<TiposUsuarios> tiposUsuarios) {
		this.tiposUsuarios = tiposUsuarios;
	}

	public InputText getTxtIdTipoUsu() {
		return txtIdTipoUsu;
	}

	public void setTxtIdTipoUsu(InputText txtIdTipoUsu) {
		this.txtIdTipoUsu = txtIdTipoUsu;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
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