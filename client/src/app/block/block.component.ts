import {Component, OnInit} from '@angular/core';
import {ScreenService} from "../service/screen.service";
import {Screen} from "../models/Screen";
import {Router} from "@angular/router";


@Component({
  selector: 'app-block',
  templateUrl: './block.component.html',
  styleUrls: ['./block.component.scss']
})
export class BlockComponent implements OnInit{

  screens: Screen[] = JSON.parse(localStorage.getItem('screen') || '[]');
  code: any;


  constructor(private screenService: ScreenService, private router: Router) {
  }

  ngOnInit(): void {
    this.screenService.postCheck(this.screens).subscribe(screen=>{
      this.screens = screen;
      localStorage.setItem('screen', JSON.stringify(this.screens))
    })
  }

  submit(): void {

    this.generate(this.screens);
  }

  private generate(screens: any): void {
    this.screenService.createServiceInit(screens, this.code).subscribe(scr => {
      console.log(scr);
      this.screenService.serviceInit = scr;
      this.router.navigate(['/generated']);
    })
  }

  removeScr(idx: number): void {
    this.screens.splice(idx, 1);
    localStorage.setItem('screen', JSON.stringify(this.screens))
  }


  edit(id: any) {
    this.router.navigate(['/screen'], {queryParams: {id: id}})
  }

  checkRule(rule: any[]): boolean{
    return rule !== undefined;
  }
}
