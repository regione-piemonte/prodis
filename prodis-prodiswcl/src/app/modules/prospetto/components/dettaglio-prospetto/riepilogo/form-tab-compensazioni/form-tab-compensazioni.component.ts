/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { DatiProvincialiService, DecodificaService, ElencoProvScoperture, Prospetto, ProspettoProvincia, ProvCompensazioni, Provincia, RiepilogoService } from 'src/app/modules/prodisapi';
import { CategoriaCompensazione, CategoriaCompensazioneInterface } from 'src/app/modules/prodiscommon/components/categoriaCompensazione';
import { CategoriaSoggetto, CategoriaSoggettoInterface } from 'src/app/modules/prodiscommon/components/categoriaSoggetto';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';
import { isNullOrUndefined } from 'util';
import { DettaglioCompensazione } from '../tab-compensazioni/tab-compensazioni.component';

@Component({
  selector: 'prodis-form-tab-compensazioni',
  templateUrl: './form-tab-compensazioni.component.html',
  styleUrls: ['./form-tab-compensazioni.component.scss']
})
export class FormTabCompensazioniComponent implements OnInit, OnDestroy {

  elencoTab: Array<DettaglioCompensazione> = [];
  elencoProvScoperture: ElencoProvScoperture[];
  selectedItem: DettaglioCompensazione;

  prospetto: Prospetto;
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
  }
  ilProspettoProvincia: ProspettoProvincia[];
  @Input() set inputIlProspettoProvincia(list: ProspettoProvincia[]){
    this.ilProspettoProvincia = list;
    this.initCompensazioni();
    this.caricaElencoProvScoperture();
    if(this.form){
      this.form.reset();
    }
  }
  @Output() readonly saveCompensazioni = new EventEmitter<ProvCompensazioni>();
  @Output() readonly loadCompensazioni = new EventEmitter<any>();
  @Output() readonly erroriInPagina = new EventEmitter<any>();

  form: FormGroup;
  formElencoComp: FormGroup;

  comboProvince: Provincia[] = [];
  comboCategoriaSogg: CategoriaSoggettoInterface[] = CategoriaSoggetto.get();
  comboCategoriaComp: CategoriaCompensazioneInterface[] = CategoriaCompensazione.get();

  listMsg: string[] = [];

  // restituisce form control
  get f() {return this.form.controls as any; }

  constructor(
    private utilitiesService: UtilitiesService,
    private prodisStorageService: ProdisStorageService,
    private datiProvincialiService: DatiProvincialiService,
    private decodificaService: DecodificaService,
    private riepilogoService: RiepilogoService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    setTimeout( ()=> {
      this.prodisStorageService.setHideButtonConfermaProsegui(false),
      100
    });
    try {
      this.prodisStorageService.prospetto$.subscribe(async item =>{
        this.prospetto = item;
      });
      this.elencoProvScoperture =  await this.datiProvincialiService.getElencoScopertureByIdProspetto(this.prospetto.id).toPromise();
      this.comboProvince = await this.decodificaService.getProvincia().toPromise();
      this.initForm();
    } catch (e) {

    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  ngOnDestroy(): void {
    setTimeout( ()=> {
      this.prodisStorageService.setHideButtonConfermaProsegui(true),
      100
    });
  }

  private initCompensazioni(){
    this.elencoTab = [];
    this.ilProspettoProvincia.forEach(ilProspetto => {
      if (ilProspetto.datiProvinciali.provCompensazionis) {
        ilProspetto.datiProvinciali.provCompensazionis.forEach(laCompensazione => {
          this.elencoTab.push({
            id: laCompensazione.id,
            dsProvinciaPerCuiSiCompensa: ilProspetto.provincia.dsProTProvincia,
            dsProvincia: laCompensazione.provincia.dsProTProvincia ,
            categoriaCompensazione: CategoriaCompensazione.getDescrizioneByCodice(laCompensazione.categoriaCompensazione),
            numeroLavoratori: laCompensazione.nLavoratori,
            categoriaSoggetto: CategoriaSoggetto.getDescrizioneByCodice(laCompensazione.categoriaSoggetto),
            cfAziendaDelGruppo: laCompensazione.cfAziendaAppartenAlGruppo,
            statoConcessione: !isNullOrUndefined(laCompensazione.statoConcessione)? laCompensazione.statoConcessione.descStatoConcessione: null,
            eccedenza: laCompensazione.categoriaCompensazione === 'E' ? laCompensazione.nLavoratori : null,
            riduzione: laCompensazione.categoriaCompensazione === 'R' ? laCompensazione.nLavoratori : null
          });
        });
      }
    });
  }

   private async caricaElencoProvScoperture(){
    this.elencoProvScoperture =  await this.datiProvincialiService.getElencoScopertureByIdProspetto(this.prospetto.id).toPromise();
  }

  /*modello il form come provCompensazione */
  private initForm() {
    this.form = new FormGroup({
      id: new FormControl(),
      provincia: new FormControl(null, Validators.required),
      categoriaCompensazione: new FormControl(null, Validators.required),
      categoriaSoggetto: new FormControl(null, Validators.required),
      nLavoratori: new FormControl(null, Validators.required),
      prospettoProv: new FormControl(null, Validators.required),
      cfAziendaAppartenAlGruppo: new FormControl({value: null, disabled: false})
    });
    this.formElencoComp = new FormGroup({
      radioEl: new FormControl()
    });
  }

  setSelectedItem(item: DettaglioCompensazione){
    this.selectedItem = item;
    this.f.id.setValue(item.id); // id compensazione
    const prospettoProv = this.ilProspettoProvincia.find(el => el.provincia.dsProTProvincia === item.dsProvinciaPerCuiSiCompensa);
    this.f.prospettoProv.patchValue(prospettoProv); // da qui si ottiene l'id ProspettoProv associato alla provincia per cui si compensa
    const prov = this.comboProvince.find(el => el.dsProTProvincia === item.dsProvincia);
    this.f.provincia.patchValue(prov);
    const catComp = this.comboCategoriaComp.find(el => el.descrizione === item.categoriaCompensazione);
    this.f.categoriaCompensazione.setValue(catComp.codice);
    this.f.nLavoratori.setValue(item.numeroLavoratori);
    const catSogg = this.comboCategoriaSogg.find(el => el.descrizione === item.categoriaSoggetto);
    this.f.categoriaSoggetto.setValue(catSogg.codice);
    /** Sostituito perche' non esiste piu' il prospetto all'interno del prospetto provincia*/
    // const datiAzienda = prospettoProv.prospetto.datiAzienda;
    const datiAzienda = this.prospetto.datiAzienda;
    const codDichiarante = datiAzienda.dichiarante.codDichiarante;
    if (codDichiarante === 'D' ) {
      this.f.cfAziendaAppartenAlGruppo.enable();
    }
    this.f.cfAziendaAppartenAlGruppo.setValue(item.cfAziendaDelGruppo);
  }

  getValueChecked(provId: number){
      return this.ilProspettoProvincia.find(prospProv => prospProv.provincia.id === provId);
  }

  onClickReset() {
    this.pulisciErroriInPagina();
    this.form.reset();
    this.formElencoComp.reset();
    this.selectedItem = null;
  }

  conferma() {
    const compensazioneToSend: ProvCompensazioni = this.getCompensazioneByForm();
    this.saveCompensazioni.emit(compensazioneToSend);
    this.formElencoComp.reset();
    this.selectedItem = null;
  }

  private getCompensazioneByForm(): ProvCompensazioni{
    const compensazione: ProvCompensazioni = {} as ProvCompensazioni;
    compensazione.id = this.f.id.value;
    const idProspettoProv = this.f.prospettoProv.value.datiProvinciali.id;
    compensazione.idProspettoProv = idProspettoProv;
    compensazione.provincia = this.f.provincia.value;
    compensazione.categoriaCompensazione = this.f.categoriaCompensazione.value;
    compensazione.nLavoratori = this.f.nLavoratori.value;
    compensazione.categoriaSoggetto = this.f.categoriaSoggetto.value;
    compensazione.cfAziendaAppartenAlGruppo = this.f.cfAziendaAppartenAlGruppo.value;
    return compensazione;
  }

  private getDettaglioCompensazioneByProvCompensazioni(provCom: ProvCompensazioni): DettaglioCompensazione{
    const dettaglioComp: DettaglioCompensazione = {} as DettaglioCompensazione;
    dettaglioComp.id = provCom.id;
    const prospettoProv = this.ilProspettoProvincia.find(item => item.datiProvinciali.id === provCom.idProspettoProv);
    dettaglioComp.dsProvinciaPerCuiSiCompensa = provCom.provincia.dsProTProvincia;
    dettaglioComp.dsProvincia = prospettoProv.provincia.dsProTProvincia;
    dettaglioComp.categoriaCompensazione = provCom.categoriaCompensazione ? CategoriaCompensazione.getDescrizioneByCodice(provCom.categoriaCompensazione) : null;
    dettaglioComp.numeroLavoratori  = provCom.nLavoratori;
    dettaglioComp.categoriaSoggetto = provCom.categoriaSoggetto ? CategoriaSoggetto.getDescrizioneByCodice(provCom.categoriaSoggetto) : null;
    dettaglioComp.cfAziendaDelGruppo = provCom.cfAziendaAppartenAlGruppo,
    dettaglioComp.statoConcessione = !isNullOrUndefined(provCom.statoConcessione) ? provCom.statoConcessione.descStatoConcessione : null;
    dettaglioComp.eccedenza = provCom.categoriaCompensazione === 'E' ? provCom.nLavoratori : null,
    dettaglioComp.riduzione = provCom.categoriaCompensazione === 'R' ? provCom.nLavoratori : null;
    return dettaglioComp;
  }


  // async onClickConfermaComp(){
  //   this.utilitiesService.showSpinner();
  //   this.listMsg = [];
  //   this.pulisciErroriInPagina();
  //   try {
  //     const res = this.riepilogoService.confermaTornaRiepilogo(this.prospetto.id).toPromise();
  //   } catch (e) {
  //     if (e.error && e.error.lnegth > 0) {
  //       const listErrors = e.error;
  //       listErrors.forEach(err => {
  //         this.listMsg.push(err.errorMessage);
  //       });
  //     }
  //     this.settaErroriInPagina();
  //   } finally {
  //     this.utilitiesService.hideSpinner();
  //   }
  // }

  async deleteCompensazioni(){
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const itemToDelete = this.selectedItem;

    try {

      const res  = await this.riepilogoService.deleteCompensazioni(itemToDelete.id).toPromise();
      if (res) {
        const esitoSP = await this.riepilogoService.storeProcedureEseguiCalcoli(this.prospetto.id,this.prospetto.codUserAggiorn,false).toPromise();
          if(esitoSP.esito === 1){
            this.loadCompensazioni.emit();
          }else{
            this.listMsg.push(esitoSP.messaggio);
           this.settaErroriInPagina();
          }
        this.onClickReset();
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaErroriInPagina();
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  getAbsoluteValue(value: number): number{
    return Utils.getAbsoluteVale(value);
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
