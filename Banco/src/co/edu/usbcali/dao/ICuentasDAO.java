package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;

public interface ICuentasDAO {
	
	public void crearCuenta (Cuentas cuenta) throws Exception;
	
	public void modificarCuenta (Cuentas cuenta) throws Exception;
	
	public Cuentas consultarCuenta (Long  cueNumero) throws Exception;

	public List<Cuentas> consultarCuentas () throws Exception;
	
	public List<Cuentas> consultarCuentasCliente(Clientes cliente) throws Exception;
	
	public List<Cuentas> consultarCuentasRetiros(Long cueNumero) throws Exception;
	
	public List<Cuentas> consultarCuentasConsignaciones(Long cueNumero) throws Exception;

}
