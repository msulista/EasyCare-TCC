package com.msulista.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "AGENDA")
@NamedQueries({
	@NamedQuery(name = "Agenda.findAll", query = "SELECT a FROM Agenda a")
})
public class Agenda implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = 6430508817163207959L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agenda_id", nullable = false, precision = 32)
	private Long id;
	
	@Column(name = "agenda_titulo", length = 50, nullable = false)
	private String titulo;
	
	@Column(name = "agenda_dt_inicio", nullable = false)
	private Date dataInicio;
	
	@Column(name = "agenda_dt_fim", nullable = false)
	private Date dataFim;
	
	@Column(name = "agenda_hr_inicio", nullable = true)
	private Date horaInicio;
	
	@Column(name = "agenda_hr_fim", nullable = true)
	private Date horaFim;
	
	@Column(name = "agenda_descricao", length = 200, nullable = true)
	private String descricao;
	
	@Transient
	private Boolean status;
	
	@Transient
	private Paciente paciente;
	
	@Transient
	private Medicacao medicacao;
	
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
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medicacao getMedicacao() {
		return medicacao;
	}
	public void setMedicacao(Medicacao medicacao) {
		this.medicacao = medicacao;
	}
	public Dieta getDieta() {
		return dieta;
	}
	public void setDieta(Dieta dieta) {
		this.dieta = dieta;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
