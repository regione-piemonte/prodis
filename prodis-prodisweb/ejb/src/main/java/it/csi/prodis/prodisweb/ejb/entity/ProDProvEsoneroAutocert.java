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
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_ESONERO_AUTOCERT database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_ESONERO_AUTOCERT")
@NamedQuery(name="ProDProvEsoneroAutocert.findAll", query="SELECT p FROM ProDProvEsoneroAutocert p")
public class ProDProvEsoneroAutocert extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idProspettoProv;
	}

	@Override
	public void setId(Long id) {
		idProspettoProv = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO_PROV")
	private long idProspettoProv;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_AUTOCERT")
	private Date dataAutocert;

	@Column(name="N_LAV_60X1000")
	private BigDecimal nLav60x1000;

	@Column(name="N_LAV_ESONERO_AUTOCERT")
	private BigDecimal nLavEsoneroAutocert;

	@Column(name="PERCENTUALE_ES_AUTOCERT")
	private BigDecimal percentualeEsAutocert;

	public ProDProvEsoneroAutocert() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public Date getDataAutocert() {
		return this.dataAutocert;
	}

	public void setDataAutocert(Date dataAutocert) {
		this.dataAutocert = dataAutocert;
	}

	public BigDecimal getnLav60x1000() {
		return this.nLav60x1000;
	}

	public void setnLav60x1000(BigDecimal nLav60x1000) {
		this.nLav60x1000 = nLav60x1000;
	}

	public BigDecimal getnLavEsoneroAutocert() {
		return this.nLavEsoneroAutocert;
	}

	public void setnLavEsoneroAutocert(BigDecimal nLavEsoneroAutocert) {
		this.nLavEsoneroAutocert = nLavEsoneroAutocert;
	}

	public BigDecimal getPercentualeEsAutocert() {
		return this.percentualeEsAutocert;
	}

	public void setPercentualeEsAutocert(BigDecimal percentualeEsAutocert) {
		this.percentualeEsAutocert = percentualeEsAutocert;
	}

}
