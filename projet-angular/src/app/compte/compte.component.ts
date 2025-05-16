import { Component, OnInit } from '@angular/core';
import { environment } from '../environment';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { combineLatest, Observable, of } from 'rxjs';
import { Client, CompteInfoResponse, Consommable, Employe, Jeu, Reservation } from '../models';
import { FormBuilder } from '@angular/forms';
import { AuthService } from '../service/auth.service';
import { CompteService } from '../service/compte.service';

@Component({
  selector: 'app-compte',
  standalone: false,
  templateUrl: './compte.component.html',
  styleUrl: './compte.component.css'
})
export class CompteComponent implements OnInit{
  
  data$!: Observable<CompteInfoResponse>; // On attend un Observable contenant toutes les données

  constructor(private compteService: CompteService) { }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      // Décoder le JWT pour obtenir l'id de l'utilisateur
      const decoded: any = jwt_decode(token);
      const userId = decoded.id; // L'ID de l'utilisateur

      // Appeler le service pour récupérer toutes les informations du compte (y compris les réservations)
      this.data$ = this.compteService.getCompteInfo(userId);
    }
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
function jwt_decode(token: string): any {
  throw new Error('Function not implemented.');
}

