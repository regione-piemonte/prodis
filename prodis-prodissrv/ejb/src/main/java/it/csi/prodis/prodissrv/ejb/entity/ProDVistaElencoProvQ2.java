/*-
 * ========================LICENSE_START=================================
 * PRODIS Servizi - EJB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodissrv.ejb.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the VISTA_ELENCO_PROV_Q2 database table.
 * 
 */
@Entity
@Table(name="PRO_V_PROSPETTO_PROVINCIA")
@NamedQuery(name="ProDVistaElencoProvQ2.findAll", query="SELECT v FROM ProDVistaElencoProvQ2 v")
public class ProDVistaElencoProvQ2 implements Serializable, BaseEntity<Long> { // implements Serializable, BaseEntity<Long> {

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

	@Column(name="DS_PRO_T_PROVINCIA")
	private String dsProTProvincia;

	@Column(name="DS_TARGA")
	private String dsTarga;

	@Column(name="ID_PROSPETTO")
	private Long idProspetto;

	@Id
	@Column(name="ID_PROSPETTO_PROV")
	private Long idProspettoProv;

	@Column(name="ID_T_PROVINCIA")
	private Long idTProvincia;

	@Column(name="N_ASSUNZIONI_EFF_DOPO_TRASF")
	private BigDecimal nAssunzioniEffDopoTrasf;

	@Column(name="N_CATE_PROT_FORZA")
	private BigDecimal nCateProtForza;

	@Column(name="N_CATE_PROT_FORZA_A_17_01_2000")
	private BigDecimal nCateProtForzaA17012000;

	@Column(name="N_CENTRAL_TELEFO_NONVEDENTI")
	private BigDecimal nCentralTelefoNonvedenti;

	@Column(name="N_CONVENZIONI_12BIS_14_FT")
	private BigDecimal nConvenzioni12bis14Ft;

	@Column(name="N_DISABILI_IN_FORZA")
	private BigDecimal nDisabiliInForza;

	@Column(name="N_INTERMITTENTI")
	private BigDecimal nIntermittenti;

	@Column(name="N_LAV_APPARTART_CATEG_ART_18")
	private BigDecimal nLavAppartartCategArt18;

	@Column(name="N_LAV_APPARTART_CATEGORIA")
	private BigDecimal nLavAppartartCategoria;

	@Column(name="N_PART_TIME")
	private BigDecimal nPartTime;

	@Column(name="N_POSTI_DISP")
	private BigDecimal nPostiDisp;

	@Column(name="N_SOMMINISTRATI_FT")
	private BigDecimal nSomministratiFt;

	@Column(name="N_TELELAVORO_FT")
	private BigDecimal nTelelavoroFt;

	@Column(name="N_TERARIAB_MASSOFIS_NONVED")
	private BigDecimal nTerariabMassofisNonved;

	@Column(name="N_TOTALE_LAVORAT_DIPENDENTI")
	private BigDecimal nTotaleLavoratDipendenti;

	@Column(name="SOSP_IN_CORSO")
	private String sospInCorso;

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

	public BigDecimal getNAssunzioniEffDopoTrasf() {
		return this.nAssunzioniEffDopoTrasf;
	}

	public void setNAssunzioniEffDopoTrasf(BigDecimal nAssunzioniEffDopoTrasf) {
		this.nAssunzioniEffDopoTrasf = nAssunzioniEffDopoTrasf;
	}

	public BigDecimal getNCateProtForza() {
		return this.nCateProtForza;
	}

	public void setNCateProtForza(BigDecimal nCateProtForza) {
		this.nCateProtForza = nCateProtForza;
	}

	public BigDecimal getNCateProtForzaA17012000() {
		return this.nCateProtForzaA17012000;
	}

	public void setNCateProtForzaA17012000(BigDecimal nCateProtForzaA17012000) {
		this.nCateProtForzaA17012000 = nCateProtForzaA17012000;
	}

	public BigDecimal getNCentralTelefoNonvedenti() {
		return this.nCentralTelefoNonvedenti;
	}

	public void setNCentralTelefoNonvedenti(BigDecimal nCentralTelefoNonvedenti) {
		this.nCentralTelefoNonvedenti = nCentralTelefoNonvedenti;
	}

	public BigDecimal getNConvenzioni12bis14Ft() {
		return this.nConvenzioni12bis14Ft;
	}

	public void setNConvenzioni12bis14Ft(BigDecimal nConvenzioni12bis14Ft) {
		this.nConvenzioni12bis14Ft = nConvenzioni12bis14Ft;
	}

	public BigDecimal getNDisabiliInForza() {
		return this.nDisabiliInForza;
	}

	public void setNDisabiliInForza(BigDecimal nDisabiliInForza) {
		this.nDisabiliInForza = nDisabiliInForza;
	}

	public BigDecimal getNIntermittenti() {
		return this.nIntermittenti;
	}

	public void setNIntermittenti(BigDecimal nIntermittenti) {
		this.nIntermittenti = nIntermittenti;
	}

	public BigDecimal getNLavAppartartCategArt18() {
		return this.nLavAppartartCategArt18;
	}

	public void setNLavAppartartCategArt18(BigDecimal nLavAppartartCategArt18) {
		this.nLavAppartartCategArt18 = nLavAppartartCategArt18;
	}

	public BigDecimal getnLavAppartartCategoria() {
		return this.nLavAppartartCategoria;
	}

	public void setnLavAppartartCategoria(BigDecimal nLavAppartartCategoria) {
		this.nLavAppartartCategoria = nLavAppartartCategoria;
	}

	public BigDecimal getNPartTime() {
		return this.nPartTime;
	}

	public void setNPartTime(BigDecimal nPartTime) {
		this.nPartTime = nPartTime;
	}

	public BigDecimal getNPostiDisp() {
		return this.nPostiDisp;
	}

	public void setNPostiDisp(BigDecimal nPostiDisp) {
		this.nPostiDisp = nPostiDisp;
	}

	public BigDecimal getNSomministratiFt() {
		return this.nSomministratiFt;
	}

	public void setNSomministratiFt(BigDecimal nSomministratiFt) {
		this.nSomministratiFt = nSomministratiFt;
	}

	public BigDecimal getNTelelavoroFt() {
		return this.nTelelavoroFt;
	}

	public void setNTelelavoroFt(BigDecimal nTelelavoroFt) {
		this.nTelelavoroFt = nTelelavoroFt;
	}

	public BigDecimal getNTerariabMassofisNonved() {
		return this.nTerariabMassofisNonved;
	}

	public void setNTerariabMassofisNonved(BigDecimal nTerariabMassofisNonved) {
		this.nTerariabMassofisNonved = nTerariabMassofisNonved;
	}

	public BigDecimal getNTotaleLavoratDipendenti() {
		return this.nTotaleLavoratDipendenti;
	}

	public void setNTotaleLavoratDipendenti(BigDecimal nTotaleLavoratDipendenti) {
		this.nTotaleLavoratDipendenti = nTotaleLavoratDipendenti;
	}

	public String getSospInCorso() {
		return this.sospInCorso;
	}

	public void setSospInCorso(String sospInCorso) {
		this.sospInCorso = sospInCorso;
	}

}
