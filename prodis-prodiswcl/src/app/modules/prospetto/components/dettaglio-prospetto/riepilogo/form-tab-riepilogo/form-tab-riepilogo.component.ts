/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { ConfermaRiepilogoProspetto, DecodificaGenerica, DecodificaService, Prospetto, ProspettoProvincia, ProspettoService, RiepilogoService, Ruolo, Soggetti } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';



@Component({
  selector: 'prodis-form-tab-riepilogo',
  templateUrl: './form-tab-riepilogo.component.html',
  styleUrls: ['./form-tab-riepilogo.component.scss']
})
export class FormTabRiepilogoComponent implements OnInit, OnDestroy {
  prospetto: Prospetto;
  newProspetto: Prospetto;
  idProspetto: number;
  termineAnnullamento: Date;
  flagForm: boolean;
  flgVisitaIspettiva: string;
  flgNessunaAssunzioneAggiun: string;
  flgComunicazionePerDatore = false;
  ilRuoloUtente: Ruolo;
  elencoAltriSoggettiDec: DecodificaGenerica[] = [];
  elencoAltriSoggetti: Soggetti[] = [];
  flgFasciaAzienda: boolean = false;
  conditionCompensazioni: boolean;

  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
    this.flgFasciaAzienda = this.prospetto.categoriaAzienda && this.prospetto.categoriaAzienda.codCategoriaAzienda === "C" ? false : true;
  }
  @Input() set inputNewProspetto(newProspetto: Prospetto) {
    this.newProspetto = newProspetto;
    if(this.newProspetto && !this.newProspetto.soggetti){
      this.newProspetto.soggetti = {} as Soggetti;
    }
    if(this.flgFasciaAzienda){
      this.newProspetto.dataPrimaAssunzione = null;
      this.newProspetto.dataSecondaAssunzione = null;
      this.newProspetto.flgNessunaAssunzioneAggiun = this.flgNessunaAssunzioneAggiun = null;
      this.prospetto.dataPrimaAssunzione = null;
      this.prospetto.dataSecondaAssunzione = null;
      this.prodisStorageService.setProspettoToSave(this.newProspetto);
    }
  }
  
  @Input() ilProspettoProvincia: ProspettoProvincia[];
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly salvaBozzaRiepilogo = new EventEmitter<Prospetto>();

  SONO_AMMINISTRATORE: boolean;

  listMsg: string[] = [];
  flgInvalid: boolean = false;

  constructor(
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private riepilogoService: RiepilogoService
  ) { }

  ngOnDestroy(): void {
    setTimeout( ()=> {
      this.prodisStorageService.setDisableQ3Btn(false), //qui
      100
    });
  }

  invalidDate(flg: boolean): string{
    this.flgInvalid = flg;
    return "Data non valida"
  }


  async ngOnInit() {
    this.utilitiesService.showSpinner();
    setTimeout(() => {
      this.prodisStorageService.setHideButtonConfermaProsegui(true),
      100
    });
    this.prodisStorageService.ruolo$.subscribe(async item => {
      this.ilRuoloUtente = item;
    });
    if (this.ilRuoloUtente.amministratore) {
      this.SONO_AMMINISTRATORE = true;
    }
    this.setFlagForm()


    const [
      soggetti
    ] = await Promise.all([
      this.decodificaService.postSoggettiDecodifica({} as DecodificaGenerica).toPromise()
    ]);
    this.elencoAltriSoggettiDec = soggetti;
    this.elencoAltriSoggettiDec.forEach(item => {
      this.elencoAltriSoggetti.push({
        id: item.idDecodifica,
        descSoggetti: item.dsDecodifica
      })
    });
    this.checkComp();
    this.utilitiesService.hideSpinner();
  }

  onClickReset() {
    this.prodisStorageService.setProspettoToSave(this.prospetto);
    this.newProspetto = Utils.clone(this.prospetto);
    console.log(this.newProspetto.soggetti);
    this.setFlagForm();
    
  }

  private setFlagForm(){ //////////////////////////////////////////////////////////////////
    if (this.prospetto) {
      if (this.prospetto.flgVisitaIspettiva === 'S') {
        this.flgVisitaIspettiva = "S";
      }else if(this.prospetto.flgVisitaIspettiva === 'N') {
        this.flgVisitaIspettiva = "N";
      }
      if (this.prospetto.flgNessunaAssunzioneAggiun === 'S') {
        this.flgNessunaAssunzioneAggiun = "S";
      }else if(this.prospetto.flgNessunaAssunzioneAggiun === 'N'){
        this.flgNessunaAssunzioneAggiun = "N";
      }
    }
  }

  svuotaCampi(){
    this.listMsg = [];
    this.pulisciErroriInPagina();
    this.newProspetto.soggetti.id = null;
    this.newProspetto.cfStudioProfessionale = null;
  }

  onClickChangeflgNessunaAssunzione(){
    this.newProspetto.flgNessunaAssunzioneAggiun = this.flgNessunaAssunzioneAggiun;
    this.onClickChange();
  }
  onClickChangeflgVisitaIspettiva(){
    this.newProspetto.flgVisitaIspettiva = this.flgVisitaIspettiva;
    this.onClickChange();
  }

  datePickerChange(date: NgbDate) {
    setTimeout(() => this.onClickChange(), 50);
  }

  onClickChange(){
    this.prodisStorageService.setDisableQ3Btn(this.flgInvalid || this.conditionCompensazioni);
    if(!this.flgInvalid){
      this.prodisStorageService.setProspettoToSave(this.newProspetto);
    }
  }

  onClickConferma() {
    //invio prospetto
    this.pulisciErroriInPagina();
    //  let tmp: Soggetti = {
    //    id:  (this.newProspetto.soggetti ? parseInt(this.newProspetto.soggetti as string) : null)
    // }
    //this.newProspetto.soggetti = tmp;
    this.newProspetto.flgNessunaAssunzioneAggiun = this.flgNessunaAssunzioneAggiun;
    this.newProspetto.flgVisitaIspettiva = this.flgVisitaIspettiva;
    this.salvaBozzaRiepilogo.emit(this.newProspetto);
  }

  private async checkComp(){
    this.listMsg = [];
    try {
      const res = await this.riepilogoService.confermaTornaRiepilogo(this.prospetto.id).toPromise();
      if(res){
        this.conditionCompensazioni = false;
        setTimeout(() => {
          this.prodisStorageService.setDisableQ3Btn(this.conditionCompensazioni), // qui
          100
        });
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        const listErrors = e.error;
        listErrors.forEach(er => {
          this.listMsg.push(er.errorMessage);
        });
      }
      this.settaErroriInPagina();
      this.conditionCompensazioni = true;
        setTimeout(() => {
          this.prodisStorageService.setDisableQ3Btn(this.conditionCompensazioni), // qui
          100
        });
       
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  private settaErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_DANGER,
      hide: true,
      listMsg: this.listMsg
    });
  }

  private pulisciErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

}
