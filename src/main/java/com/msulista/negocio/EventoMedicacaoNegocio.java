package com.msulista.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

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
			Mensagem.add("Horário informado está fora do intervalo de atendimento.");
			return false;
		} else {
			this.eventMedicacaoDAO = new EventoMedicacaoDAO();
			eventoMedicacao.setTitulo(eventoMedicacao.getAtendimento().getPaciente().getNomePaciente());
			this.salvaEvento(eventoMedicacao);
			this.replicaEvento(eventoMedicacao);
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
			Mensagem.add("Horário informado está fora do intervalo de atendimento.");
			return false;
		} else {
			this.eventMedicacaoDAO = new EventoMedicacaoDAO();
			try {
				this.eventMedicacaoDAO.alterar(eventoMedicacao);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	public List<EventoMedicacao> obterListaDiaCorrente() {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			return this.eventMedicacaoDAO.obterListaDiaCorrente();
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
	public void excluir(EventoMedicacao eventoMedicacao) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		this.eventMedicacaoDAO.excluir(eventoMedicacao);		
	}

	/**
	 * Verifica se Data do evento esta dentro do periodo de atendimento
	 * 
	 * @param atendimento
	 * @param dataHora
	 * @return
	 */
	protected boolean validaDataDoEventoForaDoPeriodoAtendimento(Atendimento atendimento, Date dataHora){
		
		if (atendimento.getDataInicial().after(dataHora) || atendimento.getDataFinal().before(dataHora)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verifica se Horario do evento esta dentro do periodo de atendimento
	 * 
	 * @param atendimento
	 * @param dataHora
	 * @return
	 */
	protected boolean validaHorarioDoEventoForaDoIntervaloAtendimento(Atendimento atendimento, Date dataHora) {
		boolean dt1 = DateUtil.comparaHr1MaiorHr2(atendimento.getHoraInicial(), dataHora);
		boolean dt2 = DateUtil.comparaHr1MaiorHr2(dataHora, atendimento.getHoraFinal());
		if (dt1 || dt2) {
			return true;
		}		
		return false;
	}
	
	/**
	 * Replica evento original com novas datas
	 * 
	 * @param evento
	 */
	protected void replicaEvento(EventoMedicacao evento) {
		if (evento.getTransientRepetirDiariamente()) {
			int repet = DateUtil.calculaNumeroDeDias(evento.getDataHora(), evento.getAtendimento().getDataFinal());
			for (int i = 0; i < repet; i++) {
				Date novoDia = this.adicionaDias(evento.getDataHora(), 1+i);
				EventoMedicacao novoEvento = this.criaNovoEvento(evento);
				novoEvento.setDataHora(novoDia);
				this.salvaEvento(novoEvento);
			}
		}
	}
	
	/**
	 * Cria novo evento com os dados do evento original para ser replicado com data difetente
	 * 
	 * @param ev
	 * @return
	 */
	protected EventoMedicacao criaNovoEvento(EventoMedicacao ev) {
		
		EventoMedicacao evento = new EventoMedicacao();
		evento.setAtendimento(ev.getAtendimento());
		evento.setDescricao(ev.getDescricao());
		evento.getMedicamentos().addAll(ev.getMedicamentos());
		evento.getRefeicoes().addAll(ev.getRefeicoes());
		evento.setTitulo(ev.getTitulo());
		
		return evento;
	}
	
	/**
	 * Salva evento no banco
	 * 
	 * @param eventoMedicacao
	 */
	protected void salvaEvento(EventoMedicacao eventoMedicacao) {
		try {
			this.eventMedicacaoDAO.salvar(eventoMedicacao);
		} catch (SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
	}
	/**
	 * Incrementa a data do evento em dias
	 * 
	 * @param dataEvento
	 * @param dia numero de incremento
	 * @return Date
	 */
	protected Date adicionaDias(Date dataEvento, int dia) {
		DateTime data = new DateTime(dataEvento);
		data = data.plusDays(dia);
		
		return data.toDate();
	}
}
