package co.edu.usbcali.vista;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.utilidades.Utilidades;


@ManagedBean
@SessionScoped
public class CuentasVista {
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuentas> cuentas;
	private Long numCuenta;
	private Cuentas cuenta;
	private Clientes cliente;
	private InputText txtIdCliente;
	private InputText txtTipoDocumento;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtEmail;
	private CommandButton btnCrear;
	private CommandButton btnLimpiar;
	
	
	public void consultarTxtId() throws Exception{
		try{	
			Long idCliente = Long.parseLong(txtIdCliente.getValue().toString());			
			cliente = delegadoDeNegocio.consultarCliente(idCliente);
			if(cliente != null){
				txtTipoDocumento.setValue(cliente.getTiposDocumentos().getTdocNombre());
				txtNombre.setValue(cliente.getCliNombre());
				txtDireccion.setValue(cliente.getCliDireccion());
				txtTelefono.setValue(cliente.getCliTelefono());
				txtEmail.setValue(cliente.getCliMail());
				cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				estado();
				btnCrear.setDisabled(false);
			}else{
				Utilidades.addInfoMessage(("El cliente no existe"));
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
	
	public void accion_limpiar(){
		txtIdCliente.setValue("");
		txtTipoDocumento.setValue("");
		txtNombre.setValue("");
		txtDireccion.setValue("");
		txtTelefono.setValue("");
		txtEmail.setValue("");
		cuentas = null;
		btnCrear.setDisabled(true);
	}
	
	public void accion_crear() throws Exception{
		try{
			Cuentas cuenta = new Cuentas();			
			cuenta.setClientes(cliente);
			
			delegadoDeNegocio.crearCuenta(cuenta);
			Utilidades.addInfoMessage(("La cuenta se creó correctamente"));
			Utilidades.addInfoMessage(("Número: "+cuenta.getCueNumero()+" Clave: "+cuenta.getCueClave()));
			accion_limpiar();		
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
	}
		
	public void accion_cambiar_estado() throws Exception{
		try{
			cuenta = delegadoDeNegocio.consultarCuenta(numCuenta);
			if(cuenta != null){	
				if(cuenta.getCueActiva().trim().equals("S")){
					cuenta.setCueActiva("N");
				}else{
					if(cuenta.getCueSaldo() > 0){
						cuenta.setCueActiva("S");
					}else{
						throw new Exception("Debe realizar una consignación");
					}
				}
				
				delegadoDeNegocio.modificarCuenta(cuenta);
				Utilidades.addInfoMessage(("Se cambio el estado de la cuenta correctamente"));
				if(cliente != null){
					cuentas = delegadoDeNegocio.consultarCuentasCliente(cliente);
				}else{
					cuentas = null;
				}				
				estado();
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
	
	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public List<Cuentas> getCuentas() {
		try{
			if(cuentas == null){
				cuentas = delegadoDeNegocio.consultarCuentas();
				estado();
			}
		}catch(Exception e){
			Utilidades.addErrorMessage(e.getMessage());
		}
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

	public InputText getTxtTipoDocumento() {
		return txtTipoDocumento;
	}

	public void setTxtTipoDocumento(InputText txtTipoDocumento) {
		this.txtTipoDocumento = txtTipoDocumento;
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

	public Cuentas getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuentas cuenta) {
		this.cuenta = cuenta;
	}

	public Long getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}	
}