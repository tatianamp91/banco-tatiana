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

import co.edu.usbcali.logica.IConsignacionesLogica;
import co.edu.usbcali.logica.ICuentasLogica;
import co.edu.usbcali.logica.IUsuariosLogica;
import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;
import co.edu.usbcali.modelo.Cuentas;
import co.edu.usbcali.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class ConsignacionesLogicaTest {
	
	@Autowired
	private IConsignacionesLogica consignacionesLogica;
	@Autowired
	private ICuentasLogica cuentasLogica;
	@Autowired
	private IUsuariosLogica usuariosLogica;
	
	private static Logger logger = LoggerFactory.getLogger(ClientesLogicaTest.class);
	/*
	@Test
	public void crearConsignacion () throws Exception {
		try{
			Consignaciones consignacion = new Consignaciones();
			
			Long conCodigo = 3L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			
			ConsignacionesId consignacionId = new ConsignacionesId();
			
			consignacionId.setConCodigo(conCodigo);
			consignacionId.setCuentas(cuenta);
			consignacion.setId(consignacionId);
			
			Usuarios usuario = usuariosLogica.consultarUsuario(10L);			
			consignacion.setUsuarios(usuario);
			consignacion.setConDescripcion("Prueba");
			consignacion.setConValor(100000D);
			consignacion.setConFecha(new Date());
			
			consignacionesLogica.crearConsignacion(consignacion);
			logger.info("La consignación se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarConsignacion () throws Exception {	
		try{
			Long conCodigo = 1L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			
			ConsignacionesId consignacionId = new ConsignacionesId();
			
			consignacionId.setConCodigo(conCodigo);
			consignacionId.setCuentas(cuenta);
			
			Consignaciones consignacion = consignacionesLogica.consultarConsignacion(consignacionId);
			if(consignacion != null){
				Usuarios usuario = usuariosLogica.consultarUsuario(10L);
				consignacion.setUsuarios(usuario);
				consignacion.setConDescripcion("Prueba");
				consignacion.setConValor(1000D);
				consignacion.setConFecha(new Date());			
				
				consignacionesLogica.modificarConsignacion(consignacion);
				logger.info("La consignación se modificó correctamente");
			}else{
				logger.info("No existe la consignación");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarConsignacion () throws Exception {
		try{
			Long conCodigo = 1L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			
			ConsignacionesId consignacionId = new ConsignacionesId();
			
			consignacionId.setConCodigo(conCodigo);
			consignacionId.setCuentas(cuenta);
			
			Consignaciones consignacion = consignacionesLogica.consultarConsignacion(consignacionId);
			if(consignacion != null){				
				consignacionesLogica.eliminarConsignacion(consignacion);
				logger.info("La consignación se eliminó correctamente");
			}else{
			logger.info("No existe la consignación");
		}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarConsignacion () throws Exception {
		try{
			Long conCodigo = 2L;
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0015");
			
			ConsignacionesId consignacionId = new ConsignacionesId();
			
			consignacionId.setConCodigo(conCodigo);
			consignacionId.setCuentas(cuenta);
			Consignaciones consignacion = consignacionesLogica.consultarConsignacion(consignacionId);
			if(consignacion != null){
				logger.info("Usuario :"+consignacion.getUsuarios().getUsuCedula());
				logger.info("Valor :"+consignacion.getConValor());
				logger.info("Fecha :"+consignacion.getConFecha());
				logger.info("Descripción :"+consignacion.getConDescripcion());
			}else{
				logger.info("No existe la consignación");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
	
	@Test
	public void consultarConsignaciones () throws Exception {
		try{
			List<Consignaciones> consignaciones = consignacionesLogica.consultarConsignaciones();
			if(consignaciones != null){
				for (Consignaciones consignacion : consignaciones) {
					logger.info("Usuario :"+consignacion.getUsuarios().getUsuCedula());
					logger.info("Valor :"+consignacion.getConValor());
					logger.info("Fecha :"+consignacion.getConFecha());
					logger.info("Descripción :"+consignacion.getConDescripcion());
					logger.info("------------------------------------------------");
				}
			}else{
				logger.info("No existen consignaciones");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
}