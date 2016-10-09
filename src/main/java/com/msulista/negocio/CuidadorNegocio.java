package com.msulista.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.msulista.dao.CuidadorDao;
import com.msulista.entidade.Cuidador;

public class CuidadorNegocio implements NegocioBase<Cuidador> {

	private final CuidadorDao cuidadorDao = new CuidadorDao();

	@Override
	public boolean salvar(final Cuidador cuidador) {
		try {
			this.cuidadorDao.salvar(cuidador);
		} catch (final SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean alterar(final Cuidador bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cuidador> obterLista() {
		// TODO Auto-generated method stub
		final List<Cuidador> cuidadores = new ArrayList<>();
		final Cuidador cuidador = new Cuidador();
		cuidador.setCpf("82707529087");
		cuidador.setEmail("marcus.rodrigues81@gmail.com");
		cuidadores.add(cuidador);
		return cuidadores;
	}

	@Override
	public Cuidador obterPorId(final Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(final Cuidador cuidador) {
		// TODO Auto-generated method stub

	}

}
