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

public class ConstantsProdis {
	public static final String COMPONENT_NAME = "PRODIS";

	// Ruoli
	public static final String RUOLO_AMMINISTRATORE_PRODIS = "Amministratore";
	public static final String RUOLO_PROVINCIA_OPERATORE_PRODIS = "Operatore Provinciale";
	public static final String RUOLO_CONSULENTE_RESPONSABILE = "Consulente Responsabile";
	public static final String RUOLO_LEGALE_RAPPRESENTANTE = "Legale rappresentante";
	public static final String RUOLO_PERSONA_CON_CARICA_AZIENDALE = "Persona con carica aziendale";
	public static final String RUOLO_PERSONA_AUTORIZZATA = "Persona autorizzata";
	public static final String RUOLO_DELEGATO_RESPONSABILE = "Delegato Responsabile";

	public static final String PARAM_PROSPETTI_NUMERO_MASSIMO = "ricerca.prospetto.numeroMassimoRecord";

	public static final int PROSPETTO_STATO_BOZZA = 1;
	public static final int PROSPETTO_STATO_DA_FIRMARE = 2;
	public static final int PROSPETTO_STATO_PRESENTATA = 3;
	public static final int PROSPETTO_STATO_IN_RETTIFICA = 9;
	public static final int PROSPETTO_STATO_RETTIFICATA = 10;
	public static final int PROSPETTO_STATO_ANNULLATA = 11;
	public static final int PROSPETTO_STATO_IN_ANNULLAMENTO = 12;
	public static final int PROSPETTO_STATO_CANCELLATA = 13;

	public static final Long COD_DICHIARANTE_A = 1L; // Datore lavoro privato
	public static final Long COD_DICHIARANTE_B = 2L; // Ente Pubblico economico
	public static final Long COD_DICHIARANTE_C = 3L; // Datore Lavoro Pubblico
	public static final Long COD_DICHIARANTE_D = 4L; // Datore lavoro privato appartenente a gruppo d'imprese

	public static final String SIGLA_ITALIANA = "IT";

	public static final String PARAMETRO_LAVORATORI_ETA_PREVISTA_MINIMA = "prodis.pdweb.lavoratori.eta.prevista.minima";
	public static final String PARAMETRO_LAVORATORI_ETA_PREVISTA_MASSIMA = "prodis.pdweb.lavoratori.eta.prevista.massima";
	public static final String PARAMETRO_REGEX_EMAIL = "prodis.pdweb.regularexpression.email";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_ALFANUMERICO = "prodis.pdweb.regularexpression.azienda.codicefiscale.alfanumerico";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_NUMERICO = "prodis.pdweb.regularexpression.azienda.codicefiscale.numerico";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_STRANIERO = "prodis.pdweb.regularexpression.azienda.codicefiscale.straniero";
	public static final String PARAMETRO_QUADRO2_ALTRECONCESSIONI_CONTROLLO_NUMERO_LAVORATORI_PREVISTI_ABILITATO = "prodis.pdweb.quadro2.altreconcessioni.controllo.numerolavoratoriprevisti.abilitato";
	public static final String PARAMETRO_REGEX_CF_PERSONAFISICA = "prodis.pdweb.regularexpression.codicefiscale.personafisica";
	public static final String DEFAULT_DATA_FORMAT_REGEXP = "^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d$";
	public static final String PARAMETRO_ELENCO_CODICI_CONTRATTI_TIROCINIO = "prodis.pdweb.contratti.tirocini.codici";
	public static final String DEFAULT_HHMM_MENSILE_FORMAT = "^((\\d)|(\\d\\d)|(99)):([0-5]\\d)$";
	public static final String PARAMETRO_DATA_RIFERIMENTO_TERMINE_PROSPETTO = "prodis.pdweb.DataTermineProspetto";

	public static final String PARAMETRI_PROTOCOLLAZIONE = "protocollazione";	
	public static final String PARAMETRO_PROT_IUP_ABILITAZIONE = "protocollazione.IUP.abilitazione";
	public static final String PARAMETRO_PROT_IUP_IDAOO = "protocollazione.IUP.idAOO";
	public static final String PARAMETRO_PROT_IUP_IDENTE = "protocollazione.IUP.idEnte";
	public static final String PARAMETRO_PROT_IUP_CODUT = "protocollazione.IUP.codiceUtente";
	public static final String PARAMETRO_PROT_IUP_DENAOO = "protocollazione.IUP.denominazioneAOO";
	public static final String PARAMETRO_PROT_IUP_CLASS = "protocollazione.IUP.indiceCalssificazionePrincipale";	
	public static final String PARAMETRO_PROT_FINTO = "protocollazione.IUP.numeroProtocolloFinto";
	public static final String PARAMETRO_MAIL = "mail";	
	public static final String PARAMETRO_MAIL_USER = "mail.userId";
	public static final String PARAMETRO_MAIL_PWD = "mail.password";
	public static final String PARAMETRO_MAIL_FROM = "mail.from";
	public static final String PARAMETRO_MAIL_TO = "mail.to";
	public static final String PARAMETRO_MAIL_SUBJECT = "mail.subject";
	public static final String PARAMETRO_TEX = "mail.text";	
	public static final String PARAMETRO_SPICOM_ABILITATO = "prodis.pdweb.comunicazione.abilita.t3";
	
	public static final Long STATO_CONCESSIONE_APPROVATA_CONCESSA = 1L;
	public static final int CAUSA_SOSPENSIONE_B_MOBILITA = 2;
	public static final int STATO_SOSPENSIONE_APPROVATA_CONCESSA = 1;
	public static final String STATO_CONCESSIONE_E = "E";
	public static final String STATO_CONCESSIONE_F = "F";

	public static final String CATEGORIA_SOGGETTO_C = "C";
	public static final String CATEGORIA_SOGGETTO_D = "D";
	public static final String DES_CATEGORIA_SOGGETTO_D = "Disabili";
	public static final String DES_CATEGORIA_SOGGETTO_C = "Categorie protette";

	public static final String CATEGORIA_COMPENSAZIONE_E = "E";
	public static final String CATEGORIA_COMPENSAZIONE_R = "R";
	
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_A = "A";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_N = "N";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_M = "M";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_I = "I";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_H = "H";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_E = "E";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_O = "O";
	public static final String TIPO_ASSUNZIONE_PROTETTA_CODICE_D = "D";

	public static final Long TIPOLOGIA_LAVORATORE_PT_DISABILI = 1L;
	public static final Long TIPOLOGIA_LAVORATORE_PT_OPERATORI = 2L;
	public static final Long TIPOLOGIA_LAVORATORE_PT_TERAPISTI = 3L;
	public static final Long TIPOLOGIA_LAVORATORE_PT_TELELAVORO_PART_TIME = 4L;
	public static final Long TIPOLOGIA_LAVORATORE_PT_DISABILI_SOMMINISTRATI = 5L;
	public static final Long TIPOLOGIA_LAVORATORE_PT_DISABILI_CONVENZIONE = 6L;

	public static final String DATA_17_01_2000 = "17/01/2000";
	public static final String DATA_31_12 = "31/12";
	
	public static final String CONTRATTO_FORMA_INDETERMINATO = "I";
	public static final String CONTRATTO_FORMA_DETERMINATO = "D";
	public static final String CONTRATTO_FORMA_ENTRAMBE = "E";
	
	public static final String TIPO_INTERMITTENTE_DESC = "Intermittente";
	public static final String TIPO_INTERMITTENTE_COD = "I";

	public static final int COD_CATEGORIA_ASSENTE = 0;
	public static final int COD_CATEGORIA_AZIENDA_A = 1;
	public static final int COD_CATEGORIA_AZIENDA_B = 2;
	public static final int COD_CATEGORIA_AZIENDA_C = 3;
	
	public static final String TERMINE_ANNULLAMENTO = "termine.annullamento";
	public static final String TERMINE_RETTIFICA = "termine.rettifica";

	public static final String TIPO_PROVENIENZA_PRODIS = "P";
	public static final String TIPO_PROVENIENZA_MINISTERO = "M";
	
	public static final int TIPO_COMUNICAZIONE_PROSPETTO_INFORMATIVO = 1;
	public static final int TIPO_COMUNICAZIONE_PROSPETTO_RETTIFICA = 2;
	public static final int TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO = 3;
	
	public static final String TIPO_COMUNICAZIONE_PROSPETTO_INFORMATIVO_COD = "01";
	public static final String TIPO_COMUNICAZIONE_PROSPETTO_RETTIFICA_COD = "02";
	public static final String TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO_COD = "03";

	public static final String TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO_DESC = "Annullamento";
	
	public static final Long PROSPETTO_STATO_PRESENTATA_ID = 3L;

}

