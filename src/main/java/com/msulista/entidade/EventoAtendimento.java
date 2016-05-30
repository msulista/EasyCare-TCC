package com.msulista.entidade;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "evento_atendimento")
@NamedQueries({
	@NamedQuery(name = "EventoAtendimento.findAll", query = "SELECT ev FROM EventoAtendimento ev"),
	@NamedQuery(name = "EventoAtendimento.findId", query = "SELECT ev FROM EventoAtendimento ev WHERE ev.id = :id"),
})
public class EventoAtendimento implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = 6430508817163207959L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id", nullable = false, precision = 32)
	private Long id;
	
	@Column(name = "event_titulo", length = 50, nullable = false)
	private String titulo;
	
	@Column(name = "event_dt_inicio", nullable = false)
	private Date dataInicio;
	
	@Column(name = "event_dt_fim", nullable = false)
	private Date dataFim;
	
	@Column(name = "event_hr_adm", nullable = true)
	private Date horaAdministracao;
		
	@Column(name = "event_descricao", length = 200, nullable = true)
	private String descricao;
	
	@Column(name = "event_status")
	private Boolean status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuid_id", nullable = true)
	private Cuidador cuidador;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paci_id", referencedColumnName = "paci_id", nullable = true)
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medi_id", referencedColumnName = "medi_id", nullable = true)
	private Medicamento medicamento;
	
	@Transient
	private Dieta dieta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getHoraAdministracao() {
		return horaAdministracao;
	}

	public void setHoraAdministracao(Date horaAdministracao) {
		this.horaAdministracao = horaAdministracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Dieta getDieta() {
		return dieta;
	}

	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
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
		EventoAtendimento other = (EventoAtendimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	

}
