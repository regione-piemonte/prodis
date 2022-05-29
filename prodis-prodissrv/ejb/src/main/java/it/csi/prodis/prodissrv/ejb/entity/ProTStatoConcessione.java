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
 * The persistent class for the PRO_T_STATO_CONCESSIONE database table.
 * 
 */
@Entity
@Table(name="PRO_T_STATO_CONCESSIONE")
@NamedQuery(name="ProTStatoConcessione.findAll", query="SELECT p FROM ProTStatoConcessione p")
public class ProTStatoConcessione implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTStatoConcessione;
	}

	@Override
	public void setId(Long id) {
		idTStatoConcessione = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_STATO_CONCESSIONE")
	private long idTStatoConcessione;

	@Column(name="COD_STATO_CONCESSIONE")
	private String codStatoConcessione;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DESC_STATO_CONCESSIONE")
	private String descStatoConcessione;

	//bi-directional many-to-one association to ProDProvCompensazioni
	@OneToMany(mappedBy="proTStatoConcessione")
	private List<ProDProvCompensazioni> proDProvCompensazionis;

	//bi-directional many-to-one association to ProDProvConvenzione
	@OneToMany(mappedBy="proTStatoConcessione")
	private List<ProDProvConvenzione> proDProvConvenziones;

	//bi-directional many-to-one association to ProDProvEsonero
	@OneToMany(mappedBy="proTStatoConcessione")
	private List<ProDProvEsonero> proDProvEsoneros;

	//bi-directional many-to-one association to ProDProvSospensione
	@OneToMany(mappedBy="proTStatoConcessione")
	private List<ProDProvSospensione> proDProvSospensiones;

	public ProTStatoConcessione() {
	}

	public long getIdTStatoConcessione() {
		return this.idTStatoConcessione;
	}

	public void setIdTStatoConcessione(long idTStatoConcessione) {
		this.idTStatoConcessione = idTStatoConcessione;
	}

	public String getCodStatoConcessione() {
		return this.codStatoConcessione;
	}

	public void setCodStatoConcessione(String codStatoConcessione) {
		this.codStatoConcessione = codStatoConcessione;
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

	public String getDescStatoConcessione() {
		return this.descStatoConcessione;
	}

	public void setDescStatoConcessione(String descStatoConcessione) {
		this.descStatoConcessione = descStatoConcessione;
	}

	public List<ProDProvCompensazioni> getProDProvCompensazionis() {
		return this.proDProvCompensazionis;
	}

	public void setProDProvCompensazionis(List<ProDProvCompensazioni> proDProvCompensazionis) {
		this.proDProvCompensazionis = proDProvCompensazionis;
	}

	public ProDProvCompensazioni addProDProvCompensazioni(ProDProvCompensazioni proDProvCompensazioni) {
		getProDProvCompensazionis().add(proDProvCompensazioni);
		proDProvCompensazioni.setProTStatoConcessione(this);

		return proDProvCompensazioni;
	}

	public ProDProvCompensazioni removeProDProvCompensazioni(ProDProvCompensazioni proDProvCompensazioni) {
		getProDProvCompensazionis().remove(proDProvCompensazioni);
		proDProvCompensazioni.setProTStatoConcessione(null);

		return proDProvCompensazioni;
	}

	public List<ProDProvConvenzione> getProDProvConvenziones() {
		return this.proDProvConvenziones;
	}

	public void setProDProvConvenziones(List<ProDProvConvenzione> proDProvConvenziones) {
		this.proDProvConvenziones = proDProvConvenziones;
	}

	public ProDProvConvenzione addProDProvConvenzione(ProDProvConvenzione proDProvConvenzione) {
		getProDProvConvenziones().add(proDProvConvenzione);
		proDProvConvenzione.setProTStatoConcessione(this);

		return proDProvConvenzione;
	}

	public ProDProvConvenzione removeProDProvConvenzione(ProDProvConvenzione proDProvConvenzione) {
		getProDProvConvenziones().remove(proDProvConvenzione);
		proDProvConvenzione.setProTStatoConcessione(null);

		return proDProvConvenzione;
	}

	public List<ProDProvEsonero> getProDProvEsoneros() {
		return this.proDProvEsoneros;
	}

	public void setProDProvEsoneros(List<ProDProvEsonero> proDProvEsoneros) {
		this.proDProvEsoneros = proDProvEsoneros;
	}

	public ProDProvEsonero addProDProvEsonero(ProDProvEsonero proDProvEsonero) {
		getProDProvEsoneros().add(proDProvEsonero);
		proDProvEsonero.setProTStatoConcessione(this);

		return proDProvEsonero;
	}

	public ProDProvEsonero removeProDProvEsonero(ProDProvEsonero proDProvEsonero) {
		getProDProvEsoneros().remove(proDProvEsonero);
		proDProvEsonero.setProTStatoConcessione(null);

		return proDProvEsonero;
	}

	public List<ProDProvSospensione> getProDProvSospensiones() {
		return this.proDProvSospensiones;
	}

	public void setProDProvSospensiones(List<ProDProvSospensione> proDProvSospensiones) {
		this.proDProvSospensiones = proDProvSospensiones;
	}

	public ProDProvSospensione addProDProvSospensione(ProDProvSospensione proDProvSospensione) {
		getProDProvSospensiones().add(proDProvSospensione);
		proDProvSospensione.setProTStatoConcessione(this);

		return proDProvSospensione;
	}

	public ProDProvSospensione removeProDProvSospensione(ProDProvSospensione proDProvSospensione) {
		getProDProvSospensiones().remove(proDProvSospensione);
		proDProvSospensione.setProTStatoConcessione(null);

		return proDProvSospensione;
	}

}
