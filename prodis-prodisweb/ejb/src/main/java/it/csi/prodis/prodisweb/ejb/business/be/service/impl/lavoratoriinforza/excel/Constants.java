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

/**
 * <p>Classe delle costanti applicative.</p>
 *
 * @author GuiGen
 */
public final class Constants {
	/**
	 * identificativo dell'applicativo.
	 */
	public static final String APPLICATION_CODE = "pdweb";

	/**
	 * nome dell'attributo di sessione in cui viene memorizzato il valore corrente del
	 * captcha 
	 */
	public static final String CAPTCHA_SESSION_NAME = "current_captcha_value";

	/*PROTECTED REGION ID(R1581691183) ENABLED START*/
	// Add here your constants

	// *************************************************************************
	// file xml pa/pd T3
	public static final String ORCH_PRODIS_PD_XML = "/defpd_orchprodissrv.xml";
	// file xml pd WS
	public static final String ORCH_PRODIS_PD_WS_XML = "/defpd_orchprodissrv_ws.xml";
	// orchestratore
	public static final String ORCH_PRODIS_WS_URI = "iup.ws.namespace.uri";
	public static final String ORCH_PRODIS_WS_LOCAL_PART = "iup.ws.local.part";
	public static final String ORCH_PRODIS_WS_URL = "iup.ws.endpoint.url";

	// profilazione ruoli
	public static final String APPLICATION_CODE_PROFILER = "PRODIS";
	public static final String ORCH_LAVPROF_PD_XML = "/defpd_orchlavprof.xml";

	// integrazione forte con Silp
	public static final String ORCH_COM_SILP_PD_XML = "/defpd_orchcomsilpsv.xml";
	public static final String ORCH_COM_SILP_PD_XML_WS = "/defpd_orchcomsilpsv_ws.xml";

	// protocollazione
	public static final String ORCH_IUP_PD_XML = "/defpd_iupsv.xml";

	// *************************************************************************
	public static final String _S_VALUE = "S";
	public static final String _N_VALUE = "N";

	// costanti relative al codice di ricerca
	public static final int RICERCA_COMUNE_Q2_SEDE_RIFERIMENTO = 1;
	public static final int RICERCA_COMUNE_Q2_L68 = 2;
	//	public static final int RICERCA_COMUNE_Q2_POSTI_DISPONIBILI = 3;
	public static final int RICERCA_COMUNE_Q1_SEDE_LEGALE = 4;
	public static final int RICERCA_COMUNE_Q1_SEDE_REFERENTE = 5;
	public static final int RICERCA_STATO_ESTERO_Q1_SEDE_LEGALE = 6;
	public static final int RICERCA_STATO_ESTERO_Q1_DATI_REFERENTE = 7;
	public static final int RICERCA_CCNL_Q1 = 8;
	public static final int RICERCA_ATECO_Q1 = 9;
	public static final int RICERCA_STATO_ESTERO_Q2_L68 = 10;
	public static final int RICERCA_QUALIFICA_ISTAT_Q2_L68 = 11;
	//	public static final int RICERCA_STATO_ESTERO_Q2_POSTI_DISPONOIBILI = 12;
	//	public static final int RICERCA_QUALIFICA_ISTAT_Q2_POSTI_DISPONOIBILI = 13;
	public static final int RICERCA_Q2_SEDE_RIFERIMENTO = 14;
	public static final int RICERCA_QUALIFICA_ISTAT_POSTI_DISPONIBILI = 15;
	public static final int RICERCA_STATO_ESTERO_POSTI_DISPONIBILI = 16;
	public static final int RICERCA_COMUNE_POSTI_DISPONIBILI = 17;

	// costanti per la navigazione

	// costanti settate dal menu e lette nelle form
	public static final String PROSPETTO_CURRENTSTATE = "PROSPETTO_CURRENTSTATE";
	public static final String PAGINA_INSERIMENTO = "PAGINA_INSERIMENTO";
	public static final String PAGINA_RICERCA = "PAGINA_RICERCA";
	public static final String PAGINA_QUADRO1_READ_ONLY_RICERCA = "QUADRO1_READ_ONLY_RICERCA";
	public static final String PAGINA_QUADRO1_EDIT_RICERCA = "QUADRO1_EDIT_RICERCA";
	public static final String PAGINA_QUADRO1_EDIT_CONSULENTE_RICERCA = "QUADRO1_EDIT_CONSULENTE_RICERCA";
	public static final String PAGINA_QUADRO1_EDIT_AZIENDA_RICERCA = "QUADRO1_EDIT_AZIENDA_RICERCA";
	public static final String PAGINA_QUADRO1_READ_ONLY_AZIENDA_RICERCA = "QUADRO1_READ_ONLY_AZIENDA_RICERCA";
	public static final String PAGINA_QUADRO1_READ_ONLY_AZIENDA_RICERCA_STAMPA = "QUADRO1_READ_ONLY_AZIENDA_RICERCA_STAMPA";
	public static final String PAGINA_QUADRO1_EDIT_CONSULENTE = "QUADRO1_EDIT_CONSULENTE";
	public static final String PAGINA_QUADRO1_EDIT = "QUADRO1_EDIT";
	public static final String PAGINA_QUADRO1_EDIT_AZIENDA = "QUADRO1_EDIT_AZIENDA";
	public static final String PAGINA_QUADRO1_READ_ONLY_RICERCA_STAMPA = "QUADRO1_READ_ONLY_RICERCA_STAMPA";
	public static final String PAGINA_ELENCO_QUADRO2_PROVINCIALI = "ELENCO_QUADRO2_PROVINCIALI";
	public static final String PAGINA_QUADRO2_READ_ONLY = "QUADRO2_READ_ONLY";
	public static final String PAGINA_QUADRO2_EDIT = "PAGINA_QUADRO2_EDIT";
	public static final String PAGINA_BACK_RICERCA = "PAGINA_BACK_RICERCA";
	public static final String PAGINA_COMPENSAZIONI_READ_ONLY = "PAGINA_COMPENSAZIONI_READ_ONLY";
	public static final String PAGINA_COMPENSAZIONI_EDIT = "PAGINA_COMPENSAZIONI_EDIT";
	public static final String PAGINA_COMPENSAZIONI_CONFERMA = "PAGINA_COMPENSAZIONI_CONFERMA";
	public static final String PAGINA_ELENCO_POSTI_DISPONIBILI_PROVINCIALI = "ELENCO_POSTI_DISPONIBILI_PROVINCIALI";
	public static final String PAGINA_POSTI_DISPONIBILI_READ_ONLY = "POSTI_DISPONIBILI_READ_ONLY";
	public static final String PAGINA_POSTI_DISPONIBILI_EDIT = "PAGINA_POSTI_DISPONIBILI_EDIT";

	// costanti relative all'integrazione forte con Silp
	public static final String SILP_AZIENDA_FLAG_FORMA_GIURIDICA = "G";
	public static final String SILP_SEDE_LEGALE = "1";
	public static final String SILP_SEDE_OPERATIVA = "2";
	public static final String SILP_AZIENDA_ATTIVA = "1";
	public static final String SILP_AZIENDA_MASTER = "S";

	// messaggi e errori comuni
	public static final String MSG_SELEZIONA_ELEMENTO = "Selezionare un elemento della lista";

	// *************************************************************************
	// Gestione Elenco Caratteri Speciali
	// Vettore contenente i caratteri speciali che devono essere rimossi dai campi descrittivi,
	// visto che sono potenzialmente 'dannosi' per il SILP.

	public static final java.util.ArrayList<String> ELENCO_CARATTERI_SPECIALI = new java.util.ArrayList<String>();
	static {
		// Aggiungere gli eventuali caratteri riscontrati pericolosi nell'array list.
		// La classe ValidazioneCampi che implementa il metodo di eliminazione dei caratteri speciali,
		// cicler� sul contenuto di questo array list, rimuovendo, se trovato, il carattere dal campo descrittivo passato.
		ELENCO_CARATTERI_SPECIALI.add("�");
	}
	// *************************************************************************

	// Stati prospetti

	public static final int BOZZA = 1;
	public static final int DA_FIRMARE = 2;
	public static final int PRESENTATA = 3;
	public static final int IN_RETTIFICA = 9;
	public static final int RETTIFICA = 10;
	public static final int ANNULLATA = 11;
	public static final int IN_ANNULLAMENTO = 12;
	public static final int CANCELLATA = 13;

	// altre costanti
	public static final String NO_SCELTA_COMBO = "-1";
	public static final String DATO_NON_PERVENUTO = "N/A";

	public static final String CAP_STATO_ESTERO = "00000";

	public static final String PDF_PRESENTE = "P";
	public static final String PDF_ASSENTE = "A";
	public static final String PDF_NON_AMMESSO = "N";

	public static final int LUNGHEZZA_MIN_DENOMINAZIONE = 5;
	public static final int LUNGHEZZA_CODICE = 16;
	public static final int LUNGHEZZA_PARTITA_IVA = 11;

	public static final int COD_CATEGORIA_ASSENTE = 0;
	public static final int COD_CATEGORIA_AZIENDA_A = 1;
	public static final int COD_CATEGORIA_AZIENDA_B = 2;
	public static final int COD_CATEGORIA_AZIENDA_C = 3;

	public static final int COD_DICHIARANTE_A = 1; //Datore lavoro privato
	public static final int COD_DICHIARANTE_B = 2; //Ente Pubblico economico
	public static final int COD_DICHIARANTE_C = 3; //Datore Lavoro Pubblico
	public static final int COD_DICHIARANTE_D = 4; //Datore lavoro privato appartenente a gruppo d'imprese

	// *************************************************************************
	// RUOLI
	public static final String RUOLO_AMMINISTRATORE_PRODIS = "Amministratore";
	public static final String RUOLO_PROVINCIA_OPERATORE_PRODIS = "Operatore Provinciale";
	public static final String RUOLO_CONSULENTE_RESPONSABILE = "Consulente Responsabile";
	//	public static final String RUOLO_REGPIEMONTE_RESPONSABILE = "";
	//	public static final String RUOLO_REGPIEMONTE_CALLCENTER = "";
	//	public static final String RUOLO_REGPIEMONTE_OPERATORE = "";
	public static final String RUOLO_LEGALE_RAPPRESENTANTE = "Legale rappresentante";
	public static final String RUOLO_PERSONA_CON_CARICA_AZIENDALE = "Persona con carica aziendale";
	public static final String RUOLO_PERSONA_aUTORIZZATA = "Persona autorizzata";
	// *************************************************************************

	// casi d'uso
	public static final String UC0_PRO_MONITORAGGIO = "UC0_PRO_MONITORAGGIO";
	public static final String UC1_PRO_ACCESSO = "UC1_PRO_ACCESSO";
	public static final String UC2_PRO_RICERCA = "UC2_PRO_RICERCA";
	public static final String UC3_PRO_INSERIMENTO = "UC3_PRO_INSERIMENTO";
	public static final String UC4_PRO_VERIFICA = "UC4_PRO_VERIFICA";
	public static final String UC5_PRO_GESTIONEAPPL = "UC5_PRO_GESTIONEAPPL";

	public static final int STATO_CONCESSIONE_APPROVATA_CONCESSA = 1;
	public static final int CAUSA_SOSPENSIONE_B_MOBILITA = 2;
	public static final int STATO_SOSPENSIONE_APPROVATA_CONCESSA = 1;

	public static final String CATEGORIA_SOGGETTO_C = "C";
	public static final String CATEGORIA_SOGGETTO_D = "D";

	public static final String CATEGORIA_COMPENSAZIONE_E = "E";
	public static final String CATEGORIA_COMPENSAZIONE_R = "R";

	public static final String TIPO_PROVENIENZA_PRODIS = "P";
	public static final String TIPO_PROVENIENZA_MINISTERO = "M";

	public static final String STATO_CONCESSIONE_E = "E";
	public static final String STATO_CONCESSIONE_F = "F";

	public static final int TIPO_COMUNICAZIONE_PROSPETTO_INFORMATIVO = 1;
	public static final int TIPO_COMUNICAZIONE_PROSPETTO_RETTIFICA = 2;
	public static final int TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO = 3;

	public static final String TIPO_COMUNICAZIONE_PROSPETTO_ANNULLAMENTO_DESC = "Annullamento";

	public static final String AMBITO_CATEGORIA_ESCUSA_E = "E";
	public static final String AMBITO_CATEGORIA_ESCUSA_D = "D";
	public static final String AMBITO_CATEGORIA_ESCUSA_C = "C";

	// drop list custom
	public static final String TIPO_PARTIME_DESC = "Part-Time";
	public static final String TIPO_PARTIME_COD = "P";
	public static final String TIPO_INTERMITTENTE_DESC = "Intermittente";
	public static final String TIPO_INTERMITTENTE_COD = "I";

	// nome cache in sessione
	public static final String CACHE_TABELLE_STATICHE = "CACHE_TABELLE_STATICHE";

	// costanti per controlli 
	public static final int MAX_LAVORATORI_AUTOCOMPENSAZIONE = 50;
	public static final int MIN_LAVORATORI_OBBLIGO_PROSPETTO = 15;
	public static final int NUM_ANNI_PRECEDENTI = 15;

	// costanti parametri
	public static final String TERMINE_ANNULLAMENTO = "termine.annullamento";
	public static final String TERMINE_RETTIFICA = "termine.rettifica";
	public static final String MAX_NUM_PROSPETTI = "ricerca.prospetto.numeroMassimoRecord";
	public static final String MAX_NUM_PROSPETTI_INVIABILI = "recovery.numeroMassimoProspettiInviabili";
	public static final String ABILITAZIONE_INVIO_MAIL = "abilitazione";
	public static final String ABILITAZIONE_APPLICATIVO = "prodis.web.abilitato";

	public static final String COMUNI_NASCITA_COD = "COMUNI_NASCITA_COD";
	public static final String STATO_ESTERO_NASCITA_COD = "STATO_ESTERO_NASCITA__COD";
	public static final String QUALIFICA_COD = "QUALIFICA_COD";
	public static final String ASSUNZIONE_PROTETTA = "ASSUNZIONE_PROTETTA";
	public static final String CONTRATTI_COD = "CONTRATTI_COD";
	public static final String CATEGORIA_SOGGETTI_COD = "CATEGORIA_SOGGETTI_COD";
	public static final String CATEGORIA_ASSUNZIONI_COD = "CATEGORIA_ASSUNZIONI_COD";

	public static final String FLAG_SI = "Si";
	public static final String FLAG_NO = "No";

	public static final String SIGLA_ITALIANA = "IT";

	public static final String DATA_17_01_2000 = "17/01/2000";

	public static final String MASSIVO_FROM_PROSPETTO = "massivoFromProspetto";
	public static final String MASSIVO_FROM_QUADRO_2 = "massivoFromQuadro2";
	public static final String ID_PROSPETTO_PROVINCIALE_FOR_MASSIVO = "idProspettoProvincialeForMassivo";

	public static final String CONTRATTO_FORMA_INDETERMINATO = "I";
	public static final String CONTRATTO_FORMA_DETERMINATO = "D";
	public static final String CONTRATTO_FORMA_ENTRAMBE = "E";

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

	public static final String PROTOCOLLAZIONE_IUP_PROTOCOLLO_T3 = "t3";
	public static final String PROTOCOLLAZIONE_IUP_PROTOCOLLO_WS = "ws";

	public static final String SESSION_WARNING_AZIENDA_CATEG_C = "messaggio.warning.azienda.cat.C";

}
