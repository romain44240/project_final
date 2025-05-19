import { Component, OnInit } from '@angular/core';
import { combineLatest, Observable, of } from 'rxjs';
import { Jeu } from '../models';
import { AdminService } from '../service/admin.service';

@Component({
  selector: 'app-bibliotheque',
  standalone: false,
  templateUrl: './bibliotheque.component.html',
  styleUrl: './bibliotheque.component.css'
})
export class BibliothequeComponent implements OnInit {

  public jeux: Observable<Jeu[]> | undefined;

  public products: Observable<{ jeux: Jeu[]; }> = of({ jeux: [] });

  selectedJeu: any = null;

  constructor(private service: AdminService) { }

  // INIT
  ngOnInit(): void {
    this.jeux = this.service.getAllJeux();


    this.products = combineLatest({
      jeux: this.jeux,
    });

    this.products.subscribe(data => {
      this.selectedJeu = data.jeux[0];
    })
  }



  onSlideChange(event: any) {
    const activeIndex = event.to;
    this.products.subscribe(data => {
      this.selectedJeu = data.jeux[activeIndex];
    });
  }

}

