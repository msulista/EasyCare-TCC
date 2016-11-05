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
import com.msulista.negocio.MedicamentoNegocio;
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

	private MedicamentoNegocio medicamentoNegocio;
	private List<Medicamento> medicamentosPopulados;

	@PostConstruct
	public void inicializar() {
		this.eventoMedicacao = new EventoMedicacao();
		this.eventoMedicacoes = new ArrayList<>();
		this.eventMedicacaoNegocio = new EventoMedicacaoNegocio();
		this.scheduleModel = new DefaultScheduleModel();

		this.eventoMedicacoes = this.eventMedicacaoNegocio.obterLista();

		// this.medicamentoNegocio = new MedicamentoNegocio();
		// this.medicamentosPopulados = new ArrayList<>();

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

	/**
	 * Popula a lista de medicamentos por paciente selecionano no atendimento.
	 */
	// public void popularListaMedicamento() {
	// if (this.eventoMedicacao.getAtendimento() != null) {
	//
	// this.medicamentosPopulados.addAll(this.medicamentoNegocio
	// .obterListaPorPaciente(this.eventoMedicacao.getAtendimento().getPaciente().getId()));
	// }
	// }

	public void eventoRealizado(final EventoMedicacao evento) {
		if (evento.getStattus() == null) {
			evento.setStattus(1);
			this.realizaBaixaEstoque(evento);
			this.eventoMedicacao = evento;
			this.eventMedicacaoNegocio.alterar(this.eventoMedicacao);
			Mensagem.add("Evento realizado!!!");
		} else {
			Mensagem.add("Evento já foi realizado!!!");
		}
	}

	/**
	 * Realiza a baixa no estoque do medicamento do evento se Status igual a 1
	 * 
	 * @param evento
	 */
	private void realizaBaixaEstoque(final EventoMedicacao evento) {
		if (evento.getStattus() == 1) {
			final MedicamentoNegocio medicamentoNegocio = new MedicamentoNegocio();
			final List<Medicamento> medicamentos = evento.getMedicamentos();
			for (final Medicamento med : medicamentos) {
				med.setEstoque(med.getEstoque() - 1);
				medicamentoNegocio.alterar(med);
			}
		}
	}

	public void eventoNaoRealizado(final EventoMedicacao evento) {

		evento.setStattus(0);
		this.eventoMedicacao = evento;
		this.eventMedicacaoNegocio.alterar(this.eventoMedicacao);
		Mensagem.add("Evento não realizado!!!");
	}

	public void verificaAlertaHorario() {
		final List<EventoMedicacao> eventos = this.obterListaDiaCorrente();
		for (final EventoMedicacao eventoMedicacao : eventos) {
			final Long minuto = DateUtil.verificaHoraAlareme(eventoMedicacao.getDataHora());
			if (minuto.intValue() >= 0 && minuto.intValue() <= 5) {
				Mensagem.add("Você possui um evento agendado em " + minuto + " minutos !!!");
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
		System.out.println("Medicamento: " + medicademento.getNome());
		this.medicademento = medicademento;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(final Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Medicamento> getMedicamentosPopulados() {
		return this.medicamentosPopulados;
	}

	public void setMedicamentosPopulados(final List<Medicamento> medicamentosPopulados) {
		this.medicamentosPopulados = medicamentosPopulados;
	}

	@URLActions(actions = { @URLAction(mappingId = "eventoMedicacao-editar", onPostback = false) })
	public void load() throws IOException {
		this.eventoMedicacao = this.eventMedicacaoNegocio.obterPorId(this.eventoMedicacao.getId());
	}
}
