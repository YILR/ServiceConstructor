import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormGroup} from "@angular/forms";
import {ModelService} from "../service/model.service";
import {ScreenRule} from "../models/Screen";

@Component({
  selector: 'app-screen-rule',
  templateUrl: './screen-rule.component.html',
  styleUrls: ['./screen-rule.component.scss']
})
export class ScreenRuleComponent implements OnInit {
  @Input()
  public childFrom!: FormGroup;
  @Input()
  public ruleName: string;
  constructor(public modelService: ModelService) { }

  ngOnInit(): void {
  }

  get formData() {
    return <FormArray>this.childFrom.get(this.ruleName);
  }

  formCon(idx: number): FormArray {
    return <FormArray>this.childFrom.get(this.ruleName)?.get([idx])?.get('conditions');
  }

  addScreenRule() {
    if(this.childFrom.get(this.ruleName) == null){
      this.childFrom.addControl(this.ruleName, new FormArray([this.modelService.getScreenRules]))
    }else {
      (this.childFrom.get(this.ruleName) as FormArray).push(this.modelService.getScreenRules);
    }
  }

  addConditions(idx: any) {
    console.log(idx);
    (this.childFrom.get(this.ruleName)?.get([idx])?.get('conditions') as FormArray).push(this.modelService.getConditions);
  }

  removeConditions(idx: any, i: any) {
    (this.childFrom.get(this.ruleName)?.get([idx])?.get('conditions') as FormArray).removeAt(i);
  }

  removeScreenRule(idx: any) {
     idx === 0 ? this.childFrom.removeControl(this.ruleName) : (this.childFrom.get(this.ruleName) as FormArray).removeAt(idx);
  }

  cloneScreenRule() {
    let size =  (this.childFrom.get(this.ruleName) as FormArray).length;
    const scrRule: ScreenRule = this.childFrom.get(this.ruleName)?.get([size-1])?.value;
    this.addScreenRule();
    (this.childFrom.get(this.ruleName) as FormArray).at(size).patchValue(scrRule)
    scrRule.conditions.forEach((cond, i) => {
      if(i >= 1){
        this.addConditions(size);
        (this.childFrom.get(this.ruleName)?.get([size])?.get('conditions') as FormArray).at(i).patchValue(cond);
      }
    })
  }

  titleRuleName(): string{
    return this.ruleName === 'screenRules' ? 'П' : 'Цикличный п';
  }

  disabledCheck(): boolean{
    return this.childFrom.get(this.ruleName) == null ? true : this.childFrom.get(this.ruleName).pristine;
  }
}
