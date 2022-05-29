/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NEW_PROSPETTO, STATO_PROSPETTO_BOZZA } from 'src/app/constants';
import { CommonService, DatiAzienda, Prospetto, RicercaProspetto, Ruolo, SystemService } from 'src/app/modules/prodisapi';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';
import { TitoloPaginaService } from 'src/app/services/titolo-pagina.service';
import { SidebarService, UtilitiesService } from '../../services';

@Component({
  selector: 'prodis-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit, OnDestroy {

  // @ViewChild('ts', { static: true }) myTabs: NgbTabset;
  tabTitolo: boolean = true;
  tabCode: boolean = false;
  ilRuolo: Ruolo;

  clickTab(tabName: string) {
    if (tabName == 'titolo') {
      this.tabTitolo = true;
      this.tabCode = false;
    } else if (tabName == 'code') {
      this.tabTitolo = false;
      this.tabCode = true;
    }
  }

  formSearchIntervento: FormGroup = new FormGroup({
    titolo: new FormControl(null),
    codIntervento: new FormControl(null)
  });

  constructor(
    private sidebarService: SidebarService,
    private utilitiesService: UtilitiesService,
    private titoloPaginaService: TitoloPaginaService,
    private router: Router,
    private prodisStorageService: ProdisStorageService,
    private systemService: SystemService
  ) { }

  ngOnInit() {
    // this.utilitiesService.hideSpinner();
    // this.sidebarService.setShowSideBar(true);
    this.sidebarService.loadContent(null);
    this.titoloPaginaService.triggerTitolo('APP.TITLE');
    this.prodisStorageService.ruolo$.subscribe(async item => {
      this.ilRuolo = item;
      console.log("prospetto in dettaglio-prospetto dentro subscribe component: " + this.ilRuolo);
    });
  }

  ngOnDestroy() {
  }


  onClickRicerca() {
    this.router.navigateByUrl('/ricerca-prospetti');
    this.prodisStorageService.setFiltroRicercaProspetto(({} as RicercaProspetto));
  }

  onClickInserisci() {
    let prospetto = NEW_PROSPETTO;
    prospetto.cfStudioProfessionale = this.ilRuolo.codiceFiscale;
    if(this.ilRuolo.idSoggetti){
      prospetto.soggetti = {id: this.ilRuolo.idSoggetti}
    }
    this.prodisStorageService.setProspetto(prospetto);
    this.prodisStorageService.setProspettoToSave(prospetto);
    this.router.navigateByUrl('/dettaglio-prospetto');
    this.prodisStorageService.setBottoneSalvaProseguiDisabilitato(true);
  }

  async onClickTestError() {
    var res = await this.systemService.testError(401).toPromise();
  }

  isBlank(str) {
    return (!str || /^\s*$/.test(str));
  }

  onGoToLink(link: string) {
    this.router.navigateByUrl(link);
  }

}
