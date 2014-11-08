package co.edu.usbcali.logica;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.dao.IConsignacionesDAO;
import co.edu.usbcali.dao.IRetirosDAO;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Service("TransaccionesLogica")
public class TransaccionesLogica implements ITransaccionesLogica {
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	@Autowired
	private IUsuariosLogica usuariosLogica;
	@Autowired 
	private IConsignacionesDAO consignacionesDAO;
	@Autowired
	private IRetirosDAO retirosDAO;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void consignacion(Consignaciones consignacion) throws Exception {
		try{
			if(consignacion == null){
				throw new Exception("la consignacion es nula");
			}
			if(consignacion.getId() == null){
				throw new Exception("El id de la consignación no puede ser vacio");
			}			
			if(consignacion.getId().getConCodigo() == null){
				throw new Exception("El código de la consignación no puede ser vacia");
			}
			if(consignacion.getId().getCuentas() == null){
				throw new Exception("La cuenta de la consignación no puede ser vacia");
			}
			Cuentas cuenta = cuentasLogica.consultarCuenta(consignacion.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(consignacion.getUsuarios() == null){
				throw new Exception("El usuario de la consignación no puede ser vacio");
			}
			Usuarios usuario = usuariosLogica.consultarUsuario(consignacion.getUsuarios().getUsuCedula());
			if(usuario == null){
				throw new Exception("El usuario no existe");
			}
			if(consignacion.getConDescripcion() == null || consignacion.getConDescripcion().trim().equals("")){
				throw new Exception("La descripción de la consignación no puede ser vacia");
			}
			if(consignacion.getConValor() == null){
				throw new Exception("El valor de la consignación no puede ser vacio");
			}
			if(consignacion.getConValor() <= 0){
				throw new Exception("El valor de la consignación debe ser mayor a 0");
			}
			if(consignacion.getConFecha() == null){
				throw new Exception("La fecha de la consignación no puede ser vacia");
			}
			if(consignacion.getId().getCuentas().getCueActiva().trim().equals("N") && consignacion.getConValor() < 100000D){
					throw new Exception("La consignación debe ser mayor a $100.000, por apertura de cuenta");
			}
			
			Consignaciones entidad = consignacionesDAO.consultarConsignacion(consignacion.getId());
			if(entidad != null){
				throw new Exception("La consignación ya existe");
			}
			
			consignacionesDAO.crearConsignacion(consignacion);
			if(consignacion.getId().getCuentas().getCueActiva().trim().equals("N")){
				cuenta.setCueActiva("S");
			}
			Double saldoInicial = cuenta.getCueSaldo();
			Double saldoConsignar = consignacion.getConValor();
			Double saldoFinal = saldoInicial + saldoConsignar;
			
			cuenta.setCueSaldo(saldoFinal);
			
			cuentasLogica.modificarCuenta(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void retiro (Retiros retiro) throws Exception {
		try{
			if(retiro == null){
				throw new Exception("El retiro es nulo");
			}
			if(retiro.getId() == null){
				throw new Exception("El id del retiro no puede ser vacio");
			}
			if(retiro.getId().getRetCodigo() == null){
				throw new Exception("El código del retiro no puede ser vacia");
			}
			if(retiro.getId().getCuentas() == null){
				throw new Exception("La cuenta del retiro no puede ser vacia");
			}
			Cuentas cuenta = cuentasLogica.consultarCuenta(retiro.getId().getCuentas().getCueNumero());
			if(cuenta == null){
				throw new Exception("La cuenta no existe");
			}
			if(retiro.getUsuarios() == null){
				throw new Exception("El usuario del retiro no puede ser vacio");
			}
			Usuarios usuario = usuariosLogica.consultarUsuario(retiro.getUsuarios().getUsuCedula());
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
			
			Double saldoInicial = cuenta.getCueSaldo();
			Double saldoRetirar = retiro.getRetValor();
			Double saldoFinal = saldoInicial - saldoRetirar;
			
			cuenta.setCueSaldo(saldoFinal);
			cuentasLogica.modificarCuenta(cuenta);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void translado (Usuarios usuario, Double valorTranslado, Cuentas cuentaOrigen, Cuentas cuentaDestino) throws Exception {
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
			Cuentas cuentaOri = cuentasLogica.consultarCuenta(cuentaOrigen.getCueNumero());
			if(cuentaOri == null){
				throw new Exception("La cuenta origen no existe");
			}
			if(cuentaDestino == null){
				throw new Exception("La cuenta destino es nula");
			}
			Cuentas cuentaDes = cuentasLogica.consultarCuenta(cuentaDestino.getCueNumero());
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
			idRetiro.setCuentas(cuentaOri);
			retiro.setId(idRetiro);
			retiro.setRetValor(valorTranslado);
			retiro.setRetFecha(new Date());
			retiro.setRetDescripcion("Translado");
			Usuarios usu = usuariosLogica.consultarUsuario(usuario.getUsuCedula());
			retiro.setUsuarios(usu);
			
			retiro(retiro);
			
			Consignaciones consignacion = new Consignaciones();
			ConsignacionesId idConsignacion = new ConsignacionesId();
			idConsignacion.setCuentas(cuentaDes);
			consignacion.setId(idConsignacion);
			consignacion.setConValor(valorTranslado);
			consignacion.setConFecha(new Date());
			consignacion.setConDescripcion("Translado");
			consignacion.setUsuarios(usu);
			
			consignacion(consignacion);	
			
			Retiros retir = new Retiros();
			RetirosId idRetir = new RetirosId();
			idRetiro.setCuentas(cuentaOri);
			retir.setId(idRetir);
			retir.setRetValor(1000D);
			retir.setRetFecha(new Date());
			retir.setRetDescripcion("Cobro Translado");
			retiro.setUsuarios(usu);
			
			retiro(retiro);
		}catch(Exception e){
			throw new Exception(e);
		}
	}
}