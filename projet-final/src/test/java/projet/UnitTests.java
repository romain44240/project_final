package projet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import projet.model.Categorie;
import projet.model.Compte;
import projet.request.ClientRequest;
import projet.request.ConsommableRequest;
import projet.request.EmployeRequest;
import projet.request.JeuRequest;
import projet.request.SurfaceRequest;
import projet.response.ClientResponse;
import projet.response.CompteInfoResponse;
import projet.response.ConsommableResponse;
import projet.response.EmployeResponse;
import projet.response.JeuResponse;
import projet.service.CompteService;
import projet.service.ProduitService;

@SpringBootTest
@Transactional
class UnitTests {
	
	// ------------------------------ SERVICES ------------------------------ //

	@Autowired
	ProduitService produitService;

	@Autowired
	CompteService compteService;

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
		
		return jeu;
	}
	
	// CONSOMMABLE
	ConsommableRequest coca() {
		ConsommableRequest consommable = new ConsommableRequest();
		consommable.setNom("Coca");
		consommable.setPrix(2.5);
		consommable.setStock(5);

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
		client.setEmail("client1@gmail.com");
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
		employe.setEmail("employe1@gmail.com");
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
	void creationClient() {
		//
		ClientRequest clientRequest = client1();

		//
		ClientResponse clientResponse = compteService.createClient(clientRequest);

		//
		assertEquals(clientResponse.getLogin(), clientRequest.getLogin());
		assertEquals(clientResponse.getNom(), clientRequest.getNom());
		assertEquals(clientResponse.getPrenom(), clientRequest.getPrenom());
		assertEquals(clientResponse.getDateArrivee(), clientRequest.getDateArrivee());
		assertEquals(clientResponse.getEmail(), clientRequest.getEmail());
		assertEquals(clientResponse.getTelephone(), clientRequest.getTelephone());
	}

	@Test
	@Sql(statements = {
		"INSERT INTO compte (type_compte, date_arrivee, email, login, nom, password, prenom, telephone) VALUES ('client', '2025-05-16T15:00:00', 't@t.com', 'test', 'nomT', 'pass', 'prenomT', '0608902487')"
	})
	void updateClient() {
		//
		Compte compte = compteService.getByLogin("test");
		ClientRequest clientRequest = client1();
		int before = compteService.getAllClients().size();
		
		//
		ClientResponse clientResponse = compteService.updateClient(compte.getId(), clientRequest);

		//
		assertEquals(before, compteService.getAllClients().size());
		assertEquals(clientResponse.getLogin(), "client1Login");
		assertEquals(clientResponse.getNom(), "client1Nom");
		assertEquals(clientResponse.getPrenom(), "client1Prenom");
		assertEquals(clientResponse.getEmail(), "client1@gmail.com");
		assertEquals(clientResponse.getDateArrivee(), LocalDate.parse("2025-04-25"));
		assertEquals(clientResponse.getTelephone(), "0101010101");
	}

	@Test
	@Sql(statements = {
		"INSERT INTO compte (type_compte, date_arrivee, email, login, nom, password, prenom, telephone) VALUES ('client', '2025-05-16T15:00:00', 't@t.com', 'test', 'nomT', 'pass', 'prenomT', '0608902487')"
	})
	void deleteClient() {
		//
		Compte byEmail = compteService.getByEmail("t@t.com");
		CompteInfoResponse cir = compteService.getCompteInfo(byEmail.getId());

		//
		boolean deleted = compteService.deleteClient(byEmail.getId());

		//
		assertNotEquals(cir, null);
		assertEquals(deleted, true);
	}

	@Test
	void creationEmploye() {
		//
		EmployeRequest employeRequest = employe1();

		//
		EmployeResponse employeResponse = compteService.createEmploye(employeRequest);

		//
		assertEquals(employeResponse.getLogin(), employeRequest.getLogin());
		assertEquals(employeResponse.getNom(), employeRequest.getNom());
		assertEquals(employeResponse.getPrenom(), employeRequest.getPrenom());
		assertEquals(employeResponse.getDateArrivee(), employeRequest.getDateArrivee());
		assertEquals(employeResponse.getEmail(), employeRequest.getEmail());
		assertEquals(employeResponse.getPoste(), employeRequest.getPoste());
		assertEquals(employeResponse.getSalaire(), employeRequest.getSalaire());
	}

	@Test
	@Sql(statements = {
		"INSERT INTO compte (type_compte, date_arrivee, email, login, nom, password, prenom, poste, salaire) VALUES ('employe', '2025-05-16T15:00:00', 't@t.com', 'test', 'nomT', 'pass', 'prenomT', 'tResponsable', '1200')"
	})
	void updateEmploye() {
		//
		Compte compte = compteService.getByLogin("test");
		EmployeRequest employeRequest = employe1();
		int before = compteService.getAllEmployes().size();
		
		//
		EmployeResponse employeResponse = compteService.updateEmploye(compte.getId(), employeRequest);

		//
		assertEquals(before, compteService.getAllEmployes().size());
		assertEquals(employeResponse.getLogin(), "employe1Login");
		assertEquals(employeResponse.getNom(), "employe1Nom");
		assertEquals(employeResponse.getPrenom(), "employe1Prenom");
		assertEquals(employeResponse.getEmail(), "employe1@gmail.com");
		assertEquals(employeResponse.getDateArrivee(), LocalDate.parse("2025-04-25"));
		assertEquals(employeResponse.getPoste(), "tResponsable");
		assertEquals(employeRequest.getSalaire(), 1200);
	}

	@Test
	@Sql(statements = {
		"INSERT INTO compte (type_compte, date_arrivee, email, login, nom, password, prenom, telephone) VALUES ('client', '2025-05-16T15:00:00', 't@t.com', 'test', 'nomT', 'pass', 'prenomT', '0608902487')"
	})
	void deleteEmploye() {
		//
		Compte byEmail = compteService.getByEmail("t@t.com");
		CompteInfoResponse cir = compteService.getCompteInfo(byEmail.getId());

		//
		boolean deleted = compteService.deleteEmploye(byEmail.getId());

		//
		assertNotEquals(cir, null);
		assertEquals(deleted, true);
	}



	@Test
	void creationJeu() {
		//
		JeuRequest jeuRequest = skyjo();
		
		//
		JeuResponse jeuResponse = produitService.createJeu(jeuRequest);
		
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
		ConsommableResponse consommableResponse = (ConsommableResponse) produitService.createConsommable(consommableRequest);
		
		//
		assertEquals(consommableRequest.getNom(), consommableResponse.getNom());
		assertEquals(consommableRequest.getPrix(), consommableResponse.getPrix());
		assertEquals(consommableRequest.getStock(), consommableResponse.getStock());
	}

	// ------------------------------ FIN TESTS UNITAIRES ------------------------------ //
}
