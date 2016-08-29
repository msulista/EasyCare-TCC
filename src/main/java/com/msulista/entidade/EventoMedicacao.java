package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

@Entity
@Table(name = "evento_medicacao")
@NamedQueries({ @NamedQuery(name = "EventoMedicacao.findAll", query = "SELECT em FROM EventoMedicacao em"),
		@NamedQuery(name = "EventoMedicacao.findId", query = "SELECT em FROM EventoMedicacao em WHERE em.id = :id"), })
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "medicamento_has_evento_medicacao", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "medi_id") })
	private List<Medicamento> medicamentos = new ArrayList<>();

	// @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
	// CascadeType.REFRESH, CascadeType.MERGE,
	// CascadeType.DETACH })
	// @JoinTable(name = "dieta", joinColumns = { @JoinColumn(name = "event_id")
	// }, inverseJoinColumns = {
	// @JoinColumn(name = "diet_id") })
	@Transient
	private List<Dieta> refeicoes = new ArrayList<>();
	
	@Transient
	private Integer transientFrequenciaEvento;

	@Override
	public Long getId() {
		return this.id;
	}

	public Date getDataHora() {
		return this.dataHora;
	}

	public void setDataHora(final Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(final String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public Integer getStattus() {
		return this.stattus;
	}

	public void setStattus(final Integer stattus) {
		this.stattus = stattus;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(final Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(final List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<Dieta> getRefeicoes() {
		return this.refeicoes;
	}

	public void setRefeicoes(final List<Dieta> refeicoes) {
		this.refeicoes = refeicoes;
	}

	public Integer getTransientFrequenciaEvento() {
		return transientFrequenciaEvento;
	}

	public void setTransientFrequenciaEvento(Integer transientFrequenciaEvento) {
		this.transientFrequenciaEvento = transientFrequenciaEvento;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventoMedicacao other = (EventoMedicacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
