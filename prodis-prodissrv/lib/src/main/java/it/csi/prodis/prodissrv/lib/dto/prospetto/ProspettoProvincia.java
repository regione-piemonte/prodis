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
import java.util.Date;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;

/**
 * The Class ProspettoProvincia.
 */
public class ProspettoProvincia extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private String flgConfermatoQ2;
	private DatiProvinciali datiProvinciali;
	private RiepilogoProvinciale riepilogoProvinciale;
	private Long idProspetto;
	private Provincia provincia;

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
	 * @return the flgConfermatoQ2
	 */
	public String getFlgConfermatoQ2() {
		return flgConfermatoQ2;
	}
	
	/**
	 * @param flgConfermatoQ2 the flgConfermatoQ2 to set
	 */
	public void setFlgConfermatoQ2(String flgConfermatoQ2) {
		this.flgConfermatoQ2 = flgConfermatoQ2;
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

	/**
	 * @return the riepilogoProvinciale
	 */
	public RiepilogoProvinciale getRiepilogoProvinciale() {
		return riepilogoProvinciale;
	}
	
	/**
	 * @param riepilogoProvinciale the riepilogoProvinciale to set
	 */
	public void setRiepilogoProvinciale(RiepilogoProvinciale riepilogoProvinciale) {
		this.riepilogoProvinciale = riepilogoProvinciale;
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

	public Long getIdProspetto() {
		return idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

}
