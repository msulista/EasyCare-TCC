package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Paciente;
import com.msulista.util.JPAUtil;

public class PacienteDao implements BaseDao<Paciente> {

	@Override
	public Boolean salvar(final Paciente paciente) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.persist(paciente);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@Override
	public Boolean alterar(final Paciente paciente) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(paciente);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> obterLista() throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Paciente.findAll");
		
		final List<Paciente> retorno = query.getResultList();
		manager.close();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> obterListaPorCuidador(Long cuidId) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Paciente.findPorCuidador");
		query.setParameter("id", cuidId);
		
		final List<Paciente> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Paciente obterEvento(final Long id) throws SQLException {

		final EntityManager manager = JPAUtil.getEntityManager();
		final Query query = manager.createNamedQuery("Paciente.findPorId");
		query.setParameter("id", id);

		final Paciente paciente = (Paciente) query.getSingleResult();
		manager.close();
		return paciente;
	}



	@Override
	public void excluir(Paciente paciente) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.remove(manager.merge(paciente));
		manager.getTransaction().commit();
		manager.close();
	}

}
