package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import com.msulista.entidade.Cuidador;
import com.msulista.util.JPAUtil;

public class CuidadorDao implements BaseDao<Cuidador> {

	@Override
	public Boolean salvar(final Cuidador cuidador) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.persist(cuidador);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@Override
	public Boolean alterar(final Cuidador bean) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(bean);
		manager.getTransaction().commit();
		manager.close();

		return null;
	}

	@Override
	public List<Cuidador> obterLista() throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		// Query query = manager.createNamedQuery("Cuidador.findAll");

		return null;
	}

	@Override
	public Cuidador obterEvento(final Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

}
