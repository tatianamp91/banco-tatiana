package co.edu.usbcali.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.logica.ITiposUsuariosLogica;
import co.edu.usbcali.modelo.TiposUsuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class TiposUsuariosLogicaTest {

	@Autowired
	private ITiposUsuariosLogica tiposUsuariosLogica;

	private static Logger logger = LoggerFactory.getLogger(TiposDocumentosLogicaTest.class);
	/*
	@Test
	public void crearTipoUsuario () throws Exception {		
		try{
			TiposUsuarios tipoUsuario = new TiposUsuarios();
			tipoUsuario.setTusuCodigo(30L);
			tipoUsuario.setTusuNombre("Empleado");
			
			tiposUsuariosLogica.crearTipoUsuario(tipoUsuario);
			logger.info("El tipo de usuario se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarTipoUsuario () throws Exception {
		try{
			TiposUsuarios tipoUsuario = tiposUsuariosLogica.consultarTipoUsuario(30L);
			if(tipoUsuario != null){
				tipoUsuario.setTusuNombre("Cajero");
				tiposUsuariosLogica.modificarTipoUsuario(tipoUsuario);
				logger.info("El tipo de usuario se modificó correctamente");
			}else{
				logger.info("No existe tipo de usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarTipoUsuario () throws Exception {
		try{
			TiposUsuarios tipoUsuario = tiposUsuariosLogica.consultarTipoUsuario(10L);
			if(tipoUsuario != null){
				tiposUsuariosLogica.eliminarTipoUsuario(tipoUsuario);
				logger.info("El tipo de usuario se eliminó correctamente");
			}else{
				logger.info("No existe tipo de usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
	
	@Test
	public void consultarTipoUsuario () throws Exception {
		try{
			TiposUsuarios tipoUsuario = tiposUsuariosLogica.consultarTipoUsuario(10L);
			if(tipoUsuario != null){
				logger.info("Nombre: "+tipoUsuario.getTusuNombre());
			}else{
				logger.info("No existe tipo de usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void consultarTiposUsuarios () throws Exception {
		try{
			List<TiposUsuarios> tiposUsuarios = tiposUsuariosLogica.consultarTiposUsuarios();
			if(tiposUsuarios != null){
				for (TiposUsuarios tipoUsuario : tiposUsuarios) {
					logger.info("Nombre: "+tipoUsuario.getTusuNombre());
					logger.info("-------------------------------------");
				}
			}else{
				logger.info("No existen tipos de usuarios");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}