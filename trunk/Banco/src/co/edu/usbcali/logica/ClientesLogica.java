package co.edu.usbcali.logica;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IClientesDAO;
import co.edu.usbcali.dao.ITiposDocumentosDAO;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.TiposDocumentos;

@Scope("singleton")
@Service("ClientesLogica")
public class ClientesLogica implements IClientesLogica {
	
	@Autowired
	private IClientesDAO clientesDAO;
	@Autowired
	private ITiposDocumentosDAO tiposDocumentosDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearCliente(Clientes cliente) throws Exception {
		try{
			if(cliente == null){
				throw new Exception("El cliente es nulo");
			}
			if(cliente.getCliId() == null){
				throw new Exception("El id del cliente no puede ser vacio");
			}
			if(cliente.getTiposDocumentos() == null){
				throw new Exception("El tipo de documento de el cliente no puede ser vacio");
			}
			TiposDocumentos tipoDocumento = tiposDocumentosDAO.consultarTipoDocumento(cliente.getTiposDocumentos().getTdocCodigo());
			if(tipoDocumento == null){
				throw new Exception("El tipo de documento no existe");
			}
			if(cliente.getCliNombre() == null || cliente.getCliNombre().trim().equals("")){
				throw new Exception("El nombre del cliente no puede ser vacio");
			}
			if(cliente.getCliDireccion() == null || cliente.getCliDireccion().trim().equals("")){
				throw new Exception("La dirección del cliente no puede ser vacia");
			}
			if(cliente.getCliTelefono() == null || cliente.getCliTelefono().trim().equals("")){
				throw new Exception("El teléfono del cliente no puede ser vacio");
			}
			if(cliente.getCliMail() == null || cliente.getCliMail().trim().equals("")){
				throw new Exception("El e-mail del cliente no puede ser vacio");
			}
			if(correElectronico(cliente.getCliMail())){
				throw new Exception("El email debe tener el formato xxx@xxx.xxx");
			}
			Clientes cli = clientesDAO.consultarCliente(cliente.getCliId());
			if(cli != null){
				throw new Exception("El cliente ya existe");
			}
			
			clientesDAO.crearCliente(cliente);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarCliente(Clientes cliente) throws Exception {
		try{
			if(cliente == null){
				throw new Exception("El cliente es nulo");
			}
			if(cliente.getCliId() == null){
				throw new Exception("El id del cliente no puede ser vacio");
			}
			if(cliente.getTiposDocumentos() == null){
				throw new Exception("El tipo de documento de el cliente no puede ser vacio");
			}
			TiposDocumentos tipoDocumento = tiposDocumentosDAO.consultarTipoDocumento(cliente.getTiposDocumentos().getTdocCodigo());
			if(tipoDocumento == null){
				throw new Exception("El tipo de documento no existe");
			}
			if(cliente.getCliNombre() == null || cliente.getCliNombre().trim().equals("")){
				throw new Exception("El nombre del cliente no puede ser vacio");
			}
			if(cliente.getCliDireccion() == null || cliente.getCliDireccion().trim().equals("")){
				throw new Exception("La dirección del cliente no puede ser vacia");
			}
			if(cliente.getCliTelefono() == null || cliente.getCliTelefono().trim().equals("")){
				throw new Exception("El teléfono del cliente no puede ser vacio");
			}
			if(cliente.getCliMail() == null || cliente.getCliMail().trim().equals("")){
				throw new Exception("El e-mail del cliente no puede ser vacio");
			}
			if(correElectronico(cliente.getCliMail())){
				throw new Exception("El email debe tener el formato xxx@xxx.xxx");
			}
			Clientes entidad = clientesDAO.consultarCliente(cliente.getCliId());
			if(entidad == null){
				throw new Exception("El cliente no existe");
			}
			
			entidad.setTiposDocumentos(tipoDocumento);
			entidad.setCliNombre(cliente.getCliNombre());
			entidad.setCliDireccion(cliente.getCliDireccion());
			entidad.setCliTelefono(cliente.getCliTelefono()); 
			entidad.setCliMail(cliente.getCliMail());
			
			clientesDAO.modificarCliente(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarCliente(Clientes cliente) throws Exception {
		try{
			if(cliente == null){
				throw new Exception("El cliente es nulo");
			}
			if(cliente.getCliId() == null){
				throw new Exception("El id del cliente no puede ser vacio");
			}
			List<Clientes> clientes = clientesDAO.consultarClientesCuentas(cliente.getCliId());
			if(clientes.size() > 0){
				throw new Exception("El cliente tiene cuentas asociadas, no se puede eliminar");
			}
			
			Clientes entidad = clientesDAO.consultarCliente(cliente.getCliId());
			if(entidad == null){
				throw new Exception("El cliente no existe");
			}
			
			clientesDAO.eliminarCliente(entidad);
		}catch(Exception e){
			throw new Exception (e);
		}		
	}

	@Override
	@Transactional(readOnly = true)
	public Clientes consultarCliente(Long cliId) throws Exception {
		try{
			if(cliId == null){
				throw new Exception("El id del cliente no puede ser vacio");
			}
			return clientesDAO.consultarCliente(cliId);
		}catch(Exception e){
			throw new Exception (e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Clientes> consultarClientes() throws Exception {
		try{
			return clientesDAO.consultarClientes();
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Clientes consultarClientesCuenta(Long numCuenta, Long idCliente, String clave) throws Exception  {
		try{
			if(numCuenta == null){
				throw new Exception("El número de la cuenta no puede ser vacio");
			}
			if(clave == null || clave.trim().equals("")){
				throw new Exception("La clave de la cuenta no puede ser vacio");
			}
			if(idCliente == null){
				throw new Exception("El id del cliente no puede ser vacio");
			}
			return clientesDAO.consultarClientesCuenta(numCuenta, idCliente, clave);
		}catch(Exception e){
			throw new Exception (e);
		}
	}
	
	public static boolean correElectronico(String entrada) {

		Pattern pat = null;
		Matcher mat = null;
		pat = Pattern
				.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
		mat = pat.matcher(entrada);

		if (mat.find()) {
			return false;
		} else {
			return true;
		}
	}

}