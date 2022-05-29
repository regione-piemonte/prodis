/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { DatiProvinciali } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-tab-altre-concessioni',
  templateUrl: './tab-altre-concessioni.component.html',
  styleUrls: ['./tab-altre-concessioni.component.scss']
})
export class TabAltreConcessioniComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali

  constructor() { }

  ngOnInit() {
  }

}
