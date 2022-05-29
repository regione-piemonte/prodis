/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { Directive, ElementRef, Renderer2, HostListener, Injector, ComponentFactoryResolver, ViewContainerRef, NgZone, Inject, ChangeDetectorRef, ApplicationRef, Input } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { TranslateService } from '@ngx-translate/core';
import { NgbTooltip, NgbTooltipConfig } from '@ng-bootstrap/ng-bootstrap';

@Directive({
  selector: '[prodisTooltip]'
})
export class ProdisTooltipDirective extends NgbTooltip {

  constructor(
    private translateService: TranslateService,
    private elementRef: ElementRef,
    private renderer: Renderer2,
    private injector: Injector,
    private componentFactoryResolver: ComponentFactoryResolver,
    private viewContainerRef: ViewContainerRef,
    private tooltipConfig: NgbTooltipConfig,
    private ngZone: NgZone,
    @Inject(DOCUMENT) private document: any,
    private changeDetectorRef: ChangeDetectorRef,
    private applicationRef: ApplicationRef
  ) {
      super(elementRef, renderer, injector, componentFactoryResolver, viewContainerRef, tooltipConfig, ngZone, document, changeDetectorRef, applicationRef);
  }

  @HostListener('mouseover') onMouseOver() {
    const key = this.elementRef.nativeElement.getAttribute('prodisTooltip');
    if (key) {
        const trans = this.translateService.instant(key);
        this.ngbTooltip = trans !== key ? trans : undefined;
    }
  }

}

