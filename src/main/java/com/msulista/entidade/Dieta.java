package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

public class Dieta implements BaseEntity, Serializable{
		
	private static final long serialVersionUID = -8708257484929430757L;
	
	private Long id;
	
	private String nome;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "evento_medicamento", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "diet_id") })
	private List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dieta other = (Dieta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
