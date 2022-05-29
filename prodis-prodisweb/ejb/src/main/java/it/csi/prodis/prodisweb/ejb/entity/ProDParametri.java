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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the PRO_D_PARAMETRI database table.
 * 
 */
@Entity
@Table(name = "PRO_D_PARAMETRI")
@NamedQuery(name = "ProDParametri.findAll", query = "SELECT p FROM ProDParametri p")
public class ProDParametri implements Serializable, BaseEntity<String> {

	@Override
	public String getId() {
		return dsNome;
	}

	@Override
	public void setId(String id) {
		dsNome = id;
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "DS_DESCRIZIONE")
	private String dsDescrizione;

	@Id
	@Column(name = "DS_NOME")
	private String dsNome;

	@Column(name = "DS_VALORE")
	private String dsValore;

	public ProDParametri() {
	}

	public String getDsDescrizione() {
		return this.dsDescrizione;
	}

	public void setDsDescrizione(String dsDescrizione) {
		this.dsDescrizione = dsDescrizione;
	}

	public String getDsNome() {
		return this.dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsValore() {
		return this.dsValore;
	}

	public void setDsValore(String dsValore) {
		this.dsValore = dsValore;
	}

}
