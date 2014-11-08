package co.edu.usbcali.logica;

import java.util.List;

import co.edu.usbcali.modelo.Clientes;

public interface IClientesLogica {
	
	public void crearCliente (Clientes cliente) throws Exception;
	
	public void modificarCliente (Clientes cliente) throws Exception;
	
	public void eliminarCliente (Clientes cliente) throws Exception;
	
	public Clientes consultarCliente (Long  cliId) throws Exception;

	public List<Clientes> consultarClientes () throws Exception;
	
	public Clientes consultarClientesCuenta(Long numCuenta, Long idCliente, String clave) throws Exception;

}
