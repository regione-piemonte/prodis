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
 * The persistent class for the PRO_D_PROV_CONVENZIONE database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_CONVENZIONE")
@NamedQuery(name="ProDProvConvenzione.findAll", query="SELECT p FROM ProDProvConvenzione p")
public class ProDProvConvenzione implements Serializable, BaseEntity<Long> {

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

	@Column(name="COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name="COD_USER_INSERIM")
	private String codUserInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="D_AGGIORN")
	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name="D_INSERIM")
	private Date dInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATTO")
	private Date dataAtto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_SCADENZA")
	private Date dataScadenza;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_STIPULA")
	private Date dataStipula;

	@Column(name="ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name="NUM_LAV_PREV_CONV_Q2")
	private BigDecimal numLavPrevConvQ2;

	//bi-directional many-to-one association to ProTAssunzioneProtetta
	@ManyToOne
	@JoinColumn(name="ID_T_ASSUNZIONE_PROTETTA")
	private ProTAssunzioneProtetta proTAssunzioneProtetta;

	//bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name="ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public ProDProvConvenzione() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
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

	public Date getDataAtto() {
		return this.dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public Date getDataScadenza() {
		return this.dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public Date getDataStipula() {
		return this.dataStipula;
	}

	public void setDataStipula(Date dataStipula) {
		this.dataStipula = dataStipula;
	}

	public String getEstremiAtto() {
		return this.estremiAtto;
	}

	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	public BigDecimal getNumLavPrevConvQ2() {
		return this.numLavPrevConvQ2;
	}

	public void setNumLavPrevConvQ2(BigDecimal numLavPrevConvQ2) {
		this.numLavPrevConvQ2 = numLavPrevConvQ2;
	}

	public ProTAssunzioneProtetta getProTAssunzioneProtetta() {
		return this.proTAssunzioneProtetta;
	}

	public void setProTAssunzioneProtetta(ProTAssunzioneProtetta proTAssunzioneProtetta) {
		this.proTAssunzioneProtetta = proTAssunzioneProtetta;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

}
