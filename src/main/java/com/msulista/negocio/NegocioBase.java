package com.msulista.negocio;

import java.util.List;

public interface NegocioBase<T> {

	/**
	 * Salva os dados no banco
	 * 
	 * @param bean é a entidade que representa
	 * @return
	 */
	public String salvar(T bean);

	public String alterar(T bean);

	public List<T> obterLista();

	public T obterPorId(Long id);
	
	public void excluir(Long id);

}
