package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Retiros;
import co.edu.usbcali.modelo.RetirosId;

@Scope("singleton")
@Repository
public class RetirosDAO implements IRetirosDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	@Override
	public void crearRetiro(Retiros retiro) throws Exception {
		sessionFactory.getCurrentSession().save(retiro);		
	}

	@Override
	public void modificarRetiro(Retiros retiro) throws Exception {
		sessionFactory.getCurrentSession().update(retiro);		
	}

	@Override
	public void eliminarRetiro(Retiros retiro) throws Exception {
		sessionFactory.getCurrentSession().delete(retiro);		
	}

	@Override
	public Retiros consultarRetiro(RetirosId id) throws Exception {
		return (Retiros) sessionFactory.getCurrentSession().get(Retiros.class, id);
	}

	@Override
	public List<Retiros> consultarRetiros() throws Exception {
		String hql = "select ret from Retiros ret";
		return (List<Retiros>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}