/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TYPE_ALERT_DANGER, TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { Atecofin, Ccnl, Comune, DatiAzienda, DecodificaGenerica, DecodificaService, Dichiarante, Prospetto, Ruolo, SedeLegale, SilpService, StatiEsteri } from 'src/app/modules/prodisapi';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-form-tab-dati-generali',
  templateUrl: './form-tab-dati-generali.component.html',
  styleUrls: ['./form-tab-dati-generali.component.scss']
})
export class FormTabDatiGeneraliComponent implements OnInit {

  prospetto: Prospetto;
  cfDisabled = true;
  @Output() readonly newDatiGenerali = new EventEmitter<Prospetto>();
  @Output() readonly salvaInBozzaDatiGenerali = new EventEmitter<Prospetto>();
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
    if (this.prospetto) {
      this.idProspetto = this.prospetto.id;
    }
  }
  newProspetto: Prospetto;
  @Input() set inputNewProspetto(newProspetto: Prospetto) {
    this.newProspetto = newProspetto;
    this.setForm(this.newProspetto);
  }
  // @Input() newProspetto: Prospetto;
  idProspetto: number;
  flagForm = false;
  aziendaGiaTrovata = false;

  get isAziendaGiaTrovata(): boolean { return this.aziendaGiaTrovata; }
  form: FormGroup;
  formSedeLegale: FormGroup;

  elencoDichiarante: Dichiarante[] = [];
  elencoStatiEsteri: StatiEsteri[] = [];

  ilRuoloUtente: Ruolo;

  listMsg: string[] = [];

  // restituisce formControls
  get f() { return this.form.controls as any; }
  get fSedeLegale() { return this.formSedeLegale.controls as any; }

  get fDatiAzienda() { return this.form.controls.datiAzienda as any; }

  private timer;
  constructor(
    private decodificaService: DecodificaService,
    private utilitiesService: UtilitiesService,
    private promptModalService: PromptModalService,
    private silpService: SilpService,
    private prodisStorageService: ProdisStorageService
  ) { }

  ngOnInit() {
    // this.getDecodifiche(this.newProspetto);
    //this.timer = setInterval(() => this.logData(), 1000);
    this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(async item => {
      this.cfDisabled = item;
      if (!this.cfDisabled) {
        this.fDatiAzienda.controls.cfAzienda.disable();
      } else {
        this.fDatiAzienda.controls.cfAzienda.enable();
      }
    });
  }

  ngOnDestroy() {
    clearInterval(this.timer);
  }
  
  ngOnChanges(changes: SimpleChanges) {
    console.log('ngOnChanges', changes);
  }

  private logData() {
    let n = 0;
     console.log("chiamata composeDat: "+(++n));
   }

  private async getDecodifiche(prospetto: Prospetto) {
    const elencoDecodDichiarante: DecodificaGenerica[] = await this.decodificaService.postDichiaranteDecodifica({} as DecodificaGenerica).toPromise();
    const elencoDecodStatiEsteri: DecodificaGenerica[] = await this.decodificaService.postStatiEsteriDecodifica({flgStatiUe: 'S'} as DecodificaGenerica
    ).toPromise();
    this.elencoDichiarante = [];
    elencoDecodDichiarante.forEach((item: DecodificaGenerica) => {
      this.elencoDichiarante.push({
        id: item.idDecodifica,
        descDichiarante: item.dsDecodifica,
        codDichiarante: item.codDecodifica
      });
    });
    this.elencoStatiEsteri = [];
    elencoDecodStatiEsteri.forEach(item => {
      this.elencoStatiEsteri.push({
        id: item.idDecodifica,
        codNazioneMin: item.codDecodifica,
        dsStatiEsteri: item.dsDecodifica,
        siglaNazione : item.siglaNazione
      });
    });
    const datiAzienda = prospetto.datiAzienda;
    if (datiAzienda && datiAzienda.dichiarante) {
      const dichiaranteCorrente = this.elencoDichiarante.find(item => item.id === datiAzienda.dichiarante.id);
      this.f.datiAzienda.get('dichiarante').patchValue(dichiaranteCorrente);
    }
    this.settaStatoEsteroCorrente(datiAzienda);
  }

  private settaStatoEsteroCorrente(datiAzienda: DatiAzienda) {
    if (datiAzienda && datiAzienda.cfCapogruppo && datiAzienda.cfCapogruppo !== '') {
      //const cf = datiAzienda.cfCapogruppo;
      let statoEsteroCorrente: StatiEsteri;
      if (datiAzienda.flgCapogruppoEstero && datiAzienda.flgCapogruppoEstero === 'S') {
        // sono un cf azienda straniero
        const siglaNazione = datiAzienda.statiEsteri ? datiAzienda.statiEsteri.siglaNazione : "";
        statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
      } else if (datiAzienda.flgCapogruppoEstero && datiAzienda.flgCapogruppoEstero === 'N') {
        // sono un cf italiano
        statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
      }

      // if (cf.length === 11 || cf.length === 16) {
      //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === 'IT');
      // } else if (cf.length > 16) {
      //   const siglaNazione = cf.substring(0, 2);
      //   statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.siglaNazione === siglaNazione);
      // }
      if (statoEsteroCorrente) {
        this.f.datiAzienda.get('statiEsteri').setValue(statoEsteroCorrente);
      }
    }
  }

  private setForm(prospetto: Prospetto) {
    this.prodisStorageService.ruolo$.subscribe(item => {
      this.ilRuoloUtente = item;
    });

    this.form = new FormGroup({
      id: new FormControl(this.idProspetto),
      dAggiorn: new FormControl(this.idProspetto ? prospetto.dAggiorn : null),
      datiAzienda: new FormGroup({
        id: new FormControl(prospetto.datiAzienda ? prospetto.datiAzienda.id : null),
        cfAzienda: new FormControl(
          ((!this.ilRuoloUtente.operatoreProvinciale && !this.ilRuoloUtente.amministratore && !this.ilRuoloUtente.consulenteRespo) ? this.ilRuoloUtente.codiceFiscale :
            (prospetto.datiAzienda && prospetto.datiAzienda.cfAzienda ? prospetto.datiAzienda.cfAzienda.toUpperCase() : null)),
          Validators.required
        ),
        denominazioneDatoreLavoro: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.denominazioneDatoreLavoro : null), Validators.required),
        cfReferente: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.cfReferente? prospetto.datiAzienda.cfReferente.toUpperCase() : null),
        cognomeReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.cognomeReferente : null),
          Validators.maxLength(50)
        ),
        nomeReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.nomeReferente : null),
          Validators.maxLength(50)
        ),
        indirizzoReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.indirizzoReferente : null),
          Validators.maxLength(100)
        ),
        capReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.capReferente : null),
          Validators.maxLength(5)
        ),
        telefonoReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.telefonoReferente : null),
          Validators.maxLength(15)
        ),
        faxReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.faxReferente : null),
          Validators.maxLength(15)
        ),
        emailReferente: new FormControl((prospetto.datiAzienda ? prospetto.datiAzienda.emailReferente : null),
          Validators.maxLength(80)
        ),
        cfCapogruppo: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.cfCapogruppo ? prospetto.datiAzienda.cfCapogruppo.toUpperCase() : null),
        flgProspettoDaCapogruppo: new FormControl(prospetto.datiAzienda ? prospetto.datiAzienda.flgProspettoDaCapogruppo : 'N'),
        atecofin: new FormGroup({
          id: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.atecofin ? prospetto.datiAzienda.atecofin.id : null),
          codAtecofinMin: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.atecofin ? prospetto.datiAzienda.atecofin.codAtecofinMin : null),
            Validators.compose([
              // Validators.required,
              Validators.minLength(3)
            ])
          ),
          dsProTAtecofin: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.atecofin ? prospetto.datiAzienda.atecofin.dsProTAtecofin : null),
            Validators.compose([
              // Validators.required,
              Validators.minLength(3)
            ])
          )
        }),
        ccnl: new FormGroup({
          id: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.ccnl ? prospetto.datiAzienda.ccnl.id : null),
          codCcnlMin: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.ccnl ? prospetto.datiAzienda.ccnl.codCcnlMin : null),
            Validators.compose([
              // Validators.required,
              Validators.minLength(2)
            ])
          ),
          dsCcnl: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.ccnl ? prospetto.datiAzienda.ccnl.dsCcnl : null),
            Validators.compose([
              // Validators.required,
              Validators.minLength(3)
            ])
          )
        }),
        comune: new FormGroup({
          id: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.comune ? prospetto.datiAzienda.comune.id : null ),
          codComuneMin: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.comune ? prospetto.datiAzienda.comune.codComuneMin : null),
            Validators.compose([
              Validators.minLength(3)
            ])
          ),
          dsProTComune: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.comune ? prospetto.datiAzienda.comune.dsProTComune : null),
            Validators.compose([
              Validators.minLength(3)
            ])
          )
        }),
        // dichiarante: new FormControl(Validators.required),
        dichiarante: new FormControl(),
        statiEsteri: new FormControl()
      })
    });
    this.formSedeLegale = new FormGroup({
      id: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.id : null),
      capSede: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.capSede : null),
        Validators.compose([
          Validators.minLength(5),
          Validators.maxLength(5)
        ])
      ),
      email: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.email : null),
        Validators.maxLength(80)
      ),
      fax: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.fax : null),
        Validators.maxLength(15)
      ),
      indirizzo: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.indirizzo : null),
        Validators.maxLength(100)
      ),
      telefono: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale ? prospetto.datiAzienda.sedeLegale.telefono : null),
        Validators.maxLength(15)
      ),
      comune: new FormGroup({
        id: new FormControl(prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale && prospetto.datiAzienda.sedeLegale.comune ? prospetto.datiAzienda.sedeLegale.comune.id : null ),
        codComuneMin: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale && prospetto.datiAzienda.sedeLegale.comune ? prospetto.datiAzienda.sedeLegale.comune.codComuneMin : null),
          Validators.compose([
            Validators.required,
            Validators.minLength(3)
          ])),
        dsProTComune: new FormControl((prospetto.datiAzienda && prospetto.datiAzienda.sedeLegale && prospetto.datiAzienda.sedeLegale.comune ? prospetto.datiAzienda.sedeLegale.comune.dsProTComune : null),
          Validators.compose([
            Validators.required,
            Validators.minLength(3)
          ]))
      })
    });
    this.setValidators();
    this.flagForm = true;
    if ((!this.ilRuoloUtente.operatoreProvinciale && !this.ilRuoloUtente.amministratore && !this.ilRuoloUtente.consulenteRespo)) {
      this.fDatiAzienda.controls.cfAzienda.disable();
    }
    if (this.prospetto.id) {
      this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(false);
    }
    this.getDecodifiche(prospetto);

  }

  // private setDecodifiche(prospetto: Prospetto) {
  //   const datiAzienda = prospetto.datiAzienda;
  //   if (datiAzienda && datiAzienda.dichiarante) {
  //     const dichiaranteCorrente = this.elencoDichiarante.find(item => item.id == datiAzienda.dichiarante.id);
  //     this.f.datiAzienda.get('dichiarante').setValue(dichiaranteCorrente);
  //   }
  //   if (datiAzienda && datiAzienda.statiEsteri) {
  //     const statoEsteroCorrente = this.elencoStatiEsteri.find(item => item.id == prospetto.datiAzienda.statiEsteri.id);
  //     this.f.datiAzienda.get('statiEsteri').setValue(statoEsteroCorrente);
  //   }
  // }

  async onClickFindSettoreAteco() {
    this.utilitiesService.showSpinner();
    console.log('Find Settore Ateco');
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.fDatiAzienda.get('atecofin').get('codAtecofinMin').value,
      dsDecodifica: this.fDatiAzienda.get('atecofin').get('dsProTAtecofin').value
    };
    try {

      const res = await this.decodificaService.postAtecofinDecodifica(decodifica).toPromise();
      let decod: DecodificaGenerica;
      if (res && res.length === 1) {
        decod = res[0];
      } else if (res && res.length > 1) {
        this.utilitiesService.hideSpinner();
        decod = await this.promptModalService.openDecodificaPrompt('Seleziona settore ATECO', res, TYPE_DECODIFICA_GENERICA.ATECO, decodifica);
      }

      if (decod) {
        const atecofin: Atecofin = {
          id: decod.idDecodifica,
          codAtecofinMin: decod.codDecodifica,
          dsProTAtecofin: decod.dsDecodifica
        };
        this.fDatiAzienda.get('atecofin').patchValue(atecofin);
        this.saveValue();
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN CODICE ATECO TROVATO');
        this.settaErroriInPagina();
      }

    } catch (e) {
      // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
      this.listMsg.push('NESSUN CODICE ATECO TROVATO');
      this.settaErroriInPagina();

    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  async onClikFindCcnl() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    console.log('Find Ccnl');
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.fDatiAzienda.get('ccnl').get('codCcnlMin').value,
      dsDecodifica: this.fDatiAzienda.get('ccnl').get('dsCcnl').value
    };
    try {

      const res = await this.decodificaService.postCcnlDecodifica(decodifica).toPromise();
      let decod: DecodificaGenerica;
      if (res && res.length === 1) {
        decod = res[0];
      } else if (res && res.length > 1) {
        this.utilitiesService.hideSpinner();
        decod = await this.promptModalService.openDecodificaPrompt('Seleziona CCNL', res, TYPE_DECODIFICA_GENERICA.CCNL, decodifica);
      }

      if (decod) {
        const ccnl: Ccnl = {
          id: decod.idDecodifica,
          codCcnlMin: decod.codDecodifica,
          dsCcnl: decod.dsDecodifica
        };
        this.fDatiAzienda.get('ccnl').patchValue(ccnl);
        console.log(this.fDatiAzienda.get('ccnl').value);
        this.saveValue();
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN CCNL TROVATO');
        this.settaErroriInPagina();
      }

    } catch (e) {
      // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
      this.listMsg.push('NESSUN CCNL TROVATO');
      this.settaErroriInPagina();
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  async onClickFindComuneSedeLegale() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    console.log('Find Comune sede legale');
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.fSedeLegale.comune.get('codComuneMin').value,
      dsDecodifica: this.fSedeLegale.comune.get('dsProTComune').value
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
        this.fSedeLegale.comune.patchValue(comune);
        this.saveValue();
        console.log(this.fSedeLegale.comune.value);
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN COMUNE PER LA SEDE LEGALE TROVATO');
        this.settaErroriInPagina();
      }

    } catch (e) {
      // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
      this.listMsg.push('NESSUN COMUNE PER LA SEDE LEGALE TROVATO');
      this.settaErroriInPagina();
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  async onClickFindComuneDatiAzienda() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    console.log('Find Comune Dati Azienda');
    const decodifica: DecodificaGenerica = {
      codDecodifica: this.fDatiAzienda.controls.comune.get('codComuneMin').value,
      dsDecodifica: this.fDatiAzienda.controls.comune.get('dsProTComune').value
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
        this.fDatiAzienda.controls.comune.patchValue(comune);
        this.saveValue();
        console.log(this.fDatiAzienda.controls.comune.controls.value);
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN COMUNE TROVATO PER I DATI DEL REFERENTE ');
        this.settaErroriInPagina();
      }

    } catch (e) {
      // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
      this.listMsg.push('NESSUN COMUNE TROVATO PER I DATI DEL REFERENTE ');
      this.settaErroriInPagina();
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  persistGeneralData() {
    console.log('persistenza dati-generali');
    let p: Prospetto;
    p = this.form.getRawValue() as Prospetto;
    p.dAggiorn = this.prospetto.dAggiorn;
    const sedeLegale = this.formSedeLegale.getRawValue() as SedeLegale;
    p.datiAzienda.sedeLegale = sedeLegale;
    console.log(p);
  }

  onClickReset() {
    this.utilitiesService.showSpinner();
    this.form.reset();
    this.formSedeLegale.reset();
    if ((this.ilRuoloUtente.operatoreProvinciale || this.ilRuoloUtente.amministratore || this.ilRuoloUtente.consulenteRespo)) {
      this.fDatiAzienda.controls.cfAzienda.enable();
    }
    this.fDatiAzienda.controls.denominazioneDatoreLavoro.enable();
    this.setForm(this.prospetto);
    this.listMsg = [];
    this.pulisciErroriInPagina();
    this.saveValue();
    this.utilitiesService.hideSpinner();
    if (this.prospetto.id) {
      this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(false);
    }
  }

  /*
  ngOnDestroy() {
    this.saveValue();
  }*/

  /*
  ngOnChanges(changes: SimpleChanges) {
    console.log("ngOnChanges" + changes);
    if (changes.newProspetto && !changes.newProspetto.isFirstChange()) {
      this.newProspetto = changes.newProspetto.currentValue;
    }
  }*/

  saveValue() {
    const newProsp = this.composeData();
    this.newDatiGenerali.emit(newProsp);
  }

  onClickConferma() {
    const newProsp = this.composeData();
    /*check */
    // this.saveValue();
    this.salvaInBozzaDatiGenerali.emit(newProsp);
  }

  private composeData(): Prospetto {
    this.logData();
    const newProsp: Prospetto = this.form.getRawValue() as Prospetto;
    const sedeLegale = this.formSedeLegale.getRawValue() as SedeLegale;
    const cfCapogruppo = newProsp.datiAzienda ? newProsp.datiAzienda.cfCapogruppo : null;
    if (cfCapogruppo === '') {
      newProsp.datiAzienda.cfCapogruppo = null;
    }
    newProsp.datiAzienda.sedeLegale = sedeLegale;
    return newProsp;
  }
  onChangeAteco() {
    this.fDatiAzienda.controls.atecofin.controls.id.setValue(null);
    this.saveValue();
  }
  onChangeCcnl() {
    this.fDatiAzienda.controls.ccnl.controls.id.setValue(null);
    this.saveValue();
  }
  onChangeComuneSedeLegale() {
    this.fSedeLegale.comune.controls.id.setValue(null);
    this.saveValue();
  }
  onChangeComuneDatiAzienda() {
    this.fDatiAzienda.controls.comune.controls.id.setValue(null);
    this.saveValue();
  }
  onChange() {
    this.saveValue();
  }
  onChangeTipologiaDichiarente() {
    this.setValidators();
    this.saveValue();
  }

  setValidators() {
    const dichiarante: Dichiarante = this.f.datiAzienda.controls.dichiarante.value;
    const controlsflgProspettoDaCapogruppo = this.f.datiAzienda.controls.flgProspettoDaCapogruppo;
    const controlsCfCapoGruppo = this.f.datiAzienda.controls.cfCapogruppo;
    controlsflgProspettoDaCapogruppo.clearValidators();
    controlsCfCapoGruppo.clearValidators();
    if (dichiarante && dichiarante.codDichiarante === 'D') {
      controlsflgProspettoDaCapogruppo.setValidators(Validators.required);
      controlsCfCapoGruppo.setValidators(Validators.required);
    }
    controlsflgProspettoDaCapogruppo.updateValueAndValidity();
    controlsCfCapoGruppo.updateValueAndValidity();
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
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

  async onClickFindCFAzienda() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    if ((!this.ilRuoloUtente.operatoreProvinciale && !this.ilRuoloUtente.amministratore && !this.ilRuoloUtente.consulenteRespo)) {
      this.newProspetto.datiAzienda.cfAzienda = this.ilRuoloUtente.codiceFiscale;
    }
    console.log('Find Codice Fiscale' + this.newProspetto.datiAzienda.cfAzienda);
    try {
      const res = await this.silpService.getAzienda(this.newProspetto.datiAzienda.cfAzienda).toPromise();

      if (res) {
        console.log('Codice fiscale trovato su silp ');
        this.newProspetto.datiAzienda.denominazioneDatoreLavoro = res.denominazioneDatoreLavoro;
        this.newProspetto.datiAzienda.elencoSedi = res.elencoSedi;
        this.newProspetto.datiAzienda.ccnl = res.ccnl;
        this.newProspetto.datiAzienda.atecofin = res.atecofin;
        this.newProspetto.datiAzienda.sedeLegale = res.sedeLegale;
        if (this.newProspetto.datiAzienda.sedeLegale) {
          const decodifica: DecodificaGenerica = {
            codDecodifica: this.newProspetto.datiAzienda.sedeLegale.comune.codComuneMin
          };
          const risultatoComuneCercato = await this.decodificaService.postComuneDecodifica(decodifica).toPromise();
          this.newProspetto.datiAzienda.sedeLegale.comune.id = risultatoComuneCercato[0].idDecodifica;
        }
        this.fDatiAzienda.controls.cfAzienda.disable();
        this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(false);
        this.setForm(this.newProspetto);
      } else {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        this.listMsg.push('NESSUN CODICE FISCALE TROVATO');
        this.settaErroriInPagina();
        this.fDatiAzienda.controls.cfAzienda.enable();
        this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(false);
      }
    } catch (e) {
      // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
      if (e.error && e.error.length > 0) {
        this.listMsg.push(e.error[0].errorMessage);
        this.settaErroriInPagina();
      }
      this.fDatiAzienda.controls.cfAzienda.enable();
      this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(true);
      console.log(e.error);
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }



}
