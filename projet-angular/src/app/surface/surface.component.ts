import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ReservationService } from '../service/reservation.service';

@Component({
  selector: 'app-surface',
  standalone: false,
  templateUrl: './surface.component.html',
  styleUrl: './surface.component.css'
})
export class SurfaceComponent implements OnInit {
  selectedDate: string = '';
  selectedTime: string = '11:00';
  selectedDuree: number = 15;
  currentDate: string = '';
  surface = {
    table: 0,
    capaciteMax: 8
  };

  surfacesDisponibles: any[] = [];

  constructor(private router: Router, private authService: AuthService, private reservationService: ReservationService) { }

  ngOnInit(): void {
    const today = new Date();
    this.currentDate = today.toISOString().split('T')[0];
    this.selectedDate = this.currentDate;

    this.checkDisponibilites();
  }

  onDateChange(): void {
    if (this.selectedDate) {
      this.selectedTime = '11:00';
    } else {
      this.selectedTime = '';
    }
    this.checkDisponibilites();
  }

  onTimeChange(event: KeyboardEvent): void {
    if (event.key === "ArrowUp" || event.key === "ArrowDown") {
      event.preventDefault();

      let [hours, minutes] = this.selectedTime.split(':').map(Number);

      if (isNaN(hours) || isNaN(minutes)) {
        hours = 11;
        minutes = 0;
      }

      let totalMinutes = hours * 60 + minutes;

      if (event.key === "ArrowUp") {
        totalMinutes += 15;
      }
      else if (event.key === "ArrowDown") {
        totalMinutes -= 15;
      }

      const minMinutes = 11 * 60;
      const maxMinutes = 23 * 60 + 59;
      totalMinutes = Math.max(minMinutes, Math.min(maxMinutes, totalMinutes));

      const newHours = String(Math.floor(totalMinutes / 60)).padStart(2, '0');
      const newMinutes = String(totalMinutes % 60).padStart(2, '0');
      this.selectedTime = `${newHours}:${newMinutes}`;
    }

    this.checkDisponibilites();
  }

  onTimeInput(event: any): void {
    let [hours, minutes] = event.target.value.split(':');

    if (isNaN(Number(hours)) || isNaN(Number(minutes))) {
      hours = '11';
      minutes = '00';
    }

    const validMinutes = ['00', '15', '30', '45'];

    if (!validMinutes.includes(minutes)) {
      minutes = '00';
    }
    this.selectedTime = `${hours}:${minutes}`;
    event.target.value = this.selectedTime;
    this.checkDisponibilites();
  }

  validateDuree() {
    if (this.selectedDuree % 15 !== 0) {
      const corrected = Math.round(this.selectedDuree / 15) * 15;
      alert(`La durée doit être un multiple de 15. Elle a été ajustée à ${corrected} minutes.`);
      this.selectedDuree = corrected;
    }
    this.checkDisponibilites();
  }

  getDebutEtFinIso(): { debutIso: string; finIso: string } {
    const [hours, minutes]: number[] = this.selectedTime.split(':').map(Number);
    const debutDateTime = new Date(this.selectedDate);
    debutDateTime.setHours(hours, minutes);

    const finDateTime = new Date(debutDateTime.getTime() + this.selectedDuree * 60000);

    const debutIso = debutDateTime.toISOString().slice(0, 19);
    const finIso = finDateTime.toISOString().slice(0, 19);

    return { debutIso, finIso };
  }

  goToReservation(tableId: number) {
    this.surface.table = tableId;
    if (tableId == 5 || tableId == 6) {
      this.surface.capaciteMax = 6;
    }
    else if (tableId > 6) {
      this.surface.capaciteMax = 4
    }

    const { debutIso, finIso } = this.getDebutEtFinIso();

    this.router.navigate(['/reservation'], {
      queryParams: {
        date: this.selectedDate,
        heure: this.selectedTime,
        duree: this.selectedDuree,
        table: this.surface.table,
        capacite: this.surface.capaciteMax
      }
    })
  }

  checkDisponibilites(): void {
    if (!this.selectedDate || !this.selectedTime || !this.selectedDuree) {
      return;
    }

    const [hours, minutes] = this.selectedTime.split(':').map(Number);
    const debutDateTime = new Date(this.selectedDate);
    debutDateTime.setHours(hours, minutes);

    const debutIso = new Date(debutDateTime.getTime() - debutDateTime.getTimezoneOffset() * 60000).toISOString();
    const finDateTime = new Date(debutDateTime.getTime() + this.selectedDuree * 60000);
    const finIso = new Date(finDateTime.getTime() - finDateTime.getTimezoneOffset() * 60000).toISOString();

    this.reservationService.getSurfacesDisponibles(debutIso, finIso).subscribe({
      next: (surfaces) => {
        this.surfacesDisponibles = surfaces;
      }
    });
  }

  surfaceEstDisponible(tableId: number): boolean {
    return this.surfacesDisponibles.some(s => s.id === tableId);
  }

}



