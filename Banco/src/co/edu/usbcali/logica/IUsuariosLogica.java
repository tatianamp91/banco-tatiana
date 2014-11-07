package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Usuarios;

public interface IUsuariosLogica {
	
	public void crearUsuario (Usuarios usuario) throws Exception;
	
	public void modificarUsuario (Usuarios usuario) throws Exception;
	
	public void eliminarUsuario (Usuarios usuario) throws Exception;
	
	public Usuarios consultarUsuario (Long  usuCedula) throws Exception;

	public List<Usuarios> consultarUsuarios () throws Exception;
	
	public Usuarios consultarUsuariosLogin(String usuLogin) throws Exception;

}
