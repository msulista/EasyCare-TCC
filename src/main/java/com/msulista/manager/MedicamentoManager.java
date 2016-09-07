package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.Medicamento;
import com.msulista.negocio.MedicamentoNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ApplicationScoped
@URLMappings(mappings = {
		@URLMapping(id = "medicamento", pattern = "/medicamento", viewId = "/pages/medicamento/medicamento-listar.xhtml"),
		@URLMapping(id = "medicamento-incluir", pattern = "/incluir", viewId = "/pages/medicamento/medicamento-incluir.xhtml", parentId = "medicamento"),
		@URLMapping(id = "medicamento-editar", pattern = "/#{medicamentoManager.medicamento.id}/editar", viewId = "/pages/medicamento/medicamento-editar.xhtml", parentId = "medicamento") })
public class MedicamentoManager {

	private Medicamento medicamento;
	private List<Medicamento> medicamentos;
	private MedicamentoNegocio medicamentoNegocio;

	public MedicamentoManager() {
		this.medicamento = new Medicamento();
		this.medicamentos = new ArrayList<>();
		this.medicamentoNegocio = new MedicamentoNegocio();
	}

	public String salvar() {
		this.medicamentoNegocio.salvar(this.medicamento);
		return "pretty:medicamento";
	}

	public String alterar() {
		this.medicamentoNegocio.alterar(this.medicamento);
		return "pretty:medicamento";
	}
	
	public String editar(Medicamento medicamentoEdit) {
		this.medicamento = medicamentoEdit;
		return "pretty:medicamento-editar";
	}

	public List<Medicamento> obterLista() {
		return this.medicamentoNegocio.obterLista();
	}

	public Medicamento obterMedicamento() {
		return this.medicamentoNegocio.obterPorId(this.medicamento.getId());
	}
	
	public String excluir(Medicamento medicamento) {
		this.medicamentoNegocio.excluir(medicamento);
		return "pretty:medicamento";
	}

	// Getter e SEtter
	public Medicamento getMedicamento() {
		return this.medicamento;
	}

	public void setMedicamento(final Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(final List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public MedicamentoNegocio getMedicamentoNegocio() {
		return this.medicamentoNegocio;
	}

	public void setMedicamentoNegocio(final MedicamentoNegocio medicamentoNegocio) {
		this.medicamentoNegocio = medicamentoNegocio;
	}

}
