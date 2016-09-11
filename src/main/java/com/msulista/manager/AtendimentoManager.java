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
import com.msulista.entidade.Paciente;
import com.msulista.negocio.AtendimentoNegocio;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "atendimento", pattern = "/atendimento", viewId = "/pages/atendimento/atendimento-listar.xhtml"),
		@URLMapping(id = "atendimento-incluir", pattern = "/incluir", viewId = "/pages/atendimento/atendimento-incluir.xhtml", parentId = "atendimento"),
		@URLMapping(id = "atendimento-editar", pattern = "/#{atendimentoManager.atendimento.id}/editar", viewId = "/pages/atendimento/atendimento-editar.xhtml", parentId = "atendimento") })
public class AtendimentoManager {

	private ScheduleModel scheduleModel;
	private Atendimento atendimento;
	private AtendimentoNegocio atendimentoNegocio;
	private List<Atendimento> atendimentos;
	private Paciente paciente;
	
	@PostConstruct
	public void inicializar() {
		this.atendimento = new Atendimento();
		this.atendimentos = new ArrayList<>();
		this.atendimentoNegocio = new AtendimentoNegocio();
		this.scheduleModel = new DefaultScheduleModel();
		
		this.atendimentos = this.atendimentoNegocio.obterLista();
		
		for (Atendimento atendimento : atendimentos) {
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(atendimento.getId());
			evt.setTitle(atendimento.getPaciente().getNomePaciente());
			evt.setStartDate(atendimento.getDataInicial());
			evt.setEndDate(atendimento.getDataFinal());
			evt.setAllDay(false);
			evt.setEditable(true);
//			evt.setStyleClass("event-green");			
			
			scheduleModel.addEvent(evt);
		}
	}
		
	
	/**
	 * Salva novo {@link Atendimento}
	 * @return
	 */
	public String salvar() {
		
		if (this.atendimentoNegocio.salvar(this.atendimento)) {
			return "pretty:atendimento";
		}
		return null;
	}
	
	/**
	 * Altera os dados de um {@link Atendimento}
	 */
	public String aterar() {
		if (this.atendimentoNegocio.alterar(this.atendimento)) {
			return "pretty:atendimento";
		}
		return null;
	}
	
	public String editar(Atendimento atendimentoEdit) {
		this.atendimento = atendimentoEdit;
		return "pretty:atendimento-editar";
	}

	/**
	 * Obtem lista de todos os {@link Atendimento}
	 * @return {@link List} {@link Atendimento}
	 */
	public List<Atendimento> obterLista() {
		
		return this.atendimentoNegocio.obterLista();
	}
	
	/**
	 * Remove o {@link Atendimento} do banco
	 */
	public String excluirAtendimento(Atendimento atendimentoExcluir) {
		this.atendimentoNegocio.excluir(atendimentoExcluir);
		return "pretty:atendimento";
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}
	
	/**
	 * Obtem atendimento por Id
	 * @return
	 */
	public Atendimento obtemAtendimento() {
		return this.atendimentoNegocio.obterPorId(this.atendimento.getId());
	}

	public void setAtendimento(final Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public AtendimentoNegocio getAtendimentoNegocio() {
		return this.atendimentoNegocio;
	}

	public void setAtendimentoNegocio(final AtendimentoNegocio atendimentoNegocio) {
		this.atendimentoNegocio = atendimentoNegocio;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(final List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}	

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}


	@URLActions(actions = { @URLAction(mappingId = "atendimento-editar", onPostback = false) })
	public void load() throws IOException {
		this.atendimento = this.atendimentoNegocio.obterPorId(this.atendimento.getId());
	}

}
