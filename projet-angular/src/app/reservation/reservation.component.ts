import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Employe, Jeu, ReservationRequest } from '../models';
import { ReservationService } from '../service/reservation.service';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-reservation',
  standalone: false,
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements OnInit {
  public authForm!: FormGroup;
  submitted = false;

  public capaciteMax = 8;
  public employesDisponibles: Employe[] = [];
  public jeux: Observable<Jeu[]> | undefined;


  constructor(private service: AuthService,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private reservationService: ReservationService,
    private snackBar: MatSnackBar) { }

  ngOnInit(): void {

    this.jeux = this.reservationService.getAllJeux();

    this.authForm = this.formBuilder.group({
      date: [''],
      heure: [''],
      duree: [''],
      table: [''],
      capacite: [''],
      employe: [''],
      jeux: ['']
    });

    this.route.queryParams.subscribe(params => {
      const date = params['date'];
      const heure = params['heure'];
      const duree = params['duree'];
      const table = params['table'];
      const capacite = params['capacite'];
      this.capaciteMax = capacite;

      this.authForm.patchValue({
        date: date,
        heure: heure,
        duree: duree,
        table: table,
        capacite: capacite
      });

      if (date && heure && duree) {
        const debut = `${date}T${heure}:00`;
        const fin = this.getFinHoraire(date, heure, duree);
        this.loadAvailableEmployees(debut, fin);
      }
    });

  }

  getFinHoraire(date: string, heure: string, duree: number): string {
    const [h, m] = heure.split(':').map(Number);
    const d = new Date(Date.UTC(
      parseInt(date.substring(0, 4)),
      parseInt(date.substring(5, 7)) - 1,
      parseInt(date.substring(8, 10)),
      h,
      m
    ));

    d.setMinutes(d.getMinutes() + duree);
    return d.toISOString().slice(0, 16);
  }

  loadAvailableEmployees(debut: string, fin: string): void {
    this.reservationService.getEmployesDisponibles(debut, fin).subscribe({
      next: (data) => this.employesDisponibles = data,
      error: (err) => console.error('Erreur lors du chargement des employés disponibles', err)
    });
  }

  public onSubmit(): void {
    this.submitted = true;
    if (this.authForm.invalid) return;

    const formData = this.authForm.value;
    const clientId = this.service.getUserId() ?? 0;

    const request: ReservationRequest = {
      debut: formData.date + 'T' + formData.heure + ':00',
      fin: this.getFinHoraire(formData.date, formData.heure, formData.duree),
      nbPersonne: formData.capacite,
      idClient: clientId,
      idEmploye: formData.employe || undefined,
      idSurface: formData.table,
      idJeu: formData.jeux || undefined
    };

    this.reservationService.createReservation(request).subscribe({
      next: (data) => {
        this.router.navigate(['/']);

        this.snackBar.open('Votre réservation a bien été prise en compte', 'Fermer', {
          duration: 3000,
          verticalPosition: 'bottom',
          horizontalPosition: 'center'
        });
      },
      error: (err) => {
        console.error('Erreur lors de la création de la réservation:', err);
        this.snackBar.open('Une erreur s\'est produite, veuillez réessayer', 'Fermer', {
          duration: 3000,
          verticalPosition: 'bottom',
          horizontalPosition: 'center',
        });
      }
    });
  }
}

