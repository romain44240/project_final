import { Injectable } from '@angular/core';
import { environment } from '../environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employe, Jeu, Reservation, ReservationRequest } from '../models';

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

  getEmployesDisponibles(debut: string, fin: string): Observable<Employe[]> {
    const params = new HttpParams()
      .set('debut', debut)
      .set('fin', fin);

    return this.http.get<any[]>(`${this.apiUrl}/compte/employe/disponibles`, { params });
  }

  getAllJeux(): Observable<Jeu[]> {
    return this.http.get<Jeu[]>(`${this.apiUrl}/produit/jeux`);
  }

  createReservation(reservation: ReservationRequest): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/reservation`, reservation);
  }

}
