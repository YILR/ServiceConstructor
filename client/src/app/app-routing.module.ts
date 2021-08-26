import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ScreenComponent} from "./screen/screen.component";
import {BlockComponent} from "./block/block.component";
import {GeneratedComponent} from "./generated/generated.component";
import {ScreenTableComponent} from "./table/screen-table/screen-table.component";

const routes: Routes = [
  {path: '', component: BlockComponent},
  {path: 'screen', component: ScreenComponent},
  {path: 'generated', component: GeneratedComponent},
  {path: 'table', component: ScreenTableComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
