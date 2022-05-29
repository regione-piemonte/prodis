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

import java.util.Calendar;

public class UtilityDateFestivita {

	private final static int[] valorimesi = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
	private final static String[] giorni = { "Domenica", "Lunedi", "Martedi", "Mercoledi", "Giovedi", "Venerdi",
			"Sabato" };

	public UtilityDateFestivita() {
	}

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

}
