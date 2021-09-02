import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Screen, ScreenRule} from "../models/Screen";
import {ActivatedRoute, Router} from "@angular/router";
import {ModelService} from "../service/model.service";
import {DataService} from "../service/data.service";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {ScreenService} from "../service/screen.service";
import {CompsComponent} from "../comps/comps.component";
import {Components} from "../models/Component";
import {NotificationService} from "../service/notification.service";

@Component({
  selector: 'app-screen',
  templateUrl: './screen.component.html',
  styleUrls: ['./screen.component.scss']
})
export class ScreenComponent implements OnInit, AfterViewInit {

  form!: FormGroup;
  types: string[] = ['INFO', 'QUESTION', 'CUSTOM', 'UNIQUE', 'REPEATABLE', 'C_UNIQUE', 'EMPTY']
  screens: Screen[] = JSON.parse(localStorage.getItem('screen') || '[]');

  filteredOptions!: Observable<Screen[]>;
  @ViewChild(CompsComponent)
  com!: CompsComponent;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private router: Router,
              private modelService: ModelService, private dataService: DataService,
              private screenService: ScreenService, private cdRef: ChangeDetectorRef,
              private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    this.buildForm();

    this.dataService.getAllScreens().subscribe((scr) => {
      this.filteredOptions = this.form.valueChanges
        .pipe(
          startWith(''),
          map(value => this._filter(value.id, scr))
        );
    });

  }

  ngAfterViewInit() {
    this.route.queryParams.subscribe((par) => {
      if (par.type === undefined)
        this.getScreen(par.id);
      else if(par.type === 'db')
        this.getScreenFromDb(par.id)
      else if (par.type === 'component')
        this.com.getComponent(par.id, 0)
    })
    this.cdRef.detectChanges();
  }

  private _filter(value: string, scr: Screen[]): Screen[] {
    let filterId!: string
    if (value !== undefined) {
      filterId = value.toLowerCase();
    }

    return scr.filter(option => option.id.toLowerCase().includes(filterId));
  }

  submit() {
    console.log(this.form)
    this.onCheckScreen();
  }

  saveScreen() {
    this.dataService.saveScreen(this.form.value).subscribe(() => {
        console.log("Done!")
      this.notificationService.showSnackBar('Экран сохранен')
      });
  }

  onChange($event: any) {
    this.getScreenFromDb($event.option.value)
  }

  private buildForm(): void {
    this.form = this.fb.group({
      type: [''],
      id: ['', [Validators.required]],
      header: ['', [Validators.required]],
      suggestion: [],
      components: this.fb.array([
       this.modelService.getComponent
      ])
    });
  }

  private onCheckScreen() {
    this.screens.push(this.form.value);
    this.screenService.postOnCheck(this.screens).subscribe((scr) => {
      this.screens = scr;
      localStorage.setItem('screen', JSON.stringify(this.screens));
      this.router.navigate(['/']);
    })
  }

  private getScreenFromDb(id: any){
    if (id !== undefined) {
      this.dataService.getScreen(id).subscribe((s) => {
        this.autoComplete(s);
      })
    }
  }

  private getScreen(id: any) {
    if (id !== undefined) {
      let scr: Screen = this.screens.find(screen => screen.id === id)!
      this.autoComplete(scr)
    }
  }

  private autoComplete(scr: Screen) {
    if (scr) {
      this.form.patchValue(scr)
      scr.components.forEach((com, i) => {
        if (com.fileType) {
          Object.keys(com.fileType).forEach((file: any) =>{
            (this.form.get('components')?.get([i])?.get(['fileType']) as FormGroup).addControl(file, new FormControl(com.fileType[file]))
          } )
        }

        if (i >= 1) {
          (this.form.get('components') as FormArray).push(this.modelService.getComponent);
          (this.form.get('components') as FormArray).at(i).patchValue(com);
        }

        this.completeChild(scr)
        this.completeField(com, i);
        this.completeRef(com, i);

      })

      this.completeScreenRule(scr, 'screenRules');
      this.completeScreenRule(scr, 'cycleScreenRules');
    }

  }

  private completeChild(scr: Screen){
    if(scr.child){
      this.form.addControl('child', this.modelService.getChild);
      this.form.get('child').patchValue(scr.child)
    }
  }

  private completeField(com: Components, idx: number) {
    if (com.field) {
      com.field?.forEach((ans, n) => {

        if (n >= 1) {
          (this.form.get('components')?.get([idx])?.get('field') as FormArray).push(this.modelService.getField);
          (this.form.get('components')?.get([idx])?.get('field') as FormArray).at(n).patchValue(ans);
        }
      });
    }
  }

  private completeRef(com: Components, i: number) {
    if (com.ref) {
      com.ref.forEach((r, n) => {
        this.com.addRef(i);
        (this.form.get('components')?.get([i])?.get('ref') as FormArray).at(n).patchValue(r);
      })
    }
  }

  private completeScreenRule(scr: Screen, ruleName: string) {
    if (scr.screenRules) {
      scr.screenRules.forEach((scR: ScreenRule, i: number) => {

        if(this.form.get(ruleName) === null){
          this.form.addControl(ruleName, new FormArray([this.modelService.getScreenRules]));
        }else {
          (this.form.get(ruleName) as FormArray).push(this.modelService.getScreenRules);
        }

        (this.form.get(ruleName) as FormArray).at(i).patchValue(scR)

        scR.conditions.forEach((condition, n) => {
          if (n >= 1) {
            (this.form.get(ruleName)?.get([i])?.get('conditions') as FormArray).push(this.modelService.getConditions);
            (this.form.get(ruleName)?.get([i])?.get('conditions') as FormArray).at(n).patchValue(condition);
          }
        });
      })
    }
  }

  selectChange() {
    this.com.ngSelectChange()
  }
}
