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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


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

	@Column(name="CODICE_FISCALE")
	private String codiceFiscale;

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

	@Column(name="FLG_COMPLETATO")
	private String flgCompletato;

	private String nome;

	@Column(name="ORARIO_SETT_CONTRATTUALE_MIN")
	private BigDecimal orarioSettContrattualeMin;

	@Column(name="ORARIO_SETT_PART_TIME_MIN")
	private BigDecimal orarioSettPartTimeMin;

	@Column(name="PERCENTUALE_DISABILITA")
	private BigDecimal percentualeDisabilita;

	private String sesso;

	//bi-directional many-to-one association to ProDDatiProvinciali
	@Column(name="ID_PROSPETTO_PROV")
	private BigDecimal idProspettoProv;

	//bi-directional many-to-one association to ProTAssunzioneProtetta
	@OneToOne
	@JoinColumn(name="ID_T_ASSUNZIONE_PROTETTA")
	private ProTAssunzioneProtetta proTAssunzioneProtetta;

	//bi-directional many-to-one association to ProTComune
	@OneToOne
	@JoinColumn(name="ID_T_COMUNE_NASCITA")
	private ProTComune proTComune;

	//bi-directional many-to-one association to ProTContratti
	@OneToOne
	@JoinColumn(name="ID_T_CONTRATTO")
	private ProTContratti proTContratti;

	//bi-directional many-to-one association to ProTIstat2001livello5
	@OneToOne
	@JoinColumn(name="ID_T_QUALIFICA_PROFESSIONALE_I")
	private ProTIstat2001livello5 proTIstat2001livello5;
	
	//bi-directional many-to-one association to ProTAssunzioneProtetta
	@OneToOne
	@JoinColumn(name="ID_T_STATO_ESTERO_NASCITA")
	private ProTStatiEsteri proTStatiEsteri;

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
	
	/*
	public Long getIdTStatoEsteroNascita() {
		return this.idTStatoEsteroNascita;
	}

	public void setIdTStatoEsteroNascita(Long idTStatoEsteroNascita) {
		this.idTStatoEsteroNascita = idTStatoEsteroNascita;
	}
	*/
	
	public String getNome() {
		return this.nome;
	}

	public ProTStatiEsteri getProTStatiEsteri() {
		return proTStatiEsteri;
	}

	public void setProTStatiEsteri(ProTStatiEsteri proTStatiEsteri) {
		this.proTStatiEsteri = proTStatiEsteri;
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

	public BigDecimal getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(BigDecimal idProspettoProv) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaAssunzione == null) ? 0 : categoriaAssunzione.hashCode());
		result = prime * result + ((categoriaSoggetto == null) ? 0 : categoriaSoggetto.hashCode());
		result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataFineRapporto == null) ? 0 : dataFineRapporto.hashCode());
		result = prime * result + ((dataInizioRapporto == null) ? 0 : dataInizioRapporto.hashCode());
		result = prime * result + ((dataNascita == null) ? 0 : dataNascita.hashCode());
		result = prime * result + ((flgCompletato == null) ? 0 : flgCompletato.hashCode());
		result = prime * result + (int) (idLavoratoriInForza ^ (idLavoratoriInForza >>> 32));
		result = prime * result + ((idProspettoProv == null) ? 0 : idProspettoProv.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((orarioSettContrattualeMin == null) ? 0 : orarioSettContrattualeMin.hashCode());
		result = prime * result + ((orarioSettPartTimeMin == null) ? 0 : orarioSettPartTimeMin.hashCode());
		result = prime * result + ((percentualeDisabilita == null) ? 0 : percentualeDisabilita.hashCode());
		result = prime * result + ((proTAssunzioneProtetta == null) ? 0 : proTAssunzioneProtetta.hashCode());
		result = prime * result + ((proTComune == null) ? 0 : proTComune.hashCode());
		result = prime * result + ((proTContratti == null) ? 0 : proTContratti.hashCode());
		result = prime * result + ((proTIstat2001livello5 == null) ? 0 : proTIstat2001livello5.hashCode());
		result = prime * result + ((proTStatiEsteri == null) ? 0 : proTStatiEsteri.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProDLavoratoriInForza other = (ProDLavoratoriInForza) obj;
		if (categoriaAssunzione == null) {
			if (other.categoriaAssunzione != null)
				return false;
		} else if (!categoriaAssunzione.equals(other.categoriaAssunzione))
			return false;
		if (categoriaSoggetto == null) {
			if (other.categoriaSoggetto != null)
				return false;
		} else if (!categoriaSoggetto.equals(other.categoriaSoggetto))
			return false;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (dataFineRapporto == null) {
			if (other.dataFineRapporto != null)
				return false;
		} else if (!dataFineRapporto.equals(other.dataFineRapporto))
			return false;
		if (dataInizioRapporto == null) {
			if (other.dataInizioRapporto != null)
				return false;
		} else if (!dataInizioRapporto.equals(other.dataInizioRapporto))
			return false;
		if (dataNascita == null) {
			if (other.dataNascita != null)
				return false;
		} else if (!dataNascita.equals(other.dataNascita))
			return false;
		if (flgCompletato == null) {
			if (other.flgCompletato != null)
				return false;
		} else if (!flgCompletato.equals(other.flgCompletato))
			return false;
		if (idLavoratoriInForza != other.idLavoratoriInForza)
			return false;
		if (idProspettoProv == null) {
			if (other.idProspettoProv != null)
				return false;
		} else if (!idProspettoProv.equals(other.idProspettoProv))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (orarioSettContrattualeMin == null) {
			if (other.orarioSettContrattualeMin != null)
				return false;
		} else if (!orarioSettContrattualeMin.equals(other.orarioSettContrattualeMin))
			return false;
		if (orarioSettPartTimeMin == null) {
			if (other.orarioSettPartTimeMin != null)
				return false;
		} else if (!orarioSettPartTimeMin.equals(other.orarioSettPartTimeMin))
			return false;
		if (percentualeDisabilita == null) {
			if (other.percentualeDisabilita != null)
				return false;
		} else if (!percentualeDisabilita.equals(other.percentualeDisabilita))
			return false;
		if (proTAssunzioneProtetta == null) {
			if (other.proTAssunzioneProtetta != null)
				return false;
		} else if (!proTAssunzioneProtetta.equals(other.proTAssunzioneProtetta))
			return false;
		if (proTComune == null) {
			if (other.proTComune != null)
				return false;
		} else if (!proTComune.equals(other.proTComune))
			return false;
		if (proTContratti == null) {
			if (other.proTContratti != null)
				return false;
		} else if (!proTContratti.equals(other.proTContratti))
			return false;
		if (proTIstat2001livello5 == null) {
			if (other.proTIstat2001livello5 != null)
				return false;
		} else if (!proTIstat2001livello5.equals(other.proTIstat2001livello5))
			return false;
		if (proTStatiEsteri == null) {
			if (other.proTStatiEsteri != null)
				return false;
		} else if (!proTStatiEsteri.equals(other.proTStatiEsteri))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		return true;
	}
	
	

}
