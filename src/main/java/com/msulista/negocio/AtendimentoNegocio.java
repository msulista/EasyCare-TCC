package com.msulista.negocio;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.msulista.dao.AtendimentoDao;
import com.msulista.dao.EventoMedicacaoDAO;
import com.msulista.entidade.Atendimento;
import com.msulista.entidade.Cuidador;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.enums.RelatorioEnum;
import com.msulista.enums.StatusEventoEnum;
import com.msulista.util.DateUtil;
import com.msulista.util.EmailUtil;
import com.msulista.util.Mensagem;
import com.msulista.util.RelatorioUtils;
import com.msulista.util.SessionUtil;
import com.msulista.vo.RealatorioAtendimentoEventoVO;
import com.msulista.vo.RelatorioAtendimentoVO;

public class AtendimentoNegocio implements NegocioBase<Atendimento> {

	private AtendimentoDao atendimentoDao;
	private RelatorioUtils relatorioUtils;
	private EventoMedicacaoDAO eventoMedicacaoDAO;

	@Override
	public boolean salvar(final Atendimento atendimento) {

		final Cuidador usuarioLogado = SessionUtil.obtemUsuarioLogado();
		atendimento.setCuidador(usuarioLogado);

		final boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(),
				atendimento.getDataFinal());
		this.verificaEnderecoAtendimento(atendimento);
		if (datas) {
			this.atendimentoDao = new AtendimentoDao();
			try {
				this.atendimentoDao.salvar(atendimento);
			} catch (final SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		} else {
			Mensagem.add("Data final n�o pode ser anterior a data inicial.");
		}
		return false;
	}

	@Override
	public boolean alterar(final Atendimento atendimento) {

		final boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(),
				atendimento.getDataFinal());
		if (datas) {
			try {
				this.atendimentoDao.alterar(atendimento);
			} catch (final SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		} else {
			Mensagem.add("Data final n�o pode ser anterior a data inicial.");
		}
		return false;
	}

	@Override
	public List<Atendimento> obterLista() {
		this.atendimentoDao = new AtendimentoDao();
		final Cuidador usuarioLogado = SessionUtil.obtemUsuarioLogado();
		try {
			return this.atendimentoDao.obterLista(usuarioLogado.getId());
		} catch (final SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return null;
	}

	@Override
	public Atendimento obterPorId(final Long id) {
		this.atendimentoDao = new AtendimentoDao();
		Atendimento atendimento = null;
		try {
			atendimento = this.atendimentoDao.obterEvento(id);
		} catch (final SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return atendimento;
	}

	@Override
	public void excluir(final Atendimento atendimento) {
		this.atendimentoDao = new AtendimentoDao();
		this.atendimentoDao.excluir(atendimento);
	}

	/**
	 * Envia relatorio de atendimento ao familiar do paciente
	 *
	 * @param paciente
	 * @throws SQLException
	 */
	public boolean enviarRealatorio(final Atendimento atendimento) throws SQLException {

		if (StringUtils.isNotBlank(atendimento.getPaciente().getEmailFamiliar())) {

			final String email = atendimento.getPaciente().getEmailFamiliar();

			final byte[] anexo = this.geraRelatorioAtendimento(atendimento);

			if (StringUtils.isNotBlank(email)) {
				EmailUtil.enviarEmail(atendimento.getCuidador().getNome(), atendimento.getPaciente().getNomePaciente(),
						email, anexo);
			}

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Exporta relatorio de atendimento Pdf
	 *
	 * @param atendimento
	 * @return StreamedContent
	 * @throws SQLException
	 */
	public StreamedContent exportaRelatorioAtendimentoPdf(final Atendimento atendimento) throws SQLException {

		final ByteArrayInputStream relatorio = new ByteArrayInputStream(this.geraRelatorioAtendimento(atendimento));

		return new DefaultStreamedContent(relatorio);
	}

	/**
	 *
	 *
	 * @param atendimento
	 * @return
	 * @throws SQLException
	 */
	public byte[] geraRelatorioAtendimento(final Atendimento atendimento) throws SQLException {

		this.relatorioUtils = new RelatorioUtils();

		final List<RelatorioAtendimentoVO> vos = new ArrayList<>();
		vos.add(this.obtemRelatorioVO(atendimento));

		final byte[] relatorio = this.relatorioUtils.gerarRelatorioPdf(RelatorioEnum.RELATORIO_ATENDIMENTO, vos, null);

		return relatorio;
	}

	public RelatorioAtendimentoVO obtemRelatorioVO(final Atendimento atendimento) {
		this.eventoMedicacaoDAO = new EventoMedicacaoDAO();

		final RelatorioAtendimentoVO relatorioVO = new RelatorioAtendimentoVO();
		relatorioVO.setCuidadorNome(atendimento.getCuidador().getNome());
		relatorioVO.setCuidadorFone(atendimento.getCuidador().getTelefone());
		relatorioVO.setPacienteNome(atendimento.getPaciente().getNomePaciente());
		relatorioVO.setPacienteEndereco(atendimento.getLocalAtendimento());
		relatorioVO.setFamiliarNome(atendimento.getPaciente().getNomeFamiliar());
		relatorioVO.setDataInicial(atendimento.getDataInicial());
		relatorioVO.setDataFinal(atendimento.getDataFinal());

		final List<EventoMedicacao> eventos = this.eventoMedicacaoDAO.obterListaPorAtendimentoId(atendimento.getId());
		final RealatorioAtendimentoEventoVO relatorioEventoVO = new RealatorioAtendimentoEventoVO();
		for (final EventoMedicacao evento : eventos) {
			relatorioEventoVO.setDia(DateFormatUtils.format(evento.getDataHora(), "dd/MM/yyyy"));
			relatorioEventoVO.setHora(DateFormatUtils.format(evento.getDataHora(), "HH:mm"));
			relatorioEventoVO.setMediamento(evento.getMedicamentos().get(0).getNome());
			relatorioEventoVO.setDosagem(evento.getDescricao());
			if (evento.getStattus() != null) {
				relatorioEventoVO.setStatus(StatusEventoEnum.obterDescricaoPorId(evento.getStattus()));
			} else {
				relatorioEventoVO.setStatus("Nenhuma a��o registrada");
			}
			relatorioVO.getEventos().add(relatorioEventoVO);
		}
		return relatorioVO;
	}

	/**
	 * Seta endere�o do paciente em local de atendimento
	 *
	 * @param atendimento
	 */
	protected void verificaEnderecoAtendimento(final Atendimento atendimento) {
		if (atendimento.getEnderecoPaciente()) {
			atendimento.setLocalAtendimento(atendimento.getPaciente().getEndereco());
		}
	}

}
