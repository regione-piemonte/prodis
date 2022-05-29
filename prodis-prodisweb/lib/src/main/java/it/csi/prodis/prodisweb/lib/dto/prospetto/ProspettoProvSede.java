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

import it.csi.prodis.prodisweb.lib.dto.BaseDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comune;

/**
 * The Class ProspettoProvSede.
 */
public class ProspettoProvSede extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String cap;
	private String codUserAggiorn;
	private String codUserInserim;
	private String cognomeReferente;
	private Date dAggiorn;
	private Date dInserim;
	private String email;
	private String fax;
	//private BigDecimal idTComune;
	private String indirizzo;
	private String nomeReferente;
	private String telefono;
	private DatiProvinciali datiProvinciali;
	private Comune comune;

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
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
	 * @return the cognomeReferente
	 */
	public String getCognomeReferente() {
		return cognomeReferente;
	}
	
	/**
	 * @param cognomeReferente the cognomeReferente to set
	 */
	public void setCognomeReferente(String cognomeReferente) {
		this.cognomeReferente = cognomeReferente;
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
	 * @return the idTComune
	 */
	/*
	public BigDecimal getIdTComune() {
		return idTComune;
	}*/
	
	/**
	 * @param idTComune the idTComune to set
	 */
	/*
	public void setIdTComune(BigDecimal idTComune) {
		this.idTComune = idTComune;
	}*/

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
	 * @return the nomeReferente
	 */
	public String getNomeReferente() {
		return nomeReferente;
	}
	
	/**
	 * @param nomeReferente the nomeReferente to set
	 */
	public void setNomeReferente(String nomeReferente) {
		this.nomeReferente = nomeReferente;
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
	 * @return the datiProvinciali
	 */
	public DatiProvinciali getDatiProvinciali() {
		return datiProvinciali;
	}
	
	/**
	 * @param datiProvinciali the datiProvinciali to set
	 */
	public void setDatiProvinciali(DatiProvinciali datiProvinciali) {
		this.datiProvinciali = datiProvinciali;
	}

	public Comune getComune() {
		return comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}
	
	
	
}
