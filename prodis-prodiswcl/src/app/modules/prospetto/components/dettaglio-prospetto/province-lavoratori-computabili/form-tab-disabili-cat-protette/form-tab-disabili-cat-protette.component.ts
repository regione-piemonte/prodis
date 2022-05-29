/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DatiProvinciali } from 'src/app/modules/prodisapi';
import { UserService } from 'src/app/services';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-form-tab-disabili-cat-protette',
  templateUrl: './form-tab-disabili-cat-protette.component.html',
  styleUrls: ['./form-tab-disabili-cat-protette.component.scss']
})
export class FormTabDisabiliCatProtetteComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();
  newDatiProv: DatiProvinciali;
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
    this.initForm();
    // this.form.patchValue(this.newDatiProv);
    this.triggerUiUpdate();
  }

  form: FormGroup;
  get f() {return this.form.controls as any; }
  constructor(
    private usersService: UserService
  ) { }

  ngOnInit() {

  }

  private initForm() {
    this.form = new FormGroup({
      nDisabiliInForza: new FormControl( this.newDatiProv.nDisabiliInForza,
        Validators.compose([
          Validators.required
        ])),
      nCentralTelefoNonvedenti: new FormControl(
        this.newDatiProv.nCentralTelefoNonvedenti,
        Validators.compose([
          Validators.required
        ])),
      nPostiPrevCentraliNonved: new FormControl(
        this.newDatiProv.nPostiPrevCentraliNonved,
        Validators.compose([
          Validators.required
        ])),
      nTerariabMassofisNonved: new FormControl(
        this.newDatiProv.nTerariabMassofisNonved,
        Validators.compose([
          Validators.required
        ])),
      nPostiPrevMassofisNonved: new FormControl(
        this.newDatiProv.nPostiPrevMassofisNonved,
        Validators.compose([
          Validators.required
        ])),
        nCateProtForza: new FormControl(
          this.newDatiProv.nCateProtForza,
          Validators.compose([
            Validators.required
          ])),
        nCateProtForzaA17012000: new FormControl(
          this.newDatiProv.nCateProtForzaA17012000,
          Validators.compose([
            Validators.maxLength(5),
            Validators.required
          ])),
        nSomministratiFt: new FormControl(
          this.newDatiProv.nSomministratiFt,
          Validators.required
        ),
        nConvenzioni12bis14Ft: new FormControl(
          this.newDatiProv.nConvenzioni12bis14Ft,
          Validators.required
        ),
    });
  }

  private triggerUiUpdate() {
    this.usersService.triggerUiUpdate();
  }

  onClickReset() {
    this.form.reset();
    this.form.patchValue(this.datiProvinciali);
    this.triggerUiUpdate();
    this.mapValues();
    this.saveDatiProvinciali.emit(this.newDatiProv);
  }

  onChange() {
    this.mapValues();
    this.saveDatiProvinciali.emit(this.newDatiProv);
   }

   salvaInBozza() {
     this.mapValues();
     this.saveDatiProvincialiBozza.emit(this.newDatiProv);
   }

   mapValues() {
     this.newDatiProv.nDisabiliInForza = this.form.controls.nDisabiliInForza.value;
     this.newDatiProv.nCentralTelefoNonvedenti = this.form.controls.nCentralTelefoNonvedenti.value;
     this.newDatiProv.nPostiPrevCentraliNonved = this.form.controls.nPostiPrevCentraliNonved.value;
     this.newDatiProv.nTerariabMassofisNonved = this.form.controls.nTerariabMassofisNonved.value;
     this.newDatiProv.nPostiPrevMassofisNonved = this.form.controls.nPostiPrevMassofisNonved.value;
     this.newDatiProv.nCateProtForza = this.form.controls.nCateProtForza.value;
     this.newDatiProv.nCateProtForzaA17012000 = this.form.controls.nCateProtForzaA17012000.value;
     this.newDatiProv.nSomministratiFt = this.form.controls.nSomministratiFt.value;
     this.newDatiProv.nConvenzioni12bis14Ft = this.form.controls.nConvenzioni12bis14Ft.value;
   }

}
