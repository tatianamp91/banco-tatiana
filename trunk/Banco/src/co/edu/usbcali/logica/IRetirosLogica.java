package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;

public interface IRetirosLogica {
	
	public void crearRetiro (Retiros retiro) throws Exception;
	
	public void cambiarSaldo(Retiros retiro) throws Exception;
	
	public void modificarRetiro (Retiros retiro) throws Exception;
	
	public void eliminarRetiro (Retiros retiro) throws Exception;
	
	public void transladoEntreCuentas(Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino) throws Exception;
	
	public Retiros consultarRetiro (RetirosId  id) throws Exception;

	public List<Retiros> consultarRetiros () throws Exception;

}
