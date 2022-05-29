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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.google.gson.Gson;

import it.csi.prodis.prodisweb.ejb.business.be.dad.LavoratoriInForzaDad;
import it.csi.prodis.prodisweb.ejb.entity.ProDParametri;

public class CampoCellaHelper {
	
	private static LinkedHashMap<String, CampoCella> mappaCampi;
	
	public static final String generaLista() {
		
		Gson gson = new Gson();
		List<CampoCella> campi = new ArrayList<CampoCella>();
		
		campi.add(new CampoCella(
				NomiCampi.SiglaProvincia.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.SiglaProvincia.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[a-zA-Z][a-zA-Z]", NomiCampi.SiglaProvincia.toString() + " deve essere composto da due caratteri alfabetici")));

		campi.add(new CampoCella(
				NomiCampi.CodiceFiscale.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceFiscale.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(
						Boolean.TRUE, 
						"^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$", 
						NomiCampi.CodiceFiscale.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.Cognome.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.Cognome.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[A-Za-z'-.][A-Za-z'-.\\s]{1,99}", NomiCampi.Cognome.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.Nome.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.Nome.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[A-Za-z'-.][A-Za-z'-.\\s]{1,99}", NomiCampi.Nome.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.Sesso.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.Sesso.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[MF]", NomiCampi.Sesso.toString() + " deve essere nel formato M/F")));

		campi.add(new CampoCella(
				NomiCampi.DataNascita.toString(), 
				TipoCella.date, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.DataNascita.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]", NomiCampi.DataNascita.toString() + " deve essere nel formato dd/MM/yyyy")));

		campi.add(new CampoCella(
				NomiCampi.CodiceComune.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceComune.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[A-Z][0-9][0-9][0-9]", NomiCampi.CodiceComune.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.CodiceStatoEstero.toString(), 
				TipoCella.string,
				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceStatoEstero.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[A-Z][0-9][0-9][0-9]", NomiCampi.CodiceStatoEstero.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.DataInizioRapporto.toString(), 
				TipoCella.date, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.DataInizioRapporto.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]", NomiCampi.DataInizioRapporto.toString() + " deve essere nel formato dd/MM/yyyy")));

		campi.add(new CampoCella(
				NomiCampi.DataFineRapporto.toString(), 
				TipoCella.date, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.DataFineRapporto.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]", NomiCampi.DataFineRapporto.toString() + " deve essere nel formato dd/MM/yyyy")));

		campi.add(new CampoCella(
				NomiCampi.CodiceQualificaIstat.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceQualificaIstat.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][.[0-9]]*", NomiCampi.CodiceQualificaIstat.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.OrarioSettimanale.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.OrarioSettimanale.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][0-9]:[0-9][0-9]", NomiCampi.OrarioSettimanale.toString() + " deve essere nel formato HH:MM")));

		campi.add(new CampoCella(
				NomiCampi.OrarioSettimanalePartTime.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.OrarioSettimanalePartTime.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9][0-9]:[0-9][0-9]", NomiCampi.OrarioSettimanalePartTime.toString() + " deve essere nel formato HH:MM")));

		campi.add(new CampoCella(
				NomiCampi.CategoriaSoggetto.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CategoriaSoggetto.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[CD]", NomiCampi.CategoriaSoggetto.toString() + " deve essere C/D")));

		campi.add(new CampoCella(
				NomiCampi.CategoriaAssunzione.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CategoriaAssunzione.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[N][OU]", NomiCampi.CategoriaAssunzione.toString() + " deve essere NU/NO")));

		campi.add(new CampoCella(
				NomiCampi.CodiceTipologiaContrattuale.toString(), 
				TipoCella.string, 
//				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceTipologiaContrattuale.toString() + " campo obbligatorio"), 
				new Obbligatorio(Boolean.FALSE, ""), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[A-Z].[0-9][0-9].[0-9][0-9]", NomiCampi.CodiceTipologiaContrattuale.toString() + " non corretto")));

		campi.add(new CampoCella(
				NomiCampi.FlgForma.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.FlgForma.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[IDE]", NomiCampi.FlgForma.toString() + " deve essere I/D")));

		campi.add(new CampoCella(
				NomiCampi.CodiceTipoAssunzioneProtetta.toString(), 
				TipoCella.string, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.CodiceTipoAssunzioneProtetta.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[ABCDEGHIFLMNO]", NomiCampi.CodiceTipoAssunzioneProtetta.toString() + " deve essere A/B/C/D/E/G/H/I/F/L/M/N/O")));

		campi.add(new CampoCella(
				NomiCampi.PercentualeDisabilita.toString(), 
				TipoCella.number, 
				new Obbligatorio(Boolean.TRUE, NomiCampi.PercentualeDisabilita.toString() + " campo obbligatorio"), 
				new Comparabile(Boolean.TRUE, ""), 
				new Validabile(Boolean.TRUE, "[0-9]{1,2}", NomiCampi.PercentualeDisabilita.toString() + " non corretta")));

		String jsonString = gson.toJson(campi);

		for (CampoCella campo : campi) {
			if (mappaCampi==null) {
				mappaCampi = new LinkedHashMap<String, CampoCella>();
			}
			mappaCampi.put(campo.getCampo(), campo);
		}
		
		return jsonString;
		
	}

	public static final void interpretaLista(String jsonString) {
		Gson gson = new Gson();
		CampoCella[] array = gson.fromJson(jsonString,CampoCella[].class);
		List<CampoCella> arrayCampi = Arrays.asList(array);
		for (CampoCella el : arrayCampi) {
			System.out.println(el);
		}
	}
	
	public static final LinkedHashMap<String, CampoCella> getMappaCampiCella(LavoratoriInForzaDad lavoratoriInForzaDad) {

		LinkedHashMap<String, CampoCella> campi = new LinkedHashMap<String, CampoCella>();
			
		Gson gson = new Gson();
			
		List<ProDParametri> campiString = lavoratoriInForzaDad.getParametri(Parametro.PARAMETRO_MASSIVOLAVORATORI_CAMPI);
			
		for (ProDParametri pEntity : campiString) {
				
			String valore = pEntity.getDsValore();
				
			StringTokenizer tkzCampi = new StringTokenizer(valore, ",");
				
			while (tkzCampi.hasMoreTokens()) {
					
				String campo = tkzCampi.nextToken();
					
				CampoCella campoCella = gson.fromJson(lavoratoriInForzaDad.getParametro(Parametro.PARAMETRO_MASSIVOLAVORATORI_CAMPI+"."+campo).getDsValore(),CampoCella.class); 
					
				campi.put(campoCella.getCampo(), campoCella);
					
			}
			
			break;
				
		}

		return campi;
		
	}
	
}
