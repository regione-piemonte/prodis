/*-
 * ========================LICENSE_START=================================
 * PRODIS BackEnd - INTEGRATION AAEP, IRIDE, COMONL submodule
 * %%
 * Copyright (C) 2022 Regione Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.prodis.prodisweb.profilazione.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profilo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cfUtente;

  private String denominazioneAzienda;

  private String ruolo;

  private String codiceFiscale;

  private String cognome;

  private String nome;

  private List<String> listaCasiUso;

  // serve per la visulizzazione nella form
  Map<String, String> radio = new HashMap<String, String>();

  public boolean isActorInUseCase(String useCase) {
    if (listaCasiUso != null) {
      for (int i = 0; i < listaCasiUso.size(); i++) {
        String caso = listaCasiUso.get(i);
        if (useCase.equalsIgnoreCase(caso)) { return true; }
      }
    }

    return false;
  }

  private String codMinSoggettoAbilitato;

  public String getCodMinSoggettoAbilitato() {
    return codMinSoggettoAbilitato;
  }

  public void setCodMinSoggettoAbilitato(String codMinSoggettoAbilitato) {
    this.codMinSoggettoAbilitato = codMinSoggettoAbilitato;
  }

  public String FlgConsulenteAccentrato;

  public String getFlgConsulenteAccentrato() {
    return FlgConsulenteAccentrato;
  }

  public void setFlgConsulenteAccentrato(String flgConsulenteAccentrato) {
    FlgConsulenteAccentrato = flgConsulenteAccentrato;
  }

  public String getCfUtente() {
    return cfUtente;
  }

  public void setCfUtente(String cfUtente) {
    this.cfUtente = cfUtente;
  }

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

  public String getCognome() {
    return cognome;
  }

  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Map<String, String> getRadio() {
    return radio;
  }

  public void setRadio(Map<String, String> radio) {
    this.radio = radio;
  }

  public List<String> getListaCasiUso() {
    return listaCasiUso;
  }

  public void setListaCasiUso(List<String> listaCasiUso) {
    this.listaCasiUso = listaCasiUso;
  }

  @Override
  public String toString() {
    return "Profilo [cfUtente=" + cfUtente + ", denominazioneAzienda=" + denominazioneAzienda + ", ruolo=" + ruolo + ", codiceFiscale=" + codiceFiscale + ", cognome=" + cognome + ", nome=" + nome + ", listaCasiUso=" + listaCasiUso + ", radio=" + radio + ", codMinSoggettoAbilitato="
        + codMinSoggettoAbilitato + ", FlgConsulenteAccentrato=" + FlgConsulenteAccentrato + "]";
  }

  
}
