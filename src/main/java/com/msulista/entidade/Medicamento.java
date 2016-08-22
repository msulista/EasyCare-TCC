package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Column(name = "medi_material")
	private String material;
	
	@Column(name = "medi_concentracao")
	private String concentracao;
	
	@Column(name = "medi_via")
	private String viaAdministracao;
	
	@Column(name = "medi_quanti_estoque")
	private Integer estoque;
	
	@Column(name = "medi_dt_validade")
	private Date dataValidade;
	
	@Column(name = "medi_tipo")
	private Integer tipo;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "evento_medicamento", joinColumns = { @JoinColumn(name = "event_id") }, inverseJoinColumns = { @JoinColumn(name = "medi_id") })
	@Transient
	private List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();
	
	
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
	
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getConcentracao() {
		return concentracao;
	}
	public void setConcentracao(String concentracao) {
		this.concentracao = concentracao;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public List<EventoMedicacao> getEventoMedicacoes() {
		return eventoMedicacoes;
	}
	public void setEventoMedicacoes(List<EventoMedicacao> eventoMedicacoes) {
		this.eventoMedicacoes = eventoMedicacoes;
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
