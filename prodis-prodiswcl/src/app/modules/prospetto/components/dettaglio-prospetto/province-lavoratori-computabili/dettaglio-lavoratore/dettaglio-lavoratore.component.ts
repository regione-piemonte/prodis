/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { SidebarService } from 'src/app/services/sidebar.service';

@Component({
  selector: 'prodis-dettaglio-lavoratore',
  templateUrl: './dettaglio-lavoratore.component.html',
  styleUrls: ['./dettaglio-lavoratore.component.scss']
})
export class DettaglioLavoratoreComponent implements OnInit {

  constructor(
    private sidebarService: SidebarService
  ) { }

  ngOnInit() {
    this.sidebarService.setShowSideBar(false);
  }

}
