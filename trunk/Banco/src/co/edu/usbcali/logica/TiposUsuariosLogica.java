package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ITiposUsuariosDAO;
import co.edu.usbcali.modelo.TiposUsuarios;

@Scope("singleton")
@Service("TiposUsuariosLogica")
public class TiposUsuariosLogica implements ITiposUsuariosLogica {
	
	@Autowired
	private ITiposUsuariosDAO tiposUsuariosDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		try{
			if(tipoUsuario == null){
				throw new Exception ("El tipo de usuario es nulo");
			}
			if(tipoUsuario.getTusuCodigo() == null){
				throw new Exception ("El codigo del tipo de usuario no puede ser vacio");
			}
			if(tipoUsuario.getTusuNombre() == null || tipoUsuario.getTusuNombre().trim().equals("") ){
				throw new Exception ("El nombre del tipo de usuario no puede ser vacio");
			}
			TiposUsuarios entidad = tiposUsuariosDAO.consultarTipoUsuario(tipoUsuario.getTusuCodigo());
			if(entidad != null){
				throw new Exception ("El tipo de usuario ya existe");
			}
			
			tiposUsuariosDAO.crearTipoUsuario(tipoUsuario);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		try{
			if(tipoUsuario == null){
				throw new Exception ("El tipo de usuario es nulo");
			}
			if(tipoUsuario.getTusuCodigo() == null){
				throw new Exception ("El codigo del tipo de usuario no puede ser vacio");
			}
			if(tipoUsuario.getTusuNombre() == null || tipoUsuario.getTusuNombre().trim().equals("") ){
				throw new Exception ("El nombre del tipo de usuario no puede ser vacio");
			}
			TiposUsuarios entidad = tiposUsuariosDAO.consultarTipoUsuario(tipoUsuario.getTusuCodigo());
			if(entidad == null){
				throw new Exception ("El tipo de usuario no existe");
			}
			
			entidad.setTusuNombre(tipoUsuario.getTusuNombre());
			
			tiposUsuariosDAO.modificarTipoUsuario(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		try{
			if(tipoUsuario == null){
				throw new Exception ("El tipo de usuario es nulo");
			}
			if(tipoUsuario.getTusuCodigo() == null){
				throw new Exception ("El codigo del tipo de usuario no puede ser vacio");
			}
			List<TiposUsuarios> tiposUsuarios = tiposUsuariosDAO.consultarTiposUsuariosUsuarios(tipoUsuario.getTusuCodigo());
			if(tiposUsuarios.size() > 0){
				throw new Exception ("El tipo de usuario tiene usuarios asociados, no se puede eliminar");
			}
			TiposUsuarios entidad = tiposUsuariosDAO.consultarTipoUsuario(tipoUsuario.getTusuCodigo());
			if(entidad == null){
				throw new Exception ("El tipo de usuario no existe");
			}
			
			tiposUsuariosDAO.eliminarTipoUsuario(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public TiposUsuarios consultarTipoUsuario(Long tusuCodigo) throws Exception {
		try{
			if(tusuCodigo == null){
				throw new Exception ("El codigo del tipo de usuario no puede ser vacio");
			}
			return tiposUsuariosDAO.consultarTipoUsuario(tusuCodigo);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<TiposUsuarios> consultarTiposUsuarios() throws Exception {
		try{
			return tiposUsuariosDAO.consultarTiposUsuarios();
		}catch(Exception e){
			throw new Exception (e);
		}
	}
}