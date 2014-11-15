package co.edu.usbcali.logica;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import co.edu.usbcali.dao.ICuentasDAO;
import co.edu.usbcali.dao.IRetirosDAO;
import co.edu.usbcali.dao.IUsuariosDAO;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Service("RetirosLogica")
public class RetirosLogica implements IRetirosLogica{
	
	@Autowired
	private IRetirosDAO retirosDAO;
	@Autowired
	private IUsuariosDAO usuariosDAO;
	@Autowired
	private ICuentasDAO cuentasDAO;
	@Autowired
	private IConsignacionesLogica consignacionesLogica;

	@Override
	@Transactional(readOnly = true)
	public Retiros consultarRetiro(RetirosId id) throws Exception {
		try{
			if(id == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(id.getRetCodigo() == null){
				throw new Exception("El codigo del retiro no puede ser vacio");
			}
			if(id.getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(id.getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			return retirosDAO.consultarRetiro(id);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Retiros> consultarRetiros() throws Exception {
		try{
			return retirosDAO.consultarRetiros();
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Retiros> consultarRetirosCuenta(Cuentas cuenta) throws Exception {
		try{
			return retirosDAO.consultarRetirosCuenta(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Long getConsecutivoRetiros(String sqlName) throws Exception {
		try{
			return retirosDAO.getConsecutivoRetiros(sqlName);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
}