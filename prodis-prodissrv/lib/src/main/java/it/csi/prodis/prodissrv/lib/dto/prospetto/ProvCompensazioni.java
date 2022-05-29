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
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatoConcessione;

/**
 * The Class ProvCompensazioni.
 */
public class ProvCompensazioni extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String categoriaCompensazione;
	private String categoriaSoggetto;
	private String cfAziendaAppartenAlGruppo;
	private String codUserAggiorn;
	private String codUserInserim;
	private Date dAggiorn;
	private Date dInserim;
	private Date dataAtto;
	private String estremiAtto;
	private String flgAutocompensazione;
	private BigDecimal nLavoratori;
	private Long idProspettoProv;
	private Provincia provincia;
	private StatoConcessione statoConcessione;

	/**
	 * @return the categoriaCompensazione
	 */
	public String getCategoriaCompensazione() {
		return categoriaCompensazione;
	}
	
	/**
	 * @param categoriaCompensazione the categoriaCompensazione to set
	 */
	public void setCategoriaCompensazione(String categoriaCompensazione) {
		this.categoriaCompensazione = categoriaCompensazione;
	}

	/**
	 * @return the categoriaSoggetto
	 */
	public String getCategoriaSoggetto() {
		return categoriaSoggetto;
	}
	
	/**
	 * @param categoriaSoggetto the categoriaSoggetto to set
	 */
	public void setCategoriaSoggetto(String categoriaSoggetto) {
		this.categoriaSoggetto = categoriaSoggetto;
	}

	/**
	 * @return the cfAziendaAppartenAlGruppo
	 */
	public String getCfAziendaAppartenAlGruppo() {
		return cfAziendaAppartenAlGruppo;
	}
	
	/**
	 * @param cfAziendaAppartenAlGruppo the cfAziendaAppartenAlGruppo to set
	 */
	public void setCfAziendaAppartenAlGruppo(String cfAziendaAppartenAlGruppo) {
		this.cfAziendaAppartenAlGruppo = cfAziendaAppartenAlGruppo;
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
	 * @return the dataAtto
	 */
	public Date getDataAtto() {
		return dataAtto;
	}
	
	/**
	 * @param dataAtto the dataAtto to set
	 */
	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	/**
	 * @return the estremiAtto
	 */
	public String getEstremiAtto() {
		return estremiAtto;
	}
	
	/**
	 * @param estremiAtto the estremiAtto to set
	 */
	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	/**
	 * @return the flgAutocompensazione
	 */
	public String getFlgAutocompensazione() {
		return flgAutocompensazione;
	}
	
	/**
	 * @param flgAutocompensazione the flgAutocompensazione to set
	 */
	public void setFlgAutocompensazione(String flgAutocompensazione) {
		this.flgAutocompensazione = flgAutocompensazione;
	}

	/**
	 * @return the nLavoratori
	 */
	public BigDecimal getnLavoratori() {
		return nLavoratori;
	}
	
	/**
	 * @param nLavoratori the nLavoratori to set
	 */
	public void setnLavoratori(BigDecimal nLavoratori) {
		this.nLavoratori = nLavoratori;
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

	/**
	 * @return the statoConcessione
	 */
	public StatoConcessione getStatoConcessione() {
		return statoConcessione;
	}
	
	/**
	 * @param statoConcessione the statoConcessione to set
	 */
	public void setStatoConcessione(StatoConcessione statoConcessione) {
		this.statoConcessione = statoConcessione;
	}

	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
