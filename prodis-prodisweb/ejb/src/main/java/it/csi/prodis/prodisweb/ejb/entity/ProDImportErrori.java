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
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_IMPORT_ERRORI database table.
 * 
 */
@Entity
@Table(name="PRO_D_IMPORT_ERRORI")
@NamedQuery(name="ProDImportErrori.findAll", query="SELECT p FROM ProDImportErrori p")
public class ProDImportErrori implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idErrore;
	}

	@Override
	public void setId(Long id) {
		idErrore = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ERRORE")
	private long idErrore;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ELAB")
	private Date dataElab;

	@Column(name="DATO_INPUT")
	private String datoInput;

	@Column(name="DS_ERRORE_NON_GESTITO")
	private String dsErroreNonGestito;

	@Column(name="ID_SPI_TRASMISSIONE")
	private BigDecimal idSpiTrasmissione;

	@Column(name="TABELLA_DESTINAZIONE")
	private String tabellaDestinazione;

	//bi-directional many-to-one association to ProTImportErroriSpicom
	@ManyToOne
	@JoinColumn(name="COD_ERRORE")
	private ProTImportErroriSpicom proTImportErroriSpicom;

	public ProDImportErrori() {
	}

	public long getIdErrore() {
		return this.idErrore;
	}

	public void setIdErrore(long idErrore) {
		this.idErrore = idErrore;
	}

	public Date getDataElab() {
		return this.dataElab;
	}

	public void setDataElab(Date dataElab) {
		this.dataElab = dataElab;
	}

	public String getDatoInput() {
		return this.datoInput;
	}

	public void setDatoInput(String datoInput) {
		this.datoInput = datoInput;
	}

	public String getDsErroreNonGestito() {
		return this.dsErroreNonGestito;
	}

	public void setDsErroreNonGestito(String dsErroreNonGestito) {
		this.dsErroreNonGestito = dsErroreNonGestito;
	}

	public BigDecimal getIdSpiTrasmissione() {
		return this.idSpiTrasmissione;
	}

	public void setIdSpiTrasmissione(BigDecimal idSpiTrasmissione) {
		this.idSpiTrasmissione = idSpiTrasmissione;
	}

	public String getTabellaDestinazione() {
		return this.tabellaDestinazione;
	}

	public void setTabellaDestinazione(String tabellaDestinazione) {
		this.tabellaDestinazione = tabellaDestinazione;
	}

	public ProTImportErroriSpicom getProTImportErroriSpicom() {
		return this.proTImportErroriSpicom;
	}

	public void setProTImportErroriSpicom(ProTImportErroriSpicom proTImportErroriSpicom) {
		this.proTImportErroriSpicom = proTImportErroriSpicom;
	}

}
