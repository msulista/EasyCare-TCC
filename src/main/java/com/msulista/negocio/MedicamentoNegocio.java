package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.MedicamentoDao;
import com.msulista.entidade.Medicamento;
import com.msulista.util.Mensagem;

public class MedicamentoNegocio implements NegocioBase<Medicamento>{

	private MedicamentoDao medicamentoDao;
	
	@Override
	public void gravar(Medicamento bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(Medicamento bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Medicamento> obterLista() {
		
		this.medicamentoDao = new MedicamentoDao();
		try {
			return this.medicamentoDao.obterLista();
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
		}
		return null;
	}

	@Override
	public Medicamento obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
