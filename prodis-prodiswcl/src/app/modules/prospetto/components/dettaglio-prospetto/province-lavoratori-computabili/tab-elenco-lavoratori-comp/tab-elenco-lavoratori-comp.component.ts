/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { DatiProvinciali, DatiProvincialiService, LavoratoriInForza } from 'src/app/modules/prodisapi';
import { CategoriaAssunzione } from 'src/app/modules/prodiscommon/components/categoriaAssunzione';
import { CategoriaSoggetto } from 'src/app/modules/prodiscommon/components/categoriaSoggetto';
import { UtilitiesService } from 'src/app/services';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-tab-elenco-lavoratori-comp',
  templateUrl: './tab-elenco-lavoratori-comp.component.html',
  styleUrls: ['./tab-elenco-lavoratori-comp.component.scss']
})

export class TabElencoLavoratoriCompComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  elencoLavoratoriInForza: LavoratoriInForza[];
  currentItem: LavoratoriInForza;

  constructor(
    private datiProvincialiService: DatiProvincialiService,
    private utilitiesService: UtilitiesService
  ) { }

  async ngOnInit() {
    this.elencoLavoratoriInForza = await this.datiProvincialiService.getLavoratoriInForzaByIdProspettoProv(this.datiProvinciali.id).toPromise();

    if (this.elencoLavoratoriInForza && this.elencoLavoratoriInForza.length > 0) {
      this.currentItem = this.elencoLavoratoriInForza[0];

    }
  }

  getDescrCatSoggetto(cod: string): string {
    return CategoriaSoggetto.getDescrizioneByCodice(cod);
  }

  getDescrCatAssunz(cod: string): string {
    return CategoriaAssunzione.getDescrizioneByCodice(cod);
  }

  getTime(val: number): string {
    return Utils.getTimeFormat(val);
  }

  onChangeItem(item: LavoratoriInForza) {
    this.currentItem = item;

  }
}
