package com.msulista.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
@NamedQueries({
	@NamedQuery(name = "Cuidador.findAll", query = "SELECT c FROM Cuidador c ORDER BY c.nome ASC"),
	@NamedQuery(name = "Cuidador.findPorId", query = "SELECT c FROM Cuidador c WHERE c.id = :id"),
})
public class Cuidador implements BaseEntity, Serializable{
	
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
	
	@Column(name = "cuid_descricao")
	private String descricao;
	
	@Column(name = "cuid_formacao")
	private String formacao;
	
	@Column(name = "cuid_localizacao")
	private String localizacao;
	
	@Column(name = "cuid_anuncio")
	private String anuncio;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "cuidador", cascade = CascadeType.REMOVE)
	private Set<EventoAtendimento> agenda = new HashSet<>();

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(String anuncio) {
		this.anuncio = anuncio;
	}
	
	public Set<EventoAtendimento> getAgenda() {
		return agenda;
	}

	public void setAgenda(Set<EventoAtendimento> agenda) {
		this.agenda = agenda;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getFormacao() {
		return formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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
		Cuidador other = (Cuidador) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
