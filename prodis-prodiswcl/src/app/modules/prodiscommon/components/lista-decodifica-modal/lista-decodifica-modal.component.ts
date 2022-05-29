/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { DecodificaGenerica, DecodificaService } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';

@Component({
  selector: 'prodis-lista-decodifica-modal',
  templateUrl: './lista-decodifica-modal.component.html',
  styleUrls: ['./lista-decodifica-modal.component.scss']
})
export class ListaDecodificaModalComponent {

  @Input() title: string;
  @Input() list: DecodificaGenerica[];
  @Input() filtroDiPartenza: DecodificaGenerica;
  @Input() callback;
  @Input() modal;
  @Input() typeSearch: string;
  searchList: DecodificaGenerica[];
  formGroupDecodifica: FormGroup;
  formRicerca: FormGroup;

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  constructor(
    private utilitiesService: UtilitiesService,
    private decoDificaService: DecodificaService
  ) { }

  ngOnInit() {
    this.initForm();


  }

  private initForm() {
    this.formGroupDecodifica = new FormGroup({
      decodifica: new FormControl()
    });
    this.formRicerca = new FormGroup({
      codDecodifica: new FormControl(this.filtroDiPartenza ? this.filtroDiPartenza.codDecodifica : null),
      dsDecodifica: new FormControl(this.filtroDiPartenza ? this.filtroDiPartenza.dsDecodifica : null)
    });

  }

  onClickConferma(){
    const selectedItem = this.formGroupDecodifica.controls.decodifica.value;
    this.callback(this.modal, selectedItem);
    console.log(selectedItem);
  }

  onClickCerca(){
    this.formGroupDecodifica.reset();
    switch(this.typeSearch){
      case TYPE_DECODIFICA_GENERICA.ATECO:
        this.findSettoreAteco();
        break;
      case TYPE_DECODIFICA_GENERICA.CCNL:
        this.findCcnl();
        break;
      case TYPE_DECODIFICA_GENERICA.COMUNE:
          this.findComune();
          break;
      case TYPE_DECODIFICA_GENERICA.QUAL_ISTAT:
          this.findQualifica();
          break;
      case TYPE_DECODIFICA_GENERICA.STATI_ESTERI:
        this.findStatiEsteri();
        break;
      default:
    }
  }

  private async findCcnl() {
    this.utilitiesService.showSpinner();
    const decodifica: DecodificaGenerica = this.formRicerca.getRawValue() as DecodificaGenerica;
    try{
      this.list = await this.decoDificaService.postCcnlDecodifica(decodifica).toPromise();
    }catch(e){

    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private async findSettoreAteco(){
    this.utilitiesService.showSpinner();
    const decodifica: DecodificaGenerica = this.formRicerca.getRawValue() as DecodificaGenerica;
    try{
      this.list = await this.decoDificaService.postAtecofinDecodifica(decodifica).toPromise();
    }catch(e){

    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private async findComune(){
    this.utilitiesService.showSpinner();
    let decodifica: DecodificaGenerica = this.formRicerca.getRawValue() as DecodificaGenerica;
    if(this.filtroDiPartenza && this.filtroDiPartenza.idFiltroFacoltativo){
      decodifica.idFiltroFacoltativo = this.filtroDiPartenza.idFiltroFacoltativo;
    }
    try{
      this.list = await this.decoDificaService.postComuneDecodifica(decodifica).toPromise();
    }catch(e){

    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private async findQualifica(){
    this.utilitiesService.showSpinner();
    const decodifica: DecodificaGenerica = this.formRicerca.getRawValue() as DecodificaGenerica;
    try{
      this.list = await this.decoDificaService.postQualificaDecodifica(decodifica).toPromise();
    }catch(e){

    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private async findStatiEsteri(){
    this.utilitiesService.showSpinner();
    const decodifica: DecodificaGenerica = this.formRicerca.getRawValue() as DecodificaGenerica;
    try{
      this.list = await this.decoDificaService.postStatiEsteriDecodifica(decodifica).toPromise();
    }catch(e){

    }finally{
      this.utilitiesService.hideSpinner();
    }
  }


}
