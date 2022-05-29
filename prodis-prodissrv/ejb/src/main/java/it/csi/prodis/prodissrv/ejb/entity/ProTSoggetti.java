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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_SOGGETTI database table.
 * 
 */
@Entity
@Table(name="PRO_T_SOGGETTI")
@NamedQuery(name="ProTSoggetti.findAll", query="SELECT p FROM ProTSoggetti p")
public class ProTSoggetti implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTSoggetti;
	}

	@Override
	public void setId(Long id) {
		idTSoggetti = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_SOGGETTI")
	private long idTSoggetti;

	@Column(name="COD_SOGGETTI")
	private String codSoggetti;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DESC_SOGGETTI")
	private String descSoggetti;

	//bi-directional many-to-one association to ProDProspetto
//	@OneToMany(mappedBy="proTSoggetti")
//	private List<ProDProspetto> proDProspettos;

	public ProTSoggetti() {
	}

	public long getIdTSoggetti() {
		return this.idTSoggetti;
	}

	public void setIdTSoggetti(long idTSoggetti) {
		this.idTSoggetti = idTSoggetti;
	}

	public String getCodSoggetti() {
		return this.codSoggetti;
	}

	public void setCodSoggetti(String codSoggetti) {
		this.codSoggetti = codSoggetti;
	}

	public Date getDataFine() {
		return this.dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataTmst() {
		return this.dataTmst;
	}

	public void setDataTmst(Date dataTmst) {
		this.dataTmst = dataTmst;
	}

	public String getDescSoggetti() {
		return this.descSoggetti;
	}

	public void setDescSoggetti(String descSoggetti) {
		this.descSoggetti = descSoggetti;
	}

//	public List<ProDProspetto> getProDProspettos() {
//		return this.proDProspettos;
//	}
//
//	public void setProDProspettos(List<ProDProspetto> proDProspettos) {
//		this.proDProspettos = proDProspettos;
//	}
//
//	public ProDProspetto addProDProspetto(ProDProspetto proDProspetto) {
//		getProDProspettos().add(proDProspetto);
//		proDProspetto.setProTSoggetti(this);
//
//		return proDProspetto;
//	}
//
//	public ProDProspetto removeProDProspetto(ProDProspetto proDProspetto) {
//		getProDProspettos().remove(proDProspetto);
//		proDProspetto.setProTSoggetti(null);
//
//		return proDProspetto;
//	}

}
