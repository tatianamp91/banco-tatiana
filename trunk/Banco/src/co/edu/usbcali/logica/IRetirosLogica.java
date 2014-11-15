package co.edu.usbcali.logica;

import java.util.List;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;

public interface IRetirosLogica {
	
	public Retiros consultarRetiro (RetirosId  id) throws Exception;

	public List<Retiros> consultarRetiros () throws Exception;
	
	public List<Retiros> consultarRetirosCuenta(Cuentas cuenta) throws Exception;
	
	public Long getConsecutivoRetiros(String sqlName) throws Exception ;

}
