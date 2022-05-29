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
import it.csi.prodis.prodissrv.lib.dto.decodifiche.AssunzioneProtetta;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Contratti;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Provincia;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;

/**
 * The Class LavoratoriSilp.
 */
public class LavoratoriSilp extends BaseDto<LavoratoriSilpPK> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private LavoratoriSilpPK id;
	private String categoriaAssunzione;
	private String categoriaSoggetto;
	private String cognome;
	private Date dataFineRapporto;
	private Date dataInizioRapporto;
	private Date dataNascita;
	private String flgImportOnline;
	private String nome;
	private BigDecimal orarioSettContrattualeMin;
	private BigDecimal orarioSettPartTimeMin;
	private String sesso;
	private AssunzioneProtetta assunzioneProtetta;
	private Comune comune;
	private Contratti contratti;
	private Istat2001livello5 istat2001livello5;
	private Provincia provincia;
	private StatiEsteri statiEsteri;

	/**
	 * @return the id
	 */
	public LavoratoriSilpPK getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(LavoratoriSilpPK id) {
		this.id = id;
	}

	/**
	 * @return the categoriaAssunzione
	 */
	public String getCategoriaAssunzione() {
		return categoriaAssunzione;
	}
	
	/**
	 * @param categoriaAssunzione the categoriaAssunzione to set
	 */
	public void setCategoriaAssunzione(String categoriaAssunzione) {
		this.categoriaAssunzione = categoriaAssunzione;
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
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the dataFineRapporto
	 */
	public Date getDataFineRapporto() {
		return dataFineRapporto;
	}
	
	/**
	 * @param dataFineRapporto the dataFineRapporto to set
	 */
	public void setDataFineRapporto(Date dataFineRapporto) {
		this.dataFineRapporto = dataFineRapporto;
	}

	/**
	 * @return the dataInizioRapporto
	 */
	public Date getDataInizioRapporto() {
		return dataInizioRapporto;
	}
	
	/**
	 * @param dataInizioRapporto the dataInizioRapporto to set
	 */
	public void setDataInizioRapporto(Date dataInizioRapporto) {
		this.dataInizioRapporto = dataInizioRapporto;
	}

	/**
	 * @return the dataNascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}
	
	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the flgImportOnline
	 */
	public String getFlgImportOnline() {
		return flgImportOnline;
	}
	
	/**
	 * @param flgImportOnline the flgImportOnline to set
	 */
	public void setFlgImportOnline(String flgImportOnline) {
		this.flgImportOnline = flgImportOnline;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the orarioSettContrattualeMin
	 */
	public BigDecimal getOrarioSettContrattualeMin() {
		return orarioSettContrattualeMin;
	}
	
	/**
	 * @param orarioSettContrattualeMin the orarioSettContrattualeMin to set
	 */
	public void setOrarioSettContrattualeMin(BigDecimal orarioSettContrattualeMin) {
		this.orarioSettContrattualeMin = orarioSettContrattualeMin;
	}

	/**
	 * @return the orarioSettPartTimeMin
	 */
	public BigDecimal getOrarioSettPartTimeMin() {
		return orarioSettPartTimeMin;
	}
	
	/**
	 * @param orarioSettPartTimeMin the orarioSettPartTimeMin to set
	 */
	public void setOrarioSettPartTimeMin(BigDecimal orarioSettPartTimeMin) {
		this.orarioSettPartTimeMin = orarioSettPartTimeMin;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}
	
	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the assunzioneProtetta
	 */
	public AssunzioneProtetta getAssunzioneProtetta() {
		return assunzioneProtetta;
	}
	
	/**
	 * @param assunzioneProtetta the assunzioneProtetta to set
	 */
	public void setAssunzioneProtetta(AssunzioneProtetta assunzioneProtetta) {
		this.assunzioneProtetta = assunzioneProtetta;
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
	 * @return the contratti
	 */
	public Contratti getContratti() {
		return contratti;
	}
	
	/**
	 * @param contratti the contratti to set
	 */
	public void setContratti(Contratti contratti) {
		this.contratti = contratti;
	}

	/**
	 * @return the istat2001livello5
	 */
	public Istat2001livello5 getIstat2001livello5() {
		return istat2001livello5;
	}
	
	/**
	 * @param istat2001livello5 the istat2001livello5 to set
	 */
	public void setIstat2001livello5(Istat2001livello5 istat2001livello5) {
		this.istat2001livello5 = istat2001livello5;
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
