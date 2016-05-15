package com.msulista.entidade;

import java.io.Serializable;
import java.util.Date;

public class Paciente implements BaseEntity, Serializable{
	
	private static final long serialVersionUID = -51227891126451827L;
	
	private Long id;
	private String nomePaciente;
	private String fonePaciente;
	private Date dtNascimento;
	private String nomeFamiliar;
	private String foneFamiliar;
	private String emailFamiliar;
	private String endereco;
	private Integer frequenciaHidratacao;
	
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
	public Integer getFrequenciaHidratacao() {
		return frequenciaHidratacao;
	}
	public void setFrequenciaHidratacao(Integer frequenciaHidratacao) {
		this.frequenciaHidratacao = frequenciaHidratacao;
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
