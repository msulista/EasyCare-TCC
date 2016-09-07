package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.AtendimentoDao;
import com.msulista.entidade.Atendimento;
import com.msulista.util.DateUtil;
import com.msulista.util.Mensagem;

public class AtendimentoNegocio implements NegocioBase<Atendimento> {

	private AtendimentoDao atendimentoDao;

	@Override
	public boolean salvar(final Atendimento atendimento) {

		boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(), atendimento.getDataFinal());
		
		if (datas) {
			this.atendimentoDao = new AtendimentoDao();
			try {
				this.atendimentoDao.salvar(atendimento);
			} catch (final SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		}else {
			Mensagem.add("Data final não pode ser anterior a data inicial.");
		}
		return false;
	}

	@Override
	public boolean alterar(final Atendimento atendimento) {
		
		boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(), atendimento.getDataFinal());
		if (datas) {
			try {
				this.atendimentoDao.alterar(atendimento);
			} catch (SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		}else {
			Mensagem.add("Data final não pode ser anterior a data inicial.");
		}
		return false;
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
	public void excluir(Atendimento atendimento) {
		this.atendimentoDao = new AtendimentoDao();
		this.atendimentoDao.excluir(atendimento);
	}
	

}
