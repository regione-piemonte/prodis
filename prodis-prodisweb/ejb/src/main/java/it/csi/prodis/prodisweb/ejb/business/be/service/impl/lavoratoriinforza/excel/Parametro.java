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

public class Parametro {
	
	public static final String PARAMETRO_REGEX_CF_PERSONAFISICA = "prodis.pdweb.regularexpression.codicefiscale.personafisica";
	public static final String PARAMETRO_REGEX_EMAIL = "prodis.pdweb.regularexpression.email";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_ALFANUMERICO = "prodis.pdweb.regularexpression.azienda.codicefiscale.alfanumerico";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_NUMERICO = "prodis.pdweb.regularexpression.azienda.codicefiscale.numerico";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_STRANIERO = "prodis.pdweb.regularexpression.azienda.codicefiscale.straniero";
	
	public static final String PARAMETRO_MASSIVOLAVORATORI_NOMEFILE = "prodis.pdweb.massivoLavoratori.nomeFile";
	public static final String PARAMETRO_MASSIVOLAVORATORI_CAMPI = "prodis.pdweb.massivoLavoratori.campi";
	public static final String PARAMETRO_MASSIVOLAVORATORI_NUMERO_LAVORATORI_PROSPETTO = "prodis.pdweb.massivoLavoratori.numero.lavoratori";
	public static final String PARAMETRO_MASSIVOLAVORATORI_NUMERO_LAVORATORI_PROSPETTO_UPLOAD = "prodis.pdweb.massivoLavoratori.numero.lavoratori.upload";
	public static final String PARAMETRO_MASSIVOLAVORATORI_CONTROLLI_CONGRUENZA_ABILITATI = "prodis.pdweb.massivoLavoratori.controlli.congruenza.abilitati";
	public static final String PARAMETRO_MASSIVOLAVORATORI_CONTROLLI_GENERALI_PREESTRAZIONE_ABILITATI = "prodis.pdweb.massivoLavoratori.controlli.generalipreestrazione.abilitati";
	
	public static final String PARAMETRO_ELENCO_CODICI_CONTRATTI_TIROCINIO = "prodis.pdweb.contratti.tirocini.codici";
	public static final String PARAMETRO_LAVORATORI_ETA_PREVISTA_MINIMA = "prodis.pdweb.lavoratori.eta.prevista.minima";
	public static final String PARAMETRO_LAVORATORI_ETA_PREVISTA_MASSIMA = "prodis.pdweb.lavoratori.eta.prevista.massima";
	
	public static final String PARAMETRO_INTERVALLO_CARICAMENTO_CACHE = "caricamento.cache.intervallo";
	
	public static final String PARAMETRO_QUADRO2_ALTRECONCESSIONI_CONTROLLO_NUMERO_LAVORATORI_PREVISTI_ABILITATO = "prodis.pdweb.quadro2.altreconcessioni.controllo.numerolavoratoriprevisti.abilitato";
	
	public static final String PARAMETRO_PROTOCOLLAZIONE_IUP_PROTOCOLLO_COMUNICAZIONE = "protocollazione.IUP.protocolloComunicazione";
	
	
	private String nome;
	public String getNome() {return nome;}
	public void setNome(String nome) {this.nome = nome;}
	
	private String valore;
	public String getValore() {return valore;}
	public void setValore(String valore) {this.valore = valore;}
	
	private String descrizione;
	public String getDescrizione() {return descrizione;}
	public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

	public String toString() {
		return "Parametro [nome=" + nome + ", valore=" + valore
				+ ", descrizione=" + descrizione + "]";
	}
	
}
