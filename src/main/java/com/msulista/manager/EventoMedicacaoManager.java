package com.msulista.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import com.msulista.entidade.Atendimento;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.entidade.Medicamento;
import com.msulista.negocio.EventoMedicacaoNegocio;
import com.msulista.util.DateUtil;
import com.msulista.util.Mensagem;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "eventoMedicacao", pattern = "/eventoMedicacao", viewId = "/pages/usuario/eventoMedicacao/eventoMedicacao-listar.xhtml"),
		@URLMapping(id = "eventoMedicacao-incluir", pattern = "/incluir", viewId = "/pages/usuario/eventoMedicacao/eventoMedicacao-incluir.xhtml", parentId = "eventoMedicacao"),
		@URLMapping(id = "eventoMedicacao-editar", pattern = "/#{eventoMedicacaoManager.eventoMedicacao.id}/editar", viewId = "/pages/usuario/eventoMedicacao/eventoMedicacao-editar.xhtml", parentId = "eventoMedicacao") })
public class EventoMedicacaoManager {

	private ScheduleModel scheduleModel;
	private EventoMedicacao eventoMedicacao;
	private List<EventoMedicacao> eventoMedicacoes;
	private EventoMedicacaoNegocio eventMedicacaoNegocio;
	private Medicamento medicademento;
	private Atendimento atendimento;

	@PostConstruct
	public void inicializar() {
		this.eventoMedicacao = new EventoMedicacao();
		this.eventoMedicacoes = new ArrayList<>();
		this.eventMedicacaoNegocio = new EventoMedicacaoNegocio();
		this.scheduleModel = new DefaultScheduleModel();

		this.eventoMedicacoes = this.eventMedicacaoNegocio.obterLista();

		for (final EventoMedicacao eventoMedicacao : this.eventoMedicacoes) {
			final DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(eventoMedicacao.getId());
			evt.setTitle(eventoMedicacao.getTitulo());
			evt.setStartDate(eventoMedicacao.getDataHora());
			evt.setEndDate(eventoMedicacao.getDataHora());
			evt.setDescription(eventoMedicacao.getDescricao());
			evt.setAllDay(false);
			evt.setEditable(true);
			// evt.setStyleClass("event-green");

			this.scheduleModel.addEvent(evt);
		}
	}

	public String salvar() {
		this.eventoMedicacao.getMedicamentos().add(this.medicademento);
		if (this.eventMedicacaoNegocio.salvar(this.eventoMedicacao)) {
			this.inicializar();
			return "pretty:eventoMedicacao";
		}
		return null;
	}

	public String alterar() {
		this.eventoMedicacao.getMedicamentos().add(this.medicademento);
		if (this.eventMedicacaoNegocio.alterar(this.eventoMedicacao)) {
			this.inicializar();
			return "pretty:eventoMedicacao";
		}
		return null;
	}

	public String editar(final EventoMedicacao evento) {
		this.eventoMedicacao = evento;
		return "pretty:eventoMedicacao-editar";
	}

	public List<EventoMedicacao> obterLista() {
		return this.eventMedicacaoNegocio.obterLista();
	}

	public List<EventoMedicacao> obterListaDiaCorrente() {
		return this.eventMedicacaoNegocio.obterListaDiaCorrente();
	}

	public EventoMedicacao obterEventoMedicacao() {
		return this.eventMedicacaoNegocio.obterPorId(this.eventoMedicacao.getId());
	}

	public String excluir(final EventoMedicacao evento) {
		this.eventMedicacaoNegocio.excluir(evento);
		return "pretty:eventoMedicacao";
	}

	public void eventoRealizado(final EventoMedicacao evento) {

		evento.setStattus(1);
		this.eventoMedicacao = evento;
		this.eventMedicacaoNegocio.alterar(this.eventoMedicacao);
		Mensagem.add("Evento realizado!!!");
	}

	public void eventoNaoRealizado(final EventoMedicacao evento) {

		evento.setStattus(0);
		this.eventoMedicacao = evento;
		this.eventMedicacaoNegocio.alterar(this.eventoMedicacao);
		Mensagem.add("Evento n�o realizado!!!");
	}

	public void verificaAlertaHorario() {
		final List<EventoMedicacao> eventos = this.obterListaDiaCorrente();
		for (final EventoMedicacao eventoMedicacao : eventos) {
			final Long minuto = DateUtil.verificaHoraAlareme(eventoMedicacao.getDataHora());
			if (minuto.intValue() >= 0 && minuto.intValue() <= 5) {
				Mensagem.add("Voc� possui um evento agendado em " + minuto + " minutos !!!");
			}
		}
	}

	/**
	 * @return the eventoMedicacao
	 */
	public EventoMedicacao getEventoMedicacao() {
		return this.eventoMedicacao;
	}

	/**
	 * @param eventoMedicacao
	 *            the eventoMedicacao to set
	 */
	public void setEventoMedicacao(final EventoMedicacao eventoMedicacao) {
		this.eventoMedicacao = eventoMedicacao;
	}

	/**
	 * @return the eventoMedicacoes
	 */
	public List<EventoMedicacao> getEventoMedicacoes() {
		return this.eventoMedicacoes;
	}

	/**
	 * @param eventoMedicacoes
	 *            the eventoMedicacoes to set
	 */
	public void setEventoMedicacoes(final List<EventoMedicacao> eventoMedicacoes) {
		this.eventoMedicacoes = eventoMedicacoes;
	}

	/**
	 * @return the eventMedicacaoNegocio
	 */
	public EventoMedicacaoNegocio getEventMedicacaoNegocio() {
		return this.eventMedicacaoNegocio;
	}

	/**
	 * @param eventMedicacaoNegocio
	 *            the eventMedicacaoNegocio to set
	 */
	public void setEventMedicacaoNegocio(final EventoMedicacaoNegocio eventMedicacaoNegocio) {
		this.eventMedicacaoNegocio = eventMedicacaoNegocio;
	}

	public ScheduleModel getScheduleModel() {
		return this.scheduleModel;
	}

	public void setScheduleModel(final ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	/**
	 * @return the medicademento
	 */
	public Medicamento getMedicademento() {
		return this.medicademento;
	}

	/**
	 * @param medicademento
	 *            the medicademento to set
	 */
	public void setMedicademento(final Medicamento medicademento) {
		this.medicademento = medicademento;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(final Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	@URLActions(actions = { @URLAction(mappingId = "eventoMedicacao-editar", onPostback = false) })
	public void load() throws IOException {
		this.eventoMedicacao = this.eventMedicacaoNegocio.obterPorId(this.eventoMedicacao.getId());
	}
}
