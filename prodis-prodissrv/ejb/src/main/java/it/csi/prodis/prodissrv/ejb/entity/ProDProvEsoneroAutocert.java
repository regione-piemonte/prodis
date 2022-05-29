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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


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

//	@Column(name="COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name="COD_USER_INSERIM")
//	private String codUserInserim;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_AGGIORN")
//	private Date dAggiorn;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_INSERIM")
//	private Date dInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_AUTOCERT")
	private Date dataAutocert;

	@Column(name="N_LAV_60X1000")
	private BigDecimal nLav60x1000;

	@Column(name="N_LAV_ESONERO_AUTOCERT")
	private BigDecimal nLavEsoneroAutocert;

	@Column(name="PERCENTUALE_ES_AUTOCERT")
	private BigDecimal percentualeEsAutocert;

	//bi-directional one-to-one association to ProDDatiProvinciali
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO_PROV")
	private ProDDatiProvinciali proDDatiProvinciali;

	public ProDProvEsoneroAutocert() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

//	public String getCodUserAggiorn() {
//		return this.codUserAggiorn;
//	}
//
//	public void setCodUserAggiorn(String codUserAggiorn) {
//		this.codUserAggiorn = codUserAggiorn;
//	}
//
//	public String getCodUserInserim() {
//		return this.codUserInserim;
//	}
//
//	public void setCodUserInserim(String codUserInserim) {
//		this.codUserInserim = codUserInserim;
//	}
//
//	public Date getDAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setDAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}
//
//	public Date getDInserim() {
//		return this.dInserim;
//	}
//
//	public void setDInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

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

	public ProDDatiProvinciali getProDDatiProvinciali() {
		return this.proDDatiProvinciali;
	}

	public void setProDDatiProvinciali(ProDDatiProvinciali proDDatiProvinciali) {
		this.proDDatiProvinciali = proDDatiProvinciali;
	}

}
