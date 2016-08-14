package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import com.msulista.entidade.Cuidador;
import com.msulista.util.JPAUtil;

public class CuidadorDao implements BaseDao<Cuidador>{

	@Override
	public Boolean salvar(Cuidador cuidador) throws SQLException {
		EntityManager manager = JPAUtil.getEntityManager();
		manager.getTransaction().begin();
		
		manager.persist(cuidador);
		manager.getTransaction().commit();
		manager.close();
		
		return true;
	}

	@Override
	public Boolean alterar(Cuidador bean) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuidador> obterLista() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuidador obterEvento(Long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

}
