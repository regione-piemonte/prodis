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
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.io.Serializable;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;

/**
 * The Class PdfProspetto.
 */
public class PdfProspetto extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private byte[] pdfProspettoDaFirmare;
	private byte[] pdfProspettoFirmato;
	private Prospetto prospetto;

	/**
	 * @return the pdfProspettoDaFirmare
	 */
	public byte[] getPdfProspettoDaFirmare() {
		return pdfProspettoDaFirmare;
	}
	
	/**
	 * @param pdfProspettoDaFirmare the pdfProspettoDaFirmare to set
	 */
	public void setPdfProspettoDaFirmare(byte[] pdfProspettoDaFirmare) {
		this.pdfProspettoDaFirmare = pdfProspettoDaFirmare;
	}

	/**
	 * @return the pdfProspettoFirmato
	 */
	public byte[] getPdfProspettoFirmato() {
		return pdfProspettoFirmato;
	}
	
	/**
	 * @param pdfProspettoFirmato the pdfProspettoFirmato to set
	 */
	public void setPdfProspettoFirmato(byte[] pdfProspettoFirmato) {
		this.pdfProspettoFirmato = pdfProspettoFirmato;
	}

	/**
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}
	
	/**
	 * @param prospetto the prospetto to set
	 */
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

}
