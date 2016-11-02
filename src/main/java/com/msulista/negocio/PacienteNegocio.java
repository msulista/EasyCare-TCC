package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.CuidadorDao;
import com.msulista.dao.PacienteDao;
import com.msulista.entidade.Cuidador;
import com.msulista.entidade.Paciente;
import com.msulista.util.Mensagem;
import com.msulista.util.SessionUtil;

public class PacienteNegocio {

	protected PacienteDao pacienteDao;
	protected CuidadorDao cuidadorDao;

	public String salvar(final Paciente paciente) {
		this.pacienteDao = new PacienteDao();
		Cuidador cui = SessionUtil.obtemUsuarioLogado();
		paciente.setCuidador(cui);
		try {
			this.pacienteDao.salvar(paciente);
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao salvar.");
			e.printStackTrace();
		}
		return null;
	}

	public String alterar(final Paciente paciente) {
		this.pacienteDao = new PacienteDao();
		try {
			this.pacienteDao.alterar(paciente);
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
			e.printStackTrace();
		}
		return null;
	}

	public Paciente obterPaciente(final Long id) {
		this.pacienteDao = new PacienteDao();
		try {
			return this.pacienteDao.obterEvento(id); 
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao obter o paciente.");
			e.printStackTrace();
		}
		return null;
	}

	public List<Paciente> obterLista() {

		this.pacienteDao = new PacienteDao();

		List<Paciente> retorno = null;
		Cuidador usuarioLogado = SessionUtil.obtemUsuarioLogado();
		try {
			retorno = this.pacienteDao.obterListaPorCuidador(usuarioLogado.getId());
		} catch (final SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}
	
	public void excluirPaciente(Paciente paciente) throws SQLException {
		this.pacienteDao = new PacienteDao();
		this.pacienteDao.excluir(paciente);
	}

}
