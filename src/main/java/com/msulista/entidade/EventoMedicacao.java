package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "evento_medicacao")
@NamedQueries({ @NamedQuery(name = "EventoMedicacao.findAll", query = "SELECT em FROM EventoMedicacao em"),
		@NamedQuery(name = "EventoMedicacao", query = "SELECT em FROM EventoMedicacao em WHERE em.id = :id"), })
public class EventoMedicacao implements BaseEntity, Serializable {

	private static final long serialVersionUID = -7077632222540575070L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;

	@Column(name = "event_dt_hr")
	private Date dataHora;

	@Column(name = "event_titulo")
	private String titulo;

	@Column(name = "event_descricao")
	private String descricao;

	@Column(name = "event_status")
	private Integer stattus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "atend_id", nullable = false)
	private Atendimento atendimento;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.DETACH })
	@JoinTable(name = "dieta", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = {
			@JoinColumn(name = "medi_id") })
	private List<Medicamento> medcamentos = new ArrayList<>();

//	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE,
//			CascadeType.DETACH })
//	@JoinTable(name = "dieta", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "diet_id") })
	private List<Dieta> refeicoes = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStattus() {
		return stattus;
	}

	public void setStattus(Integer stattus) {
		this.stattus = stattus;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Medicamento> getMedcamentos() {
		return medcamentos;
	}

	public void setMedcamentos(List<Medicamento> medcamentos) {
		this.medcamentos = medcamentos;
	}

	public List<Dieta> getRefeicoes() {
		return refeicoes;
	}

	public void setRefeicoes(List<Dieta> refeicoes) {
		this.refeicoes = refeicoes;
	}
	
}
