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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_PDF_PROSPETTO database table.
 * 
 */
@Entity
@Table(name="PRO_D_PDF_PROSPETTO")
@NamedQuery(name="ProDPdfProspetto.findAll", query="SELECT p FROM ProDPdfProspetto p")
public class ProDPdfProspetto implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idProspetto;
	}

	@Override
	public void setId(Long id) {
		idProspetto = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO")
	private long idProspetto;

	@Lob
	@Column(name="PDF_PROSPETTO_DA_FIRMARE")
	private byte[] pdfProspettoDaFirmare;

	@Lob
	@Column(name="PDF_PROSPETTO_FIRMATO")
	private byte[] pdfProspettoFirmato;

	//bi-directional one-to-one association to ProDProspetto
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO")
	private ProDProspetto proDProspetto;

	public ProDPdfProspetto() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public byte[] getPdfProspettoDaFirmare() {
		return this.pdfProspettoDaFirmare;
	}

	public void setPdfProspettoDaFirmare(byte[] pdfProspettoDaFirmare) {
		this.pdfProspettoDaFirmare = pdfProspettoDaFirmare;
	}

	public byte[] getPdfProspettoFirmato() {
		return this.pdfProspettoFirmato;
	}

	public void setPdfProspettoFirmato(byte[] pdfProspettoFirmato) {
		this.pdfProspettoFirmato = pdfProspettoFirmato;
	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

}
