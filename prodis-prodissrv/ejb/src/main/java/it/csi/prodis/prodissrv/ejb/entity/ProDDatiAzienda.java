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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_DATI_AZIENDA database table.
 * 
 */
@Entity
@Table(name="PRO_D_DATI_AZIENDA")
@NamedQuery(name="ProDDatiAzienda.findAll", query="SELECT p FROM ProDDatiAzienda p")
public class ProDDatiAzienda extends BaseAuditedEntity<Long> implements Serializable {

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

	@Column(name="CAP_REFERENTE")
	private String capReferente;

	@Column(name="CF_AZIENDA")
	private String cfAzienda;

	@Column(name="CF_CAPOGRUPPO")
	private String cfCapogruppo;

	@Column(name="CF_REFERENTE")
	private String cfReferente;

//	@Column(name="COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name="COD_USER_INSERIM")
//	private String codUserInserim;

	@Column(name="COGNOME_REFERENTE")
	private String cognomeReferente;

//	@Temporal(TemporalType.DATE)
//	@Column(name="D_AGGIORN")
//	private Date dAggiorn;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_INSERIM")
//	private Date dInserim;

	@Column(name="DENOMINAZIONE_DATORE_LAVORO")
	private String denominazioneDatoreLavoro;

	@Column(name="EMAIL_REFERENTE")
	private String emailReferente;

	@Column(name="FAX_REFERENTE")
	private String faxReferente;

	@Column(name="FLG_CAPOGRUPPO_ESTERO")
	private String flgCapogruppoEstero;

	@Column(name="FLG_PROSPETTO_DA_CAPOGRUPPO")
	private String flgProspettoDaCapogruppo;

	@Column(name="INDIRIZZO_REFERENTE")
	private String indirizzoReferente;

	@Column(name="NOME_REFERENTE")
	private String nomeReferente;

	@Column(name="TELEFONO_REFERENTE")
	private String telefonoReferente;

	//bi-directional one-to-one association to ProDProspetto
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO")
	private ProDProspetto proDProspetto;

	//bi-directional many-to-one association to ProTAtecofin
	@ManyToOne
	@JoinColumn(name="ID_T_ATECOFIN")
	private ProTAtecofin proTAtecofin;

	//bi-directional many-to-one association to ProTCcnl
	@ManyToOne
	@JoinColumn(name="ID_T_CCNL")
	private ProTCcnl proTCcnl;

	//bi-directional many-to-one association to ProTComune
	@ManyToOne
	@JoinColumn(name="ID_T_COMUNE")
	private ProTComune proTComune;

	//bi-directional many-to-one association to ProTDichiarante
	@ManyToOne
	@JoinColumn(name="ID_T_DICHIARANTE")
	private ProTDichiarante proTDichiarante;

	//bi-directional many-to-one association to ProTStatiEsteri
	@ManyToOne
	@JoinColumn(name="ID_T_STATI_ESTERI")
	private ProTStatiEsteri proTStatiEsteri;

	//bi-directional one-to-one association to ProDSedeLegale
	@OneToOne(mappedBy="proDDatiAzienda")
	private ProDSedeLegale proDSedeLegale;

	public ProDDatiAzienda() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public String getCapReferente() {
		return this.capReferente;
	}

	public void setCapReferente(String capReferente) {
		this.capReferente = capReferente;
	}

	public String getCfAzienda() {
		return this.cfAzienda;
	}

	public void setCfAzienda(String cfAzienda) {
		this.cfAzienda = cfAzienda;
	}

	public String getCfCapogruppo() {
		return this.cfCapogruppo;
	}

	public void setCfCapogruppo(String cfCapogruppo) {
		this.cfCapogruppo = cfCapogruppo;
	}

	public String getCfReferente() {
		return this.cfReferente;
	}

	public void setCfReferente(String cfReferente) {
		this.cfReferente = cfReferente;
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

	public String getCognomeReferente() {
		return this.cognomeReferente;
	}

	public void setCognomeReferente(String cognomeReferente) {
		this.cognomeReferente = cognomeReferente;
	}

//	public Date getDAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setDAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}
//
//	public Date getDInserim() {
//		return this.dInserim;
//	}
//
//	public void setDInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

	public String getDenominazioneDatoreLavoro() {
		return this.denominazioneDatoreLavoro;
	}

	public void setDenominazioneDatoreLavoro(String denominazioneDatoreLavoro) {
		this.denominazioneDatoreLavoro = denominazioneDatoreLavoro;
	}

	public String getEmailReferente() {
		return this.emailReferente;
	}

	public void setEmailReferente(String emailReferente) {
		this.emailReferente = emailReferente;
	}

	public String getFaxReferente() {
		return this.faxReferente;
	}

	public void setFaxReferente(String faxReferente) {
		this.faxReferente = faxReferente;
	}

	public String getFlgCapogruppoEstero() {
		return this.flgCapogruppoEstero;
	}

	public void setFlgCapogruppoEstero(String flgCapogruppoEstero) {
		this.flgCapogruppoEstero = flgCapogruppoEstero;
	}

	public String getFlgProspettoDaCapogruppo() {
		return this.flgProspettoDaCapogruppo;
	}

	public void setFlgProspettoDaCapogruppo(String flgProspettoDaCapogruppo) {
		this.flgProspettoDaCapogruppo = flgProspettoDaCapogruppo;
	}

	public String getIndirizzoReferente() {
		return this.indirizzoReferente;
	}

	public void setIndirizzoReferente(String indirizzoReferente) {
		this.indirizzoReferente = indirizzoReferente;
	}

	public String getNomeReferente() {
		return this.nomeReferente;
	}

	public void setNomeReferente(String nomeReferente) {
		this.nomeReferente = nomeReferente;
	}

	public String getTelefonoReferente() {
		return this.telefonoReferente;
	}

	public void setTelefonoReferente(String telefonoReferente) {
		this.telefonoReferente = telefonoReferente;
	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

	public ProTAtecofin getProTAtecofin() {
		return this.proTAtecofin;
	}

	public void setProTAtecofin(ProTAtecofin proTAtecofin) {
		this.proTAtecofin = proTAtecofin;
	}

	public ProTCcnl getProTCcnl() {
		return this.proTCcnl;
	}

	public void setProTCcnl(ProTCcnl proTCcnl) {
		this.proTCcnl = proTCcnl;
	}

	public ProTComune getProTComune() {
		return this.proTComune;
	}

	public void setProTComune(ProTComune proTComune) {
		this.proTComune = proTComune;
	}

	public ProTDichiarante getProTDichiarante() {
		return this.proTDichiarante;
	}

	public void setProTDichiarante(ProTDichiarante proTDichiarante) {
		this.proTDichiarante = proTDichiarante;
	}

	public ProTStatiEsteri getProTStatiEsteri() {
		return this.proTStatiEsteri;
	}

	public void setProTStatiEsteri(ProTStatiEsteri proTStatiEsteri) {
		this.proTStatiEsteri = proTStatiEsteri;
	}

	public ProDSedeLegale getProDSedeLegale() {
		return this.proDSedeLegale;
	}

	public void setProDSedeLegale(ProDSedeLegale proDSedeLegale) {
		this.proDSedeLegale = proDSedeLegale;
	}

}
