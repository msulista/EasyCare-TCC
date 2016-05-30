package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.PacienteDao;
import com.msulista.entidade.Paciente;

public class PacienteNegocio {

	private PacienteDao pacienteDao;

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
