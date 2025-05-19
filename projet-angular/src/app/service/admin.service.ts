import { Injectable } from '@angular/core';
import { environment } from '../environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Client, Consommable, Employe, Jeu, Produit, Reservation } from '../models';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private apiUrl: string = `${environment.API_URL}`;

  constructor(private http: HttpClient) { }

  // Compte / Client / Employes 
  getAllClients(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.apiUrl}/compte/clients`);
  }
  getAllEmployes(): Observable<Employe[]> {
    return this.http.get<Employe[]>(`${this.apiUrl}/compte/employes`);
  }

  createClient(user: Client): Observable<Client> {
    return this.http.post<Client>(`${this.apiUrl}/compte/client`, user);
  }
  createEmploye(user: Employe): Observable<Employe> {
    return this.http.post<Employe>(`${this.apiUrl}/compte/employe`, user);
  }

  updateClient(user: Client): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/compte/client/${user.id}`, user);
  }
  updateEmploye(user: Employe): Observable<Employe> {
    return this.http.put<Employe>(`${this.apiUrl}/compte/employe/${user.id}`, user);
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/compte/client/${id}`);
  }

  // Produits
  getAllProducts(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/produit`);
  }
  getAllJeux(): Observable<Jeu[]> {
    return this.http.get<Jeu[]>(`${this.apiUrl}/produit/jeux`);
  }
  getAllConsos(): Observable<Consommable[]> {
    return this.http.get<Consommable[]>(`${this.apiUrl}/produit/consos`);
  }
  createJeu(product: Jeu): Observable<Jeu> {
    return this.http.post<Jeu>(`${this.apiUrl}/produit/jeu`, product);
  }
  createConso(product: Consommable): Observable<Consommable> {
    return this.http.post<Consommable>(`${this.apiUrl}/produit/consommable`, product);
  }
  updateJeu(product: Jeu): Observable<Jeu> {
    return this.http.put<Jeu>(`${this.apiUrl}/produit/jeu/${product.id}`, product);
  }
  updateConso(product: Consommable): Observable<Consommable> {
    return this.http.put<Consommable>(`${this.apiUrl}/produit/consommable/${product.id}`, product);
  }
  deleteProduct(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/produit/${id}`);
  }

  // Réservations
  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/reservation`);
  }
  createReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.post<Reservation>(`${this.apiUrl}/reservation`, reservation);
  }
  updateReservation(reservation: Reservation): Observable<Reservation> {
    return this.http.put<Reservation>(`${this.apiUrl}/reservation`, reservation);
  }
  deleteReservation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/reservation/${id}`);
  }
}
