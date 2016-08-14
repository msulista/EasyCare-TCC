package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.CuidadorDao;
import com.msulista.entidade.Cuidador;

public class CuidadorNegocio implements NegocioBase<Cuidador>{

	private CuidadorDao cuidadorDao = new CuidadorDao();
	
	@Override
	public String salvar(Cuidador cuidador) {
		try {
			this.cuidadorDao.salvar(cuidador);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String alterar(Cuidador bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuidador> obterLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuidador obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
