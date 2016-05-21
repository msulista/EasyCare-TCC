package com.msulista.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.AgendaDao;
import com.msulista.entidade.Agenda;
import com.msulista.util.Mensagem;

public class AgendaNegocio {

	private AgendaDao agendaDao;
	
	public void gravar(Agenda agenda) {
		
		this.verificaDataSeDataInicioMaiorQueFim(agenda.getDataInicio(), agenda.getDataFim());
		
		if (agenda.getId() == null) {
			
			this.salvar(agenda);
		}else {
			
			this.alterar(agenda);
		}
	}
	
	private void salvar(Agenda agenda) {
		
		
		try {
			this.agendaDao.salvar(agenda);
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.add("Ocorreu um erro ao salvar novo evento.");
		}
		
	}
	
	private void alterar(Agenda agenda) {
		
		try {
			this.agendaDao.alterar(agenda);
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
		}
	}
	
	private void verificaDataSeDataInicioMaiorQueFim(Date dtinicio, Date dtFim) {
		
		if (dtinicio.getTime() > dtFim.getTime()) {
			Mensagem.add("Data inicial não pode ser maior que data final.");
		}
	}
		
	public List<Agenda> obterLista() {
		
		this.agendaDao = new AgendaDao();
		
		List<Agenda> retorno = null;
		try {
			retorno = this.agendaDao.obterLista();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno; 
	}
	
	public Agenda obterAgenda(Long id) {
		
		this.agendaDao = new AgendaDao();
		return this.agendaDao.obterAgenda(id);
	}
}
