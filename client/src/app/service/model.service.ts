import { Injectable } from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ModelService {

  constructor() { }

  public get getComponent(): FormGroup{
    return new FormGroup({
      id: new FormControl('', [Validators.required]),
      type: new FormControl(''),
      label: new FormControl(''),
      required: new FormControl(true),
      hint: new FormControl(null),
      suggestionId: new FormControl(),
      grid: new FormControl(),
      field: new FormArray([this.getField]),
      mask: new FormControl(),
      position: new FormControl(),
      fileType: new FormGroup({}),
      maxSize: new FormControl('50'),
      charsAmount: new FormControl(null),
      dictionaryType: new FormControl(null),
      atLeastOne: new FormControl(),
      fieldName: new FormControl(),
      hidden: new FormControl()
    });
  }

  public get getChild(): FormGroup{
    return new FormGroup({
      id: new FormControl('', [Validators.required]),
      label: new FormControl(),
      minAge: new FormControl(),
      maxAge: new FormControl(),
      singleChild: new FormControl(),
      isCycled: new FormControl(false)
    });
  }

  public get getField(): FormGroup {
    return new FormGroup({
      label: new FormControl(),
      value: new FormControl()
    })
  }

  public get getRef(): FormGroup {
    return new FormGroup({
      relatedRel: new FormControl(),
      val: new FormControl(),
      relation: new FormControl()
    })
  }

  public get getScreenRules(): FormGroup{
    return new FormGroup({
      nextDisplay: new FormControl(''),
      conditions: new FormArray([this.getConditions])
    })
  }

  public get getConditions(): FormGroup{
    return new FormGroup({
      field: new FormControl(),
      value: new FormControl(),
      predicate: new FormControl()
    })
  }

  public turn(idTag: string) {
    document.getElementById(idTag).removeAttribute('open')
    return false;
  }
}
