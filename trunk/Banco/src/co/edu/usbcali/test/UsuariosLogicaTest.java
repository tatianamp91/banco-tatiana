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
import co.edu.usbcali.logica.IUsuariosLogica;
import co.edu.usbcali.modelo.TiposUsuarios;
import co.edu.usbcali.modelo.Usuarios;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class UsuariosLogicaTest {
	
	@Autowired
	private IUsuariosLogica usuariosLogica;
	@Autowired
	private ITiposUsuariosLogica tiposUsuariosLogica;

	private static Logger logger = LoggerFactory.getLogger(TiposDocumentosLogicaTest.class);
	/*
	@Test
	public void crearUsuario () throws Exception {
		try{
			Usuarios usuario = new Usuarios();
			usuario.setUsuCedula(123456L);
			TiposUsuarios tipoUsuario = tiposUsuariosLogica.consultarTipoUsuario(10L);
			usuario.setTiposUsuarios(tipoUsuario);
			usuario.setUsuNombre("Tatiana Moncada");
			usuario.setUsuLogin("Tatiana");
			usuario.setUsuClave("1234");
			
			usuariosLogica.crearUsuario(usuario);
			logger.info("El usuario se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarUsuario () throws Exception {
		try{
			Usuarios usuario = usuariosLogica.consultarUsuario(123456L);
			if(usuario != null){
				TiposUsuarios tipoUsuario = tiposUsuariosLogica.consultarTipoUsuario(10L);
				usuario.setTiposUsuarios(tipoUsuario);
				usuario.setUsuNombre("Tatiana Moncada");
				usuario.setUsuLogin("TatianaM");
				usuario.setUsuClave("1234");
				
				usuariosLogica.modificarUsuario(usuario);
				logger.info("El usuario se modificó correctamente");
			}else{
				logger.info("No existe el usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	@Test
	public void eliminarUsuario () throws Exception {
		try{
			Usuarios usuario = usuariosLogica.consultarUsuario(123456L);
			if(usuario != null){
				usuariosLogica.eliminarUsuario(usuario);
				logger.info("El usuario se eliminó correctamente");
			}else{
				logger.info("No existe el usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void consultarUsuario () throws Exception {
		try{
			Usuarios usuario = usuariosLogica.consultarUsuario(25L);
			if(usuario != null){
				logger.info("Tipo Usuario: "+usuario.getTiposUsuarios().getTusuCodigo());
				logger.info("Nombre: "+usuario.getUsuNombre());
				logger.info("Login :"+usuario.getUsuLogin());
				logger.info("clave: "+usuario.getUsuClave());
			}else{
				logger.info("No existe el usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarUsuarios () throws Exception {
		try{
			List<Usuarios> usuarios = usuariosLogica.consultarUsuarios();
			if(usuarios != null){
				for (Usuarios usuario : usuarios) {
					logger.info("Tipo Usuario: "+usuario.getTiposUsuarios().getTusuCodigo());
					logger.info("Nombre: "+usuario.getUsuNombre());
					logger.info("Login :"+usuario.getUsuLogin());
					logger.info("clave: "+usuario.getUsuClave());
					logger.info("--------------------------------------");
				}
			}else{
				logger.info("No existen usuarios");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void consultarUsuariosLogin() throws Exception {
		try{
			Usuarios usuario = usuariosLogica.consultarUsuariosLogin("amartinez");
			if(usuario != null){
				logger.info("Tipo Usuario: "+usuario.getTiposUsuarios().getTusuCodigo());
				logger.info("Nombre: "+usuario.getUsuNombre());
				logger.info("Login :"+usuario.getUsuLogin());
				logger.info("clave: "+usuario.getUsuClave());
			}else{
				logger.info("No existe el usuario");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}