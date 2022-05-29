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

public class MassivoLavoratoriFile {
	
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	private byte[] contents;
	public byte[] getContents() {return contents;}
	public void setContents(byte[] contents) {this.contents = contents;}
	
}
