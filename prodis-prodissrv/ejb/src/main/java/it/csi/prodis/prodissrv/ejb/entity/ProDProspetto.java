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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import it.csi.prodis.prodissrv.ejb.entity.base.BaseAuditedEntity;

/**
 * The persistent class for the PRO_D_PROSPETTO database table.
 * 
 */
@Entity
@Table(name = "PRO_D_PROSPETTO")
@NamedQuery(name = "ProDProspetto.findAll", query = "SELECT p FROM ProDProspetto p")
@SequenceGenerator(name = "NAME_SEQUENCE", sequenceName = "SEQ_D_PROSPETTO", initialValue = 1, allocationSize = 1)
public class ProDProspetto extends BaseAuditedEntity<Long> implements Serializable {
	public ProTSoggetti getProTSoggetti() {
		return proTSoggetti;
	}

	public void setProTSoggetti(ProTSoggetti proTSoggetti) {
		this.proTSoggetti = proTSoggetti;
	}

	public ProTStatoVerifica getProTStatoVerifica() {
		return proTStatoVerifica;
	}

	public void setProTStatoVerifica(ProTStatoVerifica proTStatoVerifica) {
		this.proTStatoVerifica = proTStatoVerifica;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Long getId() {
		return idProspetto;
	}

	@Override
	public void setId(Long id) {
		idProspetto = id;
	}

	@Id
	@Column(name = "ID_PROSPETTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NAME_SEQUENCE")
	private Long idProspetto;

	@Column(name = "ANNO_PROTOCOLLO")
	private BigDecimal annoProtocollo;

	@Column(name = "CF_COMUNICAZIONE")
	private String cfComunicazione;

	@Column(name = "CF_STUDIO_PROFESSIONALE")
	private String cfStudioProfessionale;

	@Column(name = "CODICE_COMUNICAZIONE")
	private String codiceComunicazione;

	@Column(name = "CODICE_COMUNICAZIONE_PRECED")
	private String codiceComunicazionePreced;

	@Temporal(TemporalType.DATE)
	@Column(name = "D_FINE_SOSPENSIONE_Q1")
	private Date dFineSospensioneQ1;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INVIO")
	private Date dataInvio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PRIMA_ASSUNZIONE")
	private Date dataPrimaAssunzione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PROTOCOLLO")
	private Date dataProtocollo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RIFERIMENTO_PROSPETTO")
	private Date dataRiferimentoProspetto;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_SECONDA_ASSUNZIONE")
	private Date dataSecondaAssunzione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_TIMBRO_POSTALE")
	private Date dataTimbroPostale;

	@Column(name = "EMAIL_NOTIFICA")
	private String emailNotifica;

	@Column(name = "EMAIL_SOGGETTO_COMUNICAZIONE")
	private String emailSoggettoComunicazione;

	@Column(name = "FLG_ASSUNZIONI_PUBB_SELEZIONE")
	private String flgAssunzioniPubbSelezione;

	@Column(name = "FLG_CONFERMATO_Q1")
	private String flgConfermatoQ1;

	@Column(name = "FLG_GRADUALITA")
	private String flgGradualita;

	@Column(name = "FLG_INVIO_MINISTERO")
	private String flgInvioMinistero;

	@Column(name = "FLG_NESSUNA_ASSUNZIONE_AGGIUN")
	private String flgNessunaAssunzioneAggiun;

	@Column(name = "FLG_SOSPENSIONE_PER_MOBILITA")
	private String flgSospensionePerMobilita;

	@Column(name = "FLG_VISITA_ISPETTIVA")
	private String flgVisitaIspettiva;

	private String note;

	@Column(name = "NUM_LAVOR_IN_FORZA_NAZIONALE")
	private BigDecimal numLavorInForzaNazionale;

	@Column(name = "NUMERO_PROTOCOLLO")
	private BigDecimal numeroProtocollo;

	@Column(name = "TIPO_PROVENIENZA")
	private String tipoProvenienza;

	// bi-directional many-to-one association to ProDAssPubbliche
	/*@OneToMany(mappedBy="proDProspetto")
	private List<ProDAssPubbliche> proDAssPubbliches;*/

	//  ATTENZIONE de-commentato perche' altrimenti non abbiamo il legame con l'azienda 
	// bi-directional one-to-one association to ProDDatiAzienda
	@OneToOne(mappedBy = "proDProspetto")
	private ProDDatiAzienda proDDatiAzienda;

	// bi-directional one-to-one association to ProDPdfProspetto
	// ATTENZIONE: questa relazione rallenta la query - nella tabella PDF c'è il
	// BLOB
//	@OneToOne(mappedBy="proDProspetto")
//	private ProDPdfProspetto proDPdfProspetto;

	// bi-directional many-to-one association to ProDProspetto
	@ManyToOne
	@JoinColumn(name = "ID_PROSPETTO_PRECEDENTE")
	private ProDProspetto proDProspetto;

	// bi-directional many-to-one association to ProDProspetto
//	@OneToMany(mappedBy="proDProspetto")
//	private List<ProDProspetto> proDProspettos;

	// bi-directional many-to-one association to ProTCategoriaAzienda
	@ManyToOne
	@JoinColumn(name = "ID_T_CATEGORIA_AZIENDA")
	private ProTCategoriaAzienda proTCategoriaAzienda;

	// bi-directional many-to-one association to ProTComunicazione
	@ManyToOne
	@JoinColumn(name = "ID_T_COMUNICAZIONE")
	private ProTComunicazione proTComunicazione;

	// bi-directional many-to-one association to ProTSoggetti
	@ManyToOne
	@JoinColumn(name = "ID_T_SOGGETTI")
	private ProTSoggetti proTSoggetti;

	// bi-directional many-to-one association to ProTStatoProspetto
	@ManyToOne
	@JoinColumn(name = "ID_T_STATO_PROSPETTO")
	private ProTStatoProspetto proTStatoProspetto;

	// bi-directional many-to-one association to ProTStatoVerifica
	@ManyToOne
	@JoinColumn(name = "ID_T_STATO_VERIFICA")
	private ProTStatoVerifica proTStatoVerifica;

	// bi-directional one-to-one association to ProDProspettoGradualita
//  ATTENZIONE de-commentato perche' altrimenti non abbiamo il legame per il primo quadro 
//	@OneToOne(mappedBy="proDProspetto")
//	private ProDProspettoGradualita proDProspettoGradualita;

	// bi-directional one-to-one association to ProDRiepilogoNazionale
// 	@OneToOne(mappedBy="proDProspetto")
// 	private ProDRiepilogoNazionale proDRiepilogoNazionale;

	// bi-directional many-to-one association to ProRProspettoProvincia
//	@OneToMany(mappedBy="proDProspetto")
//	private List<ProRProspettoProvincia> proRProspettoProvincias;

	public ProDProspetto() {
	}

	// @ManyToOne(cascade=CascadeType.ALL)
	public long getIdProspetto() {
		return this.idProspetto;
	}

	public void setIdProspetto(long idProspetto) {
		this.idProspetto = idProspetto;
	}

	public BigDecimal getAnnoProtocollo() {
		return this.annoProtocollo;
	}

	public void setAnnoProtocollo(BigDecimal annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}

	public String getCfComunicazione() {
		return this.cfComunicazione;
	}

	public void setCfComunicazione(String cfComunicazione) {
		this.cfComunicazione = cfComunicazione;
	}

	public String getCfStudioProfessionale() {
		return this.cfStudioProfessionale;
	}

	public void setCfStudioProfessionale(String cfStudioProfessionale) {
		this.cfStudioProfessionale = cfStudioProfessionale;
	}

	public String getCodiceComunicazione() {
		return this.codiceComunicazione;
	}

	public void setCodiceComunicazione(String codiceComunicazione) {
		this.codiceComunicazione = codiceComunicazione;
	}

	public String getCodiceComunicazionePreced() {
		return this.codiceComunicazionePreced;
	}

	public void setCodiceComunicazionePreced(String codiceComunicazionePreced) {
		this.codiceComunicazionePreced = codiceComunicazionePreced;
	}

	public Date getDFineSospensioneQ1() {
		return this.dFineSospensioneQ1;
	}

	public void setDFineSospensioneQ1(Date dFineSospensioneQ1) {
		this.dFineSospensioneQ1 = dFineSospensioneQ1;
	}

	public Date getDataInvio() {
		return this.dataInvio;
	}

	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	public Date getDataPrimaAssunzione() {
		return this.dataPrimaAssunzione;
	}

	public void setDataPrimaAssunzione(Date dataPrimaAssunzione) {
		this.dataPrimaAssunzione = dataPrimaAssunzione;
	}

	public Date getDataProtocollo() {
		return this.dataProtocollo;
	}

	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	public Date getDataRiferimentoProspetto() {
		return this.dataRiferimentoProspetto;
	}

	public void setDataRiferimentoProspetto(Date dataRiferimentoProspetto) {
		this.dataRiferimentoProspetto = dataRiferimentoProspetto;
	}

	public Date getDataSecondaAssunzione() {
		return this.dataSecondaAssunzione;
	}

	public void setDataSecondaAssunzione(Date dataSecondaAssunzione) {
		this.dataSecondaAssunzione = dataSecondaAssunzione;
	}

	public Date getDataTimbroPostale() {
		return this.dataTimbroPostale;
	}

	public void setDataTimbroPostale(Date dataTimbroPostale) {
		this.dataTimbroPostale = dataTimbroPostale;
	}

	public String getEmailNotifica() {
		return this.emailNotifica;
	}

	public void setEmailNotifica(String emailNotifica) {
		this.emailNotifica = emailNotifica;
	}

	public String getEmailSoggettoComunicazione() {
		return this.emailSoggettoComunicazione;
	}

	public void setEmailSoggettoComunicazione(String emailSoggettoComunicazione) {
		this.emailSoggettoComunicazione = emailSoggettoComunicazione;
	}

	public String getFlgAssunzioniPubbSelezione() {
		return this.flgAssunzioniPubbSelezione;
	}

	public void setFlgAssunzioniPubbSelezione(String flgAssunzioniPubbSelezione) {
		this.flgAssunzioniPubbSelezione = flgAssunzioniPubbSelezione;
	}

	public String getFlgConfermatoQ1() {
		return this.flgConfermatoQ1;
	}

	public void setFlgConfermatoQ1(String flgConfermatoQ1) {
		this.flgConfermatoQ1 = flgConfermatoQ1;
	}

	public String getFlgGradualita() {
		return this.flgGradualita;
	}

	public void setFlgGradualita(String flgGradualita) {
		this.flgGradualita = flgGradualita;
	}

	public String getFlgInvioMinistero() {
		return this.flgInvioMinistero;
	}

	public void setFlgInvioMinistero(String flgInvioMinistero) {
		this.flgInvioMinistero = flgInvioMinistero;
	}

	public String getFlgNessunaAssunzioneAggiun() {
		return this.flgNessunaAssunzioneAggiun;
	}

	public void setFlgNessunaAssunzioneAggiun(String flgNessunaAssunzioneAggiun) {
		this.flgNessunaAssunzioneAggiun = flgNessunaAssunzioneAggiun;
	}

	public String getFlgSospensionePerMobilita() {
		return this.flgSospensionePerMobilita;
	}

	public void setFlgSospensionePerMobilita(String flgSospensionePerMobilita) {
		this.flgSospensionePerMobilita = flgSospensionePerMobilita;
	}

	public String getFlgVisitaIspettiva() {
		return this.flgVisitaIspettiva;
	}

	public void setFlgVisitaIspettiva(String flgVisitaIspettiva) {
		this.flgVisitaIspettiva = flgVisitaIspettiva;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getNumLavorInForzaNazionale() {
		return this.numLavorInForzaNazionale;
	}

	public void setNumLavorInForzaNazionale(BigDecimal numLavorInForzaNazionale) {
		this.numLavorInForzaNazionale = numLavorInForzaNazionale;
	}

	public BigDecimal getNumeroProtocollo() {
		return this.numeroProtocollo;
	}

	public void setNumeroProtocollo(BigDecimal numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	public String getTipoProvenienza() {
		return this.tipoProvenienza;
	}

	public void setTipoProvenienza(String tipoProvenienza) {
		this.tipoProvenienza = tipoProvenienza;
	}
	/*
	public List<ProDAssPubbliche> getProDAssPubbliches() {
		return this.proDAssPubbliches;
	}

	public void setProDAssPubbliches(List<ProDAssPubbliche> proDAssPubbliches) {
		this.proDAssPubbliches = proDAssPubbliches;
	}

	public ProDAssPubbliche addProDAssPubblich(ProDAssPubbliche proDAssPubblich) {
		getProDAssPubbliches().add(proDAssPubblich);
		proDAssPubblich.setProDProspetto(this);

		return proDAssPubblich;
	}

	public ProDAssPubbliche removeProDAssPubblich(ProDAssPubbliche proDAssPubblich) {
		getProDAssPubbliches().remove(proDAssPubblich);
		proDAssPubblich.setProDProspetto(null);

		return proDAssPubblich;
	}
*/
//  ATTENZIONE de-commentato perche' altrimenti non abbiamo il legame con l'azienda 
	public ProDDatiAzienda getProDDatiAzienda() {
		return this.proDDatiAzienda;
	}

	public void setProDDatiAzienda(ProDDatiAzienda proDDatiAzienda) {
		this.proDDatiAzienda = proDDatiAzienda;
	}
 
//	public ProDPdfProspetto getProDPdfProspetto() {
//		return this.proDPdfProspetto;
//	}
//
//	public void setProDPdfProspetto(ProDPdfProspetto proDPdfProspetto) {
//		this.proDPdfProspetto = proDPdfProspetto;
//	}

	public ProDProspetto getProDProspetto() {
		return this.proDProspetto;
	}

	public void setProDProspetto(ProDProspetto proDProspetto) {
		this.proDProspetto = proDProspetto;
	}

//	public List<ProDProspetto> getProDProspettos() {
//		return this.proDProspettos;
//	}
//
//	public void setProDProspettos(List<ProDProspetto> proDProspettos) {
//		this.proDProspettos = proDProspettos;
//	}

//	public ProDProspetto addProDProspetto(ProDProspetto proDProspetto) {
//		getProDProspettos().add(proDProspetto);
//		proDProspetto.setProDProspetto(this);
//
//		return proDProspetto;
//	}
//
//	public ProDProspetto removeProDProspetto(ProDProspetto proDProspetto) {
//		getProDProspettos().remove(proDProspetto);
//		proDProspetto.setProDProspetto(null);
//
//		return proDProspetto;
//	}
//
	public ProTCategoriaAzienda getProTCategoriaAzienda() {
		return this.proTCategoriaAzienda;
	}

	public void setProTCategoriaAzienda(ProTCategoriaAzienda proTCategoriaAzienda) {
		this.proTCategoriaAzienda = proTCategoriaAzienda;
	}

	public ProTComunicazione getProTComunicazione() {
		return this.proTComunicazione;
	}

	public void setProTComunicazione(ProTComunicazione proTComunicazione) {
		this.proTComunicazione = proTComunicazione;
	}

//	public ProTSoggetti getProTSoggetti() {
//		return this.proTSoggetti;
//	}
//
//	public void setProTSoggetti(ProTSoggetti proTSoggetti) {
//		this.proTSoggetti = proTSoggetti;
//	}

	public ProTStatoProspetto getProTStatoProspetto() {
		return this.proTStatoProspetto;
	}

	public void setProTStatoProspetto(ProTStatoProspetto proTStatoProspetto) {
		this.proTStatoProspetto = proTStatoProspetto;
	}

//	public ProTStatoVerifica getProTStatoVerifica() {
//		return this.proTStatoVerifica;
//	}
//
//	public void setProTStatoVerifica(ProTStatoVerifica proTStatoVerifica) {
//		this.proTStatoVerifica = proTStatoVerifica;
//	}
// 
//	public ProDProspettoGradualita getProDProspettoGradualita() {
//		return this.proDProspettoGradualita;
//	}
//
//	public void setProDProspettoGradualita(ProDProspettoGradualita proDProspettoGradualita) {
//		this.proDProspettoGradualita = proDProspettoGradualita;
//	}
// 
//	public ProDRiepilogoNazionale getProDRiepilogoNazionale() {
//		return this.proDRiepilogoNazionale;
//	}
//
//	public void setProDRiepilogoNazionale(ProDRiepilogoNazionale proDRiepilogoNazionale) {
//		this.proDRiepilogoNazionale = proDRiepilogoNazionale;
//	}

//	public List<ProRProspettoProvincia> getProRProspettoProvincias() {
//		return this.proRProspettoProvincias;
//	}
//
//	public void setProRProspettoProvincias(List<ProRProspettoProvincia> proRProspettoProvincias) {
//		this.proRProspettoProvincias = proRProspettoProvincias;
//	}
//
//	public ProRProspettoProvincia addProRProspettoProvincia(ProRProspettoProvincia proRProspettoProvincia) {
//		getProRProspettoProvincias().add(proRProspettoProvincia);
//		proRProspettoProvincia.setProDProspetto(this);
//
//		return proRProspettoProvincia;
//	}
//
//	public ProRProspettoProvincia removeProRProspettoProvincia(ProRProspettoProvincia proRProspettoProvincia) {
//		getProRProspettoProvincias().remove(proRProspettoProvincia);
//		proRProspettoProvincia.setProDProspetto(null);
//
//		return proRProspettoProvincia;
//	}

}
