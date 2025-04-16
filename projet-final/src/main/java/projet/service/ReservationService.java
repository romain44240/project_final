package projet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.dao.IDAOReservation;
import projet.dao.IDAOCompte;
import projet.dao.IDAOSurface;
import projet.dao.IDAOProduit;
import projet.dao.IDAOCommande;

import projet.model.Reservation;
import projet.model.Client;
import projet.model.Employe;
import projet.model.Surface;
import projet.model.Jeu;
import projet.model.Commande;
import projet.model.Produit;
import projet.model.Compte;
import projet.request.ReservationRequest;
import projet.request.ReservationRequestDTO;
import projet.response.ReservationResponse;

@Service
public class ReservationService {

	@Autowired
	private IDAOReservation daoReservation;

	@Autowired
	private IDAOCompte daoCompte;

	@Autowired
	private IDAOSurface daoSurface;

	@Autowired
	private IDAOProduit daoProduit;

	@Autowired
	private IDAOCommande daoCommande;

	// Lister toutes les réservations
	public List<ReservationResponse> findAll() {
		return daoReservation.findAll().stream()
				.map(ReservationResponse::fromEntity)
				.collect(Collectors.toList());
	}

	// Rechercher une réservation par ID
	public ReservationResponse findById(Integer id) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new RuntimeException("Réservation non trouvée avec id : " + id));
		return ReservationResponse.fromEntity(reservation);
	}

	// Créer une nouvelle réservation
	public ReservationResponse create(ReservationRequest dto) {
		Reservation reservation = ReservationRequest.toEntity(dto);

		// Client (hérite de Compte)
		Compte compteClient = daoCompte.findById(dto.getIdClient());
				.orElseThrow(() -> new RuntimeException("Client non trouvé avec id : " + dto.getIdClient()));
		if (!(compteClient instanceof Client)) {
			throw new RuntimeException("Le compte avec l'id " + dto.getIdClient() + " n'est pas un client.");
		}
		reservation.setClient((Client) compteClient);

		// Employé (hérite de Compte)
		Compte compteEmploye = daoCompte.findById(dto.getIdEmploye())
				.orElseThrow(() -> new RuntimeException("Employé non trouvé avec id : " + dto.getIdEmploye()));
		if (!(compteEmploye instanceof Employe)) {
			throw new RuntimeException("Le compte avec l'id " + dto.getIdEmploye() + " n'est pas un employé.");
		}
		reservation.setEmploye((Employe) compteEmploye);

		// Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		// Jeu (hérite de Produit)
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new RuntimeException("Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new RuntimeException("Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		// Commande (optionnelle)
		if (dto.getIdCommande() != null) {
			reservation.setCommande(
				daoCommande.findById(dto.getIdCommande())
					.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()))
			);
		}

		Reservation saved = daoReservation.save(reservation);
		return ReservationResponse.fromEntity(saved);
	}

	// Supprimer une réservation
	public void delete(Integer id) {
		if (!daoReservation.existsById(id)) {
			throw new RuntimeException("Réservation non trouvée avec id : " + id);
		}
		daoReservation.deleteById(id);
	}

	// Mettre à jour une réservation
	public ReservationResponse update(Integer id, ReservationRequestDTO dto) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new RuntimeException("Réservation non trouvée avec id : " + id));

		reservation.setDateReservation(dto.getDateReservation());
		reservation.setDuree(dto.getDuree());
		reservation.setNbPersonne(dto.getNbPersonne());

		// Client (hérite de Compte)
		Compte compteClient = daoCompte.findById(dto.getIdClient())
				.orElseThrow(() -> new RuntimeException("Client non trouvé avec id : " + dto.getIdClient()));
		if (!(compteClient instanceof Client)) {
			throw new RuntimeException("Le compte avec l'id " + dto.getIdClient() + " n'est pas un client.");
		}
		reservation.setClient((Client) compteClient);

		// Employé (hérite de Compte)
		Compte compteEmploye = daoCompte.findById(dto.getIdEmploye())
				.orElseThrow(() -> new RuntimeException("Employé non trouvé avec id : " + dto.getIdEmploye()));
		if (!(compteEmploye instanceof Employe)) {
			throw new RuntimeException("Le compte avec l'id " + dto.getIdEmploye() + " n'est pas un employé.");
		}
		reservation.setEmploye((Employe) compteEmploye);

		// Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		// Jeu (hérite de Produit)
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new RuntimeException("Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new RuntimeException("Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		// Commande (optionnelle)
		if (dto.getIdCommande() != null) {
			reservation.setCommande(
				daoCommande.findById(dto.getIdCommande())
					.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()))
			);
		} else {
			reservation.setCommande(null);
		}

		Reservation updated = daoReservation.save(reservation);
		return ReservationResponse.fromEntity(updated);
	}
}
