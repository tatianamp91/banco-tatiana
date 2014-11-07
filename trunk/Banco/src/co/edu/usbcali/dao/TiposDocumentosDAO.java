package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.TiposDocumentos;

@Scope("singleton")
@Repository
public class TiposDocumentosDAO implements ITiposDocumentosDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void crearTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		sessionFactory.getCurrentSession().save(tipoDocumento);		
	}

	@Override
	public void modificarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		sessionFactory.getCurrentSession().update(tipoDocumento);		
	}

	@Override
	public void eliminarTipoDocumento(TiposDocumentos tipoDocumento) throws Exception {
		sessionFactory.getCurrentSession().delete(tipoDocumento);		
	}

	@Override
	public TiposDocumentos consultarTipoDocumento(Long tdocCodigo) throws Exception {
		return (TiposDocumentos) sessionFactory.getCurrentSession().get(TiposDocumentos.class, tdocCodigo);
	}

	@Override
	public List<TiposDocumentos> consultarTiposDocumentos() throws Exception {
		String hql = "select tipd from TiposDocumentos tipd";
		return (List<TiposDocumentos>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<TiposDocumentos> consultarTiposDocumentosClientes(Long tdocCodigo) throws Exception {
		String hql = "select tipd from TiposDocumentos tipd, Clientes cli "
				+ "where tipd.tdocCodigo = cli.tiposDocumentos.tdocCodigo "
				+ "and tipd.tdocCodigo = "+tdocCodigo;
		return (List<TiposDocumentos>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}