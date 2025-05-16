import { Component, OnInit } from '@angular/core';
import { environment } from '../environment';
import { AdminService } from '../service/admin.service';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { combineLatest, Observable, of } from 'rxjs';
import { Consommable, Jeu } from '../models';

@Component({
  selector: 'app-carte',
  standalone: false,
  templateUrl: './carte.component.html',
  styleUrl: './carte.component.css'
})
export class CarteComponent implements OnInit{
private API_URL: string = `${environment.API_URL}`;

  constructor(private service: AdminService, private router: Router){}

  public products : Observable<{consos: Consommable[]; }> = of({consos: [] });

  public conso : Observable<Consommable[]> | undefined;
 


  ngOnInit(): void {
  
    this.conso = this.service.getAllConsos();

    this.products = combineLatest({
      consos: this.conso
    });
  }
}
