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
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_T_PROVINCIA database table.
 * 
 */
@Entity
@Table(name="PRO_T_PROVINCIA")
@NamedQuery(name="ProTProvincia.findAll", query="SELECT p FROM ProTProvincia p")
public class ProTProvincia implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTProvincia;
	}

	@Override
	public void setId(Long id) {
		idTProvincia = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_PROVINCIA")
	private long idTProvincia;

	@Column(name="COD_PROVINCIA_MIN")
	private String codProvinciaMin;

	@Column(name="DS_PRO_T_PROVINCIA")
	private String dsProTProvincia;

	@Column(name="DS_TARGA")
	private String dsTarga;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TMST")
	private Date dtTmst;

	//bi-directional many-to-one association to ProDLavoratoriSilp
//	@OneToMany(mappedBy="proTProvincia")
//	private List<ProDLavoratoriSilp> proDLavoratoriSilps;

	//bi-directional many-to-one association to ProDPdfSilp
	@OneToMany(mappedBy="proTProvincia")
	private List<ProDPdfSilp> proDPdfSilps;

	//bi-directional many-to-one association to ProDProvCompensazioni
//	@OneToMany(mappedBy="proTProvincia")
//	private List<ProDProvCompensazioni> proDProvCompensazionis;
//
//	//bi-directional many-to-one association to ProRProspettoProvincia
//	@OneToMany(mappedBy="proTProvincia")
//	private List<ProRProspettoProvincia> proRProspettoProvincias;

	//bi-directional many-to-one association to ProTComune
//	@OneToMany(mappedBy="proTProvincia")
//	private List<ProTComune> proTComunes;

	//bi-directional many-to-one association to ProTRegione
	@ManyToOne
	@JoinColumn(name="ID_T_REGIONE")
	private ProTRegione proTRegione;

	public ProTProvincia() {
	}

	public long getIdTProvincia() {
		return this.idTProvincia;
	}

	public void setIdTProvincia(long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}

	public String getCodProvinciaMin() {
		return this.codProvinciaMin;
	}

	public void setCodProvinciaMin(String codProvinciaMin) {
		this.codProvinciaMin = codProvinciaMin;
	}

	public String getDsProTProvincia() {
		return this.dsProTProvincia;
	}

	public void setDsProTProvincia(String dsProTProvincia) {
		this.dsProTProvincia = dsProTProvincia;
	}

	public String getDsTarga() {
		return this.dsTarga;
	}

	public void setDsTarga(String dsTarga) {
		this.dsTarga = dsTarga;
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
//		proDLavoratoriSilp.setProTProvincia(this);
//
//		return proDLavoratoriSilp;
//	}
//
//	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTProvincia(null);
//
//		return proDLavoratoriSilp;
//	}

	public List<ProDPdfSilp> getProDPdfSilps() {
		return this.proDPdfSilps;
	}

	public void setProDPdfSilps(List<ProDPdfSilp> proDPdfSilps) {
		this.proDPdfSilps = proDPdfSilps;
	}

	public ProDPdfSilp addProDPdfSilp(ProDPdfSilp proDPdfSilp) {
		getProDPdfSilps().add(proDPdfSilp);
		proDPdfSilp.setProTProvincia(this);

		return proDPdfSilp;
	}

	public ProDPdfSilp removeProDPdfSilp(ProDPdfSilp proDPdfSilp) {
		getProDPdfSilps().remove(proDPdfSilp);
		proDPdfSilp.setProTProvincia(null);

		return proDPdfSilp;
	}

//	public List<ProDProvCompensazioni> getProDProvCompensazionis() {
//		return this.proDProvCompensazionis;
//	}
//
//	public void setProDProvCompensazionis(List<ProDProvCompensazioni> proDProvCompensazionis) {
//		this.proDProvCompensazionis = proDProvCompensazionis;
//	}

//	public ProDProvCompensazioni addProDProvCompensazioni(ProDProvCompensazioni proDProvCompensazioni) {
//		getProDProvCompensazionis().add(proDProvCompensazioni);
//		proDProvCompensazioni.setProTProvincia(this);
//
//		return proDProvCompensazioni;
//	}
//
//	public ProDProvCompensazioni removeProDProvCompensazioni(ProDProvCompensazioni proDProvCompensazioni) {
//		getProDProvCompensazionis().remove(proDProvCompensazioni);
//		proDProvCompensazioni.setProTProvincia(null);
//
//		return proDProvCompensazioni;
//	}
//
//	public List<ProRProspettoProvincia> getProRProspettoProvincias() {
//		return this.proRProspettoProvincias;
//	}
//
//	public void setProRProspettoProvincias(List<ProRProspettoProvincia> proRProspettoProvincias) {
//		this.proRProspettoProvincias = proRProspettoProvincias;
//	}

//	public ProRProspettoProvincia addProRProspettoProvincia(ProRProspettoProvincia proRProspettoProvincia) {
//		getProRProspettoProvincias().add(proRProspettoProvincia);
//		proRProspettoProvincia.setProTProvincia(this);
//
//		return proRProspettoProvincia;
//	}
//
//	public ProRProspettoProvincia removeProRProspettoProvincia(ProRProspettoProvincia proRProspettoProvincia) {
//		getProRProspettoProvincias().remove(proRProspettoProvincia);
//		proRProspettoProvincia.setProTProvincia(null);
//
//		return proRProspettoProvincia;
//	}

//	public List<ProTComune> getProTComunes() {
//		return this.proTComunes;
//	}
//
//	public void setProTComunes(List<ProTComune> proTComunes) {
//		this.proTComunes = proTComunes;
//	}

//	public ProTComune addProTComune(ProTComune proTComune) {
//		getProTComunes().add(proTComune);
//		proTComune.setProTProvincia(this);
//
//		return proTComune;
//	}
//
//	public ProTComune removeProTComune(ProTComune proTComune) {
//		getProTComunes().remove(proTComune);
//		proTComune.setProTProvincia(null);
//
//		return proTComune;
//	}

	public ProTRegione getProTRegione() {
		return this.proTRegione;
	}

	public void setProTRegione(ProTRegione proTRegione) {
		this.proTRegione = proTRegione;
	}

}
