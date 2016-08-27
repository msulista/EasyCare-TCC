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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "atendimento")
@NamedQueries({ @NamedQuery(name = "Atendimento.findAll", query = "SELECT a FROM Atendimento a"),
		@NamedQuery(name = "Atendimento.findPorId", query = "SELECT a FROM Atendimento a WHERE a.id = :id"), })
public class Atendimento implements BaseEntity, Serializable {

	private static final long serialVersionUID = -8458837793631601906L;

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

	@Column(name = "atend_local")
	private String localAtendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuid_id", nullable = true)
	private Cuidador cuidador;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paci_id", nullable = true)
	private Paciente paciente;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "atendimento", cascade = CascadeType.REMOVE)
	private final List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();

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

	// public List<EventoMedicacao> getEventoMedicacoes() {
	// return eventoMedicacoes;
	// }
	//
	// public void setEventoMedicacoes(List<EventoMedicacao> eventoMedicacoes) {
	// this.eventoMedicacoes = eventoMedicacoes;
	// }

}
