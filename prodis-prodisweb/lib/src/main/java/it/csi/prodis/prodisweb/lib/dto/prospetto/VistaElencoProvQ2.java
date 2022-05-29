/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;

import it.csi.prodis.prodisweb.lib.dto.BaseDto;

/**
 * The Class VistaElencoProvQ2.
 */
public class VistaElencoProvQ2 extends BaseDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String convenzione;
	private String dsProTProvincia;
	private String dsTarga;
	private Long idProspetto;
	private Long idTProvincia;
	private BigDecimal numAssunzioniEffDopoTrasf;
	private BigDecimal numCateProtForza;
	private BigDecimal numCateProtForzaA17012000;
	private BigDecimal numCentralTelefoNonvedenti;
	private BigDecimal numConvenzioni12bis14Ft;
	private BigDecimal numDisabiliInForza;
	private BigDecimal numIntermittenti;
	private BigDecimal numLavAppartartCategArt18;
	private BigDecimal numLavAppartartCategoria;
	private BigDecimal numPartTime;
	private BigDecimal numPostiDisp;
	private BigDecimal numSomministratiFt;
	private BigDecimal numTelelavoroFt;
	private BigDecimal numTelelavoroPt;
	private BigDecimal numTerariabMassofisNonved;
	private BigDecimal numTotaleLavoratDipendenti;
	private String sospInCorso;
	private String flgConfermatoQ2;
	private BigDecimal numLavoratoriEsonero;

	/* campi non in tabella */
	private BigDecimal ptIntermittenti;
	private BigDecimal disabiliTempoPienoFt;
	private BigDecimal teleLavCatEscluseDisab;

	/**
	 * @return the convenzione
	 */
	public String getConvenzione() {
		return convenzione;
	}

	/**
	 * @param convenzione the convenzione to set
	 */
	public void setConvenzione(String convenzione) {
		this.convenzione = convenzione;
	}

	/**
	 * @return the dsProTProvincia
	 */
	public String getDsProTProvincia() {
		return dsProTProvincia;
	}

	/**
	 * @param dsProTProvincia the dsProTProvincia to set
	 */
	public void setDsProTProvincia(String dsProTProvincia) {
		this.dsProTProvincia = dsProTProvincia;
	}

	/**
	 * @return the dsTarga
	 */
	public String getDsTarga() {
		return dsTarga;
	}

	/**
	 * @param dsTarga the dsTarga to set
	 */
	public void setDsTarga(String dsTarga) {
		this.dsTarga = dsTarga;
	}

	/**
	 * @return the idProspetto
	 */
	public Long getIdProspetto() {
		return idProspetto;
	}

	/**
	 * @param idProspetto the idProspetto to set
	 */
	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	/**
	 * @return the idTProvincia
	 */
	public Long getIdTProvincia() {
		return idTProvincia;
	}

	/**
	 * @param idTProvincia the idTProvincia to set
	 */
	public void setIdTProvincia(Long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}

	/**
	 * @param sospInCorso the sospInCorso to set
	 */
	public void setSospInCorso(String sospInCorso) {
		this.sospInCorso = sospInCorso;
	}

	public BigDecimal getPtIntermittenti() {
		return ptIntermittenti;
	}

	public void setPtIntermittenti(BigDecimal ptIntermittenti) {
		this.ptIntermittenti = ptIntermittenti;
	}

	public BigDecimal getTeleLavCatEscluseDisab() {
		return teleLavCatEscluseDisab;
	}

	public void setTeleLavCatEscluseDisab(BigDecimal teleLavCatEscluseDisab) {
		this.teleLavCatEscluseDisab = teleLavCatEscluseDisab;
	}

	public BigDecimal getDisabiliTempoPienoFt() {
		return disabiliTempoPienoFt;
	}

	public void setDisabiliTempoPienoFt(BigDecimal disabiliTempoPienoFt) {
		this.disabiliTempoPienoFt = disabiliTempoPienoFt;
	}

	public BigDecimal getNumAssunzioniEffDopoTrasf() {
		return numAssunzioniEffDopoTrasf;
	}

	public void setNumAssunzioniEffDopoTrasf(BigDecimal numAssunzioniEffDopoTrasf) {
		this.numAssunzioniEffDopoTrasf = numAssunzioniEffDopoTrasf;
	}

	public BigDecimal getNumCateProtForza() {
		return numCateProtForza;
	}

	public void setNumCateProtForza(BigDecimal numCateProtForza) {
		this.numCateProtForza = numCateProtForza;
	}

	public BigDecimal getNumCateProtForzaA17012000() {
		return numCateProtForzaA17012000;
	}

	public void setNumCateProtForzaA17012000(BigDecimal numCateProtForzaA17012000) {
		this.numCateProtForzaA17012000 = numCateProtForzaA17012000;
	}

	public BigDecimal getNumCentralTelefoNonvedenti() {
		return numCentralTelefoNonvedenti;
	}

	public void setNumCentralTelefoNonvedenti(BigDecimal numCentralTelefoNonvedenti) {
		this.numCentralTelefoNonvedenti = numCentralTelefoNonvedenti;
	}

	public BigDecimal getNumConvenzioni12bis14Ft() {
		return numConvenzioni12bis14Ft;
	}

	public void setNumConvenzioni12bis14Ft(BigDecimal numConvenzioni12bis14Ft) {
		this.numConvenzioni12bis14Ft = numConvenzioni12bis14Ft;
	}

	public BigDecimal getNumDisabiliInForza() {
		return numDisabiliInForza;
	}

	public void setNumDisabiliInForza(BigDecimal numDisabiliInForza) {
		this.numDisabiliInForza = numDisabiliInForza;
	}

	public BigDecimal getNumIntermittenti() {
		return numIntermittenti;
	}

	public void setNumIntermittenti(BigDecimal numIntermittenti) {
		this.numIntermittenti = numIntermittenti;
	}

	public BigDecimal getNumLavAppartartCategArt18() {
		return numLavAppartartCategArt18;
	}

	public void setNumLavAppartartCategArt18(BigDecimal numLavAppartartCategArt18) {
		this.numLavAppartartCategArt18 = numLavAppartartCategArt18;
	}

	public BigDecimal getNumLavAppartartCategoria() {
		return numLavAppartartCategoria;
	}

	public void setNumLavAppartartCategoria(BigDecimal numLavAppartartCategoria) {
		this.numLavAppartartCategoria = numLavAppartartCategoria;
	}

	public BigDecimal getNumPartTime() {
		return numPartTime;
	}

	public void setNumPartTime(BigDecimal numPartTime) {
		this.numPartTime = numPartTime;
	}

	public BigDecimal getNumPostiDisp() {
		return numPostiDisp;
	}

	public void setNumPostiDisp(BigDecimal numPostiDisp) {
		this.numPostiDisp = numPostiDisp;
	}

	public BigDecimal getNumSomministratiFt() {
		return numSomministratiFt;
	}

	public void setNumSomministratiFt(BigDecimal numSomministratiFt) {
		this.numSomministratiFt = numSomministratiFt;
	}

	public BigDecimal getNumTelelavoroFt() {
		return numTelelavoroFt;
	}

	public void setNumTelelavoroFt(BigDecimal numTelelavoroFt) {
		this.numTelelavoroFt = numTelelavoroFt;
	}

	public BigDecimal getNumTerariabMassofisNonved() {
		return numTerariabMassofisNonved;
	}

	public void setNumTerariabMassofisNonved(BigDecimal numTerariabMassofisNonved) {
		this.numTerariabMassofisNonved = numTerariabMassofisNonved;
	}

	public BigDecimal getNumTotaleLavoratDipendenti() {
		return numTotaleLavoratDipendenti;
	}

	public void setNumTotaleLavoratDipendenti(BigDecimal numTotaleLavoratDipendenti) {
		this.numTotaleLavoratDipendenti = numTotaleLavoratDipendenti;
	}

	public String getFlgConfermatoQ2() {
		return flgConfermatoQ2;
	}

	public void setFlgConfermatoQ2(String flgConfermatoQ2) {
		this.flgConfermatoQ2 = flgConfermatoQ2;
	}

	public BigDecimal getNumLavoratoriEsonero() {
		return numLavoratoriEsonero;
	}

	public void setNumLavoratoriEsonero(BigDecimal numLavoratoriEsonero) {
		this.numLavoratoriEsonero = numLavoratoriEsonero;
	}

	public String getSospInCorso() {
		return sospInCorso;
	}

	public BigDecimal getNumTelelavoroPt() {
		return numTelelavoroPt;
	}

	public void setNumTelelavoroPt(BigDecimal numTelelavoroPt) {
		this.numTelelavoroPt = numTelelavoroPt;
	}

}
