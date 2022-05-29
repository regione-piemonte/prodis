/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { formatNumber } from '@angular/common';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Component, EventEmitter, Inject, Input, LOCALE_ID, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TIPO_LAV_INTERM, TYPE_ALERT_DANGER } from 'src/app/constants';
import { DatiProvinciali, DatiProvincialiService, DecodificaGenerica, DecodificaService, PartTime, ProvIntermittenti, TipoRipropPt } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';

@Component({
  selector: 'prodis-form-tab-part-time-interm',
  templateUrl: './form-tab-part-time-interm.component.html',
  styleUrls: ['./form-tab-part-time-interm.component.scss']
})
export class FormTabPartTimeIntermComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();
  newDatiProv: DatiProvinciali;
  listMsg: string[] = [];
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
  }
  form: FormGroup;
  formElenco: FormGroup;
  comboTipologiaLav: TipoRipropPt[] = [];

  // comboTipol: OggCustom[] = [];
  elencoLavoratorInterm: ProvIntermittenti[] = [];
  elencoLavoratoriPartT: PartTime[] = [];
  elencoCustomObj: CustomObj[] = [];
  selectedItem: CustomObj;
  flagTabReady: boolean;

  get f() { return this.form.controls as any; }



  constructor(
    private decodificaService: DecodificaService,
    private utilitiesService: UtilitiesService,
    private datiProvincialiService: DatiProvincialiService,
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    const listRes = await this.decodificaService.postTipologiaLavoratoreDecodifica({} as DecodificaGenerica).toPromise();
    listRes.forEach(item => {
      this.comboTipologiaLav.push({
        id: item.idDecodifica,
        dsTipoRipropPt: item.dsDecodifica
      });
    });
    this.comboTipologiaLav.push(TIPO_LAV_INTERM);
    await this.leggiComponiLista();
    this.initForm();
    this.utilitiesService.hideSpinner();
  }

  private async leggiComponiLista() {
    const [lavPartTime, lavInterm] = await Promise.all([
      this.datiProvincialiService.getPartTimeByIdProspettoProv(this.datiProvinciali.id).toPromise(),
      this.datiProvincialiService.getProvIntermittentiByIdProspettoProv(this.datiProvinciali.id).toPromise()
    ]);
    this.elencoLavoratoriPartT = lavPartTime;
    this.elencoLavoratorInterm = lavInterm;
    this.mergeLists();
  }

  private mergeLists() {
    this.elencoCustomObj = [];
    if (this.elencoLavoratoriPartT && this.elencoLavoratoriPartT.length > 0) {
      this.elencoLavoratoriPartT.forEach(item => {
        this.elencoCustomObj.push(this.pTToCustomObj(item));
      });
    }
    if (this.elencoLavoratorInterm && this.elencoLavoratorInterm.length > 0) {
      this.elencoLavoratorInterm.forEach(item => {
        this.elencoCustomObj.push(this.intermToCustomObj(item));
      });
    }
    this.flagTabReady = true;
  }

  private initForm() {
    this.form = new FormGroup({
      id: new FormControl(),
      tipoLav: new FormControl(),
      orarioSettSv: new FormControl('',
      Validators.pattern(/^([0-9][0-9]):[0-5][0-9]$/)),
      orarioSettContr: new FormControl('',
      Validators.pattern(/^([0-9][0-9]):[0-5][0-9]$/)),
      nLav: new FormControl()
    });
    this.formElenco = new FormGroup({
      item: new FormControl()
    });
  }

  onClickChange(el) {
    if (el.tipoLav && el.tipoLav.id === -1) {
      el.tipoLav = TIPO_LAV_INTERM;
    } else {
      const tipoLav = this.comboTipologiaLav.find(item => item.id === el.tipoLav.id);
      el.tipoLav = tipoLav;
    }
    this.selectedItem = el;
    // this.selectedItem.orarioSettContr = this.getTime(el.orarioSettContr);
    // this.selectedItem.orarioSettSv = this.getTime(el.orarioSettSv);
    this.form.patchValue(this.selectedItem);
  }

  onClickReset() {
    this.listMsg = [];
    this.pulisciErroriInPagina();
    this.resetDelForm();
    this.initForm();
  }

  private resetDelForm() {
    this.selectedItem = null;
    this.form.reset();
    this.formElenco.reset();
  }

  async onSubmit() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    try {
      const formValue = this.form.getRawValue() as CustomObj;
      const tipoLav = formValue.tipoLav;
      if (!tipoLav) {
        this.listMsg.push('Tipologia lavoratore obbligatoria <BR>');
        this.utilitiesService.hideSpinner();
        this.settaErroriInPagina();
        return;
      }
      if (tipoLav && tipoLav.id >= 0) {
        // sono un part-time
        const partTimeToSend: PartTime = {
          id: formValue.id,
          idProspettoProv: this.newDatiProv.id,
          nPartTime: formValue.nLav,
          orarioSettContrattualeMin: this.getMinute(formValue.orarioSettContr),
          orarioSettPartTimeMin: this.getMinute(formValue.orarioSettSv),
          tipoRipropPt: { id: formValue.tipoLav.id },
        };
        let response: PartTime;
        if (partTimeToSend.id) {
          let idOld;
          if(this.selectedItem.tipoLav.id === -1){
            idOld = this.selectedItem.id;
            partTimeToSend.id = null;
            response = await this.datiProvincialiService.postPartTime(this.newDatiProv.id, partTimeToSend, idOld).toPromise();
          }else{
            response = await this.datiProvincialiService.putPartTime(this.newDatiProv.id, partTimeToSend.id, partTimeToSend).toPromise();
          }
        } else {
          response = await this.datiProvincialiService.postPartTime(this.newDatiProv.id, partTimeToSend).toPromise();
        }

        if (response) {
          await this.leggiComponiLista();
          console.log('success');
          this.resetDelForm();
        }
      } else {
        // sono un intermittente
        const intermToSend: ProvIntermittenti = {
          id: formValue.id,
          idProspettoProv: this.newDatiProv.id,
          nIntermittenti: formValue.nLav,
          orarioSettimanaleContrattual: this.getMinute(formValue.orarioSettContr),
          orarioSettimanaleSvolto: this.getMinute(formValue.orarioSettSv)
        };
        let response: ProvIntermittenti;
        if (intermToSend.id) {
          let idOld;
          if(this.selectedItem.tipoLav.id >= 0){
            idOld = this.selectedItem.id;
            intermToSend.id = null;
            response = await this.datiProvincialiService.postIntermittenti(this.datiProvinciali.id, intermToSend, idOld).toPromise();
          }else{
            response = await this.datiProvincialiService.putIntermittenti(this.datiProvinciali.id, intermToSend.id, intermToSend).toPromise();
          }
        } else {
          response = await this.datiProvincialiService.postIntermittenti(this.datiProvinciali.id, intermToSend).toPromise();
        }
        if (response) {
          await this.leggiComponiLista();
          console.log('success');
          this.resetDelForm();
        }
      }
    } catch (e) {
      this.pulisciErroriInPagina();
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

  private pTToCustomObj(pt: PartTime): CustomObj {
    let obj: CustomObj;
    obj = {
      id: pt.id,
      tipoLav: this.comboTipologiaLav.find(item => item.id === pt.tipoRipropPt.id),
      orarioSettSv: this.getTime(pt.orarioSettPartTimeMin),
      orarioSettContr: this.getTime(pt.orarioSettContrattualeMin),
      nLav: pt.nPartTime,
    };
    return obj;
  }

  salvaInBozza() {
    this.saveDatiProvincialiBozza.emit(this.newDatiProv);
  }

  async deletePtInterm() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const itemToDelete: CustomObj = this.selectedItem;
    const tipoLav = itemToDelete.tipoLav;
    try {
      let res: any;
      if (tipoLav && tipoLav.id >= 0) {
        // sono un part-time
        res = await this.datiProvincialiService.deletePartTime(itemToDelete.id).toPromise();
      } else {
        // sono un intermittente
        res = await this.datiProvincialiService.deleteIntermittenti(itemToDelete.id).toPromise();
      }
      if (res) {
        const indexToDelete: number = this.elencoCustomObj.findIndex(el => el.id === res.id);
        if (indexToDelete >= 0) {
          this.elencoCustomObj.splice(indexToDelete, 1);
          this.onClickReset();
        }
        console.log('success');
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

  private intermToCustomObj(interm: ProvIntermittenti): CustomObj {
    let obj: CustomObj;
    obj = {
      id: interm.id,
      tipoLav: TIPO_LAV_INTERM,
      orarioSettSv: this.getTime(interm.orarioSettimanaleSvolto),
      orarioSettContr: this.getTime(interm.orarioSettimanaleContrattual),
      nLav: interm.nIntermittenti
    };
    return obj;
  }

  getTime(val: number): string {
     return this.utilitiesService.getTimeFormat(val);
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

  getMinute(valore: string) {
      const  oreEMinuti =  valore.split(':');
      // if (oreEMinuti.length < 2
      //   || !isNumber(parseInt(oreEMinuti[0]))
      //   || !isNumber(parseInt(oreEMinuti[1])
      //   || (parseInt(oreEMinuti[1])>60) )) {
      //   throw new Error('Formato non corretto, il formato deve essere ore:minuti (esempio 40:00, 7:05 ecc.)');
      // }
      const ore = parseInt(oreEMinuti[0]);
      const oreInMinuti = ore * 60;
      const minutiTotali = oreInMinuti + parseInt(oreEMinuti[1]);
      return minutiTotali;
  }
}

export class CustomObj {
  id?: number;
  tipoLav?: TipoRipropPt;
  orarioSettSv?: string;
  orarioSettContr?: string;
  nLav?: number;
}
