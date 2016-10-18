package com.msulista.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RelatorioAtendimentoVO implements Serializable {

	private static final long serialVersionUID = -3694950953721262491L;

	private String cuidadorNome;
	private String cuidadorFone;
	private String pacienteNome;
	private String pacienteEndereco;
	private String familiarNome;
	private Date dataInicial;
	private Date dataFinal;

	private List<RealatorioAtendimentoEventoVO> eventos = new ArrayList<>();

	public String getCuidadorNome() {
		return this.cuidadorNome;
	}

	public void setCuidadorNome(final String cuidadorNome) {
		this.cuidadorNome = cuidadorNome;
	}

	public String getCuidadorFone() {
		return this.cuidadorFone;
	}

	public void setCuidadorFone(final String cuidadorFone) {
		this.cuidadorFone = cuidadorFone;
	}

	public String getPacienteNome() {
		return this.pacienteNome;
	}

	public void setPacienteNome(final String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}

	public String getPacienteEndereco() {
		return this.pacienteEndereco;
	}

	public void setPacienteEndereco(final String pacienteEndereco) {
		this.pacienteEndereco = pacienteEndereco;
	}

	public String getFamiliarNome() {
		return this.familiarNome;
	}

	public void setFamiliarNome(final String familiarNome) {
		this.familiarNome = familiarNome;
	}

	public List<RealatorioAtendimentoEventoVO> getEventos() {
		return this.eventos;
	}

	public void setEventos(final List<RealatorioAtendimentoEventoVO> eventos) {
		this.eventos = eventos;
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

}
