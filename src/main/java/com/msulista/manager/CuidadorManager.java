package com.msulista.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.msulista.entidade.Cuidador;
import com.msulista.negocio.CuidadorNegocio;
import com.msulista.util.Mensagem;
import com.msulista.util.SessionUtil;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "cuidador", pattern = "/cuidador", viewId = "/pages/usuario/cuidador/cuidador-listar.xhtml"),
		@URLMapping(id = "cuidador-incluir", pattern = "/incluir", viewId = "/pages/cuidador/cuidador-incluir.xhtml", parentId = "cuidador"),
		@URLMapping(id = "cuidador-editar", pattern = "/#{cuidadorManager.cuidador}/editar", viewId = "/pages/usuario/cuidador/cuidador-editar.xhtml", parentId = "cuidador") })
public class CuidadorManager {

	private Cuidador cuidador;
	private CuidadorNegocio cuidadorNegocio;
	private List<Cuidador> lista;
	private Cuidador cuidadorLogado;

	public CuidadorManager() {
		this.cuidador = new Cuidador();
		this.cuidadorNegocio = new CuidadorNegocio();
		this.lista = new ArrayList<>();
		this.cuidadorLogado = SessionUtil.obtemUsuarioLogado();
	}

	public String salvar() {
		this.cuidadorNegocio.salvar(this.cuidador);
		Mensagem.add("Cadastro realizado com sucesso.");
		return "/pages/home/inicial.xhtml";
	}

	public String alterar() {
		this.cuidadorNegocio.alterar(this.cuidador);
		return "/pages/usuario/home/index.xhtml";
	}

	public List<Cuidador> obterLista() {
		return this.cuidadorNegocio.obterLista();
	}

	public Cuidador obterCuidador() {
		return this.cuidadorNegocio.obterPorId(this.cuidador.getId());
	}

	// Getters & Setters

	public Cuidador getCuidador() {
		if (this.cuidadorLogado != null) {
			this.cuidador = this.cuidadorLogado;
		}
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

	public Cuidador getCuidadorLogado() {
		return this.cuidadorLogado;
	}

	public void setCuidadorLogado(final Cuidador cuidadorLogado) {
		this.cuidadorLogado = cuidadorLogado;
	}

	@URLActions(actions = { @URLAction(mappingId = "cuidador-editar", onPostback = false) })
	public void load() throws IOException {
		this.cuidador = this.cuidadorNegocio.obterPorId(this.cuidador.getId());
	}

}
