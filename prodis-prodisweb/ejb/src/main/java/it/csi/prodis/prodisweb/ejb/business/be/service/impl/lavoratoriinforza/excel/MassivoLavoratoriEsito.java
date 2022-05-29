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
package it.csi.prodis.prodisweb.ejb.business.be.service.impl.lavoratoriinforza.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

import it.csi.prodis.prodisweb.lib.dto.ApiError;

public class MassivoLavoratoriEsito {
	
	public MassivoLavoratoriEsito() {
		setFileLavoratori(new MassivoLavoratoriFile());
		setErrori(new ArrayList<ApiError>());
		setMessaggi(new ArrayList<String>());
	}
	
	private boolean esitoPositivo;
	public boolean isEsitoPositivo() {return esitoPositivo;}
	protected void setEsitoPositivo(boolean esitoCaricamentoPositivo) {this.esitoPositivo = esitoCaricamentoPositivo;}
	
	private List<ApiError> errori;
	public List<ApiError> getErrori() {return errori;}
	private void setErrori(List<ApiError> errori) {this.errori = errori;}
	protected void addErrore(ApiError errore) {
		if (getErrori() == null) {
			setErrori(new ArrayList<ApiError>());
		}
		getErrori().add(errore);
	}
	protected void addErrori(List<ApiError> errori) {
		if (getErrori() == null) {
			setErrori(new ArrayList<ApiError>());
		}
		getErrori().addAll(errori);
	}
	
	private List<String> messaggi;
	public List<String> getMessaggi() {return messaggi;}
	public void setMessaggi(List<String> messaggi) {this.messaggi = messaggi;}
	protected void addMessaggio(String messaggio) {
		if (getMessaggi() == null) {
			setMessaggi(new ArrayList<String>());
		}
		getMessaggi().add(messaggio);
	}
	protected void addMessaggi(List<String> messaggi) {
		if (getMessaggi() == null) {
			setMessaggi(new ArrayList<String>());
		}
		getMessaggi().addAll(messaggi);
	}
	
	private MassivoLavoratoriFile fileLavoratori;
	public MassivoLavoratoriFile getFileLavoratori() {return fileLavoratori;}
	public void setFileLavoratori(MassivoLavoratoriFile fileLavoratori) {this.fileLavoratori = fileLavoratori;}

	private List<LavoratoriInForzaMassivo> lavoratoriBuoni;
	public List<LavoratoriInForzaMassivo> getLavoratoriBuoni() {return lavoratoriBuoni;}
	public void setLavoratoriBuoni(List<LavoratoriInForzaMassivo> lavoratoriBuoni) {this.lavoratoriBuoni = lavoratoriBuoni;}
	protected void addLavoratoriBuoni(LavoratoriInForzaMassivo lavoratoriBuoni) {
		if (getLavoratoriBuoni() == null) {
			setLavoratoriBuoni(new ArrayList<LavoratoriInForzaMassivo>());
		}
		getLavoratoriBuoni().add(lavoratoriBuoni);
	}
	
	private List<LavoratoriInForzaMassivo> lavoratoriCattivi;
	public List<LavoratoriInForzaMassivo> getLavoratoriCattivi() {return lavoratoriCattivi;}
	public void setLavoratoriCattivi(List<LavoratoriInForzaMassivo> lavoratoriCattivi) {this.lavoratoriCattivi = lavoratoriCattivi;}
	protected void addLavoratoriCattivi(LavoratoriInForzaMassivo lavoratoriCattivi) {
		if (getLavoratoriCattivi() == null) {
			setLavoratoriCattivi(new ArrayList<LavoratoriInForzaMassivo>());
		}
		getLavoratoriCattivi().add(lavoratoriCattivi);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
