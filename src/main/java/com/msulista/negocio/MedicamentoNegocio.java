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
	public void salvar(Medicamento medicamento) {
		this.medicamentoDao = new MedicamentoDao();
		try {
			this.medicamentoDao.salvar(medicamento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void alterar(Medicamento bean) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
