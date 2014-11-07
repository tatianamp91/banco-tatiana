package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Cuentas;

public interface ICuentasDAO {
	
	public void crearCuenta (Cuentas cuenta) throws Exception;
	
	public void modificarCuenta (Cuentas cuenta) throws Exception;
	
	public void eliminarCuenta (Cuentas cuenta) throws Exception;
	
	public Cuentas consultarCuenta (String  cueNumero) throws Exception;

	public List<Cuentas> consultarCuentas () throws Exception;
	
	public List<Cuentas> consultarCuentasRetiros(String cueNumero) throws Exception;
	
	public List<Cuentas> consultarCuentasConsignaciones(String cueNumero) throws Exception;

}
