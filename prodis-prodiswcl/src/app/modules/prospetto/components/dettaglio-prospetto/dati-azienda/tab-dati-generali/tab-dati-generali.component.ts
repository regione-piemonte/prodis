/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { STATO_PROSPETTO_BOZZA, TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { Atecofin, Ccnl, Comune, DecodificaGenerica, DecodificaService, Dichiarante, Prospetto, SedeLegale, StatiEsteri } from 'src/app/modules/prodisapi';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { PromptModalDecodificaService } from 'src/app/modules/prodiscommon/services/prompt-modal-decodifica.service';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';


@Component({
  selector: 'prodis-tab-dati-generali',
  templateUrl: './tab-dati-generali.component.html',
  styleUrls: ['./tab-dati-generali.component.scss']
})
export class TabDatiGeneraliComponent implements OnInit {

  prospetto: Prospetto;
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
  }

  constructor() { }

  async ngOnInit() {

  }

  formatCf(flg: string,cf : string): string{
    if(flg && flg === "S"){
      if(cf && cf !== ""){
        const siglaNaz = cf.substr(0,2);
        if(siglaNaz !== "IT" ){
          cf = cf.substring(2,cf.length);
        }
      }
    }
    return cf;
  }


}
