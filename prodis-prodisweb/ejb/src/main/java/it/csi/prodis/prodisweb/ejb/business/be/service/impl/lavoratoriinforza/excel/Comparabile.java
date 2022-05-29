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

public class Comparabile {
	
	Comparabile (boolean comparabile, String errore) {
		setComparabile(comparabile);
		setErrore(errore);
	}
	
	private boolean comparabile;
	public boolean isComparabile() {return comparabile;}
	public void setComparabile(boolean comparabile) {this.comparabile = comparabile;}

	private String errore;
	public String getErrore() {return errore;}
	public void setErrore(String errore) {this.errore = errore;}
	
}
