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
 * The persistent class for the PRO_T_CCNL database table.
 * 
 */
@Entity
@Table(name = "PRO_T_CCNL")
@NamedQuery(name = "ProTCcnl.findAll", query = "SELECT p FROM ProTCcnl p")
public class ProTCcnl implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTCcnl;
	}

	@Override
	public void setId(Long id) {
		idTCcnl = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_T_CCNL")
	private Long idTCcnl;

	@Column(name = "COD_CCNL_MIN")
	private String codCcnlMin;

	@Column(name = "DS_CCNL")
	private String dsCcnl;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_TMST")
	private Date dtTmst;

	@Column(name = "ID_NEW_CCNL")
	private BigDecimal idNewCcnl;

	private String settore;

	// bi-directional many-to-one association to ProDDatiAzienda
//	@OneToMany(mappedBy = "proTCcnl")
//	private List<ProDDatiAzienda> proDDatiAziendas;

	// bi-directional many-to-one association to ProTCcnl
	@ManyToOne
	@JoinColumn(name = "ID_T_CCNL_PREV")
	private ProTCcnl proTCcnl;

	// bi-directional many-to-one association to ProTCcnl
//	@OneToMany(mappedBy = "proTCcnl")
//	private List<ProTCcnl> proTCcnls;

	public ProTCcnl() {
	}

	public Long getIdTCcnl() {
		return this.idTCcnl;
	}

	public void setIdTCcnl(Long idTCcnl) {
		this.idTCcnl = idTCcnl;
	}

	public String getCodCcnlMin() {
		return this.codCcnlMin;
	}

	public void setCodCcnlMin(String codCcnlMin) {
		this.codCcnlMin = codCcnlMin;
	}

	public String getDsCcnl() {
		return this.dsCcnl;
	}

	public void setDsCcnl(String dsCcnl) {
		this.dsCcnl = dsCcnl;
	}

	public Date getDtFine() {
		return this.dtFine;
	}

	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	public Date getDtInizio() {
		return this.dtInizio;
	}

	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	public Date getDtTmst() {
		return this.dtTmst;
	}

	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	public BigDecimal getIdNewCcnl() {
		return this.idNewCcnl;
	}

	public void setIdNewCcnl(BigDecimal idNewCcnl) {
		this.idNewCcnl = idNewCcnl;
	}

	public String getSettore() {
		return this.settore;
	}

	public void setSettore(String settore) {
		this.settore = settore;
	}

//	public List<ProDDatiAzienda> getProDDatiAziendas() {
//		return this.proDDatiAziendas;
//	}
//
//	public void setProDDatiAziendas(List<ProDDatiAzienda> proDDatiAziendas) {
//		this.proDDatiAziendas = proDDatiAziendas;
//	}

//	public ProDDatiAzienda addProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().add(proDDatiAzienda);
//		proDDatiAzienda.setProTCcnl(this);
//
//		return proDDatiAzienda;
//	}
//
//	public ProDDatiAzienda removeProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().remove(proDDatiAzienda);
//		proDDatiAzienda.setProTCcnl(null);
//
//		return proDDatiAzienda;
//	}

	public ProTCcnl getProTCcnl() {
		return this.proTCcnl;
	}

	public void setProTCcnl(ProTCcnl proTCcnl) {
		this.proTCcnl = proTCcnl;
	}

//	public List<ProTCcnl> getProTCcnls() {
//		return this.proTCcnls;
//	}
//
//	public void setProTCcnls(List<ProTCcnl> proTCcnls) {
//		this.proTCcnls = proTCcnls;
//	}

//	public ProTCcnl addProTCcnl(ProTCcnl proTCcnl) {
//		getProTCcnls().add(proTCcnl);
//		proTCcnl.setProTCcnl(this);
//
//		return proTCcnl;
//	}
//
//	public ProTCcnl removeProTCcnl(ProTCcnl proTCcnl) {
//		getProTCcnls().remove(proTCcnl);
//		proTCcnl.setProTCcnl(null);
//
//		return proTCcnl;
//	}

}
