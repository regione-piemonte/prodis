/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { UserService } from '../../../../services';
// import { RouteService } from 'src/app/services';
// import { Router } from '@angular/router';

@Component({
  selector: 'prodis-back-button',
  templateUrl: './back-button.component.html',
  styleUrls: ['./back-button.component.scss']
})
export class BackButtonComponent implements OnInit {

  previousRoute: string;
  constructor(
    private location: Location,
    private userService: UserService
    // private routeService: RouteService,
    // private router: Router
  ) { }

  ngOnInit() {
    // this.previousRoute = this.routeService.getPreviousUrl();
  }

  onClick() {
    // this.location.back();
    this.userService.back();
    // this.router.navigateByUrl(this.previousRoute);
  }
}
