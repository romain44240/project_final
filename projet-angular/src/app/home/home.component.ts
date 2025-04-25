import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  private _title: string = 'demo-angular QUE JAI CHANGE';
  private _couleur: string = "black";
  public laDate: Date = new Date();
  private route2: ActivatedRoute = inject(ActivatedRoute);
  private id: number = 0;

  // Injection de dépendance de Angular
  // > Angular nous donnera l'instance de Router
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Pour les paramètres de chemin (les variables de chemin)
    this.route.params.subscribe(params => {
      console.log("path params", params);
      console.log("path param id", params['id']);
    });

    // Pour les paramètres de requête (queryparams)
    this.route.queryParams.subscribe(params => {
      console.log("query params", params);
      console.log("query param id", params['id']);

      this.id = params['id'];
    });
  }

  public get couleur(): string {
    return this._couleur;
  }

  public set couleur(value: string) {
    this._couleur = value;
  }

  public demo: any = {
    nom: "Toto"
  };

  public get title(): string {
    return this._title;
  }

  public set title(value: string) {
    this._title = value;
  }
  
  public changeTitle() {
    this._title = "Nouveau titre";
  }

  public inputTitle(evt: any) {
    this._title = evt.target.value;
  }

  public onButtonClicked(value: string) {
    alert(value);
  }

  public navToTodos() {
    this.router.navigate([ '/todo' ]);
  }
}

