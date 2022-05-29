/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;

/**
 * The Class RiepilogoProvinciale.
 */
public class RiepilogoProvinciale extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal baseComputoArt18;
	private BigDecimal baseComputoArt3;
	private String catCompensazioneCateProt;
	private String catCompensazioneDisabili;
	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private String flgSospensioniInCorso;
	private BigDecimal numCatProtInForza;
	private BigDecimal numCatProtInForzaContDis;
	private BigDecimal numCompensazioneDisabili;
	private BigDecimal numCompensazioniCateProt;
	private BigDecimal numDisabiliInForza;
	private BigDecimal numLavoratoriBaseComputo;
	private BigDecimal numLavoratoriSospensione;
	private BigDecimal numPosizioniEsonerate;
	private BigDecimal numScopertureCatProt;
	private BigDecimal numScopertureCatProtReali;
	private BigDecimal numScopertureDisabili;
	private BigDecimal numScopertureDisabiliReali;
	private BigDecimal quotaRiservaArt18;
	private BigDecimal quotaRiservaDisabili;
	private ProspettoProvincia prospettoProvincia;

	private BigDecimal numMinOrarioSettimanaleContrattualeLav;

	/**
	 * @return the baseComputoArt18
	 */
	public BigDecimal getBaseComputoArt18() {
		return baseComputoArt18;
	}

	/**
	 * @param baseComputoArt18 the baseComputoArt18 to set
	 */
	public void setBaseComputoArt18(BigDecimal baseComputoArt18) {
		this.baseComputoArt18 = baseComputoArt18;
	}

	/**
	 * @return the baseComputoArt3
	 */
	public BigDecimal getBaseComputoArt3() {
		return baseComputoArt3;
	}

	/**
	 * @param baseComputoArt3 the baseComputoArt3 to set
	 */
	public void setBaseComputoArt3(BigDecimal baseComputoArt3) {
		this.baseComputoArt3 = baseComputoArt3;
	}

	/**
	 * @return the catCompensazioneCateProt
	 */
	public String getCatCompensazioneCateProt() {
		return catCompensazioneCateProt;
	}

	/**
	 * @param catCompensazioneCateProt the catCompensazioneCateProt to set
	 */
	public void setCatCompensazioneCateProt(String catCompensazioneCateProt) {
		this.catCompensazioneCateProt = catCompensazioneCateProt;
	}

	/**
	 * @return the catCompensazioneDisabili
	 */
	public String getCatCompensazioneDisabili() {
		return catCompensazioneDisabili;
	}

	/**
	 * @param catCompensazioneDisabili the catCompensazioneDisabili to set
	 */
	public void setCatCompensazioneDisabili(String catCompensazioneDisabili) {
		this.catCompensazioneDisabili = catCompensazioneDisabili;
	}

	/**
	 * @return the codUserAggiorn
	 */
	public String getCodUserAggiorn() {
		return codUserAggiorn;
	}

	/**
	 * @param codUserAggiorn the codUserAggiorn to set
	 */
	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	/**
	 * @return the codUserInserim
	 */
	public String getCodUserInserim() {
		return codUserInserim;
	}

	/**
	 * @param codUserInserim the codUserInserim to set
	 */
	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	/**
	 * @return the dAggiorn
	 */
	public Date getDAggiorn() {
		return dAggiorn;
	}

	/**
	 * @param dAggiorn the dAggiorn to set
	 */
	public void setDAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	/**
	 * @return the dInserim
	 */
	public Date getDInserim() {
		return dInserim;
	}

	/**
	 * @param dInserim the dInserim to set
	 */
	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	/**
	 * @return the flgSospensioniInCorso
	 */
	public String getFlgSospensioniInCorso() {
		return flgSospensioniInCorso;
	}

	/**
	 * @param flgSospensioniInCorso the flgSospensioniInCorso to set
	 */
	public void setFlgSospensioniInCorso(String flgSospensioniInCorso) {
		this.flgSospensioniInCorso = flgSospensioniInCorso;
	}

	/**
	 * @return the numCatProtInForza
	 */
	public BigDecimal getNumCatProtInForza() {
		return numCatProtInForza;
	}

	/**
	 * @param numCatProtInForza the numCatProtInForza to set
	 */
	public void setNumCatProtInForza(BigDecimal numCatProtInForza) {
		this.numCatProtInForza = numCatProtInForza;
	}

	/**
	 * @return the numCatProtInForzaContDis
	 */
	public BigDecimal getNumCatProtInForzaContDis() {
		return numCatProtInForzaContDis;
	}

	/**
	 * @param numCatProtInForzaContDis the numCatProtInForzaContDis to set
	 */
	public void setNumCatProtInForzaContDis(BigDecimal numCatProtInForzaContDis) {
		this.numCatProtInForzaContDis = numCatProtInForzaContDis;
	}

	/**
	 * @return the numCompensazioneDisabili
	 */
	public BigDecimal getNumCompensazioneDisabili() {
		return numCompensazioneDisabili;
	}

	/**
	 * @param numCompensazioneDisabili the numCompensazioneDisabili to set
	 */
	public void setNumCompensazioneDisabili(BigDecimal numCompensazioneDisabili) {
		this.numCompensazioneDisabili = numCompensazioneDisabili;
	}

	/**
	 * @return the numCompensazioniCateProt
	 */
	public BigDecimal getNumCompensazioniCateProt() {
		return numCompensazioniCateProt;
	}

	/**
	 * @param numCompensazioniCateProt the numCompensazioniCateProt to set
	 */
	public void setNumCompensazioniCateProt(BigDecimal numCompensazioniCateProt) {
		this.numCompensazioniCateProt = numCompensazioniCateProt;
	}

	/**
	 * @return the numDisabiliInForza
	 */
	public BigDecimal getNumDisabiliInForza() {
		return numDisabiliInForza;
	}

	/**
	 * @param numDisabiliInForza the numDisabiliInForza to set
	 */
	public void setNumDisabiliInForza(BigDecimal numDisabiliInForza) {
		this.numDisabiliInForza = numDisabiliInForza;
	}

	/**
	 * @return the numLavoratoriBaseComputo
	 */
	public BigDecimal getNumLavoratoriBaseComputo() {
		return numLavoratoriBaseComputo;
	}

	/**
	 * @param numLavoratoriBaseComputo the numLavoratoriBaseComputo to set
	 */
	public void setNumLavoratoriBaseComputo(BigDecimal numLavoratoriBaseComputo) {
		this.numLavoratoriBaseComputo = numLavoratoriBaseComputo;
	}

	/**
	 * @return the numLavoratoriSospensione
	 */
	public BigDecimal getNumLavoratoriSospensione() {
		return numLavoratoriSospensione;
	}

	/**
	 * @param numLavoratoriSospensione the numLavoratoriSospensione to set
	 */
	public void setNumLavoratoriSospensione(BigDecimal numLavoratoriSospensione) {
		this.numLavoratoriSospensione = numLavoratoriSospensione;
	}

	/**
	 * @return the numPosizioniEsonerate
	 */
	public BigDecimal getNumPosizioniEsonerate() {
		return numPosizioniEsonerate;
	}

	/**
	 * @param numPosizioniEsonerate the numPosizioniEsonerate to set
	 */
	public void setNumPosizioniEsonerate(BigDecimal numPosizioniEsonerate) {
		this.numPosizioniEsonerate = numPosizioniEsonerate;
	}

	/**
	 * @return the numScopertureCatProt
	 */
	public BigDecimal getNumScopertureCatProt() {
		return numScopertureCatProt;
	}

	/**
	 * @param numScopertureCatProt the numScopertureCatProt to set
	 */
	public void setNumScopertureCatProt(BigDecimal numScopertureCatProt) {
		this.numScopertureCatProt = numScopertureCatProt;
	}

	/**
	 * @return the numScopertureCatProtReali
	 */
	public BigDecimal getNumScopertureCatProtReali() {
		return numScopertureCatProtReali;
	}

	/**
	 * @param numScopertureCatProtReali the numScopertureCatProtReali to set
	 */
	public void setNumScopertureCatProtReali(BigDecimal numScopertureCatProtReali) {
		this.numScopertureCatProtReali = numScopertureCatProtReali;
	}

	/**
	 * @return the numScopertureDisabili
	 */
	public BigDecimal getNumScopertureDisabili() {
		return numScopertureDisabili;
	}

	/**
	 * @param numScopertureDisabili the numScopertureDisabili to set
	 */
	public void setNumScopertureDisabili(BigDecimal numScopertureDisabili) {
		this.numScopertureDisabili = numScopertureDisabili;
	}

	/**
	 * @return the numScopertureDisabiliReali
	 */
	public BigDecimal getNumScopertureDisabiliReali() {
		return numScopertureDisabiliReali;
	}

	/**
	 * @param numScopertureDisabiliReali the numScopertureDisabiliReali to set
	 */
	public void setNumScopertureDisabiliReali(BigDecimal numScopertureDisabiliReali) {
		this.numScopertureDisabiliReali = numScopertureDisabiliReali;
	}

	/**
	 * @return the quotaRiservaArt18
	 */
	public BigDecimal getQuotaRiservaArt18() {
		return quotaRiservaArt18;
	}

	/**
	 * @param quotaRiservaArt18 the quotaRiservaArt18 to set
	 */
	public void setQuotaRiservaArt18(BigDecimal quotaRiservaArt18) {
		this.quotaRiservaArt18 = quotaRiservaArt18;
	}

	/**
	 * @return the quotaRiservaDisabili
	 */
	public BigDecimal getQuotaRiservaDisabili() {
		return quotaRiservaDisabili;
	}

	/**
	 * @param quotaRiservaDisabili the quotaRiservaDisabili to set
	 */
	public void setQuotaRiservaDisabili(BigDecimal quotaRiservaDisabili) {
		this.quotaRiservaDisabili = quotaRiservaDisabili;
	}

	/**
	 * @return the prospettoProvincia
	 */
	public ProspettoProvincia getProspettoProvincia() {
		return prospettoProvincia;
	}

	/**
	 * @param prospettoProvincia the prospettoProvincia to set
	 */
	public void setProspettoProvincia(ProspettoProvincia prospettoProvincia) {
		this.prospettoProvincia = prospettoProvincia;
	}

	public BigDecimal getNumMinOrarioSettimanaleContrattualeLav() {
		return numMinOrarioSettimanaleContrattualeLav;
	}

	public void setNumMinOrarioSettimanaleContrattualeLav(BigDecimal numMinOrarioSettimanaleContrattualeLav) {
		this.numMinOrarioSettimanaleContrattualeLav = numMinOrarioSettimanaleContrattualeLav;
	}

}
