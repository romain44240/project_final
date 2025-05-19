import { Component, OnInit } from '@angular/core';
import { CompteInfoResponse } from '../models';
import { AuthService } from '../service/auth.service';
import { CompteService } from '../service/compte.service';

@Component({
  selector: 'app-compte',
  standalone: false,
  templateUrl: './compte.component.html',
  styleUrl: './compte.component.css'
})
export class CompteComponent implements OnInit {

  compteInfo?: CompteInfoResponse;
  public utilisateurNom: string | null = null;
  selectedReservationId: number | null = null;


  constructor(
    private compteService: CompteService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    const userId = this.authService.getUserId();

    if (!userId) {
      console.error("ID utilisateur manquant !");
      return;
    }

    this.compteService.getCompteInfo(+userId).subscribe({
      next: (data) => {
        this.compteInfo = data;
      },
      error: (err) => {
        console.error("Erreur lors de la récupération des données :", err);
      }
    });

    this.utilisateurNom = this.authService.getNomUtilisateur();
  }


  getDuree(debut: string | Date, fin: string | Date): string {
    const dateDebut = new Date(debut);
    const dateFin = new Date(fin);

    const diffMs = dateFin.getTime() - dateDebut.getTime();
    const diffMinutes = Math.floor(diffMs / (1000 * 60));

    const heures = Math.floor(diffMinutes / 60);
    const minutes = diffMinutes % 60;

    return `${heures} h ${minutes} min`;
  }

  toggleJeux(id: number): void {
    this.selectedReservationId = this.selectedReservationId === id ? null : id;
  }

  isReservationListValid(): boolean {
    return Array.isArray(this.compteInfo?.reservations) && this.compteInfo.reservations.length > 0;
  }

}

