/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { Prospetto, ProspettoProvincia, Ruolo } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';


@Component({
  selector: 'prodis-tab-riepilogo',
  templateUrl: './tab-riepilogo.component.html',
  styleUrls: ['./tab-riepilogo.component.scss']
})
export class TabRiepilogoComponent implements OnInit {
  paramIdProspetto: number;
  prospetto: Prospetto;
  ilRuoloUtente: Ruolo;
  SONO_AMMINISTRATORE: boolean;

  @Input () ilProspettoProvincia: ProspettoProvincia[];
  loaded = false;
  constructor(
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService
  ) { }


  ngOnInit() {
    this.utilitiesService.showSpinner();
    this.prodisStorageService.prospetto$.subscribe(async item =>{
      this.prospetto = item;
    });
    this.prodisStorageService.ruolo$.subscribe(async item => {
      this.ilRuoloUtente = item;
    });
    if (this.ilRuoloUtente.amministratore) {
      this.SONO_AMMINISTRATORE = true;
    }

    this.utilitiesService.hideSpinner();
  }
}
