import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GeneralServiceService {

  baseUrl: string;

  constructor(private http: HttpClient) { 
    this.baseUrl = environment.backend;
  }
  
  getFechaEspecial(): Observable<Date>{
      return this.http.get<Date>(this.baseUrl + '/fechaEspecial');
  }

}
