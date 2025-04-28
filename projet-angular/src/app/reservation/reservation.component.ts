import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservation',
  standalone: false,
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements OnInit {
  selectedDate: string = '';
  selectedTime: string = '11:00'; 
  currentDate: string = '';  

  constructor() { }

  ngOnInit(): void {
    const today = new Date();
    this.currentDate = today.toISOString().split('T')[0];
  }

  onDateChange(): void {
    if (this.selectedDate) {
      this.selectedTime = '11:00';
    } else {
      this.selectedTime = '';
    }
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
  }
}