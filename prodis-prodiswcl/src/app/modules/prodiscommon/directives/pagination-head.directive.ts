/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { TemplateRef, Directive } from '@angular/core';

@Directive({selector: 'ng-template[prodisPaginationHead]'})
export class PaginationHeadDirective {
  constructor(
    public templateRef: TemplateRef<{}>
  ) {}
}
