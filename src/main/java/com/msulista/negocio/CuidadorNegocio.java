package com.msulista.negocio;

import java.sql.SQLException;
import java.util.List;

import com.msulista.dao.CuidadorDao;
import com.msulista.entidade.Cuidador;

public class CuidadorNegocio implements NegocioBase<Cuidador> {

	private final CuidadorDao cuidadorDao = new CuidadorDao();

	@Override
	public String salvar(final Cuidador cuidador) {
		try {
			this.cuidadorDao.salvar(cuidador);
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String alterar(final Cuidador bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cuidador> obterLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cuidador obterPorId(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
