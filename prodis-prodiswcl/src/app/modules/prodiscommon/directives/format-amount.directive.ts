/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[prodisFormatAmount]'
})
export class FormatAmountDirective {

  constructor() { }

  @HostListener ('blur') lostFocus() {
    // TODO?
  }
}
