import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { CompteComponent } from './compte/compte.component';
import { BibliothequeComponent } from './bibliotheque/bibliotheque.component';
import { ReservationComponent } from './reservation/reservation.component';
import { SurfaceComponent } from './surface/surface.component';
import { CarteComponent } from './carte/carte.component';
import { AdminComponent } from './admin/admin.component';
import { authInterceptor } from './auth.interceptor';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MapComponent } from './map/map.component';

@NgModule({
  declarations: [ // Déclaration dans notre module de tout ce qui est utilisé dans notre module principal
    AppComponent, 
    HomeComponent, 
    ConnexionComponent, InscriptionComponent, CompteComponent, BibliothequeComponent, ReservationComponent, SurfaceComponent, CarteComponent, AdminComponent, MapComponent
  ],
  
  imports: [ // Tous les modules dont on a besoin dans notre module à nous
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatSnackBarModule
  ],

  providers: [ // Tous les services injectables
    provideHttpClient(withInterceptors([authInterceptor])), //Remplace l'import de HttpClientModule
  ],

  bootstrap: [ // Le composant principal de notre module, si besoin
    AppComponent
  ]
})
export class AppModule { }
