/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.util;

public class ConstantsProdis {
	public static final String COMPONENT_NAME = "PRODIS-SRV";

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

	public static final String PARAMETRO_REGEX_EMAIL = "prodis.pdweb.regularexpression.email";
	public static final String PARAMETRO_REGEX_AZIENDA_CODICEFISCALE_STRANIERO = "prodis.pdweb.regularexpression.azienda.codicefiscale.straniero";
	public static final String PARAMETRO_QUADRO2_ALTRECONCESSIONI_CONTROLLO_NUMERO_LAVORATORI_PREVISTI_ABILITATO = "prodis.pdweb.quadro2.altreconcessioni.controllo.numerolavoratoriprevisti.abilitato";

	public static final Long STATO_CONCESSIONE_APPROVATA_CONCESSA = 1L;
	public static final int CAUSA_SOSPENSIONE_B_MOBILITA = 2;
	public static final int STATO_SOSPENSIONE_APPROVATA_CONCESSA = 1;
	public static final String STATO_CONCESSIONE_E = "E";
	public static final String STATO_CONCESSIONE_F = "F";

	
	public static final Long IMPORT_ERROR_PROVINCE_Q1_Q3 = 1L;
	public static final Long IMPORT_ERROR_CATEGORIA_AZIENDA = 2L;
	public static final Long IMPORT_ERROR_TIPO_DICHIARANTE = 3L;
	public static final Long IMPORT_ERROR_SETTORE_ATECO = 4L;
	public static final Long IMPORT_ERROR_CCNL = 5L;
	public static final Long IMPORT_ERROR_COMUNE_STATO_ESTERO = 6L;
	public static final Long IMPORT_ERROR_REGIONE = 7L;
	public static final Long IMPORT_ERROR_PROVINCIA = 8L;
	public static final Long IMPORT_ERROR_CATEGORIA_ESCLUSA = 9L;
	public static final Long IMPORT_ERROR_CONTRATTO = 10L;
	public static final Long IMPORT_ERROR_QUALIFICA = 11L;
	public static final Long IMPORT_ERROR_TIPO_ASSSUNZIONE = 12L;
	public static final Long IMPORT_ERROR_STATO_CONCESSIONE = 13L;
	public static final Long IMPORT_ERROR_CAUSA_SOSPENSIONE = 14L;
	public static final Long IMPORT_ERROR_TIPO_SOGGETTI = 15L;
	public static final Long IMPORT_ERROR_TIPO_COMUNICAZIONE = 16L;
	
	//tabelle decodifica
	public static final String PRO_T_COMUNICAZIONE = "PRO_T_COMUNICAZIONE";
	public static final String PRO_T_CATEGORIA_AZIENDA = "PRO_T_CATEGORIA_AZIENDA";
	public static final String PRO_T_CCNL = "PRO_T_CCNL";
	public static final String PRO_T_ATECOFIN = "PRO_T_ATECOFIN";
	public static final String PRO_T_DICHIARANTE = "PRO_T_DICHIARANTE";
	public static final String PRO_T_COMUNE = "PRO_T_COMUNE";
	public static final String PRO_T_REGIONE = "PRO_T_REGIONE";
	public static final String PRO_T_PROVINCIA = "PRO_T_PROVINCIA";
	public static final String PRO_T_STATO_CONCESSIONE = "PRO_T_STATO_CONCESSIONE";
	public static final String PRO_T_ASSUNZIONE_PROTETTA = "PRO_T_ASSUNZIONE_PROTETTA";
	public static final String PRO_T_STATI_ESTERI = "PRO_T_STATI_ESTERI";
	public static final String PRO_T_ISTAT2001LIVELLO5 = "PRO_T_ISTAT2001LIVELLO5";
	public static final String PRO_T_CONTRATTI = "PRO_T_CONTRATTI";
	public static final String PRO_T_CATEGORIA_ESCLUSE = "PRO_T_CATEGORIA_ESCLUSE";
	public static final String PRO_T_TIPO_RIPROP_PT = "PRO_T_TIPO_RIPROP_PT";
	public static final String PRO_T_CAUSA_SOSPENSIONE = "PRO_T_CAUSA_SOSPENSIONE";
	public static final String PRO_T_SOGGETTI = "PRO_T_SOGGETTI";
	
	
	public static final String TIPO_INTERMITTENTE_DESC = "Intermittente";
	public static final long PT_DISABILI = 1;
	public static final long PT_CENTRALINISTI = 2;
	public static final long PT_TERAPISTI_MASSOF_NONVEDENTI = 3;
	public static final long PT_DISABILI_SOMM = 5;
	public static final long PT_DISABILI_CONVENZIONE = 6;
	public static final long PT_TELELAVORO = 4;
	public static final long PT_ALTRE_CATEGORIA = 7;
	
	public static final String UTENTE_IMPORT_PROSPETTO_DA_SPICOM = "IMPORT_MINISTERO";
	
	public static final Long STATO_VERIFICA_VERIFICATA = 2L;
	
	
}
