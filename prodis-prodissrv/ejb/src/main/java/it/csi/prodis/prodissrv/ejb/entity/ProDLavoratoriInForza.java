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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_LAVORATORI_IN_FORZA database table.
 * 
 */
@Entity
@Table(name="PRO_D_LAVORATORI_IN_FORZA")
@NamedQuery(name="ProDLavoratoriInForza.findAll", query="SELECT p FROM ProDLavoratoriInForza p")
@SequenceGenerator(name = "LAV_SEQUENCE", sequenceName = "SEQ_D_LAVORATORI_IN_FORZA", initialValue = 1, allocationSize = 1)
public class ProDLavoratoriInForza extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idLavoratoriInForza;
	}

	@Override
	public void setId(Long id) {
		idLavoratoriInForza = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_LAVORATORI_IN_FORZA", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LAV_SEQUENCE")
	private long idLavoratoriInForza;

	@Column(name="CATEGORIA_ASSUNZIONE")
	private String categoriaAssunzione;

	@Column(name="CATEGORIA_SOGGETTO")
	private String categoriaSoggetto;

//	@Column(name="COD_USER_AGGIORN")
//	private String codUserAggiorn;
//
//	@Column(name="COD_USER_INSERIM")
//	private String codUserInserim;

	@Column(name="CODICE_FISCALE")
	private String codiceFiscale;

	private String cognome;

//	@Temporal(TemporalType.DATE)
//	@Column(name="D_AGGIORN")
//	private Date dAggiorn;
//
//	@Temporal(TemporalType.DATE)
//	@Column(name="D_INSERIM")
//	private Date dInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FINE_RAPPORTO")
	private Date dataFineRapporto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_INIZIO_RAPPORTO")
	private Date dataInizioRapporto;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_NASCITA")
	private Date dataNascita;

	@Column(name="FLG_COMPLETATO")
	private String flgCompletato;

	@Column(name="ID_T_STATO_ESTERO_NASCITA")
	private Long idTStatoEsteroNascita;

	private String nome;

	@Column(name="ORARIO_SETT_CONTRATTUALE_MIN")
	private BigDecimal orarioSettContrattualeMin;

	@Column(name="ORARIO_SETT_PART_TIME_MIN")
	private BigDecimal orarioSettPartTimeMin;

	@Column(name="PERCENTUALE_DISABILITA")
	private BigDecimal percentualeDisabilita;

	private String sesso;

	@Column(name="ID_PROSPETTO_PROV")
	private Long idProspettoProv;

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

	public ProDLavoratoriInForza() {
	}

	public long getIdLavoratoriInForza() {
		return this.idLavoratoriInForza;
	}

	public void setIdLavoratoriInForza(long idLavoratoriInForza) {
		this.idLavoratoriInForza = idLavoratoriInForza;
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

//	public String getCodUserAggiorn() {
//		return this.codUserAggiorn;
//	}
//
//	public void setCodUserAggiorn(String codUserAggiorn) {
//		this.codUserAggiorn = codUserAggiorn;
//	}
//
//	public String getCodUserInserim() {
//		return this.codUserInserim;
//	}
//
//	public void setCodUserInserim(String codUserInserim) {
//		this.codUserInserim = codUserInserim;
//	}

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

//	public Date getDAggiorn() {
//		return this.dAggiorn;
//	}
//
//	public void setDAggiorn(Date dAggiorn) {
//		this.dAggiorn = dAggiorn;
//	}
//
//	public Date getDInserim() {
//		return this.dInserim;
//	}
//
//	public void setDInserim(Date dInserim) {
//		this.dInserim = dInserim;
//	}

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

	public String getFlgCompletato() {
		return this.flgCompletato;
	}

	public void setFlgCompletato(String flgCompletato) {
		this.flgCompletato = flgCompletato;
	}

	public Long getIdTStatoEsteroNascita() {
		return this.idTStatoEsteroNascita;
	}

	public void setIdTStatoEsteroNascita(Long idTStatoEsteroNascita) {
		this.idTStatoEsteroNascita = idTStatoEsteroNascita;
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

	public BigDecimal getPercentualeDisabilita() {
		return this.percentualeDisabilita;
	}

	public void setPercentualeDisabilita(BigDecimal percentualeDisabilita) {
		this.percentualeDisabilita = percentualeDisabilita;
	}

	public String getSesso() {
		return this.sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}


	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
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

}
