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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseEntity;


/**
 * The persistent class for the PRO_D_PDF_SILP database table.
 * 
 */
@Entity
@Table(name="PRO_D_PDF_SILP")
@NamedQuery(name="ProDPdfSilp.findAll", query="SELECT p FROM ProDPdfSilp p")
public class ProDPdfSilp implements Serializable, BaseEntity<Long> {

	@Override
	public Long getId() {
		return idPdfSilp;
	}

	@Override
	public void setId(Long id) {
		idPdfSilp = id;
	}


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PDF_SILP")
	private long idPdfSilp;

	@Column(name="ANNO_PROT")
	private BigDecimal annoProt;

	@Column(name="CF_AZIENDA")
	private String cfAzienda;

	@Column(name="CODICE_REGIONALE")
	private String codiceRegionale;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_PROT")
	private Date dataProt;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_RIFERIMENTO_PROSPETTO")
	private Date dataRiferimentoProspetto;

	@Column(name="DENOMINAZIONE_DATORE_LAVORO")
	private String denominazioneDatoreLavoro;

	@Column(name="NUM_PROT")
	private BigDecimal numProt;

	@Lob
	@Column(name="PROSPETTO_PDF")
	private byte[] prospettoPdf;

	//bi-directional many-to-one association to ProTProvincia
	@ManyToOne
	@JoinColumn(name="ID_T_PROVINCIA_PROT")
	private ProTProvincia proTProvincia;

	public ProDPdfSilp() {
	}

	public long getIdPdfSilp() {
		return this.idPdfSilp;
	}

	public void setIdPdfSilp(long idPdfSilp) {
		this.idPdfSilp = idPdfSilp;
	}

	public BigDecimal getAnnoProt() {
		return this.annoProt;
	}

	public void setAnnoProt(BigDecimal annoProt) {
		this.annoProt = annoProt;
	}

	public String getCfAzienda() {
		return this.cfAzienda;
	}

	public void setCfAzienda(String cfAzienda) {
		this.cfAzienda = cfAzienda;
	}

	public String getCodiceRegionale() {
		return this.codiceRegionale;
	}

	public void setCodiceRegionale(String codiceRegionale) {
		this.codiceRegionale = codiceRegionale;
	}

	public Date getDataProt() {
		return this.dataProt;
	}

	public void setDataProt(Date dataProt) {
		this.dataProt = dataProt;
	}

	public Date getDataRiferimentoProspetto() {
		return this.dataRiferimentoProspetto;
	}

	public void setDataRiferimentoProspetto(Date dataRiferimentoProspetto) {
		this.dataRiferimentoProspetto = dataRiferimentoProspetto;
	}

	public String getDenominazioneDatoreLavoro() {
		return this.denominazioneDatoreLavoro;
	}

	public void setDenominazioneDatoreLavoro(String denominazioneDatoreLavoro) {
		this.denominazioneDatoreLavoro = denominazioneDatoreLavoro;
	}

	public BigDecimal getNumProt() {
		return this.numProt;
	}

	public void setNumProt(BigDecimal numProt) {
		this.numProt = numProt;
	}

	public byte[] getProspettoPdf() {
		return this.prospettoPdf;
	}

	public void setProspettoPdf(byte[] prospettoPdf) {
		this.prospettoPdf = prospettoPdf;
	}

	public ProTProvincia getProTProvincia() {
		return this.proTProvincia;
	}

	public void setProTProvincia(ProTProvincia proTProvincia) {
		this.proTProvincia = proTProvincia;
	}

}
