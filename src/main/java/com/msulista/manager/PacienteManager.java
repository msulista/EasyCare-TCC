package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.Paciente;
import com.msulista.negocio.PacienteNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "paciente", pattern = "/paciente", viewId = "/pages/paciente/paciente-listar.xhtml"),
		@URLMapping(id = "paciente-incluir", pattern = "/incluir", viewId = "/pages/paciente/paciente-incluir.xhtml", parentId = "paciente"),
		@URLMapping(id = "paciente-editar", pattern = "/#{pacienteManager.paciente.id}/editar", viewId = "/pages/paciente/paciente-editar.xhtml", parentId = "paciente") })
public class PacienteManager {

	private Paciente paciente;
	private List<Paciente> pacientes;
	private PacienteNegocio pacienteNegocio;

	public PacienteManager() {
		this.paciente = new Paciente();
		this.pacientes = new ArrayList<>();
		this.pacienteNegocio = new PacienteNegocio();
	}

	/**
	 * Cadastra paciente
	 *
	 */
	public String salvar() {
		this.pacienteNegocio.salvar(this.paciente);
		return "pretty:paciente";
	}

	public String alterar() {
		this.pacienteNegocio.alterar(this.paciente);
		return "pretty:paciente";
	}

	public Paciente obterPaciente(final Long id) {
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

	// Servi�os
	// public Paciente findPacientebyNome(String nome) {
	// for (Paciente paciente : pacientes) {
	// if (paciente.getNomePaciente().equalsIgnoreCase(nome)) {
	// return paciente;
	// }
	// }
	// return null;
	// }

	// Getters Setters
	public List<Paciente> getPacientes() {
		return this.pacientes;
	}

	public void setPacientes(final List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	public PacienteNegocio getPacienteNegocio() {
		return this.pacienteNegocio;
	}

	public void setPacienteNegocio(final PacienteNegocio pacienteNegocio) {
		this.pacienteNegocio = pacienteNegocio;
	}

}
