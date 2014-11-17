package co.edu.usbcali.seguridad;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.password.Password;

import co.edu.usbcali.delegadoDeNegocio.IDelegadoDeNegocio;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Usuarios;
import co.edu.usbcali.utilidades.Utilidades;
 
 
@ManagedBean
@SessionScoped
public class Autenticacion{
	
	@ManagedProperty(value = "#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	private String rol;
	private InputText txtUsuario;
	private Password txtContraseña;
	private InputText txtCliId;
	private InputText txtNumCue;
	private CommandButton btnIngresar;
     
    public String autenticacionAplicacion() throws Exception {
    	try{
    		String usuario = txtUsuario.getValue().toString();
    		String contrasena = txtContraseña.getValue().toString();
    		rol = null;
    		
	        Usuarios usuarioBD = delegadoDeNegocio.consultarUsuariosLoginClave(usuario, contrasena);	
	        if(usuarioBD != null){
				if(usuarioBD.getTiposUsuarios().getTusuCodigo() == 10){
					rol = "Cajero";
				}else if(usuarioBD.getTiposUsuarios().getTusuCodigo() == 20){
					rol = "Asesor";
				}
	        }else{
	        	if (usuario.trim().equals("admin") && contrasena.trim().equals("1234")) {
			        rol = "Admin";
		        }else{
		        	throw new Exception("Autenticación fallida");
		        }
	        }	        
	        if(!rol.trim().equals("")){
	        	HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	        	if(usuarioBD != null){
	        		httpSession.setAttribute("usuario", usuarioBD.getUsuCedula());
	        	}else{
	        		httpSession.setAttribute("usuario", usuario);
	        	}
	        }
		}catch (Exception e) {
			Utilidades.addErrorMessage((e.getMessage()));
		}
    	return rol;
    } 
    
    public String autenticacionPortal() throws Exception {
    	try{    	
	    	Long numCue = Long.parseLong(txtNumCue.getValue().toString());
	    	Long cliId = Long.parseLong(txtCliId.getValue().toString());
	    	String contrasena = txtContraseña.getValue().toString();
	    	
	    	Clientes cliente = delegadoDeNegocio.consultarClientesCuenta(numCue, cliId, contrasena);		
			if(cliente != null){
				rol = "Cliente";
				HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		        httpSession.setAttribute("cliente", cliente.getCliId());
			}else{
	        	throw new Exception("Autenticación fallida");
	        }
    	}catch (Exception e) {
			Utilidades.addErrorMessage((e.getMessage()));
		}
		return rol;
    }

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public InputText getTxtUsuario() {
		return txtUsuario;
	}
	public void setTxtUsuario(InputText txtUsuario) {
		this.txtUsuario = txtUsuario;
	}
	public Password getTxtContraseña() {
		return txtContraseña;
	}
	public void setTxtContraseña(Password txtContraseña) {
		this.txtContraseña = txtContraseña;
	}
	public InputText getTxtCliId() {
		return txtCliId;
	}
	public void setTxtCliId(InputText txtCliId) {
		this.txtCliId = txtCliId;
	}
	public InputText getTxtNumCue() {
		return txtNumCue;
	}
	public void setTxtNumCue(InputText txtNumCue) {
		this.txtNumCue = txtNumCue;
	}
	public CommandButton getBtnIngresar() {
		return btnIngresar;
	}
	public void setBtnIngresar(CommandButton btnIngresar) {
		this.btnIngresar = btnIngresar;
	}
}