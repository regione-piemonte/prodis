/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { DatiProvinciali, ProspettoProvSede } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-tab-dettaglio-provinciale',
  templateUrl: './tab-dettaglio-provinciale.component.html',
  styleUrls: ['./tab-dettaglio-provinciale.component.scss']
})
export class TabDettaglioProvincialeComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;

  constructor() { }

  ngOnInit() {
  }

}
