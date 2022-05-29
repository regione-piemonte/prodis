/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgSelectModule } from '@ng-select/ng-select';
import { TranslateModule, TranslateService } from '@ngx-translate/core';
import {
  HasValueClassDirective,
  CustomCheckboxDirective,
  DigitOnlyDirective,
  PercDigitOnlyDirective,
  IsInvalidClassDirective,
  SortableDirective,
  FormatAmountDirective,
  NgSelectHasValueDirective,
  PaginationHeadDirective,
  PaginationBodyDirective,
  ScrollListenerDirective
} from './directives';
import {
  SidebarLeftComponent
} from 'src/app/components';
import { RouterModule } from '@angular/router';
import { UnsafeHtmlPipe, CodeDescPipe, SiNoPipe, EscapeHtmlPipe } from 'src/app/pipes';
import { NgbModalModule, NgbPaginationModule, NgbAlertModule, NgbDropdownModule,
  NgbPopoverModule, NgbDateAdapter, NgbDateNativeAdapter, NgbDateParserFormatter,
  NgbDatepickerI18n, NgbTooltip } from '@ng-bootstrap/ng-bootstrap';
import { NgxMaskModule } from 'ngx-mask';
import { NgxCurrencyModule } from 'ngx-currency';
import { NestableModule } from 'ngx-nestable';
import { NgbCustomDateParserFormatterService, NgbCustomI18nService, PromptModalService } from './services';
import {
  BackButtonComponent,
  PaginatedTableComponent,
  PaginatedArrayTableComponent,
  TableComponent,
  HeadDirective,
  BodyDirective,
  TreeModalComponent,
  PromptModalComponent
} from './components';
import { FormatNumAmountDirective } from './directives/format-num-amount.directive';
import { ProdisTooltipDirective } from './directives/prodis-tooltip.directive';
import { PreventDoubleClickDirective } from './directives/prevent-double-click.directive';
import { ProdisDisabledTooltipDirective } from './directives/prodis-disabled-tooltip.directive';
import { ErrorModalComponent } from './components/error-modal/error-modal.component';
import { AlertMsgComponent } from './components/alert-msg/alert-msg.component';
import { ListaDecodificaModalComponent } from './components/lista-decodifica-modal/lista-decodifica-modal.component';
import { ListaSediModalComponent } from './components/lista-sedi-modal/lista-sedi-modal.component';
import { IdOnlyDirective } from './directives/list-id-only.directive - Copia';
import { UploadLavoratoriModalComponent } from './components/upload-lavoratori-modal/upload-lavoratori-modal.component';

@NgModule({
  declarations: [
    SidebarLeftComponent,
    BackButtonComponent,
    TreeModalComponent,
    PromptModalComponent,
    ErrorModalComponent,

    HasValueClassDirective,
    CustomCheckboxDirective,
    DigitOnlyDirective,
    PercDigitOnlyDirective,
    IdOnlyDirective,
    IsInvalidClassDirective,
    SortableDirective,
    NgSelectHasValueDirective,
    ProdisTooltipDirective,
    ProdisDisabledTooltipDirective,
    PreventDoubleClickDirective,
    CodeDescPipe,
    UnsafeHtmlPipe,
    SiNoPipe,
    EscapeHtmlPipe,

    PaginatedTableComponent,
    PaginatedArrayTableComponent,
    PaginationHeadDirective,
    PaginationBodyDirective,
    FormatAmountDirective,
    FormatNumAmountDirective,
    TableComponent,
    HeadDirective,
    BodyDirective,
    ScrollListenerDirective,
    AlertMsgComponent,
    ListaDecodificaModalComponent,
    ListaSediModalComponent,
    UploadLavoratoriModalComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TranslateModule,
    NgSelectModule,
    NgbModalModule,
    NgbPaginationModule,
    NgbAlertModule,
    NgxMaskModule,
    FormsModule,
    NgbDropdownModule,
    NgbPopoverModule,
    RouterModule,
    NgxCurrencyModule,
    NestableModule,
  ],
  exports: [
    CommonModule,
    NgSelectModule,
    NgbModalModule,
    NgbPaginationModule,
    NgbAlertModule,
    TranslateModule,
    ReactiveFormsModule,
    NgbDropdownModule,
    NgbPopoverModule,
    NgxMaskModule,
    FormsModule,
    NestableModule,

    SidebarLeftComponent,
    BackButtonComponent,

    HasValueClassDirective,
    CustomCheckboxDirective,
    DigitOnlyDirective,
    PercDigitOnlyDirective,
    IdOnlyDirective,
    IsInvalidClassDirective,
    SortableDirective,
    NgSelectHasValueDirective,
    ProdisTooltipDirective,
    ProdisDisabledTooltipDirective,
    PreventDoubleClickDirective,

    CodeDescPipe,
    UnsafeHtmlPipe,
    SiNoPipe,
    EscapeHtmlPipe,

    PaginatedTableComponent,
    PaginatedArrayTableComponent,
    PaginationHeadDirective,
    PaginationBodyDirective,
    ScrollListenerDirective,
    FormatNumAmountDirective,
    AlertMsgComponent,

    TableComponent,
    HeadDirective,
    BodyDirective,
    NgxCurrencyModule

  ],
  providers: [
    { provide: NgbDateAdapter, useClass: NgbDateNativeAdapter },
    { provide: NgbDateParserFormatter, useClass: NgbCustomDateParserFormatterService },
    { provide: NgbDatepickerI18n, useClass: NgbCustomI18nService, deps: [ TranslateService ] },
    PromptModalService
  ],
  entryComponents: [
    TreeModalComponent,
    PromptModalComponent,
    ErrorModalComponent,
    ListaDecodificaModalComponent,
    ListaSediModalComponent,
    UploadLavoratoriModalComponent
  ]
})
export class ProdiscommonModule { }
