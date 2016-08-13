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

@Entity
@Table(name = "medicamento")
@NamedQueries({
	@NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m ORDER BY m.nome ASC"),
	@NamedQuery(name = "Medicamento.findPorId", query = "SELECT m FROM Medicamento m WHERE m.id = :id"),
})
public class Medicamento implements BaseEntity, Serializable {
	
	private static final long serialVersionUID = -1660999301423330553L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medi_id")
	private Long id;
	
	@Column(name = "medi_nome")
	private String nome;
	
	@Column(name = "medi_posologia")
	private String posologia;
	
	@Column(name = "medi_dosagem")
	private String dosagem;
	
	@Column(name = "medi_via")
	private String viaAdministracao;
	
	@Column(name = "medi_quanti_estoque")
	private Integer estoque;
	
	@Column(name = "medi_dt_validade")
	private Date dataValidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paci_id")
	private Paciente paciente;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPosologia() {
		return posologia;
	}
	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}
	public String getDosagem() {
		return dosagem;
	}
	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}
	public String getViaAdministracao() {
		return viaAdministracao;
	}
	public void setViaAdministracao(String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}
	
	public Integer getEstoque() {
		return estoque;
	}
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	public Date getDataValidade() {
		return dataValidade;
	}
	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
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
		Medicamento other = (Medicamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

   

}
