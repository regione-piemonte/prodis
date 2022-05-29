/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'prodis-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  goToTop(e: Event): boolean {
    e.preventDefault();
    document.getElementsByTagName('prodis-header')[0].scrollIntoView({ behavior: 'smooth', block: 'center' });
    return false;
  }
}
