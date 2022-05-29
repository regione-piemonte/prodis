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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_LAVORATORI_SILP database table.
 * 
 */
@Entity
@Table(name="PRO_D_LAVORATORI_SILP")
@NamedQuery(name="ProDLavoratoriSilp.findAll", query="SELECT p FROM ProDLavoratoriSilp p")
public class ProDLavoratoriSilp implements Serializable, BaseEntity<ProDLavoratoriSilpPK> {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProDLavoratoriSilpPK id;

	@Column(name="CATEGORIA_ASSUNZIONE")
	private String categoriaAssunzione;

	@Column(name="CATEGORIA_SOGGETTO")
	private String categoriaSoggetto;

	private String cognome;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE_RAPPORTO")
	private Date dataFineRapporto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO_RAPPORTO")
	private Date dataInizioRapporto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NASCITA")
	private Date dataNascita;

	@Column(name="FLG_IMPORT_ONLINE")
	private String flgImportOnline;

	private String nome;

	@Column(name="ORARIO_SETT_CONTRATTUALE_MIN")
	private BigDecimal orarioSettContrattualeMin;

	@Column(name="ORARIO_SETT_PART_TIME_MIN")
	private BigDecimal orarioSettPartTimeMin;

	private String sesso;

	//bi-directional many-to-one association to ProTAssunzioneProtetta
	@ManyToOne
	@JoinColumn(name="ID_T_ASSUNZIONE_PROTETTA")
	private ProTAssunzioneProtetta proTAssunzioneProtetta;

	//bi-directional many-to-one association to ProTComune
	@ManyToOne
	@JoinColumn(name="ID_T_COMUNE_NASCITA")
	private ProTComune proTComune;

	//bi-directional many-to-one association to ProTContratti
	@ManyToOne
	@JoinColumn(name="ID_T_CONTRATTO")
	private ProTContratti proTContratti;

	//bi-directional many-to-one association to ProTIstat2001livello5
	@ManyToOne
	@JoinColumn(name="ID_T_QUALIFICA_PROFESSIONALE_I")
	private ProTIstat2001livello5 proTIstat2001livello5;

	//bi-directional many-to-one association to ProTProvincia
//	Caused by: org.hibernate.MappingException: Repeated column in mapping for entity: it.csi.prodis.prodissrv.ejb.entity.ProDLavoratoriSilp column: ID_T_PROVINCIA (should be mapped with insert="false" update="false")
//	@ManyToOne
//	@JoinColumn(name="ID_T_PROVINCIA")
//	private ProTProvincia proTProvincia;

	//bi-directional many-to-one association to ProTStatiEsteri
	@ManyToOne
	@JoinColumn(name="ID_T_STATO_ESTERO_NASCITA")
	private ProTStatiEsteri proTStatiEsteri;

	public ProDLavoratoriSilp() {
	}

	@Override
	public ProDLavoratoriSilpPK getId() {
		return this.id;
	}

	@Override
	public void setId(ProDLavoratoriSilpPK id) {
		this.id = id;
	}

	public String getCategoriaAssunzione() {
		return this.categoriaAssunzione;
	}

	public void setCategoriaAssunzione(String categoriaAssunzione) {
		this.categoriaAssunzione = categoriaAssunzione;
	}

	public String getCategoriaSoggetto() {
		return this.categoriaSoggetto;
	}

	public void setCategoriaSoggetto(String categoriaSoggetto) {
		this.categoriaSoggetto = categoriaSoggetto;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataFineRapporto() {
		return this.dataFineRapporto;
	}

	public void setDataFineRapporto(Date dataFineRapporto) {
		this.dataFineRapporto = dataFineRapporto;
	}

	public Date getDataInizioRapporto() {
		return this.dataInizioRapporto;
	}

	public void setDataInizioRapporto(Date dataInizioRapporto) {
		this.dataInizioRapporto = dataInizioRapporto;
	}

	public Date getDataNascita() {
		return this.dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getFlgImportOnline() {
		return this.flgImportOnline;
	}

	public void setFlgImportOnline(String flgImportOnline) {
		this.flgImportOnline = flgImportOnline;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getOrarioSettContrattualeMin() {
		return this.orarioSettContrattualeMin;
	}

	public void setOrarioSettContrattualeMin(BigDecimal orarioSettContrattualeMin) {
		this.orarioSettContrattualeMin = orarioSettContrattualeMin;
	}

	public BigDecimal getOrarioSettPartTimeMin() {
		return this.orarioSettPartTimeMin;
	}

	public void setOrarioSettPartTimeMin(BigDecimal orarioSettPartTimeMin) {
		this.orarioSettPartTimeMin = orarioSettPartTimeMin;
	}

	public String getSesso() {
		return this.sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public ProTAssunzioneProtetta getProTAssunzioneProtetta() {
		return this.proTAssunzioneProtetta;
	}

	public void setProTAssunzioneProtetta(ProTAssunzioneProtetta proTAssunzioneProtetta) {
		this.proTAssunzioneProtetta = proTAssunzioneProtetta;
	}

	public ProTComune getProTComune() {
		return this.proTComune;
	}

	public void setProTComune(ProTComune proTComune) {
		this.proTComune = proTComune;
	}

	public ProTContratti getProTContratti() {
		return this.proTContratti;
	}

	public void setProTContratti(ProTContratti proTContratti) {
		this.proTContratti = proTContratti;
	}

	public ProTIstat2001livello5 getProTIstat2001livello5() {
		return this.proTIstat2001livello5;
	}

	public void setProTIstat2001livello5(ProTIstat2001livello5 proTIstat2001livello5) {
		this.proTIstat2001livello5 = proTIstat2001livello5;
	}

//	public ProTProvincia getProTProvincia() {
//		return this.proTProvincia;
//	}
//
//	public void setProTProvincia(ProTProvincia proTProvincia) {
//		this.proTProvincia = proTProvincia;
//	}

	public ProTStatiEsteri getProTStatiEsteri() {
		return this.proTStatiEsteri;
	}

	public void setProTStatiEsteri(ProTStatiEsteri proTStatiEsteri) {
		this.proTStatiEsteri = proTStatiEsteri;
	}

}
