/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { AssunzioneProtetta, CausaSospensione, DatiProvinciali, DecodificaGenerica, DecodificaService, StatoConcessione } from 'src/app/modules/prodisapi';
import { UserService, UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-form-tab-altre-concessioni',
  templateUrl: './form-tab-altre-concessioni.component.html',
  styleUrls: ['./form-tab-altre-concessioni.component.scss']
})
export class FormTabAltreConcessioniComponent implements OnInit {

  @Input() datiProvinciali: DatiProvinciali;
  @Output() readonly erroriInPagina = new EventEmitter<any>();
  @Output() readonly saveDatiProvinciali = new EventEmitter<DatiProvinciali>();
  @Output() readonly saveDatiProvincialiBozza = new EventEmitter<DatiProvinciali>();
  newDatiProv: DatiProvinciali;
  @Input() set inputNewDatiProvinciali(datiProvinciali: DatiProvinciali) {
    this.newDatiProv = datiProvinciali;
  }
  form: FormGroup;
  comboTipologiaAssunzione: AssunzioneProtetta[] = [];
  comboStatoConcessione: StatoConcessione[] = [];
  comboCausaSosp: CausaSospensione[] = [];
  show = false;

  listMsg: string[] = [];

  get f() {return this.form.controls as any; }

  constructor(
    private utilitiesService: UtilitiesService,
    private decodificaService: DecodificaService,
    private userService: UserService,
    private prodisStorageService: ProdisStorageService
  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    try {
      const [tipologiaAssunzione, statiConcessione, causaSosp] = await Promise.all([
        this.decodificaService.postAssunzioneProtettaDecodifica({} as DecodificaGenerica).toPromise(),
        this.decodificaService.postStatoConcessioneDecodifica({} as DecodificaGenerica).toPromise(),
        this.decodificaService.postCausaSospensioneDecodifica({} as DecodificaGenerica).toPromise()
      ]);
      this.setListCombo(tipologiaAssunzione, statiConcessione, causaSosp);
      this.initForm(this.newDatiProv);
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.settaErroriInPagina();
      }
    }
    this.utilitiesService.hideSpinner();
  }

  private setListCombo(tipologiaAssunzione: DecodificaGenerica[], statiConcessione: DecodificaGenerica[], causaSosp: DecodificaGenerica[]) {
    tipologiaAssunzione.forEach(item => {
      this.comboTipologiaAssunzione.push({
        id: item.idDecodifica,
        codAssunzioneProtetta: item.codDecodifica,
        descAssunzioneProtetta: item.dsDecodifica
      });
    });
    statiConcessione.forEach(item => {
      this.comboStatoConcessione.push({
        id: item.idDecodifica,
        codStatoConcessione: item.codDecodifica,
        descStatoConcessione: item.dsDecodifica
      });
    });
    causaSosp.forEach(item => {
      this.comboCausaSosp.push({
        id: item.idDecodifica,
        codCausaSospensione: item.codDecodifica,
        desCausaSospensione: item.dsDecodifica
      });
    });
  }

  private initForm(datiProv: DatiProvinciali) {
    this.form = new FormGroup({
      provConvenzione: new FormGroup({
        statoConcessione: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.statoConcessione : null),
        dataAtto: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.dataAtto : null),
        estremiAtto: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.estremiAtto : null),
        assunzioneProtetta: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.assunzioneProtetta : null),
        numLavPrevConvQ2: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.numLavPrevConvQ2 : null),
        dataStipula: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.dataStipula : null),
        dataScadenza: new FormControl(datiProv && datiProv.provConvenzione ? datiProv.provConvenzione.dataScadenza : null),
      }),
      provEsonero: new FormGroup({
        statoConcessione: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.statoConcessione : null),
        dataAtto: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.dataAtto : null),
        estremiAtto: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.estremiAtto : null),
        dataAttoFinoAl: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.dataAttoFinoAl : null),
        percentuale: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.percentuale : null),
        nLavoratoriEsonero: new FormControl(datiProv && datiProv.provEsonero ? datiProv.provEsonero.nLavoratoriEsonero : null),
      }),
      provEsoneroAutocert: new FormGroup({
        dataAutocert: new FormControl(datiProv && datiProv.provEsoneroAutocert ? datiProv.provEsoneroAutocert.dataAutocert : null),
        nLav60x1000: new FormControl(datiProv && datiProv.provEsoneroAutocert ? datiProv.provEsoneroAutocert.nLav60x1000 : null),
        percentualeEsAutocert: new FormControl(datiProv && datiProv.provEsoneroAutocert ? datiProv.provEsoneroAutocert.percentualeEsAutocert : null),
        nLavEsoneroAutocert: new FormControl(datiProv && datiProv.provEsoneroAutocert ? datiProv.provEsoneroAutocert.nLavEsoneroAutocert : null)
      }),
      provSospensione: new FormGroup({
        statoConcessione: new FormControl(datiProv && datiProv.provSospensione ? datiProv.provSospensione.statoConcessione : null),
        causaSospensione: new FormControl(datiProv && datiProv.provSospensione ? datiProv.provSospensione.causaSospensione : null),
        dataFineSospensioneQ2: new FormControl(datiProv && datiProv.provSospensione ? datiProv.provSospensione.dataFineSospensioneQ2 : null),
        nLavoratori: new FormControl(datiProv && datiProv.provSospensione ? datiProv.provSospensione.nLavoratori : null)
      }),
      provGradualita: new FormGroup({
        nAssunzioniEffDopoTrasf: new FormControl(datiProv && datiProv.provGradualita ? datiProv.provGradualita.nAssunzioniEffDopoTrasf : null)
      }),
      note: new FormControl(datiProv ? datiProv.note : null)
    });
    this.setValueInCombo(this.newDatiProv);
    this.show = true;
  }

  onChange() {
    this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(this.form.invalid);
    if(!this.form.invalid){
      this.mapValues();
      this.saveDatiProvinciali.emit(this.newDatiProv);
    }
   }

   private mapValues() {
    const datiProvForm = this.form.getRawValue() as DatiProvinciali;
    this.newDatiProv.provConvenzione = datiProvForm.provConvenzione;
    this.newDatiProv.provEsonero = datiProvForm.provEsonero;
    this.newDatiProv.provEsoneroAutocert = datiProvForm.provEsoneroAutocert;
    this.newDatiProv.provSospensione = datiProvForm.provSospensione;
    this.newDatiProv.provGradualita = datiProvForm.provGradualita;
    this.newDatiProv.note = datiProvForm.note;
   }

   private setValueInCombo(datiProv: DatiProvinciali) {
    if (datiProv && datiProv.id) {
      const provConvenzione = datiProv.provConvenzione;
      if (provConvenzione) {
          const statoConcessione = provConvenzione.statoConcessione;
          if (statoConcessione && statoConcessione.id) {
            this.f.provConvenzione.controls.statoConcessione.patchValue(this.getstatoConcessCombo(statoConcessione.id));
          }
          const assunzioneProtetta = provConvenzione.assunzioneProtetta;
          if (assunzioneProtetta) {
            this.f.provConvenzione.controls.assunzioneProtetta.patchValue(this.getAssProtettaCombo(assunzioneProtetta.id));
          }
        }
      }

    const provEsonero = datiProv.provEsonero;
    if (provEsonero) {
        const statoConcessione = provEsonero.statoConcessione;
        if (statoConcessione && statoConcessione.id) {
          this.f.provEsonero.controls.statoConcessione.patchValue(this.getstatoConcessCombo(statoConcessione.id));
        }
      }

    const provSospensione = datiProv.provSospensione;
    if (provSospensione) {
        const statoConcessione = provSospensione.statoConcessione;
        if (statoConcessione && statoConcessione.id) {
          this.f.provSospensione.controls.statoConcessione.patchValue(this.getstatoConcessCombo(statoConcessione.id));
        }
        const causaSosp = provSospensione.causaSospensione;
        if (causaSosp && causaSosp.id) {
          this.form.get('provSospensione').get('causaSospensione').patchValue(this.comboCausaSosp.find(item => item.id === causaSosp.id));
        }

      }
   }

   datePickerChange(date: NgbDate) {
    setTimeout(() => this.onChange(), 50);
  }

   private getstatoConcessCombo(id: number): StatoConcessione {
     return this.comboStatoConcessione.find(item => item.id === id);
  }

  private getAssProtettaCombo(id: number): AssunzioneProtetta {
    return this.comboTipologiaAssunzione.find(item => item.id === id);
  }

  onClickReset() {
    this.form.reset();
    this.pulisciErroriInPagina();
    this.initForm(this.datiProvinciali);
    this.mapValues();
    this.saveDatiProvinciali.emit(this.newDatiProv);
    this.triggerUiUpdate();
  }

  private settaErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: TYPE_ALERT_DANGER,
      hide: true,
      listMsg: this.listMsg
    });
  }

  salvaBozza() {
    this.mapValues();
    this.saveDatiProvincialiBozza.emit(this.newDatiProv);
  }

  private pulisciErroriInPagina() {
    this.erroriInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

  private triggerUiUpdate() {
    this.userService.triggerUiUpdate();
  }

}
