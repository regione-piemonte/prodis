/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup } from '@angular/forms';
import { TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS } from 'src/app/constants';
import { ProspettoService, ReinviaProspetto, Ruolo } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-recupero-codice-reg',
  templateUrl: './recupero-codice-reg.component.html',
  styleUrls: ['./recupero-codice-reg.component.scss']
})
export class RecuperoCodiceRegComponent implements OnInit {

  form: FormGroup;
  ruolo: Ruolo;
  esito: string;

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  get f() {return this.form.controls as any}
  get conditionControlsEnable(): boolean {return (!this.f.idList.value || this.f.idList.value ==="") &&  
                                                (!this.f.protocolloDataDa.value || this.f.protocolloDataDa.value ==="") &&
                                                (!this.f.protocolloDataA.value || this.f.protocolloDataA.value ==="") &&
                                                (!this.f.numMax.value || this.f.numMax.value ==="");
                                        }
                                        
  constructor(
    private utilitiesService: UtilitiesService,
    private prodisStorageService: ProdisStorageService,
    private prospettoService: ProspettoService
  ) { }

  ngOnInit() {
    this.utilitiesService.showSpinner();
    this.prodisStorageService.ruolo$.subscribe(item => this.ruolo = item);
    this.initForm();
    this.utilitiesService.hideSpinner();
  }

  private initForm(){
    this.form = new FormGroup({
      idList: new FormControl(),
      protocolloDataDa: new FormControl(),
      protocolloDataA: new FormControl(),
      numMax: new FormControl()
    });
  }

  onClickReset(){
    this.esito = null;
    this.pulisciListaErrori();
    this.hide=false;
    this.form.reset();
    this.form.enable();
  }

  onKeyDown(control: AbstractControl){
    Object.keys(this.form.controls).forEach((key: string) => {
      const formControl: AbstractControl = this.form.get(key);
      if( formControl !== control){
        formControl.disable();
      }
    });
  }

  onKeyDownData(){
    this.disableControlsExcepDates()
  }

  disableControlsExcepDates(){
    this.f.idList.disable();
    this.f.numMax.disable();
  }

  onKeyUp(){
    if(this.conditionControlsEnable){
      this.form.enable();
    }
  }

  async onSubmit(){
    this.utilitiesService.showSpinner();
    this.esito = null;
    this.pulisciListaErrori();
    const ids = this.f.idList.value;
    let idList: string[];
    if(ids && ids.length > 0 ){
      idList = ids.split(",");
    }
    try{
      const reinviaProspettoToSend: ReinviaProspetto = {
        ruolo: this.ruolo,
        idsProspetti: idList,
        maxNTrasmissioni: this.f.numMax.value,
        dataInizio: this.f.protocolloDataDa.value,
        dataFine: this.f.protocolloDataA.value
      };
      this.esito = await this.prospettoService.reinviaProspetto(reinviaProspettoToSend).toPromise();
    }catch(e){
      if (e.error && e.error.length > 0) {
        const errors = e.error;
        errors.forEach(element => {
          this.listMsg.push(element.errorMessage);
        });
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
      }
      console.log(e.error);
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  async onClickStampa(){
    this.utilitiesService.showSpinner();
    this.pulisciListaErrori();
    const ids = this.f.idList.value;
    if(!ids || ids.lenght <= 0){
      this.listMsg.push("Inserire almeno un id di un prospetto");
      this.typeMsg = TYPE_ALERT_DANGER;
      this.hide = true;
      this.utilitiesService.hideSpinner();
      return;
    }
    let idList: string[];
    if(ids && ids.length > 0 ){
      idList = ids.split(",");
    }
    try{
      const res = await this.prospettoService.salvaPdf(idList).toPromise();
      if(res){
        this.listMsg.push(res);
        this.typeMsg = TYPE_ALERT_SUCCESS;
        this.hide = true;
      }
    }catch(e){
      const errors = e.error;
        errors.forEach(element => {
          this.listMsg.push(element.errorMessage);
        });
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private pulisciListaErrori() {
    this.typeMsg = '';
    this.hide = false;
    this.listMsg = [];
  }

}
