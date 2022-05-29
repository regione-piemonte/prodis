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

import it.csi.iride2.policy.entity.Actor;

public class RuoloIrideListaCasiUso implements Serializable {

  private static final long serialVersionUID = 1L;

  private int indice;

  private Actor actor;

  private List<String> listaCasiUso;

  public int getIndice() {
    return indice;
  }

  public void setIndice(int indice) {
    this.indice = indice;
  }

  public Actor getActor() {
    return actor;
  }

  public void setActor(Actor actor) {
    this.actor = actor;
  }

  public List<String> getListaCasiUso() {
    return listaCasiUso;
  }

  public void setListaCasiUso(List<String> listaCasiUso) {
    this.listaCasiUso = listaCasiUso;
  }

  @Override
  public String toString() {
    return "RuoloIrideListaCasiUso [indice=" + indice + ", actor=" + actor + ", listaCasiUso=" + listaCasiUso + "]";
  }

}
