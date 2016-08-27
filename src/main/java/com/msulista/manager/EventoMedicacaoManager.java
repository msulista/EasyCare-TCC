package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.EventoMedicacao;
import com.msulista.negocio.EventoMedicacaoNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "eventoMedicacao", pattern = "/eventoMedicacao", viewId = "/pages/eventoMedicacao/eventoMedicacao-listar.xhtml"),
		@URLMapping(id = "eventoMedicacao-incluir", pattern = "/incluir", viewId = "/pages/eventoMedicacao/eventoMedicacao-incluir.xhtml", parentId = "eventoMedicacao"),
		@URLMapping(id = "eventoMedicacao-editar", pattern = "/#{eventoMedicacaoManager.eventoMedicacao.id}/editar", viewId = "/pages/eventoMedicacao/eventoMedicacao-editar.xhtml", parentId = "eventoMedicacao") })
public class EventoMedicacaoManager {

	private EventoMedicacao eventoMedicacao;
	private List<EventoMedicacao> eventoMedicacoes;
	private EventoMedicacaoNegocio eventMedicacaoNegocio;
	
	public EventoMedicacaoManager() {
		this.eventoMedicacao = new EventoMedicacao();
		this.eventoMedicacoes = new ArrayList<>();
		this.eventMedicacaoNegocio = new EventoMedicacaoNegocio();
	}
	
	public String salvar() {
		this.eventMedicacaoNegocio.salvar(this.eventoMedicacao);
		return "pretty:eventoMedicacao";
	}

	public String alterar() {
		this.eventMedicacaoNegocio.alterar(this.eventoMedicacao);
		return "pretty:eventoMedicacao";
	}
	
	public List<EventoMedicacao> obterLista() {
		return this.eventMedicacaoNegocio.obterLista();
	}
	
	public EventoMedicacao obterEventoMedicacao() {
		return this.eventMedicacaoNegocio.obterPorId(this.eventoMedicacao.getId());
	}
	
	public void excluir() {
		this.eventMedicacaoNegocio.excluir(this.eventoMedicacao.getId());
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
	
	
}
