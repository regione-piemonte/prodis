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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_ESONERO database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_ESONERO")
@NamedQuery(name="ProDProvEsonero.findAll", query="SELECT p FROM ProDProvEsonero p")
public class ProDProvEsonero extends BaseAuditedEntity<Long> implements Serializable {

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
	@Column(name="DATA_ATTO")
	private Date dataAtto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATTO_FINO_AL")
	private Date dataAttoFinoAl;

	@Column(name="ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name="N_LAVORATORI_ESONERO")
	private BigDecimal nLavoratoriEsonero;

	private BigDecimal percentuale;

	//bi-directional one-to-one association to ProDDatiProvinciali
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO_PROV")
	private ProDDatiProvinciali proDDatiProvinciali;

	//bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name="ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public ProDProvEsonero() {
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

	public Date getDataAtto() {
		return this.dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public Date getDataAttoFinoAl() {
		return this.dataAttoFinoAl;
	}

	public void setDataAttoFinoAl(Date dataAttoFinoAl) {
		this.dataAttoFinoAl = dataAttoFinoAl;
	}

	public String getEstremiAtto() {
		return this.estremiAtto;
	}

	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	public BigDecimal getnLavoratoriEsonero() {
		return this.nLavoratoriEsonero;
	}

	public void setnLavoratoriEsonero(BigDecimal nLavoratoriEsonero) {
		this.nLavoratoriEsonero = nLavoratoriEsonero;
	}

	public BigDecimal getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public ProDDatiProvinciali getProDDatiProvinciali() {
		return this.proDDatiProvinciali;
	}

	public void setProDDatiProvinciali(ProDDatiProvinciali proDDatiProvinciali) {
		this.proDDatiProvinciali = proDDatiProvinciali;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

}
