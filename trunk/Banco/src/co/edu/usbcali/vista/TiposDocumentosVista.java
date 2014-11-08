package co.edu.usbcali.vista;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.TiposDocumentos;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class TiposDocumentosVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TiposDocumentos> tipoDocumentos;
	private InputText txtIdTipoDoc;
	private InputText txtNombre;
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnEliminar;
	private CommandButton btnLimpiar;
	
	
	public void consultarTxtIdTipoDoc() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoDoc.getValue().toString());
			TiposDocumentos tipoDocumento = delegadoDeNegocio.consultarTipoDocumento(id);
			if(tipoDocumento != null){
				txtNombre.setValue(tipoDocumento.getTdocNombre());
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
		txtIdTipoDoc.setValue("");
		txtNombre.setValue("");
		btnCrear.setDisabled(true);
		btnModificar.setDisabled(true);
		btnEliminar.setDisabled(true);
		tipoDocumentos = null;
	}
	
	public void accion_crear() throws Exception{
		try{
			TiposDocumentos tipoDocumento = new TiposDocumentos();
			tipoDocumento.setTdocCodigo(Long.parseLong(txtIdTipoDoc.getValue().toString()));
			tipoDocumento.setTdocNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.crearTipoDocumento(tipoDocumento);
			Utilidades.addInfoMessage(("El tipo de documento se creó correctamente"));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_modificar() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoDoc.getValue().toString());
			TiposDocumentos tipoDocumento = delegadoDeNegocio.consultarTipoDocumento(id);
			if(tipoDocumento != null){
				tipoDocumento.setTdocNombre(txtNombre.getValue().toString());
				
				delegadoDeNegocio.modificarTipoDocumento(tipoDocumento);
				Utilidades.addInfoMessage(("El tipo de documento se modificó correctamente"));
				accion_limpiar();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_eliminar() throws Exception{
		try{
			Long id = Long.parseLong(txtIdTipoDoc.getValue().toString());
			TiposDocumentos tipoDocumento = delegadoDeNegocio.consultarTipoDocumento(id);
			if(tipoDocumento != null){
				delegadoDeNegocio.eliminarTipoDocumento(tipoDocumento);
				Utilidades.addInfoMessage(("El tipo de documento se eliminó correctamente"));
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

	public List<TiposDocumentos> getTipoDocumentos() {
		try{
			if(tipoDocumentos == null){
				tipoDocumentos = delegadoDeNegocio.consultarTiposDocumentos();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return tipoDocumentos;
	}

	public void setTipoDocumentos(List<TiposDocumentos> tipoDocumentos) {
		this.tipoDocumentos = tipoDocumentos;
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

	public InputText getTxtIdTipoDoc() {
		return txtIdTipoDoc;
	}

	public void setTxtIdTipoDoc(InputText txtIdTipoDoc) {
		this.txtIdTipoDoc = txtIdTipoDoc;
	}
}