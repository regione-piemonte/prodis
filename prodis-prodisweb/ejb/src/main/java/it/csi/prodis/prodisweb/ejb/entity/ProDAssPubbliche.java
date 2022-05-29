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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;

/**
 * The persistent class for the PRO_D_ASS_PUBBLICHE database table.
 * 
 */
@Entity
@Table(name = "PRO_D_ASS_PUBBLICHE")
@NamedQuery(name = "ProDAssPubbliche.findAll", query = "SELECT p FROM ProDAssPubbliche p")
public class ProDAssPubbliche extends BaseAuditedEntity<ProDAssPubblichePK> implements Serializable  {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProDAssPubblichePK id;

	@Column(name = "COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name = "COD_USER_INSERIM")
	private String codUserInserim;

	@Temporal(TemporalType.DATE)
	@Column(name = "D_AGGIORN")
	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name = "D_INSERIM")
	private Date dInserim;

	@Column(name = "DS_NOTE")
	private String dsNote;

	@Column(name = "SALDO_DISABILI")
	private BigDecimal saldoDisabili;

	@Column(name = "SALDO_EX_ART_18")
	private BigDecimal saldoExArt18;

	// bi-directional many-to-one association to ProDProspetto
//	ID_PROSPETTO è anche nella chiave
//	Caused by: org.hibernate.MappingException: Repeated column in mapping for entity: it.csi.prodis.prodisweb.ejb.entity.ProDAssPubbliche column: ID_PROSPETTO (should be mapped with insert="false" update="false")
	@ManyToOne
	@JoinColumn(name="ID_PROSPETTO", insertable=false, updatable=false)
	private ProDProspetto proDProspetto;

	// bi-directional many-to-one association to ProTRegione
	@ManyToOne
	@JoinColumn(name="ID_T_REGIONE", insertable=false, updatable=false)
	private ProTRegione proTRegione;

	public ProDAssPubbliche() {
	}

	@Override
	public ProDAssPubblichePK getId() {
		return this.id;
	}

	@Override
	public void setId(ProDAssPubblichePK id) {
		this.id = id;
	}

	public String getCodUserAggiorn() {
		return this.codUserAggiorn;
	}

	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	public String getCodUserInserim() {
		return this.codUserInserim;
	}

	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	public Date getDAggiorn() {
		return this.dAggiorn;
	}

	public void setDAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	public Date getDInserim() {
		return this.dInserim;
	}

	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	public String getDsNote() {
		return this.dsNote;
	}

	public void setDsNote(String dsNote) {
		this.dsNote = dsNote;
	}

	public BigDecimal getSaldoDisabili() {
		return this.saldoDisabili;
	}

	public void setSaldoDisabili(BigDecimal saldoDisabili) {
		this.saldoDisabili = saldoDisabili;
	}

	public BigDecimal getSaldoExArt18() {
		return this.saldoExArt18;
	}

	public void setSaldoExArt18(BigDecimal saldoExArt18) {
		this.saldoExArt18 = saldoExArt18;
	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

	public ProTRegione getProTRegione() {
		return this.proTRegione;
	}

	public void setProTRegione(ProTRegione proTRegione) {
		this.proTRegione = proTRegione;
	}

}
