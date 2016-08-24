package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.Cuidador;
import com.msulista.negocio.CuidadorNegocio;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "cuidador", pattern = "/cuidador", viewId = "/pages/cuidador/cuidador-listar.xhtml"),
		@URLMapping(id = "cuidador-incluir", pattern = "/incluir", viewId = "/pages/cuidador/cuidador-incluir.xhtml", parentId = "cuidador"),
		@URLMapping(id = "cuidador-editar", pattern = "/#{cuidadorManager.cuidador.id}/editar", viewId = "/pages/cuidador/cuidador-editar.xhtml", parentId = "cuidador") })
public class CuidadorManager {

	private Cuidador cuidador;
	private CuidadorNegocio cuidadorNegocio;
	private List<Cuidador> lista;

	public CuidadorManager() {
		this.cuidador = new Cuidador();
		this.cuidadorNegocio = new CuidadorNegocio();
		this.lista = new ArrayList<>();
	}

	public String salvar() {
		this.cuidadorNegocio.salvar(this.cuidador);
		return "pretty:cuidador";
	}

	public String alterar() {
		this.cuidadorNegocio.alterar(this.cuidador);
		return "pretty:cuidador";
	}

	public List<Cuidador> obterLista() {
		return this.cuidadorNegocio.obterLista();
	}

	public Cuidador obterCuidador() {
		return this.cuidadorNegocio.obterPorId(this.cuidador.getId());
	}

	// Getters & Setters

	public Cuidador getCuidador() {
		return this.cuidador;
	}

	public void setCuidador(final Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public CuidadorNegocio getCuidadorNegocio() {
		return this.cuidadorNegocio;
	}

	public void setCuidadorNegocio(final CuidadorNegocio cuidadorNegocio) {
		this.cuidadorNegocio = cuidadorNegocio;
	}

	public List<Cuidador> getLista() {
		return this.lista;
	}

	public void setLista(final List<Cuidador> lista) {
		this.lista = lista;
	}
}
