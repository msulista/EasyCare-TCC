package com.msulista.vo;

import java.io.Serializable;

public class RealatorioAtendimentoEventoVO implements Serializable{
	
	private static final long serialVersionUID = 4089794192637288298L;
	
	private String hora;
	private String dia;
	private String mediamento;
	private String dosagem;
	private String status;
	
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getMediamento() {
		return mediamento;
	}
	public void setMediamento(String mediamento) {
		this.mediamento = mediamento;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
