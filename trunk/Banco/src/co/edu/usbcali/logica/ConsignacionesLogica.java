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