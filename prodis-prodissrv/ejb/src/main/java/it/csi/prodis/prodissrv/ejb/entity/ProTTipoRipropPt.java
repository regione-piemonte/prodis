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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_TIPO_RIPROP_PT database table.
 * 
 */
@Entity
@Table(name="PRO_T_TIPO_RIPROP_PT")
@NamedQuery(name="ProTTipoRipropPt.findAll", query="SELECT p FROM ProTTipoRipropPt p")
public class ProTTipoRipropPt implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTipoRipropPt;
	}

	@Override
	public void setId(Long id) {
		idTipoRipropPt = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_RIPROP_PT")
	private long idTipoRipropPt;

	@Column(name="AMBITO_RIPROP")
	private String ambitoRiprop;

	@Column(name="AMBITO_RIPROP_BC")
	private String ambitoRipropBc;

	@Column(name="DS_MIN")
	private String dsMin;

	@Column(name="DS_TIPO_RIPROP_PT")
	private String dsTipoRipropPt;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	//bi-directional many-to-one association to ProDPartTime
	@OneToMany(mappedBy="proTTipoRipropPt")
	private List<ProDPartTime> proDPartTimes;

	public ProTTipoRipropPt() {
	}

	public long getIdTipoRipropPt() {
		return this.idTipoRipropPt;
	}

	public void setIdTipoRipropPt(long idTipoRipropPt) {
		this.idTipoRipropPt = idTipoRipropPt;
	}

	public String getAmbitoRiprop() {
		return this.ambitoRiprop;
	}

	public void setAmbitoRiprop(String ambitoRiprop) {
		this.ambitoRiprop = ambitoRiprop;
	}

	public String getAmbitoRipropBc() {
		return this.ambitoRipropBc;
	}

	public void setAmbitoRipropBc(String ambitoRipropBc) {
		this.ambitoRipropBc = ambitoRipropBc;
	}

	public String getDsMin() {
		return this.dsMin;
	}

	public void setDsMin(String dsMin) {
		this.dsMin = dsMin;
	}

	public String getDsTipoRipropPt() {
		return this.dsTipoRipropPt;
	}

	public void setDsTipoRipropPt(String dsTipoRipropPt) {
		this.dsTipoRipropPt = dsTipoRipropPt;
	}

	public Date getDtFine() {
		return this.dtFine;
	}

	public void setDtFine(Date dtFine) {
		this.dtFine = dtFine;
	}

	public Date getDtInizio() {
		return this.dtInizio;
	}

	public void setDtInizio(Date dtInizio) {
		this.dtInizio = dtInizio;
	}

	public List<ProDPartTime> getProDPartTimes() {
		return this.proDPartTimes;
	}

	public void setProDPartTimes(List<ProDPartTime> proDPartTimes) {
		this.proDPartTimes = proDPartTimes;
	}

	public ProDPartTime addProDPartTime(ProDPartTime proDPartTime) {
		getProDPartTimes().add(proDPartTime);
		proDPartTime.setProTTipoRipropPt(this);

		return proDPartTime;
	}

	public ProDPartTime removeProDPartTime(ProDPartTime proDPartTime) {
		getProDPartTimes().remove(proDPartTime);
		proDPartTime.setProTTipoRipropPt(null);

		return proDPartTime;
	}

}
