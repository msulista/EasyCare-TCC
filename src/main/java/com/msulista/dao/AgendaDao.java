package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Agenda;
import com.msulista.util.JPAUtil;

public class AgendaDao implements BaseDao{

	@Override
	public Boolean salvar(Agenda agenda) throws SQLException{
		
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(agenda);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@Override
	public Boolean alterar(Agenda agenda) throws SQLException{

		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();

		Agenda retorno = this.obterAgenda(agenda.getId());
		retorno.setTitulo(agenda.getTitulo());
		retorno.setDataInicio(agenda.getDataInicio());
		retorno.setDataFim(agenda.getDataFim());
		retorno.setStatus(agenda.getStatus());
		retorno.setDescricao(agenda.getDescricao());
		
		manager.merge(retorno);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> obterLista() throws SQLException{

		EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("Agenda.findAll");
		List<Agenda> retorno = query.getResultList();
		manager.close();		
		
		return retorno;
	}

	@Override
	public Agenda obterAgenda(Long id) {
		
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("Agenda.findId");
		query.setParameter("id", id);
		Agenda agenda = (Agenda)query.getSingleResult();
		manager.close();
		return agenda;
	}

	@Override
	public void excluir(Long id) {

		EntityManager manager = JPAUtil.getEntityManager();
		Agenda agenda = this.obterAgenda(id);
		manager.remove(agenda);
		manager.close();
	}
	
	

}
