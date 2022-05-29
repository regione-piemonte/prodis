/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class ProdisDateUtils {
	public final static String DATE_FORMAT = "dd/MM/yyyy";
	public final static String FULL_DATE_FORMAT = "dd/MM/yyyy HH.mm.ss";

	/**
	 * Restituisce una data nel formato passato in input
	 * 
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	private static String formatDate(Date date, String dateFormat) {
		if (date == null)
			return "";

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);

		return sdf.format(date);
	}

	/**
	 * Restituisce una data in formato gg/mm/aaaa
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATE_FORMAT);
	}

	/**
	 * Restituisce una data in formato gg/mm/aaaa hh.mm.ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatFullDate(Date date) {
		return formatDate(date, FULL_DATE_FORMAT);
	}

	/**
	 * Restituisce una data in formato gg/mm/aaaa
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatDate(String date) {
		return formatDate(parseDate(date));
	}

	/**
	 * Restituisce una data in formato gg/mm/aaaa hh.mm.ss
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatFullDate(String date) {
		return formatFullDate(parseDate(date));
	}

	/**
	 * Questo metodo restituisce un oggetto Date partendo da una stringa formattata
	 * secondo il pattern contenuto nella costante DATE_FORMAT
	 *
	 * @param stringDate la stringa contenente la data da parsificare
	 *
	 * @return un'istanza della classe java.util.Date, o <b>null</b> nel caso in cui
	 *         non sia possibile parsificare la stringa
	 */
	public static Date parseDate(String stringDate) {
		return parseDate(stringDate, DATE_FORMAT);
	}

	/**
	 * Questo metodo restituisce un oggetto Date partendo da una stringa formattata
	 * secondo il pattern passato come parametro
	 *
	 * @param stringDate la stringa contenente la data da parsificare
	 * @param dateFormat il pattern con cui effettuare la parsificazione
	 *
	 * @return un'istanza della classe java.util.Date, o <b>null</b> nel caso in cui
	 *         non sia possibile parsificare la stringa
	 */

	public static Date parseDate(String stringDate, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		Date result = sdf.parse(stringDate, new ParsePosition(0));

		// Questo controllo � reso necessario dal comportamento della SimpleDateFormat
		// sugli anni, con pattern che prevedono gli anni di lunghezza differente da
		// due.
		// In questi casi, viene considerato valido qualsiasi anno, anche di lunghezza
		// superiore a quella prevista dal pattern e fino a nove cifre
		if (result != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(result);
			int year = cal.get(Calendar.YEAR);
			char yearSymbol = sdf.getDateFormatSymbols().getLocalPatternChars().charAt(sdf.YEAR_FIELD);
			int yearLength = (dateFormat.lastIndexOf(yearSymbol) - dateFormat.indexOf(yearSymbol)) + 1;
			if (("" + year).length() != yearLength)
				result = null;
		}

		return result;// sdf.parse(stringDate, new ParsePosition(0));
	}

	// Questo metodo si comporta come il compareTo della classe Date, ma al
	// contrario
	// di questo accetta anche un parametro null. La priorit� del null rispetto
	// ai valori non nulli viene stabilita dal flag boolean nullFirst, che settato a
	// true indica che i null precedono gli altri valori
	public static int compareTo(Date data1, Date data2, boolean nullFirst) {
		int result;

		if (data1 != null && data2 != null) {
			result = data1.compareTo(data2);
		} else {
			int nullFirstMultiplier;

			if (nullFirst)
				nullFirstMultiplier = 1;
			else
				nullFirstMultiplier = -1;

			if (data1 == null) {
				if (data2 == null)
					result = 0;
				else
					result = -1;
			} else {
				result = 1;
			}

			result = result * nullFirstMultiplier;
		}

		return result;
	}

	public static int extractDayFromDate(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static int extractMonthFromDate(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(date);
		return (cal.get(Calendar.MONTH) + 1);
	}

	public static int extractYearFromDate(Date date) {
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Data ora corrente
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * Data ora corrente
	 * 
	 * @return
	 */
	public static String getCurrentDateGGMMAAAA() {
		return formatDate(getCurrentDate(), DATE_FORMAT);
	}

	private final static int[] valorimesi = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
	private final static String[] giorni = { "Domenica", "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi",
			"Sabato" };

	// Utilizzando la formula di Carrol, il metodo per il giorno di Pasquetta dato
	// l'anno e':

	public static String trovaPasquetta(int iYear) {
		int a, b, c, d, e, f, g, h, i, k, l, m, n, p;
		int iDay, iMonth;
		String pasqtta = "";

		a = iYear % 19;
		b = (int) (iYear / 100);
		c = iYear % 100;
		d = (int) (b / 4);
		e = b % 4;
		f = (int) ((b + 8) / 25);
		g = (int) ((b - f + 1) / 3);
		h = (19 * a + b - d - g + 15) % 30;
		i = (int) (c / 4);
		k = c % 4;
		l = (32 + 2 * e + 2 * i - h - k) % 7;
		m = (int) ((a + 11 * h - 221) / 451);
		n = (int) (h + l - 7 * m + 114) / 31;
		p = (h + l - 7 * m + 114) % 31;

		iDay = p + 1;
		iMonth = n;
		pasqtta = String.valueOf(iDay + 1) + String.valueOf(iMonth);

		// return "Il Giorno di Pasqua nell'anno "+iYear+" e' "+iDay+"/"+iMonth;
		return pasqtta;
	}

// Con il metodo di Carrol troviamo il giorno dell'anno se e' lunedi, martedi, ecc

//    private static String calcolaCarrol(GregorianCalendar gc) {
//
//
//    int giorno=gc.get(Calendar.DAY_OF_MONTH);
//    int mese=gc.get(Calendar.MONTH);
//    int anno=gc.get(Calendar.YEAR);
//
//
//    int secolo=(int)anno/100;
//    anno%=100;
//    int sec=(3-(secolo%4))*2;
//    int a=(int)anno/12;
//    int rest_a= anno%12;
//    if (rest_a>7)rest_a+=2;
//    else if (rest_a>3)rest_a++;
//
//
//    int somma=sec+a+rest_a+valorimesi[mese]+giorno;
//
//
//    if (gc.isLeapYear(gc.get(Calendar.YEAR))&&mese<2) somma--;
//
//
//    return giorni[somma%=7];
//  }

	/**
	 * Questo metodo calcola il giorno della settimana usando le costanti della
	 * classe Calendar.
	 */
	private static String trovaGiorno(Calendar gc) {

		String result = null;
		switch (gc.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.SUNDAY:
			result = giorni[0];
			break;
		case Calendar.MONDAY:
			result = giorni[1];
			break;
		case Calendar.TUESDAY:
			result = giorni[2];
			break;
		case Calendar.WEDNESDAY:
			result = giorni[3];
			break;
		case Calendar.THURSDAY:
			result = giorni[4];
			break;
		case Calendar.FRIDAY:
			result = giorni[5];
			break;
		case Calendar.SATURDAY:
			result = giorni[6];
			break;

		}
		return result;
	}

//Metodo Booleano che restituisce true se il giorno della settimana e' sabato o domenica  o
// e' un giorno di festa programmato (festivita' fisse)
// Come richiesto per il LAVORO sabato NON E' considerato giorno festivo

	public static boolean festivita(Calendar gc) {

		boolean valore = false;

		String pasquetta = trovaPasquetta(gc.get(Calendar.YEAR));
		String keGiorno = trovaGiorno(gc);

		int giorno = gc.get(Calendar.DAY_OF_MONTH);
		int mese = gc.get(Calendar.MONTH) + 1;

		String day = String.valueOf(giorno);
		String month = String.valueOf(mese);

		String controlloFestivo = day + month;

		if (keGiorno.equalsIgnoreCase("Domenica") /* || keGiorno.equalsIgnoreCase("Sabato") */) {
			valore = true;
		}

		if (controlloFestivo.equalsIgnoreCase("11") // capodanno
				|| controlloFestivo.equalsIgnoreCase("61") // epifania
				|| controlloFestivo.equalsIgnoreCase("254") // liberazione
				|| controlloFestivo.equalsIgnoreCase("15") // festa del lavoro
				|| controlloFestivo.equalsIgnoreCase("26") // festa repubblica
				|| controlloFestivo.equalsIgnoreCase("158") // ferragosto
				|| controlloFestivo.equalsIgnoreCase("111") // tutti i santi
				|| controlloFestivo.equalsIgnoreCase("812") // immacolata
				|| controlloFestivo.equalsIgnoreCase("2512") // natale
				|| controlloFestivo.equalsIgnoreCase("2612") // santo stefano
				|| controlloFestivo.equalsIgnoreCase(pasquetta)) {
			valore = true;
		}

		return valore;
	}

	public static Calendar getCalendar(Date d) {
		if (d == null)
			return null;
		Calendar cal = Calendar.getInstance(Locale.ITALIAN);
		cal.setTime(d);
		return cal;
	}

	/**
	 * restituisce un oggetto calendar impostando la data in input troncata e ore
	 * minuti secondi in input
	 */
	public static Calendar getCalendar(Date d, int ore, int minuti, int secondi) {
		if (d == null)
			return null;
		Calendar cal = Calendar.getInstance(Locale.ITALIAN);
		cal.setTime(d);
		// cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, ore);
		cal.set(Calendar.MINUTE, minuti);
		cal.set(Calendar.SECOND, secondi);
		return cal;
	}

	public static Date aggiungiMinutiAData(java.util.Date date, int minuti) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.MINUTE, minuti);
		return cal.getTime();
	}

	public static Date aggiungiGiorniAData(java.util.Date date, int days) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static Date aggiungiMesiAData(java.util.Date date, int mesi) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.MONTH, mesi);
		return cal.getTime();
	}

	// aggiunge anni a una data e restituisce la data
	public static Date aggiungiAnniAData(java.util.Date date, int anni) {
		Calendar cal = getCalendar(date);
		cal.add(Calendar.YEAR, anni);
		return cal.getTime();
	}

}
