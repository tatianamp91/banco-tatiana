package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.TiposUsuarios;

@Scope("singleton")
@Repository
public class TiposUsuariosDAO implements ITiposUsuariosDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void crearTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		sessionFactory.getCurrentSession().save(tipoUsuario);		
	}

	@Override
	public void modificarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		sessionFactory.getCurrentSession().update(tipoUsuario);		
	}

	@Override
	public void eliminarTipoUsuario(TiposUsuarios tipoUsuario) throws Exception {
		sessionFactory.getCurrentSession().delete(tipoUsuario);		
	}

	@Override
	public TiposUsuarios consultarTipoUsuario(Long tusuCodigo) throws Exception {
		return (TiposUsuarios) sessionFactory.getCurrentSession().get(TiposUsuarios.class, tusuCodigo);
	}

	@Override
	public List<TiposUsuarios> consultarTiposUsuarios() throws Exception {
		String hql = "select tipu from TiposUsuarios tipu";
		return (List<TiposUsuarios>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<TiposUsuarios> consultarTiposUsuariosUsuarios(Long tusuCodigo) throws Exception {
		String hql = "select tipu from TiposUsuarios tipu, Usuarios usu "
				+ "where tipu.tusuCodigo = usu.tiposUsuarios.tusuCodigo "
				+ "and tipu.tusuCodigo ="+tusuCodigo;
		return (List<TiposUsuarios>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}