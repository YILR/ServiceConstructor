import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Components} from "../models/Component";
import {Screen} from "../models/Screen";

const SERVER = 'http://localhost:8080/api/db/'

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  getAllScreens(): Observable<Screen[]>{
    return this.http.get<Screen[]>(SERVER+'allScreens');
  }

  getAllComponents(): Observable<any>{
    return this.http.get(SERVER+'allComponents');
  }

  getScreen(id: string): Observable<Screen>{
    return this.http.get<Screen>(SERVER+'screen/'+id);
  }

  getComponent(id: string): Observable<Components>{
    return this.http.get<Components>(SERVER+'component/'+id);
  }

  saveScreen(screen: Screen): Observable<void>{
    return this.http.post<void>(SERVER+'screen', screen);
  }

  saveComponent(component: Components): Observable<void>{
    return this.http.post<void>(SERVER+'component', component);
  }

  deleteScreen(id: string): Observable<void>{
    return this.http.delete<void>(SERVER+'/screen/' + id);
  }

  deleteComponent(id: string): Observable<void>{
    return this.http.delete<void>(SERVER+'/component/' + id);
  }

}
