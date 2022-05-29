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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_R_PROSPETTO_PROVINCIA database table.
 * 
 */
@Entity
@Table(name="PRO_R_PROSPETTO_PROVINCIA") 
@NamedQuery(name="ProRProspettoProvincia.findAll", query="SELECT p FROM ProRProspettoProvincia p")
@SequenceGenerator(name = "DATI_SEQUENCE", sequenceName = "SEQ_R_PROSPETTO_PROVINCIA", initialValue = 1, allocationSize = 1)
public class ProRProspettoProvincia implements Serializable, BaseEntity<Long> {

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
	@Column(name="ID_PROSPETTO_PROV", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DATI_SEQUENCE")
	private long idProspettoProv;

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

	@Column(name="FLG_CONFERMATO_Q2")
	private String flgConfermatoQ2;

	//bi-directional one-to-one association to ProDDatiProvinciali
	@OneToOne(mappedBy="proRProspettoProvincia")
	private ProDDatiProvinciali proDDatiProvinciali;

	//bi-directional one-to-one association to ProDRiepilogoProvinciale
	@OneToOne(mappedBy="proRProspettoProvincia")
	private ProDRiepilogoProvinciale proDRiepilogoProvinciale;

	@Column(name="ID_PROSPETTO")
	private Long idProspetto;

	//bi-directional many-to-one association to ProTProvincia
	@ManyToOne
	@JoinColumn(name="ID_T_PROVINCIA")
	private ProTProvincia proTProvincia;

	public ProRProspettoProvincia() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
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

	public String getFlgConfermatoQ2() {
		return this.flgConfermatoQ2;
	}

	public void setFlgConfermatoQ2(String flgConfermatoQ2) {
		this.flgConfermatoQ2 = flgConfermatoQ2;
	}

	public ProDDatiProvinciali getProDDatiProvinciali() {
		return this.proDDatiProvinciali;
	}

	public void setProDDatiProvinciali(ProDDatiProvinciali proDDatiProvinciali) {
		this.proDDatiProvinciali = proDDatiProvinciali;
	}

	public ProDRiepilogoProvinciale getProDRiepilogoProvinciale() {
		return this.proDRiepilogoProvinciale;
	}

	public void setProDRiepilogoProvinciale(ProDRiepilogoProvinciale proDRiepilogoProvinciale) {
		this.proDRiepilogoProvinciale = proDRiepilogoProvinciale;
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public ProTProvincia getProTProvincia() {
		return this.proTProvincia;
	}

	public void setProTProvincia(ProTProvincia proTProvincia) {
		this.proTProvincia = proTProvincia;
	}

}
