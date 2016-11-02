package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Atendimento;
import com.msulista.util.JPAUtil;
import com.msulista.vo.RelatorioAtendimentoVO;

public class AtendimentoDao implements BaseDao<Atendimento> {

	@Override
	public Boolean salvar(final Atendimento atendimento) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.persist(atendimento);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@Override
	public Boolean alterar(final Atendimento atendimento) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(atendimento);
		manager.getTransaction().commit();
		manager.close();

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Atendimento> obterLista() throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Atendimento.findAll");
		final List<Atendimento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendimento> obterLista(Long id) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("Atendimento.findPorCuidador");
		query.setParameter("id", id);
		final List<Atendimento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Atendimento obterEvento(final Long id) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query query = manager.createNamedQuery("Atendimento.findPorId");
		query.setParameter("id", id);

		final Atendimento atendimento = (Atendimento) query.getSingleResult();
		manager.close();
		return atendimento;
	}
	
	public RelatorioAtendimentoVO obterRelatorioVO(Long id) {
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("Atendimento.findCustomizada");
		query.setParameter("id", id);
		
		RelatorioAtendimentoVO relatorio = (RelatorioAtendimentoVO) query.getSingleResult();
		manager.close();
		return relatorio;
	}

	@Override
	public void excluir(final Atendimento atendimento) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.remove(manager.merge(atendimento));
		manager.getTransaction().commit();
		manager.close();
	}

}
