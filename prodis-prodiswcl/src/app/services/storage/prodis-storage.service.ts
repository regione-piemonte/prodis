/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Injectable } from '@angular/core';
import { Params } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { ConfermaRiepilogo, DatiProvinciali, Prospetto, RicercaProspetto, Ruolo } from 'src/app/modules/prodisapi';

@Injectable({
  providedIn: 'root'
})
export class ProdisStorageService {

  private prospetto: BehaviorSubject<Prospetto> = new BehaviorSubject({} as Prospetto);
  private ruolo: BehaviorSubject<Ruolo> = new BehaviorSubject({} as Ruolo);
  private prospettoToSave: BehaviorSubject<Prospetto> = new BehaviorSubject({} as Prospetto);
  private DatiProvincialiToSave: BehaviorSubject<Prospetto> = new BehaviorSubject({} as DatiProvinciali);
  private bottoneSalvaProseguiDisabilitato: BehaviorSubject<boolean> = new BehaviorSubject({} as boolean);
  private hideButtonConfermaProsegui: BehaviorSubject<boolean> = new BehaviorSubject(true);  /*Aggiunta richiesta da Luisa il Conferma e prosegui, 
                                                                                                        nel Q3 deve essere visibile solo quando si è posizionati
                                                                                                        nel tab di ripeilogo */

  private disableQ3Btn: BehaviorSubject<boolean> = new BehaviorSubject(false);
  private esitoUploadLavoratori: BehaviorSubject<boolean> = new BehaviorSubject(false);

  private esitoInvioProspetto: BehaviorSubject<ConfermaRiepilogo> = new BehaviorSubject({} as ConfermaRiepilogo);


  get prospetto$(): Observable<Prospetto> { return this.prospetto.asObservable(); }
  get esitoInvioProspetto$(): Observable<ConfermaRiepilogo> { return this.esitoInvioProspetto.asObservable(); }
  get ruolo$(): Observable<Ruolo> { return this.ruolo.asObservable(); }
  get bottoneSalvaProseguiDisabilitato$(): Observable<boolean> { return this.bottoneSalvaProseguiDisabilitato.asObservable(); }
  get prospettoToSave$(): Observable<Prospetto> { return this.prospettoToSave.asObservable(); }
  get hideButtonConfermaProsegui$(): Observable<boolean> { return this.hideButtonConfermaProsegui.asObservable(); }

  get disableQ3Btn$(): Observable<boolean> { return this.disableQ3Btn.asObservable(); }
  
  private filtroRicercaProspetto: BehaviorSubject<RicercaProspetto> = new BehaviorSubject({} as RicercaProspetto);
  private urlParams: BehaviorSubject<Params> = new BehaviorSubject({} as Params);

  get filtroRicercaProspetto$(): Observable<RicercaProspetto> { return this.filtroRicercaProspetto.asObservable(); }
  get urlParams$(): Observable<Params> { return this.urlParams.asObservable(); }
  get esitoUploadLavoratori$(): Observable<boolean> { return this.esitoUploadLavoratori.asObservable(); }


  constructor() { }

  public setProspetto(prospetto: Prospetto){
    this.prospetto.next(prospetto);
  }

  private ruoloCurrent: Ruolo = null;

  public setRuolo(ilRuolo: Ruolo){
    this.ruoloCurrent = ilRuolo;
    this.ruolo.next(ilRuolo);
  }

  public setBottoneSalvaProseguiDisabilitato(ilValore: boolean){
    this.bottoneSalvaProseguiDisabilitato.next(ilValore);
  }

  public isRuoloCurrentSelected() {
    if (this.ruoloCurrent) {
      return true;
    }
    return false;
  }

  public setProspettoToSave(prospetto: Prospetto){
    this.prospettoToSave.next(prospetto);
  }

  public setEsitoInvioProspetto(esito: ConfermaRiepilogo){
    this.esitoInvioProspetto.next(esito);
  }

  public setFiltroRicercaProspetto(ilFiltro: RicercaProspetto){
    this.filtroRicercaProspetto.next(ilFiltro);
  }

  public setUrlParams(iParametriDelloUrl: Params){
    this.urlParams.next(iParametriDelloUrl);
  }

  public setHideButtonConfermaProsegui(value: boolean){
    this.hideButtonConfermaProsegui.next(value);
  }

  public setDisableQ3Btn(value: boolean){
    this.disableQ3Btn.next(value);
  }
  public setEsitoUploadLavoratori(value: boolean){
    this.esitoUploadLavoratori.next(value);
  }
}

