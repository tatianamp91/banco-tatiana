package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Consignaciones;
import co.edu.usbcali.modelo.ConsignacionesId;

@Scope("singleton")
@Repository
public class ConsignacionesDAO implements IConsignacionesDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void crearConsignacion(Consignaciones consignacion) throws Exception {
		sessionFactory.getCurrentSession().save(consignacion);		
	}

	@Override
	public void modificarConsignacion(Consignaciones consignacion) throws Exception {
		sessionFactory.getCurrentSession().update(consignacion);		
	}

	@Override
	public void eliminarConsignacion(Consignaciones consignacion) throws Exception {
		sessionFactory.getCurrentSession().delete(consignacion);		
	}

	@Override
	public Consignaciones consultarConsignacion(ConsignacionesId id) throws Exception {
		return (Consignaciones) sessionFactory.getCurrentSession().get(Consignaciones.class, id);
	}

	@Override
	public List<Consignaciones> consultarConsignaciones() throws Exception {
		String hql = "select con from Consignaciones con";
		return (List<Consignaciones>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public synchronized Long getConsecutivo(String sqlName) {

		Long consecutivo = null;
		List qlist = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.getNamedQuery(sqlName);
			qlist = query.list();
			for (java.util.Iterator iter = qlist.iterator(); iter.hasNext();) {
				consecutivo = (Long) iter.next();
			}
		} catch (org.hibernate.HibernateException e) {
			consecutivo = new Long(0);
		}
		return consecutivo;
	}
}