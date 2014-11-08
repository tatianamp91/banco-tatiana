package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;

public interface IConsignacionesDAO {
	
	public void crearConsignacion (Consignaciones consignacion) throws Exception;
	
	public void modificarConsignacion (Consignaciones consignacion) throws Exception;
	
	public void eliminarConsignacion (Consignaciones consignacion) throws Exception;
	
	public Consignaciones consultarConsignacion (ConsignacionesId  id) throws Exception;

	public List<Consignaciones> consultarConsignaciones () throws Exception;
	
	public List<Consignaciones> consultarConsignacionesCuenta(Cuentas cuenta) throws Exception;
	
	public  Long getConsecutivoConsignaciones(String sqlName) throws Exception;

}
