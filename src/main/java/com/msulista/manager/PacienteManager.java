package com.msulista.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.msulista.entidade.Dieta;
import com.msulista.entidade.Medicamento;
import com.msulista.entidade.Paciente;

@ManagedBean
public class PacienteManager {
	
	List<Paciente> pacientes = new ArrayList<>();
	
private Long id;
	
	private String nomePaciente;
	private String fonePaciente;
	private Date dtNascimento;
	private String nomeFamiliar;
	private String foneFamiliar;
	private String emailFamiliar;
	private String endereco;
	private Integer frequenciaHidratacao;
	private List<Dieta> dietas = new ArrayList<>();

	public Paciente findPacientebyNome(String nome) {
		
		for (Paciente paciente : pacientes) {
			if (paciente.getNomePaciente().equalsIgnoreCase(nome)) {
				return paciente;
			}
		}
		return null;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}
	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
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

	public List<Dieta> getDietas() {
		return dietas;
	}

	public void setDietas(List<Dieta> dietas) {
		this.dietas = dietas;
	}

	
}
