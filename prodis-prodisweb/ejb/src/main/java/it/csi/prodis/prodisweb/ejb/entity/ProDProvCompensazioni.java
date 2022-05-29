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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodisweb.ejb.entity.base.BaseAuditedEntity;


/**
 * The persistent class for the PRO_D_PROV_COMPENSAZIONI database table.
 * 
 */
@Entity
@Table(name="PRO_D_PROV_COMPENSAZIONI")
@NamedQuery(name="ProDProvCompensazioni.findAll", query="SELECT p FROM ProDProvCompensazioni p")
@SequenceGenerator(name = "COMPENSAZIONI_SEQUENCE", sequenceName = "SEQ_D_PROV_COMPENSAZIONI", initialValue = 1, allocationSize = 1)
public class ProDProvCompensazioni extends BaseAuditedEntity<Long> implements Serializable {

	@Override
	public Long getId() {
		return idCompensazione;
	}

	@Override
	public void setId(Long id) {
		idCompensazione = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COMPENSAZIONE", unique=true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPENSAZIONI_SEQUENCE")
	private long idCompensazione;

	@Column(name="CATEGORIA_COMPENSAZIONE")
	private String categoriaCompensazione;

	@Column(name="CATEGORIA_SOGGETTO")
	private String categoriaSoggetto;

	@Column(name="CF_AZIENDA_APPARTEN_AL_GRUPPO")
	private String cfAziendaAppartenAlGruppo;

	@Column(name="COD_USER_AGGIORN")
	private String codUserAggiorn;

	@Column(name="COD_USER_INSERIM")
	private String codUserInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="D_AGGIORN")
	private Date dAggiorn;

	@Temporal(TemporalType.DATE)
	@Column(name="D_INSERIM")
	private Date dInserim;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ATTO")
	private Date dataAtto;

	@Column(name="ESTREMI_ATTO")
	private String estremiAtto;

	@Column(name="FLG_AUTOCOMPENSAZIONE")
	private String flgAutocompensazione;

	@Column(name="N_LAVORATORI")
	private BigDecimal nLavoratori;

	//bi-directional many-to-one association to ProDDatiProvinciali
	@Column(name="ID_PROSPETTO_PROV")
	private BigDecimal idProspettoProv;

	//bi-directional many-to-one association to ProTProvincia
	@ManyToOne
	@JoinColumn(name="ID_T_PROVINCIA_COMP")
	private ProTProvincia proTProvincia;

	//bi-directional many-to-one association to ProTStatoConcessione
	@ManyToOne
	@JoinColumn(name="ID_T_STATO_CONCESSIONE")
	private ProTStatoConcessione proTStatoConcessione;

	public BigDecimal getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(BigDecimal idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

	public ProDProvCompensazioni() {
	}

	public long getIdCompensazione() {
		return this.idCompensazione;
	}

	public void setIdCompensazione(long idCompensazione) {
		this.idCompensazione = idCompensazione;
	}

	public String getCategoriaCompensazione() {
		return this.categoriaCompensazione;
	}

	public void setCategoriaCompensazione(String categoriaCompensazione) {
		this.categoriaCompensazione = categoriaCompensazione;
	}

	public String getCategoriaSoggetto() {
		return this.categoriaSoggetto;
	}

	public void setCategoriaSoggetto(String categoriaSoggetto) {
		this.categoriaSoggetto = categoriaSoggetto;
	}

	public String getCfAziendaAppartenAlGruppo() {
		return this.cfAziendaAppartenAlGruppo;
	}

	public void setCfAziendaAppartenAlGruppo(String cfAziendaAppartenAlGruppo) {
		this.cfAziendaAppartenAlGruppo = cfAziendaAppartenAlGruppo;
	}

	public String getCodUserAggiorn() {
		return this.codUserAggiorn;
	}

	public void setCodUserAggiorn(String codUserAggiorn) {
		this.codUserAggiorn = codUserAggiorn;
	}

	public String getCodUserInserim() {
		return this.codUserInserim;
	}

	public void setCodUserInserim(String codUserInserim) {
		this.codUserInserim = codUserInserim;
	}

	public Date getDAggiorn() {
		return this.dAggiorn;
	}

	public void setDAggiorn(Date dAggiorn) {
		this.dAggiorn = dAggiorn;
	}

	public Date getDInserim() {
		return this.dInserim;
	}

	public void setDInserim(Date dInserim) {
		this.dInserim = dInserim;
	}

	public Date getDataAtto() {
		return this.dataAtto;
	}

	public void setDataAtto(Date dataAtto) {
		this.dataAtto = dataAtto;
	}

	public String getEstremiAtto() {
		return this.estremiAtto;
	}

	public void setEstremiAtto(String estremiAtto) {
		this.estremiAtto = estremiAtto;
	}

	public String getFlgAutocompensazione() {
		return this.flgAutocompensazione;
	}

	public void setFlgAutocompensazione(String flgAutocompensazione) {
		this.flgAutocompensazione = flgAutocompensazione;
	}

	public BigDecimal getnLavoratori() {
		return this.nLavoratori;
	}

	public void setnLavoratori(BigDecimal nLavoratori) {
		this.nLavoratori = nLavoratori;
	}

	public ProTProvincia getProTProvincia() {
		return this.proTProvincia;
	}

	public void setProTProvincia(ProTProvincia proTProvincia) {
		this.proTProvincia = proTProvincia;
	}

	public ProTStatoConcessione getProTStatoConcessione() {
		return this.proTStatoConcessione;
	}

	public void setProTStatoConcessione(ProTStatoConcessione proTStatoConcessione) {
		this.proTStatoConcessione = proTStatoConcessione;
	}

}
