import { Injectable } from '@angular/core';
import { environment } from '../environment';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient } from '@angular/common/http';
import { Client, Compte, Consommable, Employe, Jeu, Produit, Reservation } from '../models';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private apiUrl: string = `${environment.API_URL}`;

  constructor(private http: HttpClient) {}

  // Compte / Client / Employes 
  getAllClients(): Observable<Client[]>{
    return this.http.get<Client[]>(`${this.apiUrl}/compte/clients`);
  }
  getAllEmployes(): Observable<Employe[]>{
    return this.http.get<Employe[]>(`${this.apiUrl}/compte/employes`);
  }

  addClient(user: Client): Observable<Client> {
    return this.http.post<Client>(`${this.apiUrl}/compte/client`, user);
  }
  addEmploye(user: Employe): Observable<Employe> {
    return this.http.post<Employe>(`${this.apiUrl}/compte/employe`, user);
  }

  updateClient(user: Client): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/compte/client/${user.id}`, user);
  }
  updateEmploye(user: Employe): Observable<Employe> {
    return this.http.put<Employe>(`${this.apiUrl}/compte/employe/${user.id}`, user);
  }

  deleteClient(id: number){
    this.http.delete(`${this.apiUrl}/compte/delete/client/${id}`);
  }

  deleteEmploye(id: number){
    this.http.delete(`${this.apiUrl}/compte/delete/employe/${id}`);
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
  addProduct(product: Produit): Observable<Produit> {
    return this.http.post<Produit>(`${this.apiUrl}/produit`, product);
  }
  updateProduct(product: Produit): Observable<Produit> {
    return this.http.put<Produit>(`${this.apiUrl}/produit/${product.id}`, product);
  }
  deleteProduct(id: number) {
    this.http.delete(`${this.apiUrl}/produit/${id}`);
  }

  // Réservations
  getAllReservations(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.apiUrl}/reservation`);
  }
  addReservation(reservation:Reservation):Observable<Reservation>{
    return this.http.post<Reservation>(`${this.apiUrl}/reservation`, reservation);
  }
  updateReservation(reservation:Reservation):Observable<Reservation>{
    return this.http.put<Reservation>(`${this.apiUrl}/reservation`, reservation);
  }
  deleteReservation(id: number) {
    this.http.delete(`${this.apiUrl}/reservation/${id}`);
  }
}
