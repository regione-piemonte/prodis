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

public class DatiAggiuntiviImpresa implements Serializable {

  private static final long serialVersionUID = 1L;

  private String codiceFiscaleImpresa;

  private String denominazioneImpresaNoAAEP;

  private String flgImpresaAccentrata;

  private String flgScuolaPubblica;

  public String getCodiceFiscaleImpresa() {
    return codiceFiscaleImpresa;
  }

  public void setCodiceFiscaleImpresa(String codiceFiscaleImpresa) {
    this.codiceFiscaleImpresa = codiceFiscaleImpresa;
  }

  public String getDenominazioneImpresaNoAAEP() {
    return denominazioneImpresaNoAAEP;
  }

  public void setDenominazioneImpresaNoAAEP(String denominazioneImpresaNoAAEP) {
    this.denominazioneImpresaNoAAEP = denominazioneImpresaNoAAEP;
  }

  public String getFlgImpresaAccentrata() {
    return flgImpresaAccentrata;
  }

  public void setFlgImpresaAccentrata(String flgImpresaAccentrata) {
    this.flgImpresaAccentrata = flgImpresaAccentrata;
  }

  public String getFlgScuolaPubblica() {
    return flgScuolaPubblica;
  }

  public void setFlgScuolaPubblica(String flgScuolaPubblica) {
    this.flgScuolaPubblica = flgScuolaPubblica;
  }

  @Override
  public String toString() {
    return "DatiAggiuntiviImpresa [codiceFiscaleImpresa=" + codiceFiscaleImpresa + ", denominazioneImpresaNoAAEP=" + denominazioneImpresaNoAAEP + ", flgImpresaAccentrata=" + flgImpresaAccentrata + ", flgScuolaPubblica=" + flgScuolaPubblica + "]";
  }

}
