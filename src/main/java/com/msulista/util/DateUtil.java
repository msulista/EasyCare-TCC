package com.msulista.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static boolean verificaData(final String data) {
		return (data.matches("\\d{2}/\\d{2}/\\d{4}"));
	}

	public static boolean verificaHorario(final String horario) {
		return (horario.matches("\\d{2}:\\d{2}"));
	}

	public static Date stringToDate(final String data) throws ParseException {
		return (new SimpleDateFormat("dd/MM/yyyy").parse(data));
	}

	public static Date stringToDatePostgre(String data) {
		final SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
		Date d1 = null;
		try {
			d1 = f.parse(data);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		data = format.format(d1);
		final Date dataSql = java.sql.Date.valueOf(data);
		return dataSql;
	}

	public static Date stringToHour(final String data) throws ParseException {

		return (new SimpleDateFormat("HH:mm").parse(data));
	}

	public static Date stringToHourPostgre(String hora) throws ParseException {
		final SimpleDateFormat h = new SimpleDateFormat("HH:mm");
		Date h1 = null;
		h1 = h.parse(hora);
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		hora = format.format(h1);
		System.out.println("Hora formatada: " + hora);

		final Date horaSql = java.sql.Date.valueOf(hora);
		return horaSql;
	}

	public static Date stringToDateHour(final String data) throws ParseException {
		return (new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(data));
	}

	public static String dateToStringDate(final Date data) {
		return (new SimpleDateFormat("dd/MM/yyyy").format(data));
	}

	public static String hourToStringHour(final Date data) {
		return (new SimpleDateFormat("HH:mm").format(data));
	}

	public static String dateHourToString(final Date data) {
		final SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		final String dataString = formatador.format(data);
		return (dataString);
	}

	public static String hourToString(final Date data) {
		final SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
		final String dataString = formatador.format(data);
		return (dataString);
	}

	public static Date addDays(final Date atual, final int days) {
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(atual);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return (calendar.getTime());
	}

	public static long differenceInDays(final Date start, final Date end) {
		final long dif = end.getTime() - start.getTime();
		return (Math.abs(dif / (24 * 60 * 60 * 1000)));
	}

	public static String pegaHoraDoSistema() {

		final Calendar calendar = new GregorianCalendar();
		final SimpleDateFormat out = new SimpleDateFormat("HH:mm:ss");
		final Date date = new Date();
		calendar.setTime(date);
		final String hora = out.format(calendar.getTime());
		return hora;
	}

	public static String pegaDataDoSistema() {

		final Calendar calendar = new GregorianCalendar();
		final SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
		final Date date = new Date();
		calendar.setTime(date);
		final String data = out.format(calendar.getTime());
		return data;
	}

	public static Date getProximoDiaUtil() {
		Date data = new Date();
		final GregorianCalendar calendar = new GregorianCalendar();

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

	public static boolean verificaDiaUtil(final Date data) {
		final GregorianCalendar calendar = new GregorianCalendar();

		if (data != null) {
			calendar.setTime(data);
			if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 1 || calendar.get(GregorianCalendar.DAY_OF_WEEK) == 7) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public static boolean verificaDataValida(final Date data, final Date novaData) {
		if (novaData != null) {
			if (data.before(novaData) || data.compareTo(novaData) == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Retorna true para dataFinal apos data inicial ou dataInicial igual DataFinal
	 * 
	 * @param dataInicial Data inicial
	 * @param dataFinal Data Final
	 * @return boolean
	 */
	public static boolean verificaDataFinalAposDataInicial(final Date dataInicial, final Date dataFinal) {
		if (dataFinal != null ) {
			if (dataInicial.before(dataFinal) || dataInicial.compareTo(dataFinal) == 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static Date retornaDataFimAposInicio(Date data) {
		final GregorianCalendar calendar = new GregorianCalendar();

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

	public static Date retornaDataFimAntesDoNovoInicio(Date data) {
		final GregorianCalendar calendar = new GregorianCalendar();

		calendar.setTime(data);
		if (calendar.get(GregorianCalendar.DAY_OF_WEEK) == 2) {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -3);
		} else {
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		}
		data = calendar.getTime();
		return data;
	}
		
	public static int getIdade(Date data) {
		Calendar cData = Calendar.getInstance();
		Calendar cHoje= Calendar.getInstance();
		cData.setTime(data);
		cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
		int idade = cData.after(cHoje) ? -1 : 0;
		cData.setTime(data);
		idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
		return idade;
	}
}
