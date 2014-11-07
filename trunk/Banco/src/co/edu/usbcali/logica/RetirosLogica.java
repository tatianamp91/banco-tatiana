package co.edu.usbcali.logica;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsignacionesDAO;
import co.edu.usbcali.dao.ICuentasDAO;
import co.edu.usbcali.dao.IRetirosDAO;
import co.edu.usbcali.dao.IUsuariosDAO;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Service("RetirosLogica")
public class RetirosLogica implements IRetirosLogica{
	
	@Autowired
	private IRetirosDAO retirosDAO;
	@Autowired
	private IUsuariosDAO usuariosDAO;
	@Autowired
	private ICuentasDAO cuentasDAO;
	@Autowired
	private IConsignacionesLogica consignacionesLogica;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void crearRetiro(Retiros retiro) throws Exception {
		try{
			if(retiro == null){
				throw new Exception("El retiro es nulo");
			}
			if(retiro.getId() == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(retiro.getId().getRetCodigo() == null){
				throw new Exception("El codigo del retiro no puede ser vacio");
			}
			if(retiro.getId().getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(retiro.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(retiro.getUsuarios() == null){
				throw new Exception("El usuario del retiro no puede ser vacio");
			}
			Usuarios usuario = usuariosDAO.consultarUsuario(retiro.getUsuarios().getUsuCedula());
			if(usuario == null){
				throw new Exception("El usuario no existe");
			}
			if(retiro.getRetValor() == null){
				throw new Exception("El valor del retiro no puede ser vacio");
			}
			if(retiro.getRetValor() < 0){
				throw new Exception("El valor del retiro no puede ser menor a 0");
			}
			if(retiro.getRetValor() > retiro.getId().getCuentas().getCueSaldo()){
				throw new Exception("El valor del retiro es mayor al saldo de la cuenta");
			}
			if(retiro.getRetFecha() == null){
				throw new Exception("La fecha del retiro no puede ser vacia");
			}
			if(retiro.getRetDescripcion() == null || retiro.getRetDescripcion().trim().equals("")){
				throw new Exception("La descripción del retiro no puede ser vacia");
			}
			if(retiro.getId().getCuentas().getCueActiva().trim().equals("N")){
				throw new Exception("La cuenta se encuentra inactiva no se pueden realizar retiros");
			}
			Retiros entidad = retirosDAO.consultarRetiro(retiro.getId());
			if(entidad != null){
				throw new Exception("El retiro ya existe");
			}
			
			retirosDAO.crearRetiro(retiro);
			cambiarSaldo(retiro);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void cambiarSaldo(Retiros retiro) throws Exception {
		
		Cuentas cuenta = cuentasDAO.consultarCuenta(retiro.getId().getCuentas().getCueNumero());
		if(cuenta != null){
			Double saldoInicial = cuenta.getCueSaldo();
			Double saldoRetirar = retiro.getRetValor();
			Double saldoFinal = saldoInicial - saldoRetirar;
			
			cuenta.setCueSaldo(saldoFinal);
			
			cuentasDAO.modificarCuenta(cuenta);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modificarRetiro(Retiros retiro) throws Exception {
		try{
			if(retiro == null){
				throw new Exception("El retiro es nulo");
			}
			if(retiro.getId() == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(retiro.getId().getRetCodigo() == null){
				throw new Exception("El codigo del retiro no puede ser vacio");
			}
			if(retiro.getId().getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(retiro.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(retiro.getUsuarios() == null){
				throw new Exception("El usuario del retiro no puede ser vacio");
			}
			Usuarios usuario = usuariosDAO.consultarUsuario(retiro.getUsuarios().getUsuCedula());
			if(usuario == null){
				throw new Exception("El usuario no existe");
			}
			if(retiro.getRetValor() == null){
				throw new Exception("El valor del retiro no puede ser vacio");
			}
			if(retiro.getRetValor() < 0){
				throw new Exception("El valor del retiro no puede ser menor a 0");
			}
			if(retiro.getRetValor() > retiro.getId().getCuentas().getCueSaldo()){
				throw new Exception("El valor del retiro es mayor al saldo de la cuenta");
			}
			if(retiro.getRetFecha() == null){
				throw new Exception("La fecha del retiro no puede ser vacia");
			}
			if(retiro.getRetDescripcion() == null || retiro.getRetDescripcion().trim().equals("")){
				throw new Exception("La descripción del retiro no puede ser vacia");
			}
			Retiros entidad = retirosDAO.consultarRetiro(retiro.getId());
			if(entidad == null){
				throw new Exception("El retiro no existe");
			}
			
			entidad.setUsuarios(usuario);
			entidad.setRetValor(retiro.getRetValor());
			entidad.setRetFecha(retiro.getRetFecha());
			entidad.setRetDescripcion(retiro.getRetDescripcion());
			
			retirosDAO.modificarRetiro(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void eliminarRetiro(Retiros retiro) throws Exception {
		try{
			if(retiro == null){
				throw new Exception("El retiro es nulo");
			}
			if(retiro.getId() == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(retiro.getId().getRetCodigo() == null){
				throw new Exception("El codigo del retiro no puede ser vacio");
			}
			if(retiro.getId().getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(retiro.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			Retiros entidad = retirosDAO.consultarRetiro(retiro.getId());
			if(entidad == null){
				throw new Exception("El retiro no existe");
			}
			
			retirosDAO.eliminarRetiro(entidad);
		}catch(Exception e){
			throw new Exception(e);
		}				
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void transladoEntreCuentas(Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino) throws Exception {
		try{
			if(valorTranslado == null){
				throw new Exception("El valor del transalado en nulo");
			}
			if(valorTranslado <= 0){
				throw new Exception("El valor del translado debe ser mayor a 0");
			}
			if(cuentaOrigen == null){
				throw new Exception("La cuenta origen es nula");
			}
			Cuentas cuentaOri = cuentasDAO.consultarCuenta(cuentaOrigen.getCueNumero());
			if(cuentaOri == null){
				throw new Exception("La cuenta origen no existe");
			}
			if(cuentaDestino == null){
				throw new Exception("La cuenta destino es nula");
			}
			Cuentas cuentaDes = cuentasDAO.consultarCuenta(cuentaDestino.getCueNumero());
			if(cuentaDes == null){
				throw new Exception("La cuenta destino no existe");
			}
			Double saldo = cuentaOrigen.getCueSaldo() + 1000;
			if(saldo < valorTranslado){
				throw new Exception("La cuenta origen no tiene saldo suficiente para el translado");
			}
			if(cuentaOrigen.getCueActiva().trim().equals("N")){
				throw new Exception("La cuenta origen esta inactiva");
			}
			if(cuentaDestino.getCueActiva().trim().equals("N")){
				throw new Exception("La cuenta destino esta inactiva");
			}
			
			Retiros retiro = new Retiros();
			RetirosId idRetiro = new RetirosId();
			idRetiro.setRetCodigo(16L);//Automatico
			idRetiro.setCuentas(cuentaOri);
			retiro.setId(idRetiro);
			retiro.setRetValor(valorTranslado);
			retiro.setRetFecha(new Date());
			retiro.setRetDescripcion("Translado");
			Usuarios usu = usuariosDAO.consultarUsuario(10L);
			retiro.setUsuarios(usu);
			
			crearRetiro(retiro);
			
			Consignaciones consignacion = new Consignaciones();
			ConsignacionesId idConsignacion = new ConsignacionesId();
			idConsignacion.setConCodigo(16L);//Automatico
			idConsignacion.setCuentas(cuentaDes);
			consignacion.setId(idConsignacion);
			consignacion.setConValor(valorTranslado);
			consignacion.setConFecha(new Date());
			consignacion.setConDescripcion("Translado");
			consignacion.setUsuarios(usu);
			
			consignacionesLogica.crearConsignacion(consignacion);	
			
			Retiros retir = new Retiros();
			RetirosId idRetir = new RetirosId();
			idRetiro.setRetCodigo(17L);// Automatico
			idRetiro.setCuentas(cuentaOri);
			retir.setId(idRetir);
			retir.setRetValor(1000D);
			retir.setRetFecha(new Date());
			retir.setRetDescripcion("Cobro Translado");
			retiro.setUsuarios(usu);
			
			crearRetiro(retiro);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Retiros consultarRetiro(RetirosId id) throws Exception {
		try{
			if(id == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(id.getRetCodigo() == null){
				throw new Exception("El codigo del retiro no puede ser vacio");
			}
			if(id.getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasDAO.consultarCuenta(id.getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			return retirosDAO.consultarRetiro(id);
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Retiros> consultarRetiros() throws Exception {
		try{
			return retirosDAO.consultarRetiros();
		}catch(Exception e){
			throw new Exception(e);
		}
	}
}