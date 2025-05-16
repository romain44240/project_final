import { Component } from '@angular/core';
import { AdminService } from '../service/admin.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Client, Consommable, Employe, Jeu, Produit, Reservation } from '../models';
import { combineLatest, Observable, of } from 'rxjs';

@Component({
  selector: 'app-admin',
  standalone: false,
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  public selectedView = 'users'; 
  public showClientForm = false;
  public showEmployeForm = false;
  public showJeuForm = false;
  public showConsoForm = false;

  public clients : Observable<Client[]> | undefined;
  public employes : Observable<Employe[]> | undefined;
  public jeux : Observable<Jeu[]> | undefined;
  public conso : Observable<Consommable[]> | undefined;
  public reservations : Observable<Reservation[]> | undefined;

  public comptes: Observable<{ clients: Client[]; employes: Employe[]; }> = of({ clients: [], employes: [] });
  public products : Observable<{ jeux: Jeu[]; consos: Consommable[]; }> = of({ jeux: [], consos: [] });


  // formulaire
  public clientForm!: FormGroup;
  public employeForm!: FormGroup;
  public jeuForm!: FormGroup;
  public consoForm!: FormGroup;

  constructor(private service: AdminService, private formBuilder: FormBuilder){}

  // INIT
  ngOnInit(): void {
    this.reservations = this.service.getAllReservations();
    this.clients = this.service.getAllClients();
    this.employes = this.service.getAllEmployes();

    this.jeux = this.service.getAllJeux();
    this.conso = this.service.getAllConsos();

    this.products = combineLatest({
      jeux: this.jeux,
      consos: this.conso
    });

    this.comptes = combineLatest({
      clients: this.clients,
      employes: this.employes
    });

    // init des bases pour les formulaires
    this.clientForm = this.formBuilder.group({
      id: null,
      login: '',
      password: '',
      nom: '',
      prenom: '',
      email: '',
      telephone: '',
      dateArrivee: new Date().toString()
    });

    this.employeForm = this.formBuilder.group({
      id:null,
      login: '',
      password: '',
      nom: '',
      prenom: '',
      dateArrivee: new Date().toString(),
      email: '',
      salaire : 0,
      poste: ''
    });

    this.jeuForm = this.formBuilder.group({
      id:null,
      nom: '',
      prix: 0,
      nbMin : 0,
      nbMax : 0,
      duree : 0,
      regle : '',
      categories : [],
      stock : 1,
      editeur: '',
      type: 'jeu' 
    });

    this.consoForm = this.formBuilder.group({
      id:null,
      nom: '',
      prix: 0 ,
      stock: 0,
      type:'consommable'
    });
  }

  // Edition et suppression
  editClient(client: Client): void {
    this.clientForm.patchValue(client);
  }

  editEmploye(employe: Employe): void {
    this.employeForm.patchValue(employe);
  }

  editJeu(jeu: Jeu): void {
    this.jeuForm.patchValue(jeu);
  }

  editConso(conso : Consommable){
    this.consoForm.patchValue(conso);
  }

  deleteClient(id: number): void {
    if(id != 0){
      if(confirm("Suppression d'un client ?")){
        this.service.deleteClient(id).subscribe({
          next: () => {
            this.clients = this.service.getAllClients(); 
            this.loadClients();
          }
        });
      }
    }else{
      alert("id 0 n'est pas possible");
    }
  }

  deleteProduct(productId: number): void {
    if(productId != 0){
      if(confirm("Suppresion d'un produit ?")){
        this.service.deleteProduct(productId).subscribe({
          next: () => {
            this.loadProduits(); 
          }
        });
      }
    }else{
      alert("id 0 n'est pas possible");
    }
    
  }
  
  deleteReservation(reservationId: number): void {
    if(reservationId != 0){
      if(confirm("Suppresion d'une reservation ?")){
        this.service.deleteReservation(reservationId).subscribe({
          next: () => {
            this.loadReservations(); 
          }
        });
      }
    }else{
      alert("id 0 n'est pas possible");
    }
  }

  // recharger les élements après certaines actions : 
  refreshComptes(): void {
    this.comptes = combineLatest({
      clients: this.clients!,
      employes: this.employes!
    });
  }

  loadClients(): void {
    this.clients = this.service.getAllClients();
    this.refreshComptes();
  }

  loadEmployes(): void {
    this.employes = this.service.getAllEmployes();
    this.refreshComptes();
  }

  loadProduits(): void {
    this.jeux = this.service.getAllJeux();
    this.conso = this.service.getAllConsos();
    this.products = combineLatest({
      jeux: this.jeux,
      consos: this.conso
    });
  }

  loadReservations(): void {
    this.reservations = this.service.getAllReservations();
  }

  createOrUpdateClient(): Observable<Client> {
    const client = this.clientForm.value;
    return client.id ? this.service.updateClient(client) : this.service.createClient(client);
  }

  createOrUpdateEmploye(): Observable<Employe> {
    const employe = this.employeForm.value;
    if (employe.id) {
      return this.service.updateEmploye(employe);
    } else {
      return this.service.createEmploye(employe);
    }
  }

  createOrUpdateJeu(): Observable<Produit> {
    const jeu = this.jeuForm.value;
    if (jeu.id) {
      return this.service.updateProduct(jeu);
    } else {
      return this.service.createProduct(jeu);
    }
  }

  createOrUpdateConso(): Observable<Produit> {
    const conso = this.consoForm.value;
    if (conso.id) {
      return this.service.updateProduct(conso);
    } else {
      return this.service.createProduct(conso);
    }
  }

  submitClient(): void {
    this.createOrUpdateClient().subscribe({
      next: () => {
        this.clientForm.reset({
          dateArrivee: new Date().toString()
        });
        this.loadClients();
      },
      error: err => console.error("Erreur client :", err)
    });
  }


  submitEmploye(): void {
    this.createOrUpdateEmploye().subscribe({
      next: () => {
        this.employeForm.reset({
          dateArrivee: new Date().toString()
        });
        this.loadEmployes();
      }
    });
  }

  submitJeu(): void {
    this.createOrUpdateJeu().subscribe({
      next: () => {
        this.jeuForm.reset();
        this.loadProduits();
      }
    });
  }

  submitConso(): void {
    this.createOrUpdateConso().subscribe({
      next: () => {
        this.consoForm.reset();
        this.loadProduits();
      }
    });
  }

}