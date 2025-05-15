import { Component } from '@angular/core';
import { environment } from '../environment';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { combineLatest, Observable, of } from 'rxjs';
import { Client, Consommable, Employe, Jeu, Reservation } from '../models';
import { FormBuilder } from '@angular/forms';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-compte',
  standalone: false,
  templateUrl: './compte.component.html',
  styleUrl: './compte.component.css'
})
export class CompteComponent {
  private API_URL: string = `${environment.API_URL}`;
  currentUser: Client | null = null;

  constructor(private service: AdminService, private router: Router, private formBuilder: FormBuilder, private authService: AuthService) { }

  public selectedView = 'users';

  public comptes: Observable<{ clients: Client[]; employes: Employe[]; }> = of({ clients: [], employes: [] });
  public products: Observable<{ jeux: Jeu[]; consos: Consommable[]; }> = of({ jeux: [], consos: [] });

  public clients: Observable<Client[]> | undefined;
  public employes: Observable<Employe[]> | undefined;
  public jeux: Observable<Jeu[]> | undefined;
  public conso: Observable<Consommable[]> | undefined;
  public reservations: Observable<Reservation[]> | undefined;


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

     this.currentUser = this.authService.getCurrentUser();
  }

  getDuree(debut: string | Date, fin: string | Date): string {
    const dateDebut = new Date(debut);
    const dateFin = new Date(fin);

    const diffMs = dateFin.getTime() - dateDebut.getTime(); // différence en millisecondes
    const diffMinutes = Math.floor(diffMs / (1000 * 60));

    const heures = Math.floor(diffMinutes / 60);
    const minutes = diffMinutes % 60;

    return `${heures} h ${minutes} min`;
  }

  



}
