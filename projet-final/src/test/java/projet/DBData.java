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
        jeuRequest1.setNom("Catan");
        jeuRequest1.setPrix(32.54);
        jeuRequest1.setStock(10);
        jeuRequest1.setNbMin(3);
        jeuRequest1.setNbMax(4);
        jeuRequest1.setDuree(1);
        jeuRequest1.setEditeur("Kosmos");
        jeuRequest1.setUrlRegle("http://ludikerborg.free.fr/Jeux/catan/Catan.pdf");
        jeuRequest1.setUrlImage("https://i92.servimg.com/u/f92/19/95/93/11/catan10.jpg");
        
        ProduitResponse jeuResponse1 = produitService.createJeu(jeuRequest1);
        
        JeuRequest jeuRequest2 = new JeuRequest();
        jeuRequest2.setNom("Monopoly");
        jeuRequest2.setPrix(29.99);
        jeuRequest2.setStock(15);
        jeuRequest2.setNbMin(2);
        jeuRequest2.setNbMax(6);
        jeuRequest2.setDuree(2);
        jeuRequest2.setEditeur("Hasbro");
        jeuRequest2.setUrlRegle("https://cdn.1j1ju.com/medias/6a/06/1d-monopoly-regle.pdf");
        jeuRequest2.setUrlImage("https://i92.servimg.com/u/f92/19/95/93/11/monopo10.png");

        ProduitResponse jeuResponse2 = produitService.createJeu(jeuRequest2);

        JeuRequest jeuRequest3 = new JeuRequest();
        jeuRequest3.setNom("Les aventuriers du rail");
        jeuRequest3.setPrix(29.99);
        jeuRequest3.setStock(15);
        jeuRequest3.setNbMin(2);
        jeuRequest3.setNbMax(5);
        jeuRequest3.setDuree(1);
        jeuRequest3.setEditeur("Asmodee");
        jeuRequest3.setUrlRegle("https://www.jeuxavolonte.asso.fr/regles/les_aventuriers_du_rail.pdf");
        jeuRequest3.setUrlImage("https://i92.servimg.com/u/f92/19/95/93/11/aventu10.jpg");

        produitService.createJeu(jeuRequest3);
        
        // Consommables
        ConsommableRequest consommableRequest1 = new ConsommableRequest();
        consommableRequest1.setNom("Café Expresso");
        consommableRequest1.setPrix(2.00);
        consommableRequest1.setStock(100);
        
        ProduitResponse consommableResponse1 = produitService.createConsommable(consommableRequest1);
        
        ConsommableRequest consommableRequest2 = new ConsommableRequest();
        consommableRequest2.setNom("Café Long");
        consommableRequest2.setPrix(2.50);
        consommableRequest2.setStock(100);
        
        produitService.createConsommable(consommableRequest2);

        ConsommableRequest consommableRequest3 = new ConsommableRequest();
        consommableRequest3.setNom("Cappuccino");
        consommableRequest3.setPrix(3.00);
        consommableRequest3.setStock(100);
        
        produitService.createConsommable(consommableRequest3);

        ConsommableRequest consommableRequest4 = new ConsommableRequest();
        consommableRequest4.setNom("Latte Macchiato");
        consommableRequest4.setPrix(3.50);
        consommableRequest4.setStock(100);
        
        produitService.createConsommable(consommableRequest4);

        ConsommableRequest consommableRequest5 = new ConsommableRequest();
        consommableRequest5.setNom("Thé Vert");
        consommableRequest5.setPrix(2.80);
        consommableRequest5.setStock(100);
        
        produitService.createConsommable(consommableRequest5);

        ConsommableRequest consommableRequest6 = new ConsommableRequest();
        consommableRequest6.setNom("Thé Noir");
        consommableRequest6.setPrix(2.80);
        consommableRequest6.setStock(100);
        
        produitService.createConsommable(consommableRequest6);

        ConsommableRequest consommableRequest7 = new ConsommableRequest();
        consommableRequest7.setNom("Chocolat Chaud");
        consommableRequest7.setPrix(3.00);
        consommableRequest7.setStock(100);
        
        produitService.createConsommable(consommableRequest7);

        ConsommableRequest consommableRequest8 = new ConsommableRequest();
        consommableRequest8.setNom("Jus d'Orange Pressé");
        consommableRequest8.setPrix(3.20);
        consommableRequest8.setStock(100);
        
        produitService.createConsommable(consommableRequest8);

        ConsommableRequest consommableRequest9 = new ConsommableRequest();
        consommableRequest9.setNom("Smoothie Fraise Banane");
        consommableRequest9.setPrix(4.00);
        consommableRequest9.setStock(100);
        
        produitService.createConsommable(consommableRequest9);

        ConsommableRequest consommableRequest10 = new ConsommableRequest();
        consommableRequest10.setNom("Limonade Maison");
        consommableRequest10.setPrix(3.50);
        consommableRequest10.setStock(100);
        
        produitService.createConsommable(consommableRequest10);

        ConsommableRequest consommableRequest11 = new ConsommableRequest();
        consommableRequest11.setNom("Coca Cola");
        consommableRequest11.setPrix(2.50);
        consommableRequest11.setStock(100);
        
        produitService.createConsommable(consommableRequest11);

        ConsommableRequest consommableRequest12 = new ConsommableRequest();
        consommableRequest12.setNom("Eau Minérale (petite bouteille 33cl)");
        consommableRequest12.setPrix(1.50);
        consommableRequest12.setStock(100);
        
        produitService.createConsommable(consommableRequest12);

        ConsommableRequest consommableRequest13 = new ConsommableRequest();
        consommableRequest13.setNom("Eau Pétillante (petite bouteille 33cl)");
        consommableRequest13.setPrix(2.00);
        consommableRequest13.setStock(100);
        
        produitService.createConsommable(consommableRequest13);

        ConsommableRequest consommableRequest14 = new ConsommableRequest();
        consommableRequest14.setNom("Bière Artisanale");
        consommableRequest14.setPrix(4.50);
        consommableRequest14.setStock(100);
        
        produitService.createConsommable(consommableRequest14);

        ConsommableRequest consommableRequest15 = new ConsommableRequest();
        consommableRequest15.setNom("Vin Blanc (verre 12cl)");
        consommableRequest15.setPrix(4.00);
        consommableRequest15.setStock(100);
        
        produitService.createConsommable(consommableRequest15);
        
        
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
        clientRequest2.setLogin("leenaP");
        clientRequest2.setPassword("client2");
        clientRequest2.setNom("Permallee");
        clientRequest2.setPrenom("Leena");
        clientRequest2.setEmail("leena@gmail.com");
        clientRequest2.setDateArrivee(LocalDate.of(2025,5,7));
        clientRequest2.setTelephone("0606060606");
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
        employeRequest.setSalaire(2000);
        EmployeResponse employeResponse = compteService.createEmploye(employeRequest);
        System.out.println("Employé créé : " + employeResponse.getPrenom() + " " + employeResponse.getNom());

        EmployeRequest employeRequest2 = new EmployeRequest();
        employeRequest2.setLogin("YannisV");
        employeRequest2.setPassword("employe2");
        employeRequest2.setNom("Voynnet");
        employeRequest2.setPrenom("Yannis");
        employeRequest2.setEmail("yv@gmail.com");
        employeRequest2.setDateArrivee(LocalDate.of(2025,2,17));
        employeRequest2.setPoste("GameMaster");
        employeRequest2.setSalaire(1500);
        EmployeResponse employeResponse2 = compteService.createEmploye(employeRequest2);
        System.out.println("Employé créé : " + employeResponse2.getPrenom() + " " + employeResponse2.getNom());

        // --- SURFACE ---
        SurfaceRequest surfaceRequest1 = new SurfaceRequest();
        surfaceRequest1.setCapacite(8);
        surfaceRequest1.setCouleur("jaune");
        SurfaceResponse surfaceResponse1 = surfaceService.create(surfaceRequest1);
        
        SurfaceRequest surfaceRequest2 = new SurfaceRequest();
        surfaceRequest2.setCapacite(8);
        surfaceRequest2.setCouleur("jaune");
        SurfaceResponse surfaceResponse2 = surfaceService.create(surfaceRequest2);
        
        SurfaceRequest surfaceRequest3 = new SurfaceRequest();
        surfaceRequest3.setCapacite(8);
        surfaceRequest3.setCouleur("jaune");
        SurfaceResponse surfaceResponse3 = surfaceService.create(surfaceRequest3);

        SurfaceRequest surfaceRequest4 = new SurfaceRequest();
        surfaceRequest4.setCapacite(8);
        surfaceRequest4.setCouleur("jaune");
        SurfaceResponse surfaceResponse4 = surfaceService.create(surfaceRequest4);

        SurfaceRequest surfaceRequest5 = new SurfaceRequest();
        surfaceRequest5.setCapacite(6);
        surfaceRequest5.setCouleur("orange");
        SurfaceResponse surfaceResponse5 = surfaceService.create(surfaceRequest5);

        SurfaceRequest surfaceRequest6 = new SurfaceRequest();
        surfaceRequest6.setCapacite(6);
        surfaceRequest6.setCouleur("orange");
        SurfaceResponse surfaceResponse6 = surfaceService.create(surfaceRequest6);

        SurfaceRequest surfaceRequest7 = new SurfaceRequest();
        surfaceRequest7.setCapacite(4);
        surfaceRequest7.setCouleur("orange");
        SurfaceResponse surfaceResponse7 = surfaceService.create(surfaceRequest7);

        SurfaceRequest surfaceRequest8 = new SurfaceRequest();
        surfaceRequest8.setCapacite(4);
        surfaceRequest8.setCouleur("orange");
        SurfaceResponse surfaceResponse8 = surfaceService.create(surfaceRequest8);

        SurfaceRequest surfaceRequest9 = new SurfaceRequest();
        surfaceRequest9.setCapacite(4);
        surfaceRequest9.setCouleur("rouge");
        SurfaceResponse surfaceResponse9 = surfaceService.create(surfaceRequest9);

        SurfaceRequest surfaceRequest10 = new SurfaceRequest();
        surfaceRequest10.setCapacite(4);
        surfaceRequest10.setCouleur("rouge");
        SurfaceResponse surfaceResponse10 = surfaceService.create(surfaceRequest10);

        SurfaceRequest surfaceRequest11 = new SurfaceRequest();
        surfaceRequest11.setCapacite(4);
        surfaceRequest11.setCouleur("rouge");
        SurfaceResponse surfaceResponse11 = surfaceService.create(surfaceRequest11);

        SurfaceRequest surfaceRequest12 = new SurfaceRequest();
        surfaceRequest12.setCapacite(4);
        surfaceRequest12.setCouleur("rouge");
        SurfaceResponse surfaceResponse12 = surfaceService.create(surfaceRequest12);
        
        System.out.println("Surfaces créées : ");
        System.out.println("Surface 1 - Capacité: " + surfaceResponse1.getCapacite() + ", Couleur: " + surfaceResponse1.getCouleur());
        System.out.println("Surface 2 - Capacité: " + surfaceResponse2.getCapacite() + ", Couleur: " + surfaceResponse2.getCouleur());
        System.out.println("Surface 3 - Capacité: " + surfaceResponse3.getCapacite() + ", Couleur: " + surfaceResponse3.getCouleur());
        System.out.println("Surface 4 - Capacité: " + surfaceResponse4.getCapacite() + ", Couleur: " + surfaceResponse4.getCouleur());
        System.out.println("Surface 5 - Capacité: " + surfaceResponse5.getCapacite() + ", Couleur: " + surfaceResponse5.getCouleur());
        System.out.println("Surface 6 - Capacité: " + surfaceResponse6.getCapacite() + ", Couleur: " + surfaceResponse6.getCouleur());
        System.out.println("Surface 7 - Capacité: " + surfaceResponse7.getCapacite() + ", Couleur: " + surfaceResponse7.getCouleur());
        System.out.println("Surface 8 - Capacité: " + surfaceResponse8.getCapacite() + ", Couleur: " + surfaceResponse8.getCouleur());
        System.out.println("Surface 9 - Capacité: " + surfaceResponse9.getCapacite() + ", Couleur: " + surfaceResponse9.getCouleur());
        System.out.println("Surface 10 - Capacité: " + surfaceResponse10.getCapacite() + ", Couleur: " + surfaceResponse10.getCouleur());
        System.out.println("Surface 11 - Capacité: " + surfaceResponse11.getCapacite() + ", Couleur: " + surfaceResponse11.getCouleur());
        System.out.println("Surface 12 - Capacité: " + surfaceResponse12.getCapacite() + ", Couleur: " + surfaceResponse12.getCouleur());
        
        // --- RESERVATION ---
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setDebut(LocalDateTime.now());
        reservationRequest.setFin(LocalDateTime.now().plusHours(2));
        reservationRequest.setNbPersonne(4);
        reservationRequest.setIdClient(clientResponse.getId());
        reservationRequest.setIdEmploye(employeResponse.getId());
        reservationRequest.setIdSurface(surfaceResponse1.getId());
        reservationRequest.setIdJeu(jeuResponse1.getId());
        
        ReservationResponse reservationResponse1 = reservationService.create(reservationRequest);
        System.out.println("Réservation créée : " + reservationResponse1.getId() + " pour le client " + clientResponse.getNom());

        
        ReservationRequest reservationRequest2 = new ReservationRequest();
        reservationRequest2.setDebut(LocalDateTime.of(2025,5,21,16,45));
        reservationRequest2.setFin(LocalDateTime.of(2025,5,21,17,45));
        reservationRequest2.setNbPersonne(8);
        reservationRequest2.setIdClient(clientResponse.getId());
        reservationRequest2.setIdEmploye(employeResponse.getId());
        reservationRequest2.setIdSurface(surfaceResponse2.getId());
        reservationRequest2.setIdJeu(jeuResponse2.getId());
        
        ReservationResponse reservationResponse2 = reservationService.create(reservationRequest2);
        System.out.println("Réservation créée : " + reservationResponse2.getId() + " pour le client " + clientResponse.getNom());
        
        
        // ACHAT
        AchatRequest achatRequest = new AchatRequest();
        achatRequest.setQuantite(5);
        achatRequest.setIdProduit(consommableResponse1.getId());
        achatRequest.setProduitType(ProduitType.CONSOMMABLE);
        achatRequest.setIdReservation(reservationResponse1.getId());

        achatService.create(achatRequest);
    }
}
