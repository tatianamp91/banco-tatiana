package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import co.edu.usbcali.modelo.Cuentas;
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
	public Retiros consultarRetiro(RetirosId id) throws Exception {
		return (Retiros) sessionFactory.getCurrentSession().get(Retiros.class, id);
	}

	@Override
	public List<Retiros> consultarRetiros() throws Exception {
		String hql = "select ret from Retiros ret";
		return (List<Retiros>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<Retiros> consultarRetirosCuenta(Cuentas cuenta) throws Exception {
		String hql = "select ret from Retiros ret, Cuentas cue "
				+ "where cue.cueNumero = ret.id.cuentas.cueNumero and cue.cueNumero ="+cuenta.getCueNumero();
		return (List<Retiros>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public synchronized Long getConsecutivoRetiros(String sqlName) {

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