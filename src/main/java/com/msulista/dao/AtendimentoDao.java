package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Atendimento;
import com.msulista.entidade.Paciente;
import com.msulista.util.JPAUtil;

public class AtendimentoDao implements BaseDao<Atendimento>{

	@Override
	public Boolean salvar(Atendimento atendimento) throws SQLException {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(atendimento);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@Override
	public Boolean alterar(Atendimento atendimento) throws SQLException {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(atendimento);
		manager.getTransaction().commit();
		manager.close();
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> obterLista() throws SQLException {
		EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("Atendimento.findAll");
		List<Atendimento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Atendimento obterEvento(Long id) throws SQLException {
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("Atendimento.findPorId");		
		query.setParameter("id", id);
		
		Atendimento atendimento = (Atendimento) query.getSingleResult();
		manager.close();
		return atendimento;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

}
