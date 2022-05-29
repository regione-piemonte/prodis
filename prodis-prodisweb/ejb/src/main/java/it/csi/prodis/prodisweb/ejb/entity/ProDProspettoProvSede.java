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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_PROSPETTO_PROV_SEDE database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROSPETTO_PROV_SEDE")
@NamedQuery(name="ProDProspettoProvSede.findAll", query="SELECT p FROM ProDProspettoProvSede p")
public class ProDProspettoProvSede implements Serializable, BaseEntity<Long> {

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

	private String cap;

	@Column(name="COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name="COD_USER_INSERIM")
	private String codUserInserim;

	@Column(name="COGNOME_REFERENTE")
	private String cognomeReferente;

	@Temporal(TemporalType.DATE)
	@Column(name="D_AGGIORN")
	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name="D_INSERIM")
	private Date dInserim;

	private String email;

	private String fax;
	
	@OneToOne
	@JoinColumn(name="ID_T_COMUNE")
	private ProTComune proTComune;

	private String indirizzo;

	@Column(name="NOME_REFERENTE")
	private String nomeReferente;

	private String telefono;

	public ProDProspettoProvSede() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCodUserAggiorn() {
		return this.codUserAggiorn;
	}

	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	public String getCodUserInserim() {
		return this.codUserInserim;
	}

	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	public String getCognomeReferente() {
		return this.cognomeReferente;
	}

	public void setCognomeReferente(String cognomeReferente) {
		this.cognomeReferente = cognomeReferente;
	}

	public Date getDAggiorn() {
		return this.dAggiorn;
	}

	public void setDAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	public Date getDInserim() {
		return this.dInserim;
	}

	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getIndirizzo() {
		return this.indirizzo;
	}

	public ProTComune getProTComune() {
		return proTComune;
	}

	public void setProTComune(ProTComune proTComune) {
		this.proTComune = proTComune;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNomeReferente() {
		return this.nomeReferente;
	}

	public void setNomeReferente(String nomeReferente) {
		this.nomeReferente = nomeReferente;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
