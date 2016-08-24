package com.msulista.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {

	public Boolean salvar(T bean) throws SQLException;

	public Boolean alterar(T bean) throws SQLException;

	public List<T> obterLista() throws SQLException;

	public T obterEvento(Long id) throws SQLException;

	public void excluir(Long id);

}
