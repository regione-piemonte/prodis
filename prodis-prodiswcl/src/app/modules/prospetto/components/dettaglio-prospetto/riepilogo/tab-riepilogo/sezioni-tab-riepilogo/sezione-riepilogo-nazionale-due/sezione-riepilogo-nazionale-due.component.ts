/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { Prospetto } from 'src/app/modules/prodisapi';

@Component({
  selector: 'prodis-sezione-riepilogo-nazionale-due',
  templateUrl: './sezione-riepilogo-nazionale-due.component.html',
  styleUrls: ['./sezione-riepilogo-nazionale-due.component.scss']
})
export class SezioneRiepilogoNazionaleDueComponent implements OnInit {

  @Input () prospetto: Prospetto;
  constructor() { }

  ngOnInit() {
  }

}
