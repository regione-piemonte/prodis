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
package it.csi.prodis.prodissrv.lib.dto.common;

import java.io.Serializable;
import java.util.List;

import it.csi.prodis.prodissrv.lib.dto.BaseDto;

/**
 * The Class Ruolo.
 */
public class Ruolo extends BaseDto<Integer> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String denominazioneAzienda;
	private String ruolo;
	private String codiceFiscale;
	private List<String> listaCasiUso;
	private boolean isLegaleRappresentante;
	private boolean isPersonaCaricaAziendale;
	private boolean isConsulenteRespo;
	private boolean isDelegatoRespo;
	private boolean isPersonaAutorizzata;
	private boolean isPersonaAutorizzataScuola;
	private boolean isOperatoreProvinciale;
	private boolean isAmministratore;
	private boolean isMonitoraggio;

	public String getDenominazioneAzienda() {
		return denominazioneAzienda;
	}

	public void setDenominazioneAzienda(String denominazioneAzienda) {
		this.denominazioneAzienda = denominazioneAzienda;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public List<String> getListaCasiUso() {
		return listaCasiUso;
	}

	public void setListaCasiUso(List<String> listaCasiUso) {
		this.listaCasiUso = listaCasiUso;
	}

	public boolean isLegaleRappresentante() {
		return isLegaleRappresentante;
	}

	public void setLegaleRappresentante(boolean isLegaleRappresentante) {
		this.isLegaleRappresentante = isLegaleRappresentante;
	}

	public boolean isPersonaCaricaAziendale() {
		return isPersonaCaricaAziendale;
	}

	public void setPersonaCaricaAziendale(boolean isPersonaCaricaAziendale) {
		this.isPersonaCaricaAziendale = isPersonaCaricaAziendale;
	}

	public boolean isConsulenteRespo() {
		return isConsulenteRespo;
	}

	public void setConsulenteRespo(boolean isConsulenteRespo) {
		this.isConsulenteRespo = isConsulenteRespo;
	}

	public boolean isDelegatoRespo() {
		return isDelegatoRespo;
	}

	public void setDelegatoRespo(boolean isDelegatoRespo) {
		this.isDelegatoRespo = isDelegatoRespo;
	}

	public boolean isPersonaAutorizzata() {
		return isPersonaAutorizzata;
	}

	public void setPersonaAutorizzata(boolean isPersonaAutorizzata) {
		this.isPersonaAutorizzata = isPersonaAutorizzata;
	}

	public boolean isPersonaAutorizzataScuola() {
		return isPersonaAutorizzataScuola;
	}

	public void setPersonaAutorizzataScuola(boolean isPersonaAutorizzataScuola) {
		this.isPersonaAutorizzataScuola = isPersonaAutorizzataScuola;
	}

	public boolean isOperatoreProvinciale() {
		return isOperatoreProvinciale;
	}

	public void setOperatoreProvinciale(boolean isOperatoreProvinciale) {
		this.isOperatoreProvinciale = isOperatoreProvinciale;
	}

	public boolean isAmministratore() {
		return isAmministratore;
	}

	public void setAmministratore(boolean isAmministratore) {
		this.isAmministratore = isAmministratore;
	}

	public boolean isMonitoraggio() {
		return isMonitoraggio;
	}

	public void setMonitoraggio(boolean isMonitoraggio) {
		this.isMonitoraggio = isMonitoraggio;
	}

}
