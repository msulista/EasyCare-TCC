package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.AtendimentoDao;
import com.msulista.entidade.Atendimento;
import com.msulista.entidade.Paciente;

public class AtendimentoNegocio implements NegocioBase<Atendimento>{

	private AtendimentoDao atendimentoDao;
	
	@Override
	public String salvar(Atendimento bean) {
		
		Paciente paci = new Paciente();
		bean.setPaciente(paci);
		
		try {
			this.atendimentoDao.salvar(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(Atendimento bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atendimento> obterLista() {
		this.atendimentoDao = new AtendimentoDao();
		try {
			return this.atendimentoDao.obterLista();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Atendimento obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
