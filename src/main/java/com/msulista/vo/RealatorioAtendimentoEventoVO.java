package com.msulista.vo;

import java.io.Serializable;

public class RealatorioAtendimentoEventoVO implements Serializable {

	private static final long serialVersionUID = 4089794192637288298L;

	private String hora;
	private String dia;
	private String mediamento;
	private Integer quantidade;
	private String dosagem;
	private String status;
	private Integer estoque;

	public String getHora() {
		return this.hora;
	}

	public void setHora(final String hora) {
		this.hora = hora;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(final String dia) {
		this.dia = dia;
	}

	public String getMediamento() {
		return this.mediamento;
	}

	public void setMediamento(final String mediamento) {
		this.mediamento = mediamento;
	}

	public Integer getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(final Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDosagem() {
		return this.dosagem;
	}

	public void setDosagem(final String dosagem) {
		this.dosagem = dosagem;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Integer getEstoque() {
		return this.estoque;
	}

	public void setEstoque(final Integer estoque) {
		this.estoque = estoque;
	}

}
