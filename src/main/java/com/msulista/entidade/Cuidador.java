package com.msulista.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cuidador")
@NamedQueries({ @NamedQuery(name = "Cuidador.findAll", query = "SELECT c FROM Cuidador c ORDER BY c.nome ASC"),
		@NamedQuery(name = "Cuidador.findPorId", query = "SELECT c FROM Cuidador c WHERE c.id = :id"), })
public class Cuidador implements BaseEntity, Serializable {

	private static final long serialVersionUID = 8989135807543251135L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuid_id")
	private Long id;

	@Column(name = "cuid_nome")
	private String nome;

	@Column(name = "cuid_fone")
	private String telefone;

	@Column(name = "cuid_email")
	private String email;

	@Column(name = "cuid_dt_nascimento")
	private Date dataNascimento;

	@Column(name = "cuid_cpf")
	private String cpf;

	@Column(name = "cuid_descricao")
	private String descricao;

	@Column(name = "cuid_formacao")
	private String formacao;

	@Column(name = "cuid_localizacao")
	private String localizacao;

	@Column(name = "cuid_status")
	private Integer status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuidador", cascade = CascadeType.REMOVE)
	private List<Atendimento> atendimentos = new ArrayList<>();

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

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setDataNascimento(final Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(final Integer status) {
		this.status = status;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getFormacao() {
		return this.formacao;
	}

	public void setFormacao(final String formacao) {
		this.formacao = formacao;
	}

	public String getLocalizacao() {
		return this.localizacao;
	}

	public void setLocalizacao(final String localizacao) {
		this.localizacao = localizacao;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(final List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(final String cpf) {
		this.cpf = cpf;
	}

	public boolean verificaLogin(final String email, final String cpf) {

		return (this.email.equalsIgnoreCase(email) && this.cpf.equals(cpf));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		final Cuidador other = (Cuidador) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cuidador [id=" + this.id + ", nome=" + this.nome + "]";
	}

}
