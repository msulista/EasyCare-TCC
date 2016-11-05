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

import com.msulista.util.DateUtil;

@Entity
@Table(name = "medicamento")
@NamedQueries({ @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m ORDER BY m.nome ASC"),
		@NamedQuery(name = "Medicamento.findPorPaciente", query = "SELECT m FROM Medicamento m  WHERE m.paciente.id = :id ORDER BY m.nome ASC"),
		@NamedQuery(name = "Medicamento.findPorCuidador", query = "SELECT m FROM Medicamento m  WHERE m.paciente.cuidador.id = :id ORDER BY m.nome ASC"),
		@NamedQuery(name = "Medicamento.findPorId", query = "SELECT m FROM Medicamento m WHERE m.id = :id"), })
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "medicamento_has_evento_medicacao", joinColumns = {
			@JoinColumn(name = "medi_id") }, inverseJoinColumns = { @JoinColumn(name = "event_id") })
	private List<EventoMedicacao> eventoMedicacoes = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "paci_id", nullable = true)
	private Paciente paciente;

	@Transient
	private String transientDtValidade;

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

	public String getViaAdministracao() {
		return this.viaAdministracao;
	}

	public void setViaAdministracao(final String viaAdministracao) {
		this.viaAdministracao = viaAdministracao;
	}

	public Integer getEstoque() {
		return this.estoque;
	}

	public void setEstoque(final Integer estoque) {
		this.estoque = estoque;
	}

	public Date getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(final Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(final String material) {
		this.material = material;
	}

	public String getConcentracao() {
		return this.concentracao;
	}

	public void setConcentracao(final String concentracao) {
		this.concentracao = concentracao;
	}

	public Integer getTipo() {
		return this.tipo;
	}

	public void setTipo(final Integer tipo) {
		this.tipo = tipo;
	}

	public List<EventoMedicacao> getEventoMedicacoes() {
		return this.eventoMedicacoes;
	}

	public void setEventoMedicacoes(final List<EventoMedicacao> eventoMedicacoes) {
		this.eventoMedicacoes = eventoMedicacoes;
	}

	public String getTransientDtValidade() {
		this.transientDtValidade = DateUtil.dateToStringDate(this.getDataValidade());
		return this.transientDtValidade;
	}

	public void setTransientDtValidade(final String transientDtValidade) {
		this.transientDtValidade = transientDtValidade;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
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
		final Medicamento other = (Medicamento) obj;
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
