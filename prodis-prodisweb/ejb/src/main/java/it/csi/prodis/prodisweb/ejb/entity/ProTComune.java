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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PRO_T_COMUNE database table.
 * 
 */
@Entity
@Table(name = "PRO_T_COMUNE")
@NamedQuery(name = "ProTComune.findAll", query = "SELECT p FROM ProTComune p")
public class ProTComune implements Serializable, it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Override
	public Long getId() {
		return idTComune;
	}

	@Override
	public void setId(Long id) {
		idTComune = id;
	}

	@Id
	@Column(name = "ID_T_COMUNE")
	private Long idTComune;

	@Column(name = "COD_CAP")
	private String codCap;

	@Column(name = "COD_COMUNE_MIN")
	private String codComuneMin;

	@Column(name = "COD_INPS")
	private String codInps;

	@Column(name = "COD_ISTAT")
	private String codIstat;

	@Column(name = "COD_PREFISSO")
	private String codPrefisso;

	@Column(name = "DS_PRO_T_COMUNE")
	private String dsProTComune;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_TMST")
	private Date dtTmst;

//	// bi-directional many-to-one association to ProDDatiAzienda
//	@OneToMany(mappedBy = "proTComune")
//	private List<ProDDatiAzienda> proDDatiAziendas;
//
//	// bi-directional many-to-one association to ProDLavoratoriInForza
//	//@OneToMany(mappedBy = "proTComune")
//	//private List<ProDLavoratoriInForza> proDLavoratoriInForzas;
//
//	// bi-directional many-to-one association to ProDLavoratoriSilp
//	@OneToMany(mappedBy = "proTComune")
//	private List<ProDLavoratoriSilp> proDLavoratoriSilps;
//
//	// bi-directional many-to-one association to ProDPostiLavoroDisp
//	@OneToMany(mappedBy = "proTComune")
//	private List<ProDPostiLavoroDisp> proDPostiLavoroDisps;
//
//	// bi-directional many-to-one association to ProDSedeLegale
//	@OneToMany(mappedBy = "proTComune")
//	private List<ProDSedeLegale> proDSedeLegales;

	// bi-directional many-to-one association to ProTCpi
	// @ManyToOne
	// @JoinColumn(name = "ID_T_CPI")
	// private ProTCpi proTCpi;
	@Column(name = "ID_T_CPI")
	private Long idTCpi;

	// bi-directional many-to-one association to ProTProvincia
	@ManyToOne
	@JoinColumn(name = "ID_PROVINCIA")
	private ProTProvincia proTProvincia;

	public ProTComune() {
	}

	public Long getIdTComune() {
		return this.idTComune;
	}

	public void setIdTComune(Long idTComune) {
		this.idTComune = idTComune;
	}

	public String getCodCap() {
		return this.codCap;
	}

	public void setCodCap(String codCap) {
		this.codCap = codCap;
	}

	public String getCodComuneMin() {
		return this.codComuneMin;
	}

	public void setCodComuneMin(String codComuneMin) {
		this.codComuneMin = codComuneMin;
	}

	public String getCodInps() {
		return this.codInps;
	}

	public void setCodInps(String codInps) {
		this.codInps = codInps;
	}

	public String getCodIstat() {
		return this.codIstat;
	}

	public void setCodIstat(String codIstat) {
		this.codIstat = codIstat;
	}

	public String getCodPrefisso() {
		return this.codPrefisso;
	}

	public void setCodPrefisso(String codPrefisso) {
		this.codPrefisso = codPrefisso;
	}

	public String getDsProTComune() {
		return this.dsProTComune;
	}

	public void setDsProTComune(String dsProTComune) {
		this.dsProTComune = dsProTComune;
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

//	public List<ProDDatiAzienda> getProDDatiAziendas() {
//		return this.proDDatiAziendas;
//	}
//
//	public void setProDDatiAziendas(List<ProDDatiAzienda> proDDatiAziendas) {
//		this.proDDatiAziendas = proDDatiAziendas;
//	}

//	public ProDDatiAzienda addProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().add(proDDatiAzienda);
//		proDDatiAzienda.setProTComune(this);
//
//		return proDDatiAzienda;
//	}

//	public ProDDatiAzienda removeProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
//		getProDDatiAziendas().remove(proDDatiAzienda);
//		proDDatiAzienda.setProTComune(null);
//
//		return proDDatiAzienda;
//	}
//
//	public List<ProDLavoratoriInForza> getProDLavoratoriInForzas() {
//		return this.proDLavoratoriInForzas;
//	}
//
//	public void setProDLavoratoriInForzas(List<ProDLavoratoriInForza> proDLavoratoriInForzas) {
//		this.proDLavoratoriInForzas = proDLavoratoriInForzas;
//	}

//	public ProDLavoratoriInForza addProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
//		getProDLavoratoriInForzas().add(proDLavoratoriInForza);
//		proDLavoratoriInForza.setProTComune(this);
//
//		return proDLavoratoriInForza;
//	}
//
//	public ProDLavoratoriInForza removeProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
//		getProDLavoratoriInForzas().remove(proDLavoratoriInForza);
//		proDLavoratoriInForza.setProTComune(null);
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

//	public ProDLavoratoriSilp addProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().add(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTComune(this);
//
//		return proDLavoratoriSilp;
//	}
//
//	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTComune(null);
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

//	public ProDPostiLavoroDisp addProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
//		getProDPostiLavoroDisps().add(proDPostiLavoroDisp);
//		proDPostiLavoroDisp.setProTComune(this);
//
//		return proDPostiLavoroDisp;
//	}
//
//	public ProDPostiLavoroDisp removeProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
//		getProDPostiLavoroDisps().remove(proDPostiLavoroDisp);
//		proDPostiLavoroDisp.setProTComune(null);
//
//		return proDPostiLavoroDisp;
//	}
//
//	public List<ProDSedeLegale> getProDSedeLegales() {
//		return this.proDSedeLegales;
//	}

//	public void setProDSedeLegales(List<ProDSedeLegale> proDSedeLegales) {
//		this.proDSedeLegales = proDSedeLegales;
//	}
//
//	public ProDSedeLegale addProDSedeLegale(ProDSedeLegale proDSedeLegale) {
//		getProDSedeLegales().add(proDSedeLegale);
//		proDSedeLegale.setProTComune(this);
//
//		return proDSedeLegale;
//	}
//
//	public ProDSedeLegale removeProDSedeLegale(ProDSedeLegale proDSedeLegale) {
//		getProDSedeLegales().remove(proDSedeLegale);
//		proDSedeLegale.setProTComune(null);
//
//		return proDSedeLegale;
//	}

//	public ProTCpi getProTCpi() {
//		return this.proTCpi;
//	}
//
//	public void setProTCpi(ProTCpi proTCpi) {
//		this.proTCpi = proTCpi;
//	}

	public ProTProvincia getProTProvincia() {
		return this.proTProvincia;
	}

	public Long getIdTCpi() {
		return idTCpi;
	}

	public void setIdTCpi(Long idTCpi) {
		this.idTCpi = idTCpi;
	}

	public void setProTProvincia(ProTProvincia proTProvincia) {
		this.proTProvincia = proTProvincia;
	}

}
