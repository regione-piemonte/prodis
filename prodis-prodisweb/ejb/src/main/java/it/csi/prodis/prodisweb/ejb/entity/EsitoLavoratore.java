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

public class EsitoLavoratore {
	
	private String lavoratore;
	private String esito;
	
	public EsitoLavoratore() {
		
	}

	public EsitoLavoratore(String lavoratore, String esito) {
		super();
		this.lavoratore = lavoratore;
		this.esito = esito;
	}
	public String getLavoratore() {
		return lavoratore;
	}
	public void setLavoratore(String lavoratore) {
		this.lavoratore = lavoratore;
	}
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	

}
