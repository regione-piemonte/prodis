/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output, SimpleChanges } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { COMUNICAZIONE_RETTIFICA_ID, PARAMETRO_TERMINE_ANNULLAMENTO } from 'src/app/constants';
import { CommonService, Prospetto } from 'src/app/modules/prodisapi';
import { UserService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';


@Component({
  selector: 'prodis-form-tab-dati-prospetto',
  templateUrl: './form-tab-dati-prospetto.component.html',
  styleUrls: ['./form-tab-dati-prospetto.component.scss']
})


export class FormTabDatiProspettoComponent implements OnInit, OnDestroy {

  cfDisabled= true;
  prospetto: Prospetto;
  idProspetto: number;
  termineAnnullamento: Date;
  flagForm: boolean;
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
  }
  newProspetto: Prospetto;
  @Input() set inputNewProspetto(newProspetto: Prospetto) {
    this.newProspetto = newProspetto;
    this.idProspetto = this.prospetto ? this.prospetto.id : null;
    this.setForm(this.newProspetto);
  }

  @Output() readonly newDatiProspetto = new EventEmitter<Prospetto>();
  @Output() readonly salvaInBozzaDatiProspetto = new EventEmitter<Prospetto>();
  form: FormGroup;

  flagRequired: boolean;

  // restituisce formControls
  get f() { return this.form.controls as any; }

  // set bottoneBottoneConfermaProsegui(formInvalid){
  //   this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(this.invalidForm())
  // }

  disableDataRifProsp: boolean = false;

  constructor(
    private prodisStorageService: ProdisStorageService,
    private commonService: CommonService,
    private userService: UserService
  ) { }

  async ngOnInit() {
    const res = await this.commonService.getParametro(PARAMETRO_TERMINE_ANNULLAMENTO).toPromise();
    const arr = res.codDecodifica.split('/', 3);
    const anno = +arr[2];
    const mese = +arr[1] - 1;
    const giorno = +arr[0];
    const termineAnnullamento = new Date(anno, mese, giorno);
    this.termineAnnullamento = termineAnnullamento;
  }

  private setForm(prospetto: Prospetto) {
    this.disableDataRifProsp = prospetto.comunicazione ? prospetto.comunicazione.id === COMUNICAZIONE_RETTIFICA_ID : false;
    this.form = new FormGroup({
        dataRiferimentoProspetto: new FormControl((prospetto && prospetto.dataRiferimentoProspetto ? new Date(prospetto.dataRiferimentoProspetto) : null)),
      flgSospensionePerMobilita: new FormControl(prospetto && prospetto.flgSospensionePerMobilita ? prospetto.flgSospensionePerMobilita : null),
      dFineSospensioneQ1: new FormControl(prospetto && prospetto.dFineSospensioneQ1 ? new Date(prospetto.dFineSospensioneQ1) : null),
      prospettoGradualita: new FormGroup({
        id: new FormControl(prospetto? prospetto.id : null),
        dataAtto: new FormControl(prospetto
          && prospetto.prospettoGradualita
          && prospetto.prospettoGradualita.dataAtto ?
          new Date(prospetto.prospettoGradualita.dataAtto) :
          null),
        estremiAtto: new FormControl(prospetto
          && prospetto.prospettoGradualita
          && prospetto.prospettoGradualita.estremiAtto ?
          prospetto.prospettoGradualita.estremiAtto :
          null),
        dataTrasformazione: new FormControl(prospetto
          && prospetto.prospettoGradualita
          && prospetto.prospettoGradualita.dataTrasformazione ?
          prospetto.prospettoGradualita.dataTrasformazione :
          null),
        percentuale: new FormControl(prospetto &&
          prospetto.prospettoGradualita &&
          prospetto.prospettoGradualita.percentuale ?
          prospetto.prospettoGradualita.percentuale :
          null),
        nAssunzioniLavPreTrasf: new FormControl(prospetto
          && prospetto.prospettoGradualita
          && prospetto.prospettoGradualita.nAssunzioniLavPreTrasf ?
          prospetto.prospettoGradualita.nAssunzioniLavPreTrasf :
          null)
      })
    });
    this.setValidatorDataFineSosp();
    this.flagForm = true;
    this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(async item => {
      this.cfDisabled = item;
    });
    console.log(this.f.dataRiferimentoProspetto);
    console.log(this.f.dataRiferimentoProspetto);
    if( this.disableDataRifProsp){
      this.f.dataRiferimentoProspetto.disable();
    }
  }

  onClickReset() {
    this.form.reset();
    this.setForm(this.prospetto);
    this.saveValue();
    this.setValidatorDataFineSosp();
  }

  ngOnDestroy() {
    this.saveValue();
  }

  ngOnChanges(changes: SimpleChanges) {
    console.log("ngOnChanges" + changes);
    if (changes.newProspetto && !changes.newProspetto.isFirstChange()) {
      this.newProspetto = changes.newProspetto.currentValue;
    }
  }

  saveValue() {
    let newProsp: Prospetto = this.form.getRawValue() as Prospetto;
    this.newProspetto.dataRiferimentoProspetto = newProsp.dataRiferimentoProspetto;
    this.newProspetto.flgSospensionePerMobilita = newProsp.flgSospensionePerMobilita;
    this.newProspetto.dFineSospensioneQ1 = newProsp.dFineSospensioneQ1;
    this.newProspetto.prospettoGradualita = newProsp.prospettoGradualita;
    this.newDatiProspetto.emit(newProsp);
  }

  onClickConferma() {
    let newProsp: Prospetto = this.form.getRawValue() as Prospetto;
    this.newProspetto.dataRiferimentoProspetto = newProsp.dataRiferimentoProspetto;
    this.newProspetto.flgSospensionePerMobilita = newProsp.flgSospensionePerMobilita;
    this.newProspetto.prospettoGradualita = newProsp.prospettoGradualita;
    this.prodisStorageService.setProspettoToSave(this.newProspetto);
    this.salvaInBozzaDatiProspetto.emit(newProsp);
  }

  onChange() {
    this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(this.form.invalid);
    if(!this.form.invalid){
      this.saveValue();
    }
  }

  datePickerChange(date: NgbDate) {
    setTimeout(() => this.saveValue(), 50);
  }

  private checkDataRifP() {
    const sysDate: Date = new Date();
    const annoCorrente = sysDate.getFullYear();
    const annoTermAnnullamento = this.termineAnnullamento.getFullYear();
    if (annoCorrente <= annoTermAnnullamento) {
      const annoPrecedente = annoCorrente - 1;
      const correctDate = new Date('31/12/' + annoPrecedente);
      this.form.controls.dataRiferimentoProspetto.setValue(correctDate);
    } else if (annoCorrente > annoTermAnnullamento) {

    }
  }

  onChangeFlagAssPubbl() {
    this.setValidatorDataFineSosp();
    this.saveValue();
  }

  setValidatorDataFineSosp() {
    const valueflag = this.form.controls.flgSospensionePerMobilita.value;
    const control = this.form.controls.dFineSospensioneQ1;
    control.clearValidators();
    if (valueflag === 'S') {
      control.setValidators(Validators.required);
      control.updateValueAndValidity();
      //this.flagRequired = true;
    } else {
      control.updateValueAndValidity();
      //this.flagRequired = false;
    }
    this.userService.triggerUiUpdate();
  }


}

function dataRifProspValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: boolean } | null => {
    //  const controlValue = this.form.controls.dataRiferimentoProspetto.value;
    //  if(controlValue){
    //    const selectedDate: Date = new Date(controlValue
    //    if(this.termineAnnullamento && selectedDate){
    //      const selectedYear = selectedDate.getFullYear();
    //      const annoTermAnnullamento = this.termineAnnullamento.getFullYear();
    //      const annoCorrente = new Date().getFullYear();
    //      if(selectedYear)
    //    }

    //  if(annoCorrente && annoTermAnnullamento){
    //    if(annoCorrente > annoTermAnnullamento){
    //      return {dataIntervalvalidate: true}
    //    }
    //  }
    return null;
  };
}
