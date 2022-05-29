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
 * The persistent class for the PRO_T_CONTRATTI database table.
 * 
 */
@Entity
@Table(name="PRO_T_CONTRATTI")
@NamedQuery(name="ProTContratti.findAll", query="SELECT p FROM ProTContratti p")
public class ProTContratti implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTContratto;
	}

	@Override
	public void setId(Long id) {
		idTContratto = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_CONTRATTO")
	private long idTContratto;

	@Column(name="COD_TIPO_CONTRATTO_MIN")
	private String codTipoContrattoMin;

	@Column(name="DS_CONTRATTO")
	private String dsContratto;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TMST")
	private Date dtTmst;

	@Column(name="FLG_FORMA")
	private String flgForma;

	private String status;

	private String tipo;

	@Column(name="TIPOLOGIA_RAPPORTO")
	private String tipologiaRapporto;

	//bi-directional many-to-one association to ProDLavoratoriInForza
	@OneToMany(mappedBy="proTContratti")
	private List<ProDLavoratoriInForza> proDLavoratoriInForzas;

	//bi-directional many-to-one association to ProDLavoratoriSilp
	@OneToMany(mappedBy="proTContratti")
	private List<ProDLavoratoriSilp> proDLavoratoriSilps;

	public ProTContratti() {
	}

	public long getIdTContratto() {
		return this.idTContratto;
	}

	public void setIdTContratto(long idTContratto) {
		this.idTContratto = idTContratto;
	}

	public String getCodTipoContrattoMin() {
		return this.codTipoContrattoMin;
	}

	public void setCodTipoContrattoMin(String codTipoContrattoMin) {
		this.codTipoContrattoMin = codTipoContrattoMin;
	}

	public String getDsContratto() {
		return this.dsContratto;
	}

	public void setDsContratto(String dsContratto) {
		this.dsContratto = dsContratto;
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

	public String getFlgForma() {
		return this.flgForma;
	}

	public void setFlgForma(String flgForma) {
		this.flgForma = flgForma;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipologiaRapporto() {
		return this.tipologiaRapporto;
	}

	public void setTipologiaRapporto(String tipologiaRapporto) {
		this.tipologiaRapporto = tipologiaRapporto;
	}

	public List<ProDLavoratoriInForza> getProDLavoratoriInForzas() {
		return this.proDLavoratoriInForzas;
	}

	public void setProDLavoratoriInForzas(List<ProDLavoratoriInForza> proDLavoratoriInForzas) {
		this.proDLavoratoriInForzas = proDLavoratoriInForzas;
	}

	public ProDLavoratoriInForza addProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
		getProDLavoratoriInForzas().add(proDLavoratoriInForza);
		proDLavoratoriInForza.setProTContratti(this);

		return proDLavoratoriInForza;
	}

	public ProDLavoratoriInForza removeProDLavoratoriInForza(ProDLavoratoriInForza proDLavoratoriInForza) {
		getProDLavoratoriInForzas().remove(proDLavoratoriInForza);
		proDLavoratoriInForza.setProTContratti(null);

		return proDLavoratoriInForza;
	}

	public List<ProDLavoratoriSilp> getProDLavoratoriSilps() {
		return this.proDLavoratoriSilps;
	}

	public void setProDLavoratoriSilps(List<ProDLavoratoriSilp> proDLavoratoriSilps) {
		this.proDLavoratoriSilps = proDLavoratoriSilps;
	}

	public ProDLavoratoriSilp addProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
		getProDLavoratoriSilps().add(proDLavoratoriSilp);
		proDLavoratoriSilp.setProTContratti(this);

		return proDLavoratoriSilp;
	}

	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
		proDLavoratoriSilp.setProTContratti(null);

		return proDLavoratoriSilp;
	}

}
