package com.msulista.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.msulista.entidade.EventoMedicacao;

public class RelatorioAtendimentoVO implements Serializable{
	
	private static final long serialVersionUID = -3694950953721262491L;
	
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
