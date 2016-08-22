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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "paciente")
@NamedQueries({
	@NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p ORDER BY p.nomePaciente ASC"),
	@NamedQuery(name = "Paciente.findPorId", query = "SELECT p FROM Paciente p WHERE p.id = :id"),
})
public class Paciente implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = -51227891126451827L;
	
	private static final String DATA_FORMATO = "dd/MM/yyyy";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paci_id")
	private Long id;
	
	@Column(name = "paci_nome")
	private String nomePaciente;
	
	@Column(name = "paci_fone")
	private String fonePaciente;
	
	@Column(name = "paci_dt_nascimento")
	private Date dtNascimento;
	
	@Column(name = "paci_no_familiar")
	private String nomeFamiliar;
	
	@Column(name = "paci_fone_familiar")
	private String foneFamiliar;
	
	@Column(name = "paci_email_familiar")
	private String emailFamiliar;
	
	@Column(name = "paci_endereco")
	private String endereco;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.REMOVE)
	private List<Atendimento> atendimentos = new ArrayList<>();
	
	
	@Transient
	private String transientDtNascimento;
	
	@Transient
	private Integer transientIdade;
	
	@Transient
	private Boolean transientResponsavel;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getFonePaciente() {
		return fonePaciente;
	}
	public void setFonePaciente(String fonePaciente) {
		this.fonePaciente = fonePaciente;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getNomeFamiliar() {
		return nomeFamiliar;
	}
	public void setNomeFamiliar(String nomeFamiliar) {
		this.nomeFamiliar = nomeFamiliar;
	}
	public String getFoneFamiliar() {
		return foneFamiliar;
	}
	public void setFoneFamiliar(String foneFamiliar) {
		this.foneFamiliar = foneFamiliar;
	}
	public String getEmailFamiliar() {
		return emailFamiliar;
	}
	public void setEmailFamiliar(String emailFamiliar) {
		this.emailFamiliar = emailFamiliar;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
		
	
		
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	public String getTransientDtNascimento() {
		
		 if (this.getDtNascimento() != null) {
	            final SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMATO);
	            this.transientDtNascimento = sdf.format(this.getDtNascimento());
	        }
		return transientDtNascimento;
	}
	public void setTransientDtNascimento(String transientDtNascimento) {
		this.transientDtNascimento = transientDtNascimento;
	}
	public Integer getTransientIdade() {
		return transientIdade;
	}
	public void setTransientIdade(Integer transientIdade) {
		this.transientIdade = transientIdade;
	}
	public Boolean getTransientResponsavel() {
		return transientResponsavel;
	}
	public void setTransientResponsavel(Boolean transientResponsavel) {
		this.transientResponsavel = transientResponsavel;
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
		Paciente other = (Paciente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
