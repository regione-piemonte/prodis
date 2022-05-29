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

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_ISTAT2001LIVELLO5 database table.
 * 
 */
@Entity
@Table(name="PRO_T_ISTAT2001LIVELLO5")
@NamedQuery(name="ProTIstat2001livello5.findAll", query="SELECT p FROM ProTIstat2001livello5 p")
public class ProTIstat2001livello5 implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTIstat2001livello5;
	}

	@Override
	public void setId(Long id) {
		idTIstat2001livello5 = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_ISTAT2001LIVELLO5")
	private long idTIstat2001livello5;

	@Column(name="COD_ISTAT2001LIVELLO5_MIN")
	private String codIstat2001livello5Min;

	@Column(name="DS_COM_ISTAT2001LIVELLO5")
	private String dsComIstat2001livello5;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TMST")
	private Date dtTmst;

	@Column(name="FLG_VLD_UD")
	private String flgVldUd;

	@Column(name="ID_NEW_ISTAT")
	private BigDecimal idNewIstat;

//	//bi-directional many-to-one association to ProDLavoratoriInForza
//	@OneToMany(mappedBy="proTIstat2001livello5")
//	private List<ProDLavoratoriInForza> proDLavoratoriInForzas;
//
//	//bi-directional many-to-one association to ProDLavoratoriSilp
//	@OneToMany(mappedBy="proTIstat2001livello5")
//	private List<ProDLavoratoriSilp> proDLavoratoriSilps;
//
//	//bi-directional many-to-one association to ProDPostiLavoroDisp
//	@OneToMany(mappedBy="proTIstat2001livello5")
//	private List<ProDPostiLavoroDisp> proDPostiLavoroDisps;

	public ProTIstat2001livello5() {
	}

	public long getIdTIstat2001livello5() {
		return this.idTIstat2001livello5;
	}

	public void setIdTIstat2001livello5(long idTIstat2001livello5) {
		this.idTIstat2001livello5 = idTIstat2001livello5;
	}

	public String getCodIstat2001livello5Min() {
		return this.codIstat2001livello5Min;
	}

	public void setCodIstat2001livello5Min(String codIstat2001livello5Min) {
		this.codIstat2001livello5Min = codIstat2001livello5Min;
	}

	public String getDsComIstat2001livello5() {
		return this.dsComIstat2001livello5;
	}

	public void setDsComIstat2001livello5(String dsComIstat2001livello5) {
		this.dsComIstat2001livello5 = dsComIstat2001livello5;
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

	public Date getDtTmst() {
		return this.dtTmst;
	}

	public void setDtTmst(Date dtTmst) {
		this.dtTmst = dtTmst;
	}

	public String getFlgVldUd() {
		return this.flgVldUd;
	}

	public void setFlgVldUd(String flgVldUd) {
		this.flgVldUd = flgVldUd;
	}

	public BigDecimal getIdNewIstat() {
		return this.idNewIstat;
	}

	public void setIdNewIstat(BigDecimal idNewIstat) {
		this.idNewIstat = idNewIstat;
	}

//	public List<ProDLavoratoriInForza> getProDLavoratoriInForzas() {
//		return this.proDLavoratoriInForzas;
//	}
//
//	public void setProDLavoratoriInForzas(List<ProDLavoratoriInForza> proDLavoratoriInForzas) {
//		this.proDLavoratoriInForzas = proDLavoratoriInForzas;
//	}

//	public ProDLavoratoriInForza addProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
//		getProDLavoratoriInForzas().add(proDLavoratoriInForza);
//		proDLavoratoriInForza.setProTIstat2001livello5(this);
//
//		return proDLavoratoriInForza;
//	}
//
//	public ProDLavoratoriInForza removeProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
//		getProDLavoratoriInForzas().remove(proDLavoratoriInForza);
//		proDLavoratoriInForza.setProTIstat2001livello5(null);
//
//		return proDLavoratoriInForza;
//	}
//
//	public List<ProDLavoratoriSilp> getProDLavoratoriSilps() {
//		return this.proDLavoratoriSilps;
//	}
//
//	public void setProDLavoratoriSilps(List<ProDLavoratoriSilp> proDLavoratoriSilps) {
//		this.proDLavoratoriSilps = proDLavoratoriSilps;
//	}
//
//	public ProDLavoratoriSilp addProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().add(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTIstat2001livello5(this);
//
//		return proDLavoratoriSilp;
//	}
//
//	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTIstat2001livello5(null);
//
//		return proDLavoratoriSilp;
//	}
//
//	public List<ProDPostiLavoroDisp> getProDPostiLavoroDisps() {
//		return this.proDPostiLavoroDisps;
//	}
//
//	public void setProDPostiLavoroDisps(List<ProDPostiLavoroDisp> proDPostiLavoroDisps) {
//		this.proDPostiLavoroDisps = proDPostiLavoroDisps;
//	}
//
//	public ProDPostiLavoroDisp addProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
//		getProDPostiLavoroDisps().add(proDPostiLavoroDisp);
//		proDPostiLavoroDisp.setProTIstat2001livello5(this);
//
//		return proDPostiLavoroDisp;
//	}
//
//	public ProDPostiLavoroDisp removeProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
//		getProDPostiLavoroDisps().remove(proDPostiLavoroDisp);
//		proDPostiLavoroDisp.setProTIstat2001livello5(null);
//
//		return proDPostiLavoroDisp;
//	}

}
