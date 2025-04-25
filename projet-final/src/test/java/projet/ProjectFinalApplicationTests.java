package projet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import projet.model.Achat;
import projet.model.Categorie;
import projet.model.Reservation;
import projet.model.Surface;
import projet.request.ClientRequest;
import projet.request.CommandeRequest;
import projet.request.ConsommableRequest;
import projet.request.EmployeRequest;
import projet.request.JeuRequest;
import projet.request.ProduitRequest.ProduitType;
import projet.request.ReservationRequest;
import projet.request.SurfaceRequest;
import projet.response.ClientResponse;
import projet.response.CommandeResponse;
import projet.response.ConsommableResponse;
import projet.response.EmployeResponse;
import projet.response.JeuResponse;
import projet.response.ProduitResponse;
import projet.response.ReservationResponse;
import projet.response.SurfaceResponse;
import projet.service.AchatService;
import projet.service.CommandeService;
import projet.service.CompteService;
import projet.service.ProduitService;
import projet.service.ReservationService;
import projet.service.SurfaceService;

@SpringBootTest
@Transactional
class ProjectFinalApplicationTests {
	
	// ------- SERVICES ------- //

	 @Autowired
	    private ProduitService produitService;

	    @Autowired
	    private CommandeService commandeService;

	    @Autowired
	    private AchatService achatService;

	    @Autowired
	    private CompteService compteService;

	    @Autowired
	    private ReservationService reservationService;

	    @Autowired
	    private SurfaceService surfaceService;

	// ------- FIN SERVICES ------- //

	// ------- OBJETS TEST ------- //

	// JEU
	JeuRequest skyjo() {
		JeuRequest jeu = new JeuRequest();
		jeu.setNom("Skyjo");
		jeu.setPrix(15.95);
		jeu.setStock(1);
		jeu.setNbMin(2);
		jeu.setNbMax(8);
		jeu.setDuree(30);
		jeu.setEditeur("Magilano");
		jeu.setRegle("Lien internet");
		List<Categorie> categories = new ArrayList();
		categories.add(Categorie.carte);
		jeu.setCategories(categories);
		jeu.setProduitType(ProduitType.JEU);
		
		return jeu;
	}
	
	// CONSOMMABLE
	ConsommableRequest coca() {
		ConsommableRequest consommable = new ConsommableRequest();
		consommable.setNom("Coca");
		consommable.setPrix(2.5);
		consommable.setStock(5);
		consommable.setProduitType(ProduitType.CONSOMMABLE);

		return consommable;
	}

	// CLIENT
	ClientRequest client1() {
		ClientRequest client = new ClientRequest();
		client.setLogin("client1Login");
		client.setPassword("client1Password");
		client.setNom("client1Nom");
		client.setPrenom("client1Prenom");
		client.setDateArrivee(LocalDate.parse("2025-04-25"));
		client.setEmail("client1@email.com");
		client.setTelephone("0101010101");
		
		return client;
	}

	// EMPLOYE
	EmployeRequest employe1() {
		EmployeRequest employe = new EmployeRequest();
		employe.setLogin("employe1Login");
		employe.setPassword("employe1Password");
		employe.setNom("employe1Nom");
		employe.setPrenom("employe1Prenom");
		employe.setDateArrivee(LocalDate.parse("2025-01-01"));
		employe.setPoste("serveur");
		employe.setSal(2000);

		return employe;
	}

	// SURFACE
	SurfaceRequest surface1() {
		SurfaceRequest surface = new SurfaceRequest();
		surface.setCapacite(8);

		return surface;
	}

	// ------- FIN OBJETS TEST ------- //
	
	@Test
	void creationJeu() {
		//
		JeuRequest jeuRequest = skyjo();
		
		//
		JeuResponse jeuResponse = (JeuResponse) produitService.create(jeuRequest);
		
		//
		assertEquals(jeuResponse.getNom(), jeuRequest.getNom());
		assertEquals(jeuResponse.getPrix(), jeuRequest.getPrix());
		assertEquals(jeuResponse.getStock(), jeuRequest.getStock());
		assertEquals(jeuResponse.getNbMin(), jeuRequest.getNbMin());
		assertEquals(jeuResponse.getNbMax(), jeuRequest.getNbMax());
		assertEquals(jeuResponse.getDuree(), jeuRequest.getDuree());
		assertEquals(jeuResponse.getEditeur(), jeuRequest.getEditeur());
		assertEquals(jeuResponse.getRegle(), jeuRequest.getRegle());
		assertEquals(jeuResponse.getCategories(), jeuRequest.getCategories());
	}
	
	@Test
	void creationConsommable() {
		//
		ConsommableRequest consommableRequest = coca();
		
		//
		ConsommableResponse consommableResponse = (ConsommableResponse) produitService.create(consommableRequest);
		
		//
		assertEquals(consommableRequest.getNom(), consommableResponse.getNom());
		assertEquals(consommableRequest.getPrix(), consommableResponse.getPrix());
		assertEquals(consommableRequest.getStock(), consommableResponse.getStock());
	}
	
	@Test
	void creationCommande() {
		//
		List<Achat> achats = new ArrayList();
		achats.add(new Achat(1, JeuRequest.convert(skyjo())));
		achats.add(new Achat(5, ConsommableRequest.convert(coca())));
		Reservation reservation = new Reservation(
			LocalDateTime.parse("2025-05-01T14:00:00"),
			120,
			4,
			ClientRequest.convert(client1()),
			EmployeRequest.convert(employe1()),
			SurfaceRequest.convert(surface1()),
			null);
		
		CommandeRequest commandeRequest = new CommandeRequest();
		commandeRequest.setAchats(achats);
		commandeRequest.setReservation(reservation);

		//
		CommandeResponse commandeResponse = commandeService.create(commandeRequest);
		
		//
		assertEquals(commandeRequest.getAchats(), commandeResponse.getAchats());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
    void contextLoads() {
        
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
        
        ProduitResponse consommableResponse2 = produitService.createConsommable(consommableRequest2);
        
        
        // --- CLIENT ---
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setLogin("romainV");
        clientRequest.setNom("Veneu");
        clientRequest.setPrenom("Romain");
        clientRequest.setEmail("romain.veneu56@gmail.com");
        clientRequest.setPassword("client1");
        clientRequest.setDateArrivee(LocalDate.now());

        ClientResponse clientResponse = compteService.createClient(clientRequest);
        System.out.println("Client créé : " + clientResponse.getPrenom() + " " + clientResponse.getNom());


        // --- EMPLOYÉ ---
        EmployeRequest employeRequest = new EmployeRequest();
        employeRequest.setLogin("JulienL");
        employeRequest.setNom("Lin");
        employeRequest.setPrenom("Julien");
        employeRequest.setPassword("employe1");
        employeRequest.setPoste("Responsable des stocks");
        employeRequest.setSal(1500);

        EmployeResponse employeResponse = compteService.createEmploye(employeRequest);
        System.out.println("Employé créé : " + employeResponse.getPrenom() + " " + employeResponse.getNom());


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
        reservationRequest.setDateReservation(LocalDateTime.now());
        reservationRequest.setDuree(2);
        reservationRequest.setNbPersonne(4);
        reservationRequest.setIdClient(clientResponse.getId());; 
        reservationRequest.setIdEmploye(employeResponse.getId());;  
        reservationRequest.setIdSurface(surfaceResponse1.getId());
        reservationRequest.setIdJeu(jeuResponse1.getId());
        
        ReservationResponse reservationResponse = reservationService.create(reservationRequest);
        System.out.println("Réservation créée : " + reservationResponse.getId() + " pour le client " + clientResponse.getNom());
    }
}
