package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;

public interface IConsignacionesLogica {
	
	public Consignaciones consultarConsignacion (ConsignacionesId  id) throws Exception;

	public List<Consignaciones> consultarConsignaciones () throws Exception;
	
	public List<Consignaciones> consultarConsignacionesCuenta(Cuentas cuenta) throws Exception;
	
	public  Long getConsecutivoConsignaciones(String sqlName) throws Exception ;

}
