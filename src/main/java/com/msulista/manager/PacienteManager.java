package com.msulista.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.msulista.entidade.Paciente;
import com.msulista.negocio.PacienteNegocio;
import com.msulista.util.DateUtil;
import com.msulista.util.EmailUtil;
import com.msulista.util.Mensagem;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "paciente", pattern = "/paciente", viewId = "/pages/usuario/paciente/paciente-listar.xhtml"),
		@URLMapping(id = "paciente-incluir", pattern = "/incluir", viewId = "/pages/usuario/paciente/paciente-incluir.xhtml", parentId = "paciente"),
		@URLMapping(id = "paciente-editar", pattern = "/#{pacienteManager.paciente.id}/editar", viewId = "/pages/usuario/paciente/paciente-editar.xhtml", parentId = "paciente") })
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

	/**
	 * Editar
	 */

	public String alterar() {
		this.pacienteNegocio.alterar(this.paciente);
		return "pretty:paciente";
	}

	public String editar(final Paciente editaPaciente) {
		this.paciente = editaPaciente;
		return "pretty:paciente-editar";
	}

	public Paciente obterPaciente(final Long id) {
		return this.pacienteNegocio.obterPaciente(id);
	}

	public String excluirPaciente(final Paciente paciente) {
		try {
			this.pacienteNegocio.excluirPaciente(paciente);
		} catch (final SQLException e) {
			Mensagem.add("Erro ao conectar com o banco de dados!!!");
		}
		return "pretty:paciente";
	}

	/**
	 * Obtem lista de pacientes
	 *
	 * @return lista de {@link Paciente}
	 */
	public List<Paciente> obterLista() {

		final List<Paciente> pacientes = this.pacienteNegocio.obterLista();
		this.adicionaIdadePaciente(pacientes);
		return pacientes;
	}

	/**
	 * Envia relatorio de atendimento ao familiar do paciente
	 *
	 * @param paciente
	 */
	public void enviarRealatorio(final Paciente paciente) {

		final String email = paciente.getEmailFamiliar();

		if (StringUtils.isNotBlank(email)) {
			EmailUtil.sendEmail(email);
		}

		Mensagem.add("Relatório enviado com sucesso.");

	}

	/**
	 * Percorre lista de {@link Paciente} e adiciona idade no Transiente
	 *
	 * @param pacientes
	 */
	private void adicionaIdadePaciente(final List<Paciente> pacientes) {
		for (final Paciente paciente : pacientes) {
			if (paciente.getDtNascimento() != null) {

				final int idade = DateUtil.getIdade(paciente.getDtNascimento());
				paciente.setTransientIdade(idade);
			}
		}
	}

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

	@URLActions(actions = { @URLAction(mappingId = "paciente-editar", onPostback = false) })
	public void load() throws IOException {
		this.paciente = this.pacienteNegocio.obterPaciente(this.paciente.getId());
	}

}
