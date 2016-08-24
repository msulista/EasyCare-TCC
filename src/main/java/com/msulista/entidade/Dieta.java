package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dieta implements BaseEntity, Serializable {

	private static final long serialVersionUID = -8708257484929430757L;

	private Long id;

	private String nome;

	// @ManyToMany(fetch = FetchType.LAZY)
	// @JoinTable(name = "evento_medicamento", joinColumns = { @JoinColumn(name
	// = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "diet_id") })
	private final List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Dieta other = (Dieta) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
