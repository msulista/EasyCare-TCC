package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.msulista.entidade.Paciente;
import com.msulista.util.JPAUtil;

public class PacienteDao implements BaseDao<Paciente>{

	public Boolean salvar(Paciente paciente) throws SQLException {

		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(paciente);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
		
	}

	public Boolean alterar(Paciente paciente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> obterLista() throws SQLException {

		EntityManager manager = JPAUtil.getEntityManager();

		Query query = manager.createNamedQuery("Paciente.findAll");
		List<Paciente> retorno = query.getResultList();
		manager.close();
		return retorno;
	}

	public Paciente obterEvento(Long id) throws SQLException {
		
		EntityManager manager = JPAUtil.getEntityManager();
		Query query = manager.createNamedQuery("Paciente.findPorId");		
		query.setParameter("id", id);
		
		Paciente paciente = (Paciente) query.getSingleResult();
		manager.close();
		return paciente;
	}

	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	

}
