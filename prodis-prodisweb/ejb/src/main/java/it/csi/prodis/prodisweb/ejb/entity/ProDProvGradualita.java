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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_GRADUALITA database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_GRADUALITA")
@NamedQuery(name="ProDProvGradualita.findAll", query="SELECT p FROM ProDProvGradualita p")
public class ProDProvGradualita extends BaseAuditedEntity<Long> implements Serializable {

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

	@Column(name="N_ASSUNZIONI_EFF_DOPO_TRASF")
	private BigDecimal nAssunzioniEffDopoTrasf;

	public ProDProvGradualita() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public BigDecimal getnAssunzioniEffDopoTrasf() {
		return this.nAssunzioniEffDopoTrasf;
	}

	public void setnAssunzioniEffDopoTrasf(BigDecimal nAssunzioniEffDopoTrasf) {
		this.nAssunzioniEffDopoTrasf = nAssunzioniEffDopoTrasf;
	}

}
