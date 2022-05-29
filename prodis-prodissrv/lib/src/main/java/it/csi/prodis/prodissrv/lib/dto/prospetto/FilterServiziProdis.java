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

public class FilterServiziProdis {

	private String caller;
	private String codiceRegionale;
	private Date dataRiferimentoDa;
	private Date dataRiferimentoA;
	private String denominazioneAzienda;
	private List<String> listCodiceFiscaleAzienda;
	private String codProvinciaMin;
	private String tipoComunicazione;

	/* */
	private Long idProspetto;

	/**
	 * @return the caller
	 */
	public String getCaller() {
		return caller;
	}

	/**
	 * @param caller the caller to set
	 */
	public void setCaller(String caller) {
		this.caller = caller;
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
	 * @return the dataRiferimentoDa
	 */
	public Date getDataRiferimentoDa() {
		return dataRiferimentoDa;
	}

	/**
	 * @param dataRiferimentoDa the dataRiferimentoDa to set
	 */
	public void setDataRiferimentoDa(Date dataRiferimentoDa) {
		this.dataRiferimentoDa = dataRiferimentoDa;
	}

	/**
	 * @return the dataRiferimentoA
	 */
	public Date getDataRiferimentoA() {
		return dataRiferimentoA;
	}

	/**
	 * @param dataRiferimentoA the dataRiferimentoA to set
	 */
	public void setDataRiferimentoA(Date dataRiferimentoA) {
		this.dataRiferimentoA = dataRiferimentoA;
	}

	/**
	 * @return the denominazioneAzienda
	 */
	public String getDenominazioneAzienda() {
		return denominazioneAzienda;
	}

	/**
	 * @param denominazioneAzienda the denominazioneAzienda to set
	 */
	public void setDenominazioneAzienda(String denominazioneAzienda) {
		this.denominazioneAzienda = denominazioneAzienda;
	}

	/**
	 * @return the listCodiceFiscaleAzienda
	 */
	public List<String> getListCodiceFiscaleAzienda() {
		return listCodiceFiscaleAzienda;
	}

	/**
	 * @param listCodiceFiscaleAzienda the listCodiceFiscaleAzienda to set
	 */
	public void setListCodiceFiscaleAzienda(List<String> listCodiceFiscaleAzienda) {
		this.listCodiceFiscaleAzienda = listCodiceFiscaleAzienda;
	}

	/**
	 * @return the codProvinciaMin
	 */
	public String getCodProvinciaMin() {
		return codProvinciaMin;
	}

	/**
	 * @param codProvinciaMin the codProvinciaMin to set
	 */
	public void setCodProvinciaMin(String codProvinciaMin) {
		this.codProvinciaMin = codProvinciaMin;
	}

	public String getTipoComunicazione() {
		return tipoComunicazione;
	}

	public void setTipoComunicazione(String tipoComunicazione) {
		this.tipoComunicazione = tipoComunicazione;
	}

	public Long getIdProspetto() {
		return idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

}
