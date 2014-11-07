package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Cuentas;

public interface ICuentasLogica {
	
	public void crearCuenta (Cuentas cuenta) throws Exception;
	
	public void generarClave(Cuentas cuenta) throws Exception;
	
	public void modificarCuenta (Cuentas cuenta) throws Exception;
	
	public void eliminarCuenta (Cuentas cuenta) throws Exception;
	
	public Cuentas consultarCuenta (String  cueNumero) throws Exception;

	public List<Cuentas> consultarCuentas () throws Exception;

}
