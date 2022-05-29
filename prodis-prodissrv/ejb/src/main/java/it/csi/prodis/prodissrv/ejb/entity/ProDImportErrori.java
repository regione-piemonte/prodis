/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_IMPORT_ERRORI database table.
 * 
 */
@Entity
@Table(name="PRO_D_IMPORT_ERRORI")
@NamedQuery(name="ProDImportErrori.findAll", query="SELECT p FROM ProDImportErrori p")
@SequenceGenerator(name = "ERR_SEQUENCE", sequenceName = "SEQ_D_IMPORT_ERRORI", initialValue = 1, allocationSize = 1)
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
	@Column(name="ID_ERRORE" , unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ERR_SEQUENCE")
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
