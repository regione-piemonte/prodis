/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnDestroy, OnInit } from '@angular/core';
import { NgbTabChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { STATO_PROSPETTO_BOZZA, TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS } from 'src/app/constants';
import { ConfermaRiepilogoProspetto, DatiProvincialiService, DecodificaGenerica, DecodificaService, Prospetto, ProspettoProvincia, ProspettoService, ProvCompensazioni, RiepilogoProvinciale, RiepilogoService, Ruolo, StatiEsteri } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-riepilogo',
  templateUrl: './riepilogo.component.html',
  styleUrls: ['./riepilogo.component.scss']
})
export class RiepilogoComponent implements OnInit {
  paramIdProspetto: number;
  prospetto: Prospetto;
  newProspetto: Prospetto;
  ilProspettoProvincia: ProspettoProvincia[];
  READ_MODE: boolean;


  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  ruolo: Ruolo;
  disableT: boolean = false;
  elencoStatiEsteri: StatiEsteri[];

  constructor(
    private prodisStorageService: ProdisStorageService,
    private datiProvincialiService: DatiProvincialiService,
    private utilitiesService: UtilitiesService,
    private riepilogoService: RiepilogoService,
    private prospettoService: ProspettoService,
    private decodificaService: DecodificaService
  ) { }


  async ngOnInit() {
    this.utilitiesService.showSpinner();


    this.prodisStorageService.prospetto$.subscribe(async item =>{
      this.prospetto = item;
      if (this.prospetto && this.prospetto.id) {
        this.prospetto = await this.prospettoService.getProspettoById(this.prospetto.id).toPromise();
        this.prospetto.datiAzienda.statiEsteri = item.datiAzienda.statiEsteri;
      }
      this.READ_MODE = this.prospetto.statoProspetto.descrizione !== STATO_PROSPETTO_BOZZA;
      this.newProspetto = Utils.clone(this.prospetto);
    });
    this.prodisStorageService.ruolo$.subscribe(ruolo => this.ruolo = ruolo);
    this.ilProspettoProvincia = await this.datiProvincialiService.getRiepilogoByIdProspetto(this.prospetto.id).toPromise();
    this.prodisStorageService.setProspettoToSave(this.prospetto);
    this.prodisStorageService.setProspetto(this.prospetto);
    this.utilitiesService.hideSpinner();
  }


  async onRicaricaErrori(erroriParams: any) {
    this.typeMsg = erroriParams.typeMsg;
    this.hide = erroriParams.hide;
    this.listMsg = erroriParams.listMsg;
  }

  async loadilProspettoProvincia(event){
    this.ilProspettoProvincia = await this.datiProvincialiService.getRiepilogoByIdProspetto(this.prospetto.id).toPromise();
    console.log("Break");
  }

  async saveCompensazione(compensazioneToSend: ProvCompensazioni){
    this.utilitiesService.showSpinner();
    this.pulisciListaErrori();
    try{

      if(compensazioneToSend){
        const idCompensazione: number = compensazioneToSend.id;
        let res: ProvCompensazioni;
        if(idCompensazione){
          res = await this.riepilogoService.putCompensazioni(compensazioneToSend.idProspettoProv,idCompensazione,compensazioneToSend).toPromise();
        }else{
          res = await this.riepilogoService.postCompensazioni(compensazioneToSend.idProspettoProv,compensazioneToSend).toPromise();
        }

        if(res){
          //this.ilProspettoProvincia = await this.datiProvincialiService.getRiepilogoByIdProspetto(this.prospetto.id).toPromise();
          const esitoSP = await this.riepilogoService.storeProcedureEseguiCalcoli(this.prospetto.id,this.prospetto.codUserAggiorn,false).toPromise();
          if(esitoSP.esito === 1){
            await this.loadilProspettoProvincia(null);
          }else{
            this.listMsg.push(esitoSP.messaggio);
            this.typeMsg = TYPE_ALERT_DANGER;
            this.hide = true;
          }
        }
      }else{
        //errore
      }
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errorList = e.error;
        errorList.forEach(e => {
          this.typeMsg = TYPE_ALERT_DANGER;
          this.listMsg.push(e.errorMessage);
        });
        this.hide = true;
      }
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  disableTab($event: NgbTabChangeEvent){
    //TODO Da scommentare a lavori ultimati
    // const nextId = $event.nextId;
    // if(!this.READ_MODE && nextId === "tabCompensazioni"){
    //   this.disableT = true;
    // }
  }

  async salvaBozzaRiepilogo(prospetto: Prospetto){
    //invio prospetto
    this.utilitiesService.showSpinner();
    await this.setCfToSend(prospetto);
    let cfStudioprofessionale = prospetto.cfStudioProfessionale;
    if(cfStudioprofessionale && cfStudioprofessionale !== ""){
      prospetto.cfStudioProfessionale = cfStudioprofessionale.toUpperCase();
    }
    const prospettoToSend = prospetto;
    try{
      const res = await this.riepilogoService.salvaBozzaRiepilogo(this.newProspetto.id,prospettoToSend).toPromise();
       if(res){
         this.prodisStorageService.setProspetto(prospetto);
         this.prodisStorageService.setProspettoToSave(prospetto);
         this.typeMsg = TYPE_ALERT_SUCCESS;
         this.hide = true;
         this.listMsg.push('Salvataggio del riepilogo in bozza andato a buon fine');
       }
    }catch(e){
      if(e.error && e.error.length > 0){
        const listErrors = e.error;
        listErrors.forEach(err => {
          this.listMsg.push(err.errorMessage);
        });
      }
      this.typeMsg = TYPE_ALERT_DANGER;
      this.hide = true;
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private pulisciListaErrori() {
    this.typeMsg = '';
    this.hide = false;
    this.listMsg = [];
  }

  private async setCfToSend(prospetto: Prospetto){
     /* settare i dati esteri */
     const elencoDecodStatiEsteri: DecodificaGenerica[] = await this.decodificaService.postStatiEsteriDecodifica({flgStatiUe: 'S'} as DecodificaGenerica
     ).toPromise();
     this.elencoStatiEsteri = [];
     elencoDecodStatiEsteri.forEach(item => {
       this.elencoStatiEsteri.push({
         id: item.idDecodifica,
         codNazioneMin: item.codDecodifica,
         dsStatiEsteri: item.dsDecodifica,
         siglaNazione : item.siglaNazione
       });
     });
     const datiAzienda = prospetto.datiAzienda;
     if (datiAzienda && datiAzienda.cfCapogruppo && datiAzienda.cfCapogruppo !== '') {
       const cf = datiAzienda.cfCapogruppo;
       let statoEsteroCorrente: StatiEsteri;
       if (datiAzienda.flgCapogruppoEstero && datiAzienda.flgCapogruppoEstero === 'S') {
         // sono un cf azienda straniero
         const siglaNazione = cf.substring(0, 2);
         statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
         prospetto.datiAzienda.cfCapogruppo = cf.substring(2,cf.length);
       } else {
         // sono un cf italiano
         statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
       }
      
       prospetto.datiAzienda.statiEsteri =  statoEsteroCorrente;
     }
  }

}
