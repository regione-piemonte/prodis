/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Directive, TemplateRef } from '@angular/core';

@Directive({selector: 'ng-template[prodisPaginationBody]'})
export class PaginationBodyDirective<T> {
  constructor(
    public templateRef: TemplateRef<{$implicit: T}>
  ) {}
}
