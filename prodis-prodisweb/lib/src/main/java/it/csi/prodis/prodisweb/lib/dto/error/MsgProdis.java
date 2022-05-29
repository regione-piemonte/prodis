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
package it.csi.prodis.prodisweb.lib.dto.error;

/**
 * PRODIS error types
 */
public enum MsgProdis implements ErrorCreator {

	// modulo-oggetto - Errore/Avviso/Prompt

	/*
	 * ATTENZIONE RICORDARSI DI ANDARE SUL FILE it.json parte angular e agginugere
	 * eventuali msg inseriti nuovi
	 */

	PROPROP0001("PRO-PRO-P-0001", "Aggiornamento/Inserimento del prospetto in bozza avvenuto correttamente"),
	PROPROP0002("PRO-PRO-P-0002", "Nessun risultato trovato"),
	PROPROE0003("PRO-PRO-E-0003", "Errori durante il salvataggio in bozza"),
	PROPROE0004("PRO-PRO-E-0004",
			"Attenzione! I prospetti trovati superano il massimo consentito. Inserire criteri di ricerca piu' restrittivi."),
	PROPROE0005("PRO-PRO-E-0005",
			"Attenzione! Lo stato attuale del prospetto non permette di procedere nel salvataggio."),
	PROPROE0006("PRO-PRO-E-0006", "E' necessario valorizzare il comune per il referente."),
	PROPROE0007("PRO-PRO-E-0007", "E' necessario valorizzare il comune per la sede legale."),
	PROPROE0008("PRO-PRO-E-0008", "Attenzione! Dati inerenti il comune referente non congruenti."),
	PROPROE0009("PRO-PRO-E-0009", "Attenzione! Dati inerenti il comune sede legale non congruenti."),
	PROPROE0010("PRO-PRO-E-0010", "Attenzione! Comune referente scaduto."),
	PROPROE0011("PRO-PRO-E-0011", "Attenzione! Comune sede legale scaduto."),
	PROPROE0012("PRO-PRO-E-0012", "Il campo Ateco (codice e descrizione) e' obbligatorio."),
	PROPROE0013("PRO-PRO-E-0013", "Attenzione! Dati inerenti l'Atecofin  non congruenti."),
	PROPROE0014("PRO-PRO-E-0014", "Attenzione! Atecofin scaduto."),
	PROPROE0015("PRO-PRO-E-0015", "Il campo CCNL (codice e descrizione) e' obbligatorio."),
	PROPROE0016("PRO-PRO-E-0016", "Attenzione! Dati inerenti il CCNL non congruenti."),
	PROPROE0017("PRO-PRO-E-0017", "Attenzione! CCNL scaduto."),

	PROPROE0018("PRO-PRO-E-0018", "Il campo comune (codice e descrizione) per la sede di riferimento e' obbligatorio."),
	PROPROE0019("PRO-PRO-E-0019", "Attenzione! Comune sede di riferimento scaduto."),
	PROPROE0020("PRO-PRO-E-0020", "Valorizzare correttamente tutte le informazioni sul prov intermittente."),
	//
	PROPROE0021("PRO-PRO-E-0021", "CAP sede legale non valido. Deve essere un numero di 5 cifre."),
	PROPROE0022("PRO-PRO-E-0022",
			"Telefono sede legale non valido. Deve essere un numero di massimo 15 cifre. E' ammesso solo il separatore /."),
	PROPROE0023("PRO-PRO-E-0023",
			"Fax sede legale non valido. Deve essere un numero di massimo 15 cifre. E' ammesso solo il separatore /."),
	PROPROE0024("PRO-PRO-E-0024", "Codice fiscale referente non valido. Deve essere un valore di 11 o 16 caratteri."),
	PROPROE0025("PRO-PRO-E-0025", "CAP referente non valido. Deve essere un numero di 5 cifre."),
	PROPROE0026("PRO-PRO-E-0026",
			"Telefono referente non valido. Deve essere un numero di massimo 15 cifre. E' ammesso solo il separatore /."),
	PROPROE0027("PRO-PRO-E-0027",
			"Fax referente non valido. Deve essere un numero di massimo 15 cifre. E' ammesso solo il separatore /."),
	PROPROE0028("PRO-PRO-E-0028", "Data riferimento prospetto non valida. Deve essere nel formato GG/MM/AAAA."),
	PROPROE0029("PRO-PRO-E-0029", "Data atto non valida. Deve essere nel formato GG/MM/AAAA."),
	PROPROE0030("PRO-PRO-E-0030", "Data trasformazione non valida. Deve essere nel formato GG/MM/AAAA."),
	PROPROE0031("PRO-PRO-E-0031", "Data fine sospensione non valida. Deve essere nel formato GG/MM/AAAA."),
	PROPROE0032("PRO-PRO-E-0032", "Percentuale gradualita' non valida."),
	PROPROE0033("PRO-PRO-E-0033", "Il codice fiscale azienda e' obbligatorio."),
	PROPROE0034("PRO-PRO-E-0034", "La denominazione azienda e' obbligatoria."),
	PROPROE0035("PRO-PRO-E-0035", "Codice fiscale azienda non valido. Deve essere un valore di 11 o 16 caratteri."),
	PROPROE0036("PRO-PRO-E-0036", "Ateco incompleto. Manca la descrizione."),
	PROPROE0037("PRO-PRO-E-0037", "Ateco incompleto. Manca il codice."),
	PROPROE0038("PRO-PRO-E-0038", "CCNL incompleto. Manca la descrizione."),
	PROPROE0039("PRO-PRO-E-0039", "CCNl incompleto. Manca il codice."),

	PROPROE0040("PRO-PRO-E-0040", "Comune sede legale incompleto. Manca la descrizione."),
	PROPROE0041("PRO-PRO-E-0041", "Comune sede legale incompleto. Manca il codice."),
	PROPROE0042("PRO-PRO-E-0042", "Codice fiscale referente non valido. Deve essere un valore di 16 caratteri."),
	PROPROE0043("PRO-PRO-E-0043", "Codice fiscale del referente non valido."),
	PROPROE0044("PRO-PRO-E-0044", "Comune referente incompleto. Manca la descrizione."),
	PROPROE0045("PRO-PRO-E-0045", "Comune referente incompleto. Manca il codice."),
	PROPROE0046("PRO-PRO-E-0046", "Codice fiscale capogruppo non ammesso per tipologia dichiarante."),
	PROPROE0047("PRO-PRO-E-0047", "Tipologia dichiarante non ammessa se si presenta il prospetto come capogruppo."),
	PROPROE0048("PRO-PRO-E-0048", "Codice fiscale obbligatorio per la tipologia dichiarante scelta."),
	PROPROE0049("PRO-PRO-E-0049", "L'azienda capogruppo non puo' essere straniera."),
	PROPROE0050("PRO-PRO-E-0050",
			"Il codice fiscale dell' azienda capogruppo deve essere uguale al codice fiscale dell' azienda."),
	PROPROE0051("PRO-PRO-E-0051",
			"Se non si presenta il prospetto per il capogruppo, il codice fiscale azienda capogruppo deve essere diverso dal codice fiscale dell' azienda."),
	PROPROE0052("PRO-PRO-E-0052", "Compilare sia la sigla internazionale che il campo codice fiscale."),
	PROPROE0053("PRO-PRO-E-0053", "Il codice fiscale dell'azienda capogruppo non e' valido."),
	PROPROE0054("PRO-PRO-E-0054", "Data atto non valida. Non deve essere superiore alla data riferimento."),
	PROPROE0055("PRO-PRO-E-0055", "Data traformazione non valida. Non deve essere superiore alla data riferimento."),
	PROPROE0056("PRO-PRO-E-0056", "La data di riferimento deve essere impostata al 31/12 dell'anno precedente."),
	PROPROE0057("PRO-PRO-E-0057", "Indirizzo PEC sede lagale non valido."),
	PROPROE0058("PRO-PRO-E-0058", "Indirizzo mail referente non valido."),

	PROPROE0059("PRO-PRO-E-0059", "Il campo tipologia del dichiarante e' obbligatorio."),
	PROPROE0060("PRO-PRO-E-0060", "Il campo PEC sede legale della sede legale e' obbligatorio."),
	PROPROE0061("PRO-PRO-E-0061", "Il campo cap della sede legale e' obbligatorio."),
	PROPROE0062("PRO-PRO-E-0062", "Il campo indirizzo della sede legale e' obbligatorio."),
	PROPROE0063("PRO-PRO-E-0063", "E' necessario valorizzare almeno uno tra i campi telefono e fax della sede legale."),
	PROPROE0064("PRO-PRO-E-0064", "Il campo codice fiscale del referente e' obbligatorio."),
	PROPROE0065("PRO-PRO-E-0065", "Il campo cognome del referente e' obbligatorio."),
	PROPROE0066("PRO-PRO-E-0066", "Il campo nome del referente e' obbligatorio."),
	PROPROE0067("PRO-PRO-E-0067", "Il campo cap del referente e' obbligatorio."),
	PROPROE0068("PRO-PRO-E-0068", "Il campo indirizzo del referente &egrave; obbligatorio."),
	PROPROE0069("PRO-PRO-E-0069",
			"E' necessario valorizzare almeno uno tra i campi telefono, fax, e-mail del referente."),
	PROPROE0070("PRO-PRO-E-0070", "Il campo data riferimento prospetto e' obbligatorio."),
	PROPROE0071("PRO-PRO-E-0071",
			"I campi data atto, estremi atto, n. assunzione lav., data trasformazione e percentuale sono da valorizzare insieme."),
	PROPROE0072("PRO-PRO-E-0072", "Il campo sospensione per mobilita' e' obbligatorio."),
	PROPROE0073("PRO-PRO-E-0073",
			"Il campo Data fine sospensione deve essere valorizzato se Sospensione per mobilita' e' uguale a Si."),
	PROPROE0074("PRO-PRO-E-0074",
			"Il campo Data fine sospensione non deve essere valorizzato se Sospensione per mobilita' e' uguale a No."),
	PROPROE0075("PRO-PRO-E-0075",
			"Per ogni assunzione mediante pubblica selezione occorre valorizzare i campi regione, saldo disabili e Saldo ex Art.18."),

	PROPROE0076("PRO-PRO-E-0076",
			"La tipologia del dichiarante non puo' essere 'Datore lavoro pubblico', se sono valorizzati i campi della Gradualita' nella sezione Dati prospetto."),
	PROPROE0077("PRO-PRO-E-0077", "Non e' possibile avere piu' di un'assunzione per ogni regione."),

	PROPROE0078("PRO-PRO-E-0078",
			"Codice fiscale referente non valido. Deve essere coerente con i dati anagrafici del referente."),
	PROPROE0080("PRO-PRO-E-0080",
			"Attenzione! Compilare i dati inerenti la gradualita' nel Quadro 1 in quanto e' stato valorizzato il campo Num. Assunzioni effettuate dopo la trasformazione nei dati provinciali per almeno una provincia del prospetto."),
	PROPROE0081("PRO-PRO-E-0081",
			"Impossibile recuperare il comune comune dei dati azienda. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROPROE0082("PRO-PRO-E-0082",
			"Impossibile recuperare il comune della sede legale. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROPROE0083("PRO-PRO-E-0083",
			"Impossibile recuperare l'Atecofin. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROPROE0084("PRO-PRO-E-0084",
			"Impossibile recuperare il CCNL. Effettuare la compilazione del campo usando il tasto 'Cerca'."),

	// Dati provinciali
	PRODPRE0001("PRO-DPR-E-0001", "Numero totale lavoratori dipendenti non valido."),
	PRODPRE0002("PRO-DPR-E-0002", "CAP sede riferimento non valido. Deve essere un numero di 5 cifre."),
	PRODPRE0003("PRO-DPR-E-0003", "Telefono sede riferimento non valido. Deve essere un numero con separatore /."),
	PRODPRE0004("PRO-DPR-E-0004", "Fax sede riferimento non valido. Deve essere un numero con separatore /."),
	PRODPRE0005("PRO-DPR-E-0005", "Numero lavoratori in telelavoro impiegati in azienda non valido."),
	PRODPRE0006("PRO-DPR-E-0006", "Numero disabili in forza L.68/99 non valido."),
	PRODPRE0007("PRO-DPR-E-0007", "Numero centralinisti telefonici non valido."),
	PRODPRE0008("PRO-DPR-E-0008", "Numero centralinisti telefonici L113 non valido."),
	PRODPRE0009("PRO-DPR-E-0009", "Numero terapisti L29 non valido."),
	PRODPRE0010("PRO-DPR-E-0010", "Numero terapisti L403 non valido."),
	PRODPRE0011("PRO-DPR-E-0011", "Numero categorie protette non valido."),
	PRODPRE0012("PRO-DPR-E-0012", "Numero categorie protette in forza al 17/01/2000 non valido."),
	PRODPRE0013("PRO-DPR-E-0013", "Numero lavoratori disabili somministrati art.34, co.3, D.Lgs 81/2015 non valido."),
	PRODPRE0014("PRO-DPR-E-0014", "Numero lavoratori disabili in convenzione art.12-bis e 14 non valido."),
	PRODPRE0015("PRO-DPR-E-0015", "Data atto azienda in convenzione non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0016("PRO-DPR-E-0016",
			"Data stipula atto azienda in convenzione non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0017("PRO-DPR-E-0017",
			"Data scadenza azienda in convenzione non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0018("PRO-DPR-E-0018", "Data atto azienda in esonero non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0019("PRO-DPR-E-0019", "Data fino al azienda in esonero non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0020("PRO-DPR-E-0020", "Percentale azienda in esonero non valida."),
	PRODPRE0021("PRO-DPR-E-0021", "Numero lavoratori in esonero non valido."),
	PRODPRE0022("PRO-DPR-E-0022", "Numero lavoratori in sospensione non valido."),
	PRODPRE0023("PRO-DPR-E-0023", "Numero assunzioni dopo trasformazione non valido."),
	PRODPRE0024("PRO-DPR-E-0024", "Il campo note deve essere lungo al massimo 2000 caratteri."),
	PRODPRE0025("PRO-DPR-E-0025", "Numero lavoratori previsti in Convenzione non valido."),
	PRODPRE0026("PRO-DPR-E-0026",
			"Data data fine sospensione in Sospensioni non valida. Deve essere nel formato GG/MM/AAAA."),
	PRODPRE0027("PRO-DPR-E-0027", "Il numero di lavoratori previsti puo' essere compreso tra 0 e 99."),
	PRODPRE0028("PRO-DPR-E-0028", "Data autocertificazione non valida."),
	PRODPRE0029("PRO-DPR-E-0029", "Num. lavoratori 60 per mille non valido."),
	PRODPRE0030("PRO-DPR-E-0030", "Num. lavoratori in esonero parziale autocertificato non valido."),
	PRODPRE0031("PRO-DPR-E-0031", "Percentale azienda in esonero autocertificato non valida."),

	PRODPRE0032("PRO-DPR-E-0032", "Il numero totale lavoratori dipendenti e' obbligatorio."),
	PRODPRE0033("PRO-DPR-E-0033", "Il numero disabili in forza L.68/99 e' obbligatorio."),
	PRODPRE0034("PRO-DPR-E-0034", "Il numero centralinisti telefonici e' obbligatorio."),
	PRODPRE0035("PRO-DPR-E-0035", "Il numero centralinisti telefonici L113 e' obbligatorio."),
	PRODPRE0036("PRO-DPR-E-0036", "Il numero terapisti L29 e' obbligatorio."),
	PRODPRE0037("PRO-DPR-E-0037", "Il numero terapisti L113 e' obbligatorio."),
	PRODPRE0038("PRO-DPR-E-0038", "Il numero categorie protette e' obbligatorio."),
	PRODPRE0039("PRO-DPR-E-0039", "Il numero categorie protette conteggiate come disabili e' obbligatorio."),
	PRODPRE0040("PRO-DPR-E-0040", "Il numero categorie protette esubero e' obbligatorio."),
	PRODPRE0041("PRO-DPR-E-0041",
			"Per inserire l'esonero occorre valorizzare correttamente tutti i dati della sezione; per eliminare l'esonero, occorre cancellare tutti i dati della sezione"),
	PRODPRE0042("PRO-DPR-E-0042", "Il campo Data autocertificazione e' obbligatorio."),
	PRODPRE0043("PRO-DPR-E-0043", "Il campo Num. lavoratori 60 per mille e' obbligatorio."),
	PRODPRE0044("PRO-DPR-E-0044", "Il campo Num. lavoratori in esonero parziale autocertificato e' obbligatorio."),

	PRODPRE0045("PRO-DPR-E-0045", "Indirizzo e-mail sede di riferimento non valido."),
	PRODPRE0046("PRO-DPR-E-0046", "La data scadenza deve essere successiva alla data stipula"),
	PRODPRE0047("PRO-DPR-E-0047", "Il campo cap della sede di riferimento e' obbligatorio."),
	PRODPRE0048("PRO-DPR-E-0048", "Il campo indirizzo della sede di riferimento e' obbligatorio."),
	PRODPRE0049("PRO-DPR-E-0049",
			"E' necessario valorizzare almeno uno tra i campi telefono, fax, e-mail della sede di riferimento."),
	PRODPRE0050("PRO-DPR-E-0050", "Il campo cognome referente e' obbligatorio."),
	PRODPRE0051("PRO-DPR-E-0051", "Il campo nome referente e'; obbligatorio."),
	PRODPRE0052("PRO-DPR-E-0052",
			"Il campo stato in convenzione e' obbligatorio se si valorizza la tipologia di convenzione."),
	PRODPRE0053("PRO-DPR-E-0053", "Il campo estremi atto e' obbligatorio per lo stato selezionato."),
	PRODPRE0054("PRO-DPR-E-0054", "Il campo tipologia di convenzione e' obbligatorio se si valorizza lo stato."),
	PRODPRE0055("PRO-DPR-E-0055", "Il campo data atto Convenzione e' obbligatorio per lo stato selezionato."),
	PRODPRE0056("PRO-DPR-E-0056", "Il campo data stipula e' obbligatorio per lo stato selezionato."),
	PRODPRE0057("PRO-DPR-E-0057", "Il campo data scadenza e' obbligatorio per lo stato selezionato."),
	PRODPRE0058("PRO-DPR-E-0058",
			"Il campo numero lavoratori previsti in convenzione e' obbligatorio per lo stato selezionato."),
	PRODPRE0059("PRO-DPR-E-0059", "Il campo data atto Esonero Autorizzato e' obbligatorio per lo stato selezionato."),
	PRODPRE0061("PRO-DPR-E-0061", "Il campo fino al e' obbligatorio per lo stato selezionato."),
	PRODPRE0062("PRO-DPR-E-0062", "Il campo percentuale e' obbligatorio per lo stato selezionato."),
	PRODPRE0063("PRO-DPR-E-0063", "Il campo numero lavoratori in esonero e' obbligatorio per lo stato selezionato."),
	PRODPRE0064("PRO-DPR-E-0064",
			"Lo stato e la causale scelti nella sezione 'sospensioni' richiedono l'indicazione della sospensione per mobilita' nei Dati prospetto del Quadro 1."),
	PRODPRE0065("PRO-DPR-E-0065", "La sezione 'sospensioni' non puo' essere popolata in modo parziale."),
	PRODPRE0066("PRO-DPR-E-0066",
			"Il campo data fine sospensione deve essere valorizzato se sono valorizzati gli altri campi della sezione Sospensioni."),
	PRODPRE0067("PRO-DPR-E-0067", "Trovati piu' comuni per codice e/o descrizione inseriti."),
	PRODPRE0068("PRO-DPR-E-0068", "Nessun comune trovato per codice e/o descrizione inseriti."),
	PRODPRE0069("PRO-DPR-E-0069",
			"La sospensione per mobilita' a carattere nazionale deve essere attivata essendo gia' approvata a livello provinciale."),
	PRODPRE0070("PRO-DPR-E-0070", "Il CAP della sede e' obbligatorio."),
	PRODPRE0071("PRO-DPR-E-0071", "L'indirizzo della sede e' obbligatorio."),
	PRODPRE0072("PRO-DPR-E-0072", "Occorre valorizzare almeno uno tra numero di telefono fax e mail."),
	PRODPRE0073("PRO-DPR-E-0073",
			"Numero assunzioni dopo trasformazione non ammesso; non e' stata compilata la sezione Gradualita' nei Dati prospetto del Quadro 1."),
	PRODPRE0074("PRO-DPR-E-0074",
			"Numero assunzioni dopo trasformazione obbligatorio; e' stata compilata la sezione Gradualita' nei Dati prospetto del Quadro 1."),
	PRODPRE0075("PRO-DPR-E-0075", "La data atto della convenzione non deve essere successiva alla data riferimento."),
	PRODPRE0076("PRO-DPR-E-0076", "La data stipula non deve essere successiva alla data riferimento."),
	PRODPRE0077("PRO-DPR-E-0077", "La data atto dell'esonero non deve essere successiva alla data riferimento."),
	PRODPRE0078("PRO-DPR-E-0078",
			"La tipologia del dichiarante non puo' essere 'Datore lavoro pubblico', se sono valorizzati i campi della sezione \\ Esonero parziale autocertificato 60 per mille art. 5, co.3-bis L.68/99\\ . "),
	PRODPRE0079("PRO-DPR-E-0079", "La data autocertificazione deve essere minore o uguale alla data riferimento."),

	PRODPRE0080("PRO-DPR-E-0080",
			"Tipo assunzione protetta scaduta o non trovata. Impossibile proseguire nei controlli e nei conteggi."),
	PRODPRE0081("PRO-DPR-E-0081",
			"La somma dei 'lavoratori computabili' con 'categoria soggetto D' non somministrati o in convenzione, "
					+ "deve essere maggiore o uguale alla somma di: N. Disabili in forza L.68/99 a tempo pieno e part-time + "
					+ "N. centralinisti telefonici non vedenti a tempo pieno e part-time "
					+ "+ N. terapisti della riabilitazione e massofisioterapisti non vedenti a tempo pieno e part-time."),
	PRODPRE0082("PRO-DPR-E-0082",
			"La somma dei 'lavoratori computabili' con 'categoria soggetto D' non somministrati o in convenzione e 'tipo assunzione protetta massofisioterapisti', "
					+ "deve essere maggiore o uguale al N. terapisti della riabilitazione e massofisioterapisti non vedenti a tempo pieno e part-time."),
	PRODPRE0083("PRO-DPR-E-0083",
			"La somma dei 'lavoratori computabili' con 'categoria soggetto D' non somministrati o in convenzione e 'tipo assunzione protetta centralinista', "
					+ "deve essere maggiore o uguale al N.; centralinisti telefonici non vedenti a tempo pieno e part-time."),
	PRODPRE0084("PRO-DPR-E-0084", "La somma dei 'lavoratori computabili' con 'categoria soggetto D' somministrati, "
			+ "deve essere maggiore o uguale al N.; lavoratori disabili somministrati a tempo pieno e part-time."),
	PRODPRE0085("PRO-DPR-E-0085", "La somma dei 'lavoratori computabili' con 'categoria soggetto D' in convenzione, "
			+ " deve essere maggiore o uguale al N. lavoratori disabili in convenzione art.12-bis e 14 a tempo pieno e part-time."),
	PRODPRE0086("PRO-DPR-E-0086",
			"La somma dei 'lavoratori computabili' con 'categoria soggetto C' deve essere maggiore o uguale a: N. categorie protette in forza."),
	PRODPRE0087("PRO-DPR-E-0087",
			"Il N. dei lavoratori appartenenti alle categorie protette in forza al 17/01/2000 deve essere minore o uguale a N. categorie protette in forza."),
	PRODPRE0088("PRO-DPR-E-0088",
			"Il N. categorie protette in esubero deve essere maggiore o uguale al N. delle categorie protette conteggiate come disabili."),
	PRODPRE0089("PRO-DPR-E-0089",
			"La somma dei lavoratori computabili con categoria C e data assunzione minore o uguale al 17/01/2000 deve essere maggiore o "
					+ " uguale al numero delle categorie protette di cui in forza al 17/01/2000."),
	PRODPRE0090("PRO-DPR-E-0090", "I dati relativi ai lavoratori non sono stati confermati."),
	PRODPRE0091("PRO-DPR-E-0091", "Per eliminare le convenzioni e' necessario cancellare tutti i campi relativi"),
	PRODPRE0092("PRO-DPR-E-0092", "Per eliminare le sospensioni e' necessario cancellare tutti i campi relativi"),

//	PRODPRE0091	("PRO-DPR-E-0091", message.append("Il lavoratore ");
//	message.append(lav.getCognome()).append(" ");
//	message.append(lav.getNome()).append(" (cf:");
//	message.append(lav.getCodiceFiscale());
//	message.append(") ");
//	message.append("presenta una data fine rapporto inferiore alla data riferimento del prospetto. "),

//	PRODPRE0092	("PRO-DPR-E-0092", "I dati relativi alle province elencate non sono stati confermati:"),
	PRODPRE0093("PRO-DPR-E-0093", "Impossibile eseguire i controlli sul comune della sede legale."),
	PRODPRE0094("PRO-DPR-E-0094", "Non sono stati inseriti i dati relativi alla provincia della sede legale."),
//	PRODPRW0095	("PRO-DPR-W-0095", "Per questa categoria di azienda (15-35 dipendenti) e' possibile considerare i part time come tempo pieno e conteggiare la persona come 1 unita', " +
//			"se il lavoratore disabile ha invalidita' superiore al 50&#37; o ascrivibile alla 5&deg; categoria, indipendentemente dall'orario di lavoro svolto."),
	PRODPRE0096("PRO-DPR-E-0096", "Nessun dato provinciale e' stato inserito."),
	PRODPRW0097("PRO-DPR-W-0097",
			"Attenzione: ci sono delle scoperture, verificare la correttezza dei dati inseriti ed eventualmente indicare i 'posti di lavoro disponibili', tramite l'apposita sezione."),
	PRODPRW0098("PRO-DPR-W-0098",
			"Attenzione: ci sono delle scoperture su almeno una provincia, verificare la correttezza dei dati inseriti ed eventualmente indicare le 'compensazioni territoriali', tramite l'apposita sezione."),
	PRODPRE0099("PRO-DPR-E-0081",
			"Impossibile recuperare il comune della sede di riferimento. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PRODPRW0101("PRO-DPR-W-0101",
			"Attenzione! Non è stato valorizzato il campo Num. Assunzioni effettuate dopo la trasformazione nei dati del Quadro 2."),

	PROLAVE0001("PRO-LAV-E-00001", "Codice fiscale lavoratore obbligatorio."),
	PROLAVE0002("PRO-LAV-E-00002", "Codice fiscale formalmente errato."),
	PRODLAV0003("PRO-LAV-E-00003",
			"Codice fiscale errato, verificarne la congruenza con i parametri che lo compongono."),
	PROLAVE0004("PRO-LAV-E-00004", "Cognome lavoratore obbligatorio."),
	PROLAVE0005("PRO-LAV-E-00005", "Nome lavoratore obbligatorio."),
	PROLAVE0006("PRO-LAV-E-00006", "Sesso lavoratore obbligatorio."),
	PROLAVE0007("PRO-LAV-E-00007", "E' obbligatorio valorizzare almeno uno tra comune e stato estero di nascita."),
	PROLAVE0008("PRO-LAV-E-00008", "Data di nascita lavoratore obbligatoria."),
	PROLAVE0009("PRO-LAV-E-00009", "Formato data di nascita lavoratore non valido."),
	PROLAVE0010("PRO-LAV-E-00010", "Specificare solo uno tra comune e stato estero di nascita."),

	PROLAVE0011("PRO-LAV-E-00011",
			"Nessun comune soddisfa codice e/o descrizione impostati o comune non esistente alla data di nascita del lavoratore."),
	PROLAVE0012("PRO-LAV-E-00012", "Errore nella ricerca del comune."),
	PROLAVE0013("PRO-LAV-E-00013",
			"Nessuno stato estero soddisfa codice e/o descrizione impostati o stato non esistente alla data di nascita del lavoratore."),

	PROLAVE0014("PRO-LAV-E-00014", "Data inizio rapporto obbligatoria."),
	PROLAVE0015("PRO-LAV-E-00015", "Formato data inizio rapporto non valido."),
	PROLAVE0016("PRO-LAV-E-00016", "La data inizio rapporto non deve essere successiva alla data riferimento."),
	PROLAVE0017("PRO-LAV-E-00017", "La data inizio rapporto deve essere maggiore alla data di nascita del lavoratore."),
	PROLAVE0018("PRO-LAV-E-00018", "Tipo assunzione protetta obbligatorio."),

	PROLAVE0019("PRO-LAV-E-00019", "Tipo contratto o tipo assunzione scaduta."), PROLAVE0020("PRO-LAV-E-00020",
			"Per il tipo assunzione protetta selezionato non e' ammesso nessun valore di tipologia contrattuale."),
//	PROLAVE0021	("PRO-LAV-E-00021", "Il campo Forma contratto e' obbligatorio."),
//	PROLAVE0021	("PRO-LAV-E-00021", "Il campo Forma contratto non e' coerente con il contratto / tipo assunzione protetta a tempo indeterminato."),
//	PROLAVE0021	("PRO-LAV-E-00021", "Il campo Forma contratto non e' coerente con il contratto / tipo assunzione protetta  a tempo determinato."),
//	PROLAVE0022	("PRO-LAV-E-00022", "La data di fine rapporto non deve essere valorizzata per contratti  / tipo assunzione protetta a tempo indeterminato."),
//	PROLAVE0022	("PRO-LAV-E-00022", "La data di fine rapporto obbligatoria per contratti  / tipo assunzione protetta a tempo determinato."),
//	PROLAVE0022	("PRO-LAV-E-00022", "Formato data di fine rapporto non valida."),
//	PROLAVE0022	("PRO-LAV-E-00022", "La data di fine rapporto deve essere posteriore a quella di inizio rapporto."),
//	PROLAVE0022	("PRO-LAV-E-00022", "Il campo Forma contratto e' obbligatorio."),
//	PROLAVE0022	("PRO-LAV-E-00022", "La Data fine rapporto e' obbligatoria per la tipologia e la forma contrattuali selezionate."),
//	PROLAVE0022	("PRO-LAV-E-00022", "La Data fine rapporto non deve essere valorizzata per la tipologia e la forma contrattuali selezionate."),
	PROLAVE0023("PRO-LAV-E-00023", "Tipologia contrattuale obbligatoria."),
	PROLAVE0024("PRO-LAV-E-00024", "La data fine rapporto non deve essere inferiore alla data riferimento."),
	PROLAVE0025("PRO-LAV-E-00025", "Il tipo assunzione protetta e' incompatibile con il tipo contratto tirocinio."),
	PROLAVE0026("PRO-LAV-E-00026", "Qualifica ISTAT obbligatorio."),

	PROLAVE0027("PRO-LAV-E-00027", "Nessuna qualifica ISTAT soddisfa codice e/o descrizione impostati."),
	PROLAVE0028("PRO-LAV-E-00028", "Trovate piu' qualifiche ISTAT che soddisfano codice e/o descrizione impostati"),
	PROLAVE0029("PRO-LAV-E-00029", "Qualifica Istat scaduta o non trovata."),
	PROLAVE0030("PRO-LAV-E-00030", "Orario settimanale e' un campo obbligatorio (HH:MM)."),
	PROLAVE0031("PRO-LAV-E-00031",
			"Formato orario settimale non corretto, il formato deve essere ore:minuti (esempio 7:40, 7:05 ecc.)."),
	PROLAVE0032("PRO-LAV-E-00032", "Orario settimanale part time e' un campo obbligatorio (HH:MM)."),
	PROLAVE0033("PRO-LAV-E-00033",
			"Formato orario settimale part time non corretto, il formato deve essere ore:minuti (esempio 7:40, 7:05 ecc.)."),
	PROLAVE0034("PRO-LAV-E-00034", "Categoria soggetto e' un campo obbligatorio."),
	PROLAVE0035("PRO-LAV-E-00035", "Non e' possibile inserire un valore percentuale superiore a 100."),
	PROLAVE0036("PRO-LAV-E-00036",
			"Percentuale disabilita' e' un campo obbligatorio per la categoria soggetto scelta."),
	PROLAVE0037("PRO-LAV-E-00037", "Percentuale disabilita' non e' ammessa per la categoria soggetto scelta."),
	PROLAVE0038("PRO-LAV-E-00038", "Non e' possibile inserire un valore percentuale inferiore a 0."),

	PROLAVE0039("PRO-LAV-E-00039", "Categoria assunzione e' un campo obbligatorio."),
	PROLAVE0040("PRO-LAV-E-00040",
			"Per il 'Tipo Assunzione Protetta' selezionato, la durata della missione non deve essere inferiore a 12 mesi."),
	PROLAVE0041("PRO-LAV-E-00041",
			"Per il 'Tipo Assunzione Protetta' selezionato, il valore della 'Categoria Soggetto' deve essere 'Disabili'."),
//	PROLAVE0042	("PRO-LAV-E-00042", "E\\' gia' stato inserito un lavoratore con codice fiscale   + lav.getCodiceFiscale() +  . "),
//	PROLAVE0043	("PRO-LAV-E-00043", ""E' gia' stato inserito un lavoratore con codice fiscale " + lav.getCodiceFiscale() + " in un'altra provincia.""),
	PROLAVW0044("PRO-LAV-W-00044", "Il lavoratore ha meno di 16 anni. Verificare le date oppure confermare."),
	PROLAVW0045("PRO-LAV-W-00045", "Il lavoratore ha piu' di 75 anni. Verificare le date oppure confermare."),
	PROLAVE0046("PRO-LAV-E-00046",
			"Impossibile recuperare il comune di nascita. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROLAVE0047("PRO-LAV-E-00047",
			"Impossibile recuperare lo stato estero di nascita. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROLAVE0048("PRO-LAV-E-00048",
			"Impossibile recuperare la qualifica Istat. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROLAVE0049("PRO-LAV-E-00049",
			"Per il ''Tipo Assunzione Protetta'' selezionato, il valore della ''Categoria Soggetto'' deve essere ''Disabili''."),

	PROCATE0001("PRO-CAT-E-00001", "Numero di lavoratori e' obbligatorio."),
	PROCATE0002("PRO-CAT-E-00002", "Tipo categoria e' obbligatoro."),
	PROCATE0003("PRO-CAT-E-00003", "La categoria che si sta per inserire esiste gia' in elenco."),
	//

	PROPTME0001("PRO-PTM-E-00001", "Tipologia lavoratore e' un campo obbligatorio."),
	PROPTME0002("PRO-PTM-E-00002", "Orario settimanale contrattuale e' un campo obbligatorio."),
	PROPTME0003("PRO-PTM-E-00003", "Orario settimanale contrattuale valore non ammesso."),
	PROPTME0004("PRO-PTM-E-00004", "Orario settimanale svolto e' un campo obbligatorio."),
	PROPTME0005("PRO-PTM-E-00005", "Orario settimanale svolto valore non ammesso."),
	PROPTME0006("PRO-PTM-E-00006", "N. lavoratori e' un campo obbligatorio."),
	PROPTME0007("PRO-PTM-E-00007", "N. lavoratori deve essere maggiore di zero."),
	PROPTME0008("PRO-PTM-E-00008", "Orario settimanale part-time e' maggiore o uguale all' orario contrattuale."),
	PROPTME0009("PRO-PTM-E-00009", "Per la tipologia contrattuale esiste gia' l'orario contrattuale e part time."),

	PROINTE0002("PRO-INT-E-00002", "Orario settimanale contrattuale e' un campo obbligatorio."),
	PROINTE0003("PRO-INT-E-00003", "Orario settimanale contrattuale valore non ammesso."),
	PROINTE0004("PRO-INT-E-00004", "Orario settimanale svolto e' un campo obbligatorio."),
	PROINTE0005("PRO-INT-E-00005", "Orario settimanale svolto valore non ammesso."),
	PROINTE0006("PRO-INT-E-00006", "N. lavoratori e' un campo obbligatorio."),
	PROINTE0007("PRO-INT-E-00007", "N. lavoratori deve essere maggiore di zero."),
	PROINTE0008("PRO-INT-E-00008", "Orario settimanale e' maggiore o uguale all' orario contrattuale."),
	PROINTE0009("PRO-INT-E-00009",
			"Per la tipologia contrattuale esiste gia' l'orario contrattuale e l'orario svolto."),

	PROPDIE0001("PRO-PDI-E-00001", "Numero posti e' un campo obbligatorio."),
	PROPDIE0002("PRO-PDI-E-00002", "Numero posti deve essere maggiore di zero."),
	PROPDIE0003("PRO-PDI-E-00003", "Categoria assunzione e' un campo obbligatorio."),
	PROPDIE0004("PRO-PDI-E-00004", "Categoria soggetto e' un campo obbligatorio."),
	PROPDIE0005("PRO-PDI-E-00005", "Capacita' di richieste/controindicazioni e' un campo obbligatorio."),
	PROPDIE0006("PRO-PDI-E-00006", "Mansione/descrizione compiti e' un campo obbligatorio."),
	PROPDIE0007("PRO-PDI-E-00007", "Presenza di barriere architettoniche e' un campo obbligatorio."),
	PROPDIE0008("PRO-PDI-E-00008", "Turni notturni e' un campo obbligatorio."),
	PROPDIE0009("PRO-PDI-E-00009", "Raggiungibilita' mezzi pubblici e' un campo obbligatorio."),
	PROPDIE0010("PRO-PDI-E-00010", "Qualifica professionale istat e' un campo obbligatorio."),
	PROPDIE0011("PRO-PDI-E-00011", "Nessuna qualifica ISTAT soddisfa codice e/o descrizione impostati."),
	PROPDIE0012("PRO-PDI-E-00012", "Trovate piu' qualifiche ISTAT che soddisfano codice e/o descrizione impostati"),
	PROPDIE0013("PRO-PDI-E-00013", "Qualifica Istat scaduta o non trovata."),
	PROPDIE0014("PRO-PDI-E-00014", "Valorizzare il comune assunzione."),
	PROPDIE0015("PRO-PDI-E-00015", "Valorizzare solo uno tra comune e stato estero di assunzione."),
	PROPDIE0016("PRO-PDI-E-00016", "Compilare codice e descrizione del comune o dello stato estero di assunzione."),
	PROPDIE0018("PRO-PDI-E-00018", "Comune e' un campo obbligatorio."),
	PROPDIE0019("PRO-PDI-E-00019", "Nessun comune di assunzione soddisfa codice e/o descrizione impostati."),
	PROPDIE0020("PRO-PDI-E-00020", "Trovati piu' comuni di assunzione che soddisfano codice e/o descrizione impostati"),
	PROPDIE0021("PRO-PDI-E-00021", "Comune di asssunzione scaduto o non trovato."),
	PROPDIE0022("PRO-PDI-E-00022", "Nessuno stato estero di assunzione soddisfa codice e/o descrizione impostati."),
	PROPDIE0023("PRO-PDI-E-00023",
			"Trovati piu' stati esteri di assunzione che soddisfano codice e/o descrizione impostati"),
	PROPDIE0024("PRO-PDI-E-00024", "Stato estero di asssunzione scaduto o non trovato."),
	PROPDIE0025("PRO-PDI-E-00025",
			"Impossibile recuperare la qualifica Istat. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROPDIE0026("PRO-PDI-E-00026",
			"Impossibile recuperare il comune. Effettuare la compilazione del campo usando il tasto 'Cerca'."),
	PROPDIE0027("PRO-PDI-E-00027",
			"Impossibile recuperare lo stato estero. Effettuare la compilazione del campo usando il tasto 'Cerca'."),

	PROPCTE0001("PRO-PCT-E-00001", "Categoria compensazione e' un campo obbligatorio."),
	PROPCTE0002("PRO-PCT-E-00002", "Numero lavoratori e' un campo obbligatorio e deve essere maggiore di zero."),
	PROPCTE0003("PRO-PCT-E-00003", "Categoria soggetto e' un campo obbligatorio."),
	PROPCTE0004("PRO-PCT-E-00004", "Provincia e' un campo obbligatorio."),
	PROPCTE0005("PRO-PCT-E-00005", "Provincia per cui si compensa e' un campo obbligatorio."),
	PROPCTE0006("PRO-PCT-E-00006", "Il Codice fiscale azienda appartenente al gruppo non puo' essere valorizzato."),
	PROPCTE0007("PRO-PCT-E-00007", "Formato codice fiscale errato."),
	PROPCTE0008("PRO-PCT-E-00008", "La provincia di compensazione deve essere diversa da quella per cui si compensa."),
	PROPCTE0009("PRO-PCT-E-00009",
			"La provincia di compensazione deve essere tra quelle di cui si e' compilato il prospetto provinciale."),
	PROPCTE0010("PRO-PCT-E-00010",
			"Provincia di compensazione deve essere nella stessa regione di quella per cui si compensa."),
	PROPCTE0011("PRO-PCT-E-00011",
			"La categoria compensazione Eccedenza per disabili non puo' essere impostata perche' sulla provincia per cui si compensa esistono posizioni esonerate."),
	PROPCTE0012("PRO-PCT-E-00012",
			"Per la provincia per cui si compensa sono state inserite categorie di compensazione diverse per lo stesso tipo soggetto."),
	PROPCTE0013("PRO-PCT-E-00013",
			"Per la provincia per cui si compensa sono state inserite categorie di compensazione uguali per la stessa provincia e per lo stesso tipo soggetto."),
	PROPCTE0014("PRO-PCT-E-00014",
			"Non e' possibile avere compensazioni con codice fiscale azienda del gruppo per categorie di compensazioni diverse per lo stesso tipo soggetto."),
//	PROPCTE0015	("PRO-PCT-E-00015", "Le eccedenze relative alla categoria soggetto  +\r\n" + 
//			"					soggetto +   non sono compensate dalle riduzioni."),

	PRORIEE0001("PRO-RIE-E-00001", "Data prima assunzione non valida."),
	PRORIEE0002("PRO-RIE-E-00002", "Data prima assunzione superiore alla data riferimento."),
	PRORIEE0003("PRO-RIE-E-00003", "Data seconda assunzione non valida."),
	PRORIEE0004("PRO-RIE-E-00004", "Inserire il campo data prima assunzione."),
	PRORIEE0005("PRO-RIE-E-00005", "Data prima assunzione superiore alla data seconda assunzione."),
	PRORIEE0006("PRO-RIE-E-00006", "Data seconda assunzione superiore alla data riferimento."),
	PRORIEE0007("PRO-RIE-E-00007", "Nessuna assunzione aggiuntiva e' un dato obbligatorio."),
	PRORIEE0008("PRO-RIE-E-00008", "Data prima assunzione e' un dato obbligatorio."),
	PRORIEE0009("PRO-RIE-E-00009", "Data prima assunzione non compatibile con nessuna assunzione aggiuntiva."),
	PRORIEE0010("PRO-RIE-E-00010", "Data seconda assunzione non compatibile con nessuna assunzione aggiuntiva."),
	PRORIEE0011("PRO-RIE-E-00011", "Data prima assunzione non compatibile con la categoria azienda selezionata."),
	PRORIEE0012("PRO-RIE-E-00012", "Il dato nessuna assunzione aggiuntiva non puo' assumere il valore 'N'."),
	PRORIEE0013("PRO-RIE-E-00013", "Per i campo note la massima lunghezza ammessa e' 2000 caratteri."),
	PRORIEE0014("PRO-RIE-E-00014", "Codice fiscale del soggetto e' errato."),
	PRORIEE0015("PRO-RIE-E-00015", "Email del soggetto e' un campo obbligatorio."),
	PRORIEE0016("PRO-RIE-E-00016", "Email del soggetto non valida."),
	PRORIEE0017("PRO-RIE-E-00017", "Data timbro postale e' un campo obbligatorio."),
	PRORIEE0018("PRO-RIE-E-00018", "Email notifica invio comunicazione e' un campo obbligatorio."),
	PRORIEE0019("PRO-RIE-E-00019", "Email notifica invio comunicazione non e' valida."),
	PRORIEE0020("PRO-RIE-E-00020",
			"La tipologia del dichiarante deve essere di tipo datore di lavoro privato appartenente a gruppo imprese perche' e' stato impostato il codice fiscale dell'azienda appartenente al gruppo sulle compensazioni territoriali."),
	PRORIEE0021("PRO-RIE-E-00021",
			"La tipologia del dichiarante deve essere diversa da datore di lavoro pubblico perche' sulle compensazioni territoriali e' stata impostata una provincia di compensazione fuori dalla regione della provincia per cui si compensa."),
	PRORIEE0022("PRO-RIE-E-00022",
			"Per la stessa provincia non possono esistere contemporaneamente delle compensazione per disabili di categoria Eccedenza e delle posizioni esonerate."),
	PRORIEE0023("PRO-RIE-E-00023",
			"La somma delle scoperture provinciali e' diversa dalla scopertura nazionale. Eventualmente inserire le compensazioni territoriali e proseguire."),
	PRORIEE0024("PRO-RIE-E-00024",
			"La somma delle scoperture provinciali e' diversa dalla scopertura nazionale. E' obbligatorio inserire le compensazioni territoriali."),

	PRORETE0001("PRO-RET-E-00001", "Non e' possibile rettificare il prospetto."),

	PROANNE0001("PRO-ANN-E-00001", "Non e' possibile annullare il prospetto."),
	PRODUPE0001("PRO-DUP-E-00001", "Non e' possibile duplicare il prospetto."),

	// coferma prospetto
	PROCONE0001("PRO-CON-E-0001", "Il ruolo dell'operatore non e' stato inserito."),
	PROSPIW0002("PRO-CON-W-0002", "Non e' stato possibile inviare la comunicazione, sara' inviata appena possibile."),
	PROSPIE0003("PRO-CON-E-0003", "Errore nel sistema di protocollazione."),
	PROSPIE0004("PRO-CON-E-0004", "Errore nella persistenza dei dati."),
	PROSPIW0005("PRO-CON-W-0005", "Errore invio mail."),
	PROSPIE0006("PRO-CON-E-0006", "Il prospetto non e' nello stato BOZZA."),

	// codici errore SILP
	PROSLPE0001("PRO-SLP-E-0001", "Codice fiscale azienda errato"),
	PROSLPE0002("PRO-SLP-E-0002", "Codice fiscale errato"),

	// codici errore SPICOM

	// codici errore IUP

	PROUPDE0001("PRO-UPD-E-0001", "Errore: nessun file selezionato."),
	PROUPDE0002("PRO-UPD-E-0002", "Errore: il formato del file non e' corretto."),
	PROUPDE0003("PRO-UPD-E-0003", "Errore generico nell'import massivo dei lavoratori"),
	PROUPDE0007("PRO-UPD-E-0007", "Intestazione colonne errate, file non riconosciuto."),
	PROUPDE0008("PRO-UPD-E-0008",
			"Il prospetto presenta un numero di lavoratori tale per cui è necessario procedere selezionando le singole provincie."),
	PROUPDE0009("PRO-UPD-E-0009",
			"Il numero di lavoratori che si vuole caricare è eccessivo e supera il numero massimo di lavoratori caricabili per prospetto."),
	PROUPDE0020("PRO-UPD-E-0020", "Flag forma non corretto."),
	PROUPDE0021("PRO-UPD-E-0021", "Flag forma non coerente con la tipologia contrattuale."),
	PROUPDE0022("PRO-UPD-E-0022",
			"La data di fine rapporto non deve essere valorizzata per contratti / tipo assunzione protetta a tempo indeterminato."),;

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
