/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AssPubbliche, DecodificaGenerica, DecodificaService, Regione } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-form-tab-assunzioni-pubbl-sel',
  templateUrl: './form-tab-assunzioni-pubbl-sel.component.html',
  styleUrls: ['./form-tab-assunzioni-pubbl-sel.component.scss']
})
export class FormTabAssunzioniPubblSelComponent implements OnInit {
  cfDisabled= true;
  elencoAssunzioni: AssPubbliche[];
  @Input() set inputElencoAssunzioni(elenco:  AssPubbliche[]){
    if(!elenco){
      this.elencoAssunzioni = [];
    }else{
      this.elencoAssunzioni = elenco;
    }
  }
  @Output() readonly newElencoAssunzioni = new EventEmitter<AssPubbliche[]>();
  @Output() readonly salvaInBozzaAssunzioniPubbliche = new EventEmitter<AssPubbliche[]>();
  elencoRegioniDec: DecodificaGenerica[] = [];
  elencoRegioni: Regione[] = []
  formAssPubbliche: FormGroup;
  formElencoAssPubb: FormGroup;
  flagForm: boolean = false;
  selectedItem: AssPubbliche;

  get f() {return this.formAssPubbliche.controls as any}
  constructor(
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private prodisStorageService: ProdisStorageService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    const [
         regioni
    ] = await Promise.all([
      this.decodificaService.postRegioneDecodifica({} as DecodificaGenerica).toPromise()
    ]);
    this.elencoRegioniDec = regioni;
    this.elencoRegioniDec.forEach(item => {
      this.elencoRegioni.push({
        id: item.idDecodifica,
        dsProTRegione: item.dsDecodifica
      })
    });
    this.initForm();
    this.utilitiesService.hideSpinner();
  }



  private initForm(){
    this.formAssPubbliche = new FormGroup({
      id: new FormControl(),
      regione: new FormControl(null,
        Validators.required
      ),
      saldoDisabili: new FormControl(null,Validators.required),
      saldoExArt18: new FormControl(null,Validators.required),
      dsNote: new FormControl(),
    });
    this.formElencoAssPubb = new FormGroup({
      item: new FormControl()
    });
    this.flagForm = true;
    this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(async item => {
      this.cfDisabled = item;
    });

  }

  // disableRegione(): boolean{
  //   this.elencoRegioni.forEach(reg => {
  //     this.newElencoAssunzioni.forEach(assP)
  //   });
  // }
  onClickReset(){
    this.selectedItem = null;
    this.formAssPubbliche.reset();
    this.formElencoAssPubb.reset();
    //this.initForm();
  }

  conferma(){
    const assPubbl = this.formAssPubbliche.getRawValue() as AssPubbliche;
    if(this.selectedItem){
      const index = this.elencoAssunzioni.indexOf(this.selectedItem);
      this.elencoAssunzioni[index] = assPubbl;
    }else{
      let listTmp: AssPubbliche[] = [];
      listTmp.push(assPubbl);
      this.elencoAssunzioni.forEach(assP => listTmp.push(assP));
      this.elencoAssunzioni = listTmp;
    }

    this.newElencoAssunzioni.emit(this.elencoAssunzioni);
    this.onClickReset();
  }

  setItemInForm(item: AssPubbliche){
    this.selectedItem = item;
    this.formAssPubbliche.patchValue(this.selectedItem);
    this.formAssPubbliche.controls.regione.patchValue(this.elencoRegioni.find(item=> item.id == this.selectedItem.regione.id));
    this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(async item => {
      this.cfDisabled = item;
    });

  }

  onClickSalvaInBozza(){
    this.salvaInBozzaAssunzioniPubbliche.emit(this.elencoAssunzioni);
  }

  onClickCancella(){
    if(this.selectedItem){
      const index: number = this.elencoAssunzioni.indexOf(this.selectedItem);
      if(index >= 0){
        this.elencoAssunzioni.splice(index,1);
        this.onClickReset();
        this.newElencoAssunzioni.emit(this.elencoAssunzioni);
      }
    }
  }
}
