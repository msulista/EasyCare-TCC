package com.msulista.negocio;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

import com.msulista.dao.EventoMedicacaoDAO;
import com.msulista.entidade.Atendimento;
import com.msulista.entidade.Cuidador;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.util.DateUtil;
import com.msulista.util.Mensagem;
import com.msulista.util.SessionUtil;

public class EventoMedicacaoNegocio implements NegocioBase<EventoMedicacao> {

	private EventoMedicacaoDAO eventMedicacaoDAO;

	@Override
	public boolean salvar(final EventoMedicacao eventoMedicacao) {

		final boolean data = this.validaDataDoEventoForaDoPeriodoAtendimento(eventoMedicacao.getAtendimento(),
				eventoMedicacao.getDataHora());
		final boolean hora = this.validaHorarioDoEventoForaDoIntervaloAtendimento(eventoMedicacao.getAtendimento(),
				eventoMedicacao.getDataHora());
		if (data) {
			Mensagem.add("Data informada está fora do periodo de atendimento.");
			return false;
		} else if (hora) {
			Mensagem.add("Horário informado está fora do intervalo de atendimento.");
			return false;
		} else {
			eventoMedicacao.setTitulo(eventoMedicacao.getAtendimento().getPaciente().getNomePaciente());
			this.salvaEvento(eventoMedicacao);
			this.multiplicadorDeEventos(eventoMedicacao);
		}
		return true;
	}

	@Override
	public boolean alterar(final EventoMedicacao eventoMedicacao) {

		final boolean data = this.validaDataDoEventoForaDoPeriodoAtendimento(eventoMedicacao.getAtendimento(),
				eventoMedicacao.getDataHora());
		final boolean hora = this.validaHorarioDoEventoForaDoIntervaloAtendimento(eventoMedicacao.getAtendimento(),
				eventoMedicacao.getDataHora());
		if (data) {
			Mensagem.add("Data informada está fora do periodo de atendimento.");
			return false;
		} else if (hora) {
			Mensagem.add("Horário informado está fora do intervalo de atendimento.");
			return false;
		} else {
			this.eventMedicacaoDAO = new EventoMedicacaoDAO();
			try {
				eventoMedicacao.setTitulo(eventoMedicacao.getAtendimento().getPaciente().getNomePaciente());
				this.eventMedicacaoDAO.alterar(eventoMedicacao);
				this.multiplicadorDeEventos(eventoMedicacao);
			} catch (final SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public List<EventoMedicacao> obterLista() {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		final Cuidador usuarioLogado = SessionUtil.obtemUsuarioLogado();
		try {
			if (usuarioLogado != null) {

				return this.eventMedicacaoDAO.obterLista(usuarioLogado.getId());
			}
		} catch (final SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	public List<EventoMedicacao> obterListaDiaCorrente() {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			return this.eventMedicacaoDAO.obterListaDiaCorrente();
		} catch (final SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public EventoMedicacao obterPorId(final Long id) {
		this.eventMedicacaoDAO = new EventoMedicacaoDAO();
		try {
			return this.eventMedicacaoDAO.obterEvento(id);
		} catch (final SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
		return null;
	}

	@Override
	public void excluir(final EventoMedicacao eventoMedicacao) {
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
	protected boolean validaDataDoEventoForaDoPeriodoAtendimento(final Atendimento atendimento, final Date dataHora) {

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
	protected boolean validaHorarioDoEventoForaDoIntervaloAtendimento(final Atendimento atendimento,
			final Date dataHora) {
		final boolean dt1 = DateUtil.comparaHr1MaiorHr2(atendimento.getHoraInicial(), dataHora);
		final boolean dt2 = DateUtil.comparaHr1MaiorHr2(dataHora, atendimento.getHoraFinal());
		if (dt1 || dt2) {
			return true;
		}
		return false;
	}

	protected void multiplicadorDeEventos(final EventoMedicacao eventoMedicacao) {

		if (eventoMedicacao.getTransientRepetirDiariamente() && eventoMedicacao.getTransientFrequenciaEvento() > 0) {
			this.repeteEventoHora(eventoMedicacao);
			this.replicaEventoPorDataEHora(eventoMedicacao);
		} else if (eventoMedicacao.getTransientRepetirDiariamente()) {
			this.replicaEvento(eventoMedicacao);
		} else if (eventoMedicacao.getTransientFrequenciaEvento() > 0) {
			this.repeteEventoHora(eventoMedicacao);
		}
	}

	/**
	 * Cria novos eventos por data e hora
	 *
	 * @param eventoMedicacao
	 */
	private void replicaEventoPorDataEHora(final EventoMedicacao eventoMedicacao) {
		final int repet = DateUtil.calculaNumeroDeDias(eventoMedicacao.getDataHora(),
				eventoMedicacao.getAtendimento().getDataFinal());
		for (int i = 0; i < repet; i++) {
			final Date novoDia = this.adicionaDias(eventoMedicacao.getDataHora(), 1 + i);
			final EventoMedicacao novoEvento = this.criaNovoEvento(eventoMedicacao);
			novoEvento.setDataHora(novoDia);
			this.salvaEvento(novoEvento);
			this.repeteEventoHora(novoEvento);
		}
	}

	/**
	 * Replica evento original com novas datas
	 *
	 * @param evento
	 */
	protected void replicaEvento(final EventoMedicacao evento) {
		if (evento.getTransientRepetirDiariamente()) {
			final int repet = DateUtil.calculaNumeroDeDias(evento.getDataHora(),
					evento.getAtendimento().getDataFinal());
			for (int i = 0; i < repet; i++) {
				final Date novoDia = this.adicionaDias(evento.getDataHora(), 1 + i);
				final EventoMedicacao novoEvento = this.criaNovoEvento(evento);
				novoEvento.setDataHora(novoDia);
				this.salvaEvento(novoEvento);
			}
		}
	}

	/**
	 * Repete o evento incrementando hora de acordo com o informado
	 *
	 * @param evento
	 */
	protected void repeteEventoHora(final EventoMedicacao evento) {
		if (evento.getTransientFrequenciaEvento() > 0) {
			final Date novaHora = this.adicionaHora(evento.getDataHora(), evento.getTransientFrequenciaEvento());
			final EventoMedicacao novoEvento = this.criaNovoEvento(evento);
			novoEvento.setDataHora(novaHora);
			this.salvaEvento(novoEvento);
		}
	}

	/**
	 * Cria novo evento com os dados do evento original para ser replicado com
	 * data difetente
	 *
	 * @param ev
	 * @return
	 */
	protected EventoMedicacao criaNovoEvento(final EventoMedicacao ev) {

		final EventoMedicacao evento = new EventoMedicacao();
		evento.setAtendimento(ev.getAtendimento());
		evento.setQuantidade(ev.getQuantidade());
		evento.setDescricao(ev.getDescricao());
		evento.getMedicamentos().addAll(ev.getMedicamentos());
		evento.getRefeicoes().addAll(ev.getRefeicoes());
		evento.setTitulo(ev.getTitulo());
		evento.setTransientFrequenciaEvento(ev.getTransientFrequenciaEvento());

		return evento;
	}

	/**
	 * Salva evento no banco
	 *
	 * @param eventoMedicacao
	 */
	protected void salvaEvento(final EventoMedicacao eventoMedicacao) {
		try {
			this.eventMedicacaoDAO.salvar(eventoMedicacao);
		} catch (final SQLException e) {
			Mensagem.add("Erro ao executar Sql.");
		}
	}

	/**
	 * Incrementa a data do evento em dias
	 *
	 * @param dataEvento
	 * @param dia
	 *            numero de incremento
	 * @return Date
	 */
	protected Date adicionaDias(final Date dataEvento, final int dia) {
		DateTime data = new DateTime(dataEvento);
		data = data.plusDays(dia);

		return data.toDate();
	}

	/**
	 * Incrementa a data do evento em horas
	 *
	 * @param dataEvento
	 * @param hora
	 *            numero de horas de incremento
	 * @return Date
	 */
	protected Date adicionaHora(final Date dataEvento, final int hora) {
		DateTime data = new DateTime(dataEvento);
		data = data.plusHours(hora);

		return data.toDate();
	}
}
