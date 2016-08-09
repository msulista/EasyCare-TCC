package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.PacienteDao;
import com.msulista.entidade.Paciente;
import com.msulista.util.Mensagem;

public class PacienteNegocio {

	protected PacienteDao pacienteDao;
	
//	public void gravar(Paciente paciente) {
//		
//		if (paciente.getId() == null) {
//				
//			this.salvar(paciente);
//		}else {
//				
//			this.alterar(paciente);
//		}
//	}
	
	public String salvar(Paciente paciente) {	
		this.pacienteDao = new PacienteDao();
		try {
			pacienteDao.salvar(paciente);
			//Mensagem.add("Cadastro realizado com sucesso.");
			return "pretty:paciente";
		} catch (SQLException e) {
			Mensagem.add("Ocorreu um erro ao salvar.");
			e.printStackTrace();
		}
		return null;
	}
	
	public String alterar(Paciente paciente) {
		this.pacienteDao = new PacienteDao();
		try {
			this.pacienteDao.alterar(paciente);
			return "pretty:paciente";
		} catch (SQLException e) {
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
			e.printStackTrace();
		}
		return null;
	}

	public Paciente obterPaciente(Long id) {
		
		try {
			return this.pacienteDao.obterEvento(id);
		} catch (SQLException e) {
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Paciente> obterLista() {

		this.pacienteDao = new PacienteDao();

		List<Paciente> retorno = null;
		try {
			retorno = this.pacienteDao.obterLista();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}
	


}
