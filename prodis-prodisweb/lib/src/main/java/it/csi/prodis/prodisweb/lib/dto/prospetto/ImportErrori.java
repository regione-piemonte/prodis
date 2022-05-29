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

import it.csi.prodis.prodisweb.lib.dto.BaseDto;
import it.csi.prodis.prodisweb.lib.dto.decodifiche.ImportErroriSpicom;

/**
 * The Class ImportErrori.
 */
public class ImportErrori extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Date dataElab;
	private String datoInput;
	private String dsErroreNonGestito;
	private BigDecimal idSpiTrasmissione;
	private String tabellaDestinazione;
	private ImportErroriSpicom importErroriSpicom;

	/**
	 * @return the dataElab
	 */
	public Date getDataElab() {
		return dataElab;
	}
	
	/**
	 * @param dataElab the dataElab to set
	 */
	public void setDataElab(Date dataElab) {
		this.dataElab = dataElab;
	}

	/**
	 * @return the datoInput
	 */
	public String getDatoInput() {
		return datoInput;
	}
	
	/**
	 * @param datoInput the datoInput to set
	 */
	public void setDatoInput(String datoInput) {
		this.datoInput = datoInput;
	}

	/**
	 * @return the dsErroreNonGestito
	 */
	public String getDsErroreNonGestito() {
		return dsErroreNonGestito;
	}
	
	/**
	 * @param dsErroreNonGestito the dsErroreNonGestito to set
	 */
	public void setDsErroreNonGestito(String dsErroreNonGestito) {
		this.dsErroreNonGestito = dsErroreNonGestito;
	}

	/**
	 * @return the idSpiTrasmissione
	 */
	public BigDecimal getIdSpiTrasmissione() {
		return idSpiTrasmissione;
	}
	
	/**
	 * @param idSpiTrasmissione the idSpiTrasmissione to set
	 */
	public void setIdSpiTrasmissione(BigDecimal idSpiTrasmissione) {
		this.idSpiTrasmissione = idSpiTrasmissione;
	}

	/**
	 * @return the tabellaDestinazione
	 */
	public String getTabellaDestinazione() {
		return tabellaDestinazione;
	}
	
	/**
	 * @param tabellaDestinazione the tabellaDestinazione to set
	 */
	public void setTabellaDestinazione(String tabellaDestinazione) {
		this.tabellaDestinazione = tabellaDestinazione;
	}

	/**
	 * @return the importErroriSpicom
	 */
	public ImportErroriSpicom getImportErroriSpicom() {
		return importErroriSpicom;
	}
	
	/**
	 * @param importErroriSpicom the importErroriSpicom to set
	 */
	public void setImportErroriSpicom(ImportErroriSpicom importErroriSpicom) {
		this.importErroriSpicom = importErroriSpicom;
	}

}
