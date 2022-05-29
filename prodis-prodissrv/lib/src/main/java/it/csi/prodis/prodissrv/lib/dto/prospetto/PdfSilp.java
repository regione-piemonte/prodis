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
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;

/**
 * The Class PdfSilp.
 */
public class PdfSilp extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal annoProt;
	private String cfAzienda;
	private String codiceRegionale;
	private Date dataProt;
	private Date dataRiferimentoProspetto;
	private String denominazioneDatoreLavoro;
	private BigDecimal numProt;
	private byte[] prospettoPdf;
	private Provincia provincia;

	/**
	 * @return the annoProt
	 */
	public BigDecimal getAnnoProt() {
		return annoProt;
	}
	
	/**
	 * @param annoProt the annoProt to set
	 */
	public void setAnnoProt(BigDecimal annoProt) {
		this.annoProt = annoProt;
	}

	/**
	 * @return the cfAzienda
	 */
	public String getCfAzienda() {
		return cfAzienda;
	}
	
	/**
	 * @param cfAzienda the cfAzienda to set
	 */
	public void setCfAzienda(String cfAzienda) {
		this.cfAzienda = cfAzienda;
	}

	/**
	 * @return the codiceRegionale
	 */
	public String getCodiceRegionale() {
		return codiceRegionale;
	}
	
	/**
	 * @param codiceRegionale the codiceRegionale to set
	 */
	public void setCodiceRegionale(String codiceRegionale) {
		this.codiceRegionale = codiceRegionale;
	}

	/**
	 * @return the dataProt
	 */
	public Date getDataProt() {
		return dataProt;
	}
	
	/**
	 * @param dataProt the dataProt to set
	 */
	public void setDataProt(Date dataProt) {
		this.dataProt = dataProt;
	}

	/**
	 * @return the dataRiferimentoProspetto
	 */
	public Date getDataRiferimentoProspetto() {
		return dataRiferimentoProspetto;
	}
	
	/**
	 * @param dataRiferimentoProspetto the dataRiferimentoProspetto to set
	 */
	public void setDataRiferimentoProspetto(Date dataRiferimentoProspetto) {
		this.dataRiferimentoProspetto = dataRiferimentoProspetto;
	}

	/**
	 * @return the denominazioneDatoreLavoro
	 */
	public String getDenominazioneDatoreLavoro() {
		return denominazioneDatoreLavoro;
	}
	
	/**
	 * @param denominazioneDatoreLavoro the denominazioneDatoreLavoro to set
	 */
	public void setDenominazioneDatoreLavoro(String denominazioneDatoreLavoro) {
		this.denominazioneDatoreLavoro = denominazioneDatoreLavoro;
	}

	/**
	 * @return the numProt
	 */
	public BigDecimal getNumProt() {
		return numProt;
	}
	
	/**
	 * @param numProt the numProt to set
	 */
	public void setNumProt(BigDecimal numProt) {
		this.numProt = numProt;
	}

	/**
	 * @return the prospettoPdf
	 */
	public byte[] getProspettoPdf() {
		return prospettoPdf;
	}
	
	/**
	 * @param prospettoPdf the prospettoPdf to set
	 */
	public void setProspettoPdf(byte[] prospettoPdf) {
		this.prospettoPdf = prospettoPdf;
	}

	/**
	 * @return the provincia
	 */
	public Provincia getProvincia() {
		return provincia;
	}
	
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

}
