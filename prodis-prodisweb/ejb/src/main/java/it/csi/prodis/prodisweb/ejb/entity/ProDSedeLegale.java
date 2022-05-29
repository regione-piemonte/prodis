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

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;
import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_SEDE_LEGALE database table.
 * 
 */
@Entity
@Table(name="PRO_D_SEDE_LEGALE")
@NamedQuery(name="ProDSedeLegale.findAll", query="SELECT p FROM ProDSedeLegale p")
public class ProDSedeLegale extends BaseAuditedEntity<Long> implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idProspetto;
	}

	@Override
	public void setId(Long id) {
		idProspetto = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PROSPETTO")
	private long idProspetto;

	@Column(name="CAP_SEDE")
	private String capSede;

	@Column(name="COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name="COD_USER_INSERIM")
	private String codUserInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="D_AGGIORN")
	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name="D_INSERIM")
	private Date dInserim;

	private String email;

	private String fax;

	private String indirizzo;

	private String telefono;

	//bi-directional many-to-one association to ProTComune
	@ManyToOne
	@JoinColumn(name="ID_T_COMUNE")
	private ProTComune proTComune;

	//bi-directional many-to-one association to ProTStatiEsteri
	@ManyToOne
	@JoinColumn(name="ID_T_STATI_ESTERI")
	private ProTStatiEsteri proTStatiEsteri;

	public ProDSedeLegale() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public String getCapSede() {
		return this.capSede;
	}

	public void setCapSede(String capSede) {
		this.capSede = capSede;
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

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ProTComune getProTComune() {
		return this.proTComune;
	}

	public void setProTComune(ProTComune proTComune) {
		this.proTComune = proTComune;
	}

	public ProTStatiEsteri getProTStatiEsteri() {
		return this.proTStatiEsteri;
	}

	public void setProTStatiEsteri(ProTStatiEsteri proTStatiEsteri) {
		this.proTStatiEsteri = proTStatiEsteri;
	}

}
