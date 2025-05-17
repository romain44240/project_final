import { Injectable, OnInit } from '@angular/core';
import { environment } from '../environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private apiUrl: string = `${environment.API_URL}`;

  constructor(private http: HttpClient) { }

  getSurfacesDisponibles(debut: string, fin: string): Observable<any[]> {
    const params = new HttpParams()
      .set('debut', debut)
      .set('fin', fin);

    return this.http.get<any[]>(`${this.apiUrl}/surface/disponible`, { params });
  }
}
