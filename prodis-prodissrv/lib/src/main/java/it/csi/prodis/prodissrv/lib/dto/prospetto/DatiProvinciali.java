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
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseAuditedDto;

/**
 * The Class DatiProvinciali.
 */
public class DatiProvinciali extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal baseComputo;
	private String flgAziConConvenzione;
	private String flgAziEsoneroParziale;
	private String flgAziGradu;
	private String flgAziSosp;
	private String flgCategorieEscluseCompute;
	private String flgCompensazioniTerritorial;
	private String flgDettaglioIntermittenti;
	private String flgDettaglioPartime;
	private String flgLavoratoriInForza;
	private String flgPostiLavoroDisponibili;
	private BigDecimal nCateProtForza;
	private BigDecimal nCateProtForzaA17012000;
	private String nCateProtForzaCntDis;
	private String nCateProtForzaEsubero;
	private BigDecimal nCentralTelefoNonvedenti;
	private BigDecimal nConvenzioni12bis14Ft;
	private BigDecimal nDisabiliInForza;
	private BigDecimal nIntermittentiRiproporziona;
	private BigDecimal nPartimeRiproporzionati;
	private BigDecimal nPostiPrevCentraliNonved;
	private BigDecimal nPostiPrevMassofisNonved;
	private BigDecimal nSomministratiFt;
	private BigDecimal nTelelavoroFt;
	private BigDecimal nTerariabMassofisNonved;
	private BigDecimal nTotaleLavoratDipendenti;
	private String note;
	private List<CategorieEscluse> categorieEscluses;
	private ProspettoProvincia prospettoProvincia;
	private List<LavoratoriInForza> lavoratoriInForzas;
	private List<PartTime> partTimes;
	private List<PostiLavoroDisp> postiLavoroDisps;
	private ProspettoProvSede prospettoProvSede;
	private List<ProvCompensazioni> provCompensazionis;
	private ProvConvenzione provConvenzione;
	private ProvEsonero provEsonero;
	private ProvEsoneroAutocert provEsoneroAutocert;
	private ProvGradualita provGradualita;
	private List<ProvIntermittenti> provIntermittentis;
	private ProvSospensione provSospensione;

	// campo aggiunto perche' calcolato
	private String flgAutocompensazioni;

	/**
	 * @return the baseComputo
	 */
	public BigDecimal getBaseComputo() {
		return baseComputo;
	}

	/**
	 * @param baseComputo the baseComputo to set
	 */
	public void setBaseComputo(BigDecimal baseComputo) {
		this.baseComputo = baseComputo;
	}

	/**
	 * @return the flgAziConConvenzione
	 */
	public String getFlgAziConConvenzione() {
		return flgAziConConvenzione;
	}

	/**
	 * @param flgAziConConvenzione the flgAziConConvenzione to set
	 */
	public void setFlgAziConConvenzione(String flgAziConConvenzione) {
		this.flgAziConConvenzione = flgAziConConvenzione;
	}

	/**
	 * @return the flgAziEsoneroParziale
	 */
	public String getFlgAziEsoneroParziale() {
		return flgAziEsoneroParziale;
	}

	/**
	 * @param flgAziEsoneroParziale the flgAziEsoneroParziale to set
	 */
	public void setFlgAziEsoneroParziale(String flgAziEsoneroParziale) {
		this.flgAziEsoneroParziale = flgAziEsoneroParziale;
	}

	/**
	 * @return the flgAziGradu
	 */
	public String getFlgAziGradu() {
		return flgAziGradu;
	}

	/**
	 * @param flgAziGradu the flgAziGradu to set
	 */
	public void setFlgAziGradu(String flgAziGradu) {
		this.flgAziGradu = flgAziGradu;
	}

	/**
	 * @return the flgAziSosp
	 */
	public String getFlgAziSosp() {
		return flgAziSosp;
	}

	/**
	 * @param flgAziSosp the flgAziSosp to set
	 */
	public void setFlgAziSosp(String flgAziSosp) {
		this.flgAziSosp = flgAziSosp;
	}

	/**
	 * @return the flgCategorieEscluseCompute
	 */
	public String getFlgCategorieEscluseCompute() {
		return flgCategorieEscluseCompute;
	}

	/**
	 * @param flgCategorieEscluseCompute the flgCategorieEscluseCompute to set
	 */
	public void setFlgCategorieEscluseCompute(String flgCategorieEscluseCompute) {
		this.flgCategorieEscluseCompute = flgCategorieEscluseCompute;
	}

	/**
	 * @return the flgCompensazioniTerritorial
	 */
	public String getFlgCompensazioniTerritorial() {
		return flgCompensazioniTerritorial;
	}

	/**
	 * @param flgCompensazioniTerritorial the flgCompensazioniTerritorial to set
	 */
	public void setFlgCompensazioniTerritorial(String flgCompensazioniTerritorial) {
		this.flgCompensazioniTerritorial = flgCompensazioniTerritorial;
	}

	/**
	 * @return the flgDettaglioIntermittenti
	 */
	public String getFlgDettaglioIntermittenti() {
		return flgDettaglioIntermittenti;
	}

	/**
	 * @param flgDettaglioIntermittenti the flgDettaglioIntermittenti to set
	 */
	public void setFlgDettaglioIntermittenti(String flgDettaglioIntermittenti) {
		this.flgDettaglioIntermittenti = flgDettaglioIntermittenti;
	}

	/**
	 * @return the flgDettaglioPartime
	 */
	public String getFlgDettaglioPartime() {
		return flgDettaglioPartime;
	}

	/**
	 * @param flgDettaglioPartime the flgDettaglioPartime to set
	 */
	public void setFlgDettaglioPartime(String flgDettaglioPartime) {
		this.flgDettaglioPartime = flgDettaglioPartime;
	}

	/**
	 * @return the flgLavoratoriInForza
	 */
	public String getFlgLavoratoriInForza() {
		return flgLavoratoriInForza;
	}

	/**
	 * @param flgLavoratoriInForza the flgLavoratoriInForza to set
	 */
	public void setFlgLavoratoriInForza(String flgLavoratoriInForza) {
		this.flgLavoratoriInForza = flgLavoratoriInForza;
	}

	/**
	 * @return the flgPostiLavoroDisponibili
	 */
	public String getFlgPostiLavoroDisponibili() {
		return flgPostiLavoroDisponibili;
	}

	/**
	 * @param flgPostiLavoroDisponibili the flgPostiLavoroDisponibili to set
	 */
	public void setFlgPostiLavoroDisponibili(String flgPostiLavoroDisponibili) {
		this.flgPostiLavoroDisponibili = flgPostiLavoroDisponibili;
	}

	/**
	 * @return the nCateProtForza
	 */
	public BigDecimal getnCateProtForza() {
		return nCateProtForza;
	}

	/**
	 * @param nCateProtForza the nCateProtForza to set
	 */
	public void setnCateProtForza(BigDecimal nCateProtForza) {
		this.nCateProtForza = nCateProtForza;
	}

	/**
	 * @return the nCateProtForzaA17012000
	 */
	public BigDecimal getnCateProtForzaA17012000() {
		return nCateProtForzaA17012000;
	}

	/**
	 * @param nCateProtForzaA17012000 the nCateProtForzaA17012000 to set
	 */
	public void setnCateProtForzaA17012000(BigDecimal nCateProtForzaA17012000) {
		this.nCateProtForzaA17012000 = nCateProtForzaA17012000;
	}

	/**
	 * @return the nCateProtForzaCntDis
	 */
	public String getNCateProtForzaCntDis() {
		return nCateProtForzaCntDis;
	}

	/**
	 * @param nCateProtForzaCntDis the nCateProtForzaCntDis to set
	 */
	public void setnCateProtForzaCntDis(String nCateProtForzaCntDis) {
		this.nCateProtForzaCntDis = nCateProtForzaCntDis;
	}

	/**
	 * @return the nCateProtForzaEsubero
	 */
	public String getNCateProtForzaEsubero() {
		return nCateProtForzaEsubero;
	}

	/**
	 * @param nCateProtForzaEsubero the nCateProtForzaEsubero to set
	 */
	public void setnCateProtForzaEsubero(String nCateProtForzaEsubero) {
		this.nCateProtForzaEsubero = nCateProtForzaEsubero;
	}

	/**
	 * @return the nCentralTelefoNonvedenti
	 */
	public BigDecimal getnCentralTelefoNonvedenti() {
		return nCentralTelefoNonvedenti;
	}

	/**
	 * @param nCentralTelefoNonvedenti the nCentralTelefoNonvedenti to set
	 */
	public void setnCentralTelefoNonvedenti(BigDecimal nCentralTelefoNonvedenti) {
		this.nCentralTelefoNonvedenti = nCentralTelefoNonvedenti;
	}

	/**
	 * @return the nConvenzioni12bis14Ft
	 */
	public BigDecimal getnConvenzioni12bis14Ft() {
		return nConvenzioni12bis14Ft;
	}

	/**
	 * @param nConvenzioni12bis14Ft the nConvenzioni12bis14Ft to set
	 */
	public void setnConvenzioni12bis14Ft(BigDecimal nConvenzioni12bis14Ft) {
		this.nConvenzioni12bis14Ft = nConvenzioni12bis14Ft;
	}

	/**
	 * @return the nDisabiliInForza
	 */
	public BigDecimal getnDisabiliInForza() {
		return nDisabiliInForza;
	}

	/**
	 * @param nDisabiliInForza the nDisabiliInForza to set
	 */
	public void setnDisabiliInForza(BigDecimal nDisabiliInForza) {
		this.nDisabiliInForza = nDisabiliInForza;
	}

	/**
	 * @return the nIntermittentiRiproporziona
	 */
	public BigDecimal getnIntermittentiRiproporziona() {
		return nIntermittentiRiproporziona;
	}

	/**
	 * @param nIntermittentiRiproporziona the nIntermittentiRiproporziona to set
	 */
	public void setnIntermittentiRiproporziona(BigDecimal nIntermittentiRiproporziona) {
		this.nIntermittentiRiproporziona = nIntermittentiRiproporziona;
	}

	/**
	 * @return the nPartimeRiproporzionati
	 */
	public BigDecimal getnPartimeRiproporzionati() {
		return nPartimeRiproporzionati;
	}

	/**
	 * @param nPartimeRiproporzionati the nPartimeRiproporzionati to set
	 */
	public void setnPartimeRiproporzionati(BigDecimal nPartimeRiproporzionati) {
		this.nPartimeRiproporzionati = nPartimeRiproporzionati;
	}

	/**
	 * @return the nPostiPrevCentraliNonved
	 */
	public BigDecimal getnPostiPrevCentraliNonved() {
		return nPostiPrevCentraliNonved;
	}

	/**
	 * @param nPostiPrevCentraliNonved the nPostiPrevCentraliNonved to set
	 */
	public void setnPostiPrevCentraliNonved(BigDecimal nPostiPrevCentraliNonved) {
		this.nPostiPrevCentraliNonved = nPostiPrevCentraliNonved;
	}

	/**
	 * @return the nPostiPrevMassofisNonved
	 */
	public BigDecimal getnPostiPrevMassofisNonved() {
		return nPostiPrevMassofisNonved;
	}

	/**
	 * @param nPostiPrevMassofisNonved the nPostiPrevMassofisNonved to set
	 */
	public void setnPostiPrevMassofisNonved(BigDecimal nPostiPrevMassofisNonved) {
		this.nPostiPrevMassofisNonved = nPostiPrevMassofisNonved;
	}

	/**
	 * @return the nSomministratiFt
	 */
	public BigDecimal getnSomministratiFt() {
		return nSomministratiFt;
	}

	/**
	 * @param nSomministratiFt the nSomministratiFt to set
	 */
	public void setnSomministratiFt(BigDecimal nSomministratiFt) {
		this.nSomministratiFt = nSomministratiFt;
	}

	/**
	 * @return the nTelelavoroFt
	 */
	public BigDecimal getnTelelavoroFt() {
		return nTelelavoroFt;
	}

	/**
	 * @param nTelelavoroFt the nTelelavoroFt to set
	 */
	public void setnTelelavoroFt(BigDecimal nTelelavoroFt) {
		this.nTelelavoroFt = nTelelavoroFt;
	}

	/**
	 * @return the nTerariabMassofisNonved
	 */
	public BigDecimal getnTerariabMassofisNonved() {
		return nTerariabMassofisNonved;
	}

	/**
	 * @param nTerariabMassofisNonved the nTerariabMassofisNonved to set
	 */
	public void setnTerariabMassofisNonved(BigDecimal nTerariabMassofisNonved) {
		this.nTerariabMassofisNonved = nTerariabMassofisNonved;
	}

	/**
	 * @return the nTotaleLavoratDipendenti
	 */
	public BigDecimal getnTotaleLavoratDipendenti() {
		return nTotaleLavoratDipendenti;
	}

	/**
	 * @param nTotaleLavoratDipendenti the nTotaleLavoratDipendenti to set
	 */
	public void setnTotaleLavoratDipendenti(BigDecimal nTotaleLavoratDipendenti) {
		this.nTotaleLavoratDipendenti = nTotaleLavoratDipendenti;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the categorieEscluses
	 */
	public List<CategorieEscluse> getCategorieEscluses() {
		return categorieEscluses;
	}

	/**
	 * @param categorieEscluses the categorieEscluses to set
	 */
	public void setCategorieEscluses(List<CategorieEscluse> categorieEscluses) {
		this.categorieEscluses = categorieEscluses;
	}

	/**
	 * @return the prospettoProvincia
	 */
	public ProspettoProvincia getProspettoProvincia() {
		return prospettoProvincia;
	}

	/**
	 * @param prospettoProvincia the prospettoProvincia to set
	 */
	public void setProspettoProvincia(ProspettoProvincia prospettoProvincia) {
		this.prospettoProvincia = prospettoProvincia;
	}

	/**
	 * @return the lavoratoriInForzas
	 */
	public List<LavoratoriInForza> getLavoratoriInForzas() {
		return lavoratoriInForzas;
	}

	/**
	 * @param lavoratoriInForzas the lavoratoriInForzas to set
	 */
	public void setLavoratoriInForzas(List<LavoratoriInForza> lavoratoriInForzas) {
		this.lavoratoriInForzas = lavoratoriInForzas;
	}

	/**
	 * @return the partTimes
	 */
	public List<PartTime> getPartTimes() {
		return partTimes;
	}

	/**
	 * @param partTimes the partTimes to set
	 */
	public void setPartTimes(List<PartTime> partTimes) {
		this.partTimes = partTimes;
	}

	/**
	 * @return the postiLavoroDisps
	 */
	public List<PostiLavoroDisp> getPostiLavoroDisps() {
		return postiLavoroDisps;
	}

	/**
	 * @param postiLavoroDisps the postiLavoroDisps to set
	 */
	public void setPostiLavoroDisps(List<PostiLavoroDisp> postiLavoroDisps) {
		this.postiLavoroDisps = postiLavoroDisps;
	}

	/**
	 * @return the prospettoProvSede
	 */
	public ProspettoProvSede getProspettoProvSede() {
		return prospettoProvSede;
	}

	/**
	 * @param prospettoProvSede the prospettoProvSede to set
	 */
	public void setProspettoProvSede(ProspettoProvSede prospettoProvSede) {
		this.prospettoProvSede = prospettoProvSede;
	}

	/**
	 * @return the provCompensazionis
	 */
	public List<ProvCompensazioni> getProvCompensazionis() {
		return provCompensazionis;
	}

	/**
	 * @param provCompensazionis the provCompensazionis to set
	 */
	public void setProvCompensazionis(List<ProvCompensazioni> provCompensazionis) {
		this.provCompensazionis = provCompensazionis;
	}

	/**
	 * @return the provConvenzione
	 */
	public ProvConvenzione getProvConvenzione() {
		return provConvenzione;
	}

	/**
	 * @param provConvenzione the provConvenzione to set
	 */
	public void setProvConvenzione(ProvConvenzione provConvenzione) {
		this.provConvenzione = provConvenzione;
	}

	/**
	 * @return the provEsonero
	 */
	public ProvEsonero getProvEsonero() {
		return provEsonero;
	}

	/**
	 * @param provEsonero the provEsonero to set
	 */
	public void setProvEsonero(ProvEsonero provEsonero) {
		this.provEsonero = provEsonero;
	}

	/**
	 * @return the provEsoneroAutocert
	 */
	public ProvEsoneroAutocert getProvEsoneroAutocert() {
		return provEsoneroAutocert;
	}

	/**
	 * @param provEsoneroAutocert the provEsoneroAutocert to set
	 */
	public void setProvEsoneroAutocert(ProvEsoneroAutocert provEsoneroAutocert) {
		this.provEsoneroAutocert = provEsoneroAutocert;
	}

	/**
	 * @return the provGradualita
	 */
	public ProvGradualita getProvGradualita() {
		return provGradualita;
	}

	/**
	 * @param provGradualita the provGradualita to set
	 */
	public void setProvGradualita(ProvGradualita provGradualita) {
		this.provGradualita = provGradualita;
	}

	/**
	 * @return the provIntermittentis
	 */
	public List<ProvIntermittenti> getProvIntermittentis() {
		return provIntermittentis;
	}

	/**
	 * @param provIntermittentis the provIntermittentis to set
	 */
	public void setProvIntermittentis(List<ProvIntermittenti> provIntermittentis) {
		this.provIntermittentis = provIntermittentis;
	}

	/**
	 * @return the provSospensione
	 */
	public ProvSospensione getProvSospensione() {
		return provSospensione;
	}

	/**
	 * @param provSospensione the provSospensione to set
	 */
	public void setProvSospensione(ProvSospensione provSospensione) {
		this.provSospensione = provSospensione;
	}

	public String getFlgAutocompensazioni() {
		return flgAutocompensazioni;
	}

	public void setFlgAutocompensazioni(String flgAutocompensazioni) {
		this.flgAutocompensazioni = flgAutocompensazioni;
	}
}
