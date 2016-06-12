package com.msulista.manager;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.msulista.entidade.EventoAtendimento;
import com.msulista.entidade.Medicamento;
import com.msulista.entidade.Paciente;
import com.msulista.negocio.AgendaNegocio;
import com.msulista.negocio.MedicamentoNegocio;
import com.msulista.negocio.PacienteNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean(name = "agendaManager")
@ViewScoped
@URLMappings(mappings = { @URLMapping(id = "agenda", pattern = "/agenda", viewId = "/pages/agenda/agenda.xhtml") })
public class AgendaManager implements Serializable {

	private static final long serialVersionUID = -4557100553498435618L;

	private ScheduleModel eventModel;
	private AgendaNegocio agendaNegocio;
	private PacienteNegocio pacienteNegocio;
	private MedicamentoNegocio medicamentoNegocio;
	private EventoAtendimento eventoAtendimento;
	private List<Paciente> pacientes;
	private List<Medicamento> medicamentos;
	private List<EventoAtendimento> eventos;

	@PostConstruct
	public void inicializar() {
		this.agendaNegocio = new AgendaNegocio();
		this.pacienteNegocio = new PacienteNegocio();
		this.medicamentoNegocio = new MedicamentoNegocio();
		this.eventoAtendimento = new EventoAtendimento();
		this.eventModel = new DefaultScheduleModel();

		this.eventos = this.agendaNegocio.obterLista();

		for (EventoAtendimento evento : eventos) {

			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(evento.getId());
			evt.setTitle(evento.getTitulo());
			evt.setStartDate(evento.getDataInicio());
			evt.setEndDate(evento.getDataFim());
			evt.setDescription(evento.getDescricao());
			evt.setAllDay(false);
			evt.setEditable(true);
			evt.setStyleClass("event-green");

			eventModel.addEvent(evt);
		}
	}

	public void quandoSelecionado(SelectEvent selectEvent) {

		ScheduleEvent eventoSelecionado = (ScheduleEvent) selectEvent
				.getObject();

		for (EventoAtendimento ev : eventos) {
			if (ev.getId() == (Long) eventoSelecionado.getData()) {
				this.eventoAtendimento = ev;
				break;
			}
		}
	}

	public void quandoNovo(SelectEvent selectEvent) {

		eventoAtendimento = new EventoAtendimento();
		this.pacienteNegocio = new PacienteNegocio();
		this.medicamentoNegocio = new MedicamentoNegocio();
		this.pacientes = this.pacienteNegocio.obterLista();
		this.medicamentos = this.medicamentoNegocio.obterLista();

		ScheduleEvent novoEvento = new DefaultScheduleEvent("",
				(Date) selectEvent.getObject(), (Date) selectEvent.getObject());

		eventoAtendimento.setDataInicio(new java.sql.Date(novoEvento
				.getStartDate().getTime()));
		eventoAtendimento.setDataFim(new java.sql.Date(novoEvento.getEndDate()
				.getTime()));
	}

	public void gravar() {

		this.agendaNegocio.gravar(eventoAtendimento);
		this.inicializar();
		eventoAtendimento = new EventoAtendimento();
	}

	public void quandoMovido(ScheduleEntryMoveEvent evSelect) {

		for (EventoAtendimento ev : eventos) {
			if (ev.getId() == (Long) evSelect.getScheduleEvent().getData()) {
				this.eventoAtendimento = ev;
				this.agendaNegocio.gravar(eventoAtendimento);
				this.inicializar();
				break;
			}
		}
	}

	public void quandoRedimencionado(ScheduleEntryResizeEvent evSelect) {

		for (EventoAtendimento ev : eventos) {
			if (ev.getId() == (Long) evSelect.getScheduleEvent().getData()) {
				this.eventoAtendimento = ev;
				this.agendaNegocio.gravar(eventoAtendimento);
				this.inicializar();
				break;
			}
		}
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public AgendaNegocio getAgendaNegocio() {
		return agendaNegocio;
	}

	public void setAgendaNegocio(AgendaNegocio agendaNegocio) {
		this.agendaNegocio = agendaNegocio;
	}

	public PacienteNegocio getPacienteNegocio() {
		return pacienteNegocio;
	}

	public void setPacienteNegocio(PacienteNegocio pacienteNegocio) {
		this.pacienteNegocio = pacienteNegocio;
	}

	public MedicamentoNegocio getMedicamentoNegocio() {
		return medicamentoNegocio;
	}

	public void setMedicamentoNegocio(MedicamentoNegocio medicamentoNegocio) {
		this.medicamentoNegocio = medicamentoNegocio;
	}

	public EventoAtendimento getEventoAtendimento() {
		return eventoAtendimento;
	}

	public void setEventoAtendimento(EventoAtendimento eventoAtendimento) {
		this.eventoAtendimento = eventoAtendimento;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<EventoAtendimento> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoAtendimento> eventos) {
		this.eventos = eventos;
	}

}
