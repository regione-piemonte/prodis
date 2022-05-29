/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { ProspettoProvincia } from 'src/app/modules/prodisapi';
import { CategoriaAssunzione } from 'src/app/modules/prodiscommon/components/categoriaAssunzione';
import { CategoriaSoggetto } from 'src/app/modules/prodiscommon/components/categoriaSoggetto';


export interface PostoDisponibile {
  id: number;
  dsProvincia: string;
  mansione: string;
  dsCapacita: string;
  flgBarriere: string;
  flgTurni: string;
  flgMezziPubblici: string;
  targa: string;
  qualifica: string;
  nPosti: number;
  categoriaSoggetto: string;
  dsComune: string;
  categoriaAssunzione: string;
}
@Component({
  selector: 'prodis-tab-posti-disp',
  templateUrl: './tab-posti-disp.component.html',
  styleUrls: ['./tab-posti-disp.component.scss']
})
export class TabPostiDispComponent implements OnInit {
  @Input() ilProspettoProvincia: ProspettoProvincia[];
  selectedItem: PostoDisponibile;
  elencoTab: Array<PostoDisponibile> = [];
  constructor() { }

  ngOnInit() {
    this.ilProspettoProvincia.forEach(ilProspetto => {
      if (ilProspetto.datiProvinciali.postiLavoroDisps) {
        ilProspetto.datiProvinciali.postiLavoroDisps.forEach(ilPostoDisponibile => {
          this.elencoTab.push({
            id: ilPostoDisponibile.id,
            dsProvincia: ilProspetto.provincia.dsProTProvincia,
            mansione: ilPostoDisponibile.descMansione,
            dsCapacita: ilPostoDisponibile.descCapacitaRichiesteContr,
            flgBarriere: ilPostoDisponibile.flgPresenzaBarriereArchite,
            flgTurni: ilPostoDisponibile.flgTurniNotturni,
            flgMezziPubblici: ilPostoDisponibile.flgRaggiungibMezziPubblici,
            targa: ilProspetto.provincia.dsTarga,
            qualifica: ilPostoDisponibile.istat2001livello5 ? 
                  ilPostoDisponibile.istat2001livello5.codIstat2001livello5Min+" - "+ilPostoDisponibile.istat2001livello5.dsComIstat2001livello5
                        :
                  "",
            nPosti: ilPostoDisponibile.nPosti,
            categoriaSoggetto: CategoriaSoggetto.getDescrizioneByCodice(ilPostoDisponibile.categoriaSoggetto),
            dsComune: ilPostoDisponibile.comune.codComuneMin+" - "+ilPostoDisponibile.comune.dsProTComune,
            categoriaAssunzione: CategoriaAssunzione.getDescrizioneByCodice(ilPostoDisponibile.categoriaAssunzione)
          });
        });
      }
    });

  }

  onClickChange(el) {
    this.selectedItem = el;
  }

}
