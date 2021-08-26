import { Component, OnInit } from '@angular/core';
import {ScreenService} from "../service/screen.service";

@Component({
  selector: 'app-generated',
  templateUrl: './generated.component.html',
  styleUrls: ['./generated.component.scss']
})
export class GeneratedComponent implements OnInit {

  serviceInit: any = this.screenService.serviceInit;

  constructor(private screenService: ScreenService) { }

  ngOnInit(): void {
  }

}
