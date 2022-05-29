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

public class DatiAggiuntiviConsulente implements Serializable {

  private static final long serialVersionUID = 1L;

  private String capStudioProfessionale;

  private String codiceComuneStudioProfessionale;

  private String codiceFiscaleStudioProfessionale;

  private String descrizioneComuneStudioProfessionale;

  private String descrizioneStudioProfessionale;

  private String faxStudioProfessionale;

  private String flgConsulenteAccentrato;

  private String indirizzoStudioProfessionale;

  private String mailStudioProfessionale;

  private String partitaIvaStudioProfessionale;

  private String telefonoStudioProfessionale;

  private String codMinSoggettoAbilitato;

  public String getCapStudioProfessionale() {
    return capStudioProfessionale;
  }

  public void setCapStudioProfessionale(String capStudioProfessionale) {
    this.capStudioProfessionale = capStudioProfessionale;
  }

  public String getCodiceComuneStudioProfessionale() {
    return codiceComuneStudioProfessionale;
  }

  public void setCodiceComuneStudioProfessionale(String codiceComuneStudioProfessionale) {
    this.codiceComuneStudioProfessionale = codiceComuneStudioProfessionale;
  }

  public String getCodiceFiscaleStudioProfessionale() {
    return codiceFiscaleStudioProfessionale;
  }

  public void setCodiceFiscaleStudioProfessionale(String codiceFiscaleStudioProfessionale) {
    this.codiceFiscaleStudioProfessionale = codiceFiscaleStudioProfessionale;
  }

  public String getDescrizioneComuneStudioProfessionale() {
    return descrizioneComuneStudioProfessionale;
  }

  public void setDescrizioneComuneStudioProfessionale(String descrizioneComuneStudioProfessionale) {
    this.descrizioneComuneStudioProfessionale = descrizioneComuneStudioProfessionale;
  }

  public String getDescrizioneStudioProfessionale() {
    return descrizioneStudioProfessionale;
  }

  public void setDescrizioneStudioProfessionale(String descrizioneStudioProfessionale) {
    this.descrizioneStudioProfessionale = descrizioneStudioProfessionale;
  }

  public String getFaxStudioProfessionale() {
    return faxStudioProfessionale;
  }

  public void setFaxStudioProfessionale(String faxStudioProfessionale) {
    this.faxStudioProfessionale = faxStudioProfessionale;
  }

  public String getFlgConsulenteAccentrato() {
    return flgConsulenteAccentrato;
  }

  public void setFlgConsulenteAccentrato(String flgConsulenteAccentrato) {
    this.flgConsulenteAccentrato = flgConsulenteAccentrato;
  }

  public String getIndirizzoStudioProfessionale() {
    return indirizzoStudioProfessionale;
  }

  public void setIndirizzoStudioProfessionale(String indirizzoStudioProfessionale) {
    this.indirizzoStudioProfessionale = indirizzoStudioProfessionale;
  }

  public String getMailStudioProfessionale() {
    return mailStudioProfessionale;
  }

  public void setMailStudioProfessionale(String mailStudioProfessionale) {
    this.mailStudioProfessionale = mailStudioProfessionale;
  }

  public String getPartitaIvaStudioProfessionale() {
    return partitaIvaStudioProfessionale;
  }

  public void setPartitaIvaStudioProfessionale(String partitaIvaStudioProfessionale) {
    this.partitaIvaStudioProfessionale = partitaIvaStudioProfessionale;
  }

  public String getTelefonoStudioProfessionale() {
    return telefonoStudioProfessionale;
  }

  public void setTelefonoStudioProfessionale(String telefonoStudioProfessionale) {
    this.telefonoStudioProfessionale = telefonoStudioProfessionale;
  }

  public String getCodMinSoggettoAbilitato() {
    return codMinSoggettoAbilitato;
  }

  public void setCodMinSoggettoAbilitato(String codMinSoggettoAbilitato) {
    this.codMinSoggettoAbilitato = codMinSoggettoAbilitato;
  }

  @Override
  public String toString() {
    return "DatiAggiuntiviConsulente [capStudioProfessionale=" + capStudioProfessionale + ", codiceComuneStudioProfessionale=" + codiceComuneStudioProfessionale + ", codiceFiscaleStudioProfessionale=" + codiceFiscaleStudioProfessionale + ", descrizioneComuneStudioProfessionale="
        + descrizioneComuneStudioProfessionale + ", descrizioneStudioProfessionale=" + descrizioneStudioProfessionale + ", faxStudioProfessionale=" + faxStudioProfessionale + ", flgConsulenteAccentrato=" + flgConsulenteAccentrato + ", indirizzoStudioProfessionale=" + indirizzoStudioProfessionale
        + ", mailStudioProfessionale=" + mailStudioProfessionale + ", partitaIvaStudioProfessionale=" + partitaIvaStudioProfessionale + ", telefonoStudioProfessionale=" + telefonoStudioProfessionale + ", codMinSoggettoAbilitato=" + codMinSoggettoAbilitato + "]";
  }

}
