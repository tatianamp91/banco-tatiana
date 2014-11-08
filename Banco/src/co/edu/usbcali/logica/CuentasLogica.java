package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IClientesDAO;
import co.edu.usbcali.dao.ICuentasDAO;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;

@Scope("singleton")
@Service("CuentasLogica")
public class CuentasLogica implements ICuentasLogica {
	
	@Autowired
	private ICuentasDAO cuentasDAO;
	@Autowired
	private IClientesDAO clientesDAO;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearCuenta(Cuentas cuenta) throws Exception {
		try{
			if(cuenta == null){
				throw new Exception("La cuenta es nula");
			}
			/*
			if(cuenta.getCueNumero() == null || cuenta.getCueNumero().trim().equals("")){
				throw new Exception("El numero de la cuenta no puede ser vacio");
			}*/
			if(cuenta.getClientes() == null){
				throw new Exception("El cliente de la cuenta no puede ser vacio");
			}
			Clientes cliente = clientesDAO.consultarCliente(cuenta.getClientes().getCliId());
			if(cliente == null){
				throw new Exception("El cliente no existe");
			}
			/*
			if(cuenta.getCueSaldo() == null){
				throw new Exception("El saldo de la cuenta no puede ser vacio");
			}
			if(cuenta.getCueSaldo() < 0){
				throw new Exception("El saldo de la cuenta no puede ser menor a 0");
			}
			if(cuenta.getCueActiva() == null || cuenta.getCueActiva().trim().equals("")){
				throw new Exception("El estado de la cuenta no puede ser vacio");
			}
			if(cuenta.getCueClave() == null || cuenta.getCueClave().trim().equals("")){
				throw new Exception("La clave de la cuenta no puede ser vacia");
			}

			Cuentas entidad = cuentasDAO.consultarCuenta(cuenta.getCueNumero());
			if(entidad != null){
				throw new Exception("La cuenta ya existe");
			}*/
			
			cuenta.setCueSaldo(0D);
			cuenta.setCueActiva("N");
			cuenta.setCueClave("1234");
			
			cuentasDAO.crearCuenta(cuenta);			
			generarClave(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void generarClave(Cuentas cuenta) throws Exception {
		try{
			String cliId = (cuenta.getClientes().getCliId()).toString();
			String clave = cliId.substring((cliId.length()-4), (cliId.length()));
			
			clave = clave + cuenta.getCueNumero();
			cuenta.setCueClave(clave);
			
			cuentasDAO.modificarCuenta(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarCuenta(Cuentas cuenta) throws Exception {
		try{
			if(cuenta == null){
				throw new Exception("La cuenta es nula");
			}
			if(cuenta.getCueNumero() == null){
				throw new Exception("El numero de la cuenta no puede ser vacio");
			}
			if(cuenta.getClientes() == null){
				throw new Exception("El cliente de la cuenta no puede ser vacio");
			}
			Clientes cliente = clientesDAO.consultarCliente(cuenta.getClientes().getCliId());
			if(cliente == null){
				throw new Exception("El cliente no existe");
			}
			if(cuenta.getCueSaldo() == null){
				throw new Exception("El saldo de la cuenta no puede ser vacio");
			}
			if(cuenta.getCueSaldo() < 0){
				throw new Exception("El saldo de la cuenta no puede ser menor a 0");
			}
			if(cuenta.getCueActiva() == null || cuenta.getCueActiva().trim().equals("")){
				throw new Exception("El estado de la cuenta no puede ser vacio");
			}
			if(cuenta.getCueClave() == null || cuenta.getCueClave().trim().equals("")){
				throw new Exception("La clave de la cuenta no puede ser vacia");
			}
			Cuentas entidad = cuentasDAO.consultarCuenta(cuenta.getCueNumero());
			if(entidad == null){
				throw new Exception("La cuenta no existe");
			}
			
			entidad.setClientes(cliente);
			entidad.setCueSaldo(cuenta.getCueSaldo());
			entidad.setCueActiva(cuenta.getCueActiva());
			entidad.setCueClave(cuenta.getCueClave());
			
			cuentasDAO.modificarCuenta(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarCuenta(Cuentas cuenta) throws Exception {
		try{
			if(cuenta == null){
				throw new Exception("La cuenta es nula");
			}
			if(cuenta.getCueNumero() == null){
				throw new Exception("El numero de la cuenta no puede ser vacio");
			}
			List<Cuentas> cuentas = cuentasDAO.consultarCuentasRetiros(cuenta.getCueNumero());
			if(cuentas.size() > 0){
				throw new Exception("La cuenta tiene retiros asociados no puede ser eliminada");
			}
			List<Cuentas> cue = cuentasDAO.consultarCuentasConsignaciones(cuenta.getCueNumero());
			if(cue.size() > 0){
				throw new Exception("La cuenta tiene consignaciones asociadas no puede ser eliminada");
			}
			Cuentas entidad = cuentasDAO.consultarCuenta(cuenta.getCueNumero());
			if(entidad == null){
				throw new Exception("La cuenta no existe");
			}
			
			cuentasDAO.eliminarCuenta(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Cuentas consultarCuenta(Long cueNumero) throws Exception {
		try{
			if(cueNumero == null){
				throw new Exception("El numero de la cuenta no puede ser vacio");
			}
			return cuentasDAO.consultarCuenta(cueNumero);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cuentas> consultarCuentas() throws Exception {
		try{
			return cuentasDAO.consultarCuentas();
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cuentas> consultarCuentasCliente(Clientes cliente) throws Exception {
		try{
			return cuentasDAO.consultarCuentasCliente(cliente);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
}