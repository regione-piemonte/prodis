/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';
import { MESI, TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS, TYPE_ALERT_WARNING, TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { AssunzioneProtetta, Comune, Contratti, DatiProvinciali, DatiProvincialiService, DecodificaGenerica, DecodificaService, Istat2001livello5, LavoratoriInForza, SilpService, StatiEsteri } from 'src/app/modules/prodisapi';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

const LIST_CATEGORIA_SOGGETTO: ComboObj[] = [
  {
    val: 'D',
    des: 'Disabili'
  },
  {
    val: 'C',
    des: 'Categorie protette'
  }
];

const LIST_CATEGORIA_ASSUNZIONE: ComboObj[] = [
  {
    val: 'NU',
    des: 'Assunzione Numerica(NU)'
  },
  {
    val: 'NO',
    des: 'Assunzione Nominativa(NO)'
  }
];

@Component({
  selector: 'prodis-form-tab-elenco-lavoratori-comp',
  templateUrl: './form-tab-elenco-lavoratori-comp.component.html',
  styleUrls: ['./form-tab-elenco-lavoratori-comp.component.scss']
})
export class FormTabElencoLavoratoriCompComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Output() readonly msgInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();

  newDatiProv: DatiProvinciali;
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
  }

  elencoLavoratoriInForza: LavoratoriInForza[];
  currentItem: LavoratoriInForza;


  form: FormGroup;
  formElencoLav: FormGroup;

  contrattis: Contratti[] = [];
  elencoTipoAssunzione: AssunzioneProtetta[] = [];
  elencoCatSogg: ComboObj[] = LIST_CATEGORIA_SOGGETTO;
  elencoCatAss: ComboObj[] = LIST_CATEGORIA_ASSUNZIONE;
  flgWarning: boolean = false;

  listMsg: string[] = [];

  get f() { return this.form.controls as any; }

  constructor(
    private silpApiService: SilpService,
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private promptModalService: PromptModalService,
    private datiProvincialiService: DatiProvincialiService,
    private prodisStorageService: ProdisStorageService
  ) { }

  async ngOnInit() {
    try {
      this.utilitiesService.showSpinner();
      this.initForm();
      this.elencoLavoratoriInForza = await this.datiProvincialiService.getLavoratoriInForzaByIdProspettoProv(this.datiProvinciali.id).toPromise();
      const resContratti: DecodificaGenerica[] = await this.decodificaService.postContrattiDecodifica({} as DecodificaGenerica).toPromise();
      resContratti.forEach(item => {
        this.contrattis.push({
          id: item.idDecodifica,
          codTipoContrattoMin: item.codDecodifica,
          dsContratto: item.dsDecodifica,
          flgForma: item.flgForma
        });
      });
      const resAssunzione: DecodificaGenerica[] = await this.decodificaService.postAssunzioneProtettaDecodifica({} as DecodificaGenerica).toPromise();
      resAssunzione.forEach(item => {
        this.elencoTipoAssunzione.push({
          id: item.idDecodifica,
          codAssunzioneProtetta: item.codDecodifica,
          descAssunzioneProtetta: item.dsDecodifica
        });
      });
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
    this.prodisStorageService.esitoUploadLavoratori$.subscribe(item => {
      if(item){
        this.loadElencoLavoratori();
      }
    });
  }


  async loadElencoLavoratori(){
    this.utilitiesService.showSpinner();
    try{
      this.elencoLavoratoriInForza = await this.datiProvincialiService.getLavoratoriInForzaByIdProspettoProv(this.datiProvinciali.id).toPromise();
      this.prodisStorageService.setEsitoUploadLavoratori(false);
    }catch(e){
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    }finally{
      this.utilitiesService.hideSpinner();
    }
  }

  private initForm() {
    this.form = new FormGroup({
      id: new FormControl(),
      codiceFiscale: new FormControl(null,
        Validators.pattern('[A-Z]{6}[0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{2}[A-Z][0-9LMNPQRSTUV]{3}[A-Z]')
      ),
      nome: new FormControl(),
      cognome: new FormControl(),
      sesso: new FormControl(),
      dataNascita: new FormControl(),
      comune: new FormGroup({
        id: new FormControl(),
        codComuneMin: new FormControl(),
        dsProTComune: new FormControl()
      }),
      statiEsteri: new FormGroup({
        id: new FormControl(),
        codNazioneMin: new FormControl(),
        dsStatiEsteri: new FormControl()
      }),
      percentualeDisabilita: new FormControl(),
      dataInizioRapporto: new FormControl(),
      contratti: new FormGroup({
        id: new FormControl(),
        codTipoContrattoMin: new FormControl(),
        dsContratto: new FormControl(),
        flgForma: new FormControl(),
      }),
      dataFineRapporto: new FormControl(),
      istat2001livello5: new FormGroup({
        id: new FormControl(),
        codIstat2001livello5Min: new FormControl(),
        dsComIstat2001livello5: new FormControl()
      }),
      assunzioneProtetta: new FormControl(),
    // orarioSettContrattualeMin: new FormControl('00:00',
      orarioSettContrattualeMin: new FormControl('',
      Validators.pattern(/^([0-9][0-9]):[0-5][0-9]$/)),
      orarioSettPartTimeMin: new FormControl('00:00',
      Validators.pattern(/^([0-9][0-9]):[0-5][0-9]$/)),
      categoriaSoggetto: new FormControl(),
      categoriaAssunzione: new FormControl()
    });

    this.formElencoLav = new FormGroup({
      item: new FormControl()
    });

  }

  async onClickFindCf() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
    try {
      const cfInput: string = this.f.codiceFiscale.value;
      const res = await this.silpApiService.getLavoratore(cfInput).toPromise();
      if (res) {
        this.f.cognome.setValue(res.cognome);
        this.f.nome.setValue(res.nome);
        this.f.dataNascita.setValue(res.dataNascita);
        this.f.sesso.setValue(res.sesso);

        if (res.statiEsteri && res.statiEsteri.codNazioneMin) {
           this.f.statiEsteri.patchValue(res.statiEsteri);
           this.f.comune.reset();
        } else {
           this.f.comune.patchValue(res.comune);
           this.f.statiEsteri.reset();
        }
      } else {
        this.listMsg.push('NESSUN LAVORATORE TROVATO');
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
      console.log(e);
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  onClickPreCompila() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
   // this.pulisciErroriInPagina();
    const cfInput: string = this.f.codiceFiscale.value;
    if (cfInput && cfInput !== '') {
      const sesso = this.sessoCf(cfInput);
      const dataNascita = this.dataCf(cfInput);
      this.f.sesso.setValue(sesso);
      this.f.dataNascita.setValue(new Date(dataNascita));
      this.comuneCf(cfInput);
    } else {
      this.utilitiesService.hideSpinner();
      console.log('Errore');
    }
    this.utilitiesService.hideSpinner();
  }

  async onClickFindComune() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
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
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }

    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  setForma() {
    const idContrattoByForm = this.f.contratti.value ? this.f.contratti.value.id : null;
    if (idContrattoByForm) {
      const contratto = this.contrattis.find(el => el.id === idContrattoByForm);
      const flgForma = contratto.flgForma;
      if (flgForma === 'D' || flgForma === 'I') {
        this.f.contratti.controls.flgForma.disable();
      } else {
        this.f.contratti.controls.flgForma.enable();
      }
      this.f.contratti.patchValue(contratto);
    } else {
      this.f.contratti.controls.flgForma.reset();
      this.f.contratti.controls.flgForma.enable();
    }

  }

  async onClickFindQualificaIstat() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
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
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }

    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  setSelectedItem(item: LavoratoriInForza) {
    this.currentItem = item;
    this.flgWarning = false;
    console.log(this.currentItem);
    this.setValueInForm(this.currentItem);

  }

  async onClickFindStatoEstero() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
    console.log('Find Stati esteri');
    const decodifica: DecodificaGenerica = {
      dsDecodifica: this.f.statiEsteri.controls.dsStatiEsteri.value,
      codDecodifica: this.f.statiEsteri.controls.codNazioneMin.value
    };
    try {
      const res = await this.decodificaService.postStatiEsteriDecodifica(decodifica).toPromise();
      let decod: DecodificaGenerica;
      if (res && res.length === 1) {
        decod = res[0];
      } else if (res && res.length > 1) {
        this.utilitiesService.hideSpinner();
        decod = await this.promptModalService.openDecodificaPrompt('Seleziona stato estero', res, TYPE_DECODIFICA_GENERICA.STATI_ESTERI, decodifica);
      }

      if (decod) {
          const statoEs: StatiEsteri = {
            id: decod.idDecodifica,
            codNazioneMin: decod.codDecodifica,
            dsStatiEsteri: decod.dsDecodifica
          };
          this.f.statiEsteri.patchValue(statoEs);
        } else {
          // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
          this.listMsg.push('NESSUNO STATO TROVATO');
          this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
        }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  async deleteLav() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
    const idToDelete: number = this.currentItem.id;
    try {
      const res: LavoratoriInForza = await this.datiProvincialiService.deleteLavoratoriInForza(idToDelete).toPromise();
      if (res) {
        const index = this.elencoLavoratoriInForza.findIndex(lav => lav.id === this.currentItem.id);
        if (index >= 0) {
          this.elencoLavoratoriInForza.splice(index, 1);
          console.log('success');
          this.currentItem = null;
          this.listMsg = [];
          this.resettaForm();
        }
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  private setValueInForm(item: LavoratoriInForza) {
    this.f.id.setValue(item.id);
    this.f.codiceFiscale.setValue(item.codiceFiscale);
    this.f.nome.setValue(item.nome);
    this.f.cognome.setValue(item.cognome);
    this.f.sesso.setValue(item.sesso);
    this.f.dataNascita.setValue(item.dataNascita);
    if(item.comune){
      this.f.comune.patchValue(item.comune);
    }else{
      this.f.comune.reset();
    }
    if(item.statiEsteri){
      this.f.statiEsteri.patchValue(item.statiEsteri);
    }else{
      this.f.statiEsteri.reset();
    }
    this.f.percentualeDisabilita.setValue(item.percentualeDisabilita);
    this.f.dataInizioRapporto.setValue(item.dataInizioRapporto);
    if (item.contratti) {
      const contratto = this.contrattis.find(el => el.id === item.contratti.id);
      this.f.contratti.patchValue(contratto);
      const flgForma = contratto.flgForma;
      if (flgForma === 'D' || flgForma === 'I') {
        this.f.contratti.controls.flgForma.disable();
      } else if (flgForma === 'E' && item.dataFineRapporto ) {
        this.f.contratti.controls.flgForma.setValue('D');
      } else if (flgForma === 'E' && !item.dataFineRapporto ) {
        this.f.contratti.controls.flgForma.setValue('I');
      } else {
        this.f.contratti.controls.flgForma.enable();
      }
    } else {
      // in questo caso non ho nessun tipo di contratto selezionato ma potrei avere il flgforma da prevalorizzare
      // nel caso in cui tipo assunzione protetta sia impostato
      if (item.assunzioneProtetta.codAssunzioneProtetta === 'M') {
          this.f.contratti.controls.flgForma.setValue('D');
      } else if (item.assunzioneProtetta.codAssunzioneProtetta === 'N') {
          this.f.contratti.controls.flgForma.setValue('I');
      }
    }
    this.f.dataFineRapporto.setValue(item.dataFineRapporto);
    this.f.istat2001livello5.patchValue(item.istat2001livello5 ? item.istat2001livello5 : null);
    if (item.assunzioneProtetta) {
      const ass = this.elencoTipoAssunzione.find(el => el.id === item.assunzioneProtetta.id);
      this.f.assunzioneProtetta.patchValue(ass);
    }else{
      this.f.assunzioneProtetta.reset();
    }
    //this.f.get('assunzioneProtetta').setValue(item.assunzioneProtetta);
    this.f.orarioSettContrattualeMin.setValue(Utils.getTimeFormat(item.orarioSettContrattualeMin));
    this.f.orarioSettPartTimeMin.setValue(Utils.getTimeFormat(item.orarioSettPartTimeMin));
    if (this.currentItem.categoriaAssunzione) {
      const categoriaAssunzione = this.elencoCatAss.find(el => el.val === item.categoriaAssunzione);
      this.f.categoriaAssunzione.setValue(categoriaAssunzione.val);
    }
    if (this.currentItem.categoriaSoggetto) {
      const categoriaSoggetto = this.elencoCatSogg.find(el => el.val === item.categoriaSoggetto);
      this.f.categoriaSoggetto.setValue(categoriaSoggetto.val);
    }
  }

  onClickReset() {
    this.currentItem = null;
    this.listMsg = [];
    this.pulisciErroriInPagina();
    this.resettaForm();
  }


  private settaMsgInPagina(typeMsg: string,hide: boolean,listMsg: string[]){
    this.msgInPagina.emit({
      typeMsg: typeMsg,
      hide: hide,
      listMsg: listMsg
    });
  }


  private pulisciErroriInPagina() {
    this.msgInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

  async conferma() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    //this.pulisciErroriInPagina();
    const lavoratoreToSend: LavoratoriInForza = this.getFormAsLavoratoreInForza();
    console.log('conferma: ' + lavoratoreToSend);
    if (lavoratoreToSend.contratti && !lavoratoreToSend.contratti.id) {
      lavoratoreToSend.contratti.dsContratto = null;
      lavoratoreToSend.contratti.codTipoContrattoMin = null;
    }
    if (lavoratoreToSend.comune && lavoratoreToSend.comune.dsProTComune === '' &&  lavoratoreToSend.comune.codComuneMin === '') {
      lavoratoreToSend.comune = null;
    }
    let res: LavoratoriInForza;
    try {
      if (lavoratoreToSend.id) {
        res = await this.datiProvincialiService.putLavoratoriInForza(this.flgWarning,lavoratoreToSend.idProspettoProv, lavoratoreToSend.id, lavoratoreToSend).toPromise();
        if (res) {
          // const index = this.elencoLavoratoriInForza.indexOf(this.currentItem);
          // if (index >= 0) {
          //   this.elencoLavoratoriInForza[index] = res;
          // } else {
          //   this.elencoLavoratoriInForza.push(res);
          // }
          this.listMsg.push('Lavoratore modificato correttamente');
          this.settaMsgInPagina(TYPE_ALERT_SUCCESS,true,this.listMsg);
          this.currentItem = null;
          this.resettaForm();
          this.elencoLavoratoriInForza = await this.datiProvincialiService.getLavoratoriInForzaByIdProspettoProv(this.datiProvinciali.id).toPromise();
        }
      } else {
        res = await this.datiProvincialiService.postLavoratoriInForza(lavoratoreToSend.idProspettoProv, this.flgWarning,lavoratoreToSend).toPromise();
        if (res) {
          // this.elencoLavoratoriInForza.push(res);
          // this.listMsg.push('Lavoratore inserito correttamente');
          this.listMsg.push('Lavoratore inserito correttamente');
          this.settaMsgInPagina(TYPE_ALERT_SUCCESS,true,this.listMsg);
          this.currentItem = null;
          this.resettaForm();
          this.elencoLavoratoriInForza = await this.datiProvincialiService.getLavoratoriInForzaByIdProspettoProv(this.datiProvinciali.id).toPromise();
        }
      }
    } catch (e) {
      if (e.error && e.error.length > 0) {
          const errorResponseList = e.error;
          let elencoErrori: string[] = [];
          let elencoWarning: string[] = [];
          errorResponseList.forEach(element => {
            const cod = element.code.charAt(8);
            if(cod === "W"){
              elencoWarning.push(element.errorMessage+ '<br>');
            }else if(cod === "E"){
              elencoErrori.push(element.errorMessage + '<br>');
            }
          });
          if(elencoErrori.length > 0){
            this.settaMsgInPagina(TYPE_ALERT_DANGER,true,elencoErrori);
          }else if(elencoWarning.length > 0){
              this.flgWarning = true;
              this.settaMsgInPagina(TYPE_ALERT_WARNING,true,elencoWarning);
          }
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  private resettaForm() {
    this.flgWarning = false;
    // this.form.reset();
    // this.formElencoLav.reset();
    this.initForm();
   // this.form.controls.orarioSettContrattualeMin.setValue('00:00');
    this.form.controls.orarioSettPartTimeMin.setValue('00:00');

  }

  private async comuneCf(codiceFiscale) {
    this.listMsg = [];
    //this.pulisciErroriInPagina();
    if (codiceFiscale.charAt(11).toUpperCase() === 'Z' ) {
      console.log('estero');
      const codStatoEstero = codiceFiscale.substring(11, 15);
      console.log(codStatoEstero);
      if (codStatoEstero && codStatoEstero !== '') {
        const decodificaGenerica: DecodificaGenerica = {
          codDecodifica: codStatoEstero
        };
        try {
          const res: DecodificaGenerica[] = await this.decodificaService.postStatiEsteriDecodifica(decodificaGenerica).toPromise();
          let decod: DecodificaGenerica;
          if (res && res.length === 1) {
            decod = res[0];
          }
          if (decod) {
            const statoEstero: StatiEsteri = {
              id: decod.idDecodifica,
              codNazioneMin: decod.codDecodifica,
              dsStatiEsteri: decod.dsDecodifica
            };
            this.f.statiEsteri.patchValue(statoEstero);
          }
        } catch (e) {
          if (e.error && e.error.length > 0) {
            e.error.forEach(element => {
              this.listMsg.push(element.errorMessage + '<BR>');
            });
            this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
          }
        } finally {
          this.utilitiesService.hideSpinner();
        }
      }
    } else {
      const codComune = codiceFiscale.substring(11, 15);
      if (codComune && codComune !== '') {
        const decodificaGenerica: DecodificaGenerica = {
          codDecodifica: codComune
        };
        try {
          const res: DecodificaGenerica[] = await this.decodificaService.postComuneDecodifica(decodificaGenerica).toPromise();
          let decod: DecodificaGenerica;
          if (res && res.length === 1) {
            decod = res[0];
          }
          if (decod) {
            const comune: Comune = {
              id: decod.idDecodifica,
              codComuneMin: decod.codDecodifica,
              dsProTComune: decod.dsDecodifica
            };
            this.f.comune.patchValue(comune);
          }
        } catch (e) {
          if (e.error && e.error.length > 0) {
            e.error.forEach(element => {
              this.listMsg.push(element.errorMessage + '<BR>');
            });
            this.settaMsgInPagina(TYPE_ALERT_DANGER,true,this.listMsg);
          }
        } finally {
          this.utilitiesService.hideSpinner();
        }
      }

    }
 }

 private dataCf(codiceFiscale) {
     let [ anno, giorno ] = [ codiceFiscale.substring(6, 8), codiceFiscale.substring(9, 11) ];
     if (giorno > 40) {
       giorno -= 40;
       giorno = '0' + giorno;
     }
     return (anno < 20 ? '20' : '19' ) + anno + '-' + MESI[codiceFiscale.charAt(8)] + '-' + giorno;
   }

 private sessoCf(codiceFiscale) { return codiceFiscale.substring(9, 11) > 40 ? 'F' : 'M'; }

  private getFormAsLavoratoreInForza(): LavoratoriInForza {
    const lav: LavoratoriInForza = {} as LavoratoriInForza;
    lav.id = this.f.id.value;
    lav.codiceFiscale = this.f.codiceFiscale.value;
    lav.cognome = this.f.cognome.value;
    lav.nome = this.f.nome.value;
    lav.sesso = this.f.sesso.value;
    lav.dataNascita = this.f.dataNascita.value;
    lav.comune = this.f.comune.value;
    lav.percentualeDisabilita = this.f.percentualeDisabilita.value;
    lav.dataInizioRapporto = this.f.dataInizioRapporto.value;
    lav.contratti = this.f.contratti.getRawValue() as Contratti;
    lav.dataFineRapporto = this.f.dataFineRapporto.value;
    lav.istat2001livello5 = this.f.istat2001livello5.value;
    lav.assunzioneProtetta = this.f.assunzioneProtetta.value;
    lav.orarioSettContrattualeMin = this.getMinute(this.f.orarioSettContrattualeMin.value);
    lav.orarioSettPartTimeMin =  this.getMinute(this.f.orarioSettPartTimeMin.value);
    lav.categoriaSoggetto = this.f.categoriaSoggetto.value ? this.f.categoriaSoggetto.value : null;
    lav.categoriaAssunzione = this.f.categoriaAssunzione.value ? this.f.categoriaAssunzione.value : null;
    lav.idProspettoProv = this.newDatiProv.id;
    lav.statiEsteri = this.f.statiEsteri.value;
    return lav;
  }

  getMinute(valore: string) {
    const  oreEMinuti =  valore.split(':');
    // if (oreEMinuti.length < 2
    //   || !isNumber(parseInt(oreEMinuti[0]))
    //   || !isNumber(parseInt(oreEMinuti[1])
    //   || (parseInt(oreEMinuti[1])>60) )) {
    //   throw new Error('Formato non corretto, il formato deve essere ore:minuti (esempio 40:00, 7:05 ecc.)');
    // }
    const ore = parseInt(oreEMinuti[0]);
    const oreInMinuti = ore * 60;
    const minutiTotali = oreInMinuti + parseInt(oreEMinuti[1]);
    return minutiTotali;
}

}


export class ComboObj {
  val?: string;
  des?: string;
}
