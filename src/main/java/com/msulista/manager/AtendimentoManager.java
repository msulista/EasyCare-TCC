package com.msulista.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

	private Atendimento atendimento;
	private AtendimentoNegocio atendimentoNegocio;
	private List<Atendimento> atendimentos;
	private Paciente paciente;

	public AtendimentoManager() {
		this.atendimento = new Atendimento();
		this.atendimentos = new ArrayList<>();
		this.atendimentoNegocio = new AtendimentoNegocio();
	}

	public String salvar() {
		this.atendimentoNegocio.salvar(this.atendimento);
		return "pretty:index";
	}

	public List<Atendimento> obterLista() {
		return this.atendimentoNegocio.obterLista();
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
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

	@URLActions(actions = { @URLAction(mappingId = "atendimento-editar", onPostback = false) })
	public void load() throws IOException {
		this.atendimento = this.atendimentoNegocio.obterPorId(this.atendimento.getId());
	}

}
