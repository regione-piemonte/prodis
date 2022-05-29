/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DecodificaService, SedeLegale } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';

@Component({
  selector: 'prodis-lista-sedi-modal',
  templateUrl: './lista-sedi-modal.component.html',
  styleUrls: ['./lista-sedi-modal.component.scss']
})
export class ListaSediModalComponent implements OnInit {

  
  @Input() title: string;
  @Input() list: SedeLegale[];
  @Input() callback;
  @Input() modal;
  @Input() cfAzienda;


  form: FormGroup;

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  constructor() { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.form = new FormGroup({
      sedeLegale: new FormControl()
    });
  }

  onClickConferma(){
    const selectedItem = this.form.controls.sedeLegale.value;
    this.callback(this.modal, selectedItem);
    console.log(selectedItem);
  }


}
