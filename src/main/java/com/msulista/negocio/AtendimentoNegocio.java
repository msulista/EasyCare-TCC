package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.AtendimentoDao;
import com.msulista.entidade.Atendimento;
import com.msulista.util.Mensagem;

public class AtendimentoNegocio implements NegocioBase<Atendimento> {

	private AtendimentoDao atendimentoDao;

	@Override
	public String salvar(final Atendimento bean) {

		this.atendimentoDao = new AtendimentoDao();

		try {
			this.atendimentoDao.salvar(bean);
		} catch (final SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return null;
	}

	@Override
	public String alterar(final Atendimento atendimento) {
		
		try {
			this.atendimentoDao.alterar(atendimento);
		} catch (SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return null;
	}

	@Override
	public List<Atendimento> obterLista() {
		this.atendimentoDao = new AtendimentoDao();
		try {
			return this.atendimentoDao.obterLista();
		} catch (final SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return null;
	}

	@Override
	public Atendimento obterPorId(final Long id) {
		
		Atendimento atendimento = null;
		try {
			atendimento = this.atendimentoDao.obterEvento(id);
		} catch (SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return atendimento;
	}

	@Override
	public void excluir(Long id) {
		
		this.atendimentoDao.excluir(id);
	}

}
