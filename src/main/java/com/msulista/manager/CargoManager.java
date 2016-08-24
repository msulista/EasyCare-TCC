package com.msulista.manager;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.msulista.dao.CargoService;
import com.msulista.entidade.Cargo;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@RequestScoped
@URLMappings(mappings = { @URLMapping(id = "cargo", pattern = "/cargo", viewId = "/pages/cargo/cargo-listar.xhtml"),
		@URLMapping(id = "cargo-incluir", pattern = "/incluir", viewId = "/pages/cargo/cargo-incluir.xhtml", parentId = "cargo") })
public class CargoManager {

	private Cargo cargo = new Cargo();
	private CargoService service = new CargoService();
	private List<Cargo> lista;

	public CargoManager() {
		this.populaLista();
	}

	public void populaLista() {

		this.lista = new ArrayList<>();
		this.lista = this.service.listarAtivos();
	}

	public List<Cargo> getLista() {
		return this.lista;
	}

	public void setLista(final List<Cargo> lista) {
		this.lista = lista;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(final Cargo cargo) {
		this.cargo = cargo;
	}

	public CargoService getService() {
		return this.service;
	}

	public void setService(final CargoService service) {
		this.service = service;
	}

	public String save() {
		if (this.service.save(this.cargo)) {
			return "pretty:cargo";
		} else {
			return null;
		}
	}

	public List<Cargo> listarAtivos() {
		return this.service.listarAtivos();
	}

	public String desativar(final Long id) {
		this.service.desativar(id);
		return "pretty:cargo";
	}

}
