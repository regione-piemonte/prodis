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

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_RIEPILOGO_NAZIONALE database table.
 * 
 */
@Entity
@Table(name="PRO_D_RIEPILOGO_NAZIONALE")
@NamedQuery(name="ProDRiepilogoNazionale.findAll", query="SELECT p FROM ProDRiepilogoNazionale p")
public class ProDRiepilogoNazionale extends BaseAuditedEntity<Long> implements Serializable {

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

	@Column(name="BASE_COMPUTO_ART_18")
	private BigDecimal baseComputoArt18;

	@Column(name="BASE_COMPUTO_ART_3")
	private BigDecimal baseComputoArt3;

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

	@Column(name="FLG_SOSPENSIONI_IN_CORSO")
	private String flgSospensioniInCorso;

	@Column(name="NUM_CAT_PROT_IN_FORZA")
	private BigDecimal numCatProtInForza;

	@Column(name="NUM_CAT_PROT_IN_FORZA_CNT_DIS")
	private BigDecimal numCatProtInForzaCntDis;

	@Column(name="NUM_DISABILI_IN_FORZA")
	private BigDecimal numDisabiliInForza;

	@Column(name="NUM_LAVORATORI_BASE_COMPUTO")
	private BigDecimal numLavoratoriBaseComputo;

	@Column(name="NUM_LAVORATORI_SOSPENSIONE")
	private BigDecimal numLavoratoriSospensione;

	@Column(name="NUM_POSIZIONI_ESONERATE")
	private BigDecimal numPosizioniEsonerate;

	@Column(name="NUM_SCOPERT_CAT_PROT_REALI")
	private BigDecimal numScopertCatProtReali;

	@Column(name="NUM_SCOPERT_CATEGORIE_PROTETTE")
	private BigDecimal numScopertCategorieProtette;

	@Column(name="NUM_SCOPERT_DISABILI")
	private BigDecimal numScopertDisabili;

	@Column(name="NUM_SCOPERT_DISABILI_REALI")
	private BigDecimal numScopertDisabiliReali;

	@Column(name="QUOTA_ESUBERI_ART_18")
	private BigDecimal quotaEsuberiArt18;

	@Column(name="QUOTA_RISERVA_ART_18")
	private BigDecimal quotaRiservaArt18;

	@Column(name="QUOTA_RISERVA_DISABILI")
	private BigDecimal quotaRiservaDisabili;

	//bi-directional one-to-one association to ProDProspetto
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO")
	private ProDProspetto proDProspetto;

	public ProDRiepilogoNazionale() {
	}

	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public BigDecimal getBaseComputoArt18() {
		return this.baseComputoArt18;
	}

	public void setBaseComputoArt18(BigDecimal baseComputoArt18) {
		this.baseComputoArt18 = baseComputoArt18;
	}

	public BigDecimal getBaseComputoArt3() {
		return this.baseComputoArt3;
	}

	public void setBaseComputoArt3(BigDecimal baseComputoArt3) {
		this.baseComputoArt3 = baseComputoArt3;
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

	public String getFlgSospensioniInCorso() {
		return this.flgSospensioniInCorso;
	}

	public void setFlgSospensioniInCorso(String flgSospensioniInCorso) {
		this.flgSospensioniInCorso = flgSospensioniInCorso;
	}

	public BigDecimal getNumCatProtInForza() {
		return this.numCatProtInForza;
	}

	public void setNumCatProtInForza(BigDecimal numCatProtInForza) {
		this.numCatProtInForza = numCatProtInForza;
	}

	public BigDecimal getNumCatProtInForzaCntDis() {
		return this.numCatProtInForzaCntDis;
	}

	public void setNumCatProtInForzaCntDis(BigDecimal numCatProtInForzaCntDis) {
		this.numCatProtInForzaCntDis = numCatProtInForzaCntDis;
	}

	public BigDecimal getNumDisabiliInForza() {
		return this.numDisabiliInForza;
	}

	public void setNumDisabiliInForza(BigDecimal numDisabiliInForza) {
		this.numDisabiliInForza = numDisabiliInForza;
	}

	public BigDecimal getNumLavoratoriBaseComputo() {
		return this.numLavoratoriBaseComputo;
	}

	public void setNumLavoratoriBaseComputo(BigDecimal numLavoratoriBaseComputo) {
		this.numLavoratoriBaseComputo = numLavoratoriBaseComputo;
	}

	public BigDecimal getNumLavoratoriSospensione() {
		return this.numLavoratoriSospensione;
	}

	public void setNumLavoratoriSospensione(BigDecimal numLavoratoriSospensione) {
		this.numLavoratoriSospensione = numLavoratoriSospensione;
	}

	public BigDecimal getNumPosizioniEsonerate() {
		return this.numPosizioniEsonerate;
	}

	public void setNumPosizioniEsonerate(BigDecimal numPosizioniEsonerate) {
		this.numPosizioniEsonerate = numPosizioniEsonerate;
	}

	public BigDecimal getNumScopertCatProtReali() {
		return this.numScopertCatProtReali;
	}

	public void setNumScopertCatProtReali(BigDecimal numScopertCatProtReali) {
		this.numScopertCatProtReali = numScopertCatProtReali;
	}

	public BigDecimal getNumScopertCategorieProtette() {
		return this.numScopertCategorieProtette;
	}

	public void setNumScopertCategorieProtette(BigDecimal numScopertCategorieProtette) {
		this.numScopertCategorieProtette = numScopertCategorieProtette;
	}

	public BigDecimal getNumScopertDisabili() {
		return this.numScopertDisabili;
	}

	public void setNumScopertDisabili(BigDecimal numScopertDisabili) {
		this.numScopertDisabili = numScopertDisabili;
	}

	public BigDecimal getNumScopertDisabiliReali() {
		return this.numScopertDisabiliReali;
	}

	public void setNumScopertDisabiliReali(BigDecimal numScopertDisabiliReali) {
		this.numScopertDisabiliReali = numScopertDisabiliReali;
	}

	public BigDecimal getQuotaEsuberiArt18() {
		return this.quotaEsuberiArt18;
	}

	public void setQuotaEsuberiArt18(BigDecimal quotaEsuberiArt18) {
		this.quotaEsuberiArt18 = quotaEsuberiArt18;
	}

	public BigDecimal getQuotaRiservaArt18() {
		return this.quotaRiservaArt18;
	}

	public void setQuotaRiservaArt18(BigDecimal quotaRiservaArt18) {
		this.quotaRiservaArt18 = quotaRiservaArt18;
	}

	public BigDecimal getQuotaRiservaDisabili() {
		return this.quotaRiservaDisabili;
	}

	public void setQuotaRiservaDisabili(BigDecimal quotaRiservaDisabili) {
		this.quotaRiservaDisabili = quotaRiservaDisabili;
	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

}
