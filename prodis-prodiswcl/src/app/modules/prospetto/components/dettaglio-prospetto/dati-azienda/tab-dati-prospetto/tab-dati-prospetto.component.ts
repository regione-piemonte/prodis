/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { Prospetto } from 'src/app/modules/prodisapi';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-tab-dati-prospetto',
  templateUrl: './tab-dati-prospetto.component.html',
  styleUrls: ['./tab-dati-prospetto.component.scss']
})
export class TabDatiProspettoComponent implements OnInit {

  prospetto: Prospetto;
  @Input() set inputProspetto(prospetto: Prospetto){
    this.prospetto = prospetto;
  }
  constructor(
    private prodisStorageService: ProdisStorageService
  ) {}

  ngOnInit() {
    // this.prodisStorageService.prospetto$.subscribe(item =>{ 
    //   this.prospetto = item; 
    // });
    console.log(this.prospetto);
  }

}
