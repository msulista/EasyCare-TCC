package com.msulista.negocio;

import java.util.List;

public interface NegocioBase<T> {

	public String salvar(T bean);
		
	public String alterar(T bean);
	
	public List<T> obterLista();
	
	public T obterPorId(Long id);
	
}
