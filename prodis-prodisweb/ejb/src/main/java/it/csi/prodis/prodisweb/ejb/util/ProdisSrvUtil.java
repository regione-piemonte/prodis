/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.util;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;

import it.csi.prodis.prodisweb.ejb.entity.ProTComune;

public class ProdisSrvUtil {

	final static String consonanti = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";
	final static String numeri = "0123456789";

	// Tabella per la verifica del posizionamento di lettere e numeri
	static int[] tabCF1 = {0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0};

	// Tabelle per la verifica del mese
	static char[] tabCF2 = {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
	static int[] tabCF3 = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	//private static final char[] mese = new char[] {'A', 'B', 'C', 'D', 'E', 'H', 'L', 'M', 'P', 'R', 'S', 'T'};
	private static String [] festivita = {"1-1","6-1","25-4","1-5","2-6","15-8","1-11","8-12","25-12","26-12"};

	public static final java.sql.Date convert(java.util.Date data) {
		return (data != null) ? new java.sql.Date(data.getTime()) : null;
	}

	public static final java.util.Date convert(java.sql.Date data) {
		return (data != null) ? new java.util.Date(data.getTime()) : null;
	}

	public static final Long convert(BigDecimal number) {
		if (number != null) {
			return number.longValue();
		} else {
			return Long.valueOf(0l);
		}
	}

	public static final BigDecimal convert(Long number) {
		BigDecimal result = null;
		if (number != null
				&& number.longValue() != 0) {
			result = new BigDecimal(number);
		} else {
			result = new BigDecimal(0l);
		}
		return result;
	}

	public static final String concatena (List<String> lista) {
		String result = null;
		if (lista != null) {
			for (String l : lista) {
				if (result == null) {
					result = l;
				} else {
					result += "|"+l;
				}
			}
		}
		return result;
	}

	public static final boolean isNotNullAndNotZero (BigDecimal val) {
		if (val == null) {
			return false;
		} else {
			return !(val.longValue()==0);
		}
	}

	public static final boolean isNotEmpty(String stringa) {
		return !isEmpty(stringa);
	}

	public static final boolean isEmpty(String stringa) {
		if (stringa == null) {
			return true;
		} else {
			return stringa.length()==0;
		}
	}

	public static final String toUpperCase(String param) {
		return param != null ? param.trim().toUpperCase() : null;
	}
	// Controlla la correttezza di un codice fiscale
	public static boolean controllaCF(String codiceFiscale) {
		// Verifica la lunghezza della stringa
		//System.out.println("CodiceFiscale, ENTRA in controllaCF: " + codiceFiscale.length());
		try {
			if (codiceFiscale.length() != 16) {
				return false;
			}

			// prima verifica che il carattere di controllo corrisponda ai primi 15 caratteri
			char crt = calcolaControlCrt(codiceFiscale.toUpperCase());
			if (codiceFiscale.toUpperCase().charAt(15) != crt) {
				return false;
			}

			// controllo per CF con omocodia, ovvero con almeno uno fra i 7 caratteri normalmente numerici,
			// impostato a non numerico
			boolean cfConOmocodia = false;
			try{
				int[] posizioniNumeriche = {6, 7, 9, 10, 12, 13, 14};
				for (int i = 0; i < posizioniNumeriche.length; i++) {
					char car = codiceFiscale.charAt(posizioniNumeriche[i]);
					Integer num = new Integer(car);
					if (num.intValue() < 48 || num.intValue() > 57) {
						cfConOmocodia = true;
					}
				}
			}
			catch (NumberFormatException ex) {
				cfConOmocodia = true;
			}

			if(cfConOmocodia){
				//         char carControllo = codiceFiscale.charAt(14);    // <---- tutte le cifre possono essere lettere!
				//         if ( (byte) carControllo > 64 && (byte) carControllo < 91) {

				//       	  // prima verifica che il carattere di controllo corrisponda ai primi 15 caratteri
				//           char crt = calcolaControlCrt(codiceFiscale);
				//           if (codiceFiscale.charAt(15) != crt) {
				//             return false;
				//           }

				// se il carattere di controllo corrisponde, allora ripulisco
				codiceFiscale = controllaEPulisciCFConOmocodia(codiceFiscale);
			}

			// Per ogni carattere verifica la disposizione di lettere e numeri
			int tmp;
			char c;
			for (int i = 0; i < 16; i++) {
				c = codiceFiscale.toUpperCase().charAt(i);
				if ( (byte) c > 64 && (byte) c < 91) {
					tmp = 0;
				}
				else if ( (byte) c > 47 && (byte) c < 58) {
					tmp = 1;
				}
				else {
					tmp = 2;
				}
				//           //System.out.println("ENTRA in controllaCF tmp" + tmp);
				if (tabCF1[i] != tmp) {
					return false;
				}
			}

			// Verifica il mese
			int mese = 0;
			boolean found = false;
			for (mese = 0; mese < 12; mese++) {
				if (tabCF2[mese] == codiceFiscale.toUpperCase().charAt(8)) {
					//             //System.out.println("ENTRA in controllaCF found = true;");
					found = true;
					break;
				}
			}
			if (!found) {
				return false;
			}

			// Estrae giorno e anno e verifica la data
			int giorno = Integer.parseInt(codiceFiscale.substring(9, 11));
			if (giorno > 40) {
				giorno = giorno - 40;
			}
			int anno = Integer.parseInt(codiceFiscale.substring(6, 8));
			if (anno % 4 == 0) {
				tabCF3[1] = 29;
			}
			if (giorno > tabCF3[mese]) {
				return false;
			}
		}
		catch (NumberFormatException ex) {
			//System.out.println("-----------> CodiceFiscale.controllaCF, ERRORE: " + ex.getMessage());
		}
		return true;
	}
	private static char calcolaControlCrt(String codFisc) {
		int counter = 0; // serve per accumulare il codice numerico
		int offset = 0;
		// I primi 8 caratteri dispari...
		for (int i = 0; i < 8; i++, offset += 2) {


			char tmpChar = codFisc.charAt(offset);

			if (tmpChar == 'A' || tmpChar == '0')
				counter += 1;
			else if (tmpChar == 'B' || tmpChar == '1')
				counter += 0;
			else if (tmpChar == 'C' || tmpChar == '2')
				counter += 5;
			else if (tmpChar == 'D' || tmpChar == '3')
				counter += 7;
			else if (tmpChar == 'E' || tmpChar == '4')
				counter += 9;
			else if (tmpChar == 'F' || tmpChar == '5')
				counter += 13;
			else if (tmpChar == 'G' || tmpChar == '6')
				counter += 15;
			else if (tmpChar == 'H' || tmpChar == '7')
				counter += 17;
			else if (tmpChar == 'I' || tmpChar == '8')
				counter += 19;
			else if (tmpChar == 'J' || tmpChar == '9')
				counter += 21;
			else if (tmpChar == 'K')
				counter += 2;
			else if (tmpChar == 'L')
				counter += 4;
			else if (tmpChar == 'M')
				counter += 18;
			else if (tmpChar == 'N')
				counter += 20;
			else if (tmpChar == 'O')
				counter += 11;
			else if (tmpChar == 'P')
				counter += 3;
			else if (tmpChar == 'Q')
				counter += 6;
			else if (tmpChar == 'R')
				counter += 8;
			else if (tmpChar == 'S')
				counter += 12;
			else if (tmpChar == 'T')
				counter += 14;
			else if (tmpChar == 'U')
				counter += 16;
			else if (tmpChar == 'V')
				counter += 10;
			else if (tmpChar == 'W')
				counter += 22;
			else if (tmpChar == 'X')
				counter += 25;
			else if (tmpChar == 'Y')
				counter += 24;
			else if (tmpChar == 'Z')
				counter += 23;
		}

		// I primi 7 caratteri pari
		offset = 1;
		for (int j = 0; j < 7; j++, offset += 2) {
			char tmpChar = codFisc.charAt(offset);
			if (Character.isDigit(tmpChar))
				counter += tmpChar - '0';

			else if (tmpChar == 'A')
				counter += 0;
			else if (tmpChar == 'B')
				counter += 1;
			else if (tmpChar == 'C')
				counter += 2;
			else if (tmpChar == 'D')
				counter += 3;
			else if (tmpChar == 'E')
				counter += 4;
			else if (tmpChar == 'F')
				counter += 5;
			else if (tmpChar == 'G')
				counter += 6;
			else if (tmpChar == 'H')
				counter += 7;
			else if (tmpChar == 'I')
				counter += 8;
			else if (tmpChar == 'J')
				counter += 9;
			else if (tmpChar == 'K')
				counter += 10;
			else if (tmpChar == 'L')
				counter += 11;
			else if (tmpChar == 'M')
				counter += 12;
			else if (tmpChar == 'N')
				counter += 13;
			else if (tmpChar == 'O')
				counter += 14;
			else if (tmpChar == 'P')
				counter += 15;
			else if (tmpChar == 'Q')
				counter += 16;
			else if (tmpChar == 'R')
				counter += 17;
			else if (tmpChar == 'S')
				counter += 18;
			else if (tmpChar == 'T')
				counter += 19;
			else if (tmpChar == 'U')
				counter += 20;
			else if (tmpChar == 'V')
				counter += 21;
			else if (tmpChar == 'W')
				counter += 22;
			else if (tmpChar == 'X')
				counter += 23;
			else if (tmpChar == 'Y')
				counter += 24;
			else if (tmpChar == 'Z')
				counter += 25;
		}
		// il codiceCrt ?? un numero tra 0 e 25
		int codiceCrt = counter % 26;

		// il codice risultante ?? il (codiceCrt+1)-esimo carattere dell'alfabeto
		return (char) ('A' + codiceCrt);
	}
	public static String controllaEPulisciCFConOmocodia(String codiceFiscale) {
		// stringa per controllo e calcolo omocodia
		String omoCodici = "LMNPQRSTUV";
		codiceFiscale = codiceFiscale.toUpperCase();
		char[] cCodice = codiceFiscale.toCharArray();

		// check della correttezza formale del codice fiscale
		// elimino dalla stringa gli eventuali caratteri utilizzati negli
		// spazi riservati ai 7 che sono diventati carattere in caso di omocodia
		for (int k = 6; k < 15; k++) {
			if ( (k == 8) || (k == 11))
				continue;
			int x = (omoCodici.indexOf(cCodice[k]));
			if (x != -1)
				cCodice[k] = Integer.toString(x).charAt(0);
		}
		//        codiceFiscale = cCodice.toString();
		codiceFiscale = new String(cCodice);
		return codiceFiscale;
	}
	//////////////////
	public static final boolean controllaCAP(String cap){
		String numeri="0123456789";
		int i=cap.length();
		if(i!=5) return false;
		for (int j = 0 ; j<i ; j++){
			boolean ret=false;
			for(int k=0;k<10;k++){
				if (cap.charAt(j) == numeri.charAt(k))
					ret = true;
			}
			if(!ret)
				return false;
		}
		return true;
	}

	public static final boolean controllaTel(String tel){
		String numeri="0123456789 /";
		int i=tel.length();
		if(i>15) return false;
		for (int j = 0 ; j<i ; j++){
			boolean ret=false;
			for(int k=0;k<12;k++){
				if (tel.charAt(j) == numeri.charAt(k))
					ret = true;
			}
			if(!ret)
				return false;
		}
		return true;
	}
	public static final boolean controllaData(Date data)  {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter .setLenient(false) ;
			formatter.format(data);
			return true;
		} catch ( Exception exception) {
			return false;
		}
	}
	public static final boolean controllaPercentuale(Integer numero) {
		if (!controllaNumero(numero)) {
			return false;
		} else if (numero < 0 || numero > 100) {
			return false;
		}
		return true;
	}
	public static final boolean controllaNumero(Integer numero) {
		Long numeroLong = 0L;
		try {
			numeroLong = numero.longValue();
		}
		catch (NumberFormatException ex) {
			////System.out.println(" - ValidazioneCampi.controllaNumero: NumberFormatException " + ex);
			return false;
		}

		if (numeroLong.longValue() < 0) {
			return false;
		}
		return true;
	}
	public static boolean controllaPartitaIVA(String partitaIVA) {
		//System.out.println("CodiceFiscale, ENTRA in controllaPartitaIVA: " + partitaIVA.length());
		// Verifica la lunghezza della stringa
		try {
			if (partitaIVA.length() != 11) {
				return false;
			}
			if(StringUtils.containsOnly(partitaIVA, "0")) {
				return false;
			}

			// Verifica che la stringa sia composta da sole cifre
			char c;
			for (int i = 0; i < 11; i++) {
				c = partitaIVA.charAt(i);
				if ( (byte) c < 48 || (byte) c > 57) {
					return false;
				}
			}

			// Algoritmo di calcolo dell'ultima cifra
			int Vd, Vp;
			int ctrl = 0;
			String Wk = "";
			for (int i = 0; i < 9; i += 2) {
				Vd = (byte) partitaIVA.charAt(i) - 48;
				Vp = 2 * ( (byte) partitaIVA.charAt(i + 1) - 48);
				Wk = (Vp < 10) ? "0" + String.valueOf(Vp) : String.valueOf(Vp);
				ctrl += Vd + (byte) Wk.charAt(0) - 48 + (byte) Wk.charAt(1) - 48;
			}

			if(ctrl < 10) {
				Wk = "0" + String.valueOf(ctrl);
			}
			else if(ctrl > 99) {
				Wk = String.valueOf(ctrl).substring(0, 1);
			}
			else {
				Wk = String.valueOf(ctrl);
			}

			// Verifica la correttezza dell'ultima cifra
			if ( (byte) partitaIVA.charAt(10) - 48 !=
					(10 - ( (byte) Wk.charAt(1) - 48)) % 10) {
				return false;
			}
		}
		catch (Exception ex) {
			//System.out.println("CodiceFiscale.controllaPartitaIVA, ERRORE: " + ex.getMessage());
			return false;

		}

		// Tutto ok
		return true;
	}
	public static String convertiDataInStringa(Date data) {
		if (data == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ITALY);
		return formatter.format(data);
	}
	public static boolean confrontaData1MaggioreData2(String data1, String data2) {

		Date d1 = convertiStringaInData(data1);
		Date d2 = convertiStringaInData(data2);

		if(d2.before(d1)) {
			return true;
		}
		return false;
	}
	public static boolean confrontaData1MaggioreData2(Date d1, Date d2) {


		if(d2.before(d1)) {
			return true;
		}
		return false;
	}
	public static boolean confrontaData1minoreData2(Date d1, Date d2) {


		if(d1.before(d2)) {
			return true;
		}
		return false;
	}

	public static boolean confrontaData1MaggioreUgualeData2(String data1, String data2) {

		Date d1 = convertiStringaInData(data1);
		Date d2 = convertiStringaInData(data2);

		if(d2.before(d1) || d2.equals(d1)) {
			return true;
		}
		return false;
	}
	public static boolean confrontaData1MaggioreUgualeData2(Date d1, Date d2) {

		if(d2.before(d1) || d2.equals(d1)) {
			return true;
		}
		return false;
	}

	public static boolean confrontaData1MinoreUgualeData2(Date d1, Date d2) {

		if(d1.before(d2) || d2.equals(d1)) {
			return true;
		}
		return false;
	}
	public static java.util.Date convertiStringaInData(String data) {
		if (data == null || data.equals(""))
			return null;
		java.util.Date date = null;
		StringBuffer newDate = new StringBuffer();
		java.text.SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ITALY);
		java.text.ParsePosition pos = new ParsePosition(0);
		try {
			String[] dataParts = data.split("/");
			for(int i=0; i < dataParts.length; i++){
				dataParts[i] = org.apache.commons.lang.StringUtils.leftPad(dataParts[i] , 2, "0");
			}
			newDate.append(dataParts[0]);
			newDate.append("/");
			newDate.append(dataParts[1]);
			newDate.append("/");
			newDate.append(dataParts[2]);

			//		      newDate = data.substring(0, 2) + "/" +
			//		          data.substring(3, 5) + "/" +
			//		          data.substring(6, 10);

			date = formatter.parse(newDate.toString(), pos);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			date = null;
		}
		return date;
	}
	public static boolean verificaDataUltimoGiornoAnnoPrecedente(String data) {
		GregorianCalendar cal = convertiStringaInGragorianCalendar(data);

		if(checkGiornoFineAnno(cal) && verificaDataConAnnoUgualeAnnoPrecedente(data)) {
			return true;
		}

		return false;
	}
	public static GregorianCalendar convertiStringaInGragorianCalendar(String
			data) {
		if (data == null || data.trim().equals(""))
			return null;
		GregorianCalendar date = null;
		try {
			date = new GregorianCalendar(Integer.parseInt(data.substring(6, 10)),
					Integer.parseInt(data.substring(3, 5)) - 1,
					Integer.parseInt(data.substring(0, 2)));
		}
		catch (Exception ex) {
			date = null;
		}
		return date;
	}
	public static boolean checkGiornoFineAnno(GregorianCalendar cal) {
		int ultimoGiorno = 31;
		int meseUltimo = 11;  // Calendar.MONTH parte da zero
		//	   	   Date data = cal.getTime();

		if(cal.get(Calendar.DAY_OF_MONTH) == ultimoGiorno && cal.get(Calendar.MONTH) == meseUltimo) {
			return true;
		}

		return false;
	}
	public static boolean verificaDataConAnnoUgualeAnnoPrecedente(String data) {
		int annoPrec = getAnnoPrecedente(1);

		String anno = data.substring(6);
		if (parseInt(anno) != annoPrec) {
			return false;
		}
		return true;
	}
	public static int getAnnoPrecedente(int numAnni) {
		String annoCorrente = getAnnoCorrenteStringa();
		int annoPrec = parseInt(annoCorrente) - numAnni;
		return annoPrec;
	}
	public static int parseInt(String value) {
		int n = 0;
		try {
			n = Integer.parseInt(value);
		}
		catch (NumberFormatException ex) {
			return -1;
		}
		return n;
	}
	public static String getAnnoCorrenteStringa() {
		try {
			String sysdate = convertiDataInStringa(new Date());
			String annoCorrente = sysdate.substring(6);
			return annoCorrente;
		}
		catch(Exception e) {
			return "";
		}
	}
	public static boolean controllaSecondoRegExp(String valore, String regex) {
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
		Matcher m = p.matcher(valore);
		return m.matches();
	}
	public static boolean checkValore(String valore, String regex) {
		if(!controllaSecondoRegExp(valore, regex)) {
			return false;
		}
		return true;
	}

	public static boolean isVoid(Object objIn) {
		try {
			// oggetto nullo 
			if(objIn == null) {
				return true;
			}	 
			// stringa vuota
			else if(objIn instanceof String && ((String)objIn).trim().equals("")) {
				return true;
			}
			// Long
			else if(objIn instanceof Long && ((Long) objIn).longValue() == 0) {
				return true;
			}
			else if(objIn instanceof Integer && ((Integer) objIn).intValue() == 0) {
				return true;
			}
			// Boolean: se non ?? nullo,
			else if(objIn instanceof Boolean) {
				try {
					@SuppressWarnings("unused")
					boolean b = ((Boolean) objIn).booleanValue();
					return false;
				}
				catch(Exception e) {
					return true;
				}
			}
			//collection nulla o vuota
			else if(objIn instanceof Collection && (objIn == null || ((Collection) objIn).size() == 0)) {
				return true;
			}
			else if(objIn instanceof ArrayList && (objIn == null || ((ArrayList) objIn).size() == 0)) {
				return true;
			}
			// java.util.date
			else if(objIn instanceof java.util.Date && ((java.util.Date) objIn).getTime() == 0) {
				return true;
			}
			else if(objIn instanceof java.sql.Date && ((java.sql.Date) objIn).getTime() == 0) {
				return true;
			}
			// gregoriaCal
			else if(objIn instanceof GregorianCalendar && ((GregorianCalendar) objIn).getTime() == null) {
				return true;
			}
			else{
				return false;
			}
		}
		catch(Exception e) {
			return true;
		}
	}
	public static boolean isNotVoid(Object objIn) {
		return !isVoid(objIn);
	}

	public static boolean isControlloAbilitato(String valore) {
		Boolean result = Boolean.valueOf(valore);
		return result;
	}
	public static boolean isVoidInteger(Integer objIn) {
		if(objIn == null) {
			return true;
		}
		else if(objIn.intValue() == 0) {
			// lo zero viene considerato come valore non nullo
			return false;
		}
		return false;
	}

	public static ProTComune getComuneValido(Date data, String codiceMinisteriale, List<ProTComune> comuni) {
		if (codiceMinisteriale != null && !"".equals(codiceMinisteriale)) {
			ArrayList<ProTComune> res = getComune(codiceMinisteriale, comuni);
			if (res != null) {
				for (ProTComune comune : res) {
					if (comune != null) {
						Date dataInizio = comune.getDtInizio();
						Date dataFine = comune.getDtFine();
						if (isDecodificaValida(data, dataInizio, dataFine)) {
							return comune;
						}
					} 
				}

			} 
		}
		return null;
	}
	public static final boolean isDecodificaValida(Date dataDiVerifica, Date dataInizio, Date dataFine) {

		boolean result = false;

		DateTime ora = dataDiVerifica != null ? new DateTime(dataDiVerifica.getTime()) : new DateTime();

		DateTime inizio = new DateTime(dataInizio);

		if (dataFine == null) {
			result = ora.isAfter(inizio);
		} else {
			DateTime fine = new DateTime(dataFine);
			result = ora.isAfter(inizio) && ora.isBefore(fine);
		}

		return result;
	}
	public static ArrayList<ProTComune> getComune (String codiceMinisteriale, List<ProTComune> comuni) {
		ArrayList<ProTComune> result = null;
		if (codiceMinisteriale != null && !"".equals(codiceMinisteriale) && comuni != null && comuni.size() > 0) {
			result = new ArrayList<ProTComune>();
			for (ProTComune comune : comuni) {
				if (codiceMinisteriale.equals(comune.getCodComuneMin())) {
					result.add(comune);
				}
			}
		}
		return result;
	}

	public static String getAnnoFromDateString(Date data) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(data);
			int anno = c.get(Calendar.YEAR);
			return toString(anno);
		}
		catch(Exception e) {
			return "";
		}
	}
	public static String toString(Object in ) {
		return in!=null?in.toString():null;
	}
	public static Date dateAddYear(java.util.Date date, int year) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}
	public static Date dateAddDays(java.util.Date date, int days) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	public static int differenzaInAnniFraDueDate(Date dInizio , Date dFine) {

		int anni = 0;

		DateTime dtInizio = new DateTime(dInizio.getTime());
		DateTime dtFine = new DateTime(dFine.getTime());

		Period periodo = new Period(dtInizio, dtFine);
		anni = periodo.getYears();

		return anni;

	}
	public static boolean checkFestivita(Date data){
		GregorianCalendar cal0 = new GregorianCalendar();
		cal0.setTime(data);

		int anno = cal0.get(Calendar.YEAR);

		// domenica
		if(checkDomenica(data)) {
			return true;
		}

		GregorianCalendar cal = easterDate(anno);
		// Pasqua
		if(data == cal.getTime()) {
			return true;
		}
		// pasquetta
		else if(ProdisSrvUtil.dateAddDays(data, 1) == ProdisSrvUtil.dateAddDays(cal.getTime(), 1)) {
			return true;
		}
		// controllo con la lista delle festivit?? standard
		else {
			String dataString = ProdisSrvUtil.convertiDataInStringa(data);
			String giorno = dataString.substring(0, 2);
			String mese = dataString.substring(3, 5);
			String ddMm = giorno + "-" + mese;

			for (int i = 0; i < festivita.length; i++) {
				if (ddMm.equals(festivita[i]))
					return true;
			}
		}
		return false;
	}

	public static boolean checkDomenica(Date data) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(data);

		int giornoDellaSettimana = cal.get(Calendar.DAY_OF_WEEK);
		if(giornoDellaSettimana == 1) {
			return true;
		}
		return false;
	}
	public static GregorianCalendar easterDate(int eY) {

		int prime, dominical;
		int i, j;
		int eMonth = 0, eDay = 0;
		int sundayLetter[] = new int[35];
		int
		goldenNumber[] = {
				14, 3, 0, 11, 0, 19, 8, 0, 16, 5, 0, 13, 2, 0, 10, 0, 18, 7, 0, 15, 4,
				0, 12, 1, 0, 9, 17, 6,
				0, 0, 0, 0, 0, 0, 0};

		//System.out.println("Easter.easterDate called for " + eY);

		prime = (eY + 1) % 19;
		if (prime == 0)
			prime = 19;

		dominical = (eY + (eY / 4) + 6) % 7;

		//System.out.println("Prime is " + prime + ", Dominical is " + dominical);

		for (i = 0; i < 35; i++) {
			sundayLetter[i] = 6 - ( (i + 6 - 4) % 7);
		}

		lookup:for (i = 0; i < 35; i++) {
			if (prime == goldenNumber[i]) {
				for (j = i + 1; j < 35; j++) {
					if (sundayLetter[j] == dominical) {
						if (j > 9) {
							eMonth = 4;
							eDay = j - 9;
						}
						else {
							eMonth = 3;
							eDay = j + 22;
						}
						break lookup;
					}
				}
			}
		}
		GregorianCalendar eGC = new GregorianCalendar(eY,eMonth-1,eDay);
		return eGC;
	}
	public static int confrontaDate(Date data1, Date data2) {
		try {
			String sData1 = getStringDate(data1);
			String sData2 = getStringDate(data2);
			int dd1 = Integer.parseInt(sData1.substring(0, 2));
			int mm1 = Integer.parseInt(sData1.substring(3, 5));
			int yy1 = Integer.parseInt(sData1.substring(6, 10));
			int dd2 = Integer.parseInt(sData2.substring(0, 2));
			int mm2 = Integer.parseInt(sData2.substring(3, 5));
			int yy2 = Integer.parseInt(sData2.substring(6, 10));
			//se data2 ?? maggiore di data1
			if (yy2 > yy1)
				return 1;
			if (yy2 == yy1 && mm2 > mm1)
				return 1;
			if (yy2 == yy1 && mm2 == mm1 && dd2 > dd1)
				return 1;
			//se data2 ?? minore di data1
			if (yy2 < yy1)
				return -1;
			if (yy2 == yy1 && mm2 < mm1)
				return -1;
			if (yy2 == yy1 && mm2 == mm1 && dd2 < dd1)
				return -1;
			//altrimenti sono uguali
		}
		catch (Exception ex) {
			return 2;
		}
		return 0;
	}
	public static String getStringDate(java.util.Date data) {
		if (data == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(data);
	}
	public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
