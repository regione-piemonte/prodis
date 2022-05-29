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
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;

/**
 * The persistent class for the PRO_D_DATI_PROVINCIALI database table.
 * 
 */
@Entity
@Table(name = "PRO_D_DATI_PROVINCIALI")
@NamedQuery(name = "ProDDatiProvinciali.findAll", query = "SELECT p FROM ProDDatiProvinciali p")
public class ProDDatiProvinciali extends BaseAuditedEntity<Long> implements Serializable {

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
	@Column(name = "ID_PROSPETTO_PROV", unique = true)
	private long idProspettoProv;

	@Column(name = "BASE_COMPUTO")
	private BigDecimal baseComputo;

	@Column(name = "FLG_AZI_CON_CONVENZIONE")
	private String flgAziConConvenzione;

	@Column(name = "FLG_AZI_ESONERO_PARZIALE")
	private String flgAziEsoneroParziale;

	@Column(name = "FLG_AZI_GRADU")
	private String flgAziGradu;

	@Column(name = "FLG_AZI_SOSP")
	private String flgAziSosp;

	@Column(name = "FLG_CATEGORIE_ESCLUSE_COMPUTE")
	private String flgCategorieEscluseCompute;

	@Column(name = "FLG_COMPENSAZIONI_TERRITORIAL")
	private String flgCompensazioniTerritorial;

	@Column(name = "FLG_DETTAGLIO_INTERMITTENTI")
	private String flgDettaglioIntermittenti;

	@Column(name = "FLG_DETTAGLIO_PARTIME")
	private String flgDettaglioPartime;

	@Column(name = "FLG_LAVORATORI_IN_FORZA")
	private String flgLavoratoriInForza;

	@Column(name = "FLG_POSTI_LAVORO_DISPONIBILI")
	private String flgPostiLavoroDisponibili;

	@Column(name = "N_CATE_PROT_FORZA")
	private BigDecimal nCateProtForza;

	@Column(name = "N_CATE_PROT_FORZA_A_17_01_2000")
	private BigDecimal nCateProtForzaA17012000;

	@Column(name = "N_CATE_PROT_FORZA_CNT_DIS")
	private String nCateProtForzaCntDis;

	@Column(name = "N_CATE_PROT_FORZA_ESUBERO")
	private String nCateProtForzaEsubero;

	@Column(name = "N_CENTRAL_TELEFO_NONVEDENTI")
	private BigDecimal nCentralTelefoNonvedenti;

	@Column(name = "N_CONVENZIONI_12BIS_14_FT")
	private BigDecimal nConvenzioni12bis14Ft;

	@Column(name = "N_DISABILI_IN_FORZA")
	private BigDecimal nDisabiliInForza;

	@Column(name = "N_INTERMITTENTI_RIPROPORZIONA")
	private BigDecimal nIntermittentiRiproporziona;

	@Column(name = "N_PARTIME_RIPROPORZIONATI")
	private BigDecimal nPartimeRiproporzionati;

	@Column(name = "N_POSTI_PREV_CENTRALI_NONVED")
	private BigDecimal nPostiPrevCentraliNonved;

	@Column(name = "N_POSTI_PREV_MASSOFIS_NONVED")
	private BigDecimal nPostiPrevMassofisNonved;

	@Column(name = "N_SOMMINISTRATI_FT")
	private BigDecimal nSomministratiFt;

	@Column(name = "N_TELELAVORO_FT")
	private BigDecimal nTelelavoroFt;

	@Column(name = "N_TERARIAB_MASSOFIS_NONVED")
	private BigDecimal nTerariabMassofisNonved;

	@Column(name = "N_TOTALE_LAVORAT_DIPENDENTI")
	private BigDecimal nTotaleLavoratDipendenti;

	private String note;

	// bi-directional one-to-one association to ProDProspettoProvSede
	@OneToOne
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProspettoProvSede proDProspettoProvSede;

	// bi-directional one-to-one association to ProDProvConvenzione
	@OneToOne
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProvConvenzione proDProvConvenzione;

	// bi-directional one-to-one association to ProDProvEsonero
	@OneToOne
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProvEsonero proDProvEsonero;

	// bi-directional one-to-one association to ProDProvEsoneroAutocert
	@OneToOne
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProvEsoneroAutocert proDProvEsoneroAutocert;

	// bi-directional one-to-one association to ProDProvGradualita
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProvGradualita proDProvGradualita;

	// bi-directional one-to-one association to ProDProvSospensione
	// de-commentato perché senza va in errore il riepilogo del quadro 1
	@OneToOne
	@JoinColumn(name = "ID_PROSPETTO_PROV")
	private ProDProvSospensione proDProvSospensione;

	public ProDDatiProvinciali() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public BigDecimal getBaseComputo() {
		return this.baseComputo;
	}

	public void setBaseComputo(BigDecimal baseComputo) {
		this.baseComputo = baseComputo;
	}

	public String getFlgAziConConvenzione() {
		return this.flgAziConConvenzione;
	}

	public void setFlgAziConConvenzione(String flgAziConConvenzione) {
		this.flgAziConConvenzione = flgAziConConvenzione;
	}

	public String getFlgAziEsoneroParziale() {
		return this.flgAziEsoneroParziale;
	}

	public void setFlgAziEsoneroParziale(String flgAziEsoneroParziale) {
		this.flgAziEsoneroParziale = flgAziEsoneroParziale;
	}

	public String getFlgAziGradu() {
		return this.flgAziGradu;
	}

	public void setFlgAziGradu(String flgAziGradu) {
		this.flgAziGradu = flgAziGradu;
	}

	public String getFlgAziSosp() {
		return this.flgAziSosp;
	}

	public void setFlgAziSosp(String flgAziSosp) {
		this.flgAziSosp = flgAziSosp;
	}

	public String getFlgCategorieEscluseCompute() {
		return this.flgCategorieEscluseCompute;
	}

	public void setFlgCategorieEscluseCompute(String flgCategorieEscluseCompute) {
		this.flgCategorieEscluseCompute = flgCategorieEscluseCompute;
	}

	public String getFlgCompensazioniTerritorial() {
		return this.flgCompensazioniTerritorial;
	}

	public void setFlgCompensazioniTerritorial(String flgCompensazioniTerritorial) {
		this.flgCompensazioniTerritorial = flgCompensazioniTerritorial;
	}

	public String getFlgDettaglioIntermittenti() {
		return this.flgDettaglioIntermittenti;
	}

	public void setFlgDettaglioIntermittenti(String flgDettaglioIntermittenti) {
		this.flgDettaglioIntermittenti = flgDettaglioIntermittenti;
	}

	public String getFlgDettaglioPartime() {
		return this.flgDettaglioPartime;
	}

	public void setFlgDettaglioPartime(String flgDettaglioPartime) {
		this.flgDettaglioPartime = flgDettaglioPartime;
	}

	public String getFlgLavoratoriInForza() {
		return this.flgLavoratoriInForza;
	}

	public void setFlgLavoratoriInForza(String flgLavoratoriInForza) {
		this.flgLavoratoriInForza = flgLavoratoriInForza;
	}

	public String getFlgPostiLavoroDisponibili() {
		return this.flgPostiLavoroDisponibili;
	}

	public void setFlgPostiLavoroDisponibili(String flgPostiLavoroDisponibili) {
		this.flgPostiLavoroDisponibili = flgPostiLavoroDisponibili;
	}

	public BigDecimal getnCateProtForza() {
		return this.nCateProtForza;
	}

	public void setnCateProtForza(BigDecimal nCateProtForza) {
		this.nCateProtForza = nCateProtForza;
	}

	public BigDecimal getnCateProtForzaA17012000() {
		return this.nCateProtForzaA17012000;
	}

	public void setnCateProtForzaA17012000(BigDecimal nCateProtForzaA17012000) {
		this.nCateProtForzaA17012000 = nCateProtForzaA17012000;
	}

	public String getnCateProtForzaCntDis() {
		return this.nCateProtForzaCntDis;
	}

	public void setnCateProtForzaCntDis(String nCateProtForzaCntDis) {
		this.nCateProtForzaCntDis = nCateProtForzaCntDis;
	}

	public String getnCateProtForzaEsubero() {
		return this.nCateProtForzaEsubero;
	}

	public void setnCateProtForzaEsubero(String nCateProtForzaEsubero) {
		this.nCateProtForzaEsubero = nCateProtForzaEsubero;
	}

	public BigDecimal getnCentralTelefoNonvedenti() {
		return this.nCentralTelefoNonvedenti;
	}

	public void setnCentralTelefoNonvedenti(BigDecimal nCentralTelefoNonvedenti) {
		this.nCentralTelefoNonvedenti = nCentralTelefoNonvedenti;
	}

	public BigDecimal getnConvenzioni12bis14Ft() {
		return this.nConvenzioni12bis14Ft;
	}

	public void setnConvenzioni12bis14Ft(BigDecimal nConvenzioni12bis14Ft) {
		this.nConvenzioni12bis14Ft = nConvenzioni12bis14Ft;
	}

	public BigDecimal getnDisabiliInForza() {
		return this.nDisabiliInForza;
	}

	public void setnDisabiliInForza(BigDecimal nDisabiliInForza) {
		this.nDisabiliInForza = nDisabiliInForza;
	}

	public BigDecimal getnIntermittentiRiproporziona() {
		return this.nIntermittentiRiproporziona;
	}

	public void setnIntermittentiRiproporziona(BigDecimal nIntermittentiRiproporziona) {
		this.nIntermittentiRiproporziona = nIntermittentiRiproporziona;
	}

	public BigDecimal getnPartimeRiproporzionati() {
		return this.nPartimeRiproporzionati;
	}

	public void setnPartimeRiproporzionati(BigDecimal nPartimeRiproporzionati) {
		this.nPartimeRiproporzionati = nPartimeRiproporzionati;
	}

	public BigDecimal getnPostiPrevCentraliNonved() {
		return this.nPostiPrevCentraliNonved;
	}

	public void setnPostiPrevCentraliNonved(BigDecimal nPostiPrevCentraliNonved) {
		this.nPostiPrevCentraliNonved = nPostiPrevCentraliNonved;
	}

	public BigDecimal getnPostiPrevMassofisNonved() {
		return this.nPostiPrevMassofisNonved;
	}

	public void setnPostiPrevMassofisNonved(BigDecimal nPostiPrevMassofisNonved) {
		this.nPostiPrevMassofisNonved = nPostiPrevMassofisNonved;
	}

	public BigDecimal getnSomministratiFt() {
		return this.nSomministratiFt;
	}

	public void setnSomministratiFt(BigDecimal nSomministratiFt) {
		this.nSomministratiFt = nSomministratiFt;
	}

	public BigDecimal getnTelelavoroFt() {
		return this.nTelelavoroFt;
	}

	public void setnTelelavoroFt(BigDecimal nTelelavoroFt) {
		this.nTelelavoroFt = nTelelavoroFt;
	}

	public BigDecimal getnTerariabMassofisNonved() {
		return this.nTerariabMassofisNonved;
	}

	public void setnTerariabMassofisNonved(BigDecimal nTerariabMassofisNonved) {
		this.nTerariabMassofisNonved = nTerariabMassofisNonved;
	}

	public BigDecimal getnTotaleLavoratDipendenti() {
		return this.nTotaleLavoratDipendenti;
	}

	public void setnTotaleLavoratDipendenti(BigDecimal nTotaleLavoratDipendenti) {
		this.nTotaleLavoratDipendenti = nTotaleLavoratDipendenti;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ProDProspettoProvSede getProDProspettoProvSede() {
		return this.proDProspettoProvSede;
	}

	public void setProDProspettoProvSede(ProDProspettoProvSede proDProspettoProvSede) {
		this.proDProspettoProvSede = proDProspettoProvSede;
	}

	public ProDProvConvenzione getProDProvConvenzione() {
		return this.proDProvConvenzione;
	}

	public void setProDProvConvenzione(ProDProvConvenzione proDProvConvenzione) {
		this.proDProvConvenzione = proDProvConvenzione;
	}

	public ProDProvEsonero getProDProvEsonero() {
		return this.proDProvEsonero;
	}

	public void setProDProvEsonero(ProDProvEsonero proDProvEsonero) {
		this.proDProvEsonero = proDProvEsonero;
	}

	public ProDProvEsoneroAutocert getProDProvEsoneroAutocert() {
		return this.proDProvEsoneroAutocert;
	}

	public void setProDProvEsoneroAutocert(ProDProvEsoneroAutocert proDProvEsoneroAutocert) {
		this.proDProvEsoneroAutocert = proDProvEsoneroAutocert;
	}

	public ProDProvGradualita getProDProvGradualita() {
		return this.proDProvGradualita;
	}

	public void setProDProvGradualita(ProDProvGradualita proDProvGradualita) {
		this.proDProvGradualita = proDProvGradualita;
	}

	public ProDProvSospensione getProDProvSospensione() {
		return this.proDProvSospensione;
	}

	public void setProDProvSospensione(ProDProvSospensione proDProvSospensione) {
		this.proDProvSospensione = proDProvSospensione;
	}

}
