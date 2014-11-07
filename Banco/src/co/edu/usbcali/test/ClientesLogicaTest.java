package co.edu.usbcali.test;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import co.edu.usbcali.logica.IClientesLogica;
import co.edu.usbcali.logica.ICuentasLogica;
import co.edu.usbcali.logica.ITiposDocumentosLogica;
import co.edu.usbcali.modelo.Clientes;
import co.edu.usbcali.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class ClientesLogicaTest {
	
	@Autowired
	private IClientesLogica clientesLogica;
	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;
	@Autowired
	private ICuentasLogica cuentasLogica;
	
	private static Logger logger = LoggerFactory.getLogger(ClientesLogicaTest.class);
	
	
	@Test
	public void crearCliente () throws Exception {
		try{		
			Clientes cliente = new Clientes();
			
			cliente.setCliId(1151938779L);
			TiposDocumentos tipoDocumento = tiposDocumentosLogica.consultarTipoDocumento(10L);
			
			cliente.setTiposDocumentos(tipoDocumento);
			cliente.setCliNombre("Sindy Tatiana Moncada Pisso");
			cliente.setCliDireccion("Calle 5 # 45-12");
			cliente.setCliTelefono("3459871");
			cliente.setCliMail("tatiana@gmail.com");
			
			clientesLogica.crearCliente(cliente);
			logger.info("El cliente se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void modificarInformacionCliente () throws Exception {
		try{
			Clientes cliente = clientesLogica.consultarCliente(1151938779L);
			if(cliente != null){
				cliente.setCliDireccion("Calle 5 # 45-12");
				cliente.setCliTelefono("3459871");
				cliente.setCliMail("tatiana91@gmail.com");
				
				clientesLogica.modificarCliente(cliente);
				logger.info("La información del cliente se modificó correctamente");
			}		
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarCliente () throws Exception {
		try{
			Clientes cliente = clientesLogica.consultarCliente(1151938779L);
			if(cliente != null){
				TiposDocumentos tipoDocumento = tiposDocumentosLogica.consultarTipoDocumento(10L);
				cliente.setTiposDocumentos(tipoDocumento);
				cliente.setCliDireccion("Calle 5 # 45-12");
				cliente.setCliTelefono("3459871");
				cliente.setCliMail("tatiana@gmail.com");
				
				clientesLogica.modificarCliente(cliente);
				logger.info("El cliente se modificó correctamente");
			}		
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarCliente () throws Exception {
		try{
			Clientes cliente = clientesLogica.consultarCliente(1151938779L);
			if(cliente != null){			
				clientesLogica.eliminarCliente(cliente);
				logger.info("El cliente se emilinó correctamente");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarCliente ()throws Exception {
		try{
			Clientes cliente = clientesLogica.consultarCliente(1151938779L);
			if(cliente != null){
				logger.info("Tipo de documento del cliente: "+cliente.getTiposDocumentos().getTdocCodigo());
				logger.info("Nombre del cliente: "+cliente.getCliNombre());
				logger.info("Dirección del cliente: "+cliente.getCliDireccion());
				logger.info("Teléfono del cliente: "+cliente.getCliTelefono());
				logger.info("Email del cliente: "+cliente.getCliMail());
			}else{
				logger.info("No existen clientes");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarClientes ()throws Exception {
		try{
			List<Clientes> cliente = clientesLogica.consultarClientes();
			for (Clientes cli : cliente) {
				logger.info("Tipo de documento del cliente: "+cli.getTiposDocumentos().getTdocCodigo());
				logger.info("Nombre del cliente: "+cli.getCliNombre());
				logger.info("Dirección del cliente: "+cli.getCliDireccion());
				logger.info("Teléfono del cliente: "+cli.getCliTelefono());
				logger.info("Email del cliente: "+cli.getCliMail());
				logger.info("-------------------------------------------------------------");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarClientesCuenta ()throws Exception {
		try{
			String numCuenta = "4008-5305-0010";
			Long idCliente = 101234L;
			String clave = "1234";
			Clientes cli = clientesLogica.consultarClientesCuenta(numCuenta, idCliente, clave);
			
			logger.info("Tipo de documento del cliente: "+cli.getTiposDocumentos().getTdocCodigo());
			logger.info("Nombre del cliente: "+cli.getCliNombre());
			logger.info("Dirección del cliente: "+cli.getCliDireccion());
			logger.info("Teléfono del cliente: "+cli.getCliTelefono());
			logger.info("Email del cliente: "+cli.getCliMail());
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}