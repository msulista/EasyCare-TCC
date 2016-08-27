package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.EventoMedicacaoDAO;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.util.Mensagem;

public class EventoMedicacaoNegocio implements NegocioBase<EventoMedicacao>{
	
	private EventoMedicacaoDAO eventMedicacaoDAO;
	
	@Override
	public String salvar(EventoMedicacao eventoMedicacao) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			this.eventMedicacaoDAO.salvar(eventoMedicacao);
		} catch (SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public String alterar(EventoMedicacao eventoMedicacao) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			this.eventMedicacaoDAO.alterar(eventoMedicacao);
		} catch (SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public List<EventoMedicacao> obterLista() {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			return this.eventMedicacaoDAO.obterLista();
		} catch (SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public EventoMedicacao obterPorId(Long id) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			return this.eventMedicacaoDAO.obterEvento(id);
		} catch (SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public void excluir(Long id) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		this.eventMedicacaoDAO.excluir(id);		
	}

}
