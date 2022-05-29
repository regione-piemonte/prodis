/*
* SPDX-FileCopyrightText: Copyright 2022 | Regione Piemonte
* SPDX-License-Identifier: EUPL-1.2
*/
import { FormGroup, AbstractControl, FormArray, FormControl } from '@angular/forms';

export class ProdisValidators {

  static atLeastOneNotEmpty() {
    return (controlGroup: FormGroup) => {
      const valid = ProdisValidators.checkFormGroup(controlGroup, field => field.value);
      if (!valid) {
        return {
          atLeastOneNotEmpty : {
            text : 'At least one value should be set'
          }
        };
      }
      return null;
    };
  }

  static atLeastOneNotValid() {
    return (controlGroup: FormGroup) => {
      const valid = ProdisValidators.checkFormGroup(controlGroup, field => field.valid);
      if (!valid) {
        return {
          atLeastOneNotEmpty : {
            text : 'At least one value should be set'
          }
        };
      }
      return null;
    };
  }

  private static checkFormGroup(controlGroup: FormGroup | FormArray, operation: (fc: AbstractControl) => boolean) {
    const controls = controlGroup.controls;
    if (controls) {
      return Object.keys(controls).some(key => {
        const field = controls[key];
        if (field instanceof FormGroup || field instanceof FormArray) {
          return ProdisValidators.checkFormGroup(field, operation);
        }
        return operation(field);
      });
    }
    return true;
  }

  static minNumVal(minVal: number) {
    return (control: FormControl) => {
      const val = control.value;
      if (isNaN(val)) {
        return true;
      } else {
        let numVal = Number.parseFloat(val);
        if (numVal === 0) {
          numVal = 0.00;
        }
        if (minVal > numVal) {
          return {
            minNumVal: {
              text: 'Too small value'
            }
          };
        } else {
          return null;
        }
      }
    };
  }
}
