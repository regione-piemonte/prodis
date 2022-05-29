/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { STATO_PROSPETTO_BOZZA, TYPE_ALERT_DANGER, TYPE_ALERT_SUCCESS } from 'src/app/constants';
import { DatiProvinciali, DatiProvincialiService, Prospetto, ProspettoProvSede } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-tab-dettaglio-province',
  templateUrl: './tab-dettaglio-province.component.html',
  styleUrls: ['./tab-dettaglio-province.component.scss']
})
export class TabDettaglioProvinceComponent implements OnInit {

  private static readonly SCROLL_TARGET = 'em[data-scroll-marker="notifiche"]';

  idProspettoProv: number;
  idProvincia: number;
  dsProvincia: string;
  @Output() readonly notifySalvataggio = new EventEmitter<null>();
  @Input() set inputDsProvincia(dsProvincia: string) {
    this.dsProvincia = dsProvincia;
  }
  @Input() set inputIdProspettoProv(idProspettoProv: number) {
    this.idProspettoProv = idProspettoProv;
    this.prodisStorageService.prospetto$.subscribe(async item => {
      if (this.idProspettoProv) {
        this.datiProvinciali = await this.datiProvincialiService.getDatiProvincialiByIdProspettoProv(this.idProspettoProv).toPromise();
        this.setFieldRequired();
        this.datiProvincialiToSave = Utils.clone(this.datiProvinciali);
      }
      this.READ_MODE = item.statoProspetto.descrizione !== STATO_PROSPETTO_BOZZA;
    });
  }
  @Input() set inputIdProvincia(idProvincia: number) {
    this.idProvincia = idProvincia;
  }

  prospetto: Prospetto;
  @Input() set inputProspetto(prospetto: Prospetto) {
    this.prospetto = prospetto;
  }
  READ_MODE = true;
  datiProvinciali: DatiProvinciali;
  datiProvincialiToSave: DatiProvinciali;


  typeMsg: string;
  hide = false;
  listMsg: string[] = [];

  flgDisableConfermaDatiProvBtn: boolean;

  constructor(
    private datiProvincialiService: DatiProvincialiService,
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService
  ) { }

  ngOnInit() {
    this.prodisStorageService.bottoneSalvaProseguiDisabilitato$.subscribe(item => this.flgDisableConfermaDatiProvBtn = item);
  }

  async onRicaricaErrori(erroriParams: any) {
    this.typeMsg = erroriParams.typeMsg;
    this.hide = erroriParams.hide;
    this.listMsg = erroriParams.listMsg;
    this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
  }


  setDatiProvincialiToSave(datiProv: DatiProvinciali) {
    this.datiProvincialiToSave = datiProv;
  }

  async saveDatiProvincialiBozza(datiProv: DatiProvinciali) {
    this.utilitiesService.showSpinner();
    this.pulisciListaErrori();
    // datiProv.provCompensazionis = null;
    // datiProv.postiLavoroDisps = null;
    try {
      datiProv = Utils.setCampiAltreConcessioni(datiProv);
      const res: DatiProvinciali = await this.datiProvincialiService.putDatiProvinciali(datiProv.id, true, datiProv).toPromise();
      if (res) {
        this.datiProvinciali = res;
        this.datiProvincialiToSave = Utils.clone(res);
      }
      this.typeMsg = TYPE_ALERT_SUCCESS;
      this.hide = true;
      this.listMsg.push('Salvataggio dei dati provinciali andato a buon fine');
      this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
      }
    } finally {
      this.notifySalvataggio.emit();
      this.utilitiesService.hideSpinner();
      this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
    }
  }

  async onClickConferma() {
    this.utilitiesService.showSpinner();
    this.pulisciListaErrori();
    let nTelelavoroFt = this.datiProvincialiToSave.nTelelavoroFt;
    nTelelavoroFt = nTelelavoroFt == null ? 0 : nTelelavoroFt;
    this.datiProvincialiToSave.nTelelavoroFt = nTelelavoroFt;

    try {
      this.datiProvincialiToSave = Utils.setCampiAltreConcessioni(this.datiProvincialiToSave);
      const res: DatiProvinciali = await this.datiProvincialiService.putDatiProvinciali(this.datiProvincialiToSave.id, false, this.datiProvincialiToSave).toPromise();
      if (res) {
        this.datiProvinciali = res;
        this.datiProvincialiToSave = res;
        this.typeMsg = TYPE_ALERT_SUCCESS;
        this.hide = true;
        this.listMsg.push('Salvataggio del dato provinciale andato a buon fine');
        this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
      }

    } catch (e) {
      if (e.error && e.error.length > 0) {
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.listMsg = [];
        e.error.forEach(element => {
          this.listMsg.push(element.errorMessage + '<BR>');
        });
        this.utilitiesService.scrollTo(TabDettaglioProvinceComponent.SCROLL_TARGET);
      }
    } finally {
      this.notifySalvataggio.emit();
      this.utilitiesService.hideSpinner();
    }
  }

  private pulisciListaErrori() {
    this.typeMsg = '';
    this.hide = false;
    this.listMsg = [];
  }

  private setFieldRequired() {
    if ( Utils.isNullOrUndefined(this.datiProvinciali.nTotaleLavoratDipendenti)  ) {
      this.datiProvinciali.nTotaleLavoratDipendenti = 0;
    }
    if (!this.datiProvinciali.prospettoProvSede) {
      this.datiProvinciali.prospettoProvSede = {} as ProspettoProvSede;
    }
    if (!this.datiProvinciali.prospettoProvSede.cognomeReferente) {
      this.datiProvinciali.prospettoProvSede.cognomeReferente = this.prospetto.datiAzienda.cognomeReferente;
    }
    if (!this.datiProvinciali.prospettoProvSede.nomeReferente) {
      this.datiProvinciali.prospettoProvSede.nomeReferente =  this.prospetto.datiAzienda.nomeReferente;
    }

    if (Utils.isNullOrUndefined(this.datiProvinciali.nDisabiliInForza)) {
      this.datiProvinciali.nDisabiliInForza = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nCentralTelefoNonvedenti)) {
      this.datiProvinciali.nCentralTelefoNonvedenti = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nPostiPrevCentraliNonved)) {
      this.datiProvinciali.nPostiPrevCentraliNonved = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nTerariabMassofisNonved)) {
      this.datiProvinciali.nTerariabMassofisNonved = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nPostiPrevMassofisNonved)) {
      this.datiProvinciali.nPostiPrevMassofisNonved = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nCateProtForzaA17012000)) {
      this.datiProvinciali.nCateProtForzaA17012000 = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nSomministratiFt)) {
      this.datiProvinciali.nSomministratiFt = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nConvenzioni12bis14Ft)) {
      this.datiProvinciali.nConvenzioni12bis14Ft = 0;
    }
    if (Utils.isNullOrUndefined(this.datiProvinciali.nCateProtForza)) {
      this.datiProvinciali.nCateProtForza = 0;
    }
  }

}
