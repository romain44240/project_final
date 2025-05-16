package projet;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import projet.request.AchatRequest;
import projet.request.ClientRequest;
import projet.request.ConsommableRequest;
import projet.request.EmployeRequest;
import projet.request.JeuRequest;
import projet.request.ReservationRequest;
import projet.request.SurfaceRequest;
import projet.request.AchatRequest.ProduitType;
import projet.response.ClientResponse;
import projet.response.EmployeResponse;
import projet.response.ProduitResponse;
import projet.response.ReservationResponse;
import projet.response.SurfaceResponse;
import projet.service.AchatService;
import projet.service.CompteService;
import projet.service.ProduitService;
import projet.service.ReservationService;
import projet.service.SurfaceService;

@SpringBootTest
public class DBData {

    @Autowired
    ProduitService produitService;

    @Autowired
    CompteService compteService;

    @Autowired
    SurfaceService surfaceService;

    @Autowired
    ReservationService reservationService;

    @Autowired
    AchatService achatService;

    @Test
    void insertDataIntoDB() {
        
        // Jeux
        JeuRequest jeuRequest1 = new JeuRequest();
        jeuRequest1.setNom("Jeu de l'oie");
        jeuRequest1.setPrix(7.0);
        jeuRequest1.setStock(10);
        jeuRequest1.setNbMin(2);
        jeuRequest1.setNbMax(4);
        jeuRequest1.setDuree(1);
        jeuRequest1.setEditeur("");
        jeuRequest1.setRegle("");
        
        ProduitResponse jeuResponse1 = produitService.createJeu(jeuRequest1);
        
        JeuRequest jeuRequest2 = new JeuRequest();
        jeuRequest2.setNom("Monopoly");
        jeuRequest2.setPrix(19.99);
        jeuRequest2.setStock(15);
        jeuRequest2.setNbMin(2);
        jeuRequest2.setNbMax(6);
        jeuRequest2.setDuree(2);
        jeuRequest2.setEditeur("Hasbro");
        jeuRequest2.setRegle("Faites faillite ou devenez riche !");

        ProduitResponse jeuResponse2 = produitService.createJeu(jeuRequest2);
        
        // Consommables
        ConsommableRequest consommableRequest1 = new ConsommableRequest();
        consommableRequest1.setNom("Pepsi");
        consommableRequest1.setPrix(3.50);
        consommableRequest1.setStock(100);
        
        ProduitResponse consommableResponse1 = produitService.createConsommable(consommableRequest1);
        
        ConsommableRequest consommableRequest2 = new ConsommableRequest();
        consommableRequest2.setNom("Eau pétillante");
        consommableRequest2.setPrix(2.0);
        consommableRequest2.setStock(100);
        
        produitService.createConsommable(consommableRequest2);
        
        
        // --- CLIENT ---
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setLogin("romainV");
        clientRequest.setPassword("client1");
        clientRequest.setNom("Veneu");
        clientRequest.setPrenom("Romain");
        clientRequest.setEmail("romain.veneu56@gmail.com");
        clientRequest.setDateArrivee(LocalDate.now());
        clientRequest.setTelephone("0601020304");
        ClientResponse clientResponse = compteService.createClient(clientRequest);
        System.out.println("Client créé : " + clientResponse.getPrenom() + " " + clientResponse.getNom());
        
        ClientRequest clientRequest2 = new ClientRequest();
        clientRequest2.setLogin("LeenaP");
        clientRequest2.setPassword("client2");
        clientRequest2.setNom("test");
        clientRequest2.setPrenom("Leena");
        clientRequest2.setEmail("leena.p44@gmail.com");
        clientRequest2.setDateArrivee(LocalDate.now());
        clientRequest2.setTelephone("0690762456");
        ClientResponse clientResponse2 = compteService.createClient(clientRequest2);
        System.out.println("Client créé : " + clientResponse2.getPrenom() + " " + clientResponse2.getNom());

        // --- EMPLOYÉ ---
        EmployeRequest employeRequest = new EmployeRequest();
        employeRequest.setLogin("JulienL");
        employeRequest.setPassword("employe1");
        employeRequest.setNom("Lin");
        employeRequest.setPrenom("Julien");
        employeRequest.setEmail("jl@gmail.com");
        employeRequest.setDateArrivee(LocalDate.now());
        employeRequest.setPoste("Responsable des stocks");
        employeRequest.setSalaire(1500);
        EmployeResponse employeResponse = compteService.createEmploye(employeRequest);
        System.out.println("Employé créé : " + employeResponse.getPrenom() + " " + employeResponse.getNom());
        
        EmployeRequest employeRequest2 = new EmployeRequest();
        employeRequest2.setLogin("YannisV");
        employeRequest2.setPassword("employe2");
        employeRequest2.setNom("test2");
        employeRequest2.setPrenom("Yannis");
        employeRequest2.setEmail("yv@gmail.com");
        employeRequest2.setDateArrivee(LocalDate.now());
        employeRequest2.setPoste("Responsable des ventes");
        employeRequest2.setSalaire(1600);
        EmployeResponse employeResponse2 = compteService.createEmploye(employeRequest2);
        System.out.println("Employé créé : " + employeResponse2.getPrenom() + " " + employeResponse2.getNom());



        // --- SURFACE ---
        SurfaceRequest surfaceRequest1 = new SurfaceRequest();
        surfaceRequest1.setCapacite(4);
        surfaceRequest1.setCouleur("rouge");
        SurfaceResponse surfaceResponse1 = surfaceService.create(surfaceRequest1);
        
        SurfaceRequest surfaceRequest2 = new SurfaceRequest();
        surfaceRequest2.setCapacite(8);
        surfaceRequest2.setCouleur("noir");
        SurfaceResponse surfaceResponse2 = surfaceService.create(surfaceRequest2);
        
        SurfaceRequest surfaceRequest3 = new SurfaceRequest();
        surfaceRequest3.setCapacite(2);
        surfaceRequest3.setCouleur("marron");
        SurfaceResponse surfaceResponse3 = surfaceService.create(surfaceRequest3);
        
        System.out.println("Surfaces créées : ");
        System.out.println("Surface 1 - Capacité: " + surfaceResponse1.getCapacite() + ", Couleur: " + surfaceResponse1.getCouleur());
        System.out.println("Surface 2 - Capacité: " + surfaceResponse2.getCapacite() + ", Couleur: " + surfaceResponse2.getCouleur());
        System.out.println("Surface 3 - Capacité: " + surfaceResponse3.getCapacite() + ", Couleur: " + surfaceResponse3.getCouleur());

        
        // --- RESERVATION ---
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setDebut(LocalDateTime.now());
        reservationRequest.setFin(LocalDateTime.now().plusHours(2));
        reservationRequest.setNbPersonne(4);
        reservationRequest.setIdClient(clientResponse.getId());; 
        reservationRequest.setIdEmploye(employeResponse.getId());;  
        reservationRequest.setIdSurface(surfaceResponse1.getId());
        reservationRequest.setIdJeu(jeuResponse1.getId());
        
        ReservationResponse reservationResponse = reservationService.create(reservationRequest);
        System.out.println("Réservation créée : " + reservationResponse.getId() + " pour le client " + clientResponse.getNom());

        
        ReservationRequest reservationRequest2 = new ReservationRequest();
        reservationRequest2.setDebut(LocalDateTime.now());
        reservationRequest2.setFin(LocalDateTime.now().plusHours(2));
        reservationRequest2.setNbPersonne(8);
        reservationRequest2.setIdClient(clientResponse.getId());; 
        reservationRequest2.setIdEmploye(employeResponse.getId());;  
        reservationRequest2.setIdSurface(surfaceResponse2.getId());
        reservationRequest2.setIdJeu(jeuResponse2.getId());
        
        ReservationResponse reservationResponse2 = reservationService.create(reservationRequest2);
        System.out.println("Réservation créée : " + reservationResponse2.getId() + " pour le client " + clientResponse.getNom());
        
        
        // ACHAT
        AchatRequest achatRequest = new AchatRequest();
        achatRequest.setQuantite(5);
        achatRequest.setIdProduit(consommableResponse1.getId());
        achatRequest.setProduitType(ProduitType.CONSOMMABLE);
        achatRequest.setIdReservation(reservationResponse.getId());

        achatService.create(achatRequest);
    }
}
