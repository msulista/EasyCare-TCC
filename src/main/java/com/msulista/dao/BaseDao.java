package com.msulista.dao;

import java.util.List;

import com.msulista.entidade.Agenda;

public interface BaseDao {
	
	public Boolean salvar(Agenda agenda);
	
	public Boolean alterar(Agenda agenda);
	
	public List<Agenda> obterLista();
	
	public Agenda obterAgenda(Long id);
	
	public void excluir(Long id);

}
