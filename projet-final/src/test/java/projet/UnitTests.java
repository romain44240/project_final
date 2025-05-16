package projet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import projet.model.Categorie;
import projet.request.ClientRequest;
import projet.request.ConsommableRequest;
import projet.request.EmployeRequest;
import projet.request.JeuRequest;
import projet.request.ProduitRequest.ProduitType;
import projet.request.SurfaceRequest;
import projet.response.ConsommableResponse;
import projet.response.JeuResponse;
import projet.service.ProduitService;

@SpringBootTest
@Transactional
class UnitTests {
	
	// ------------------------------ SERVICES ------------------------------ //

	@Autowired
	ProduitService produitService;

	// ------------------------------ FIN SERVICES ------------------------------ //

	// ------------------------------ OBJETS TEST ------------------------------ //

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
		jeu.setUrlRegle("Lien internet");
		jeu.setUrlImage("Lien internet");
		List<Categorie> categories = new ArrayList<Categorie>();
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
		employe.setSalaire(2000);

		return employe;
	}

	// SURFACE
	SurfaceRequest surface1() {
		SurfaceRequest surface = new SurfaceRequest();
		surface.setCapacite(8);

		return surface;
	}

	// ------------------------------ FIN OBJETS TEST ------------------------------ //
	
	// ------------------------------ TESTS UNITAIRES ------------------------------ //

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
		assertEquals(jeuResponse.getUrlRegle(), jeuRequest.getUrlRegle());
		assertEquals(jeuResponse.getUrlImage(), jeuRequest.getUrlImage());
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

	// ------------------------------ FIN TESTS UNITAIRES ------------------------------ //
}
