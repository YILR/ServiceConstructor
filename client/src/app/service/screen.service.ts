import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Screen} from "../models/Screen";

const SERVER = 'http://localhost:8080/api/'

@Injectable({
  providedIn: 'root'
})
export class ScreenService {
  serviceInit: any;

  constructor(private http: HttpClient) { }

  createServiceInit(screen: Screen[], code: any): Observable<any>{
   return this.http.post<Screen[]>(SERVER+'create/'+code, screen);
  }

  postCheck(screen: Screen[]): Observable<Screen[]> {
    return this.http.post<Screen[]>(SERVER+'check', screen);
  }

  postOnCheck(screen: Screen[]): Observable<Screen[]> {
    return this.http.post<Screen[]>(SERVER+'oncheck', screen);
  }
}
