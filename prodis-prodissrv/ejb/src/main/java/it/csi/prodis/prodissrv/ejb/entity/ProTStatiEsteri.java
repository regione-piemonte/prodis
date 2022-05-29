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
 * The persistent class for the PRO_T_STATI_ESTERI database table.
 * 
 */
@Entity
@Table(name="PRO_T_STATI_ESTERI")
@NamedQuery(name="ProTStatiEsteri.findAll", query="SELECT p FROM ProTStatiEsteri p")
public class ProTStatiEsteri implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTStatiEsteri;
	}

	@Override
	public void setId(Long id) {
		idTStatiEsteri = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_STATI_ESTERI")
	private long idTStatiEsteri;

	@Column(name="COD_NAZIONE_MIN")
	private String codNazioneMin;

	@Column(name="DS_STATI_ESTERI")
	private String dsStatiEsteri;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_FINE")
	private Date dtFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_INIZIO")
	private Date dtInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DT_TMST")
	private Date dtTmst;

	@Column(name="FLG_UE")
	private String flgUe;

	@Column(name="SIGLA_NAZIONE")
	private String siglaNazione;

	//bi-directional many-to-one association to ProDDatiAzienda
	@OneToMany(mappedBy="proTStatiEsteri")
	private List<ProDDatiAzienda> proDDatiAziendas;

	//bi-directional many-to-one association to ProDLavoratoriSilp
	@OneToMany(mappedBy="proTStatiEsteri")
	private List<ProDLavoratoriSilp> proDLavoratoriSilps;

	//bi-directional many-to-one association to ProDPostiLavoroDisp
	@OneToMany(mappedBy="proTStatiEsteri")
	private List<ProDPostiLavoroDisp> proDPostiLavoroDisps;

	//bi-directional many-to-one association to ProDSedeLegale
	@OneToMany(mappedBy="proTStatiEsteri")
	private List<ProDSedeLegale> proDSedeLegales;

	public ProTStatiEsteri() {
	}

	public long getIdTStatiEsteri() {
		return this.idTStatiEsteri;
	}

	public void setIdTStatiEsteri(long idTStatiEsteri) {
		this.idTStatiEsteri = idTStatiEsteri;
	}

	public String getCodNazioneMin() {
		return this.codNazioneMin;
	}

	public void setCodNazioneMin(String codNazioneMin) {
		this.codNazioneMin = codNazioneMin;
	}

	public String getDsStatiEsteri() {
		return this.dsStatiEsteri;
	}

	public void setDsStatiEsteri(String dsStatiEsteri) {
		this.dsStatiEsteri = dsStatiEsteri;
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

	public String getFlgUe() {
		return this.flgUe;
	}

	public void setFlgUe(String flgUe) {
		this.flgUe = flgUe;
	}

	public String getSiglaNazione() {
		return this.siglaNazione;
	}

	public void setSiglaNazione(String siglaNazione) {
		this.siglaNazione = siglaNazione;
	}

	public List<ProDDatiAzienda> getProDDatiAziendas() {
		return this.proDDatiAziendas;
	}

	public void setProDDatiAziendas(List<ProDDatiAzienda> proDDatiAziendas) {
		this.proDDatiAziendas = proDDatiAziendas;
	}

	public ProDDatiAzienda addProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
		getProDDatiAziendas().add(proDDatiAzienda);
		proDDatiAzienda.setProTStatiEsteri(this);

		return proDDatiAzienda;
	}

	public ProDDatiAzienda removeProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
		getProDDatiAziendas().remove(proDDatiAzienda);
		proDDatiAzienda.setProTStatiEsteri(null);

		return proDDatiAzienda;
	}

	public List<ProDLavoratoriSilp> getProDLavoratoriSilps() {
		return this.proDLavoratoriSilps;
	}

	public void setProDLavoratoriSilps(List<ProDLavoratoriSilp> proDLavoratoriSilps) {
		this.proDLavoratoriSilps = proDLavoratoriSilps;
	}

	public ProDLavoratoriSilp addProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
		getProDLavoratoriSilps().add(proDLavoratoriSilp);
		proDLavoratoriSilp.setProTStatiEsteri(this);

		return proDLavoratoriSilp;
	}

	public ProDLavoratoriSilp removeProDLavoratoriSilp(ProDLavoratoriSilp proDLavoratoriSilp) {
		getProDLavoratoriSilps().remove(proDLavoratoriSilp);
		proDLavoratoriSilp.setProTStatiEsteri(null);

		return proDLavoratoriSilp;
	}

	public List<ProDPostiLavoroDisp> getProDPostiLavoroDisps() {
		return this.proDPostiLavoroDisps;
	}

	public void setProDPostiLavoroDisps(List<ProDPostiLavoroDisp> proDPostiLavoroDisps) {
		this.proDPostiLavoroDisps = proDPostiLavoroDisps;
	}

	public ProDPostiLavoroDisp addProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
		getProDPostiLavoroDisps().add(proDPostiLavoroDisp);
		proDPostiLavoroDisp.setProTStatiEsteri(this);

		return proDPostiLavoroDisp;
	}

	public ProDPostiLavoroDisp removeProDPostiLavoroDisp(ProDPostiLavoroDisp proDPostiLavoroDisp) {
		getProDPostiLavoroDisps().remove(proDPostiLavoroDisp);
		proDPostiLavoroDisp.setProTStatiEsteri(null);

		return proDPostiLavoroDisp;
	}

	public List<ProDSedeLegale> getProDSedeLegales() {
		return this.proDSedeLegales;
	}

	public void setProDSedeLegales(List<ProDSedeLegale> proDSedeLegales) {
		this.proDSedeLegales = proDSedeLegales;
	}

	public ProDSedeLegale addProDSedeLegale(ProDSedeLegale proDSedeLegale) {
		getProDSedeLegales().add(proDSedeLegale);
		proDSedeLegale.setProTStatiEsteri(this);

		return proDSedeLegale;
	}

	public ProDSedeLegale removeProDSedeLegale(ProDSedeLegale proDSedeLegale) {
		getProDSedeLegales().remove(proDSedeLegale);
		proDSedeLegale.setProTStatiEsteri(null);

		return proDSedeLegale;
	}

}
