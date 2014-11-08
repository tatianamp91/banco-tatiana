package co.edu.usbcali.vista;

import java.util.ArrayList;
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
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.Usuarios;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class RetirosVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Retiros> retiros;
	private InputText txtIdCliente;
	private List<Cuentas> cuentas;
	private List<Long> numerosCuentas;
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
		retiros = null;
		cuentas = null;
		numCuenta = null;
	}
	
	public void consultarTxtIdCliente(){
		try{
			Clientes cliente = delegadoDeNegocio.consultarCliente(Long.parseLong(txtIdCliente.getValue().toString()));
			if (cliente != null) {
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				if(cuentas != null){
					numerosCuentas = new ArrayList<Long>();
					for (Cuentas cuenta : cuentas) {
						numerosCuentas.add(cuenta.getCueNumero()); 
					}
				}
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
	
	public void consultarRetiros() {
		try{
			Cuentas cuenta = delegadoDeNegocio.consultarCuenta(numCuenta);
			if(cuenta != null){
				retiros = null;
				retiros = delegadoDeNegocio.consultarRetirosCuenta(cuenta);
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
		
	public void accion_retirar() throws Exception {
		try{	
			Retiros retiro = new Retiros();
			
			RetirosId id = new RetirosId();
			Long idRetiro = delegadoDeNegocio.getConsecutivoRetiros("SEQ_RETIROS");
			Cuentas cuen = delegadoDeNegocio.consultarCuenta(numCuenta);
			id.setRetCodigo(idRetiro);
			id.setCuentas(cuen);	
			retiro.setId(id);
			
			usuCedula = 10L;//cambiar a usuario en sesión
			Usuarios usuario = delegadoDeNegocio.consultarUsuario(usuCedula);
			retiro.setUsuarios(usuario);
			retiro.setRetValor(Double.parseDouble(txtValor.getValue().toString()));
			retiro.setRetDescripcion(txtDescripcion.getValue().toString());
			retiro.setRetFecha(new Date());
			
			delegadoDeNegocio.retiro(retiro);
			Utilidades.addInfoMessage(("El retiro se realizo correctamente"));
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

	public List<Retiros> getRetiros() {
		try{
			if(retiros == null){
				retiros = delegadoDeNegocio.consultarRetiros();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
		return retiros;
	}

	public void setRetiros(List<Retiros> retiros) {
		this.retiros = retiros;
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

	public List<Long> getNumerosCuentas() {
		return numerosCuentas;
	}

	public void setNumerosCuentas(List<Long> numerosCuentas) {
		this.numerosCuentas = numerosCuentas;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}
}