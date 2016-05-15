package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

import com.msulista.entidade.Agenda;

public interface BaseDao {
	
	public Boolean salvar(Agenda agenda);
	
	public Boolean alterar(Agenda agenda);
	
	public List<Agenda> obterLista() throws SQLException;
	
	public Agenda obterAgenda(Long id);
	
	public void excluir(Long id);

}
