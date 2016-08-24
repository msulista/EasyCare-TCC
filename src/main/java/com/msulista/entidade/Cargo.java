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
@NamedQueries({ @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c ORDER BY c.nome ASC"),
		@NamedQuery(name = "Cargo.findAtivos", query = "SELECT c FROM Cargo c WHERE c.registroValidadeFim IS NULL OR c.registroValidadeFim > CURRENT_DATE ORDER BY c.nome ASC"),
		@NamedQuery(name = "Cargo.findNome", query = "SELECT c FROM Cargo c WHERE c.nome = :nome"),
		@NamedQuery(name = "Cargo.findId", query = "SELECT c FROM Cargo c WHERE c.id = :id") })
public class Cargo implements BaseEntity, Serializable {

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

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public Date getRegistroValidadeInicio() {
		return this.registroValidadeInicio;
	}

	public void setRegistroValidadeInicio(final Date registroValidadeInicio) {
		this.registroValidadeInicio = registroValidadeInicio;
	}

	public Date getRegistroValidadeFim() {
		return this.registroValidadeFim;
	}

	public void setRegistroValidadeFim(final Date registroValidade) {
		this.registroValidadeFim = registroValidade;
	}

	public Date getDataManipulacao() {
		return this.dataManipulacao;
	}

	public void setDataManipulacao(final Date dataManipulacao) {
		this.dataManipulacao = dataManipulacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.nome == null) ? 0 : this.nome.hashCode());
		result = prime * result + ((this.registroValidadeFim == null) ? 0 : this.registroValidadeFim.hashCode());
		result = prime * result + ((this.registroValidadeInicio == null) ? 0 : this.registroValidadeInicio.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final Cargo other = (Cargo) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!this.nome.equals(other.nome)) {
			return false;
		}
		if (this.registroValidadeFim == null) {
			if (other.registroValidadeFim != null) {
				return false;
			}
		} else if (!this.registroValidadeFim.equals(other.registroValidadeFim)) {
			return false;
		}
		if (this.registroValidadeInicio == null) {
			if (other.registroValidadeInicio != null) {
				return false;
			}
		} else if (!this.registroValidadeInicio.equals(other.registroValidadeInicio)) {
			return false;
		}
		return true;
	}
}
