import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ScreenComponent} from './screen/screen.component';
import {CompsComponent} from './comps/comps.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {BlockComponent} from './block/block.component';
import {HttpClientModule} from "@angular/common/http";
import {ScreenRuleComponent} from './screen-rule/screen-rule.component';
import {GeneratedComponent} from './generated/generated.component';
import {ScreenTableComponent} from './table/screen-table/screen-table.component';
import {ComponentTableComponent} from './table/component-table/component-table.component';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatTableModule } from '@angular/material/table';
import {MatInputModule} from "@angular/material/input";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatDialogModule} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import { DialogBoxComponent } from './table/dialog-box/dialog-box.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import { ChildComponent } from './child/child.component';


@NgModule({
  declarations: [
    AppComponent,
    ScreenComponent,
    CompsComponent,
    BlockComponent,
    ScreenRuleComponent,
    GeneratedComponent,
    ScreenTableComponent,
    ComponentTableComponent,
    DialogBoxComponent,
    ChildComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatDialogModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatAutocompleteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
