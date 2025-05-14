import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthRequest } from '../auth-request';
import { take } from 'rxjs';

@Component({
  selector: 'app-reservation',
  standalone: false,
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements OnInit{
  public authForm!: FormGroup;
  submitted = false;

  public capaciteMax = 8;

  constructor(private service: AuthService, private router: Router, private formBuilder: FormBuilder, private route: ActivatedRoute) { }

  ngOnInit(): void {

    this.authForm = this.formBuilder.group({
      date: [''],
      heure: [''],
      duree: [''],
      table: [''],
      capacite: ['']
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
      })
    })
  }
}
