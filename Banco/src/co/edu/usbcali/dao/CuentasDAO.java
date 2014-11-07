package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Cuentas;

@Scope ("singleton")
@Repository
public class CuentasDAO implements ICuentasDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void crearCuenta(Cuentas cuenta) throws Exception {
		sessionFactory.getCurrentSession().save(cuenta);		
	}

	@Override
	public void modificarCuenta(Cuentas cuenta) throws Exception {
		sessionFactory.getCurrentSession().update(cuenta);		
	}

	@Override
	public void eliminarCuenta(Cuentas cuenta) throws Exception {
		sessionFactory.getCurrentSession().delete(cuenta);		
	}

	@Override
	public Cuentas consultarCuenta(String cueNumero) throws Exception {
		return (Cuentas) sessionFactory.getCurrentSession().get(Cuentas.class, cueNumero);
	}

	@Override
	public List<Cuentas> consultarCuentas() throws Exception {
		String hql = "select cue from Cuentas cue";
		return (List<Cuentas>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}	
	
	@Override
	public List<Cuentas> consultarCuentasRetiros(String cueNumero) throws Exception {
		String hql = "select cue from Cuentas cue, Retiros ret "
				+ "where cue.cueNumero = ret.id.cuentas.cueNumero "
				+ "and cue.cueNumero = '"+cueNumero+"'";
		return (List<Cuentas>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<Cuentas> consultarCuentasConsignaciones(String cueNumero) throws Exception {
		String hql = "select cue from Cuentas cue, Consignaciones con "
				+ "where cue.cueNumero = con.id.cuentas.cueNumero "
				+ "and cue.cueNumero = '"+cueNumero+"'";
		return (List<Cuentas>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
}