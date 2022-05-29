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


import it.csi.prodis.prodissrv.lib.dto.BaseDto;

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
	private BigDecimal nAssunzioniEffDopoTrasf;
	private BigDecimal nCateProtForza;
	private BigDecimal nCateProtForzaA17012000;
	private BigDecimal nCentralTelefoNonvedenti;
	private BigDecimal nConvenzioni12bis14Ft;
	private BigDecimal nDisabiliInForza;
	private BigDecimal nIntermittenti;
	private BigDecimal nLavAppartartCategArt18;
	private BigDecimal nLavAppartartCategoria;
	private BigDecimal nPartTime;
	private BigDecimal nPostiDisp;
	private BigDecimal nSomministratiFt;
	private BigDecimal nTelelavoroFt;
	private BigDecimal nTerariabMassofisNonved;
	private BigDecimal nTotaleLavoratDipendenti;
	private String sospInCorso;
	
	/*campi non in tabella*/
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
	 * @return the nAssunzioniEffDopoTrasf
	 */
	public BigDecimal getNAssunzioniEffDopoTrasf() {
		return nAssunzioniEffDopoTrasf;
	}
	
	/**
	 * @param nAssunzioniEffDopoTrasf the nAssunzioniEffDopoTrasf to set
	 */
	public void setNAssunzioniEffDopoTrasf(BigDecimal nAssunzioniEffDopoTrasf) {
		this.nAssunzioniEffDopoTrasf = nAssunzioniEffDopoTrasf;
	}

	/**
	 * @return the nCateProtForza
	 */
	public BigDecimal getNCateProtForza() {
		return nCateProtForza;
	}
	
	/**
	 * @param nCateProtForza the nCateProtForza to set
	 */
	public void setNCateProtForza(BigDecimal nCateProtForza) {
		this.nCateProtForza = nCateProtForza;
	}

	/**
	 * @return the nCateProtForzaA17012000
	 */
	public BigDecimal getNCateProtForzaA17012000() {
		return nCateProtForzaA17012000;
	}
	
	/**
	 * @param nCateProtForzaA17012000 the nCateProtForzaA17012000 to set
	 */
	public void setNCateProtForzaA17012000(BigDecimal nCateProtForzaA17012000) {
		this.nCateProtForzaA17012000 = nCateProtForzaA17012000;
	}

	/**
	 * @return the nCentralTelefoNonvedenti
	 */
	public BigDecimal getNCentralTelefoNonvedenti() {
		return nCentralTelefoNonvedenti;
	}
	
	/**
	 * @param nCentralTelefoNonvedenti the nCentralTelefoNonvedenti to set
	 */
	public void setNCentralTelefoNonvedenti(BigDecimal nCentralTelefoNonvedenti) {
		this.nCentralTelefoNonvedenti = nCentralTelefoNonvedenti;
	}

	/**
	 * @return the nConvenzioni12bis14Ft
	 */
	public BigDecimal getNConvenzioni12bis14Ft() {
		return nConvenzioni12bis14Ft;
	}
	
	/**
	 * @param nConvenzioni12bis14Ft the nConvenzioni12bis14Ft to set
	 */
	public void setNConvenzioni12bis14Ft(BigDecimal nConvenzioni12bis14Ft) {
		this.nConvenzioni12bis14Ft = nConvenzioni12bis14Ft;
	}

	/**
	 * @return the nDisabiliInForza
	 */
	public BigDecimal getNDisabiliInForza() {
		return nDisabiliInForza;
	}
	
	/**
	 * @param nDisabiliInForza the nDisabiliInForza to set
	 */
	public void setNDisabiliInForza(BigDecimal nDisabiliInForza) {
		this.nDisabiliInForza = nDisabiliInForza;
	}

	/**
	 * @return the nIntermittenti
	 */
	public BigDecimal getNIntermittenti() {
		return nIntermittenti;
	}
	
	/**
	 * @param nIntermittenti the nIntermittenti to set
	 */
	public void setNIntermittenti(BigDecimal nIntermittenti) {
		this.nIntermittenti = nIntermittenti;
	}

	/**
	 * @return the nLavAppartartCategArt18
	 */
	public BigDecimal getNLavAppartartCategArt18() {
		return nLavAppartartCategArt18;
	}
	
	/**
	 * @param nLavAppartartCategArt18 the nLavAppartartCategArt18 to set
	 */
	public void setNLavAppartartCategArt18(BigDecimal nLavAppartartCategArt18) {
		this.nLavAppartartCategArt18 = nLavAppartartCategArt18;
	}

	/**
	 * @return the nLavAppartartCategoria
	 */
	public BigDecimal getNLavAppartartCategoria() {
		return nLavAppartartCategoria;
	}
	
	/**
	 * @param nLavAppartartCategoria the nLavAppartartCategoria to set
	 */
	public void setNLavAppartartCategoria(BigDecimal nLavAppartartCategoria) {
		this.nLavAppartartCategoria = nLavAppartartCategoria;
	}

	/**
	 * @return the nPartTime
	 */
	public BigDecimal getNPartTime() {
		return nPartTime;
	}
	
	/**
	 * @param nPartTime the nPartTime to set
	 */
	public void setNPartTime(BigDecimal nPartTime) {
		this.nPartTime = nPartTime;
	}

	/**
	 * @return the nPostiDisp
	 */
	public BigDecimal getNPostiDisp() {
		return nPostiDisp;
	}
	
	/**
	 * @param nPostiDisp the nPostiDisp to set
	 */
	public void setNPostiDisp(BigDecimal nPostiDisp) {
		this.nPostiDisp = nPostiDisp;
	}

	/**
	 * @return the nSomministratiFt
	 */
	public BigDecimal getNSomministratiFt() {
		return nSomministratiFt;
	}
	
	/**
	 * @param nSomministratiFt the nSomministratiFt to set
	 */
	public void setNSomministratiFt(BigDecimal nSomministratiFt) {
		this.nSomministratiFt = nSomministratiFt;
	}

	/**
	 * @return the nTelelavoroFt
	 */
	public BigDecimal getNTelelavoroFt() {
		return nTelelavoroFt;
	}
	
	/**
	 * @param nTelelavoroFt the nTelelavoroFt to set
	 */
	public void setNTelelavoroFt(BigDecimal nTelelavoroFt) {
		this.nTelelavoroFt = nTelelavoroFt;
	}

	/**
	 * @return the nTerariabMassofisNonved
	 */
	public BigDecimal getNTerariabMassofisNonved() {
		return nTerariabMassofisNonved;
	}
	
	/**
	 * @param nTerariabMassofisNonved the nTerariabMassofisNonved to set
	 */
	public void setNTerariabMassofisNonved(BigDecimal nTerariabMassofisNonved) {
		this.nTerariabMassofisNonved = nTerariabMassofisNonved;
	}

	/**
	 * @return the nTotaleLavoratDipendenti
	 */
	public BigDecimal getNTotaleLavoratDipendenti() {
		return nTotaleLavoratDipendenti;
	}
	
	/**
	 * @param nTotaleLavoratDipendenti the nTotaleLavoratDipendenti to set
	 */
	public void setNTotaleLavoratDipendenti(BigDecimal nTotaleLavoratDipendenti) {
		this.nTotaleLavoratDipendenti = nTotaleLavoratDipendenti;
	}

	/**
	 * @return the sospInCorso
	 */
	public String getSospInCorso() {
		return sospInCorso;
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
	
	

}
