package co.edu.usbcali.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.modelo.Clientes;

@Scope("singleton")
@Repository
public class ClientesDAO implements IClientesDAO {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public void crearCliente(Clientes cliente) throws Exception {
		sessionFactory.getCurrentSession().save(cliente);		
	}

	@Override
	public void modificarCliente(Clientes cliente) throws Exception {
		sessionFactory.getCurrentSession().update(cliente);		
	}

	@Override
	public void eliminarCliente(Clientes cliente) throws Exception {
		sessionFactory.getCurrentSession().delete(cliente);		
	}

	@Override
	public Clientes consultarCliente(Long cliId) throws Exception {
		return (Clientes) sessionFactory.getCurrentSession().get(Clientes.class, cliId);
	}

	@Override
	public List<Clientes> consultarClientes() throws Exception {
		String hql = "select cli from Clientes cli";
		return (List<Clientes>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public List<Clientes> consultarClientesCuentas(Long cliId) throws Exception {
		String hql = "select cli from Clientes cli, Cuentas cue "
				+ "where cli.cliId = cue.clientes.cliId "
				+ "and cli.cliId ="+cliId;
		return (List<Clientes>) sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Override
	public Clientes consultarClientesCuenta(Long numCuenta, Long idCliente, String clave) throws Exception {
		String hql = "select cli from Clientes cli, Cuentas cue where cli.cliId = cue.clientes.cliId "
				+ "and cue.cueNumero = '"+numCuenta+"' and cli.cliId = "+idCliente+" "
				+ "and cue.cueClave = '"+clave+"'";
		return (Clientes) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
}