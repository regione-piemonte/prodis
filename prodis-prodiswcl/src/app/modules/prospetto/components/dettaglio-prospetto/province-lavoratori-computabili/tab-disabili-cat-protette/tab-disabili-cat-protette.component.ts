/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { DatiProvinciali } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-tab-disabili-cat-protette',
  templateUrl: './tab-disabili-cat-protette.component.html',
  styleUrls: ['./tab-disabili-cat-protette.component.scss']
})
export class TabDisabiliCatProtetteComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;

  constructor() { }

  ngOnInit() {
  }

}
