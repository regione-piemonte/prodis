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
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_SOSPENSIONE database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_SOSPENSIONE")
@NamedQuery(name="ProDProvSospensione.findAll", query="SELECT p FROM ProDProvSospensione p")
public class ProDProvSospensione extends BaseAuditedEntity<Long>  implements Serializable {

	@Override
	public Long getId() {
		return idProspettoProv;
	}

	@Override
	public void setId(Long id) {
		idProspettoProv = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO_PROV")
	private long idProspettoProv;

//	@Column(name="COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name="COD_USER_INSERIM")
//	private String codUserInserim;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_AGGIORN")
//	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name="D_FINE_SOSPENSIONE_Q2")
	private Date dFineSospensioneQ2;

//	@Temporal(TemporalType.DATE)
//	@Column(name="D_INSERIM")
//	private Date dInserim;

	@Column(name="N_LAVORATORI")
	private BigDecimal nLavoratori;

	//bi-directional one-to-one association to ProDDatiProvinciali
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO_PROV")
	private ProDDatiProvinciali proDDatiProvinciali;

	//bi-directional many-to-one association to ProTCausaSospensione
	@ManyToOne
	@JoinColumn(name="ID_T_CAUSA_SOSPENSIONE")
	private ProTCausaSospensione proTCausaSospensione;

	//bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name="ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public ProDProvSospensione() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

//	public String getCodUserAggiorn() {
//		return this.codUserAggiorn;
//	}
//
//	public void setCodUserAggiorn(String codUserAggiorn) {
//		this.codUserAggiorn = codUserAggiorn;
//	}
//
//	public String getCodUserInserim() {
//		return this.codUserInserim;
//	}
//
//	public void setCodUserInserim(String codUserInserim) {
//		this.codUserInserim = codUserInserim;
//	}
//
//	public Date getDAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setDAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}

	public Date getDFineSospensioneQ2() {
		return this.dFineSospensioneQ2;
	}

	public void setDFineSospensioneQ2(Date dFineSospensioneQ2) {
		this.dFineSospensioneQ2 = dFineSospensioneQ2;
	}

//	public Date getDInserim() {
//		return this.dInserim;
//	}
//
//	public void setDInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

	public BigDecimal getnLavoratori() {
		return this.nLavoratori;
	}

	public void setnLavoratori(BigDecimal nLavoratori) {
		this.nLavoratori = nLavoratori;
	}

	public ProDDatiProvinciali getProDDatiProvinciali() {
		return this.proDDatiProvinciali;
	}

	public void setProDDatiProvinciali(ProDDatiProvinciali proDDatiProvinciali) {
		this.proDDatiProvinciali = proDDatiProvinciali;
	}

	public ProTCausaSospensione getProTCausaSospensione() {
		return this.proTCausaSospensione;
	}

	public void setProTCausaSospensione(ProTCausaSospensione proTCausaSospensione) {
		this.proTCausaSospensione = proTCausaSospensione;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

}
