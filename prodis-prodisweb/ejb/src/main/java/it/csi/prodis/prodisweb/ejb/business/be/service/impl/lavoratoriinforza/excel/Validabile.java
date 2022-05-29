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

public class Validabile {
	
	Validabile (boolean validabile, String regularExpression, String errore) {
		setValidabile(validabile);
		setRegularExpression(regularExpression);
		setErrore(errore);
	}
	
	private boolean validabile;
	public boolean isValidabile() {return validabile;}
	public void setValidabile(boolean validabile) {this.validabile = validabile;}
	
	private String regularExpression;
	public String getRegularExpression() {return regularExpression;}
	public void setRegularExpression(String regularExpression) {this.regularExpression = regularExpression;}
	
	private String errore;
	public String getErrore() {return errore;}
	public void setErrore(String errore) {this.errore = errore;}
	
}
