package co.edu.usbcali.dao;

import java.util.List;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;

public interface IRetirosDAO {
	
	public void crearRetiro (Retiros retiro) throws Exception;
	
	public void modificarRetiro (Retiros retiro) throws Exception;
	
	public void eliminarRetiro (Retiros retiro) throws Exception;
	
	public Retiros consultarRetiro (RetirosId  id) throws Exception;

	public List<Retiros> consultarRetiros () throws Exception;
	
	public List<Retiros> consultarRetirosCuenta(Cuentas cuenta) throws Exception;
	
	public Long getConsecutivoRetiros(String sqlName) ;

}
