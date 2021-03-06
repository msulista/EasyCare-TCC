package com.msulista.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.EventoMedicacao;
import com.msulista.util.DateUtil;
import com.msulista.util.JPAUtil;
import com.msulista.util.SessionUtil;

public class EventoMedicacaoDAO implements BaseDao<EventoMedicacao>{

	@Override
	public Boolean salvar(EventoMedicacao evento) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.persist(evento);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@Override
	public Boolean alterar(EventoMedicacao evento) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.merge(evento);
		manager.getTransaction().commit();
		manager.close();

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EventoMedicacao> obterLista() throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("EventoMedicacao.findAll");
		final List<EventoMedicacao> retorno = query.getResultList();
		manager.close();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoMedicacao> obterLista(Long id) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("EventoMedicacao.findPorCuidador");
		query.setParameter("id", id);
		final List<EventoMedicacao> retorno = query.getResultList();
		manager.close();
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoMedicacao> obterListaDiaCorrente() throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("EventoMedicacao.findDiaCorrente");
		query.setParameter("dataInicio", new Date());
		query.setParameter("dataFim", DateUtil.dataSistemaUltimoHorario());
		query.setParameter("id", SessionUtil.obtemUsuarioLogado().getId());
		final List<EventoMedicacao> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public EventoMedicacao obterEvento(Long id) throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();
		final Query query = manager.createNamedQuery("EventoMedicacao.findId");
		query.setParameter("id", id);

		final EventoMedicacao eventoMedicacao= (EventoMedicacao) query.getSingleResult();
		manager.close();
		return eventoMedicacao;
	}
	
	@SuppressWarnings("unchecked")
	public List<EventoMedicacao> obterListaPorAtendimentoId(Long atendId) {
		
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("EventoMedicacao.findPorAtendimentoId");
		query.setParameter("id", atendId);
		List<EventoMedicacao> retorno = query.getResultList();
		manager.close();
		
		return retorno;
	}

	@Override
	public void excluir(EventoMedicacao eventoMedicacao) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		manager.remove(manager.merge(eventoMedicacao));
		manager.getTransaction().commit();
		manager.close();
	}

}
