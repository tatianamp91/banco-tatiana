package co.edu.usbcali.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.logica.IClientesLogica;
import co.edu.usbcali.logica.ICuentasLogica;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.Cuentas;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class CuentasLogicaTest {
	
	@Autowired
	private ICuentasLogica cuentasLogica;
	@Autowired
	private IClientesLogica clientesLogica;
	
	private static Logger logger = LoggerFactory.getLogger(ClientesLogicaTest.class);
	
	@Test
	public void crearCuenta () throws Exception {
		try{
			Cuentas cuenta = new Cuentas();
			cuenta.setCueNumero(4853050085L);//Colocar automatico
			Clientes cliente = clientesLogica.consultarCliente(1151938779L);
			cuenta.setClientes(cliente);
			
			cuentasLogica.crearCuenta(cuenta);
			logger.info("La cuenta se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void modificarCuenta () throws Exception {
		try{
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			if(cuenta != null){
				Clientes cliente = clientesLogica.consultarCliente(1151938779L);
				cuenta.setClientes(cliente);
				cuenta.setCueSaldo(100D);
				cuenta.setCueActiva("S");
				cuenta.setCueClave("12345678");
				
				cuentasLogica.modificarCuenta(cuenta);
				logger.info("La cuenta se modificó correctamente");
			}else{
				logger.info("No existe la cuenta");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarClaveCuenta () throws Exception {
		try{
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			if(cuenta != null){
				cuenta.setCueClave("123456789");			
				cuentasLogica.modificarCuenta(cuenta);
				logger.info("La clave de la cuenta se modificó correctamente");
			}else{
				logger.info("No existe la cuenta");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void desactivarCuenta () throws Exception {
		try{
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			if(cuenta != null){
				cuenta.setCueActiva("N");
				cuentasLogica.modificarCuenta(cuenta);
				logger.info("La cuenta se desactivo correctamente");
			}else{
				logger.info("No existe la cuenta");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarCuenta () throws Exception {
		try{
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			if(cuenta != null){			
				cuentasLogica.eliminarCuenta(cuenta);
				logger.info("La cuenta se eliminó correctamente");
			}else{
				logger.info("No existe la cuenta");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarCuenta () throws Exception {
		try{
			Cuentas cuenta = cuentasLogica.consultarCuenta("4008-5305-0085");
			if(cuenta != null){
				logger.info("Cliente: "+cuenta.getClientes().getCliId());
				logger.info("Saldo: "+cuenta.getCueSaldo());
				logger.info("Activa: "+cuenta.getCueActiva());
				logger.info("clave: "+cuenta.getCueClave());
			}else{
				logger.info("No existe la cuenta");
			}			
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarCuentas () throws Exception{
		try{
			List<Cuentas> cuentas = cuentasLogica.consultarCuentas();
			if(cuentas != null){
				for (Cuentas cuenta : cuentas) {
					logger.info("Cliente: "+cuenta.getClientes().getCliId());
					logger.info("Saldo: "+cuenta.getCueSaldo());
					logger.info("Activa: "+cuenta.getCueActiva());
					logger.info("clave: "+cuenta.getCueClave());
					logger.info("-------------------------------------");
				}
			}else{
				logger.info("No existen cuentas");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}