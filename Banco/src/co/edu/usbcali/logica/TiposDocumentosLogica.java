package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.ITiposDocumentosDAO;
import co.edu.usbcali.modelo.TiposDocumentos;

@Scope("singleton")
@Service("TiposDocumentosLogica")
public class TiposDocumentosLogica implements ITiposDocumentosLogica{
	
	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		try{
			if(tipoDocumento == null){
				throw new Exception("El tipo de documento es nulo");
			}
			if(tipoDocumento.getTdocCodigo() == null){
				throw new Exception("El codigo del tipo de documento no puede ser vacio");
			}
			if(tipoDocumento.getTdocNombre() == null || tipoDocumento.getTdocNombre().trim().equals("")){
				throw new Exception("El nombre del tipo de documento no puede ser vacio");
			}
			TiposDocumentos entidad = tiposDocumentosDAO.consultarTipoDocumento(tipoDocumento.getTdocCodigo());
			if(entidad != null){
				throw new Exception("El tipo de documento ya existe");
			}
			
			tiposDocumentosDAO.crearTipoDocumento(tipoDocumento);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		try{
			if(tipoDocumento == null){
				throw new Exception("El tipo de documento es nulo");
			}
			if(tipoDocumento.getTdocCodigo() == null){
				throw new Exception("El codigo del tipo de documento no puede ser vacio");
			}
			if(tipoDocumento.getTdocNombre() == null || tipoDocumento.getTdocNombre().trim().equals("")){
				throw new Exception("El nombre del tipo de documento no puede ser vacio");
			}
			TiposDocumentos entidad = tiposDocumentosDAO.consultarTipoDocumento(tipoDocumento.getTdocCodigo());
			if(entidad == null){
				throw new Exception("El tipo de documento no existe");
			}
			
			entidad.setTdocNombre(tipoDocumento.getTdocNombre());
			
			tiposDocumentosDAO.modificarTipoDocumento(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}			
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		try{
			if(tipoDocumento == null){
				throw new Exception("El tipo de documento es nulo");
			}
			if(tipoDocumento.getTdocCodigo() == null){
				throw new Exception("El codigo del tipo de documento no puede ser vacio");
			}
			List<TiposDocumentos> tiposDocumentos = tiposDocumentosDAO.consultarTiposDocumentosClientes(tipoDocumento.getTdocCodigo());
			if(tiposDocumentos.size() > 0){
				throw new Exception("El tipo de documento tiene clientes asociados, no se puede eliminar");
			}
			TiposDocumentos entidad = tiposDocumentosDAO.consultarTipoDocumento(tipoDocumento.getTdocCodigo());
			if(entidad == null){
				throw new Exception("El tipo de documento no existe");
			}
			
			tiposDocumentosDAO.eliminarTipoDocumento(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public TiposDocumentos consultarTipoDocumento(Long tdocCodigo) throws Exception {
		try{
			if(tdocCodigo == null){
				throw new Exception("El codigo del tipo de documento no puede ser vacio");
			}
			return tiposDocumentosDAO.consultarTipoDocumento(tdocCodigo);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<TiposDocumentos> consultarTiposDocumentos() throws Exception {
		try{
			return tiposDocumentosDAO.consultarTiposDocumentos();
		}catch(Exception e){
			throw new Exception (e);
		}
	}
}