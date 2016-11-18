package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.CuidadorDao;
import com.msulista.entidade.Cuidador;
import com.msulista.util.Mensagem;

public class CuidadorNegocio implements NegocioBase<Cuidador> {

	private CuidadorDao cuidadorDao;

	@Override
	public boolean salvar(final Cuidador cuidador) {
		this.cuidadorDao = new CuidadorDao();
		try {
			this.cuidadorDao.salvar(cuidador);
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao cadastrar cuidador.");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean alterar(final Cuidador bean) {
		this.cuidadorDao = new CuidadorDao();
		try {
			Cuidador cuidadorBanco = this.cuidadorDao.obterEvento(bean.getId());
			if (bean.getSenha().isEmpty() ) {
				bean.setSenha(cuidadorBanco.getSenha());
			}
			this.cuidadorDao.alterar(bean);
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
			e.printStackTrace();
			
		}
		return true;
	}

	@Override
	public List<Cuidador> obterLista() {
		this.cuidadorDao = new CuidadorDao();
		List<Cuidador> retorno = null;
		
		try {
			retorno = this.cuidadorDao.obterLista();
		} catch (final SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno;
	}

	@Override
	public Cuidador obterPorId(final Long id) {
		this.cuidadorDao = new CuidadorDao();
		try {
			return this.cuidadorDao.obterEvento(id); 
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao obter o cuidador.");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void excluir(final Cuidador cuidador) {
		this.cuidadorDao = new CuidadorDao();
		this.cuidadorDao.excluir(cuidador);
	}

}
