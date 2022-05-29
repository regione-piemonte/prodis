/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - LIB submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.lib.dto.prospetto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.CategoriaAzienda;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Comunicazione;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.Soggetti;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoProspetto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.StatoVerifica;

/**
 * The Class Prospetto.
 */
public class Prospetto extends BaseAuditedDto<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private BigDecimal annoProtocollo;
	private String cfComunicazione;
	private String cfStudioProfessionale;
	private String codiceComunicazione;
	private String codiceComunicazionePreced;
	private Date dFineSospensioneQ1;
	private Date dataInvio;
	private Date dataPrimaAssunzione;
	private Date dataProtocollo;
	private Date dataRiferimentoProspetto;
	private Date dataSecondaAssunzione;
	private Date dataTimbroPostale;
	private String emailNotifica;
	private String emailSoggettoComunicazione;
	private String flgAssunzioniPubbSelezione;
	private String flgConfermatoQ1;
	private String flgGradualita;
	private String flgInvioMinistero;
	private String flgNessunaAssunzioneAggiun;
	private String flgSospensionePerMobilita;
	private String flgVisitaIspettiva;
	private String note;
	private BigDecimal numLavorInForzaNazionale;
	private BigDecimal numeroProtocollo;
	private String tipoProvenienza;
	private DatiAzienda datiAzienda;
	private Long idProspettoPrecedente;
	private CategoriaAzienda categoriaAzienda;
	private Comunicazione comunicazione;
	private Soggetti soggetti;
	private StatoProspetto statoProspetto;
	private StatoVerifica statoVerifica;
	private ProspettoGradualita prospettoGradualita;
	private RiepilogoNazionale riepilogoNazionale;
	private List<ProspettoProvincia> prospettoProvincias;
	private List<AssPubbliche> assPubbliche;

	/**
	 * @return the annoProtocollo
	 */
	public BigDecimal getAnnoProtocollo() {
		return annoProtocollo;
	}

	/**
	 * @param annoProtocollo the annoProtocollo to set
	 */
	public void setAnnoProtocollo(BigDecimal annoProtocollo) {
		this.annoProtocollo = annoProtocollo;
	}

	/**
	 * @return the cfComunicazione
	 */
	public String getCfComunicazione() {
		return cfComunicazione;
	}

	/**
	 * @param cfComunicazione the cfComunicazione to set
	 */
	public void setCfComunicazione(String cfComunicazione) {
		this.cfComunicazione = cfComunicazione;
	}

	/**
	 * @return the cfStudioProfessionale
	 */
	public String getCfStudioProfessionale() {
		return cfStudioProfessionale;
	}

	/**
	 * @param cfStudioProfessionale the cfStudioProfessionale to set
	 */
	public void setCfStudioProfessionale(String cfStudioProfessionale) {
		this.cfStudioProfessionale = cfStudioProfessionale;
	}

	/**
	 * @return the codiceComunicazione
	 */
	public String getCodiceComunicazione() {
		return codiceComunicazione;
	}

	/**
	 * @param codiceComunicazione the codiceComunicazione to set
	 */
	public void setCodiceComunicazione(String codiceComunicazione) {
		this.codiceComunicazione = codiceComunicazione;
	}

	/**
	 * @return the codiceComunicazionePreced
	 */
	public String getCodiceComunicazionePreced() {
		return codiceComunicazionePreced;
	}

	/**
	 * @param codiceComunicazionePreced the codiceComunicazionePreced to set
	 */
	public void setCodiceComunicazionePreced(String codiceComunicazionePreced) {
		this.codiceComunicazionePreced = codiceComunicazionePreced;
	}

	/**
	 * @return the dFineSospensioneQ1
	 */
	public Date getdFineSospensioneQ1() {
		return dFineSospensioneQ1;
	}

	/**
	 * @param dFineSospensioneQ1 the dFineSospensioneQ1 to set
	 */
	public void setdFineSospensioneQ1(Date dFineSospensioneQ1) {
		this.dFineSospensioneQ1 = dFineSospensioneQ1;
	}

	/**
	 * @return the dataInvio
	 */
	public Date getDataInvio() {
		return dataInvio;
	}

	/**
	 * @param dataInvio the dataInvio to set
	 */
	public void setDataInvio(Date dataInvio) {
		this.dataInvio = dataInvio;
	}

	/**
	 * @return the dataPrimaAssunzione
	 */
	public Date getDataPrimaAssunzione() {
		return dataPrimaAssunzione;
	}

	/**
	 * @param dataPrimaAssunzione the dataPrimaAssunzione to set
	 */
	public void setDataPrimaAssunzione(Date dataPrimaAssunzione) {
		this.dataPrimaAssunzione = dataPrimaAssunzione;
	}

	/**
	 * @return the dataProtocollo
	 */
	public Date getDataProtocollo() {
		return dataProtocollo;
	}

	/**
	 * @param dataProtocollo the dataProtocollo to set
	 */
	public void setDataProtocollo(Date dataProtocollo) {
		this.dataProtocollo = dataProtocollo;
	}

	/**
	 * @return the dataRiferimentoProspetto
	 */
	public Date getDataRiferimentoProspetto() {
		return dataRiferimentoProspetto;
	}

	/**
	 * @param dataRiferimentoProspetto the dataRiferimentoProspetto to set
	 */
	public void setDataRiferimentoProspetto(Date dataRiferimentoProspetto) {
		this.dataRiferimentoProspetto = dataRiferimentoProspetto;
	}

	/**
	 * @return the dataSecondaAssunzione
	 */
	public Date getDataSecondaAssunzione() {
		return dataSecondaAssunzione;
	}

	/**
	 * @param dataSecondaAssunzione the dataSecondaAssunzione to set
	 */
	public void setDataSecondaAssunzione(Date dataSecondaAssunzione) {
		this.dataSecondaAssunzione = dataSecondaAssunzione;
	}

	/**
	 * @return the dataTimbroPostale
	 */
	public Date getDataTimbroPostale() {
		return dataTimbroPostale;
	}

	/**
	 * @param dataTimbroPostale the dataTimbroPostale to set
	 */
	public void setDataTimbroPostale(Date dataTimbroPostale) {
		this.dataTimbroPostale = dataTimbroPostale;
	}

	/**
	 * @return the emailNotifica
	 */
	public String getEmailNotifica() {
		return emailNotifica;
	}

	/**
	 * @param emailNotifica the emailNotifica to set
	 */
	public void setEmailNotifica(String emailNotifica) {
		this.emailNotifica = emailNotifica;
	}

	/**
	 * @return the emailSoggettoComunicazione
	 */
	public String getEmailSoggettoComunicazione() {
		return emailSoggettoComunicazione;
	}

	/**
	 * @param emailSoggettoComunicazione the emailSoggettoComunicazione to set
	 */
	public void setEmailSoggettoComunicazione(String emailSoggettoComunicazione) {
		this.emailSoggettoComunicazione = emailSoggettoComunicazione;
	}

	/**
	 * @return the flgAssunzioniPubbSelezione
	 */
	public String getFlgAssunzioniPubbSelezione() {
		return flgAssunzioniPubbSelezione;
	}

	/**
	 * @param flgAssunzioniPubbSelezione the flgAssunzioniPubbSelezione to set
	 */
	public void setFlgAssunzioniPubbSelezione(String flgAssunzioniPubbSelezione) {
		this.flgAssunzioniPubbSelezione = flgAssunzioniPubbSelezione;
	}

	/**
	 * @return the flgConfermatoQ1
	 */
	public String getFlgConfermatoQ1() {
		return flgConfermatoQ1;
	}

	/**
	 * @param flgConfermatoQ1 the flgConfermatoQ1 to set
	 */
	public void setFlgConfermatoQ1(String flgConfermatoQ1) {
		this.flgConfermatoQ1 = flgConfermatoQ1;
	}

	/**
	 * @return the flgGradualita
	 */
	public String getFlgGradualita() {
		return flgGradualita;
	}

	/**
	 * @param flgGradualita the flgGradualita to set
	 */
	public void setFlgGradualita(String flgGradualita) {
		this.flgGradualita = flgGradualita;
	}

	/**
	 * @return the flgInvioMinistero
	 */
	public String getFlgInvioMinistero() {
		return flgInvioMinistero;
	}

	/**
	 * @param flgInvioMinistero the flgInvioMinistero to set
	 */
	public void setFlgInvioMinistero(String flgInvioMinistero) {
		this.flgInvioMinistero = flgInvioMinistero;
	}

	/**
	 * @return the flgNessunaAssunzioneAggiun
	 */
	public String getFlgNessunaAssunzioneAggiun() {
		return flgNessunaAssunzioneAggiun;
	}

	/**
	 * @param flgNessunaAssunzioneAggiun the flgNessunaAssunzioneAggiun to set
	 */
	public void setFlgNessunaAssunzioneAggiun(String flgNessunaAssunzioneAggiun) {
		this.flgNessunaAssunzioneAggiun = flgNessunaAssunzioneAggiun;
	}

	/**
	 * @return the flgSospensionePerMobilita
	 */
	public String getFlgSospensionePerMobilita() {
		return flgSospensionePerMobilita;
	}

	/**
	 * @param flgSospensionePerMobilita the flgSospensionePerMobilita to set
	 */
	public void setFlgSospensionePerMobilita(String flgSospensionePerMobilita) {
		this.flgSospensionePerMobilita = flgSospensionePerMobilita;
	}

	/**
	 * @return the flgVisitaIspettiva
	 */
	public String getFlgVisitaIspettiva() {
		return flgVisitaIspettiva;
	}

	/**
	 * @param flgVisitaIspettiva the flgVisitaIspettiva to set
	 */
	public void setFlgVisitaIspettiva(String flgVisitaIspettiva) {
		this.flgVisitaIspettiva = flgVisitaIspettiva;
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
	 * @return the numLavorInForzaNazionale
	 */
	public BigDecimal getNumLavorInForzaNazionale() {
		return numLavorInForzaNazionale;
	}

	/**
	 * @param numLavorInForzaNazionale the numLavorInForzaNazionale to set
	 */
	public void setNumLavorInForzaNazionale(BigDecimal numLavorInForzaNazionale) {
		this.numLavorInForzaNazionale = numLavorInForzaNazionale;
	}

	/**
	 * @return the numeroProtocollo
	 */
	public BigDecimal getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(BigDecimal numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	/**
	 * @return the tipoProvenienza
	 */
	public String getTipoProvenienza() {
		return tipoProvenienza;
	}

	/**
	 * @param tipoProvenienza the tipoProvenienza to set
	 */
	public void setTipoProvenienza(String tipoProvenienza) {
		this.tipoProvenienza = tipoProvenienza;
	}

	/**
	 * @return the datiAzienda
	 */
	public DatiAzienda getDatiAzienda() {
		return datiAzienda;
	}

	/**
	 * @param datiAzienda the datiAzienda to set
	 */
	public void setDatiAzienda(DatiAzienda datiAzienda) {
		this.datiAzienda = datiAzienda;
	}

	/**
	 * @return the categoriaAzienda
	 */
	public CategoriaAzienda getCategoriaAzienda() {
		return categoriaAzienda;
	}

	/**
	 * @param categoriaAzienda the categoriaAzienda to set
	 */
	public void setCategoriaAzienda(CategoriaAzienda categoriaAzienda) {
		this.categoriaAzienda = categoriaAzienda;
	}

	/**
	 * @return the comunicazione
	 */
	public Comunicazione getComunicazione() {
		return comunicazione;
	}

	/**
	 * @param comunicazione the comunicazione to set
	 */
	public void setComunicazione(Comunicazione comunicazione) {
		this.comunicazione = comunicazione;
	}

	/**
	 * @return the soggetti
	 */
	public Soggetti getSoggetti() {
		return soggetti;
	}

	/**
	 * @param soggetti the soggetti to set
	 */
	public void setSoggetti(Soggetti soggetti) {
		this.soggetti = soggetti;
	}

	/**
	 * @return the statoProspetto
	 */
	public StatoProspetto getStatoProspetto() {
		return statoProspetto;
	}

	/**
	 * @param statoProspetto the statoProspetto to set
	 */
	public void setStatoProspetto(StatoProspetto statoProspetto) {
		this.statoProspetto = statoProspetto;
	}

	/**
	 * @return the statoVerifica
	 */
	public StatoVerifica getStatoVerifica() {
		return statoVerifica;
	}

	/**
	 * @param statoVerifica the statoVerifica to set
	 */
	public void setStatoVerifica(StatoVerifica statoVerifica) {
		this.statoVerifica = statoVerifica;
	}

	/**
	 * @return the prospettoGradualita
	 */
	public ProspettoGradualita getProspettoGradualita() {
		return prospettoGradualita;
	}

	/**
	 * @param prospettoGradualita the prospettoGradualita to set
	 */
	public void setProspettoGradualita(ProspettoGradualita prospettoGradualita) {
		this.prospettoGradualita = prospettoGradualita;
	}

	/**
	 * @return the riepilogoNazionale
	 */
	public RiepilogoNazionale getRiepilogoNazionale() {
		return riepilogoNazionale;
	}

	/**
	 * @param riepilogoNazionale the riepilogoNazionale to set
	 */
	public void setRiepilogoNazionale(RiepilogoNazionale riepilogoNazionale) {
		this.riepilogoNazionale = riepilogoNazionale;
	}

	/**
	 * @return the prospettoProvincias
	 */
	public List<ProspettoProvincia> getProspettoProvincias() {
		return prospettoProvincias;
	}

	/**
	 * @param prospettoProvincias the prospettoProvincias to set
	 */
	public void setProspettoProvincias(List<ProspettoProvincia> prospettoProvincias) {
		this.prospettoProvincias = prospettoProvincias;
	}

	public List<AssPubbliche> getAssPubbliche() {
		return assPubbliche;
	}

	public void setAssPubbliche(List<AssPubbliche> assPubbliche) {
		this.assPubbliche = assPubbliche;
	}

	public Long getIdProspettoPrecedente() {
		return idProspettoPrecedente;
	}

	public void setIdProspettoPrecedente(Long idProspettoPrecedente) {
		this.idProspettoPrecedente = idProspettoPrecedente;
	}

}
