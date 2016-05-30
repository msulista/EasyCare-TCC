package com.msulista.negocio;

import java.util.List;

public interface NegocioBase<T> {

	public void gravar(T bean);
		
	public void alterar(T bean);
	
	public List<T> obterLista();
	
	public T obterPorId(Long id);
	
}
