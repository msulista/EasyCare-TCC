package com.msulista.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.msulista.entidade.EventoMedicacao;

public class RelatorioAtendimentoVO implements Serializable{
	
	private static final long serialVersionUID = -3694950953721262491L;
		
	private String cuidadorNome;
	private String cuidadorFone;
	private String pacienteNome;
	private String pacienteEndereco;
	private String familiarNome;
	
	private List<RealatorioAtendimentoEventoVO> eventos = new ArrayList<>();
		
	public String getCuidadorNome() {
		return cuidadorNome;
	}
	public void setCuidadorNome(String cuidadorNome) {
		this.cuidadorNome = cuidadorNome;
	}
	public String getCuidadorFone() {
		return cuidadorFone;
	}
	public void setCuidadorFone(String cuidadorFone) {
		this.cuidadorFone = cuidadorFone;
	}
	public String getPacienteNome() {
		return pacienteNome;
	}
	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}
	public String getPacienteEndereco() {
		return pacienteEndereco;
	}
	public void setPacienteEndereco(String pacienteEndereco) {
		this.pacienteEndereco = pacienteEndereco;
	}
	public String getFamiliarNome() {
		return familiarNome;
	}
	public void setFamiliarNome(String familiarNome) {
		this.familiarNome = familiarNome;
	}

	public List<RealatorioAtendimentoEventoVO> getEventos() {
		return eventos;
	}

	public void setEventos(List<RealatorioAtendimentoEventoVO> eventos) {
		this.eventos = eventos;
	}


}
