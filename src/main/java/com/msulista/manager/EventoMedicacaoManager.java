package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
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
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ApplicationScoped
@URLMappings(mappings = {
		@URLMapping(id = "eventoMedicacao", pattern = "/eventoMedicacao", viewId = "/pages/eventoMedicacao/eventoMedicacao-listar.xhtml"),
		@URLMapping(id = "eventoMedicacao-incluir", pattern = "/incluir", viewId = "/pages/eventoMedicacao/eventoMedicacao-incluir.xhtml", parentId = "eventoMedicacao"),
		@URLMapping(id = "eventoMedicacao-editar", pattern = "/#{eventoMedicacaoManager.eventoMedicacao.id}/editar", viewId = "/pages/eventoMedicacao/eventoMedicacao-editar.xhtml", parentId = "eventoMedicacao") })
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
		
		for (EventoMedicacao eventoMedicacao : eventoMedicacoes) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(eventoMedicacao.getId());
			evt.setTitle(eventoMedicacao.getTitulo());
			evt.setStartDate(eventoMedicacao.getDataHora());
			evt.setEndDate(eventoMedicacao.getDataHora());
			evt.setDescription(eventoMedicacao.getDescricao());
			evt.setAllDay(false);
			evt.setEditable(true);
//			evt.setStyleClass("event-green");			
			
			scheduleModel.addEvent(evt);
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
	
	public String editar(EventoMedicacao evento) {
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
	
	public String excluir(EventoMedicacao evento) {
		this.eventMedicacaoNegocio.excluir(evento);
		return "pretty:eventoMedicacao";
	}
	
	public String eventoRealizado(EventoMedicacao evento) {
		
		evento.setStattus(1);
		this.eventoMedicacao = evento;
		this.eventMedicacaoNegocio.alterar(eventoMedicacao);
		
		return "pretty:eventoMedicacao";
	}
	
	public String eventoNaoRealizado(EventoMedicacao evento) {
		
		evento.setStattus(0);
		this.eventoMedicacao = evento;
		this.eventMedicacaoNegocio.alterar(eventoMedicacao);
		
		return "pretty:eventoMedicacao";
	}
	
	
	public void verificaAlertaHorario() {
		List<EventoMedicacao> eventos = this.obterListaDiaCorrente();
		for (EventoMedicacao eventoMedicacao : eventos) {
			Long minuto = DateUtil.verificaHoraAlareme(eventoMedicacao.getDataHora()); 
			if (minuto.intValue() <= 1) {
				Mensagem.add("Voc� possui um evento agendado agora!!!");
			}			
		}		
	}
	
	/**
	 * @return the eventoMedicacao
	 */
	public EventoMedicacao getEventoMedicacao() {
		return eventoMedicacao;
	}

	/**
	 * @param eventoMedicacao the eventoMedicacao to set
	 */
	public void setEventoMedicacao(EventoMedicacao eventoMedicacao) {
		this.eventoMedicacao = eventoMedicacao;
	}

	/**
	 * @return the eventoMedicacoes
	 */
	public List<EventoMedicacao> getEventoMedicacoes() {
		return eventoMedicacoes;
	}

	/**
	 * @param eventoMedicacoes the eventoMedicacoes to set
	 */
	public void setEventoMedicacoes(List<EventoMedicacao> eventoMedicacoes) {
		this.eventoMedicacoes = eventoMedicacoes;
	}

	/**
	 * @return the eventMedicacaoNegocio
	 */
	public EventoMedicacaoNegocio getEventMedicacaoNegocio() {
		return eventMedicacaoNegocio;
	}

	/**
	 * @param eventMedicacaoNegocio the eventMedicacaoNegocio to set
	 */
	public void setEventMedicacaoNegocio(EventoMedicacaoNegocio eventMedicacaoNegocio) {
		this.eventMedicacaoNegocio = eventMedicacaoNegocio;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	/**
	 * @return the medicademento
	 */
	public Medicamento getMedicademento() {
		return medicademento;
	}

	/**
	 * @param medicademento the medicademento to set
	 */
	public void setMedicademento(Medicamento medicademento) {
		this.medicademento = medicademento;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}
	
}
