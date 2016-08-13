package com.msulista.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.msulista.entidade.Dieta;
import com.msulista.entidade.Medicamento;
import com.msulista.entidade.Paciente;
import com.msulista.negocio.PacienteNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "paciente", pattern = "/paciente", viewId = "/pages/paciente/paciente-listar.xhtml"),
		@URLMapping(id = "paciente-incluir", pattern = "/incluir", viewId = "/pages/paciente/paciente-incluir.xhtml", parentId = "paciente"),
		@URLMapping(id = "paciente-editar", pattern = "/#{pacienteManager.paciente.id}/editar", viewId = "/pages/paciente/paciente-editar.xhtml", parentId = "paciente")
})
public class PacienteManager {
	
	private Paciente paciente;
	private List<Paciente> pacientes;
	private PacienteNegocio pacienteNegocio;
	
	public PacienteManager() {
		super();
		this.paciente = new Paciente();
		this.pacientes = new ArrayList<>();
		this.pacienteNegocio = new PacienteNegocio();
	}
	
	/**
	 * Cadastra paciente
	 * 
	 */
	public String salvar() {
		this.pacienteNegocio.salvar(paciente);
		return "pretty:paciente";
	}
	
	public String alterar() {
		this.pacienteNegocio.alterar(paciente);
		return "pretty:paciente";
	}
	public Paciente obterPaciente(Long id) {
		return this.pacienteNegocio.obterPaciente(id);
	}
	
	/**
	 * Obtem lista de pacientes
	 * 
	 * @return lista de {@link Paciente}
	 */
	public List<Paciente> obterLista() {
		return this.pacienteNegocio.obterLista();
	}

	//Serviços
//	public Paciente findPacientebyNome(String nome) {
//		for (Paciente paciente : pacientes) {
//			if (paciente.getNomePaciente().equalsIgnoreCase(nome)) {
//				return paciente;
//			}
//		}
//		return null;
//	}
	
	//Getters Setters
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public PacienteNegocio getPacienteNegocio() {
		return pacienteNegocio;
	}

	public void setPacienteNegocio(PacienteNegocio pacienteNegocio) {
		this.pacienteNegocio = pacienteNegocio;
	}
	
	

	
}
