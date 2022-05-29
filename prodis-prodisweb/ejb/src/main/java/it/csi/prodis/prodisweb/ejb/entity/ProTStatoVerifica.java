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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_STATO_VERIFICA database table.
 * 
 */
@Entity
@Table(name="PRO_T_STATO_VERIFICA")
@NamedQuery(name="ProTStatoVerifica.findAll", query="SELECT p FROM ProTStatoVerifica p")
public class ProTStatoVerifica implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTStatoVerifica;
	}

	@Override
	public void setId(Long id) {
		idTStatoVerifica = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_STATO_VERIFICA")
	private long idTStatoVerifica;

	private String descrizione;

	//bi-directional many-to-one association to ProDProspetto
	@OneToMany(mappedBy="proTStatoVerifica")
	private List<ProDProspetto> proDProspettos;

	public ProTStatoVerifica() {
	}

	public long getIdTStatoVerifica() {
		return this.idTStatoVerifica;
	}

	public void setIdTStatoVerifica(long idTStatoVerifica) {
		this.idTStatoVerifica = idTStatoVerifica;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<ProDProspetto> getProDProspettos() {
		return this.proDProspettos;
	}

	public void setProDProspettos(List<ProDProspetto> proDProspettos) {
		this.proDProspettos = proDProspettos;
	}

	public ProDProspetto addProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().add(proDProspetto);
		proDProspetto.setProTStatoVerifica(this);

		return proDProspetto;
	}

	public ProDProspetto removeProDProspetto(ProDProspetto proDProspetto) {
		getProDProspettos().remove(proDProspetto);
		proDProspetto.setProTStatoVerifica(null);

		return proDProspetto;
	}

}
