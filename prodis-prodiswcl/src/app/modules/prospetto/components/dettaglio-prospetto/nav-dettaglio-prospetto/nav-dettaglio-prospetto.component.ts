/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, Input, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbNav, NgbNavChangeEvent } from '@ng-bootstrap/ng-bootstrap';
import { EventEmitter } from 'events';
import { MaskDirective } from 'ngx-mask';
import { COD_DICHIARANTE_DATORE_LAVORO_PUBBLICO, STATO_PROSPETTO_BOZZA, TYPE_ALERT_DANGER } from 'src/app/constants';
import { ApiError, ConfermaRiepilogo, ConfermaRiepilogoProspetto, DatiProvincialiService, DecodificaGenerica, DecodificaService, Dichiarante, PostPutProspettoResponse, Prospetto, ProspettoService, RiepilogoService, RitornoPassaggioQ3, Ruolo, Soggetti, StatiEsteri } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';
import { isNullOrUndefined } from 'util';

@Component({
  selector: 'prodis-nav-dettaglio-prospetto',
  templateUrl: './nav-dettaglio-prospetto.component.html',
  styleUrls: ['./nav-dettaglio-prospetto.component.scss']
})
export class NavDettaglioProspettoComponent implements OnInit {


  @ViewChild('modalConfermaInvio', { static: false }) modalConfermaInvio: any;

  listMsg: string[] = [];
  typeMsg: string;
  hide = false;
  salvaProseguiDisabled = false;  // tentativo di legare salva e prosegui a compilazione corretta codice fiscale
  active;
  disabled = true;
  prospetto: Prospetto;
  prospettoToSave: Prospetto;
  idNavItem = 2;
  editMode = false;
  currentActivatedId = 1;
  nextButtonText = 'Avanti';
  flagAlertMsg = false;
  resQ1: PostPutProspettoResponse;
  resQ2: RitornoPassaggioQ3;
  visualizzaBottoneInUltimaPagina = true;
  ruolo: Ruolo;
  hideButtonConfermaProsegui: boolean = true;
  disabledQ3: boolean;

  elencoStatiEsteri: StatiEsteri[] = [];

  msgResCheckDatoreLavoro: string = "";

  constructor(
    private prodisStorageService: ProdisStorageService,
    private prospettoService: ProspettoService,
    private utilitiesService: UtilitiesService,
    private datiProvincialiService: DatiProvincialiService,
    private riepilogoService: RiepilogoService,
    private decodificaService: DecodificaService,
    private modalService: NgbModal,
    private router: Router
  ) { }

  ngOnInit() {
    this.prodisStorageService.prospetto$.subscribe(item => {
      this.prospetto = item;
      if (this.prospetto) {
        this.editMode = this.prospetto.statoProspetto.descrizione === STATO_PROSPETTO_BOZZA;
      }
    });
    if (this.editMode) {
      this.nextButtonText = 'Conferma e prosegui';
      this.prodisStorageService.prospettoToSave$.subscribe(item => {
        this.prospettoToSave = item;
        console.log('on init nav dettaglio prospetto: ' + this.prospetto);
      });
      this.prodisStorageService.hideButtonConfermaProsegui$.subscribe(item =>{
        this.hideButtonConfermaProsegui = item
        console.log("conferma e prosegui"+this.hideButtonConfermaProsegui);
      });
      //this.nextButtonText = 'Conferma e prosegui';
      this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(async item => {
        if (item === true) {
          this.salvaProseguiDisabled = true;
        } else {
          this.salvaProseguiDisabled = false;
        }
      });
      this.prodisStorageService.disableQ3Btn$.subscribe(item => {
        this.disabledQ3 = item;
      });
      this.prodisStorageService.ruolo$.subscribe(item => {
        this.ruolo = item;
      });

    } else {
      this.visualizzaBottoneInUltimaPagina = false;
    }


  }

  onNavChange(changeEvent: NgbNavChangeEvent) {
    console.log(changeEvent);
    console.log('id nav item prima del change: ' + this.idNavItem);
    if (changeEvent.nextId < 3) {
      if (this.editMode) {
        this.nextButtonText = 'Conferma e prosegui';
      }
      this.idNavItem = changeEvent.nextId + 1;
      if(changeEvent.nextId === 2){
        this.resQ2 = null;
      }
      if(changeEvent.nextId < 2){
        this.resQ1 = null;
        this.resQ2 = null;
      }
    } 
    console.log('id nav item dopo il change: ' + this.idNavItem);
  }

  toggleDisabled() {
    // this.disabled = !this.disabled;
    // if (this.disabled) {
    //   this.active = 1;
    // }
  }


  async nextNav(nav: NgbNav) {
    this.currentActivatedId = nav.activeId;
    console.log('nav-dettaglio-prospetto: ' + this.currentActivatedId);
    this.pulisciErroriInPagina();
    if (this.editMode) {
      const idProspettoToSave: number = this.prospettoToSave.id;
      if (this.currentActivatedId === 1) {

        if (this.prospettoToSave.assPubbliche && this.prospettoToSave.assPubbliche.length > 0) {
          this.prospettoToSave.flgAssunzioniPubbSelezione = 'S';
        } else {
          this.prospettoToSave.flgAssunzioniPubbSelezione = 'N';
        }
        this.prospettoToSave = Utils.textTransformUpperCaseCfQ1(this.prospettoToSave);
        try {
          if (this.prospettoToSave && this.prospettoToSave.id) {
            console.log(this.prospettoToSave);
            this.resQ1 = await this.prospettoService.putProspettoUpdate(idProspettoToSave, false, this.prospettoToSave).toPromise();
          } else {
            this.resQ1 = await this.prospettoService.postProspetto(false, this.prospettoToSave).toPromise();
          }
          if (this.resQ1) {
            const resProspetto: Prospetto = this.resQ1.prospetto;
            const warnings: ApiError[] = this.resQ1.warnings;
            await this.setStatiEsteri(resProspetto);
            this.prodisStorageService.setProspetto(resProspetto);
            this.prodisStorageService.setProspettoToSave(resProspetto);
            if(warnings && warnings.length > 0){
              let elencoWarning = ""
              warnings.forEach(element => {
                elencoWarning += element.errorMessage + '<br>';
              });
              this.utilitiesService.showToastrInfoMessage(elencoWarning);
            }
            this.goToNextNav(nav);
          }
        } catch (e) {

          if (e.error && e.error.length > 0) {
            const errorResponseList = e.error;
            let elencoErrori = "";
            let elencoWarning = ""
            errorResponseList.forEach(element => {
              const cod = element.code.charAt(8);
              if(cod === "W"){
                elencoWarning += element.errorMessage + '<br>';
              }else if(cod === "E"){
                elencoErrori += element.errorMessage + '<br>';
              }
            });
            if(elencoErrori.length > 0){
              this.utilitiesService.showToastrErrorMessage(elencoErrori);
              if(elencoWarning.length > 0){
                this.utilitiesService.showToastrInfoMessage(elencoWarning);
              }
            }else{
              if(elencoWarning.length > 0){
                this.utilitiesService.showToastrInfoMessage(elencoWarning);
                this.goToNextNav(nav);
                
              }
            }

          }
        }
      } else if (this.currentActivatedId === 2) {
        try {
          this.resQ2 = await this.datiProvincialiService.getConfermaProvince(idProspettoToSave).toPromise();

          if (this.resQ2 && this.resQ2.esito) {
            const response: string = "esito: "+this.resQ2.esito + ": "+this.resQ2.messaggio;
            //da verificare attualmente si procede soltanto se l'esito vale 1
            if(this.resQ2.esito === 1){
              this.CheckPassaggioQ3(nav);
            }else{
              //se esito è != 1 lo si tratta come un errore
              this.utilitiesService.showToastrErrorMessage(response);
            }
          }
        } catch (e) {
          if (e.error && e.error.length > 0) {
            const errorResponseList = e.error;
            let elencoErrori = "";
            let elencoWarning = ""
            errorResponseList.forEach(element => {
              const cod = element.code.charAt(8);
              if(cod === "W"){
                elencoWarning += element.errorMessage + '<br>';
              }else if(cod === "E"){
                elencoErrori += element.errorMessage + '<br>';
              }
            });
            if(elencoErrori.length > 0){
              this.utilitiesService.showToastrErrorMessage(elencoErrori);
              if(elencoWarning.length > 0){
                this.utilitiesService.showToastrInfoMessage(elencoWarning);
              }
            }else{
              if(elencoWarning.length > 0){
                this.utilitiesService.showToastrInfoMessage(elencoWarning);
                this.CheckPassaggioQ3(nav);
              }
            }

          }
          console.log(e.error);
        }
      }else{
        //this.utilitiesService.showSpinner();
        this.msgResCheckDatoreLavoro = "";
        const dichiarante: Dichiarante = this.prospettoToSave.datiAzienda.dichiarante
        if(dichiarante && dichiarante.codDichiarante === COD_DICHIARANTE_DATORE_LAVORO_PUBBLICO){
          this.utilitiesService.showSpinner();
          try{
            //chiamat al servizio di checkScopertureDatoriLavoriPubblici
            this.msgResCheckDatoreLavoro = await this.prospettoService.getCheckScopertureDatoriLavoriPubblici(this.prospettoToSave.id).toPromise();
            if(!this.msgResCheckDatoreLavoro){
              this.msgResCheckDatoreLavoro = "";
            }
          }catch(e){
            if (e.error && e.error.length > 0) {
              const errorResponseList = e.error;
              let elencoErrori: string = "";
              errorResponseList.forEach(element => {
                elencoErrori += element.errorMessage + '<br>';
              });
              this.utilitiesService.showToastrErrorMessage(elencoErrori);
            }
          }finally{
            this.utilitiesService.hideSpinner();
          }
        }

        try {
          await this.modalService.open(this.modalConfermaInvio, {size: 'md', scrollable: true }).result;
        } catch (e) {
          // Rejected. Ignore and exit
          return;
        }
      }
    } else {
      this.goToNextNav(nav);
    }
  }

  private async CheckPassaggioQ3(nav: NgbNav){
    try{
      const responseCheckPassaggioQ3 = await this.prospettoService.checkPassaggioQ3(this.prospetto.id).toPromise();
      if(responseCheckPassaggioQ3){
        this.goToNextNav(nav);
      }else{
        console.log("errore");
      }
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errorResponseList = e.error;
        let elencoErrori = "";
        let elencoWarning = ""
        errorResponseList.forEach(element => {
          const cod = element.code.charAt(8);
          if(cod === "W"){
            elencoWarning += element.errorMessage + '<br>';
          }else if(cod === "E"){
            elencoErrori += element.errorMessage + '<br>';
          }
        });
        if(elencoErrori.length > 0){
          this.utilitiesService.showToastrErrorMessage(elencoErrori);
          if(elencoWarning.length > 0){
            this.utilitiesService.showToastrInfoMessage(elencoWarning);
          }
        }else{
          if(elencoWarning.length > 0){
            this.utilitiesService.showToastrInfoMessage(elencoWarning);
            this.goToNextNav(nav);
          }
        }

      }
    }
  }

  private pulisciErroriInPagina() {
    this.listMsg = [];
    this.typeMsg = null;
    this.hide = false;
  }

  private goToNextNav(nav: NgbNav) {
    nav.select(this.idNavItem);
    const activeId = nav.activeId;
    if (activeId < 3) {
      this.idNavItem = activeId + 1;
    }
    if(this.editMode && activeId === 3){
      this.nextButtonText = "Conferma e invia"
    }
  }

  private async setStatiEsteri(prospetto: Prospetto){
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
       // if (cf.length === 11 || cf.length === 16) {
       //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
       // } else if (cf.length > 16) {
       //   const siglaNazione = cf.substring(0, 2);
       //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
       // }
       prospetto.datiAzienda.statiEsteri =  statoEsteroCorrente;
     }
  }

  async inviaProspetto(modal: any){
    this.utilitiesService.showSpinner();
    modal.dismiss();
    await this.setStatiEsteri(this.prospettoToSave);
    let cfStudioprofessionale = this.prospettoToSave.cfStudioProfessionale;
    if(cfStudioprofessionale && cfStudioprofessionale !== ""){
      this.prospettoToSave.cfStudioProfessionale = cfStudioprofessionale.toUpperCase();
    }
    if(!this.prospettoToSave.soggetti){
      this.prospettoToSave.soggetti = {} as Soggetti;
    }
    const confermaRiepilogoToSend: ConfermaRiepilogoProspetto = {
      prospetto: this.prospettoToSave,
      ruolo: this.ruolo
    }
    try{
      const responseQ3: ConfermaRiepilogo = await this.riepilogoService.confermaRiepilogo(this.prospettoToSave.id,confermaRiepilogoToSend).toPromise();
      if(responseQ3){
        this.prodisStorageService.setEsitoInvioProspetto(responseQ3);
        this.utilitiesService.hideSpinner();
        this.router.navigateByUrl('/esito-invio-prospetto');
      }
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errorResponseList = e.error;
        let elencoErrori = "";
        errorResponseList.forEach(element => {
            elencoErrori += element.errorMessage + '<br>';
        });
        this.utilitiesService.showToastrErrorMessage(elencoErrori);
      }
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

}
