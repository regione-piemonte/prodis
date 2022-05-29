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
 * The persistent class for the PRO_T_ASSUNZIONE_PROTETTA database table.
 * 
 */
@Entity
@Table(name="PRO_T_ASSUNZIONE_PROTETTA")
@NamedQuery(name="ProTAssunzioneProtetta.findAll", query="SELECT p FROM ProTAssunzioneProtetta p")
public class ProTAssunzioneProtetta implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTAssunzioneProtetta;
	}

	@Override
	public void setId(Long id) {
		idTAssunzioneProtetta = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_ASSUNZIONE_PROTETTA")
	private long idTAssunzioneProtetta;

	@Column(name="COD_ASSUNZIONE_PROTETTA")
	private String codAssunzioneProtetta;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DESC_ASSUNZIONE_PROTETTA")
	private String descAssunzioneProtetta;

	@Column(name="FLG_CONVENSIONE")
	private String flgConvensione;

//	//bi-directional many-to-one association to ProDLavoratoriInForza
//	@OneToMany(mappedBy="proTAssunzioneProtetta")
//	private List<ProDLavoratoriInForza> proDLavoratoriInForzas;
//
//	//bi-directional many-to-one association to ProDLavoratoriSilp
//	@OneToMany(mappedBy="proTAssunzioneProtetta")
//	private List<ProDLavoratoriSilp> proDLavoratoriSilps;
//
//	//bi-directional many-to-one association to ProDProvConvenzione
//	@OneToMany(mappedBy="proTAssunzioneProtetta")
//	private List<ProDProvConvenzione> proDProvConvenziones;

	public ProTAssunzioneProtetta() {
	}

	public long getIdTAssunzioneProtetta() {
		return this.idTAssunzioneProtetta;
	}

	public void setIdTAssunzioneProtetta(long idTAssunzioneProtetta) {
		this.idTAssunzioneProtetta = idTAssunzioneProtetta;
	}

	public String getCodAssunzioneProtetta() {
		return this.codAssunzioneProtetta;
	}

	public void setCodAssunzioneProtetta(String codAssunzioneProtetta) {
		this.codAssunzioneProtetta = codAssunzioneProtetta;
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

	public String getDescAssunzioneProtetta() {
		return this.descAssunzioneProtetta;
	}

	public void setDescAssunzioneProtetta(String descAssunzioneProtetta) {
		this.descAssunzioneProtetta = descAssunzioneProtetta;
	}

	public String getFlgConvensione() {
		return this.flgConvensione;
	}

	public void setFlgConvensione(String flgConvensione) {
		this.flgConvensione = flgConvensione;
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
//		proDLavoratoriInForza.setProTAssunzioneProtetta(this);
//
//		return proDLavoratoriInForza;
//	}
//
//	public ProDLavoratoriInForza removeProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
//		getProDLavoratoriInForzas().remove(proDLavoratoriInForza);
//		proDLavoratoriInForza.setProTAssunzioneProtetta(null);
//
//		return proDLavoratoriInForza;
//	}

//	public List<ProDLavoratoriSilp> getProDLavoratoriSilps() {
//		return this.proDLavoratoriSilps;
//	}
//
//	public void setProDLavoratoriSilps(List<ProDLavoratoriSilp> proDLavoratoriSilps) {
//		this.proDLavoratoriSilps = proDLavoratoriSilps;
//	}

//	public ProDLavoratoriSilp addProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().add(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTAssunzioneProtetta(this);
//
//		return proDLavoratoriSilp;
//	}
//
//	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
//		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
//		proDLavoratoriSilp.setProTAssunzioneProtetta(null);
//
//		return proDLavoratoriSilp;
//	}
//
//	public List<ProDProvConvenzione> getProDProvConvenziones() {
//		return this.proDProvConvenziones;
//	}
//
//	public void setProDProvConvenziones(List<ProDProvConvenzione> proDProvConvenziones) {
//		this.proDProvConvenziones = proDProvConvenziones;
//	}

//	public ProDProvConvenzione addProDProvConvenzione(ProDProvConvenzione proDProvConvenzione) {
//		getProDProvConvenziones().add(proDProvConvenzione);
//		proDProvConvenzione.setProTAssunzioneProtetta(this);
//
//		return proDProvConvenzione;
//	}
//
//	public ProDProvConvenzione removeProDProvConvenzione(ProDProvConvenzione proDProvConvenzione) {
//		getProDProvConvenziones().remove(proDProvConvenzione);
//		proDProvConvenzione.setProTAssunzioneProtetta(null);
//
//		return proDProvConvenzione;
//	}

}
