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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_IMPORT_ERRORI_SPICOM database table.
 * 
 */
@Entity
@Table(name="PRO_T_IMPORT_ERRORI_SPICOM")
@NamedQuery(name="ProTImportErroriSpicom.findAll", query="SELECT p FROM ProTImportErroriSpicom p")
public class ProTImportErroriSpicom implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return codErrore;
	}

	@Override
	public void setId(Long id) {
		codErrore = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_ERRORE")
	private long codErrore;

	@Column(name="DESC_ERRORE")
	private String descErrore;

	//bi-directional many-to-one association to ProDImportErrori
	@OneToMany(mappedBy="proTImportErroriSpicom")
	private List<ProDImportErrori> proDImportErroris;

	public ProTImportErroriSpicom() {
	}

	public long getCodErrore() {
		return this.codErrore;
	}

	public void setCodErrore(long codErrore) {
		this.codErrore = codErrore;
	}

	public String getDescErrore() {
		return this.descErrore;
	}

	public void setDescErrore(String descErrore) {
		this.descErrore = descErrore;
	}

	public List<ProDImportErrori> getProDImportErroris() {
		return this.proDImportErroris;
	}

	public void setProDImportErroris(List<ProDImportErrori> proDImportErroris) {
		this.proDImportErroris = proDImportErroris;
	}

	public ProDImportErrori addProDImportErrori(ProDImportErrori proDImportErrori) {
		getProDImportErroris().add(proDImportErrori);
		proDImportErrori.setProTImportErroriSpicom(this);

		return proDImportErrori;
	}

	public ProDImportErrori removeProDImportErrori(ProDImportErrori proDImportErrori) {
		getProDImportErroris().remove(proDImportErrori);
		proDImportErrori.setProTImportErroriSpicom(null);

		return proDImportErrori;
	}

}
