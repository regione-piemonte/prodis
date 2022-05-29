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

import it.csi.prodis.prodissrv.lib.dto.BaseAuditedDto;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Comune;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.Istat2001livello5;
import it.csi.prodis.prodissrv.lib.dto.decodifiche.StatiEsteri;

/**
 * The Class PostiLavoroDisp.
 */
public class PostiLavoroDisp extends BaseAuditedDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String categoriaAssunzione;
	private String categoriaSoggetto;
	private String descCapacitaRichiesteContr;
	private String descMansione;
	private String flgPresenzaBarriereArchite;
	private String flgRaggiungibMezziPubblici;
	private String flgTurniNotturni;
	private BigDecimal nPosti;
	private Long idProspettoProv;
	private Comune comune;
	private Istat2001livello5 istat2001livello5;
	private StatiEsteri statiEsteri;

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
	 * @return the descCapacitaRichiesteContr
	 */
	public String getDescCapacitaRichiesteContr() {
		return descCapacitaRichiesteContr;
	}

	/**
	 * @param descCapacitaRichiesteContr the descCapacitaRichiesteContr to set
	 */
	public void setDescCapacitaRichiesteContr(String descCapacitaRichiesteContr) {
		this.descCapacitaRichiesteContr = descCapacitaRichiesteContr;
	}

	/**
	 * @return the descMansione
	 */
	public String getDescMansione() {
		return descMansione;
	}

	/**
	 * @param descMansione the descMansione to set
	 */
	public void setDescMansione(String descMansione) {
		this.descMansione = descMansione;
	}

	/**
	 * @return the flgPresenzaBarriereArchite
	 */
	public String getFlgPresenzaBarriereArchite() {
		return flgPresenzaBarriereArchite;
	}

	/**
	 * @param flgPresenzaBarriereArchite the flgPresenzaBarriereArchite to set
	 */
	public void setFlgPresenzaBarriereArchite(String flgPresenzaBarriereArchite) {
		this.flgPresenzaBarriereArchite = flgPresenzaBarriereArchite;
	}

	/**
	 * @return the flgRaggiungibMezziPubblici
	 */
	public String getFlgRaggiungibMezziPubblici() {
		return flgRaggiungibMezziPubblici;
	}

	/**
	 * @param flgRaggiungibMezziPubblici the flgRaggiungibMezziPubblici to set
	 */
	public void setFlgRaggiungibMezziPubblici(String flgRaggiungibMezziPubblici) {
		this.flgRaggiungibMezziPubblici = flgRaggiungibMezziPubblici;
	}

	/**
	 * @return the flgTurniNotturni
	 */
	public String getFlgTurniNotturni() {
		return flgTurniNotturni;
	}

	/**
	 * @param flgTurniNotturni the flgTurniNotturni to set
	 */
	public void setFlgTurniNotturni(String flgTurniNotturni) {
		this.flgTurniNotturni = flgTurniNotturni;
	}

	/**
	 * @return the nPosti
	 */
	public BigDecimal getnPosti() {
		return nPosti;
	}

	/**
	 * @param nPosti the nPosti to set
	 */
	public void setnPosti(BigDecimal nPosti) {
		this.nPosti = nPosti;
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

	public Long getIdProspettoProv() {
		return idProspettoProv;
	}

	public void setIdProspettoProv(Long idProspettoProv) {
		this.idProspettoProv = idProspettoProv;
	}

}
