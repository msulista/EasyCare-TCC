package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.MedicamentoDao;
import com.msulista.entidade.Cuidador;
import com.msulista.entidade.Medicamento;
import com.msulista.util.SessionUtil;

public class MedicamentoNegocio implements NegocioBase<Medicamento> {

	private MedicamentoDao medicamentoDao;

	@Override
	public boolean salvar(final Medicamento medicamento) {
		this.medicamentoDao = new MedicamentoDao();
		try {
			this.medicamentoDao.salvar(medicamento);
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean alterar(final Medicamento bean) {
		this.medicamentoDao = new MedicamentoDao();
		try {
			this.medicamentoDao.alterar(bean);
		} catch (final SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}

		return true;

	}

	@Override
	public List<Medicamento> obterLista() {

		this.medicamentoDao = new MedicamentoDao();
		List<Medicamento> retorno = null;
		
		Cuidador usuarioLogado = SessionUtil.obtemUsuarioLogado();
		try {
			retorno = this.medicamentoDao.obterListaPorCuidador(usuarioLogado.getId());
		} catch (final SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}
	
	public List<Medicamento> obterListaPorPaciente(Long id) {

		this.medicamentoDao = new MedicamentoDao();
		List<Medicamento> retorno = null;
		try {
			retorno = this.medicamentoDao.obterListaPorPaciente(id);
		} catch (final SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}

	@Override
	public Medicamento obterPorId(final Long id) {
		this.medicamentoDao = new MedicamentoDao();
		Medicamento medicamento = null;
		try {
			medicamento = this.medicamentoDao.obterEvento(id);
		} catch (final SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}

		return medicamento;
	}

	@Override
	public void excluir(Medicamento medicamento) {
		this.medicamentoDao = new MedicamentoDao();
		this.medicamentoDao.excluir(medicamento);		
	}

}
