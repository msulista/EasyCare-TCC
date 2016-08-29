package com.msulista.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.msulista.dao.EventoMedicacaoDAO;
import com.msulista.entidade.Atendimento;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.util.DateUtil;
import com.msulista.util.Mensagem;

public class EventoMedicacaoNegocio implements NegocioBase<EventoMedicacao>{
	
	private EventoMedicacaoDAO eventMedicacaoDAO;
	
	@Override
	public boolean salvar(EventoMedicacao eventoMedicacao) {
		
		boolean data = this.validaDataDoEventoForaDoPeriodoAtendimento(eventoMedicacao.getAtendimento(), eventoMedicacao.getDataHora());
		boolean hora = this.validaHorarioDoEventoForaDoIntervaloAtendimento(eventoMedicacao.getAtendimento(), eventoMedicacao.getDataHora());
		if (data) {
			Mensagem.add("Data informada está fora do periodo de atendimento.");
			return false;
		} else if (hora) {
			Mensagem.add("Horario informado está fora do intervalo de atendimento.");
			return false;
		} else {
			this.eventMedicacaoDAO = new EventoMedicacaoDAO();
			try {
				this.eventMedicacaoDAO.salvar(eventoMedicacao);
			} catch (SQLException e) {
				Mensagem.add("Erro ao executar Sql.");
			}
		}
		return true;
	}

	@Override
	public boolean alterar(EventoMedicacao eventoMedicacao) {
		
		boolean data = this.validaDataDoEventoForaDoPeriodoAtendimento(eventoMedicacao.getAtendimento(), eventoMedicacao.getDataHora());
		boolean hora = this.validaHorarioDoEventoForaDoIntervaloAtendimento(eventoMedicacao.getAtendimento(), eventoMedicacao.getDataHora());
		if (data) {
			Mensagem.add("Data informada está fora do periodo de atendimento.");
			return false;
		} else if (hora) {
			Mensagem.add("Horario informado está fora do intervalo de atendimento.");
			return false;
		} else {
			this.eventMedicacaoDAO = new EventoMedicacaoDAO();
			try {
				this.eventMedicacaoDAO.alterar(eventoMedicacao);
			} catch (SQLException e) {
				Mensagem.add("Erro ao executar Sql.");
			}
		}
		return true;
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

	protected boolean validaDataDoEventoForaDoPeriodoAtendimento(Atendimento atendimento, Date dataHora){
		
		if (atendimento.getDataInicial().after(dataHora) || atendimento.getDataFinal().before(dataHora)) {
			return true;
		}
		return false;
	}
	
	protected boolean validaHorarioDoEventoForaDoIntervaloAtendimento(Atendimento atendimento, Date dataHora) {
		boolean dt1 = DateUtil.comparaHr1MaiorHr2(atendimento.getHoraInicial(), dataHora);
		boolean dt2 = DateUtil.comparaHr1MaiorHr2(dataHora, atendimento.getHoraFinal());
		if (dt1 || dt2) {
			return true;
		}		
		return false;
	}
}
