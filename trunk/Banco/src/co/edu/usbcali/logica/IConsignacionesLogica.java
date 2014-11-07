package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;

public interface IConsignacionesLogica {
	
	public void crearConsignacion (Consignaciones consignacion) throws Exception;
	
	public void cambiarSaldo(Consignaciones consignacion) throws Exception;
	
	public void modificarConsignacion (Consignaciones consignacion) throws Exception;
	
	public void eliminarConsignacion (Consignaciones consignacion) throws Exception;
	
	public Consignaciones consultarConsignacion (ConsignacionesId  id) throws Exception;

	public List<Consignaciones> consultarConsignaciones () throws Exception;

}
