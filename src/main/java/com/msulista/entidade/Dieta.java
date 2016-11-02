package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class Dieta implements BaseEntity, Serializable {

	private static final long serialVersionUID = -8708257484929430757L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diet_id")
	private Long id;

	@Column(name = "diet_nome")
	private String nome;
	
	@Column(name = "diet_porcao")
	private String porcao;
	
	@Column(name = "diet_descricao")
	private String descricao;
	
	@Column(name = "diet_tp_refeicao")
	private Integer tipoRefeicao;
	
	@Column(name = "diet_vl_nutri")
	private Double valorNutricional;

	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dieta_has_evento_medicacao", joinColumns = { @JoinColumn(name = "diet_id") }, inverseJoinColumns = { @JoinColumn(name = "event_id") })
	private List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paci_id", nullable = true)
	private Paciente paciente;
	
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
	
	

	public String getPorcao() {
		return porcao;
	}

	public void setPorcao(String porcao) {
		this.porcao = porcao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipoRefeicao() {
		return tipoRefeicao;
	}

	public void setTipoRefeicao(Integer tipoRefeicao) {
		this.tipoRefeicao = tipoRefeicao;
	}

	public Double getValorNutricional() {
		return valorNutricional;
	}

	public void setValorNutricional(Double valorNutricional) {
		this.valorNutricional = valorNutricional;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public List<EventoMedicacao> getEventoMedicacoes() {
		return eventoMedicacoes;
	}

	public void setEventoMedicacoes(List<EventoMedicacao> eventoMedicacoes) {
		this.eventoMedicacoes = eventoMedicacoes;
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
