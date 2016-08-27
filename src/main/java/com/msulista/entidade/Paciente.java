package com.msulista.entidade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

@Entity
@Table(name = "paciente")
@NamedQueries({ @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p ORDER BY p.nomePaciente ASC"),
		@NamedQuery(name = "Paciente.findPorId", query = "SELECT p FROM Paciente p WHERE p.id = :id"), })
public class Paciente implements BaseEntity, Serializable {

	private static final long serialVersionUID = -51227891126451827L;

	private static final String DATA_FORMATO = "dd/MM/yyyy";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paci_id")
	private Long id;

	@Column(name = "paci_nome")
	private String nomePaciente;

	@Column(name = "paci_fone")
	private String fonePaciente;

	@Column(name = "paci_dt_nascimento")
	private Date dtNascimento;

	@Column(name = "paci_no_familiar")
	private String nomeFamiliar;

	@Column(name = "paci_fone_familiar")
	private String foneFamiliar;

	@Column(name = "paci_email_familiar")
	private String emailFamiliar;

	@Column(name = "paci_endereco")
	private String endereco;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paciente", cascade = CascadeType.REMOVE)
	private List<Atendimento> atendimentos = new ArrayList<>();

	@Transient
	private String transientDtNascimento;

	@Transient
	private Integer transientIdade;

	@Transient
	private Boolean transientResponsavel;
	
	@Transient
	private Boolean googleAgenda;

	@Override
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNomePaciente() {
		return this.nomePaciente;
	}

	public void setNomePaciente(final String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getFonePaciente() {
		return this.fonePaciente;
	}

	public void setFonePaciente(final String fonePaciente) {
		this.fonePaciente = fonePaciente;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(final Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNomeFamiliar() {
		return this.nomeFamiliar;
	}

	public void setNomeFamiliar(final String nomeFamiliar) {
		this.nomeFamiliar = nomeFamiliar;
	}

	public String getFoneFamiliar() {
		return this.foneFamiliar;
	}

	public void setFoneFamiliar(final String foneFamiliar) {
		this.foneFamiliar = foneFamiliar;
	}

	public String getEmailFamiliar() {
		return this.emailFamiliar;
	}

	public void setEmailFamiliar(final String emailFamiliar) {
		this.emailFamiliar = emailFamiliar;
	}

	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(final String endereco) {
		this.endereco = endereco;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(final List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public String getTransientDtNascimento() {

		if (this.getDtNascimento() != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat(DATA_FORMATO);
			this.transientDtNascimento = sdf.format(this.getDtNascimento());
		}
		return this.transientDtNascimento;
	}

	public void setTransientDtNascimento(final String transientDtNascimento) {
		this.transientDtNascimento = transientDtNascimento;
	}

	public Integer getTransientIdade() {
		return this.transientIdade;
	}

	public void setTransientIdade(final Integer transientIdade) {
		this.transientIdade = transientIdade;
	}

	public Boolean getTransientResponsavel() {
		return this.transientResponsavel;
	}

	public void setTransientResponsavel(final Boolean transientResponsavel) {
		this.transientResponsavel = transientResponsavel;
	}

	public Boolean getGoogleAgenda() {
		return googleAgenda;
	}

	public void setGoogleAgenda(Boolean googleAgenda) {
		this.googleAgenda = googleAgenda;
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
		final Paciente other = (Paciente) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
