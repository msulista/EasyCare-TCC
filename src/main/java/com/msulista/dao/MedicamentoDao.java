package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Medicamento;
import com.msulista.util.JPAUtil;

public class MedicamentoDao implements BaseDao<Medicamento> {

	@Override
	public Boolean salvar(final Medicamento medicamento) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.persist(medicamento);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@Override
	public Boolean alterar(final Medicamento bean) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(bean);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> obterLista() throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Medicamento.findAll");
		final List<Medicamento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Medicamento> obterListaPorCuidador(final Long id) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Medicamento.findPorCuidador");
		query.setParameter("id", id);
		final List<Medicamento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@SuppressWarnings("unchecked")
	public List<Medicamento> obterListaPorPaciente(final Long id) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Medicamento.findPorPaciente");
		query.setParameter("id", id);
		final List<Medicamento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Medicamento obterEvento(final Long id) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		final Query query = manager.createNamedQuery("Medicamento.findPorId");
		query.setParameter("id", id);

		final Medicamento medicamento = (Medicamento) query.getSingleResult();
		manager.close();

		return medicamento;
	}

	@Override
	public void excluir(final Medicamento medicamento) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.remove(manager.merge(medicamento));
		manager.getTransaction().commit();
		manager.close();

	}

}
