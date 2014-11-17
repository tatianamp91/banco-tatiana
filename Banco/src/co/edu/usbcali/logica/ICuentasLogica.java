package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;

public interface ICuentasLogica {
	
	public void crearCuenta (Cuentas cuenta) throws Exception;
	
	public void generarClave(Cuentas cuenta) throws Exception;
	
	public void modificarCuenta (Cuentas cuenta) throws Exception;
	
	public Cuentas consultarCuenta (Long  cueNumero) throws Exception;

	public List<Cuentas> consultarCuentas () throws Exception;
	
	public List<Cuentas> consultarCuentasCliente(Clientes cliente) throws Exception;

}
