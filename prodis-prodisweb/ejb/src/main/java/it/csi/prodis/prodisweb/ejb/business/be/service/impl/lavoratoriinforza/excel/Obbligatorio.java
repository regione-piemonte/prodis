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

public class Obbligatorio {
	
	Obbligatorio (boolean obbligatorio, String errore) {
		setObbligatorio(obbligatorio);
		setErrore(errore);
	}
	
	private boolean obbligatorio;
	public boolean isObbligatorio() {return obbligatorio;}
	public void setObbligatorio(boolean obbligatorio) {this.obbligatorio = obbligatorio;}
	
	private String errore;
	public String getErrore() {return errore;}
	public void setErrore(String errore) {this.errore = errore;}
	
}
