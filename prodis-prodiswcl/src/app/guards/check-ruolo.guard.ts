/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Injectable } from '@angular/core';
import {
  CanLoad,
  Route,
  CanActivate,
  ActivatedRouteSnapshot,
  CanActivateChild,
  Router
} from '@angular/router';
import { UserService, SidebarService } from 'src/app/services';
import { first } from 'rxjs/operators';
import { ProdisStorageService } from 'src/app/services/storage/prodis-storage.service';

@Injectable({
  providedIn: 'root'
})
export class CheckRuoloGuard implements CanLoad, CanActivate, CanActivateChild {

  constructor(
    private router: Router,
    // private sidebarService: SidebarService,
    // private userService: UserService,
    private prodisStorageService: ProdisStorageService
  ) {}


  async canLoad(route: Route): Promise<boolean> {
    return this.mayExecute(route);
  }

  async canActivate(route: ActivatedRouteSnapshot): Promise<boolean> {
    return this.mayExecute(route);
  }

  async canActivateChild(childRoute: ActivatedRouteSnapshot): Promise<boolean> {
    return this.mayExecute(childRoute);
  }

  private async mayExecute(route: ActivatedRouteSnapshot | Route): Promise<boolean> {
    if (!this.prodisStorageService.isRuoloCurrentSelected()) {
      this.router.navigateByUrl('/scelta-ruolo');
      return false;
    }

    return true;
  }
}
