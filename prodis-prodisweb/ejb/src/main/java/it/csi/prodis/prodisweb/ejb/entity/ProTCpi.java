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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_CPI database table.
 * 
 */
@Entity
@Table(name="PRO_T_CPI")
@NamedQuery(name="ProTCpi.findAll", query="SELECT p FROM ProTCpi p")
public class ProTCpi implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTCpi;
	}

	@Override
	public void setId(Long id) {
		idTCpi = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_CPI")
	private long idTCpi;

	@Column(name="C_UTENTE")
	private String cUtente;

	@Column(name="COD_CPI")
	private String codCpi;

	@Column(name="DS_PRO_T_CPI")
	private String dsProTCpi;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;
	
	@Column(name="DS_Mail")
	private String dsMail;	

	//bi-directional many-to-one association to ProTComune
//	@OneToMany(mappedBy="proTCpi")
//	private List<ProTComune> proTComunes;

	public ProTCpi() {
	}

	public long getIdTCpi() {
		return this.idTCpi;
	}

	public void setIdTCpi(long idTCpi) {
		this.idTCpi = idTCpi;
	}

	public String getCUtente() {
		return this.cUtente;
	}

	public void setCUtente(String cUtente) {
		this.cUtente = cUtente;
	}

	public String getCodCpi() {
		return this.codCpi;
	}

	public void setCodCpi(String codCpi) {
		this.codCpi = codCpi;
	}

	public String getDsProTCpi() {
		return this.dsProTCpi;
	}

	public void setDsProTCpi(String dsProTCpi) {
		this.dsProTCpi = dsProTCpi;
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

	
	public String getDsMail() {
		return dsMail;
	}

	public void setDsMail(String dsMail) {
		this.dsMail = dsMail;
	}

//	public List<ProTComune> getProTComunes() {
//		return this.proTComunes;
//	}
//
//	public void setProTComunes(List<ProTComune> proTComunes) {
//		this.proTComunes = proTComunes;
//	}
//
//	public ProTComune addProTComune(ProTComune proTComune) {
//		getProTComunes().add(proTComune);
//		proTComune.setProTCpi(this);
//
//		return proTComune;
//	}
//
//	public ProTComune removeProTComune(ProTComune proTComune) {
//		getProTComunes().remove(proTComune);
//		proTComune.setProTCpi(null);
//
//		return proTComune;
//	}

}
