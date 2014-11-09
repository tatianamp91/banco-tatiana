package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Usuarios;

@Scope("singleton")
@Repository
public class UsuariosDAO implements IUsuariosDAO{

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void crearUsuario(Usuarios usuario) throws Exception {
		sessionFactory.getCurrentSession().save(usuario);		
	}

	@Override
	public void modificarUsuario(Usuarios usuario) throws Exception {
		sessionFactory.getCurrentSession().update(usuario);		
	}

	@Override
	public void eliminarUsuario(Usuarios usuario) throws Exception {
		sessionFactory.getCurrentSession().delete(usuario);		
	}

	@Override
	public Usuarios consultarUsuario(Long usuCedula) throws Exception {
		return (Usuarios) sessionFactory.getCurrentSession().get(Usuarios.class, usuCedula);
	}

	@Override
	public List<Usuarios> consultarUsuarios() throws Exception {
		String hql = "select usu from Usuarios usu";
		return (List<Usuarios>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<Usuarios> consultarUsuariosRetiros(Long usuCedula) throws Exception {
		String hql = "select usu from Usuarios usu, Retiros ret "
				+ "where usu.usuCedula = ret.usuarios.usuCedula "
				+ "and usu.usuCedula = "+usuCedula;
		return (List<Usuarios>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<Usuarios> consultarUsuariosConsignaciones(Long usuCedula) throws Exception {
		String hql = "select usu from Usuarios usu, Consignaciones con "
				+ "where usu.usuCedula = con.usuarios.usuCedula "
				+ "and usu.usuCedula = "+usuCedula;
		return (List<Usuarios>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public Usuarios consultarUsuariosLogin(Usuarios usuario) throws Exception {
		String hql = "select usu from Usuarios usu where usu.usuLogin ='"+usuario.getUsuLogin()+"'";
		return (Usuarios) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
	
	@Override
	public Usuarios consultarUsuariosLoginClave(String usuario, String clave) throws Exception {
		String hql = "select usu from Usuarios usu where usu.usuLogin ='"+usuario+"' "
				+ "and usu.usuClave = '"+clave+"'";
		return (Usuarios) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
	
}