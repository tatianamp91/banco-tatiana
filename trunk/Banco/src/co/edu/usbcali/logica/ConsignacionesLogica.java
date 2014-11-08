package co.edu.usbcali.logica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsignacionesDAO;
import co.edu.usbcali.dao.ICuentasDAO;
import co.edu.usbcali.dao.IUsuariosDAO;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Service("ConsignacionesLogica")
public class ConsignacionesLogica implements IConsignacionesLogica {
	
	@Autowired
	private IConsignacionesDAO consignacionesDAO;
	@Autowired
	private IUsuariosDAO usuariosDAO;
	@Autowired
	private ICuentasDAO cuentasDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearConsignacion(Consignaciones consignacion) throws Exception {
		try{
			if(consignacion == null){
				throw new Exception("la consignacion es nula");
			}
			if(consignacion.getId() == null){
				throw new Exception("El id de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getConCodigo() == null){
				throw new Exception("El codigo de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getCuentas() == null){
				throw new Exception("La cuenta de la consignación no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(consignacion.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(consignacion.getUsuarios() == null){
				throw new Exception("El usuario de la consignación no puede ser vacio");
			}
			Usuarios usuario = usuariosDAO.consultarUsuario(consignacion.getUsuarios().getUsuCedula());
			if(usuario == null){
				throw new Exception("El usuario no existe");
			}
			if(consignacion.getConDescripcion() == null || consignacion.getConDescripcion().trim().equals("")){
				throw new Exception("La descripción de la consignación no puede ser vacia");
			}
			if(consignacion.getConValor() == null){
				throw new Exception("El valor de la consignación no puede ser vacio");
			}
			if(consignacion.getConValor() <= 0){
				throw new Exception("El valor de la consignación debe ser mayor a 0");
			}
			if(consignacion.getConFecha() == null){
				throw new Exception("La fecha de la consignación no puede ser vacia");
			}
			if(consignacion.getId().getCuentas().getCueActiva().trim().equals("N") && consignacion.getConValor() < 100000D){
					throw new Exception("La consignación debe ser mayor a $100.000, por apertura de cuenta");
			}
			Consignaciones entidad = consignacionesDAO.consultarConsignacion(consignacion.getId());
			if(entidad != null){
				throw new Exception("La consignación ya existe");
			}
			
			consignacionesDAO.crearConsignacion(consignacion);
			if(consignacion.getId().getCuentas().getCueActiva().trim().equals("N")){
				cuenta.setCueActiva("S");
				cuentasDAO.modificarCuenta(cuenta);
			}
			cambiarSaldo(consignacion);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cambiarSaldo(Consignaciones consignacion) throws Exception {
		
		Cuentas cuenta = cuentasDAO.consultarCuenta(consignacion.getId().getCuentas().getCueNumero());
		if(cuenta != null){
			Double saldoInicial = cuenta.getCueSaldo();
			Double saldoConsignar = consignacion.getConValor();
			Double saldoFinal = saldoInicial + saldoConsignar;
			
			cuenta.setCueSaldo(saldoFinal);
			
			cuentasDAO.modificarCuenta(cuenta);
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarConsignacion(Consignaciones consignacion) throws Exception {
		try{
			if(consignacion == null){
				throw new Exception("la consignacion es nula");
			}
			if(consignacion.getId() == null){
				throw new Exception("El id de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getConCodigo() == null){
				throw new Exception("El codigo de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getCuentas() == null){
				throw new Exception("La cuenta de la consignación no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(consignacion.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(consignacion.getUsuarios() == null){
				throw new Exception("El usuario de la consignación no puede ser vacio");
			}
			Usuarios usuario = usuariosDAO.consultarUsuario(consignacion.getUsuarios().getUsuCedula());
			if(usuario == null){
				throw new Exception("El usuario no existe");
			}
			if(consignacion.getConDescripcion() == null || consignacion.getConDescripcion().trim().equals("")){
				throw new Exception("La descripción de la consignación no puede ser vacia");
			}
			if(consignacion.getConValor() == null){
				throw new Exception("El valor de la consignación no puede ser vacio");
			}
			if(consignacion.getConValor() <= 0){
				throw new Exception("El valor de la consignación debe ser mayor a 0");
			}
			if(consignacion.getConFecha() == null){
				throw new Exception("La fecha de la consignación no puede ser vacia");
			}
			Consignaciones entidad = consignacionesDAO.consultarConsignacion(consignacion.getId());
			if(entidad == null){
				throw new Exception("La consignación no existe");
			}
					
			entidad.setUsuarios(usuario);
			entidad.setConDescripcion(consignacion.getConDescripcion());
			entidad.setConValor(consignacion.getConValor());
			entidad.setConFecha(consignacion.getConFecha());
			
			consignacionesDAO.modificarConsignacion(entidad);
			cambiarSaldo(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarConsignacion(Consignaciones consignacion) throws Exception {
		try{
			if(consignacion == null){
				throw new Exception("la consignacion es nula");
			}
			if(consignacion.getId() == null){
				throw new Exception("El id de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getConCodigo() == null){
				throw new Exception("El codigo de la consignación no puede ser vacio");
			}
			if(consignacion.getId().getCuentas() == null){
				throw new Exception("La cuenta de la consignación no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(consignacion.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			Consignaciones entidad = consignacionesDAO.consultarConsignacion(consignacion.getId());
			if(entidad == null){
				throw new Exception("La consignación no existe");
			}
			
			consignacionesDAO.eliminarConsignacion(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}				
	}

	@Override
	@Transactional(readOnly = true)
	public Consignaciones consultarConsignacion(ConsignacionesId id) throws Exception {
		try{
			if(id == null){
				throw new Exception("El id de la consignación no puede ser vacio");
			}
			if(id.getConCodigo() == null){
				throw new Exception("El codigo de la consignación no puede ser vacio");
			}
			if(id.getCuentas() == null){
				throw new Exception("La cuenta de la consignación no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(id.getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			return consignacionesDAO.consultarConsignacion(id);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Consignaciones> consultarConsignaciones() throws Exception {
		try{
			return consignacionesDAO.consultarConsignaciones();
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Consignaciones> consultarConsignacionesCuenta(Cuentas cuenta) throws Exception {
		try{
			return consignacionesDAO.consultarConsignacionesCuenta(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public  Long getConsecutivoConsignaciones(String sqlName) throws Exception {
		try{
			return consignacionesDAO.getConsecutivoConsignaciones(sqlName);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
}