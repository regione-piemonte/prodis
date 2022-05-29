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

import java.util.Date;
import java.util.List;

public class TestataProspetto {

	private String cfAzienda;
	private String pivaAzienda;
	private String denominazioneDatoreDiLavoro;
	private String codiceRegionale;
	private Long idProspetto;
	private Date dataRiferimentoProspetto;
	private String descrizioneComunicazione;
	private List<String> elencoCodProvince;
	private String tipoComunicazione;
	private String tipoProvenienza;
	private Date dataTimbroPostale;
	private Date dataProtocollo;
	private Long annoProtocollo;
	private String numeroProtocolloRiferimento;
	private Long statoProspettoId;
	private String statoProspettoDescrizione;

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
	 * @return the pivaAzienda
	 */
	public String getPivaAzienda() {
		return pivaAzienda;
	}

	/**
	 * @param pivaAzienda the pivaAzienda to set
	 */
	public void setPivaAzienda(String pivaAzienda) {
		this.pivaAzienda = pivaAzienda;
	}

	/**
	 * @return the denominazioneDatoreDiLavoro
	 */
	public String getDenominazioneDatoreDiLavoro() {
		return denominazioneDatoreDiLavoro;
	}

	/**
	 * @param denominazioneDatoreDiLavoro the denominazioneDatoreDiLavoro to set
	 */
	public void setDenominazioneDatoreDiLavoro(String denominazioneDatoreDiLavoro) {
		this.denominazioneDatoreDiLavoro = denominazioneDatoreDiLavoro;
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
	 * @return the statoProspettoId
	 */
	public Long getStatoProspettoId() {
		return statoProspettoId;
	}

	/**
	 * @param statoProspettoId the statoProspettoId to set
	 */
	public void setStatoProspettoId(Long statoProspettoId) {
		this.statoProspettoId = statoProspettoId;
	}

	/**
	 * @return the statoProspettoDescrizione
	 */
	public String getStatoProspettoDescrizione() {
		return statoProspettoDescrizione;
	}

	/**
	 * @param statoProspettoDescrizione the statoProspettoDescrizione to set
	 */
	public void setStatoProspettoDescrizione(String statoProspettoDescrizione) {
		this.statoProspettoDescrizione = statoProspettoDescrizione;
	}

	/**
	 * @return the descrizioneComunicazione
	 */
	public String getDescrizioneComunicazione() {
		return descrizioneComunicazione;
	}

	/**
	 * @param descrizioneComunicazione the descrizioneComunicazione to set
	 */
	public void setDescrizioneComunicazione(String descrizioneComunicazione) {
		this.descrizioneComunicazione = descrizioneComunicazione;
	}

	/**
	 * @return the tipoProvenienza
	 */
	public String getTipoProvenienza() {
		return tipoProvenienza;
	}

	/**
	 * @param tipoProvenienza the tipoProvenienza to set
	 */
	public void setTipoProvenienza(String tipoProvenienza) {
		this.tipoProvenienza = tipoProvenienza;
	}

	/**
	 * @return the dataTimbroPostale
	 */
	public Date getDataTimbroPostale() {
		return dataTimbroPostale;
	}

	/**
	 * @param dataTimbroPostale the dataTimbroPostale to set
	 */
	public void setDataTimbroPostale(Date dataTimbroPostale) {
		this.dataTimbroPostale = dataTimbroPostale;
	}

	/**
	 * @return the dataProtocollo
	 */
	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	/**
	 * @param dataProtocollo the dataProtocollo to set
	 */
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	/**
	 * @return the annoProtocollo
	 */
	public Long getAnnoProtocollo() {
		return annoProtocollo;
	}

	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(Long annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}

	/**
	 * @return the numeroProtocolloRiferimento
	 */
	public String getNumeroProtocolloRiferimento() {
		return numeroProtocolloRiferimento;
	}

	/**
	 * @param numeroProtocolloRiferimento the numeroProtocolloRiferimento to set
	 */
	public void setNumeroProtocolloRiferimento(String numeroProtocolloRiferimento) {
		this.numeroProtocolloRiferimento = numeroProtocolloRiferimento;
	}

	public String getTipoComunicazione() {
		return tipoComunicazione;
	}

	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}

	public List<String> getElencoCodProvince() {
		return elencoCodProvince;
	}

	public void setElencoCodProvince(List<String> elencoCodProvince) {
		this.elencoCodProvince = elencoCodProvince;
	}

}
