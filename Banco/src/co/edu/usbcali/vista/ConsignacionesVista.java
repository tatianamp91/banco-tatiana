package co.edu.usbcali.vista;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

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
	private InputText txtIdCliente;
	private List<Cuentas> cuentas;
	private Long numCuenta;
	private InputText txtTipoDoc;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	private InputText txtValor;
	private InputText txtDescripcion;
	private Long usuCedula;
	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	
	public void accion_limpiar(){
		txtIdCliente.setValue("");
		txtTipoDoc.setValue("");
		txtNombre.setValue("");
		txtDireccion.setValue("");
		txtTelefono.setValue("");
		txtEmail.setValue("");
		txtValor.setValue("");
		txtDescripcion.setValue("");
		btnCrear.setDisabled(true);
		consignaciones = null;
		cuentas = null;
		numCuenta = null;
	}
	
	public void accion_consignar() throws Exception{
		try{			
			Consignaciones consignacion = new Consignaciones();
			
			ConsignacionesId id = new ConsignacionesId();
			Long idConsignacion = delegadoDeNegocio.getConsecutivo("SEQ_CONSIGNACIONES");
			Cuentas cuen = delegadoDeNegocio.consultarCuenta(numCuenta);
			id.setConCodigo(idConsignacion);
			id.setCuentas(cuen);	
			
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(usuCedula);
			consignacion.setUsuarios(usuario);
			consignacion.setConValor(Double.parseDouble(txtValor.getValue().toString()));
			consignacion.setConDescripcion(txtDescripcion.getValue().toString());
			consignacion.setConFecha(new Date());
			
			delegadoDeNegocio.consignacion(consignacion);
			Utilidades.addInfoMessage(("La consignación se realizo correctamente"));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void consultarTxtIdCliente(){
		try{
			Clientes cliente = delegadoDeNegocio.consultarCliente(Long.parseLong(txtIdCliente.getValue().toString()));
			if (cliente != null) {
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				txtTipoDoc.setValue(cliente.getTiposDocumentos().getTdocNombre());
				txtNombre.setValue(cliente.getCliNombre());
				txtDireccion.setValue(cliente.getCliDireccion());
				txtTelefono.setValue(cliente.getCliTelefono());
				txtEmail.setValue(cliente.getCliMail());
				txtValor.setValue("");
				txtDescripcion.setValue("");
				btnCrear.setDisabled(false);
			} else {
				Utilidades.addErrorMessage("No existe cliente");
				btnCrear.setDisabled(true);
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
		try{
			if(consignaciones == null){
				consignaciones = delegadoDeNegocio.consultarConsignaciones();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return consignaciones;
	}

	public void setConsignaciones(List<Consignaciones> consignaciones) {
		this.consignaciones = consignaciones;
	}

	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
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

	public Long getUsuCedula() {
		return usuCedula;
	}

	public void setUsuCedula(Long usuCedula) {
		this.usuCedula = usuCedula;
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

	public InputText getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(InputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}
	
}