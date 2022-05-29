/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Directive, HostBinding, Input, Output, EventEmitter, HostListener } from '@angular/core';
import { SortDirection, SortEvent } from 'src/app/models';

const rotate: {[key: string]: SortDirection} = { asc: 'desc', desc: '', '': 'asc' };

@Directive({
  selector: '[prodisSortable]'
})
export class SortableDirective {

  @Input('prodisSortable') sortable: string;
  @Input() direction: SortDirection = '';
  @Output() readonly sort = new EventEmitter<SortEvent>();

  @HostBinding('class.asc') get asc() { return this.direction === 'asc'; }
  @HostBinding('class.desc') get desc() { return this.direction === 'desc'; }

  constructor() { }

  @HostListener('click') rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({column: this.sortable, direction: this.direction});
  }

}
