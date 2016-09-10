package com.msulista.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Interval;

public class DateUtil {

	private static final String DATA_FORMATO = "dd/MM/yyyy";
	private static final String HORA_FORMATO = "HH:mm";

	public static boolean verificaData(final String data) {
		return (data.matches("\\d{2}/\\d{2}/\\d{4}"));
	}

	public static boolean verificaHorario(final String horario) {
		return (horario.matches("\\d{2}:\\d{2}"));
	}

	public static Date stringToDate(final String data) throws ParseException {
		return (new SimpleDateFormat(DATA_FORMATO).parse(data));
	}

	public static Date stringToDatePostgre(String data) {
		final SimpleDateFormat f = new SimpleDateFormat(DATA_FORMATO);
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

		return (new SimpleDateFormat(HORA_FORMATO).parse(data));
	}

	public static Date stringToHourPostgre(String hora) throws ParseException {
		final SimpleDateFormat h = new SimpleDateFormat(HORA_FORMATO);
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
		return (new SimpleDateFormat(DATA_FORMATO).format(data));
	}

	public static String hourToStringHour(final Date data) {
		return (new SimpleDateFormat(HORA_FORMATO).format(data));
	}

	public static String dateHourToString(final Date data) {
		final SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		final String dataString = formatador.format(data);
		return (dataString);
	}

	public static String hourToString(final Date data) {
		final SimpleDateFormat formatador = new SimpleDateFormat(HORA_FORMATO);
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
		final SimpleDateFormat out = new SimpleDateFormat(DATA_FORMATO);
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
	 * Retorna true para dataFinal apos data inicial ou dataInicial igual
	 * DataFinal
	 *
	 * @param dataInicial
	 *            Data inicial
	 * @param dataFinal
	 *            Data Final
	 * @return boolean
	 */
	public static boolean verificaDataFinalAposDataInicial(final Date dataInicial, final Date dataFinal) {
		if (dataFinal != null) {
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

	public static int getIdade(final Date data) {
		final Calendar cData = Calendar.getInstance();
		final Calendar cHoje = Calendar.getInstance();
		cData.setTime(data);
		cData.set(Calendar.YEAR, cHoje.get(Calendar.YEAR));
		int idade = cData.after(cHoje) ? -1 : 0;
		cData.setTime(data);
		idade += cHoje.get(Calendar.YEAR) - cData.get(Calendar.YEAR);
		return idade;
	}

	public static Calendar dateToCalendarTimeZeroTrueOrFalse(final Date date, final boolean setTimeToZero) {
		final Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);
		if (setTimeToZero) {
			calendario.set(Calendar.HOUR_OF_DAY, 0);
			calendario.set(Calendar.MINUTE, 0);
			calendario.set(Calendar.SECOND, 0);
			calendario.set(Calendar.MILLISECOND, 0);
		}
		return calendario;
	}

	public static boolean comparaHr1MaiorHr2(final Date hr1, final Date hr2) {

		final SimpleDateFormat sdf = new SimpleDateFormat(HORA_FORMATO);
		final String hora1 = sdf.format(hr1);
		final String hora2 = sdf.format(hr2);
		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = sdf.parse(hora1);
			dt2 = sdf.parse(hora2);
		} catch (final ParseException e) {
			e.printStackTrace();
		}

		return dt1.after(dt2);
	}

	public static int calculaNumeroDeDias(final Date dataInicial, final Date dataFinal) {

		final DateTime dtInicial = new DateTime(dataInicial);
		final DateTime dtFinal = new DateTime(dataFinal);

		final Days dias = Days.daysBetween(dtInicial, dtFinal);
		final int dia = dias.getDays();
		return dia + 1;
	}

	public static Long verificaHoraAlareme(final Date dataHora) {

		final DateTime horaAtual = new DateTime();
		final DateTime horaEvento = new DateTime(dataHora);

		final Interval intervalo = new Interval(horaAtual, horaEvento);
		final Duration duracao = intervalo.toDuration();

		return duracao.getStandardMinutes();
	}

}
