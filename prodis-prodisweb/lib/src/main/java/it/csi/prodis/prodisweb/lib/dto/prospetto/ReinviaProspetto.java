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

import java.util.Date;
import java.util.List;

import it.csi.prodis.prodisweb.lib.dto.common.Ruolo;

public class ReinviaProspetto {

	private Ruolo ruolo;
	private List<String> idsProspetti;
	private Date dataInizio;
	private Date dataFine;
	private Long maxNTrasmissioni;
	public Ruolo getRuolo() {
		return ruolo;
	}
	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}
	public List<String> getIdsProspetti() {
		return idsProspetti;
	}
	public void setIdsProspetti(List<String> idsProspetti) {
		this.idsProspetti = idsProspetti;
	}
	public Date getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}
	public Date getDataFine() {
		return dataFine;
	}
	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}
	public Long getMaxNTrasmissioni() {
		return maxNTrasmissioni;
	}
	public void setMaxNTrasmissioni(Long maxNTrasmissioni) {
		this.maxNTrasmissioni = maxNTrasmissioni;
	}

}
