package com.msulista.manager;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.msulista.dao.AgendaDao;
import com.msulista.entidade.Agenda;
import com.ocpsoft.pretty.faces.annotation.URLMapping;
import com.ocpsoft.pretty.faces.annotation.URLMappings;

@ManagedBean
@ViewScoped
@URLMappings(mappings = {
		@URLMapping(id = "agenda", pattern = "/agenda", viewId = "/pages/agenda/agenda.xhtml")
})
public class AgendaManager implements Serializable {

	
	private static final long serialVersionUID = -4557100553498435618L;
	
	private ScheduleModel eventModel;
	private AgendaDao agendaDao;
	private Agenda agenda;
	private List<Agenda> eventos;
	
	
	@PostConstruct
	public void inicializar() {
		agendaDao = new AgendaDao();
		agenda = new Agenda();
		eventModel = new DefaultScheduleModel();
		
		try {
			
			eventos = agendaDao.obterLista();
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Erro ao executar Sql."));
		}
		
		for (Agenda evento : eventos) {
			
			DefaultScheduleEvent evt = new DefaultScheduleEvent();
			evt.setData(evento.getId());
			evt.setTitle(evento.getTitulo());
			evt.setStartDate(evento.getDataInicio());
			evt.setEndDate(evento.getDataFim());
			evt.setDescription(evento.getDescricao());
			evt.setAllDay(true);
			evt.setEditable(true);
			
			eventModel.addEvent(evt);
		}
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}
	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public AgendaDao getAgendaDao() {
		return agendaDao;
	}
	public void setAgendaDao(AgendaDao agendaDao) {
		this.agendaDao = agendaDao;
	}

	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Agenda> getEventos() {
		return eventos;
	}
	public void setEventos(List<Agenda> eventos) {
		this.eventos = eventos;
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//
//	public Date getRandomDate(Date base) {
//		Calendar date = Calendar.getInstance();
//		date.setTime(base);
//		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random day of month
//
//		return date.getTime();
//	}
//
//	public Date getInitialDate() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
//
//		return calendar.getTime();
//	}
//
//	public ScheduleModel getEventModel() {
//		return eventModel;
//	}
//
//	private Calendar today() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
//
//		return calendar;
//	}
//
//	private Date previousDay8Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
//		t.set(Calendar.HOUR, 8);
//
//		return t.getTime();
//	}
//
//	private Date previousDay11Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) - 1);
//		t.set(Calendar.HOUR, 11);
//
//		return t.getTime();
//	}
//
//	private Date today1Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.HOUR, 1);
//
//		return t.getTime();
//	}
//
//	private Date theDayAfter3Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.DATE, t.get(Calendar.DATE) + 2);
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.HOUR, 3);
//
//		return t.getTime();
//	}
//
//	private Date today6Pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.HOUR, 6);
//
//		return t.getTime();
//	}
//
//	private Date nextDay9Am() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.AM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
//		t.set(Calendar.HOUR, 9);
//
//		return t.getTime();
//	}
//
//	private Date nextDay11Am() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.AM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
//		t.set(Calendar.HOUR, 11);
//
//		return t.getTime();
//	}
//
//	private Date fourDaysLater3pm() {
//		Calendar t = (Calendar) today().clone();
//		t.set(Calendar.AM_PM, Calendar.PM);
//		t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
//		t.set(Calendar.HOUR, 3);
//
//		return t.getTime();
//	}
//
//	public ScheduleEvent getEvent() {
//		return event;
//	}
//
//	public void setEvent(ScheduleEvent event) {
//		this.event = event;
//	}
//
//	public void addEvent(ActionEvent actionEvent) {
//		if (event.getId() == null)
//			eventModel.addEvent(event);
//		else
//			eventModel.updateEvent(event);
//
//		event = new DefaultScheduleEvent();
//	}
//
//	public void onEventSelect(SelectEvent selectEvent) {
//		event = (ScheduleEvent) selectEvent.getObject();
//	}
//
//	public void onDateSelect(SelectEvent selectEvent) {
//		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
//	}
//
//	public void onEventMove(ScheduleEntryMoveEvent event) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved",
//				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//		addMessage(message);
//	}
//
//	public void onEventResize(ScheduleEntryResizeEvent event) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized",
//				"Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
//
//		addMessage(message);
//	}
//
//	private void addMessage(FacesMessage message) {
//		FacesContext.getCurrentInstance().addMessage(null, message);
//	}
}
