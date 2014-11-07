package co.edu.usbcali.dao;

import java.util.List;

import co.edu.usbcali.modelo.TiposDocumentos;

public interface ITiposDocumentosDAO {
	
	public void crearTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public void modificarTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public void eliminarTipoDocumento (TiposDocumentos tipoDocumento) throws Exception;
	
	public TiposDocumentos consultarTipoDocumento (Long  tdocCodigo) throws Exception;

	public List<TiposDocumentos> consultarTiposDocumentos () throws Exception;
	
	public List<TiposDocumentos> consultarTiposDocumentosClientes(Long tdocCodigo) throws Exception;

}
