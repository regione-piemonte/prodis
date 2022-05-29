/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS, TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { Comune, DatiProvincialiService, DecodificaGenerica, DecodificaService, Istat2001livello5, PostiLavoroDisp, Prospetto, ProspettoProvincia, RiepilogoService } from 'src/app/modules/prodisapi';
import { CategoriaAssunzione, CategoriaAssunzioneInterface } from 'src/app/modules/prodiscommon/components/categoriaAssunzione';
import { CategoriaSoggetto, CategoriaSoggettoInterface } from 'src/app/modules/prodiscommon/components/categoriaSoggetto';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

export interface PostoDisponibileForm {
  id: number;
  dsProvincia: string;
  mansione: string;
  dsCapacita: string;
  flgBarriere: string;
  flgTurni: string;
  flgMezziPubblici: string;
  targa: string;
  qualifica: Istat2001livello5;
  nPosti: number;
  categoriaSoggetto: string;
  comune: Comune;
  categoriaAssunzione: string;
  //statiEsteri: StatiEsteri; prodis 212
}
@Component({
  selector: 'prodis-form-tab-posti-disp',
  templateUrl: './form-tab-posti-disp.component.html',
  styleUrls: ['./form-tab-posti-disp.component.scss']
})
export class FormTabPostiDispComponent implements OnInit, OnDestroy {


  prospetto: Prospetto;
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
  }
  ilProspettoProvincia: ProspettoProvincia[];
  @Input() set InputilProspettoProvincia(ilProspettoProvincia: ProspettoProvincia[]) {
    this.ilProspettoProvincia = ilProspettoProvincia;
  }

  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly setPostoLavoroDisp = new EventEmitter<any>();

  selectedItem: PostoDisponibileForm;
  elencoTab: Array<PostoDisponibileForm> = [];
  comboCategoriaAssunzione: CategoriaAssunzioneInterface[] = CategoriaAssunzione.get();
  comboCategoriaSogg: CategoriaSoggettoInterface[] = CategoriaSoggetto.get();


  form: FormGroup;
  formElencoPostiDip: FormGroup;

  listMsg: string[] = [];

  get f() { return this.form.controls as any; }

  constructor(
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private promptModalService: PromptModalService,
    private riepilogoService: RiepilogoService,
    private prodisStorageService: ProdisStorageService,
  ) { }

  ngOnInit() {
    this.initForm();
    setTimeout( ()=> {
      this.prodisStorageService.setHideButtonConfermaProsegui(false),
      100
    });
    this.ilProspettoProvincia.forEach(ilProspetto => {
      if (ilProspetto.datiProvinciali.postiLavoroDisps) {
        ilProspetto.datiProvinciali.postiLavoroDisps.forEach(ilPostoDisponibile => {
          this.elencoTab.push({
            id: ilPostoDisponibile.id,
            dsProvincia: ilProspetto.provincia.dsProTProvincia,
            mansione: ilPostoDisponibile.descMansione,
            dsCapacita: ilPostoDisponibile.descCapacitaRichiesteContr,
            flgBarriere: ilPostoDisponibile.flgPresenzaBarriereArchite,
            flgTurni: ilPostoDisponibile.flgTurniNotturni,
            flgMezziPubblici: ilPostoDisponibile.flgRaggiungibMezziPubblici,
            targa: ilProspetto.provincia.dsTarga,
            qualifica: ilPostoDisponibile.istat2001livello5,
            nPosti: ilPostoDisponibile.nPosti,
            categoriaSoggetto: ilPostoDisponibile.categoriaSoggetto ? CategoriaSoggetto.getDescrizioneByCodice(ilPostoDisponibile.categoriaSoggetto) : null,
            comune: ilPostoDisponibile.comune,
            categoriaAssunzione: ilPostoDisponibile.categoriaAssunzione ? CategoriaAssunzione.getDescrizioneByCodice(ilPostoDisponibile.categoriaAssunzione) : null
          });
        });
      }
    });
  }

  ngOnDestroy(): void {
    setTimeout( ()=> {
      this.prodisStorageService.setHideButtonConfermaProsegui(true),
      100
    });
  }

  setSelectedItem(postoDisp: PostoDisponibileForm) {
    this.selectedItem = postoDisp;
    this.f.id.setValue(postoDisp.id);
    const prospettoProv = this.ilProspettoProvincia.find(el => el.provincia.dsProTProvincia === postoDisp.dsProvincia);
    this.f.prospettoProv.setValue(prospettoProv);
    if (postoDisp.qualifica) {
      this.f.istat2001livello5.patchValue(postoDisp.qualifica);
    } else {
      this.f.istat2001livello5.reset();
    }
    this.f.descMansione.setValue(postoDisp.mansione);
    this.f.nPosti.setValue(postoDisp.nPosti);
    const catSogg = this.comboCategoriaSogg.find(el => el.descrizione === postoDisp.categoriaSoggetto);
    if (catSogg) {
      this.f.categoriaSoggetto.setValue(catSogg.codice);
    } else {
      this.f.categoriaSoggetto.setValue(null);
    }
    if (postoDisp.comune) {
      this.f.comune.patchValue(postoDisp.comune);
    } else {
      this.f.comune.reset();
    }
    /* PRODIS 212
    if (postoDisp.statiEsteri) {
      this.f.statiEsteri.patchValue(postoDisp.statiEsteri);
    } else {
      this.f.statiEsteri.reset();
    }*/
    this.f.descCapacitaRichiesteContr.setValue(postoDisp.dsCapacita);
    this.f.flgPresenzaBarriereArchite.setValue(postoDisp.flgBarriere);
    this.f.flgRaggiungibMezziPubblici.setValue(postoDisp.flgMezziPubblici);
    this.f.flgTurniNotturni.setValue(postoDisp.flgTurni);
    const catAss = this.comboCategoriaAssunzione.find(el => el.descrizione === postoDisp.categoriaAssunzione);
    if (catAss) {
      this.f.categoriaAssunzione.setValue(catAss.codice);
    } else {
      this.f.categoriaAssunzione.setValue(null);
    }
  }

  async onClickFindQualificaIstat() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    console.log('Find Qualifica istat');
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.f.istat2001livello5.controls.codIstat2001livello5Min.value,
      dsDecodifica: this.f.istat2001livello5.controls.dsComIstat2001livello5.value
    };
    try {

      const res = await this.decodificaService.postQualificaDecodifica(decodifica).toPromise();
      let decod: DecodificaGenerica;
      if (res && res.length === 1) {
        decod = res[0];
      } else if (res && res.length > 1) {
        this.utilitiesService.hideSpinner();
        decod = await this.promptModalService.openDecodificaPrompt('Seleziona qualifica', res, TYPE_DECODIFICA_GENERICA.QUAL_ISTAT, decodifica);
      }

      if (decod) {
        const qualifica: Istat2001livello5 = {
          id: decod.idDecodifica,
          codIstat2001livello5Min: decod.codDecodifica,
          dsComIstat2001livello5: decod.dsDecodifica
        };
        this.f.istat2001livello5.patchValue(qualifica);
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUNA QUALIFICA ISTAT TROVATA');
        this.settaErroriInPagina();
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

  onClickReset() {
    this.pulisciErroriInPagina();
    this.selectedItem = null;
    this.form.reset();
    this.formElencoPostiDip.reset();
  }

  private initForm() {
    this.form = new FormGroup({
      id: new FormControl(), // id posto disponibile
      prospettoProv: new FormControl(),
      istat2001livello5: new FormGroup({
        id: new FormControl(),
        codIstat2001livello5Min: new FormControl(null, Validators.minLength(3)),
        dsComIstat2001livello5: new FormControl(null, Validators.minLength(3))
      }),
      descMansione: new FormControl(),
      nPosti: new FormControl(),
      categoriaSoggetto: new FormControl(),
      comune: new FormGroup({
        id: new FormControl(),
        codComuneMin: new FormControl(null, Validators.minLength(3)),
        dsProTComune: new FormControl(null, Validators.minLength(3))
      }),
      /* prodis 212
      statiEsteri: new FormGroup({
        id: new FormControl(),
        codNazioneMin: new FormControl(null, Validators.minLength(3)),
        dsStatiEsteri: new FormControl(null, Validators.minLength(3))
      }),*/
      descCapacitaRichiesteContr: new FormControl(),
      flgPresenzaBarriereArchite: new FormControl(),
      flgRaggiungibMezziPubblici: new FormControl(),
      flgTurniNotturni: new FormControl(),
      categoriaAssunzione: new FormControl()
    });
    this.formElencoPostiDip = new FormGroup({
      postoDisp: new FormControl()
    });
  }

  async onClickFindComune() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    console.log('Find Comune sede legale');
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.f.comune.controls.codComuneMin.value,
      dsDecodifica: this.f.comune.controls.dsProTComune.value
    };
    try {

      const res = await this.decodificaService.postComuneDecodifica(decodifica).toPromise();
      let decod: DecodificaGenerica;
      if (res && res.length === 1) {
        decod = res[0];
      } else if (res && res.length > 1) {
        this.utilitiesService.hideSpinner();
        decod = await this.promptModalService.openDecodificaPrompt('Seleziona Comune', res, TYPE_DECODIFICA_GENERICA.COMUNE, decodifica);
      }

      if (decod) {
        const comune: Comune = {
          id: decod.idDecodifica,
          codComuneMin: decod.codDecodifica,
          dsProTComune: decod.dsDecodifica
        };
        this.f.comune.patchValue(comune);
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN COMUNE PER LA SEDE LEGALE TROVATO');
        this.settaErroriInPagina();
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
  // prodis 212
  // async onClickFindStatoEstero() {
  //   this.utilitiesService.showSpinner();
  //   const decodifica: DecodificaGenerica = {
  //     codDecodifica: this.f.statiEsteri.controls.codNazioneMin.value,
  //     dsDecodifica: this.f.statiEsteri.controls.dsStatiEsteri.value
  //   };
  //   try {
  //     const res = await this.decodificaService.postStatiEsteriDecodifica(decodifica).toPromise();
  //     let decod: DecodificaGenerica;
  //     if (res && res.length === 1) {
  //       decod = res[0];
  //     } else if (res && res.length > 1) {
  //       this.utilitiesService.hideSpinner();
  //       decod = await this.promptModalService.openDecodificaPrompt('Seleziona uno stato estero', res, TYPE_DECODIFICA_GENERICA.STATI_ESTERI);
  //     }
  //     if (decod) {
  //       const statoEstero: StatiEsteri = {
  //         id: decod.idDecodifica,
  //         codNazioneMin: decod.codDecodifica,
  //         dsStatiEsteri: decod.dsDecodifica
  //       };
  //       this.f.statiEsteri.patchValue(statoEstero);
  //     } else {
  //       // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
  //       this.listMsg.push('NESSUN COMUNE PER LA SEDE LEGALE TROVATO');
  //       this.settaErroriInPagina();
  //     }
  //   } catch (e) {
  //     if (e.error && e.error.length > 0) {
  //       this.listMsg = [];
  //       e.error.forEach(element => {
  //         this.listMsg.push(element.errorMessage + '<BR>');
  //       });
  //       this.settaErroriInPagina();
  //     }
  //   } finally {
  //     this.utilitiesService.hideSpinner();
  //   }
  // }

  async conferma() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    try {
      const postoDisponibileToSend: PostiLavoroDisp = this.getPostoDisponibileByForm();
      let res;
      if (postoDisponibileToSend) {
        const idPostoDiLavoroDisp: number = postoDisponibileToSend.id;
        if (idPostoDiLavoroDisp) {
          res = await this.riepilogoService.putPostiLavoroDisp(postoDisponibileToSend.idProspettoProv, idPostoDiLavoroDisp, postoDisponibileToSend).toPromise();
        } else {
          res = await this.riepilogoService.postPostiLavoroDisp(postoDisponibileToSend.idProspettoProv, postoDisponibileToSend).toPromise();
        }
        if (res) {
          const resForForm = this.getPostoDisponibileFormByPostodisp(res);
          if (this.selectedItem) {
            // sovrascrive elemento in lista
            for (let index = 0; index < this.elencoTab.length; index++) {
              const element = this.elencoTab[index];
              if (element.id === this.selectedItem.id) {
                this.elencoTab[index] = resForForm;
                this.listMsg.push('Posti aggiornati correttamente');
              }
            }
            // const index = this.elencoTab.indexOf(this.selectedItem);
            // if (index >= 0) {
            //   this.elencoTab[index] = resForForm;
            // }
          } else {
            // inserisci lelemnto in lista
            this.elencoTab.push(resForForm);
            this.listMsg.push('Posti inseriti correttamente');
          }
          this.settaMsgInPagina();
          this.selectedItem = null;
          this.form.reset();
          this.setPostoLavoroDisp.emit();
        }
      } else {
        // errore
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        const errorList = e.error;
        errorList.forEach(e => {
          this.listMsg.push(e.errorMessage + '<BR>');
        });
        this.settaErroriInPagina();
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  private getPostoDisponibileFormByPostodisp(postoDisp: PostiLavoroDisp): PostoDisponibileForm {
    const postoDispform: PostoDisponibileForm = {} as PostoDisponibileForm;
    postoDispform.id = postoDisp.id;
    const prospettoProv = this.ilProspettoProvincia.find(item => item.datiProvinciali.id === postoDisp.idProspettoProv);
    postoDispform.dsProvincia = prospettoProv.provincia.dsProTProvincia;
    postoDispform.mansione = postoDisp.descMansione;
    postoDispform.dsCapacita = postoDisp.descCapacitaRichiesteContr;
    postoDispform.flgBarriere = postoDisp.flgPresenzaBarriereArchite;
    postoDispform.flgTurni = postoDisp.flgTurniNotturni;
    postoDispform.flgMezziPubblici = postoDisp.flgRaggiungibMezziPubblici;
    postoDispform.targa = prospettoProv.provincia.dsTarga;
    postoDispform.qualifica = postoDisp.istat2001livello5;
    postoDispform.nPosti = postoDisp.nPosti;
    postoDispform.categoriaSoggetto = postoDisp.categoriaSoggetto ? CategoriaSoggetto.getDescrizioneByCodice(postoDisp.categoriaSoggetto) : null;
    postoDispform.comune = postoDisp.comune;
    postoDispform.categoriaAssunzione = postoDisp.categoriaAssunzione ? CategoriaAssunzione.getDescrizioneByCodice(postoDisp.categoriaAssunzione) : null;
    //postoDispform.statiEsteri = postoDisp.statiEsteri; PRODIS 212
    return postoDispform;
  }

  private getPostoDisponibileByForm(): PostiLavoroDisp {
    const postoDisp: PostiLavoroDisp = {} as PostiLavoroDisp;
    postoDisp.id = this.f.id.value;
    const prospettoProv = this.f.prospettoProv.value;
    const idDatiProvinciali: number = prospettoProv.datiProvinciali.id;
    postoDisp.idProspettoProv = idDatiProvinciali;
    postoDisp.istat2001livello5 = this.f.istat2001livello5.value;
    postoDisp.descMansione = this.f.descMansione.value;
    postoDisp.nPosti = this.f.nPosti.value;
    postoDisp.categoriaSoggetto = this.f.categoriaSoggetto.value;
    postoDisp.categoriaAssunzione = this.f.categoriaAssunzione.value;
    postoDisp.comune = this.f.comune.value;
    //postoDisp.statiEsteri = this.f.statiEsteri.value; prodis 212
    postoDisp.descCapacitaRichiesteContr = this.f.descCapacitaRichiesteContr.value;
    postoDisp.flgPresenzaBarriereArchite = this.f.flgPresenzaBarriereArchite.value;
    postoDisp.flgRaggiungibMezziPubblici = this.f.flgRaggiungibMezziPubblici.value;
    postoDisp.flgTurniNotturni = this.f.flgTurniNotturni.value;
    return postoDisp;
  }

  private settaErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_DANGER,
      hide: true,
      listMsg: this.listMsg
    });
  }

  private pulisciErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: "",
      hide: false,
      listMsg: []
    });
  }

  async deletePostiDisp() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const itemToDelete = this.selectedItem;

    try {

      const res  = await this.riepilogoService.deletePostiLavoroDisp(itemToDelete.id).toPromise();

      if (res) {
        const indexToDelete: number = this.elencoTab.findIndex(el => el.id === res.id);
        if (indexToDelete >= 0) {
          this.elencoTab.splice(indexToDelete, 1);
          this.onClickReset();
        }
        console.log('success');
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

  onChangeQistat(){
    this.f.istat2001livello5.get('id').setValue(null);
  }
  onChangeComune(){
    this.f.comune.get('id').setValue(null);
  }
  //prodis 212
  // onChangeStatiEsteri(){
  //   this.f.statiEsteri.get('id').setValue(null);
  // }
  private settaMsgInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_SUCCESS,
      hide: true,
      listMsg: this.listMsg
    });
  }

}
