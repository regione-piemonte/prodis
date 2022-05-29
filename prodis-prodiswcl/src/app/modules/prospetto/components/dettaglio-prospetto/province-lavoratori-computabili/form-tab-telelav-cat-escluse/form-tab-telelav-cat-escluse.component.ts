/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import BigNumber from 'bignumber.js';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { CategoriaEscluse, CategorieEscluse, DatiProvinciali, DatiProvincialiService, DecodificaGenerica, DecodificaService } from 'src/app/modules/prodisapi';
import { UserService, UtilitiesService } from 'src/app/services';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-form-tab-telelav-cat-escluse',
  templateUrl: './form-tab-telelav-cat-escluse.component.html',
  styleUrls: ['./form-tab-telelav-cat-escluse.component.scss']
})
export class FormTabTelelavCatEscluseComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();
  newDatiProv: DatiProvinciali;
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
  }
  form: FormGroup;
  formSelectedItem: FormGroup;
  formLav: FormGroup;
  selectedItem: CategorieEscluse;
  categorieEscluse: CategorieEscluse[] = [];
  comboCategorieEscluse: OggCustom[] = [];
  prevIdCat: number;

  listMsg: string[] = [];

  // restituisce formControls
  get f() { return this.form.controls as any; }

  constructor(
    private decodificaService: DecodificaService,
    private utilitiesService: UtilitiesService,
    private datiProvincialiService: DatiProvincialiService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    try {
      this.categorieEscluse = await this.datiProvincialiService.getCategorieEscluseByIdProspettoProv(this.datiProvinciali.id).toPromise();
      const res: DecodificaGenerica[] = await this.decodificaService.postCategoriaEscluseDecodifica({} as DecodificaGenerica).toPromise();
      this.setCombocategorieEscluse(res);
      this.initForm();
      this.formLav.patchValue(this.newDatiProv);
    } catch (e) {
      if (e.error && e.error.length > 0) {
        // this.typeMsg = TYPE_ALERT_DANGER;
        // this.hide = true;
        // e.error.forEach(element => {
        //   this.listMsg.push(element.errorMessage);
        // });
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  // private async getList(){
  //   this.categorieEscluse = await this.datiProvincialiService.getCategorieEscluseByIdProspettoProv(this.datiProvinciali.id).toPromise();
  // }

  private setCombocategorieEscluse(list: DecodificaGenerica[]) {
    list.forEach(cat => {
      this.comboCategorieEscluse.push({
        item: {
          id: cat.idDecodifica,
          codCategoriaEscluse: cat.codDecodifica,
          desCategoriaEscluse: cat.dsDecodifica
        },
        disable: (this.categorieEscluse.find(el => el.categoriaEscluse.id === cat.idDecodifica)) ? true : false
      });
    });
  }

  private initForm() {
    this.formLav = new FormGroup({
      nTelelavoroFt: new FormControl((this.newDatiProv.nTelelavoroFt == null ? 0 : this.newDatiProv.nTelelavoroFt))
    });
    this.form = new FormGroup({
      id: new FormControl(),
      categoriaEscluse: new FormControl(),
      nLavAppartartCategoria: new FormControl(),
      idProspettoProv: new FormControl()
      // datiProvinciali: new FormGroup({
      //   id: new FormControl()
      // })
    });
    this.formSelectedItem = new FormGroup({
      categoriaEsclusa: new FormControl()
    });
    // this.setValidators();
  }

  onClickChange(item: CategorieEscluse) {
    this.selectedItem = item;
    this.f.id.setValue(this.selectedItem.id);
    this.f.nLavAppartartCategoria.setValue(this.selectedItem.nLavAppartartCategoria);
    this.prevIdCat = this.selectedItem.categoriaEscluse.id;
    const categoria = this.comboCategorieEscluse.find(el => el.item.id === this.prevIdCat);
    this.f.categoriaEscluse.setValue(categoria.item);
  }

  onClickReset() {
    this.pulisciErroriInPagina();
    this.selectedItem = this.prevIdCat = null;
    this.form.reset();
    this.formSelectedItem.reset();
    this.formLav.reset();
    this.formLav.patchValue(this.datiProvinciali);
    this.onChange();
    this.removeValidators();
  }

  onClickResetNoFormLav() {
    this.pulisciErroriInPagina();
    this.selectedItem = this.prevIdCat = null;
    this.form.reset();
    this.formSelectedItem.reset();
    this.onChange();
    this.removeValidators();
  }

  onChange() {
    this.mapValues();
    this.saveDatiProvinciali.emit(this.newDatiProv);
    // this.setValidators();
  }

  private mapValues() {
    let nTelelavoroFt = this.formLav.controls.nTelelavoroFt.value;
    nTelelavoroFt = nTelelavoroFt == null || nTelelavoroFt === '' || nTelelavoroFt === "" ? 0 : nTelelavoroFt;
    // this.newDatiProv.nTelelavoroFt = this.formLav.controls.nTelelavoroFt.value;
    this.newDatiProv.nTelelavoroFt = nTelelavoroFt;
  }

  setValidators() {
    const catEsclValue = this.f.categoriaEscluse.value;
    let nLavValue = this.f.nLavAppartartCategoria.value;
    nLavValue = nLavValue && nLavValue === '' ? null : nLavValue;
    if (catEsclValue || nLavValue) {
      this.setValidatorsRequired();
    } else {
      this.removeValidators();
    }

  }

  private setValidatorsRequired() {
    const controlCatEscl = this.f.categoriaEscluse;
    const controlNlav = this.f.nLavAppartartCategoria;

    this.removeValidators();
    this.f.categoriaEscluse.setValidators(Validators.required);
    this.f.nLavAppartartCategoria.setValidators(Validators.required);
    this.f.categoriaEscluse.updateValueAndVaylidit();
    this.f.nLavAppartartCategoria.updateValueAndVaylidit();
  }

  private removeValidators() {
    this.f.categoriaEscluse.clearValidators();
    this.f.nLavAppartartCategoria.clearValidators();
    this.f.categoriaEscluse.updateValueAndValidity();
    this.f.nLavAppartartCategoria.updateValueAndValidity();
  }

  async conferma() {
    this.utilitiesService.showSpinner();
    this.pulisciErroriInPagina();
    const formCatEscl = this.form.getRawValue() as CategorieEscluse;
    try {
      let resCatEscluse: CategorieEscluse;
      if (formCatEscl.id) {
        /*modifica*/
        resCatEscluse = await this.datiProvincialiService.putCategorieEscluse(this.datiProvinciali.id, formCatEscl.id, formCatEscl).toPromise();
      } else {
        /*inserimento*/
        resCatEscluse = await this.datiProvincialiService.postCategorieEscluse(this.datiProvinciali.id, formCatEscl).toPromise();
      }

      if (resCatEscluse) {
        const idT = resCatEscluse.categoriaEscluse.id;
        if (this.prevIdCat) {
          if (this.prevIdCat !== idT) {
            this.comboCategorieEscluse.find(el => el.item.id === this.prevIdCat).disable = false;
          }
          const index = this.categorieEscluse.findIndex(el => el.id === resCatEscluse.id);
          if (index >= 0) {
            this.categorieEscluse[index] = resCatEscluse;
          }
        } else {
          this.categorieEscluse.push(resCatEscluse);
          this.comboCategorieEscluse.find(el => el.item.id === idT).disable = true;
        }
        this.utilitiesService.hideSpinner();
        this.onClickResetNoFormLav();
      }

    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaErroriInPagina();
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }

  }

  salvaInBozza() {
    this.mapValues();
    this.saveDatiProvincialiBozza.emit(this.newDatiProv);
  }

  async deleteCatEscl() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const idCategoriaToDelete = this.selectedItem.id;
    try {
      const res: CategorieEscluse = await this.datiProvincialiService.deleteCategorieEscluse(idCategoriaToDelete).toPromise();
      if (res) {
        this.comboCategorieEscluse.find(el => el.item.id === res.categoriaEscluse.id).disable = false;
        const indexToDelete: number = this.categorieEscluse.findIndex(cat => cat.id === res.id);
        if (indexToDelete >= 0) {
          this.categorieEscluse.splice(indexToDelete, 1);
          this.selectedItem = this.prevIdCat = null;
          this.form.reset();
          this.formSelectedItem.reset();
        }
        console.log('success');
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaErroriInPagina();
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  private settaErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_DANGER,
      hide: true,
      listMsg: this.listMsg
    });
  }

  private pulisciErroriInPagina() {
    this.listMsg = [];
    this.erroriInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

}

class OggCustom {
  item: CategoriaEscluse;
  disable = false;
}
