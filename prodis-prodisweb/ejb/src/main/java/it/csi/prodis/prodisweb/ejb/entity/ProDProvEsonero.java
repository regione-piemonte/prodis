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
 * The persistent class for the PRO_D_PROV_ESONERO database table.
 * 
 */
@Entity
@Table(name = "PRO_D_PROV_ESONERO")
@NamedQuery(name = "ProDProvEsonero.findAll", query = "SELECT p FROM ProDProvEsonero p")
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
	@Column(name = "ID_PROSPETTO_PROV")
	private long idProspettoProv;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ATTO")
	private Date dataAtto;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ATTO_FINO_AL")
	private Date dataAttoFinoAl;

	@Column(name = "ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name = "N_LAVORATORI_ESONERO")
	private BigDecimal numLavoratoriEsonero;

	private BigDecimal percentuale;

	// bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name = "ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public ProDProvEsonero() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

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

	public BigDecimal getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

	public BigDecimal getNumLavoratoriEsonero() {
		return numLavoratoriEsonero;
	}

	public void setNumLavoratoriEsonero(BigDecimal numLavoratoriEsonero) {
		this.numLavoratoriEsonero = numLavoratoriEsonero;
	}

}
