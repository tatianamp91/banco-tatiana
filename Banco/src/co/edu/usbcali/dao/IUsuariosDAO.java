package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Usuarios;

public interface IUsuariosDAO {
	
	public void crearUsuario (Usuarios usuario) throws Exception;
	
	public void modificarUsuario (Usuarios usuario) throws Exception;
	
	public void eliminarUsuario (Usuarios usuario) throws Exception;
	
	public Usuarios consultarUsuario (Long  usuCedula) throws Exception;

	public List<Usuarios> consultarUsuarios () throws Exception;
	
	public List<Usuarios> consultarUsuariosRetiros(Long usuCedula) throws Exception;
	
	public List<Usuarios> consultarUsuariosConsignaciones(Long usuCedula) throws Exception;
	
	public Usuarios consultarUsuariosLogin(Usuarios usuario) throws Exception;
}
