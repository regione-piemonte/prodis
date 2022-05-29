/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { PaginationDataChange } from 'src/app/models';
import { CategorieEscluse, DatiProvinciali, DatiProvincialiService, PagedResponseCategorieEscluse } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-tab-telelav-cat-escluse',
  templateUrl: './tab-telelav-cat-escluse.component.html',
  styleUrls: ['./tab-telelav-cat-escluse.component.scss']
})

export class TabTelelavCatEscluseComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  currentPaginationData: PaginationDataChange;
  pagedResponse: PagedResponseCategorieEscluse;
  flagRicercaEffettuata: boolean = false;
  selectedItem: CategorieEscluse;
  categorieEscluse: CategorieEscluse[];


  constructor(
    private datiProvincialiService: DatiProvincialiService
  ) { }

  async ngOnInit() {
    this.categorieEscluse = await this.datiProvincialiService.getCategorieEscluseByIdProspettoProv(this.datiProvinciali.id).toPromise();
    // this.categorieEscluse = this.datiProvinciali.categorieEscluses;
    if(this.categorieEscluse && this.categorieEscluse.length > 0){
      this.selectedItem = this.categorieEscluse[0];
    }
  }

  onClickChange(el){
    this.selectedItem = el;
    console.log(el);
  }

}
