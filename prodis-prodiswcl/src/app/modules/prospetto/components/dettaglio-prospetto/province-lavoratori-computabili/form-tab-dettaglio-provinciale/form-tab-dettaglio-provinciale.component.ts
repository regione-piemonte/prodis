/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TYPE_ALERT_DANGER, TYPE_DECODIFICA_GENERICA } from 'src/app/constants';
import { Comune, DatiAzienda, DatiProvinciali, DecodificaGenerica, DecodificaService, Prospetto, SedeLegale, SilpService } from 'src/app/modules/prodisapi';
import { PromptModalService } from 'src/app/modules/prodiscommon/services';
import { UserService, UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-form-tab-dettaglio-provinciale',
  templateUrl: './form-tab-dettaglio-provinciale.component.html',
  styleUrls: ['./form-tab-dettaglio-provinciale.component.scss']
})
export class FormTabDettaglioProvincialeComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Input() idProvincia: number;
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();
  newDatiProv: DatiProvinciali;
  prospetto: Prospetto;
  dsProvincia: string;
  @Input() set inputDsProvincia(dsProvincia: string) {
    this.dsProvincia = dsProvincia;
  }
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
    this.prodisStorageService.prospetto$.subscribe(item => {
      this.prospetto = item;
      this.initForm();
    });
  }


  form: FormGroup;
  listMsg: string[] = [];

  // restituisce formControls
  get f() { return this.form.controls as any; }

  constructor(
    private decodificaService: DecodificaService,
    private utilitiesService: UtilitiesService,
    private promptModalService: PromptModalService,
    private userService: UserService,
    private silpService: SilpService,
    private prodisStorageService: ProdisStorageService
  ) { }

  ngOnInit() {
    // this.utilitiesService.showSpinner();

    // this.utilitiesService.hideSpinner();
  }

  private initForm() {
    this.form = new FormGroup({
      nTotaleLavoratDipendenti: new FormControl(this.newDatiProv.nTotaleLavoratDipendenti, Validators.required),
      prospettoProvSede: new FormGroup({
        id: new FormControl(this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.id : null),
        cap: new FormControl(this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.cap : null, Validators.minLength(5)),
        indirizzo: new FormControl(this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.indirizzo : null),
        telefono: new FormControl(
          this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.telefono : null
          // Validators.pattern("/[0-9\/\.\- ]+$/")
        ),
        fax: new FormControl(
          this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.fax : null
          // Validators.pattern("[0-9\/\.\- ]+")
        ),
        email: new FormControl(
          this.newDatiProv.prospettoProvSede ? this.newDatiProv.prospettoProvSede.email : null
        ),
        cognomeReferente: new FormControl(this.newDatiProv.prospettoProvSede.cognomeReferente),
        nomeReferente: new FormControl(this.newDatiProv.prospettoProvSede.nomeReferente),
        comune: new FormGroup({
          id: new FormControl(this.newDatiProv.prospettoProvSede && this.newDatiProv.prospettoProvSede.comune ? this.newDatiProv.prospettoProvSede.comune.id : null),
          codComuneMin: new FormControl(
            this.newDatiProv.prospettoProvSede && this.newDatiProv.prospettoProvSede.comune ? this.newDatiProv.prospettoProvSede.comune.codComuneMin : null,
            Validators.minLength(3)
          ),
          dsProTComune: new FormControl(this.newDatiProv.prospettoProvSede && this.newDatiProv.prospettoProvSede.comune ? this.newDatiProv.prospettoProvSede.comune.dsProTComune : null,
            Validators.minLength(3)
          )
        })
      })
    });
  }

  onClickReset() {
    this.pulisciErroriInPagina();
    this.form.reset();
    // this.setForm(this.datiProvinciali);
    this.pulisciErroriInPagina();
    this.form.patchValue(this.datiProvinciali);
    this.mapValues();
    this.saveDatiProvinciali.emit(this.newDatiProv);
    this.triggerUiUpdate();
  }

  async onClickFindComune() {
    this.utilitiesService.showSpinner();
    this.listMsg = [];
    this.pulisciErroriInPagina();
    const decodifica: DecodificaGenerica = {
       codDecodifica: this.f.prospettoProvSede.get('comune').get('codComuneMin').value,
       dsDecodifica: this.f.prospettoProvSede.get('comune').get('dsProTComune').value,
       idFiltroFacoltativo: this.idProvincia
     };
    try {
       const res = await this.decodificaService.postComuneDecodifica(decodifica).toPromise();
       let decod: DecodificaGenerica;
       this.utilitiesService.hideSpinner();
       if (res && res.length === 1) {
          decod = res[0];
        }
       if (res && res.length > 1) {
          decod = await this.promptModalService.openDecodificaPrompt('Seleziona Comune', res, TYPE_DECODIFICA_GENERICA.COMUNE, decodifica);
        }
       if (decod) {
          const comune: Comune = {
            id: decod.idDecodifica,
            codComuneMin: decod.codDecodifica,
            dsProTComune: decod.dsDecodifica
          };
          this.f.prospettoProvSede.controls.comune.patchValue(comune);
          this.onChange();
        } else {
            // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
            this.listMsg.push('Il comune ricercato non è presente nella provincia: ' + this.dsProvincia);
            this.settaErroriInPagina();
        }
     } catch (e) {
      if (e.error && e.error.length > 0) {
        e.error.forEach(item => {
          this.listMsg.push(item);
        });
      }
      this.settaErroriInPagina();
     } finally {
      this.utilitiesService.hideSpinner();
     }
    }

    private triggerUiUpdate() {
      this.userService.triggerUiUpdate();
    }

    async onClickCercaSede() {
      this.utilitiesService.showSpinner();
      const cfAzienda = this.prospetto.datiAzienda.cfAzienda;
      this.listMsg = [];
      try {
        const res: DatiAzienda = await this.silpService.getAzienda(cfAzienda).toPromise();
        const listaSedi: SedeLegale[] = res.elencoSedi ? res.elencoSedi : null;
        if (res && listaSedi) {
          console.log('Codice fiscale trovato su silp ');
          this.utilitiesService.hideSpinner();
          const sedeRes = await this.promptModalService.openListaSediPrompt('Prospetto disabili web - ricerca sede', listaSedi, cfAzienda);
          this.f.prospettoProvSede.controls.comune.patchValue(sedeRes.comune);
          this.f.prospettoProvSede.controls.indirizzo.patchValue(sedeRes.indirizzo);
          this.f.prospettoProvSede.controls.telefono.patchValue(sedeRes.telefono);
          this.onChange();
        } else {
          // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
           this.listMsg.push('Non esistono sedi per l\'azienda inserita');
           this.settaErroriInPagina();
        }
      } catch (e) {
        // in questo caso non e' stato trovato nessun risultato quindi bisogna far comparire messaggio di errore
        if (e.error && e.error.length > 0) {
          const errorList = e.error;
          errorList.forEach(e => {
            this.listMsg.push(e.errorMessage);
          });
        }
        this.listMsg.push('Non esistono sedi per l\'azienda inserita');
        this.settaErroriInPagina();
        console.log(e.error);
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
      this.erroriInPagina.emit({
        typeMsg: '',
        hide: false,
        listMsg: []
      });
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
      this.newDatiProv.nTotaleLavoratDipendenti = this.form.controls.nTotaleLavoratDipendenti.value;
      this.newDatiProv.prospettoProvSede = this.form.controls.prospettoProvSede.value;
    }
    // ngOnDestroy() {
    //   this.saveValue();
    // }

    // ngOnChanges(changes: SimpleChanges) {
    //   console.log("ngOnChanges" + changes);
    //   if (changes.inputNewDatiProvinciali && !changes.inputNewDatiProvinciali.isFirstChange()) {
    //      console.log(changes.inputNewDatiProvinciali.currentValue);
    //   }
    // }

    // saveValue() {
    //   let newProsp: Prospetto = this.form.getRawValue() as Prospetto;
    //   const sedeLegale = this.formSedeLegale.getRawValue() as SedeLegale
    //   newProsp.datiAzienda.sedeLegale = sedeLegale;
    //   //this.newDatiGenerali.emit(newProsp);
    // }

}
