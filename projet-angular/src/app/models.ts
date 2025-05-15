export interface Produit {
  id: number;
  nom: string;
  prix: number;
  stock: number;
  type?: 'consommable' | 'jeu';  
}

export interface Consommable extends Produit {
  type: 'consommable';
}

//TODO à adapter en back
export interface Categorie {
  id: number;
  nom: string;
}

export interface Jeu extends Produit {
  type: 'jeu';
  nbMin: number;
  nbMax: number;
  duree: number;
  editeur: string;
  regle: string; // ça doit être une url normalement
  categories: Categorie[];
}

export interface Surface {
  id: number;
  capacite: number;
  couleur?: string;
}

export interface Reservation {
  id: number;
  dateReservation: string; // attention format string -> localDate
  client: Client;
  surface: Surface;
  jeu?: Jeu;
  consommable?: Consommable;
}

export interface Compte {
  id: number;
  login: string;
  nom: string;
  prenom: string;
  email: string;
  dateArrivee: string; // attention format string -> localDate
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
