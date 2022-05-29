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
import java.util.List;

public class ProfiloUtente implements Serializable {

  private static final long serialVersionUID = 1L;

  public String codiceFiscaleUtente;

  public String ruoloUtente;

  public String dsNome;

  public String dsCognome;

  public List<ImpresaInfoc> listaAziendeAAEP;

  public List<RuoloIrideListaCasiUso> listRuoliIride;

  public List<ProfileComonl> listaProfiliComonl;

  public String getCodiceFiscaleUtente() {
    return codiceFiscaleUtente;
  }

  public void setCodiceFiscaleUtente(String codiceFiscaleUtente) {
    this.codiceFiscaleUtente = codiceFiscaleUtente;
  }

  public String getRuoloUtente() {
    return ruoloUtente;
  }

  public void setRuoloUtente(String ruoloUtente) {
    this.ruoloUtente = ruoloUtente;
  }

  public String getDsNome() {
    return dsNome;
  }

  public void setDsNome(String dsNome) {
    this.dsNome = dsNome;
  }

  public String getDsCognome() {
    return dsCognome;
  }

  public void setDsCognome(String dsCognome) {
    this.dsCognome = dsCognome;
  }

  public List<ImpresaInfoc> getListaAziendeAAEP() {
    return listaAziendeAAEP;
  }

  public void setListaAziendeAAEP(List<ImpresaInfoc> listaAziendeAAEP) {
    this.listaAziendeAAEP = listaAziendeAAEP;
  }

  public List<RuoloIrideListaCasiUso> getListRuoliIride() {
    return listRuoliIride;
  }

  public void setListRuoliIride(List<RuoloIrideListaCasiUso> listRuoliIride) {
    this.listRuoliIride = listRuoliIride;
  }

  public List<ProfileComonl> getListaProfiliComonl() {
    return listaProfiliComonl;
  }

  public void setListaProfiliComonl(List<ProfileComonl> listaProfiliComonl) {
    this.listaProfiliComonl = listaProfiliComonl;
  }

  @Override
  public String toString() {
    return "ProfiloUtente [codiceFiscaleUtente=" + codiceFiscaleUtente + ", ruoloUtente=" + ruoloUtente + ", dsNome=" + dsNome + ", dsCognome=" + dsCognome + ", listaAziendeAAEP=" + listaAziendeAAEP + ", listRuoliIride=" + listRuoliIride + ", listaProfiliComonl=" + listaProfiliComonl + "]";
  }

}
