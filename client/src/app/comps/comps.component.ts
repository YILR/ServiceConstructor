import {Component, Input} from '@angular/core';
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {TypesComps} from "../models/types/types-comps";
import {ModelService} from "../service/model.service";
import {DataService} from "../service/data.service";
import {Observable} from "rxjs";
import {Components} from "../models/Component";
import {map, startWith} from "rxjs/operators";
import {NotificationService} from "../service/notification.service";

@Component({
  selector: 'app-comps',
  templateUrl: './comps.component.html',
  styleUrls: ['./comps.component.scss']
})
export class CompsComponent  {

  @Input()
  public childFrom!: FormGroup;
  fileType: string[] = this.typesComps.fileType;
  filteredOptions!: Observable<Components[]>;
  private components: Components[];

  constructor(public typesComps: TypesComps, public modelService: ModelService,
              private dataService: DataService, private notificationService: NotificationService) {
    this.dataService.getAllComponents().subscribe(comps => {
      this.components = comps;
      if(this.getComponentsByType !== undefined){
        this.ngSelectChange();
      }
    })

  }

  public ngSelectChange(){

    let comps = this.components.filter(comp =>
      this.getComponentsByType.includes(comp.type));

    this.filteredOptions = this.childFrom.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value.id, comps))
      );
  }

  private _filter(value: string, comps: Components[]): Components[] {
    let filterId!: string
    if (value !== undefined) {
      filterId = value.toLowerCase();
    }

    return  comps.filter(option => option.id.toLowerCase().includes(filterId));
  }

  addComponent() {
    (this.childFrom.get('components') as FormArray).push(this.modelService.getComponent);
  }

  addField(idx: any) {
    (this.childFrom.get('components')?.get([idx])?.get('field') as FormArray).push(this.modelService.getField);
  }

   addRef(idx: any) {
    console.log(this.childFrom.get('components')?.get([idx]))
    if(this.childFrom.get('components')?.get([idx])?.get('ref') === null){
      (this.childFrom.get('components')?.get([idx]) as FormGroup).addControl('ref', new FormArray([this.modelService.getRef]))
    }else {
      (this.childFrom.get('components')?.get([idx])?.get('ref') as FormArray).push(this.modelService.getRef);
    }

  }

  get formData() {
    return <FormArray>this.childFrom.get('components');
  }

  formField(idx: any) {
    return <FormArray>this.childFrom.get('components')?.get([idx])?.get('field');
  }

  formRef(idx: any) {
    return <FormArray>this.childFrom.get('components')?.get([idx])?.get('ref');
  }

  get getComponentsByType() {
    return this.typesComps.types[this.childFrom.get('type')?.value];
  }

  removeComponent(idx: number) {
    (this.childFrom.get('components') as FormArray).removeAt(idx);
  }

  removeField(idx: any, i: any) {
    (this.childFrom.get('components')?.get([idx])?.get('field') as FormArray).removeAt(i);
  }

  removeRef(idx: any, i: any) {
    i === 0 ? (this.childFrom.get('components')?.get([idx]) as FormGroup).removeControl('ref') :
      (this.childFrom.get('components')?.get([idx])?.get('ref') as FormArray).removeAt(i);
  }

  fileTypes(idx: any){
    if(!this.childFrom.get('components')?.get([idx]).get('fileType').get('JPEG')){
        this.fileType.forEach(file=>
          (this.childFrom.get('components')?.get([idx])?.get('fileType') as FormGroup).addControl(file, new FormControl(true))
        )
    }

    return this.fileType;
  }

  onChange($event: any, idx: number) {
    this.getComponent($event.option.value, idx)
  }

  public getComponent(id: any, idx: number) {
    if (id !== undefined) {
      this.dataService.getComponent(id).subscribe(copm => this.autoComplete(copm, idx));
    }
  }

  onCheckRequiredAndGrid(type: string): boolean{
    return type !== 'QuestionScr' && type !== 'CheckBox';
  }

  onCheckValid(type: string): boolean{
    return  !['StringInput', 'SnilsInput'].includes(type);
  }

  private autoComplete(comp: Components, idx: number) {

    this.childFrom.get('components')?.get([idx])?.patchValue(comp)

    if (comp && comp.type === 'QuestionScr') {
      comp.field?.forEach((ans, n) => {
        if( n >= 1){
          this.addField(idx);
          (this.childFrom.get('components')?.get([idx])?.get('field') as FormArray).at(n).patchValue(ans);
        }
      });
    }
  }

  saveComponent(idx: number): void {
    this.dataService.saveComponent(this.childFrom.get('components')?.get([idx])?.value).subscribe(() => {
        console.log("Done!")
        this.notificationService.showSnackBar('?????????????????? ????????????????')
      });
  }

}
