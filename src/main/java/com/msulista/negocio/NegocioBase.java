package com.msulista.negocio;

import java.util.List;

public interface NegocioBase<T> {

	/**
	 * Salva os dados no banco
	 * 
	 * @param bean � a entidade que representa
	 * @return
	 */
	public boolean salvar(T bean);

	public boolean alterar(T bean);

	public List<T> obterLista();

	public T obterPorId(Long id);
	
	public void excluir(T bean);

}
