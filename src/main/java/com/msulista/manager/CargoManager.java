package com.msulista.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.msulista.dao.CargoService;
import com.msulista.entidade.Cargo;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@RequestScoped
@URLMappings(mappings = {
		@URLMapping(id = "cargo", pattern = "/cargo", viewId = "/pages/cargo/cargo-listar.xhtml"),
		@URLMapping(id = "cargo-incluir", pattern = "/incluir", viewId = "/pages/cargo/cargo-incluir.xhtml", parentId = "cargo")
})
public class CargoManager {

	private Cargo cargo = new Cargo();
	private CargoService service = new CargoService();
	private List<Cargo> lista;
	
	public CargoManager() {	
		populaLista();
	}
		
	public void populaLista(){
		
		lista = new ArrayList<Cargo>();
		lista = service.listarAtivos();
	}
	
	public List<Cargo> getLista() {
		return lista;
	}

	public void setLista(List<Cargo> lista) {
		this.lista = lista;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public CargoService getService() {
		return service;
	}

	public void setService(CargoService service) {
		this.service = service;
	}
	
	public String save(){
		if(service.save(cargo)){
		return "pretty:cargo";
		}else{
			return null;
		}
	}
	
	
	public List<Cargo> listarAtivos(){
		return service.listarAtivos();		
	}
	
	public String desativar(Long id){
		service.desativar(id);
		return "pretty:cargo";
	}
	
}
