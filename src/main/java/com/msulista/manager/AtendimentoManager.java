package com.msulista.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.crypto.AEADBadTagException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

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
		@URLMapping(id = "atendimento-editar", pattern = "/#{atendimentoManager.atendimento.id}/editar", viewId = "/pages/atendimento/atendimento-editar.xhtml", parentId = "atendimento")
})
public class AtendimentoManager {
	
	private Atendimento atendimento;
	private AtendimentoNegocio atendimentoNegocio = new AtendimentoNegocio();
	private List<Atendimento> atendimentos;
	private Paciente paciente;

	public AtendimentoManager() {		
		atendimento = new Atendimento();
		atendimentos = new ArrayList<>();
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public AtendimentoNegocio getAtendimentoNegocio() {
		return atendimentoNegocio;
	}

	public void setAtendimentoNegocio(AtendimentoNegocio atendimentoNegocio) {
		this.atendimentoNegocio = atendimentoNegocio;
	}
		
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String salvar() {
		this.atendimentoNegocio.salvar(atendimento);
		return "pretty:index";
	}
	
	public List<Atendimento> obterLista() {
		return this.atendimentoNegocio.obterLista();
	}
	
	@URLActions(actions = { @URLAction(mappingId = "atendimento-editar", onPostback = false) })
	public void load() throws IOException {
		atendimento = atendimentoNegocio.obterPorId(atendimento.getId());
	}
	
}
