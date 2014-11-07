package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.TiposUsuarios;

public interface ITiposUsuariosDAO {
	
	public void crearTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public void modificarTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public void eliminarTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public TiposUsuarios consultarTipoUsuario (Long  tusuCodigo) throws Exception;

	public List<TiposUsuarios> consultarTiposUsuarios () throws Exception;
	
	public List<TiposUsuarios> consultarTiposUsuariosUsuarios(Long tusuCodigo) throws Exception;
}
