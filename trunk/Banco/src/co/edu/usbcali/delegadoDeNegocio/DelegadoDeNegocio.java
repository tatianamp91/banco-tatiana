package co.edu.usbcali.delegadoDeNegocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.logica.IClientesLogica;
import co.edu.usbcali.logica.IConsignacionesLogica;
import co.edu.usbcali.logica.ICuentasLogica;
import co.edu.usbcali.logica.IRetirosLogica;
import co.edu.usbcali.logica.ITiposDocumentosLogica;
import co.edu.usbcali.logica.ITiposUsuariosLogica;
import co.edu.usbcali.logica.ITransaccionesLogica;
import co.edu.usbcali.logica.IUsuariosLogica;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.TiposDocumentos;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Component("delegadoDeNegocio")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	
	@Autowired
	private IClientesLogica clientesLogica;
	@Autowired
	private IConsignacionesLogica consignacionesLogica;
	@Autowired
	private ICuentasLogica cuentasLogica;
	@Autowired
	private IRetirosLogica retirosLogica;
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;
	@Autowired
	private ITiposUsuariosLogica tiposUsuariosLogica;
	@Autowired
	private IUsuariosLogica usuariosLogica;
	@Autowired
	private ITransaccionesLogica transaccionesLogica;

	@Override
	public void crearCliente(Clientes cliente) throws Exception {
		clientesLogica.crearCliente(cliente);
	}

	@Override
	public void modificarCliente(Clientes cliente) throws Exception {
		clientesLogica.modificarCliente(cliente);
	}

	@Override
	public void eliminarCliente(Clientes cliente) throws Exception {
		clientesLogica.eliminarCliente(cliente);
	}

	@Override
	public Clientes consultarCliente(Long cliId) throws Exception {
		return clientesLogica.consultarCliente(cliId);
	}

	@Override
	public List<Clientes> consultarClientes() throws Exception {
		return clientesLogica.consultarClientes();
	}
	
	@Override
	public Clientes consultarClientesCuenta(Long numCuenta, Long idCliente, String clave) throws Exception{
		return clientesLogica.consultarClientesCuenta(numCuenta, idCliente, clave);
	}

	@Override
	public void crearConsignacion(Consignaciones consignacion) throws Exception {
		consignacionesLogica.crearConsignacion(consignacion);
	}
	
	@Override
	public void cambiarSaldo(Consignaciones consignacion) throws Exception{
		consignacionesLogica.cambiarSaldo(consignacion);
	}

	@Override
	public void modificarConsignacion(Consignaciones consignacion) throws Exception {
		consignacionesLogica.modificarConsignacion(consignacion);
	}

	@Override
	public void eliminarConsignacion(Consignaciones consignacion) throws Exception {
		consignacionesLogica.eliminarConsignacion(consignacion);
	}

	@Override
	public Consignaciones consultarConsignacion(ConsignacionesId id) throws Exception {
		return consignacionesLogica.consultarConsignacion(id);
	}

	@Override
	public List<Consignaciones> consultarConsignaciones() throws Exception {
		return consignacionesLogica.consultarConsignaciones();
	}
	
	@Override
	public List<Consignaciones> consultarConsignacionesCuenta(Cuentas cuenta) throws Exception {
		return consignacionesLogica.consultarConsignacionesCuenta(cuenta);
	}
	
	@Override
	public  Long getConsecutivoConsignaciones(String sqlName) throws Exception {
		return consignacionesLogica.getConsecutivoConsignaciones(sqlName);
	}

	@Override
	public void crearCuenta(Cuentas cuenta) throws Exception {
		cuentasLogica.crearCuenta(cuenta);
	}
	
	@Override
	public void generarClave(Cuentas cuenta) throws Exception {
		cuentasLogica.generarClave(cuenta);
	}
	
	@Override
	public void modificarCuenta(Cuentas cuenta) throws Exception {
		cuentasLogica.modificarCuenta(cuenta);
	}
	
	
	@Override
	public void eliminarCuenta(Cuentas cuenta) throws Exception {
		cuentasLogica.eliminarCuenta(cuenta);
	}

	@Override
	public Cuentas consultarCuenta(Long cueNumero) throws Exception {
		return cuentasLogica.consultarCuenta(cueNumero);
	}

	@Override
	public List<Cuentas> consultarCuentas() throws Exception {
		return cuentasLogica.consultarCuentas();
	}
	
	@Override
	public List<Cuentas> consultarCuentasCliente(Clientes cliente) throws Exception {
		return cuentasLogica.consultarCuentasCliente(cliente);
	}

	@Override
	public void crearRetiro(Retiros retiro) throws Exception {
		retirosLogica.crearRetiro(retiro);
	}
	
	@Override
	public void cambiarSaldo(Retiros retiro) throws Exception {
		retirosLogica.cambiarSaldo(retiro);
	}

	@Override
	public void modificarRetiro(Retiros retiro) throws Exception {
		retirosLogica.modificarRetiro(retiro);
	}

	@Override
	public void eliminarRetiro(Retiros retiro) throws Exception {
		retirosLogica.eliminarRetiro(retiro);
	}
	
	@Override
	public List<Retiros> consultarRetirosCuenta(Cuentas cuenta) throws Exception {
		return retirosLogica.consultarRetirosCuenta(cuenta);
	}
	
	@Override
	public Long getConsecutivoRetiros(String sqlName) throws Exception {
		return retirosLogica.getConsecutivoRetiros(sqlName);
	}

	@Override
	public void transladoEntreCuentas(Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino)
		throws Exception {
		retirosLogica.transladoEntreCuentas(valorTranslado, cuentaOrigen, cuentaDestino);
	}
	@Override
	public Retiros consultarRetiro(RetirosId id) throws Exception {
		return retirosLogica.consultarRetiro(id);
	}

	@Override
	public List<Retiros> consultarRetiros() throws Exception {
		return retirosLogica.consultarRetiros();
	}

	@Override
	public void crearTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		tiposDocumentosLogica.crearTipoDocumento(tipoDocumento);
	}

	@Override
	public void modificarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		tiposDocumentosLogica.modificarTipoDocumento(tipoDocumento);
	}

	@Override
	public void eliminarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		tiposDocumentosLogica.eliminarTipoDocumento(tipoDocumento);
	}

	@Override
	public TiposDocumentos consultarTipoDocumento(Long tdocCodigo) throws Exception {
		return tiposDocumentosLogica.consultarTipoDocumento(tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultarTiposDocumentos() throws Exception {
		return tiposDocumentosLogica.consultarTiposDocumentos();
	}

	@Override
	public void crearTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		tiposUsuariosLogica.crearTipoUsuario(tipoUsuario);
	}

	@Override
	public void modificarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		tiposUsuariosLogica.modificarTipoUsuario(tipoUsuario);
	}

	@Override
	public void eliminarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		tiposUsuariosLogica.eliminarTipoUsuario(tipoUsuario);
	}

	@Override
	public TiposUsuarios consultarTipoUsuario(Long tusuCodigo) throws Exception {
		return tiposUsuariosLogica.consultarTipoUsuario(tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTiposUsuarios() throws Exception {
		return tiposUsuariosLogica.consultarTiposUsuarios();
	}

	@Override
	public void crearUsuario(Usuarios usuario) throws Exception {
		usuariosLogica.crearUsuario(usuario);
	}

	@Override
	public void modificarUsuario(Usuarios usuario) throws Exception {
		usuariosLogica.modificarUsuario(usuario);
	}

	@Override
	public void eliminarUsuario(Usuarios usuario) throws Exception {
		usuariosLogica.eliminarUsuario(usuario);
	}

	@Override
	public Usuarios consultarUsuario(Long usuCedula) throws Exception {
		return usuariosLogica.consultarUsuario(usuCedula);
	}

	@Override
	public List<Usuarios> consultarUsuarios() throws Exception {
		return usuariosLogica.consultarUsuarios();
	}

	@Override
	public void consignacion(Consignaciones consignacion) throws Exception {
		transaccionesLogica.consignacion(consignacion);		
	}

	@Override
	public void retiro(Retiros retiro) throws Exception {
		transaccionesLogica.retiro(retiro);		
	}

	@Override
	public void translado(Usuarios usuario, Double valorTranslado,
			Cuentas cuentaOrigen, Cuentas cuentaDestino) throws Exception {
		transaccionesLogica.translado(usuario, valorTranslado, cuentaOrigen, cuentaDestino);		
	}
}