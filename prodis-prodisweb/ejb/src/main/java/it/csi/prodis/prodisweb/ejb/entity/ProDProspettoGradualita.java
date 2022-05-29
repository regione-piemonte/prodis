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
 * The persistent class for the PRO_D_PROSPETTO_GRADUALITA database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROSPETTO_GRADUALITA")
@NamedQuery(name="ProDProspettoGradualita.findAll", query="SELECT p FROM ProDProspettoGradualita p")
public class ProDProspettoGradualita extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idProspetto;
	}

	@Override
	public void setId(Long id) {
		idProspetto = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO")
	// @JoinColumn(name="ID_PROSPETTO")
	private long idProspetto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATTO")
	private Date dataAtto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TRASFORMAZIONE")
	private Date dataTrasformazione;

	@Column(name="ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name="N_ASSUNZIONI_LAV_PRE_TRASF")
	private BigDecimal nAssunzioniLavPreTrasf;

	private BigDecimal percentuale;

	public ProDProspettoGradualita() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public Date getDataAtto() {
		return this.dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public Date getDataTrasformazione() {
		return this.dataTrasformazione;
	}

	public void setDataTrasformazione(Date dataTrasformazione) {
		this.dataTrasformazione = dataTrasformazione;
	}

	public String getEstremiAtto() {
		return this.estremiAtto;
	}

	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	public BigDecimal getnAssunzioniLavPreTrasf() {
		return this.nAssunzioniLavPreTrasf;
	}

	public void setnAssunzioniLavPreTrasf(BigDecimal nAssunzioniLavPreTrasf) {
		this.nAssunzioniLavPreTrasf = nAssunzioniLavPreTrasf;
	}

	public BigDecimal getPercentuale() {
		return this.percentuale;
	}

	public void setPercentuale(BigDecimal percentuale) {
		this.percentuale = percentuale;
	}

}
