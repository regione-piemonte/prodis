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
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Regione;

/**
 * The Class AssPubbliche.
 */
public class AssPubbliche extends BaseDto<AssPubblichePK> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private AssPubblichePK id;
	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private String dsNote;
	private BigDecimal saldoDisabili;
	private BigDecimal saldoExArt18;
	private Prospetto prospetto;
	private Regione regione;

	/**
	 * @return the id
	 */
	public AssPubblichePK getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(AssPubblichePK id) {
		this.id = id;
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
	 * @return the dsNote
	 */
	public String getDsNote() {
		return dsNote;
	}
	
	/**
	 * @param dsNote the dsNote to set
	 */
	public void setDsNote(String dsNote) {
		this.dsNote = dsNote;
	}

	/**
	 * @return the saldoDisabili
	 */
	public BigDecimal getSaldoDisabili() {
		return saldoDisabili;
	}
	
	/**
	 * @param saldoDisabili the saldoDisabili to set
	 */
	public void setSaldoDisabili(BigDecimal saldoDisabili) {
		this.saldoDisabili = saldoDisabili;
	}

	/**
	 * @return the saldoExArt18
	 */
	public BigDecimal getSaldoExArt18() {
		return saldoExArt18;
	}
	
	/**
	 * @param saldoExArt18 the saldoExArt18 to set
	 */
	public void setSaldoExArt18(BigDecimal saldoExArt18) {
		this.saldoExArt18 = saldoExArt18;
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

	/**
	 * @return the regione
	 */
	public Regione getRegione() {
		return regione;
	}
	
	/**
	 * @param regione the regione to set
	 */
	public void setRegione(Regione regione) {
		this.regione = regione;
	}

}
