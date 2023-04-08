import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { Usuario } from '../interfaces/usuario';
import { Carrito, Producto, Tipo } from '../interfaces/carrito';
import { FechaEspecial } from '../interfaces/fecha-especial';

@Injectable({
  providedIn: 'root'
})
export class GeneralService {

  baseUrl: string;

  constructor(private http: HttpClient) { 
    this.baseUrl = environment.backend;
  }
  
  getFechaEspecial(): Observable<FechaEspecial>{
      return this.http.get<FechaEspecial>(this.baseUrl + '/fechaEspecial');
  }

  login(usuario: string): Observable<Usuario>{
      return this.http.get<Usuario>(this.baseUrl + '/login?nombre=' + usuario);
  }

  getProductos(): Observable<Producto[]>{
      return this.http.get<Producto[]>(this.baseUrl + '/productos')
  }

  getTipoCarrito(vip: boolean): Observable<Tipo>{
      return this.http.get<Tipo>(this.baseUrl + '/consultarTipoCarrito?vip=' + vip);
  }

  registrarCarrito(carrito: Carrito): Observable<void>{
      return this.http.post<void>(this.baseUrl + '/registrarCarrito', carrito);
  }

}
