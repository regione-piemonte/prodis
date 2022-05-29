/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { DatiProvincialiService, ElencoProvScoperture, Prospetto, ProspettoProvincia } from 'src/app/modules/prodisapi';
import { CategoriaCompensazione } from 'src/app/modules/prodiscommon/components/categoriaCompensazione';
import { CategoriaSoggetto } from 'src/app/modules/prodiscommon/components/categoriaSoggetto';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';
import { isNullOrUndefined } from 'util';
export interface DettaglioCompensazione {
  id: number;
  dsProvincia: string;
  dsProvinciaPerCuiSiCompensa: string;
  categoriaCompensazione: string;
  numeroLavoratori: number;
  categoriaSoggetto: string;
  cfAziendaDelGruppo: string;
  statoConcessione: string;
  eccedenza: number;
  riduzione: number;
}
@Component({
  selector: 'prodis-tab-compensazioni',
  templateUrl: './tab-compensazioni.component.html',
  styleUrls: ['./tab-compensazioni.component.scss']
})
export class TabCompensazioniComponent implements OnInit {
  @Input() ilProspettoProvincia: ProspettoProvincia[];
  selectedItem: DettaglioCompensazione;
  elencoTab: Array<DettaglioCompensazione> = [];
  prospetto: Prospetto;
  elencoProvScoperture : ElencoProvScoperture[];

  constructor(
    private prodisStorageService : ProdisStorageService,
    private datiProvincialiService : DatiProvincialiService,
    private utilitiesService : UtilitiesService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    this.ilProspettoProvincia.forEach(ilProspetto => {
      if (ilProspetto.datiProvinciali.provCompensazionis) {
        ilProspetto.datiProvinciali.provCompensazionis.forEach(laCompensazione => {
          this.elencoTab.push({
            id: laCompensazione.id,
            dsProvinciaPerCuiSiCompensa: ilProspetto.provincia.dsProTProvincia,
            dsProvincia: laCompensazione.provincia.dsProTProvincia,
            categoriaCompensazione: CategoriaCompensazione.getDescrizioneByCodice(laCompensazione.categoriaCompensazione),
            numeroLavoratori: laCompensazione.nLavoratori,
            categoriaSoggetto: CategoriaSoggetto.getDescrizioneByCodice(laCompensazione.categoriaSoggetto),
            cfAziendaDelGruppo: laCompensazione.cfAziendaAppartenAlGruppo,
            statoConcessione: !isNullOrUndefined(laCompensazione.statoConcessione)? laCompensazione.statoConcessione.descStatoConcessione: null,
            eccedenza: laCompensazione.categoriaCompensazione === 'E'? laCompensazione.nLavoratori: null,
            riduzione: laCompensazione.categoriaCompensazione === 'R'? laCompensazione.nLavoratori: null
          });
        });
      }
    });

    this.prodisStorageService.prospetto$.subscribe(async item =>{
      this.prospetto = item;
    });
    this.elencoProvScoperture =  await this.datiProvincialiService.getElencoScopertureByIdProspetto(this.prospetto.id).toPromise();
    this.utilitiesService.hideSpinner();
  }

  onClickChange(el) {
    this.selectedItem = el;
  }

  getAbsoluteValue(value: number): number{
    return Utils.getAbsoluteVale(value);
  }

}
