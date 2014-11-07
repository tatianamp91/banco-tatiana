package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ITiposUsuariosDAO;
import co.edu.usbcali.dao.IUsuariosDAO;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Service("UsuariosLogica")
public class UsuariosLogica implements IUsuariosLogica{
	
	@Autowired
	private IUsuariosDAO usuariosDAO;
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearUsuario(Usuarios usuario) throws Exception {
		try{
			if(usuario == null){
				throw new Exception ("El usuario es nulo");
			}
			if(usuario.getUsuCedula() == null){
				throw new Exception ("La cedula del usuario no puede ser vacio");
			}
			if(usuario.getTiposUsuarios() == null){
				throw new Exception ("El tipo de usuario no puede ser vacio");
			}
			TiposUsuarios tipoUsuario = tiposUsuariosDAO.consultarTipoUsuario(usuario.getTiposUsuarios().getTusuCodigo());
			if(tipoUsuario == null){
				throw new Exception ("El tipo de usuario no existe");
			}
			if(usuario.getUsuNombre() == null || usuario.getUsuNombre().trim().equals("")){
				throw new Exception ("El nombre del usuario no puede ser vacio");
			}
			if(usuario.getUsuLogin() == null || usuario.getUsuLogin().trim().equals("")){
				throw new Exception ("El login del usuario no puede ser vacio");
			}
			if(usuario.getUsuClave() == null || usuario.getUsuClave().trim().equals("")){
				throw new Exception ("La clave del usuario no puede ser vacia");
			}
			Usuarios usu = consultarUsuariosLogin(usuario.getUsuLogin());
			if(usu != null){
				throw new Exception ("Ya existe usuario con el mismo login");
			}			
			Usuarios entidad = usuariosDAO.consultarUsuario(usuario.getUsuCedula());
			if(entidad != null){
				throw new Exception ("El usuario ya existe");
			}
			
			usuariosDAO.crearUsuario(usuario);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarUsuario(Usuarios usuario) throws Exception {
		try{
			if(usuario == null){
				throw new Exception ("El usuario es nulo");
			}
			if(usuario.getUsuCedula() == null){
				throw new Exception ("La cedula del usuario no puede ser vacio");
			}
			if(usuario.getTiposUsuarios() == null){
				throw new Exception ("El tipo de usuario no puede ser vacio");
			}
			TiposUsuarios tipoUsuario = tiposUsuariosDAO.consultarTipoUsuario(usuario.getTiposUsuarios().getTusuCodigo());
			if(tipoUsuario == null){
				throw new Exception ("El tipo de usuario no existe");
			}
			if(usuario.getUsuNombre() == null || usuario.getUsuNombre().trim().equals("")){
				throw new Exception ("El nombre del usuario no puede ser vacio");
			}
			if(usuario.getUsuLogin() == null || usuario.getUsuLogin().trim().equals("")){
				throw new Exception ("El login del usuario no puede ser vacio");
			}
			if(usuario.getUsuClave() == null || usuario.getUsuClave().trim().equals("")){
				throw new Exception ("La clave del usuario no puede ser vacia");
			}
			Usuarios usu = consultarUsuariosLogin(usuario.getUsuLogin());
			if(usu != null){
				throw new Exception ("Ya existe usuario con el mismo login");
			}
			Usuarios entidad = usuariosDAO.consultarUsuario(usuario.getUsuCedula());
			if(entidad == null){
				throw new Exception ("El usuario no existe");
			}
			
			entidad.setTiposUsuarios(tipoUsuario);
			entidad.setUsuNombre(usuario.getUsuNombre());
			entidad.setUsuLogin(usuario.getUsuLogin());
			entidad.setUsuClave(usuario.getUsuClave());
			
			usuariosDAO.modificarUsuario(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarUsuario(Usuarios usuario) throws Exception {
		try{
			if(usuario == null){
				throw new Exception ("El usuario es nulo");
			}
			if(usuario.getUsuCedula() == null){
				throw new Exception ("La cedula del usuario no puede ser vacio");
			}
			List<Usuarios> usuarios = usuariosDAO.consultarUsuariosRetiros(usuario.getUsuCedula());
			if(usuarios.size() > 0){
				throw new Exception ("El usuario tiene retiros asociados, no se puede eliminar");
			}
			List<Usuarios> usu = usuariosDAO.consultarUsuariosConsignaciones(usuario.getUsuCedula());
			if(usu.size() > 0){
				throw new Exception ("El usuario tiene consignaciones asociados, no se puede eliminar");
			}
			Usuarios entidad = usuariosDAO.consultarUsuario(usuario.getUsuCedula());
			if(entidad == null){
				throw new Exception ("El usuario no existe");
			}
			
			usuariosDAO.eliminarUsuario(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuarios consultarUsuario(Long usuCedula) throws Exception {
		try{
			if(usuCedula == null){
				throw new Exception ("La cedula del usuario no puede ser vacio");
			}
			return usuariosDAO.consultarUsuario(usuCedula);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuarios> consultarUsuarios() throws Exception {
		try{
			return usuariosDAO.consultarUsuarios();
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuarios consultarUsuariosLogin(String usuLogin) throws Exception {
		try{
			if(usuLogin == null){
				throw new Exception ("El login del usuario no puede ser vacio");
			}
			return usuariosDAO.consultarUsuariosLogin(usuLogin);
		}catch(Exception e){
			throw new Exception (e);
		}
	}
}