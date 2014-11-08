package co.edu.usbcali.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.logica.ICuentasLogica;
import co.edu.usbcali.logica.IRetirosLogica;
import co.edu.usbcali.logica.IUsuariosLogica;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;
import co.edu.usbcali.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class RetirosLogicaTest {
	
	@Autowired
	private IRetirosLogica retirosLogica;
	@Autowired
	private ICuentasLogica cuentasLogica;
	@Autowired
	private IUsuariosLogica usuariosLogica;
	
	private static Logger logger = LoggerFactory.getLogger(ClientesLogicaTest.class);
	
	@Test
	public void crearRetiro () throws Exception {
		try{
			Retiros retiro = new Retiros();
			
			Long conCodigo = 1L;
			Cuentas cuenta = cuentasLogica.consultarCuenta(400853050085L);
			
			RetirosId retiroId = new RetirosId();
			
			retiroId.setRetCodigo(conCodigo);
			retiroId.setCuentas(cuenta);
			retiro.setId(retiroId);
			
			Usuarios usuario = usuariosLogica.consultarUsuario(10L);			
			retiro.setUsuarios(usuario);
			retiro.setRetDescripcion("Prueba");
			retiro.setRetValor(120000D);
			retiro.setRetFecha(new Date());
			
			retirosLogica.crearRetiro(retiro);
			logger.info("El retiro se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void modificarRetiro () throws Exception { 
		try{
			Long conCodigo = 1L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			
			RetirosId retiroId = new RetirosId();
			
			retiroId.setRetCodigo(conCodigo);
			retiroId.setCuentas(cuenta);
			
			Retiros retiro = retirosLogica.consultarRetiro(retiroId);
			if(retiro != null){	
				Usuarios usuario = usuariosLogica.consultarUsuario(10L);			
				retiro.setUsuarios(usuario);
				retiro.setRetDescripcion("Prueba");
				retiro.setRetValor(80D);
				retiro.setRetFecha(new Date());
				
				retirosLogica.modificarRetiro(retiro);
				logger.info("El retiro se modificó correctamente");
			}else{
				logger.info("No existe el retiro");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarRetiro () throws Exception {
		try{
			Long conCodigo = 1L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			
			RetirosId retiroId = new RetirosId();
			
			retiroId.setRetCodigo(conCodigo);
			retiroId.setCuentas(cuenta);
			
			Retiros retiro = retirosLogica.consultarRetiro(retiroId);
			if(retiro != null){	
				retirosLogica.eliminarRetiro(retiro);
				logger.info("El retiro se eliminó correctamente");
			}else{
				logger.info("No existe el retiro");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void transladoEntreCuentas()
		throws Exception{
		
		Double valorTranslado = 1000D;
		Cuentas cuentaOrigen = cuentasLogica.consultarCuenta("4008-5305-0010");
		Cuentas cuentaDestino = cuentasLogica.consultarCuenta("4008-5305-0015");
		
		retirosLogica.transladoEntreCuentas(valorTranslado, cuentaOrigen, cuentaDestino);
		logger.info("El translado se realizó correctamente");
	}
	
	@Test
	public void consultarRetiro () throws Exception {
		try{
			Long conCodigo = 2L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0015");
			
			RetirosId retiroId = new RetirosId();
			
			retiroId.setRetCodigo(conCodigo);
			retiroId.setCuentas(cuenta);
			
			Retiros retiro = retirosLogica.consultarRetiro(retiroId);
			if(retiro != null){
				logger.info("Usuario: "+retiro.getUsuarios().getUsuCedula());
				logger.info("Valor: "+retiro.getRetValor());
				logger.info("Fecha: "+retiro.getRetFecha());
				logger.info("Descripción: "+retiro.getRetDescripcion());
			}else {
				logger.info("No existe el retiro");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarRetiros () throws Exception {
		try{
			List<Retiros> retiros = retirosLogica.consultarRetiros();
			if(retiros != null){
				for (Retiros retiro : retiros) {
					logger.info("Usuario: "+retiro.getUsuarios().getUsuCedula());
					logger.info("Valor: "+retiro.getRetValor());
					logger.info("Fecha: "+retiro.getRetFecha());
					logger.info("Descripción: "+retiro.getRetDescripcion());
					logger.info("-------------------------------------");
				}
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}