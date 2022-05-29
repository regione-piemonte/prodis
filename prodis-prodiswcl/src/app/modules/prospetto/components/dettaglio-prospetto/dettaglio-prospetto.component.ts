/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Prospetto } from 'src/app/modules/prodisapi';
import { UtilitiesService } from 'src/app/services';
import { SidebarService } from 'src/app/services/sidebar.service';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Component({
  selector: 'prodis-dettaglio-prospetto',
  templateUrl: './dettaglio-prospetto.component.html',
  styleUrls: ['./dettaglio-prospetto.component.scss']
})
export class DettaglioProspettoComponent implements OnInit {


  @ViewChild('modalSuntoProspetto', { static: false }) modalSuntoProspetto: any;
  paramIdProspetto: number;
  prospetto: Prospetto;
  constructor(
    private readonly router: Router,
    private route: ActivatedRoute,
    private sidebarService: SidebarService,
    private modalService: NgbModal,
    private prodisStorageService: ProdisStorageService,
    private utilitiesService: UtilitiesService
  ) { }


  ngOnInit() {
    this.utilitiesService.showSpinner();
    this.sidebarService.setShowSideBar(false);
    this.route.queryParams.subscribe(params => { console.log('params: '+params)});
    this.prodisStorageService.prospetto$.subscribe(item =>{
      this.prospetto = item;
      console.log("prospetto in dettaglio-prospetto dentro subscribe component: "+this.prospetto);
    });
    console.log("prospetto in dettaglio-prospetto fuori subscribe component: "+this.prospetto);
    this.utilitiesService.hideSpinner();
  }



  async openModalSuntoProspetto(){
    try {
      await this.modalService.open(this.modalSuntoProspetto, { scrollable: true }).result;
    } catch (e) {
      // Rejected. Ignore and exit
      return;
    }
  }

  public onClickTornaAlloElenco(){
    return this.router.navigateByUrl('/ricerca-prospetti?flgRitornaElenco=S');
  }

}
