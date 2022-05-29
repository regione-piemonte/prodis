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
 * The Class RiepilogoNazionale.
 */
public class RiepilogoNazionale extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal baseComputoArt18;
	private BigDecimal baseComputoArt3;
	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private String flgSospensioniInCorso;
	private BigDecimal numCatProtInForza;
	private BigDecimal numCatProtInForzaCntDis;
	private BigDecimal numDisabiliInForza;
	private BigDecimal numLavoratoriBaseComputo;
	private BigDecimal numLavoratoriSospensione;
	private BigDecimal numPosizioniEsonerate;
	private BigDecimal numScopertCatProtReali;
	private BigDecimal numScopertCategorieProtette;
	private BigDecimal numScopertDisabili;
	private BigDecimal numScopertDisabiliReali;
	private BigDecimal quotaEsuberiArt18;
	private BigDecimal quotaRiservaArt18;
	private BigDecimal quotaRiservaDisabili;
	private Prospetto prospetto;

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
	 * @return the numCatProtInForzaCntDis
	 */
	public BigDecimal getNumCatProtInForzaCntDis() {
		return numCatProtInForzaCntDis;
	}
	
	/**
	 * @param numCatProtInForzaCntDis the numCatProtInForzaCntDis to set
	 */
	public void setNumCatProtInForzaCntDis(BigDecimal numCatProtInForzaCntDis) {
		this.numCatProtInForzaCntDis = numCatProtInForzaCntDis;
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
	 * @return the numScopertCatProtReali
	 */
	public BigDecimal getNumScopertCatProtReali() {
		return numScopertCatProtReali;
	}
	
	/**
	 * @param numScopertCatProtReali the numScopertCatProtReali to set
	 */
	public void setNumScopertCatProtReali(BigDecimal numScopertCatProtReali) {
		this.numScopertCatProtReali = numScopertCatProtReali;
	}

	/**
	 * @return the numScopertCategorieProtette
	 */
	public BigDecimal getNumScopertCategorieProtette() {
		return numScopertCategorieProtette;
	}
	
	/**
	 * @param numScopertCategorieProtette the numScopertCategorieProtette to set
	 */
	public void setNumScopertCategorieProtette(BigDecimal numScopertCategorieProtette) {
		this.numScopertCategorieProtette = numScopertCategorieProtette;
	}

	/**
	 * @return the numScopertDisabili
	 */
	public BigDecimal getNumScopertDisabili() {
		return numScopertDisabili;
	}
	
	/**
	 * @param numScopertDisabili the numScopertDisabili to set
	 */
	public void setNumScopertDisabili(BigDecimal numScopertDisabili) {
		this.numScopertDisabili = numScopertDisabili;
	}

	/**
	 * @return the numScopertDisabiliReali
	 */
	public BigDecimal getNumScopertDisabiliReali() {
		return numScopertDisabiliReali;
	}
	
	/**
	 * @param numScopertDisabiliReali the numScopertDisabiliReali to set
	 */
	public void setNumScopertDisabiliReali(BigDecimal numScopertDisabiliReali) {
		this.numScopertDisabiliReali = numScopertDisabiliReali;
	}

	/**
	 * @return the quotaEsuberiArt18
	 */
	public BigDecimal getQuotaEsuberiArt18() {
		return quotaEsuberiArt18;
	}
	
	/**
	 * @param quotaEsuberiArt18 the quotaEsuberiArt18 to set
	 */
	public void setQuotaEsuberiArt18(BigDecimal quotaEsuberiArt18) {
		this.quotaEsuberiArt18 = quotaEsuberiArt18;
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
	 * @return the prospetto
	 */
	public Prospetto getProspetto() {
		return prospetto;
	}
	
	/**
	 * @param prospetto the prospetto to set
	 */
	public void setProspetto(Prospetto prospetto) {
		this.prospetto = prospetto;
	}

}
