/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseEntity;

/**
 * The persistent class for the VISTA_ELENCO_PROV_Q2 database table.
 * 
 */
@Entity
@Table(name = "PRO_V_PROSPETTO_PROVINCIA")
@NamedQuery(name = "ProDVistaElencoProvQ2.findAll", query = "SELECT v FROM ProDVistaElencoProvQ2 v")
public class ProDVistaElencoProvQ2 implements Serializable, BaseEntity<Long> { // implements Serializable,
																				// BaseEntity<Long> {

	private static final long serialVersionUID = 1L;

	@Override
	public Long getId() {
		return idProspettoProv;
	}

	@Override
	public void setId(Long id) {
		idProspettoProv = id;
	}

	private String convenzione;

	@Column(name = "DS_PRO_T_PROVINCIA")
	private String dsProTProvincia;

	@Column(name = "DS_TARGA")
	private String dsTarga;

	@Column(name = "ID_PROSPETTO")
	private Long idProspetto;

	@Id
	@Column(name = "ID_PROSPETTO_PROV")
	private Long idProspettoProv;

	@Column(name = "ID_T_PROVINCIA")
	private Long idTProvincia;

	@Column(name = "N_ASSUNZIONI_EFF_DOPO_TRASF")
	private BigDecimal numAssunzioniEffDopoTrasf;

	@Column(name = "N_CATE_PROT_FORZA")
	private BigDecimal numCateProtForza;

	@Column(name = "N_CATE_PROT_FORZA_A_17_01_2000")
	private BigDecimal numCateProtForzaA17012000;

	@Column(name = "N_CENTRAL_TELEFO_NONVEDENTI")
	private BigDecimal numCentralTelefoNonvedenti;

	@Column(name = "N_CONVENZIONI_12BIS_14_FT")
	private BigDecimal numConvenzioni12bis14Ft;

	@Column(name = "N_DISABILI_IN_FORZA")
	private BigDecimal numDisabiliInForza;

	@Column(name = "N_INTERMITTENTI")
	private BigDecimal numIntermittenti;

	@Column(name = "N_LAV_APPARTART_CATEG_ART_18")
	private BigDecimal numLavAppartartCategArt18;

	@Column(name = "N_LAV_APPARTART_CATEGORIA")
	private BigDecimal numLavAppartartCategoria;

	@Column(name = "N_PART_TIME")
	private BigDecimal numPartTime;

	@Column(name = "N_POSTI_DISP")
	private BigDecimal numPostiDisp;

	@Column(name = "N_SOMMINISTRATI_FT")
	private BigDecimal numSomministratiFt;

	@Column(name = "N_TELELAVORO_FT")
	private BigDecimal numTelelavoroFt;

	@Column(name = "N_TERARIAB_MASSOFIS_NONVED")
	private BigDecimal numTerariabMassofisNonved;

	@Column(name = "N_TOTALE_LAVORAT_DIPENDENTI")
	private BigDecimal numTotaleLavoratDipendenti;

	@Column(name = "SOSP_IN_CORSO")
	private String sospInCorso;

	@Column(name = "FLG_CONFERMATO_Q2")
	private String flgConfermatoQ2;

	@Column(name = "N_LAVORATORI_ESONERO")
	private BigDecimal numLavoratoriEsonero;

	@Column(name = "N_TELELAVORO_PT")
	private BigDecimal numTelelavoroPt;

	public ProDVistaElencoProvQ2() {
	}

	public String getConvenzione() {
		return this.convenzione;
	}

	public void setConvenzione(String convenzione) {
		this.convenzione = convenzione;
	}

	public String getDsProTProvincia() {
		return this.dsProTProvincia;
	}

	public void setDsProTProvincia(String dsProTProvincia) {
		this.dsProTProvincia = dsProTProvincia;
	}

	public String getDsTarga() {
		return this.dsTarga;
	}

	public void setDsTarga(String dsTarga) {
		this.dsTarga = dsTarga;
	}

	public Long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(Long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public Long getIdProspettoProv() {
		return this.idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public Long getIdTProvincia() {
		return this.idTProvincia;
	}

	public void setIdTProvincia(Long idTProvincia) {
		this.idTProvincia = idTProvincia;
	}

	public String getSospInCorso() {
		return this.sospInCorso;
	}

	public void setSospInCorso(String sospInCorso) {
		this.sospInCorso = sospInCorso;
	}

	public BigDecimal getNumAssunzioniEffDopoTrasf() {
		return numAssunzioniEffDopoTrasf;
	}

	public void setNumAssunzioniEffDopoTrasf(BigDecimal numAssunzioniEffDopoTrasf) {
		this.numAssunzioniEffDopoTrasf = numAssunzioniEffDopoTrasf;
	}

	public BigDecimal getNumCateProtForza() {
		return numCateProtForza;
	}

	public void setNumCateProtForza(BigDecimal numCateProtForza) {
		this.numCateProtForza = numCateProtForza;
	}

	public BigDecimal getNumCateProtForzaA17012000() {
		return numCateProtForzaA17012000;
	}

	public void setNumCateProtForzaA17012000(BigDecimal numCateProtForzaA17012000) {
		this.numCateProtForzaA17012000 = numCateProtForzaA17012000;
	}

	public BigDecimal getNumCentralTelefoNonvedenti() {
		return numCentralTelefoNonvedenti;
	}

	public void setNumCentralTelefoNonvedenti(BigDecimal numCentralTelefoNonvedenti) {
		this.numCentralTelefoNonvedenti = numCentralTelefoNonvedenti;
	}

	public BigDecimal getNumConvenzioni12bis14Ft() {
		return numConvenzioni12bis14Ft;
	}

	public void setNumConvenzioni12bis14Ft(BigDecimal numConvenzioni12bis14Ft) {
		this.numConvenzioni12bis14Ft = numConvenzioni12bis14Ft;
	}

	public BigDecimal getNumDisabiliInForza() {
		return numDisabiliInForza;
	}

	public void setNumDisabiliInForza(BigDecimal numDisabiliInForza) {
		this.numDisabiliInForza = numDisabiliInForza;
	}

	public BigDecimal getNumIntermittenti() {
		return numIntermittenti;
	}

	public void setNumIntermittenti(BigDecimal numIntermittenti) {
		this.numIntermittenti = numIntermittenti;
	}

	public BigDecimal getNumLavAppartartCategArt18() {
		return numLavAppartartCategArt18;
	}

	public void setNumLavAppartartCategArt18(BigDecimal numLavAppartartCategArt18) {
		this.numLavAppartartCategArt18 = numLavAppartartCategArt18;
	}

	public BigDecimal getNumLavAppartartCategoria() {
		return numLavAppartartCategoria;
	}

	public void setNumLavAppartartCategoria(BigDecimal numLavAppartartCategoria) {
		this.numLavAppartartCategoria = numLavAppartartCategoria;
	}

	public BigDecimal getNumPartTime() {
		return numPartTime;
	}

	public void setNumPartTime(BigDecimal numPartTime) {
		this.numPartTime = numPartTime;
	}

	public BigDecimal getNumPostiDisp() {
		return numPostiDisp;
	}

	public void setNumPostiDisp(BigDecimal numPostiDisp) {
		this.numPostiDisp = numPostiDisp;
	}

	public BigDecimal getNumSomministratiFt() {
		return numSomministratiFt;
	}

	public void setNumSomministratiFt(BigDecimal numSomministratiFt) {
		this.numSomministratiFt = numSomministratiFt;
	}

	public BigDecimal getNumTelelavoroFt() {
		return numTelelavoroFt;
	}

	public void setNumTelelavoroFt(BigDecimal numTelelavoroFt) {
		this.numTelelavoroFt = numTelelavoroFt;
	}

	public BigDecimal getNumTerariabMassofisNonved() {
		return numTerariabMassofisNonved;
	}

	public void setNumTerariabMassofisNonved(BigDecimal numTerariabMassofisNonved) {
		this.numTerariabMassofisNonved = numTerariabMassofisNonved;
	}

	public BigDecimal getNumTotaleLavoratDipendenti() {
		return numTotaleLavoratDipendenti;
	}

	public void setNumTotaleLavoratDipendenti(BigDecimal numTotaleLavoratDipendenti) {
		this.numTotaleLavoratDipendenti = numTotaleLavoratDipendenti;
	}

	public String getFlgConfermatoQ2() {
		return flgConfermatoQ2;
	}

	public void setFlgConfermatoQ2(String flgConfermatoQ2) {
		this.flgConfermatoQ2 = flgConfermatoQ2;
	}

	public BigDecimal getNumLavoratoriEsonero() {
		return numLavoratoriEsonero;
	}

	public void setNumLavoratoriEsonero(BigDecimal numLavoratoriEsonero) {
		this.numLavoratoriEsonero = numLavoratoriEsonero;
	}

	public BigDecimal getNumTelelavoroPt() {
		return numTelelavoroPt;
	}

	public void setNumTelelavoroPt(BigDecimal numTelelavoroPt) {
		this.numTelelavoroPt = numTelelavoroPt;
	}

}
