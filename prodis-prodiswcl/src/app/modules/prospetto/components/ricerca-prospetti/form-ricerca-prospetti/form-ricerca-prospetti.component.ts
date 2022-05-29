/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Params } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { ProspettoService, RicercaProspetto, Ruolo, StatoProspetto } from 'src/app/modules/prodisapi';
import { DecodificaService } from 'src/app/modules/prodisapi/api/decodifica.service';
import { Provincia } from 'src/app/modules/prodisapi/model/provincia';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-form-ricerca-prospetti',
  templateUrl: './form-ricerca-prospetti.component.html',
  styleUrls: ['./form-ricerca-prospetti.component.scss']
})
export class FormRicercaProspettiComponent implements OnInit {

  // @Input() formRicercaProspetto: RicercaProspetto;
  ilFiltroRicercaProspetto: RicercaProspetto;
  @Input() set filtriRicercaProspetto(ricercaProspetto: RicercaProspetto) {
    this.ilFiltroRicercaProspetto = ricercaProspetto;
  }
  @Output() readonly datiRicerca = new EventEmitter<RicercaProspetto>();
  @Output() readonly ricaricaElenco = new EventEmitter<any>();
  @Output() readonly erroriInPagina = new EventEmitter<any>();

  elencoProvince: Provincia[];
  elencoStatiProspetto: StatoProspetto[];
  idProvinciaSelezionato: string;
  listaStatiSelezionati: StatoProspetto[] = [];


  flagMsg = false;
  flagErrorAnnoProt = false;
  flagErrorCodiceFiscale = false;
  flagErrorProtocolloDataDa = false;
  flagErrorProtocolloDataA = false;
  flagErrorRiferimentoDa = false;
  flagErrorRiferimentoA = false;
  flagReset = false;

  ilRuoloUtente: Ruolo;
  readOnlyCFAzienda: boolean;
  // readOnlyProvincia: boolean;
  urlParams: Params;

  listMsg: string[] = [];

  constructor(
    private decodificaService: DecodificaService,
    private utilitiesService: UtilitiesService,
    private translateService: TranslateService,
    private prodisStorageService: ProdisStorageService,
    private prospettoService: ProspettoService

  ) { }

  async ngOnInit() {
    this.utilitiesService.showSpinner();
    const [province,
      statoProspetto
    ] = await Promise.all([
      this.decodificaService.getProvincia().toPromise(),
      this.decodificaService.getStatoProspetto().toPromise(),
    ]);
    this.elencoProvince = province;
    this.elencoStatiProspetto = statoProspetto;
    this.prodisStorageService.ruolo$.subscribe(item => {
      this.ilRuoloUtente = item;
    });
    this.readOnlyCFAzienda = !(this.ilRuoloUtente.amministratore || this.ilRuoloUtente.operatoreProvinciale || this.ilRuoloUtente.consulenteRespo);
    // this.readOnlyProvincia = this.ilRuoloUtente.operatoreProvinciale;

    this.prodisStorageService.filtroRicercaProspetto$.subscribe(item => {
      this.ilFiltroRicercaProspetto = item;
    });



    if (this.ilFiltroRicercaProspetto) {
      if (this.ilFiltroRicercaProspetto.statoProspettos && this.ilFiltroRicercaProspetto.statoProspettos.length > 0) {
        this.listaStatiSelezionati = Utils.clone(this.ilFiltroRicercaProspetto.statoProspettos);
      }
      if (this.ilFiltroRicercaProspetto && this.ilFiltroRicercaProspetto.provincia && this.ilFiltroRicercaProspetto.provincia.codProvinciaMin) {
        this.idProvinciaSelezionato = this.ilFiltroRicercaProspetto.provincia.codProvinciaMin;
      }
    }
    if (this.ilRuoloUtente.consulenteRespo) {
      this.ilFiltroRicercaProspetto.codiceFiscaleStudioProf = this.ilRuoloUtente.codiceFiscale;
    } else if (!this.ilRuoloUtente.operatoreProvinciale && !this.ilRuoloUtente.amministratore) {
      this.ilFiltroRicercaProspetto.codiceFiscaleAzienda = this.ilRuoloUtente.codiceFiscale;
    }

    const flgRitornaElenco = new URL(location.href).searchParams.get('flgRitornaElenco');
    if (flgRitornaElenco && flgRitornaElenco === 'S') {
      this.prodisStorageService.urlParams$.subscribe(item => {
        this.urlParams = item;
        if (this.urlParams && this.urlParams.page > -1) {
          this.ricaricaElenco.emit({
            ilFiltroRicercaProspetto: this.ilFiltroRicercaProspetto,
            numeroPagina: this.urlParams.page
          });
        }
      });
    }
    this.utilitiesService.hideSpinner();

  }

  async onSubmit() {
    this.pulisciFlagInPagina();

    if (!Utils.isNullOrUndefined(this.idProvinciaSelezionato)) {
      if (this.elencoProvince) {
        this.ilFiltroRicercaProspetto.provincia = this.elencoProvince.filter(provincia => provincia.codProvinciaMin === this.idProvinciaSelezionato)[0];
      }
    } else {
      this.ilFiltroRicercaProspetto.provincia = null;
    }
    if (!Utils.isNullOrUndefined(this.listaStatiSelezionati) && this.listaStatiSelezionati.length > 0) {
      this.ilFiltroRicercaProspetto.statoProspettos = this.listaStatiSelezionati;
    } else {
      this.ilFiltroRicercaProspetto.statoProspettos = null;
    }
    this.listMsg = [];

    if (this.ilFiltroRicercaProspetto.codiceFiscaleAzienda) {
      const pippo = await this.prospettoService.getCheckCodiceFiscale(this.ilFiltroRicercaProspetto.codiceFiscaleAzienda).toPromise();
      if (pippo === '0') {
        this.listMsg.push(this.translateService.instant('ERROR.FIELD.FISCAL_CODE.INVALID'));
        this.flagErrorCodiceFiscale = true;
      }
    }
    console.log('controlli sui campi inseriti nella ricerca');

    if (!this.controllaCoppiaCampiObbligatori(this.ilFiltroRicercaProspetto.annoProspetto, this.ilFiltroRicercaProspetto.numeroProtocollo)) {
      this.listMsg.push(this.translateService.instant('ERROR.VALIDATION_FORM.ANNO_AND_NUMERO_PROT'));
      this.flagErrorAnnoProt = true;
    }
    if (!this.controllaCoppiaCampiObbligatori(this.ilFiltroRicercaProspetto.dataProtocolloDa, this.ilFiltroRicercaProspetto.dataProtocolloA)) {
      this.listMsg.push(this.translateService.instant('ERROR.VALIDATION_FORM.DATA_DA_AND_A_PROT'));
      this.flagErrorProtocolloDataA = true;
      this.flagErrorProtocolloDataDa = true;
    }
    if (!this.controllaCoppiaCampiObbligatori(this.ilFiltroRicercaProspetto.dataRiferimentoDa, this.ilFiltroRicercaProspetto.dataRiferimentoA)) {
      this.listMsg.push(this.translateService.instant('ERROR.VALIDATION_FORM.DATA_DA_AND_A_RIF'));
      this.flagErrorRiferimentoDa = true;
      this.flagErrorRiferimentoA = true;
    }
    if (!Utils.isNullOrUndefinedOrCampoVuoto(this.ilFiltroRicercaProspetto.denominazioneAzienda) && this.ilFiltroRicercaProspetto.denominazioneAzienda.length < 3) {
      this.listMsg.push(this.translateService.instant('ERROR.VALIDATION_FORM.DENOM_AZIENDA_MIN_TRE_CARATTERI'));
    }
    if (this.listMsg.length > 0) {
      this.settaErroriInPagina();
    } else {
      this.datiRicerca.emit(this.ilFiltroRicercaProspetto);
      this.prodisStorageService.setFiltroRicercaProspetto(this.ilFiltroRicercaProspetto);
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
    this.pulisciFlagInPagina();

    this.erroriInPagina.emit({
      typeMsg: '',
      hide: false,
      listMsg: []
    });
  }

  private pulisciFlagInPagina() {
    this.flagErrorAnnoProt = false;
    this.flagErrorCodiceFiscale = false;
    this.flagErrorProtocolloDataDa = false;
    this.flagErrorProtocolloDataA = false;
    this.flagErrorRiferimentoDa = false;
    this.flagErrorRiferimentoA = false;
  }

  private putParamInForm() {
    let goSearch = false;
    if (this.ilFiltroRicercaProspetto) {
      if (this.ilFiltroRicercaProspetto.statoProspettos && this.ilFiltroRicercaProspetto.statoProspettos.length > 0) {
        this.listaStatiSelezionati = Utils.clone(this.ilFiltroRicercaProspetto.statoProspettos);
      }
      if (this.filtriRicercaProspetto && this.filtriRicercaProspetto.provincia && this.filtriRicercaProspetto.provincia.codProvinciaMin) {
        this.idProvinciaSelezionato = this.filtriRicercaProspetto.provincia.codProvinciaMin;
      }
    } else {
      return;
    }

    if (
      this.ilFiltroRicercaProspetto.annoProspetto || this.ilFiltroRicercaProspetto.codiceFiscaleAzienda ||
      this.ilFiltroRicercaProspetto.codiceRegionale || this.ilFiltroRicercaProspetto.dataProtocolloA ||
      this.ilFiltroRicercaProspetto.dataProtocolloDa || this.ilFiltroRicercaProspetto.dataRiferimentoA ||
      this.ilFiltroRicercaProspetto.dataRiferimentoDa || this.ilFiltroRicercaProspetto.denominazioneAzienda ||
      this.ilFiltroRicercaProspetto.numeroProtocollo || (this.ilFiltroRicercaProspetto.provincia) ||
      (this.ilFiltroRicercaProspetto.statoProspettos && this.ilFiltroRicercaProspetto.statoProspettos.length > 0)
    ) {
      goSearch = true;
    }

    if (goSearch) {
      this.onSubmit();
    }

  }

  private controllaCoppiaCampiObbligatori(obj1: Object, obj2: Object) {
    return Utils.isNullOrUndefinedOrCampoVuoto(obj1)

      && Utils.isNullOrUndefinedOrCampoVuoto(obj2)
      || !Utils.isNullOrUndefinedOrCampoVuoto(obj1)
      && !Utils.isNullOrUndefinedOrCampoVuoto(obj2);
  }

  checkSelectedStates(statoId: number) {
    const listaStatiSelezionati: StatoProspetto[] = this.ilFiltroRicercaProspetto.statoProspettos;
    if (listaStatiSelezionati && listaStatiSelezionati.length > 0) {
      return listaStatiSelezionati.find(item => item.id === statoId);
    }
  }


  selectRemoveStato(statoP: StatoProspetto) {
    if (this.listaStatiSelezionati) {
      if (this.listaStatiSelezionati.length > 0) {
        const statoTmp = this.listaStatiSelezionati.find(item => item.id === statoP.id);
        const indexItemToRemove: number = this.listaStatiSelezionati.indexOf(statoTmp);
        if (indexItemToRemove >= 0) {
          this.listaStatiSelezionati.splice(indexItemToRemove, 1);
        } else {
          this.listaStatiSelezionati.push(statoP);
        }
      } else {
        this.listaStatiSelezionati.push(statoP);
      }

    }
  }

  onClickReset() {
    this.pulisciErroriInPagina();

    this.ilFiltroRicercaProspetto = ({} as RicercaProspetto);
    this.prodisStorageService.ruolo$.subscribe(item => {
      this.ilRuoloUtente = item;
    });
    if (this.ilRuoloUtente.consulenteRespo) {
      this.ilFiltroRicercaProspetto = {
        codiceFiscaleStudioProf: this.ilRuoloUtente.codiceFiscale
      };
    } else if (!this.ilRuoloUtente.operatoreProvinciale && !this.ilRuoloUtente.amministratore) {
      this.ilFiltroRicercaProspetto = {
        codiceFiscaleAzienda: this.ilRuoloUtente.codiceFiscale
      };
    }
    this.prodisStorageService.setFiltroRicercaProspetto(this.ilFiltroRicercaProspetto);
    this.idProvinciaSelezionato = null;
    this.flagReset = true;
    this.listaStatiSelezionati = [];
    setTimeout(() => this.flagReset = false, 100);
  }

}
