/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.util;

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

	public static Calendar getCalendar(Date d) {
		if (d == null)
			return null;
		Calendar cal = Calendar.getInstance(Locale.ITALIAN);
		cal.setTime(d);
		return cal;
	}

}
