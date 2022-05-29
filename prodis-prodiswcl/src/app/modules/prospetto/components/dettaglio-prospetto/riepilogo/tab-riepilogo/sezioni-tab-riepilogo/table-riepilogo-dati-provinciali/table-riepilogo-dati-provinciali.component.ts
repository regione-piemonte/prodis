/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { ProspettoProvincia } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-table-riepilogo-dati-provinciali',
  templateUrl: './table-riepilogo-dati-provinciali.component.html',
  styleUrls: ['./table-riepilogo-dati-provinciali.component.scss']
})
export class TableRiepilogoDatiProvincialiComponent implements OnInit {

  @Input () ilProspettoProvincia: ProspettoProvincia[];

  constructor() { }

  ngOnInit() {
    this.ilProspettoProvincia;
  }

}
