package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import com.msulista.entidade.Agenda;

public interface BaseDao {
	
	public Boolean salvar(Agenda agenda) throws SQLException;
	
	public Boolean alterar(Agenda agenda) throws SQLException;
	
	public List<Agenda> obterLista() throws SQLException;
	
	public Agenda obterAgenda(Long id) throws SQLException;
	
	public void excluir(Long id);

}
