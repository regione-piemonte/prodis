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
package it.csi.prodis.prodissrv.lib.dto.error;

/**
 * PRODIS error types
 */
public enum MsgProdis implements ErrorCreator {

	SEC00001("SEC00001", "Utente non autorizzato"), // Errore
	CR00001("CR00001", "Impostare correttamente i criteri di ricerca"), // Errore
	CR00002("CR00002", "Il numero di record massimo non può essere superiore a 200"), // Errore

	CFA00001("CFA00001", "Id_prospetto non valorizzato"),

	ANA0001("ANA0001", "Prospetto non trovato"), // Messaggio
	OK000001("OK000001", "Operazione conclusa correttamente"), // Messaggio
	DBA00001("DBA00001", "Errore interno di sistema"), // Errore
	
	//------------- SERVIZIO IMPORTAZIONE PROSPETTI DA SPICOM --------------/
	SP0001("SP0001", "Prospetto gia\' presente in PRODIS"),
	SP0002("SP0002", "Errore tecnico"),
	SP0003("SP0003", "Errore validazione"),
	;

	// modulo-oggetto - Errore/Avviso/Prompt
	// PROPROP0001("PRO-PRO-P-0001", "Nessun risultato trovato");

	private final String code;
	private final String type;
	private final String group;
	private final String message;

	/**
	 * Private constructor
	 * 
	 * @param code    the code
	 * @param message the message
	 */
	private MsgProdis(String code, String message) {
		this(code, null, message);
	}

	/**
	 * Private constructor
	 * 
	 * @param code    the code
	 * @param group   the group
	 * @param message the message
	 */
	private MsgProdis(String code, String group, String message) {
		this.code = code;
		this.type = "ERROR";
		this.group = group;
		this.message = message;
	}

	private MsgProdis(String code, String type, String group, String message) {
		this.code = code;
		this.type = type;
		this.group = group;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getGroup() {
		return group;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
