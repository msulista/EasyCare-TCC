package com.msulista.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.StreamedContent;

import com.msulista.entidade.Atendimento;
import com.msulista.entidade.Paciente;
import com.msulista.negocio.AtendimentoNegocio;
import com.msulista.util.Mensagem;
import com.msulista.vo.RelatorioAtendimentoVO;
import com.ocpsoft.pretty.faces.annotation.URLAction;
import com.ocpsoft.pretty.faces.annotation.URLActions;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "atendimento", pattern = "/atendimento", viewId = "/pages/usuario/atendimento/atendimento-listar.xhtml"),
		@URLMapping(id = "atendimento-incluir", pattern = "/incluir", viewId = "/pages/usuario/atendimento/atendimento-incluir.xhtml", parentId = "atendimento"),
		@URLMapping(id = "atendimento-editar", pattern = "/#{atendimentoManager.atendimento.id}/editar", viewId = "/pages/usuario/atendimento/atendimento-editar.xhtml", parentId = "atendimento") })

public class AtendimentoManager {

	private ScheduleModel scheduleModel;
	private Atendimento atendimento;
	private AtendimentoNegocio atendimentoNegocio;
	private List<Atendimento> atendimentos;
	private Paciente paciente;

	private RelatorioAtendimentoVO relatorioVo;

	@PostConstruct
	public void inicializar() {
		this.atendimento = new Atendimento();
		this.atendimentos = new ArrayList<>();
		this.atendimentoNegocio = new AtendimentoNegocio();
		this.scheduleModel = new DefaultScheduleModel();

		this.atendimentos = this.atendimentoNegocio.obterLista();

		for (final Atendimento atendimento : this.atendimentos) {
			final DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(atendimento.getId());
			evt.setTitle(atendimento.getPaciente().getNomePaciente());
			evt.setStartDate(atendimento.getDataInicial());
			evt.setEndDate(atendimento.getDataFinal());
			evt.setAllDay(false);
			evt.setEditable(true);
			// evt.setStyleClass("event-green");

			this.scheduleModel.addEvent(evt);
		}
	}

	/**
	 * Salva novo {@link Atendimento}
	 *
	 * @return
	 */
	public String salvar() {

		if (this.atendimentoNegocio.salvar(this.atendimento)) {
			return "pretty:atendimento";
		}
		return null;
	}

	/**
	 * Altera os dados de um {@link Atendimento}
	 */
	public String aterar() {
		if (this.atendimentoNegocio.alterar(this.atendimento)) {
			return "pretty:atendimento";
		}
		return null;
	}

	public String editar(final Atendimento atendimentoEdit) {
		this.atendimento = atendimentoEdit;
		return "pretty:atendimento-editar";
	}

	public StreamedContent relatorio(final Atendimento atendimento) {

		StreamedContent relatorio = null;

		try {
			relatorio = this.atendimentoNegocio.exportaRelatorioAtendimentoPdf(atendimento);
			Mensagem.add("Relatório gerado com sucesso!");
		} catch (final SQLException e) {
			Mensagem.add("Ocorreu um erro ao gerar o relatório!");
		}
		return relatorio;
	}

	/**
	 * Obtem lista de todos os {@link Atendimento}
	 *
	 * @return {@link List} {@link Atendimento}
	 */
	public List<Atendimento> obterLista() {

		return this.atendimentoNegocio.obterLista();
	}

	/**
	 * Remove o {@link Atendimento} do banco
	 */
	public String excluirAtendimento(final Atendimento atendimentoExcluir) {
		this.atendimentoNegocio.excluir(atendimentoExcluir);
		Mensagem.add("Atendimento Excluido com sucesso!");
		return "pretty:atendimento";
	}

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	/**
	 * Obtem atendimento por Id
	 *
	 * @return
	 */
	public Atendimento obtemAtendimento() {
		return this.atendimentoNegocio.obterPorId(this.atendimento.getId());
	}

	public void enviarRelatorio(final Atendimento atendimento) {
		if (StringUtils.isNotBlank(atendimento.getPaciente().getEmailFamiliar())) {

			try {
				if (this.atendimentoNegocio.enviarRealatorio(atendimento)) {
					Mensagem.add("Relatório enviado com sucesso.");
				} else {
					Mensagem.add("Paciente não possui familiar cadastrado para envio de relatório.");
				}
			} catch (final SQLException e) {
				Mensagem.add("Falha ao enviar relatório.");
				e.printStackTrace();
			}
		} else {
			Mensagem.add("Paciente não possui responsável cadastrado!");
		}
	}

	public void setAtendimento(final Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public AtendimentoNegocio getAtendimentoNegocio() {
		return this.atendimentoNegocio;
	}

	public void setAtendimentoNegocio(final AtendimentoNegocio atendimentoNegocio) {
		this.atendimentoNegocio = atendimentoNegocio;
	}

	public List<Atendimento> getAtendimentos() {
		return this.atendimentos;
	}

	public void setAtendimentos(final List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(final Paciente paciente) {
		this.paciente = paciente;
	}

	public ScheduleModel getScheduleModel() {
		return this.scheduleModel;
	}

	public void setScheduleModel(final ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public RelatorioAtendimentoVO getRelatorioVo() {
		return this.relatorioVo;
	}

	public void setRelatorioVo(final RelatorioAtendimentoVO relatorioVo) {
		this.relatorioVo = relatorioVo;
	}

	@URLActions(actions = { @URLAction(mappingId = "atendimento-editar", onPostback = false) })
	public void load() throws IOException {
		this.atendimento = this.atendimentoNegocio.obterPorId(this.atendimento.getId());
	}

}
