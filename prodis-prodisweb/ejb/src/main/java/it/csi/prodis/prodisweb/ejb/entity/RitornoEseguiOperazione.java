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
package it.csi.prodis.prodisweb.ejb.entity;

import it.csi.prodis.prodisweb.lib.dto.prospetto.Prospetto;

public class RitornoEseguiOperazione {
	
	private Long esito;
	private String messaggio;
	private Long newId;
	private Boolean successo;
	private Prospetto nuovoProspetto;
	public Long getEsito() {
		return esito;
	}
	public void setEsito(Long esito) {
		this.esito = esito;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	public Boolean getSuccesso() {
		return successo;
	}
	public void setSuccesso(Boolean successo) {
		this.successo = successo;
	}
	public Prospetto getNuovoProspetto() {
		return nuovoProspetto;
	}
	public void setNuovoProspetto(Prospetto nuovoProspetto) {
		this.nuovoProspetto = nuovoProspetto;
	}
	
	

}
