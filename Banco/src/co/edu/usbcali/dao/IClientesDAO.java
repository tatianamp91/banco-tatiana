package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.Clientes;

public interface IClientesDAO {
	
	public void crearCliente (Clientes cliente) throws Exception;
	
	public void modificarCliente (Clientes cliente) throws Exception;
	
	public void eliminarCliente (Clientes cliente) throws Exception;
	
	public Clientes consultarCliente (Long  cliId) throws Exception;

	public List<Clientes> consultarClientes () throws Exception;
	
	public List<Clientes> consultarClientesCuentas(Long cliId) throws Exception;
	
	public Clientes consultarClientesCuenta(String numCuenta, Long idCliente, String clave) throws Exception;
}
