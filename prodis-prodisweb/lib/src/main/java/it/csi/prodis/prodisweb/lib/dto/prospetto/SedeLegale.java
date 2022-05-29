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
import java.util.Date;

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatiEsteri;

/**
 * The Class SedeLegale.
 */
public class SedeLegale extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String capSede;
	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private String email;
	private String fax;
	private String indirizzo;
	private String telefono;
	private DatiAzienda datiAzienda;
	private Comune comune;
	private StatiEsteri statiEsteri;

	/**
	 * @return the capSede
	 */
	public String getCapSede() {
		return capSede;
	}
	
	/**
	 * @param capSede the capSede to set
	 */
	public void setCapSede(String capSede) {
		this.capSede = capSede;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the datiAzienda
	 */
	public DatiAzienda getDatiAzienda() {
		return datiAzienda;
	}
	
	/**
	 * @param datiAzienda the datiAzienda to set
	 */
	public void setDatiAzienda(DatiAzienda datiAzienda) {
		this.datiAzienda = datiAzienda;
	}

	/**
	 * @return the comune
	 */
	public Comune getComune() {
		return comune;
	}
	
	/**
	 * @param comune the comune to set
	 */
	public void setComune(Comune comune) {
		this.comune = comune;
	}

	/**
	 * @return the statiEsteri
	 */
	public StatiEsteri getStatiEsteri() {
		return statiEsteri;
	}
	
	/**
	 * @param statiEsteri the statiEsteri to set
	 */
	public void setStatiEsteri(StatiEsteri statiEsteri) {
		this.statiEsteri = statiEsteri;
	}

}
