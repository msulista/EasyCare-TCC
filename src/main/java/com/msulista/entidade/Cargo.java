package com.msulista.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.msulista.util.DateUtil;

@Entity
@Table(name = "SGR_CARGO")
@NamedQueries({
	@NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c ORDER BY c.nome ASC"),
	@NamedQuery(name = "Cargo.findAtivos", query = "SELECT c FROM Cargo c WHERE c.registroValidadeFim IS NULL OR c.registroValidadeFim > CURRENT_DATE ORDER BY c.nome ASC"),
	@NamedQuery(name = "Cargo.findNome", query = "SELECT c FROM Cargo c WHERE c.nome = :nome"),
	@NamedQuery(name = "Cargo.findId", query = "SELECT c FROM Cargo c WHERE c.id = :id")
})
public class Cargo implements BaseEntity, Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARGO", nullable = false, precision = 32)
	private Long id;

	@Column(name = "NOME", length = 45, nullable = false)
	private String nome;

	@Column(name = "REGISTRO_VALIDADE_INICIO", nullable = false)
	private Date registroValidadeInicio;

	@Column(name = "REGISTRO_VALIDADE_FIM", nullable = true)
	private Date registroValidadeFim;
	
	@Transient
	private Date dataManipulacao;
	
	public Cargo() {
		this.registroValidadeInicio = DateUtil.getProximoDiaUtil();
		this.dataManipulacao = DateUtil.getProximoDiaUtil();
	}

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

	public Date getRegistroValidadeInicio() {
		return registroValidadeInicio;
	}

	public void setRegistroValidadeInicio(Date registroValidadeInicio) {
		this.registroValidadeInicio = registroValidadeInicio;
	}

	public Date getRegistroValidadeFim() {
		return registroValidadeFim;
	}

	public void setRegistroValidadeFim(Date registroValidade) {
		this.registroValidadeFim = registroValidade;
	}	

	public Date getDataManipulacao() {
		return dataManipulacao;
	}

	public void setDataManipulacao(Date dataManipulacao) {
		this.dataManipulacao = dataManipulacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((registroValidadeFim == null) ? 0 : registroValidadeFim.hashCode());
		result = prime * result + ((registroValidadeInicio == null) ? 0 : registroValidadeInicio.hashCode());
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
		Cargo other = (Cargo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (registroValidadeFim == null) {
			if (other.registroValidadeFim != null)
				return false;
		} else if (!registroValidadeFim.equals(other.registroValidadeFim))
			return false;
		if (registroValidadeInicio == null) {
			if (other.registroValidadeInicio != null)
				return false;
		} else if (!registroValidadeInicio.equals(other.registroValidadeInicio))
			return false;
		return true;
	}
}
