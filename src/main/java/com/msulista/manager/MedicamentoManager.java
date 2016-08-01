package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.Medicamento;
import com.msulista.negocio.MedicamentoNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "medicamento", pattern = "/medicamento", viewId = "/pages/medicamento/medicamento-listar.xhtml"),
		@URLMapping(id = "medicamento-incluir", pattern = "/incluir", viewId = "/pages/medicamento/medicamento-incluir.xhtml", parentId = "medicamento"),
		@URLMapping(id = "medicamento-editar", pattern = "/#{medicamentoManager.medicamento.id}/editar", viewId = "/pages/medicamento/medicamento-editar.xhtml", parentId = "paciente")
})
public class MedicamentoManager {
	
	private Medicamento medicamento;
	private List<Medicamento> medicamentos;
	private MedicamentoNegocio medicamentoNegocio;
	
	public MedicamentoManager() {
		this.medicamento = new Medicamento();
		this.medicamentos = new ArrayList<>();
		this.medicamentoNegocio = new MedicamentoNegocio();
	}
	
	public void salvar() {
		this.medicamentoNegocio.salvar(medicamento);
	}
	
	public List<Medicamento> obterLista() {
		return this.medicamentoNegocio.obterLista();
	}

	
	
	//Getter e SEtter
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}
	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public MedicamentoNegocio getMedicamentoNegocio() {
		return medicamentoNegocio;
	}
	public void setMedicamentoNegocio(MedicamentoNegocio medicamentoNegocio) {
		this.medicamentoNegocio = medicamentoNegocio;
	}

}
