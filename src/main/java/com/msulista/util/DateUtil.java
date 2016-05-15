package com.msulista.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static boolean verificaData(String data) { 
		return (data.matches("\\d{2}/\\d{2}/\\d{4}"));
	}

	public static boolean verificaHorario(String horario) {
		return (horario.matches("\\d{2}:\\d{2}"));
	}

	public static Date stringToDate(String data) throws ParseException {
		return (new SimpleDateFormat("dd/MM/yyyy").parse(data));
	}

	public static Date stringToDatePostgre(String data) {
		SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
		Date d1 = null;
		try {
			d1 = f.parse(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		data = format.format(d1);
		Date dataSql = java.sql.Date.valueOf(data);
		return dataSql;
	}

	public static Date stringToHour(String data) throws ParseException {

		return (new SimpleDateFormat("HH:mm").parse(data));
	}

	public static Date stringToHourPostgre(String hora) throws ParseException {
		SimpleDateFormat h = new SimpleDateFormat("HH:mm");
		Date h1 = null;
		h1 = h.parse(hora);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		hora = format.format(h1);
		System.out.println("Hora formatada: " + hora);

		Date horaSql = java.sql.Date.valueOf(hora);
		return horaSql;
	}

	public static Date stringToDateHour(String data) throws ParseException {
		return (new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
	}

	public static String dateToStringDate(Date data) {
		return (new SimpleDateFormat("dd/MM/yyyy").format(data));
	}

	public static String hourToStringHour(Date data) {
		return (new SimpleDateFormat("HH:mm").format(data));
	}

	public static String dateHourToString(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataString = formatador.format(data);
		return (dataString);
	}

	public static String hourToString(Date data) {
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		String dataString = formatador.format(data);
		return (dataString);
	}

	public static Date addDays(Date atual, int days) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(atual);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return (calendar.getTime());
	}

	public static long differenceInDays(Date start, Date end) {
		long dif = end.getTime() - start.getTime();
		return (Math.abs(dif / (24 * 60 * 60 * 1000)));
	}

	public static String pegaHoraDoSistema() {

		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat out = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		calendar.setTime(date);
		String hora = out.format(calendar.getTime());
		return hora;
	}

	public static String pegaDataDoSistema() {

		Calendar calendar = new GregorianCalendar();
		SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		calendar.setTime(date);
		String data = out.format(calendar.getTime());
		return data;
	}

	public static Date getProximoDiaUtil() {
		Date data = new Date();
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(data);
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 6) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 3);
		} else if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 2);
		} else {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		data = calendar.getTime();
		return data;
	}

	public static boolean verificaDiaUtil(Date data) {
		GregorianCalendar calendar = new GregorianCalendar();

		if(data!=null){
		calendar.setTime(data);
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1 || calendar.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
			return false;
		} else {
			return true;
		}
		}else{
			return true;
		}
	}

	public static boolean verificaDataValida(Date data, Date novaData) {
		if (novaData != null) {
			if (data.before(novaData) || data.compareTo(novaData) == 0) {
				return true;
			} else {
				return false;
			}
		}else{
			return true;
		}
	}
	public static Date retornaDataFimAposInicio(Date data){
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(data);
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 6) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 3);
		} else if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 2);
		} else {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		}
		data = calendar.getTime();
		return data;
	}
	public static Date retornaDataFimAntesDoNovoInicio(Date data){
		GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(data);
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 2) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -3);
		} else {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		}
		data = calendar.getTime();
		return data;
	}
	// public static Date DataDoSistemaMySql(){
	// SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	// Date dataAtual = new Date();
	// //Date dataMySql = java.sql.Date.valueOf();
	// return data;
	// }
}
