package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Cuidador;
import com.msulista.entidade.Paciente;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuidador> obterLista() throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("Cuidador.findAll");
		
		final List<Cuidador> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Cuidador obterEvento(final Long id) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query query = manager.createNamedQuery("Cuidador.findPorId");
		query.setParameter("id", id);

		final Cuidador cuidador = (Cuidador) query.getSingleResult();
		manager.close();
		return cuidador;
	}

	@Override
	public void excluir(Cuidador cuidador) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.remove(manager.merge(cuidador));
		manager.getTransaction().commit();
		manager.close();
	}

}
