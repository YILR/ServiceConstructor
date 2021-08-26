import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {ModelService} from "../service/model.service";
import {DataService} from "../service/data.service";

@Component({
  selector: 'app-child',
  templateUrl: './child.component.html',
  styleUrls: ['./child.component.scss']
})
export class ChildComponent implements OnInit {

  @Input()
  public childFrom!: FormGroup;

  constructor(public modelService: ModelService, private dataService: DataService) { }

  ngOnInit(): void {
    this.buildForm();
  }

  private buildForm(): void{
    this.childFrom.addControl('child', this.modelService.getChild);
  }

}
