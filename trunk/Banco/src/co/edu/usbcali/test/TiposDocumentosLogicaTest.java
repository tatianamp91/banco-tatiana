package co.edu.usbcali.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.edu.usbcali.logica.ITiposDocumentosLogica;
import co.edu.usbcali.modelo.TiposDocumentos;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:C:\\Users\\pc\\Workspaces\\MyEclipse Professional 2014\\Banco\\WebRoot\\WEB-INF\\applicationContext.xml"})
public class TiposDocumentosLogicaTest {

	@Autowired
	private ITiposDocumentosLogica tiposDocumentosLogica;
	
	private static Logger logger = LoggerFactory.getLogger(TiposDocumentosLogicaTest.class);
	/*
	@Test
	public void crearTipoDocumento () throws Exception {
		try{
			TiposDocumentos tipoDocumento = new TiposDocumentos();
			tipoDocumento.setTdocCodigo(40L);
			tipoDocumento.setTdocNombre("Pasaporte");
			
			tiposDocumentosLogica.crearTipoDocumento(tipoDocumento);
			logger.info("El tipo de documento se creó correctamente");
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void modificarTipoDocumento () throws Exception {
		try{
			TiposDocumentos tipoDocumento = tiposDocumentosLogica.consultarTipoDocumento(40L);
			if(tipoDocumento != null){
				tipoDocumento.setTdocNombre("Pasaportes");
				tiposDocumentosLogica.modificarTipoDocumento(tipoDocumento);
				logger.info("El tipo de documento se modificó correctamente");
			}else{
				logger.info("No existe tipo de documento");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	
	@Test
	public void eliminarTipoDocumento () throws Exception {
		try{
			TiposDocumentos tipoDocumento = tiposDocumentosLogica.consultarTipoDocumento(40L);
			if(tipoDocumento != null){
				tiposDocumentosLogica.eliminarTipoDocumento(tipoDocumento);
				logger.info("El tipo de documento se eliminó correctamente");
			}else{
				logger.info("No existe tipo de documento");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	*/
	@Test
	public void consultarTipoDocumento () throws Exception {
		try{
			TiposDocumentos tipoDocumento = tiposDocumentosLogica.consultarTipoDocumento(40L);
			if(tipoDocumento != null){
				logger.info("Nombre:"+tipoDocumento.getTdocNombre());
			}else{
				logger.info("No existe tipo de documento");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}
	/*
	@Test
	public void consultarTiposDocumentos () throws Exception {
		try{
			List<TiposDocumentos> tiposDocumentos = tiposDocumentosLogica.consultarTiposDocumentos();
			if(tiposDocumentos != null){
				for (TiposDocumentos tipoDocumento : tiposDocumentos) {
					logger.info("Nombre:"+tipoDocumento.getTdocNombre());
					logger.info("---------------------------------------");
				}
			}else{
				logger.info("No existen tipos de documentos");
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}*/
}