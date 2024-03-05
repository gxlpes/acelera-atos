import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL_CLIENT_API } from '../envs/env';
import { Cliente } from '../interfaces/cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private baseUrl = BASE_URL_CLIENT_API
  private resource = 'cliente'

  constructor(private http: HttpClient) { }

  getAll(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(`${this.baseUrl}/${this.resource}`)
  }

  getById(id: string): Observable<Cliente> {
    return this.http.get<Cliente>(`${this.baseUrl}/${this.resource}/${id}`)
  }

  save(cliente: Partial<Cliente>): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.baseUrl}/${this.resource}`, cliente)
  }

  delete(id: string): Observable<{}> {
    return this.http.delete(`${this.baseUrl}/${this.resource}/${id}`)
  }

  update(id: string, cliente: Partial<Cliente>): Observable<Cliente> {
    return this.http.patch<Cliente>(`${this.baseUrl}/${this.resource}/${id}`, cliente)
  }

}
