package co.edu.usbcali.logica;

import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.Usuarios;

public interface ITransaccionesLogica {
	
	public void consignacion(Consignaciones consignacion) throws Exception;
	
	public void retiro (Retiros retiro) throws Exception;
	
	public void translado (Usuarios usuario, Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino) throws Exception;

}