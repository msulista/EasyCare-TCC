package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Agenda;
import com.msulista.util.JPAUtil;

public class AgendaDao implements BaseDao{

	@Override
	public Boolean salvar(Agenda agenda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean alterar(Agenda agenda) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
