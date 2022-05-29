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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_RIEPILOGO_PROVINCIALE database table.
 * 
 */
@Entity
@Table(name="PRO_D_RIEPILOGO_PROVINCIALE")
@NamedQuery(name="ProDRiepilogoProvinciale.findAll", query="SELECT p FROM ProDRiepilogoProvinciale p")
public class ProDRiepilogoProvinciale implements Serializable, BaseEntity<Long> {

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

	@Column(name="BASE_COMPUTO_ART_18")
	private BigDecimal baseComputoArt18;

	@Column(name="BASE_COMPUTO_ART_3")
	private BigDecimal baseComputoArt3;

	@Column(name="CAT_COMPENSAZIONE_CATE_PROT")
	private String catCompensazioneCateProt;

	@Column(name="CAT_COMPENSAZIONE_DISABILI")
	private String catCompensazioneDisabili;

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

	@Column(name="NUM_CAT_PROT_IN_FORZA_CONT_DIS")
	private BigDecimal numCatProtInForzaContDis;

	@Column(name="NUM_COMPENSAZIONE_DISABILI")
	private BigDecimal numCompensazioneDisabili;

	@Column(name="NUM_COMPENSAZIONI_CATE_PROT")
	private BigDecimal numCompensazioniCateProt;

	@Column(name="NUM_DISABILI_IN_FORZA")
	private BigDecimal numDisabiliInForza;

	@Column(name="NUM_LAVORATORI_BASE_COMPUTO")
	private BigDecimal numLavoratoriBaseComputo;

	@Column(name="NUM_LAVORATORI_SOSPENSIONE")
	private BigDecimal numLavoratoriSospensione;

	@Column(name="NUM_POSIZIONI_ESONERATE")
	private BigDecimal numPosizioniEsonerate;

	@Column(name="NUM_SCOPERTURE_CAT_PROT")
	private BigDecimal numScopertureCatProt;

	@Column(name="NUM_SCOPERTURE_CAT_PROT_REALI")
	private BigDecimal numScopertureCatProtReali;

	@Column(name="NUM_SCOPERTURE_DISABILI")
	private BigDecimal numScopertureDisabili;

	@Column(name="NUM_SCOPERTURE_DISABILI_REALI")
	private BigDecimal numScopertureDisabiliReali;

	@Column(name="QUOTA_RISERVA_ART_18")
	private BigDecimal quotaRiservaArt18;

	@Column(name="QUOTA_RISERVA_DISABILI")
	private BigDecimal quotaRiservaDisabili;

	//bi-directional one-to-one association to ProRProspettoProvincia
	@OneToOne
	@JoinColumn(name="ID_PROSPETTO_PROV")
	private ProRProspettoProvincia proRProspettoProvincia;

	public ProDRiepilogoProvinciale() {
	}

	public long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
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

	public String getCatCompensazioneCateProt() {
		return this.catCompensazioneCateProt;
	}

	public void setCatCompensazioneCateProt(String catCompensazioneCateProt) {
		this.catCompensazioneCateProt = catCompensazioneCateProt;
	}

	public String getCatCompensazioneDisabili() {
		return this.catCompensazioneDisabili;
	}

	public void setCatCompensazioneDisabili(String catCompensazioneDisabili) {
		this.catCompensazioneDisabili = catCompensazioneDisabili;
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

	public BigDecimal getNumCatProtInForzaContDis() {
		return this.numCatProtInForzaContDis;
	}

	public void setNumCatProtInForzaContDis(BigDecimal numCatProtInForzaContDis) {
		this.numCatProtInForzaContDis = numCatProtInForzaContDis;
	}

	public BigDecimal getNumCompensazioneDisabili() {
		return this.numCompensazioneDisabili;
	}

	public void setNumCompensazioneDisabili(BigDecimal numCompensazioneDisabili) {
		this.numCompensazioneDisabili = numCompensazioneDisabili;
	}

	public BigDecimal getNumCompensazioniCateProt() {
		return this.numCompensazioniCateProt;
	}

	public void setNumCompensazioniCateProt(BigDecimal numCompensazioniCateProt) {
		this.numCompensazioniCateProt = numCompensazioniCateProt;
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

	public BigDecimal getNumScopertureCatProt() {
		return this.numScopertureCatProt;
	}

	public void setNumScopertureCatProt(BigDecimal numScopertureCatProt) {
		this.numScopertureCatProt = numScopertureCatProt;
	}

	public BigDecimal getNumScopertureCatProtReali() {
		return this.numScopertureCatProtReali;
	}

	public void setNumScopertureCatProtReali(BigDecimal numScopertureCatProtReali) {
		this.numScopertureCatProtReali = numScopertureCatProtReali;
	}

	public BigDecimal getNumScopertureDisabili() {
		return this.numScopertureDisabili;
	}

	public void setNumScopertureDisabili(BigDecimal numScopertureDisabili) {
		this.numScopertureDisabili = numScopertureDisabili;
	}

	public BigDecimal getNumScopertureDisabiliReali() {
		return this.numScopertureDisabiliReali;
	}

	public void setNumScopertureDisabiliReali(BigDecimal numScopertureDisabiliReali) {
		this.numScopertureDisabiliReali = numScopertureDisabiliReali;
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

	public ProRProspettoProvincia getProRProspettoProvincia() {
		return this.proRProspettoProvincia;
	}

	public void setProRProspettoProvincia(ProRProspettoProvincia proRProspettoProvincia) {
		this.proRProspettoProvincia = proRProspettoProvincia;
	}

}
