import { Injectable } from '@angular/core';
import { environment } from '../environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompteInfoResponse } from '../models';

@Injectable({
  providedIn: 'root'
})
export class CompteService {

  private apiUrl: string = `${environment.API_URL}`;

  constructor(private http: HttpClient) {}

  getCompteInfo(userId: number): Observable<CompteInfoResponse> {
    return this.http.get<CompteInfoResponse>(`${this.apiUrl}/compte/client/${userId}/info`);
  }

}
