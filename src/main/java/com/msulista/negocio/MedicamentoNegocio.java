package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.MedicamentoDao;
import com.msulista.entidade.Medicamento;

public class MedicamentoNegocio implements NegocioBase<Medicamento>{

	private MedicamentoDao medicamentoDao;
	
	@Override
	public String salvar(Medicamento medicamento) {
		this.medicamentoDao = new MedicamentoDao();
		try {
			this.medicamentoDao.salvar(medicamento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String alterar(Medicamento bean) {

		try {
			this.medicamentoDao.alterar(bean);
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		
		return null;
		
	}

	@Override
	public List<Medicamento> obterLista() {
		
		this.medicamentoDao = new MedicamentoDao();
		List<Medicamento> retorno = null;
		try {
			retorno = this.medicamentoDao.obterLista();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}

	@Override
	public Medicamento obterPorId(Long id) {

		Medicamento medicamento = null;
		try {
			medicamento = this.medicamentoDao.obterEvento(id);
		} catch (SQLException e) {
//			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		
		return medicamento;
	}

}
