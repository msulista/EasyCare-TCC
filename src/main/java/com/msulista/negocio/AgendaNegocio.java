package com.msulista.negocio;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.msulista.dao.AgendaDao;
import com.msulista.entidade.EventoAtendimento;
import com.msulista.util.Mensagem;

public class AgendaNegocio {

	private AgendaDao agendaDao;
	
	public void gravar(EventoAtendimento eventoAtendimento) {
		
		this.setaDataFim(eventoAtendimento);
		if (eventoAtendimento.getId() == null) {
				
			this.salvar(eventoAtendimento);
		}else {
				
			this.alterar(eventoAtendimento);
		}
	}
	
	private void salvar(EventoAtendimento eventoAtendimento) {
		
		try {
			this.agendaDao.salvar(eventoAtendimento);
			Mensagem.add("Evento salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
//			Mensagem.add("Ocorreu um erro ao salvar novo evento.");
		}
		
	}
	
	private void alterar(EventoAtendimento eventoAtendimento) {
		
		try {
			this.agendaDao.alterar(eventoAtendimento);
		} catch (SQLException e) {
			e.printStackTrace();
			Mensagem.add("Ocorreu um erro ao alterar o evento.");
		}
	}
		
	private Boolean verificaDataSeDataInicioMaiorQueFim(Date dtinicio, Date dtFim) {
		Calendar calIni = Calendar.getInstance();
		Calendar calFim = Calendar.getInstance();
		calIni.setTime(dtinicio);
		calIni.set(Calendar.HOUR_OF_DAY, 0);
		calIni.set(Calendar.MINUTE, 0);
		calIni.set(Calendar.SECOND, 0);
		calIni.set(Calendar.MILLISECOND, 0);
		calFim.setTime(dtFim);
		
		if (calFim.before(calIni)) {
			Mensagem.add("Data inicial não pode ser maior que data final.");
			return false;
		}
		return true;
	}
	
	private void setaDataFim(EventoAtendimento evento) {
		Calendar calIni = Calendar.getInstance();
		Calendar calFim = Calendar.getInstance();
		calIni.setTime(evento.getDataInicio());
//		calIni.set(Calendar.HOUR_OF_DAY, 0);
		calIni.set(Calendar.MINUTE, 5);
		calIni.set(Calendar.SECOND, 0);
		calIni.set(Calendar.MILLISECOND, 0);
		calFim.setTime(calIni.getTime());
		evento.setDataFim(calFim.getTime());
	}
		
	public List<EventoAtendimento> obterLista() {
		
		this.agendaDao = new AgendaDao();
		
		List<EventoAtendimento> retorno = null;
		try {
			retorno = this.agendaDao.obterLista();
		} catch (SQLException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		return retorno; 
	}
	
	public EventoAtendimento obterAgenda(Long id) {
		
		this.agendaDao = new AgendaDao();
		return this.agendaDao.obterEvento(id);
	}
	
//	 private EventoAtendimento ajustaHora(EventoAtendimento evento) {
//		 
//		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//		 Date hora = Calendar.getInstance().getTime();
//		 String dataFormatada = sdf.format(hora);
//		 
//	        Calendar calendar = Calendar.getInstance();
//	        calendar.setTime(evento.getHoraAdministracao());
//	        calendar.g
//	        if(calendar.g 12){
//	        	calendar.set(Calendar.AM_PM, Calendar.PM);
//	        	calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
//	        	calendar.set(Calendar.HOUR, 9);
//	        	
//	        }
//	         
//	        return calendar.getTime();
//	}
}
