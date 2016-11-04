package com.msulista.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "atendimento")
@NamedQueries({ @NamedQuery(name = "Atendimento.findAll", query = "SELECT a FROM Atendimento a"),
		@NamedQuery(name = "Atendimento.findPorCuidador", query = "SELECT a FROM Atendimento a WHERE a.cuidador.id = :id"),
		@NamedQuery(name = "Atendimento.findPorId", query = "SELECT a FROM Atendimento a WHERE a.id = :id"), })
public class Atendimento implements BaseEntity, Serializable {

	private static final long serialVersionUID = -8458837793631601906L;

	private static final String DATA_FORMATO = "dd/MM/yyyy";

	private static final String HORA_FORMATO = "HH:mm";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "atend_id")
	private Long id;

	@Column(name = "atend_dt_inicial")
	private Date dataInicial;

	@Column(name = "atend_dt_final")
	private Date dataFinal;

	@Column(name = "atend_hr_inicial")
	private Date horaInicial;

	@Column(name = "atend_hr_final")
	private Date horaFinal;

	@Column(name = "atend_localizacao")
	private String localAtendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuid_id", nullable = true)
	private Cuidador cuidador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paci_id", nullable = true)
	private Paciente paciente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atendimento", cascade = CascadeType.REMOVE)
	private final List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();

	@Transient
	private String transientDtIni;

	@Transient
	private String transientDtFim;

	@Transient
	private String transientHrIni;

	@Transient
	private String transientHrFim;

	@Transient
	private String dtPeriodo;

	@Transient
	private String hrIntervalo;

	@Transient
	private boolean enderecoPaciente;

	@Transient
	private String atendimentoTitulo;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Date getDataInicial() {
		return this.dataInicial;
	}

	public void setDataInicial(final Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return this.dataFinal;
	}

	public void setDataFinal(final Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getHoraInicial() {
		return this.horaInicial;
	}

	public void setHoraInicial(final Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	public Date getHoraFinal() {
		return this.horaFinal;
	}

	public void setHoraFinal(final Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getLocalAtendimento() {
		return this.localAtendimento;
	}

	public void setLocalAtendimento(final String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public Cuidador getCuidador() {
		return this.cuidador;
	}

	public void setCuidador(final Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the eventoMedicacoes
	 */
	public List<EventoMedicacao> getEventoMedicacoes() {
		return this.eventoMedicacoes;
	}

	public boolean getEnderecoPaciente() {
		return this.enderecoPaciente;
	}

	public void setEnderecoPaciente(final boolean enderecoPaciente) {
		this.enderecoPaciente = enderecoPaciente;
	}

	/**
	 * @return the transientDtIni
	 */
	public String getTransientDtIni() {
		if (this.getDataInicial() != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMATO);
			this.transientDtIni = sdf.format(this.getDataInicial());
		}
		return this.transientDtIni;
	}

	/**
	 * @return the transientDtFim
	 */
	public String getTransientDtFim() {
		if (this.getDataFinal() != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMATO);
			this.transientDtFim = sdf.format(this.getDataFinal());
		}
		return this.transientDtFim;
	}

	/**
	 * @return the transientHrIni
	 */
	public String getTransientHrIni() {
		if (this.getHoraInicial() != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(HORA_FORMATO);
			this.transientHrIni = sdf.format(this.getHoraInicial());
		}
		return this.transientHrIni;
	}

	/**
	 * @return the transientHrFim
	 */
	public String getTransientHrFim() {
		if (this.getHoraFinal() != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(HORA_FORMATO);
			this.transientHrFim = sdf.format(this.getHoraFinal());
		}
		return this.transientHrFim;
	}

	/**
	 * @return the dtPeriodo
	 */
	public String getDtPeriodo() {
		this.dtPeriodo = this.getTransientDtIni() + " até " + this.getTransientDtFim();
		return this.dtPeriodo;
	}

	/**
	 * @return the hrIntervalo
	 */
	public String getHrIntervalo() {
		this.hrIntervalo = this.getTransientHrIni() + " até " + this.getTransientHrFim();
		return this.hrIntervalo;
	}

	public String getAtendimentoTitulo() {
		this.atendimentoTitulo = this.getPaciente().getNomePaciente() + " - " + this.getTransientDtIni() + " até "
				+ this.getTransientDtFim();
		return this.atendimentoTitulo;
	}

	public void setAtendimentoTitulo(final String atendimentoTitulo) {
		this.atendimentoTitulo = atendimentoTitulo;
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
		final Atendimento other = (Atendimento) obj;
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
