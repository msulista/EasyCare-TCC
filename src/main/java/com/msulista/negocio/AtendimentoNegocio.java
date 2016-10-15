package com.msulista.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import com.msulista.dao.AtendimentoDao;
import com.msulista.dao.EventoMedicacaoDAO;
import com.msulista.entidade.Atendimento;
import com.msulista.entidade.EventoMedicacao;
import com.msulista.enums.RelatorioEnum;
import com.msulista.enums.StatusEventoEnum;
import com.msulista.util.DateUtil;
import com.msulista.util.EmailUtil;
import com.msulista.util.Mensagem;
import com.msulista.util.RelatorioUtils;
import com.msulista.vo.RealatorioAtendimentoEventoVO;
import com.msulista.vo.RelatorioAtendimentoVO;


public class AtendimentoNegocio implements NegocioBase<Atendimento> {

	private AtendimentoDao atendimentoDao;
	private RelatorioUtils relatorioUtils;
	private EventoMedicacaoDAO eventoMedicacaoDAO;

	@Override
	public boolean salvar(final Atendimento atendimento) {

		boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(), atendimento.getDataFinal());
		this.verificaEnderecoAtendimento(atendimento);
		if (datas) {
			this.atendimentoDao = new AtendimentoDao();
			try {
				this.atendimentoDao.salvar(atendimento);
			} catch (final SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		}else {
			Mensagem.add("Data final não pode ser anterior a data inicial.");
		}
		return false;
	}

	@Override
	public boolean alterar(final Atendimento atendimento) {
		
		boolean datas = DateUtil.verificaDataFinalAposDataInicial(atendimento.getDataInicial(), atendimento.getDataFinal());
		if (datas) {
			try {
				this.atendimentoDao.alterar(atendimento);
			} catch (SQLException e) {
				Mensagem.add("Erro ao acessar o banco de dados.");
			}
			return true;
		}else {
			Mensagem.add("Data final não pode ser anterior a data inicial.");
		}
		return false;
	}

	@Override
	public List<Atendimento> obterLista() {
		this.atendimentoDao = new AtendimentoDao();
		try {
			return this.atendimentoDao.obterLista();
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
		} catch (SQLException e) {
			Mensagem.add("Erro ao acessar o banco de dados.");
		}
		return atendimento;
	}

	@Override
	public void excluir(Atendimento atendimento) {
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
			
			byte[] anexo = this.geraRelatorioAtendimento(atendimento);
			
			if (StringUtils.isNotBlank(email)) {
				EmailUtil.enviarEmail(atendimento.getCuidador().getNome(), atendimento.getPaciente().getNomePaciente(), email, anexo);
			}
			
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 
	 * 
	 * @param atendimento
	 * @return
	 * @throws SQLException
	 */
	public byte[] geraRelatorioAtendimento(Atendimento atendimento) throws SQLException {
		
		List<RelatorioAtendimentoVO> vos = new ArrayList<>();
		relatorioUtils = new RelatorioUtils();
		atendimentoDao = new AtendimentoDao();
		eventoMedicacaoDAO = new EventoMedicacaoDAO();
		
		RelatorioAtendimentoVO relatorioVO = new RelatorioAtendimentoVO();
		relatorioVO.setCuidadorNome(atendimento.getCuidador().getNome());
		relatorioVO.setCuidadorFone(atendimento.getCuidador().getTelefone());
		relatorioVO.setPacienteNome(atendimento.getPaciente().getNomePaciente());
		relatorioVO.setPacienteEndereco(atendimento.getLocalAtendimento());
		relatorioVO.setFamiliarNome(atendimento.getPaciente().getNomeFamiliar());
				
		List<EventoMedicacao> eventos = eventoMedicacaoDAO.obterListaPorAtendimentoId(atendimento.getId());
		RealatorioAtendimentoEventoVO relatorioEventoVO = new RealatorioAtendimentoEventoVO();
		for (EventoMedicacao evento : eventos) {
			relatorioEventoVO.setDia(DateFormatUtils.format(evento.getDataHora(), "dd/MM/yyyy"));
			relatorioEventoVO.setHora(DateFormatUtils.format(evento.getDataHora(), "HH:mm"));
			relatorioEventoVO.setMediamento(evento.getMedicamentos().get(0).getNome());
			relatorioEventoVO.setDosagem(evento.getDescricao());
			if (evento.getStattus() != null) {
				relatorioEventoVO.setStatus(StatusEventoEnum.obterDescricaoPorId(evento.getStattus()));
			}else {
				relatorioEventoVO.setStatus("Nenhuma ação registrada");
			}
			relatorioVO.getEventos().add(relatorioEventoVO);
		}
		byte[] relatorio = this.relatorioUtils.gerarRelatorioPdf(RelatorioEnum.RELATORIO_ATENDIMENTO, vos, null);
		
		return relatorio;
	}
	
//	private Map<String, Object> criaMapParametros(Atendimento atendimento) {
//		
//        final Map<String, Object> parametros = new HashMap<String, Object>();
//
//        String dtIni = null;
//    	String dtFim = null;
//
//        if (atendimento.getDataInicial() != null) {
//            dtIni = DateFormatUtils.format(atendimento.getDataInicial(), "dd/MM/yyyy");
//        }
//        if (atendimento.getDataFinal() != null) {
//            dtFim = DateFormatUtils.format(atendimento.getDataFinal(), "dd/MM/yyyy");
//        }
//        parametros.put("dataInicial", dtIni);
//        parametros.put("dataFinal", dtFim);
//        parametros.put("cuidadorNome", atendimento.getCuidador().getNome());
//        parametros.put("cuidadorFone", atendimento.getCuidador().getTelefone());
//        parametros.put("pacienteNome", atendimento.getPaciente().getNomePaciente());
//        parametros.put("pacienteEndereco", atendimento.getLocalAtendimento());
//        if (StringUtils.isNotBlank(atendimento.getPaciente().getNomeFamiliar())) {
//        	parametros.put("familiarNome", atendimento.getPaciente().getNomeFamiliar());
//		}
//
//        return parametros;
//    }
	
	
	/**
	 * Seta endereço do paciente em local de atendimento
	 * 
	 * @param atendimento
	 */
	protected void verificaEnderecoAtendimento(Atendimento atendimento) {
		if (atendimento.getEnderecoPaciente()) {
			atendimento.setLocalAtendimento(atendimento.getPaciente().getEndereco());
		}
	}

}
