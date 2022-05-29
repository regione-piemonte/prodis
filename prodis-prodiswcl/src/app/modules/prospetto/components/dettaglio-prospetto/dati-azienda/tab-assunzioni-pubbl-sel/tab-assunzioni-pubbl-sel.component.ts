/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { AssPubbliche, Prospetto, ProspettoService } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-tab-assunzioni-pubbl-sel',
  templateUrl: './tab-assunzioni-pubbl-sel.component.html',
  styleUrls: ['./tab-assunzioni-pubbl-sel.component.scss']
})
export class TabAssunzioniPubblSelComponent implements OnInit {

  prospetto: Prospetto;
  elencoAssunzioniDelProspetto: AssPubbliche[] = [];
  constructor(
    private prospettoService: ProspettoService,
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    this.prodisStorageService.prospetto$.subscribe(item =>{
      this.prospetto = item;
    });
    console.log("prospetto: ");
    const [elencoAssunzioni
      // , regione
      // , ccnl
      // , ateco
    ] = await Promise.all([
      this.prospettoService.getAssunzioniPubblicheByIdProspetto(this.prospetto.id).toPromise()
    ]);

    this.elencoAssunzioniDelProspetto = elencoAssunzioni;

    let assunzione = this.elencoAssunzioniDelProspetto[0];
    this.utilitiesService.hideSpinner();
  }

  get elencoAssunzioni(): AssPubbliche[] {
    return this.elencoAssunzioniDelProspetto;
  }

}
