package co.edu.usbcali.delegadoDeNegocio;

import java.util.List;

import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.TiposDocumentos;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.modelo.Usuarios;

public interface IDelegadoDeNegocio {
	
	public void crearCliente (Clientes cliente) throws Exception;
	
	public void modificarCliente (Clientes cliente) throws Exception;
	
	public void eliminarCliente (Clientes cliente) throws Exception;
	
	public Clientes consultarCliente (Long  cliId) throws Exception;

	public List<Clientes> consultarClientes () throws Exception;
	
	public Clientes consultarClientesCuenta(Long numCuenta, Long idCliente, String clave) throws Exception;
	
	public void crearConsignacion (Consignaciones consignacion) throws Exception;
	
	public void cambiarSaldo(Consignaciones consignacion) throws Exception;
	
	public void modificarConsignacion (Consignaciones consignacion) throws Exception;
	
	public void eliminarConsignacion (Consignaciones consignacion) throws Exception;
	
	public Consignaciones consultarConsignacion (ConsignacionesId  id) throws Exception;

	public List<Consignaciones> consultarConsignaciones () throws Exception;
	
	public void crearCuenta (Cuentas cuenta) throws Exception;
	
	public void generarClave(Cuentas cuenta) throws Exception;
	
	public void modificarCuenta (Cuentas cuenta) throws Exception;
	
	public void eliminarCuenta (Cuentas cuenta) throws Exception;
	
	public Cuentas consultarCuenta (Long  cueNumero) throws Exception;

	public List<Cuentas> consultarCuentas () throws Exception;
	
	public List<Cuentas> consultarCuentasCliente(Clientes cliente) throws Exception;
	
	public void crearRetiro (Retiros retiro) throws Exception;
	
	public void cambiarSaldo(Retiros retiro) throws Exception;
	
	public void modificarRetiro (Retiros retiro) throws Exception;
	
	public void eliminarRetiro (Retiros retiro) throws Exception;
	
	public void transladoEntreCuentas(Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino)
		throws Exception;
	
	public Retiros consultarRetiro (RetirosId  id) throws Exception;

	public List<Retiros> consultarRetiros () throws Exception;
	
	public void crearTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public void modificarTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public void eliminarTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public TiposDocumentos consultarTipoDocumento (Long  tdocCodigo) throws Exception;

	public List<TiposDocumentos> consultarTiposDocumentos () throws Exception;
	
	public void crearTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public void modificarTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public void eliminarTipoUsuario (TiposUsuarios tipoUsuario) throws Exception;
	
	public TiposUsuarios consultarTipoUsuario (Long  tusuCodigo) throws Exception;

	public List<TiposUsuarios> consultarTiposUsuarios () throws Exception;
	
	public void crearUsuario (Usuarios usuario) throws Exception;
	
	public void modificarUsuario (Usuarios usuario) throws Exception;
	
	public void eliminarUsuario (Usuarios usuario) throws Exception;
	
	public Usuarios consultarUsuario (Long  usuCedula) throws Exception;

	public List<Usuarios> consultarUsuarios () throws Exception;

}