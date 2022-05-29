/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Inject, Input, LOCALE_ID, OnInit } from '@angular/core';
import { TIPO_LAV_INTERM } from 'src/app/constants';
import { DatiProvinciali, DatiProvincialiService, PartTime, Prospetto, ProvIntermittenti } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';
import { CustomObj } from '../form-tab-part-time-interm/form-tab-part-time-interm.component';

@Component({
  selector: 'prodis-tab-part-time-interm',
  templateUrl: './tab-part-time-interm.component.html',
  styleUrls: ['./tab-part-time-interm.component.scss']
})
export class TabPartTimeIntermComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  elencoLavoratorInterm: ProvIntermittenti[] = [];
  elencoLavoratoriPartT: PartTime[] = [];
  elencoCustomObj: CustomObj[] = [];
  selectedItem: CustomObj;
  constructor(
    private datiProvincialiService: DatiProvincialiService,
    @Inject(LOCALE_ID) public locale: string,
  ) { }

  async ngOnInit() {
    // this.listPartTime = await this.datiProvincialiService.getPartTimeByIdProspettoProv(this.datiProvinciali.id).toPromise();
    // if (this.listPartTime && this.listPartTime.length > 0) {
    //   this.selectedItem = this.listPartTime[0];
    // }
    const [lavPartTime, lavInterm] = await Promise.all([
      this.datiProvincialiService.getPartTimeByIdProspettoProv(this.datiProvinciali.id).toPromise(),
      this.datiProvincialiService.getProvIntermittentiByIdProspettoProv(this.datiProvinciali.id).toPromise()
    ]);
    this.elencoLavoratoriPartT = lavPartTime;
    this.elencoLavoratorInterm = lavInterm;
    this.mergeLists();
    if (this.elencoCustomObj && this.elencoCustomObj.length > 0) {
      this.selectedItem = this.elencoCustomObj[0];
    }
  }

  private mergeLists() {
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
  }

  getTime(val: number): string {
    if (val) {
    return Utils.getTimeFormat(val);
    } else {
      return '00:00';
    }
   // return this.utilitiesService.getTimeFormat(val);
  }

  private pTToCustomObj(pt: PartTime): CustomObj {
    let obj: CustomObj;
    obj = {
      id: pt.id,
      tipoLav: pt.tipoRipropPt,
      orarioSettSv: pt && pt.orarioSettPartTimeMin ? this.getTime(pt.orarioSettPartTimeMin) : '00:00',
      orarioSettContr: pt && pt.orarioSettContrattualeMin ? this.getTime(pt.orarioSettContrattualeMin) : '00:00',
      nLav: pt.nPartTime,
    };
    return obj;
  }

  private intermToCustomObj(interm: ProvIntermittenti): CustomObj {
    let obj: CustomObj;
    obj = {
      id: interm.id,
      tipoLav: TIPO_LAV_INTERM,
      orarioSettSv: interm && interm.orarioSettimanaleSvolto ? this.getTime(interm.orarioSettimanaleSvolto) : '00:00',
      orarioSettContr: interm && interm.orarioSettimanaleContrattual ? this.getTime(interm.orarioSettimanaleContrattual) : '00:00',
      nLav: interm.nIntermittenti
    };
    return obj;
  }


}
