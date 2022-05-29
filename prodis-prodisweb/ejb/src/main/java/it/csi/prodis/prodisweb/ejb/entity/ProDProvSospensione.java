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

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;

/**
 * The persistent class for the PRO_D_PROV_SOSPENSIONE database table.
 * 
 */
@Entity
@Table(name = "PRO_D_PROV_SOSPENSIONE")
@NamedQuery(name = "ProDProvSospensione.findAll", query = "SELECT p FROM ProDProvSospensione p")
public class ProDProvSospensione extends BaseAuditedEntity<Long> implements Serializable {

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
	@Column(name = "ID_PROSPETTO_PROV")
	private long idProspettoProv;

	@Temporal(TemporalType.DATE)
	@Column(name = "D_FINE_SOSPENSIONE_Q2")
	private Date dataFineSospensioneQ2;

	@Column(name = "N_LAVORATORI")
	private BigDecimal nLavoratori;

	// bi-directional many-to-one association to ProTCausaSospensione
	@ManyToOne
	@JoinColumn(name = "ID_T_CAUSA_SOSPENSIONE")
	private ProTCausaSospensione proTCausaSospensione;

	// bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name = "ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public ProDProvSospensione() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public BigDecimal getnLavoratori() {
		return this.nLavoratori;
	}

	public void setnLavoratori(BigDecimal nLavoratori) {
		this.nLavoratori = nLavoratori;
	}

	public ProTCausaSospensione getProTCausaSospensione() {
		return this.proTCausaSospensione;
	}

	public void setProTCausaSospensione(ProTCausaSospensione proTCausaSospensione) {
		this.proTCausaSospensione = proTCausaSospensione;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

	public Date getDataFineSospensioneQ2() {
		return dataFineSospensioneQ2;
	}

	public void setDataFineSospensioneQ2(Date dataFineSospensioneQ2) {
		this.dataFineSospensioneQ2 = dataFineSospensioneQ2;
	}

}
