import { Component } from '@angular/core';
import { environment } from '../environment';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { Client, Compte, Consommable, Employe, Jeu, Produit, Reservation } from '../models';
import { combineLatest, Observable, of } from 'rxjs';

@Component({
  selector: 'app-admin',
  standalone: false,
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  private API_URL: string = `${environment.API_URL}`;
  constructor(private service: AdminService, private router: Router, private formBuilder: FormBuilder){}

  public selectedView = 'users'; 

  public comptes: Observable<{ clients: Client[]; employes: Employe[]; }> = of({ clients: [], employes: [] });
  public products : Observable<{ jeux: Jeu[]; consos: Consommable[]; }> = of({ jeux: [], consos: [] });

  public clients : Observable<Client[]> | undefined;
  public employes : Observable<Employe[]> | undefined;
  public jeux : Observable<Jeu[]> | undefined;
  public conso : Observable<Consommable[]> | undefined;
  public reservations : Observable<Reservation[]> | undefined;


  ngOnInit(): void {
    this.reservations = this.service.getAllReservations();
    this.clients = this.service.getAllClients();
    this.employes = this.service.getAllEmployes();

    this.jeux = this.service.getAllJeux();
    this.conso = this.service.getAllConsos();

    this.products = combineLatest({
      jeux: this.jeux,
      consos: this.conso
    });

    this.comptes = combineLatest({
      clients: this.clients,
      employes: this.employes
    });
  }

  editClient(client: Client): void {
    console.log("Modifier client", client);
  }

  editEmploye(employe: Employe): void {
    console.log("Modifier employé", employe);
  }

  deleteUser(userId: number): void {
    console.log("Supprimer utilisateur avec ID :", userId);
  }

  editJeu(jeu: Jeu): void {
    console.log("Modifier Jeu", jeu);
  }

  editConso(conso : Consommable){
    console.log("Modifier Conso", conso);
  }

  deleteProduct(productId: number): void {
    console.log("Supprimer produit ID :", productId);
  }

  deleteReservation(reservationId: number): void {
    console.log("Supprimer réservation ID :", reservationId);
  }

}