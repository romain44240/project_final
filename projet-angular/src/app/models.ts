export interface Produit {
  id?: number;
  nom: string;
  prix: number;
  stock: number;
  type?: 'consommable' | 'jeu';
}
export interface Consommable extends Produit {
  type: 'consommable';
}
export interface Categorie {
  id?: number;
  nom: string;
}
export interface Jeu extends Produit {
  type: 'jeu';
  nbMin: number;
  nbMax: number;
  duree: number;
  editeur: string;
  urlRegle: string;
  urlImage: string;
  categories: Categorie[];
}
export interface Surface {
  id?: number;
  capacite: number;
  couleur?: string;
}
export interface Reservation {
  id?: number;
  debut: string;
  fin: string;
  nbPersonne: number;
  client: Client;
  employe?: Employe;
  surface: Surface;
  jeu?: Jeu;
}
export interface Compte {
  id?: number;
  login: string;
  password: string;
  nom: string;
  prenom: string;
  email: string;
  dateArrivee: string; 
}
export interface Client extends Compte {
  telephone?: string;
  reservations?: Reservation[];
}
export interface Employe extends Compte {
  poste: string;
  salaire: number;
  reservation?: Reservation;
}
export interface CompteInfoResponse {
  reservations: Reservation[];
}
export interface ReservationRequest {
  debut: string;
  fin: string;
  nbPersonne: number;
  idClient: number;
  idEmploye?: number;
  idSurface: number;
  idJeu?: number;
}

