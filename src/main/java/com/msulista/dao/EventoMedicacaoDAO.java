package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.EventoMedicacao;
import com.msulista.util.JPAUtil;
import com.msulista.util.Mensagem;

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
	
	public List<EventoMedicacao> obterListaDiaCorrente() throws SQLException {
		final EntityManager manager = JPAUtil.getEntityManager();

		final Query query = manager.createNamedQuery("EventoMedicacao.findDiaCorrente");
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

	@Override
	public void excluir(Long id) {
		final EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		EventoMedicacao eventoMedicacao = null;
		try {
			eventoMedicacao = this.obterEvento(id);
		} catch (SQLException e) {
			Mensagem.add("Erro ao conectar com o banco de dados.");
		}
		manager.remove(eventoMedicacao);
		manager.getTransaction().commit();
		manager.close();
	}

}
