/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbAccordion } from '@ng-bootstrap/ng-bootstrap';
import { toJSDate } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-calendar';
import { TYPE_ALERT_DANGER } from 'src/app/constants';
import { PaginationDataChange } from 'src/app/models/pagination-data-change';
import { SortEvent } from 'src/app/models/sort-event';
import { PagedResponseProspetto, ProspettoService, RicercaProspetto, Ruolo } from 'src/app/modules/prodisapi';
import { LogService, UtilitiesService } from 'src/app/services';
import { SidebarService } from 'src/app/services/sidebar.service';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { Utils } from 'src/app/utils';

@Component({
  selector: 'prodis-ricerca-prospetti',
  templateUrl: './ricerca-prospetti.component.html',
  styleUrls: ['./ricerca-prospetti.component.scss']
})
export class RicercaProspettiComponent implements OnInit {

  @ViewChild('accordionRicerca', { static: false }) accordionRicerca: NgbAccordion;

  ricercaEffettuata: boolean = false;
  formRicercaProspetto: RicercaProspetto;
  ricercaProspetto: RicercaProspetto;
  currentPaginationData: PaginationDataChange;
  pagedResponse: PagedResponseProspetto;
  ilRuoloUtente: Ruolo;

  typeMsg: string;
  hide = false;
  listMsg: string[] = [];
  // _listMsg: string[] = [];
  // get listMsg() {
  //   return this._listMsg;
  // }
  // set listMsg(val: string[]) {
  //   try { throw new Error(''); } catch(err) { const e: Error = err; console.log(e.stack); }
  //   this._listMsg = val;
  // }

  constructor(
    private sidebarService: SidebarService,
    private activatedRoute: ActivatedRoute,
    private utilitiesService: UtilitiesService,
    private prospettoService: ProspettoService,
    private router: Router,
    private logService: LogService,
    private prodisStorageService: ProdisStorageService
  ) { }

  ngOnInit() {
    this.utilitiesService.showSpinner();
    this.sidebarService.setShowSideBar(false);
    this.initRicercaProspetto();
    this.currentPaginationData = {
      limit: this.activatedRoute.snapshot.params.limit || 10,
      page: this.activatedRoute.snapshot.params.page || 0,
      offset: 0,
      sort: this.activatedRoute.snapshot.params.sort
    };
    this.utilitiesService.hideSpinner();
  }

  private initRicercaProspetto() {
    if (!this.ricercaProspetto) {
      this.ricercaProspetto = {
        provincia: null
      }
    }
  }

  async onCercaProspetto(ricercaProspetto: RicercaProspetto) {
    this.ricercaEffettuata = false;
    this.ricercaProspetto = Utils.clone(ricercaProspetto);
    this.effettuaRicerca(this.currentPaginationData.page, this.currentPaginationData.limit);
  }

  async onRicaricaElenco(urlParams: any) {
    const flgRitornaElenco = new URL(location.href).searchParams.get("flgRitornaElenco");
    if (flgRitornaElenco && flgRitornaElenco === 'S') {
      this.ricercaEffettuata = false;
      this.ricercaProspetto = Utils.clone(urlParams.ilFiltroRicercaProspetto);
      this.effettuaRicerca(urlParams.numeroPagina, this.currentPaginationData.limit);
    }
  }

  async onRicaricaErrori(erroriParams: any) {
    this.listMsg = [];
    this.typeMsg = erroriParams.typeMsg;
    this.hide = erroriParams.hide;
    this.listMsg = erroriParams.listMsg;
  }

  private async effettuaRicerca(page: number, limit: number, sort?: SortEvent) {
    try {
        this.typeMsg = '';
        this.hide = false;
        this.listMsg = [];
      this.utilitiesService.showSpinner();
      this.pagedResponse = await this.prospettoService.getRicercaProspetti(
        this.ricercaProspetto,
        page,
        limit,
        sort ? sort.column : undefined,
        sort ? sort.direction : undefined)
        .toPromise();

      setTimeout(() => this.ricercaEffettuata = true, 100);

      // collassa l'accordion quando la ricerca ottiene dei risultati. Commentare la seguente istruzione per disabilitare l'automatismo
      this.accordionRicerca.collapseAll();

      this.router.navigate(
        [
          this.clearObject({
            annoProspetto: this.ricercaProspetto.annoProspetto,
            codiceFiscaleAzienda: this.ricercaProspetto.codiceFiscaleAzienda,
            codiceRegionale: this.ricercaProspetto.codiceRegionale,
            dataProtocolloA: this.ricercaProspetto.dataProtocolloA,
            dataProtocolloDa: this.ricercaProspetto.dataProtocolloDa,
            dataRiferimentoA: this.ricercaProspetto.dataRiferimentoA,
            dataRiferimentoDa: this.ricercaProspetto.dataRiferimentoDa,
            denominazioneAzienda: this.ricercaProspetto.denominazioneAzienda,
            numeroProtocollo: this.ricercaProspetto.numeroProtocollo,
            provincia: this.ricercaProspetto.provincia ? this.ricercaProspetto.provincia.id : null,
            page,
            limit,
            sort: JSON.stringify(sort)
          })
        ],
        {
          relativeTo: this.activatedRoute,
          // NOTE: By using the replaceUrl option, we don't increase the Browser's
          // history depth with every filtering keystroke. This way, the List-View
          // remains a single item in the Browser's history, which allows the back
          // button to function much more naturally for the user.
          replaceUrl: true
        }
      );

    } catch (e) {
      this.logService.error(this.constructor.name, 'effettuaRicerca', e);
      //this.utilitiesService.handleApiErrors(e, 'SIDEBAR.ORDINI.ORDER.TITLE');
      if (e.error && e.error.length > 0) {
        if(e.error[0].code === "PRO-PRO-P-0002"){
          //variante 282
          setTimeout(() => this.ricercaEffettuata = false, 100);
          this.accordionRicerca.expandAll();
        }
        this.typeMsg = TYPE_ALERT_DANGER;
        this.hide = true;
        this.listMsg.push(e.error[0].errorMessage);
        // this.utilitiesService.showToastrErrorMessage(e.error[0].errorMessage);
        // this.utilitiesService.showToastrErrorMessage('Pippo', 'Pluto');
      }
    } finally {
      this.utilitiesService.hideSpinner();
    }
  }

  async onChangePaginationData(paginationData: PaginationDataChange) {
    //this.ricercaEffettuata = false;
    this.effettuaRicerca(paginationData.page, paginationData.limit, paginationData.sort);
  }

  private clearObject<T>(obj: T): T {
    const res = {} as T;
    Object.keys(obj)
      .filter(key => obj[key] !== null && obj[key] !== undefined)
      .forEach(key => res[key] = obj[key]);

    return res;
  }

}
