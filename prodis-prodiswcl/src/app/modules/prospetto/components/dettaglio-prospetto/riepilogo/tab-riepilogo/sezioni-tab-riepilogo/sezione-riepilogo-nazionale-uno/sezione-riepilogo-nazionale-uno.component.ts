/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { Prospetto } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-sezione-riepilogo-nazionale-uno',
  templateUrl: './sezione-riepilogo-nazionale-uno.component.html',
  styleUrls: ['./sezione-riepilogo-nazionale-uno.component.scss']
})
export class SezioneRiepilogoNazionaleUnoComponent implements OnInit {
  @Input () prospetto: Prospetto;
  constructor() { }

  ngOnInit() {
  }

}
