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
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Atecofin;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Ccnl;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Dichiarante;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;

/**
 * The Class DatiAzienda.
 */
public class DatiAzienda extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String capReferente;
	private String cfAzienda;
	private String cfCapogruppo;
	private String cfReferente;
	private String cognomeReferente;
	private String denominazioneDatoreLavoro;
	private String emailReferente;
	private String faxReferente;
	private String flgCapogruppoEstero;
	private String flgProspettoDaCapogruppo;
	private String indirizzoReferente;
	private String nomeReferente;
	private String telefonoReferente;
	private Prospetto prospetto;
	private Atecofin atecofin;
	private Ccnl ccnl;
	private Comune comune;
	private Dichiarante dichiarante;
	private StatiEsteri statiEsteri;
	private SedeLegale sedeLegale;
	private List<SedeLegale> elencoSedi;
	private String idAziendaSilp;

	/**
	 * @return the capReferente
	 */
	public String getCapReferente() {
		return capReferente;
	}

	/**
	 * @param capReferente the capReferente to set
	 */
	public void setCapReferente(String capReferente) {
		this.capReferente = capReferente;
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
	 * @return the cfCapogruppo
	 */
	public String getCfCapogruppo() {
		return cfCapogruppo;
	}

	/**
	 * @param cfCapogruppo the cfCapogruppo to set
	 */
	public void setCfCapogruppo(String cfCapogruppo) {
		this.cfCapogruppo = cfCapogruppo;
	}

	/**
	 * @return the cfReferente
	 */
	public String getCfReferente() {
		return cfReferente;
	}

	/**
	 * @param cfReferente the cfReferente to set
	 */
	public void setCfReferente(String cfReferente) {
		this.cfReferente = cfReferente;
	}

	/*
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
	 * @return the emailReferente
	 */
	public String getEmailReferente() {
		return emailReferente;
	}

	/**
	 * @param emailReferente the emailReferente to set
	 */
	public void setEmailReferente(String emailReferente) {
		this.emailReferente = emailReferente;
	}

	/**
	 * @return the faxReferente
	 */
	public String getFaxReferente() {
		return faxReferente;
	}

	/**
	 * @param faxReferente the faxReferente to set
	 */
	public void setFaxReferente(String faxReferente) {
		this.faxReferente = faxReferente;
	}

	/**
	 * @return the flgCapogruppoEstero
	 */
	public String getFlgCapogruppoEstero() {
		return flgCapogruppoEstero;
	}

	/**
	 * @param flgCapogruppoEstero the flgCapogruppoEstero to set
	 */
	public void setFlgCapogruppoEstero(String flgCapogruppoEstero) {
		this.flgCapogruppoEstero = flgCapogruppoEstero;
	}

	/**
	 * @return the flgProspettoDaCapogruppo
	 */
	public String getFlgProspettoDaCapogruppo() {
		return flgProspettoDaCapogruppo;
	}

	/**
	 * @param flgProspettoDaCapogruppo the flgProspettoDaCapogruppo to set
	 */
	public void setFlgProspettoDaCapogruppo(String flgProspettoDaCapogruppo) {
		this.flgProspettoDaCapogruppo = flgProspettoDaCapogruppo;
	}

	/**
	 * @return the indirizzoReferente
	 */
	public String getIndirizzoReferente() {
		return indirizzoReferente;
	}

	/**
	 * @param indirizzoReferente the indirizzoReferente to set
	 */
	public void setIndirizzoReferente(String indirizzoReferente) {
		this.indirizzoReferente = indirizzoReferente;
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
	 * @return the telefonoReferente
	 */
	public String getTelefonoReferente() {
		return telefonoReferente;
	}

	/**
	 * @param telefonoReferente the telefonoReferente to set
	 */
	public void setTelefonoReferente(String telefonoReferente) {
		this.telefonoReferente = telefonoReferente;
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
	 * @return the atecofin
	 */
	public Atecofin getAtecofin() {
		return atecofin;
	}

	/**
	 * @param atecofin the atecofin to set
	 */
	public void setAtecofin(Atecofin atecofin) {
		this.atecofin = atecofin;
	}

	/**
	 * @return the ccnl
	 */
	public Ccnl getCcnl() {
		return ccnl;
	}

	/**
	 * @param ccnl the ccnl to set
	 */
	public void setCcnl(Ccnl ccnl) {
		this.ccnl = ccnl;
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
	 * @return the dichiarante
	 */
	public Dichiarante getDichiarante() {
		return dichiarante;
	}

	/**
	 * @param dichiarante the dichiarante to set
	 */
	public void setDichiarante(Dichiarante dichiarante) {
		this.dichiarante = dichiarante;
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

	/**
	 * @return the sedeLegale
	 */
	public SedeLegale getSedeLegale() {
		return sedeLegale;
	}

	/**
	 * @param sedeLegale the sedeLegale to set
	 */
	public void setSedeLegale(SedeLegale sedeLegale) {
		this.sedeLegale = sedeLegale;
	}

	public String getIdAziendaSilp() {
		return idAziendaSilp;
	}

	public void setIdAziendaSilp(String idAziendaSilp) {
		this.idAziendaSilp = idAziendaSilp;
	}

	public List<SedeLegale> getElencoSedi() {
		return elencoSedi;
	}

	public void setElencoSedi(List<SedeLegale> elencoSedi) {
		this.elencoSedi = elencoSedi;
	}

}
