import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { CompteComponent } from './compte/compte.component';
import { BibliothequeComponent } from './bibliotheque/bibliotheque.component';
import { ReservationComponent } from './reservation/reservation.component';
import { SurfaceComponent } from './surface/surface.component';
import { CarteComponent } from './carte/carte.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', component: HomeComponent},
  {path: 'connexion', component: ConnexionComponent},
  {path: 'inscription', component: InscriptionComponent},
  {path: 'compte', component: CompteComponent},
  {path: 'bibliotheque', component: BibliothequeComponent},
  {path: 'surface', component: SurfaceComponent},
  {path: 'reservation', component: ReservationComponent},
  {path: 'carte', component: CarteComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
