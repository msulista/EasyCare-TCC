package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.EventoAtendimento;
import com.msulista.util.JPAUtil;

public class AgendaDao {

	public Boolean salvar(EventoAtendimento eventoAtendimento) throws SQLException{
		
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(eventoAtendimento);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	public Boolean alterar(EventoAtendimento eventoAtendimento) throws SQLException{

		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		EventoAtendimento retorno = this.obterEvento(eventoAtendimento.getId());
		retorno.setTitulo(eventoAtendimento.getTitulo());
		retorno.setDataInicio(eventoAtendimento.getDataInicio());
		retorno.setDataFim(eventoAtendimento.getDataFim());
		retorno.setStatus(eventoAtendimento.getStatus());
		retorno.setDescricao(eventoAtendimento.getDescricao());
		
		manager.merge(retorno);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<EventoAtendimento> obterLista() throws SQLException{

		EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("EventoAtendimento.findAll");
		List<EventoAtendimento> retorno = query.getResultList();
		manager.close();		
		
		return retorno;
	}

	public EventoAtendimento obterEvento(Long id) {
		
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("EventoAtendimento.findId");
		query.setParameter("id", id);
		EventoAtendimento eventoAtendimento = (EventoAtendimento)query.getSingleResult();
		manager.close();
		return eventoAtendimento;
	}

	public void excluir(Long id) {

		EntityManager manager = JPAUtil.getEntityManager();
		EventoAtendimento eventoAtendimento = this.obterEvento(id);
		manager.remove(eventoAtendimento);
		manager.close();
	}
	
	

}
