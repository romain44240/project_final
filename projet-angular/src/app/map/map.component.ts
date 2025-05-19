import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  standalone: false,
  templateUrl: './map.component.html',
  styleUrl: './map.component.css'
})
export class MapComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    // Créer la carte et la centrer sur Lille
    const map = L.map('map').setView([50.6292, 3.0573], 13); // Coordonnées de Lille

    // Ajouter le fond de carte (carte OpenStreetMap)
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    // Ajouter un marqueur à l'adresse
    const customIcon = L.icon({
      iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png', // URL de l'icône
      iconSize: [25, 41], // Taille de l'icône
      iconAnchor: [12, 41], // Point d'ancrage de l'icône
      popupAnchor: [1, -34] // Position du popup
    });

    // Ajouter un marqueur avec l'icône personnalisée
    const marker = L.marker([50.6292, 3.0573], { icon: customIcon }).addTo(map);
    marker.bindPopup("<b>La Tour de Jeu</b><br>1 rue de Lille, 59000 Lille").openPopup();
  }
  }
{

}
