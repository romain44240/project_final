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
import { authGuard } from './auth.guard';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', component: HomeComponent},
  {path: 'connexion', component: ConnexionComponent},
  {path: 'inscription', component: InscriptionComponent},
  {path: 'compte', component: CompteComponent, canActivate: [authGuard], data: {roles: ['ROLE_EMPLOYE', 'ROLE_CLIENT']}},
  {path: 'bibliotheque', component: BibliothequeComponent},
  {path: 'surface', component: SurfaceComponent},
  {path: 'reservation', component: ReservationComponent, canActivate: [authGuard],data: {roles: ['ROLE_EMPLOYE', 'ROLE_CLIENT']}},
  {path: 'carte', component: CarteComponent},
  {path: 'admin', component: AdminComponent, canActivate: [authGuard], data: {roles: ['ROLE_EMPLOYE']}}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
