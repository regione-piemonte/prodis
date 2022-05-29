/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PARAMETRO_PRODIS_ABILITATO, TYPE_ALERT_DANGER } from 'src/app/constants';
import { CommonService, Ruolo, UserAccessLog } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { SidebarService } from 'src/app/services/sidebar.service';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';


@Component({
  selector: 'prodis-scelta-ruolo',
  templateUrl: './scelta-ruolo.component.html',
  styleUrls: ['./scelta-ruolo.component.scss']
})
export class SceltaRuoloComponent implements OnInit {

  listaRuoli: Ruolo[];
  typeMsg: string;
  hide = false;
  listMsg: string[] = [];

  ilRuoloSelezionato : Ruolo;

  constructor(
    private sidebarService: SidebarService,
    private router: Router,
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService,
    private commonService: CommonService
  ) { }

  async ngOnInit() {

    const res = await this.commonService.getParametro(PARAMETRO_PRODIS_ABILITATO).toPromise();
    if (res && res.codDecodifica === 'N') {
      this.listMsg.push("" + res.dsDecodifica);
      this.settaErroriInPagina();

    } else {
      this.utilitiesService.showSpinner();
      this.sidebarService.setShowSideBar(true);
      this.listaRuoli = await this.utilitiesService.getRuoli();
      if (this.listaRuoli.length === 0) {
        this.listMsg.push("Nessun ruolo trovato ! ");
        this.settaErroriInPagina();
      }
    }
    this.utilitiesService.hideSpinner();
  }

  onClickChange(el) {
    this.prodisStorageService.setRuolo(el);
    this.ilRuoloSelezionato = el as Ruolo;
    let userAccessLog : UserAccessLog = {
      cfUtente: this.ilRuoloSelezionato.cfUtente,
      dsCognome : this.ilRuoloSelezionato.denominazioneAzienda,
      dsRuolo : this.ilRuoloSelezionato.ruolo,
      dsNome :  this.ilRuoloSelezionato.codiceFiscale
      }
    this.commonService.insertUserAccessLog(userAccessLog).toPromise();
    this.router.navigate(['/home']);
  }

  private settaErroriInPagina() {
    this.typeMsg = TYPE_ALERT_DANGER,
      this.hide = true,
      this.listMsg = this.listMsg
  }


}
