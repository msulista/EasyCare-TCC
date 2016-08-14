package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Medicamento;
import com.msulista.util.JPAUtil;

public class MedicamentoDao implements BaseDao<Medicamento>{

	@Override
	public Boolean salvar(Medicamento medicamento) throws SQLException {
		
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(medicamento);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@Override
	public Boolean alterar(Medicamento bean) throws SQLException {

		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.merge(bean);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> obterLista() throws SQLException {
		
		EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("Medicamento.findAll");
		List<Medicamento> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	@Override
	public Medicamento obterEvento(Long id) throws SQLException {
		
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("Medicamento.findPorId");		
		query.setParameter("id", id);
		
		Medicamento medicamento = (Medicamento) query.getSingleResult();
		manager.close();
		
		return medicamento;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
