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
 * The persistent class for the PRO_T_CAUSA_SOSPENSIONE database table.
 * 
 */
@Entity
@Table(name="PRO_T_CAUSA_SOSPENSIONE")
@NamedQuery(name="ProTCausaSospensione.findAll", query="SELECT p FROM ProTCausaSospensione p")
public class ProTCausaSospensione implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idTCausaSospensione;
	}

	@Override
	public void setId(Long id) {
		idTCausaSospensione = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_T_CAUSA_SOSPENSIONE")
	private long idTCausaSospensione;

	@Column(name="COD_CAUSA_SOSPENSIONE")
	private String codCausaSospensione;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO")
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_TMST")
	private Date dataTmst;

	@Column(name="DES_CAUSA_SOSPENSIONE")
	private String desCausaSospensione;

	//bi-directional many-to-one association to ProDProvSospensione
	@OneToMany(mappedBy="proTCausaSospensione")
	private List<ProDProvSospensione> proDProvSospensiones;

	public ProTCausaSospensione() {
	}

	public long getIdTCausaSospensione() {
		return this.idTCausaSospensione;
	}

	public void setIdTCausaSospensione(long idTCausaSospensione) {
		this.idTCausaSospensione = idTCausaSospensione;
	}

	public String getCodCausaSospensione() {
		return this.codCausaSospensione;
	}

	public void setCodCausaSospensione(String codCausaSospensione) {
		this.codCausaSospensione = codCausaSospensione;
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

	public String getDesCausaSospensione() {
		return this.desCausaSospensione;
	}

	public void setDesCausaSospensione(String desCausaSospensione) {
		this.desCausaSospensione = desCausaSospensione;
	}

	public List<ProDProvSospensione> getProDProvSospensiones() {
		return this.proDProvSospensiones;
	}

	public void setProDProvSospensiones(List<ProDProvSospensione> proDProvSospensiones) {
		this.proDProvSospensiones = proDProvSospensiones;
	}

	public ProDProvSospensione addProDProvSospensione(ProDProvSospensione proDProvSospensione) {
		getProDProvSospensiones().add(proDProvSospensione);
		proDProvSospensione.setProTCausaSospensione(this);

		return proDProvSospensione;
	}

	public ProDProvSospensione removeProDProvSospensione(ProDProvSospensione proDProvSospensione) {
		getProDProvSospensiones().remove(proDProvSospensione);
		proDProvSospensione.setProTCausaSospensione(null);

		return proDProvSospensione;
	}

}
