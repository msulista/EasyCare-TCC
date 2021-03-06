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

import com.msulista.util.DateUtil;

@Entity
@Table(name = "evento_medicacao")
@NamedQueries({ @NamedQuery(name = "EventoMedicacao.findAll", query = "SELECT em FROM EventoMedicacao em"),
		@NamedQuery(name = "EventoMedicacao.findPorCuidador", query = "SELECT em FROM EventoMedicacao em WHERE em.atendimento.cuidador.id = :id"),
		@NamedQuery(name = "EventoMedicacao.findId", query = "SELECT em FROM EventoMedicacao em WHERE em.id = :id"),
		@NamedQuery(name = "EventoMedicacao.findDiaCorrente", query = "SELECT em FROM EventoMedicacao em WHERE em.dataHora BETWEEN :dataInicio AND :dataFim AND em.atendimento.cuidador.id = :id ORDER BY em.dataHora"),
		@NamedQuery(name = "EventoMedicacao.findPorAtendimentoId", query = "SELECT em FROM EventoMedicacao em WHERE em.atendimento.id = :id ORDER BY em.dataHora"), })
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
	
	@Column(name = "event_quant")
	private Integer quantidade;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "atend_id", nullable = false)
	private Atendimento atendimento;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "medicamento_has_evento_medicacao", joinColumns = {
			@JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "medi_id") })
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
	private Integer transientFrequenciaEvento = 0;

	@Transient
	private boolean transientRepetirDiariamente;

	@Transient
	private String transientHora;

	@Transient
	private String transientNomeMedicacao;

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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
		return this.transientFrequenciaEvento;
	}

	public void setTransientFrequenciaEvento(final Integer transientFrequenciaEvento) {
		this.transientFrequenciaEvento = transientFrequenciaEvento;
	}

	public boolean getTransientRepetirDiariamente() {

		return this.transientRepetirDiariamente;
	}

	public void setTransientRepetirDiariamente(final boolean transientRepetirDiariamente) {
		this.transientRepetirDiariamente = transientRepetirDiariamente;
	}

	public String getTransientHora() {

		return DateUtil.hourToStringHour(this.getDataHora());
	}

	public void setTransientHora(final String transientHora) {
		this.transientHora = transientHora;
	}

	public String getTransientNomeMedicacao() {
		if (!this.getMedicamentos().isEmpty()) {

			this.transientNomeMedicacao = this.getMedicamentos().get(0).getNome();
		}
		return this.transientNomeMedicacao;
	}

	public void setTransientNomeMedicacao(final String transientNomeMedicacao) {
		this.transientNomeMedicacao = transientNomeMedicacao;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		final EventoMedicacao other = (EventoMedicacao) obj;
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
