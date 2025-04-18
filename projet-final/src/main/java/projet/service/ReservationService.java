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

	
	public List<ReservationResponse> findAll() {
		return daoReservation.findAll().stream()
				.map(ReservationResponse::convert)
				.collect(Collectors.toList());
	}

	
	public ReservationResponse findById(Integer id) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new RuntimeException("Réservation non trouvée avec id : " + id));
		return ReservationResponse.convert(reservation);
	}

	
	public ReservationResponse create(ReservationRequest dto) {
		Reservation reservation = ReservationRequest.toEntity(dto);

		//Client
		reservation.setClient(getClientById(dto.getIdClient()));

		//Employé
		reservation.setEmploye(getEmployeById(dto.getIdEmploye()));

		//Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		//Jeu 
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new RuntimeException("Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new RuntimeException("Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		//Commande 
		if (dto.getIdCommande() != null) {
			reservation.setCommande(
				daoCommande.findById(dto.getIdCommande())
					.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()))
			);
		}

		Reservation saved = daoReservation.save(reservation);
		return ReservationResponse.convert(saved);
	}

	
	public void delete(Integer id) {
		if (!daoReservation.existsById(id)) {
			throw new RuntimeException("Réservation non trouvée avec id : " + id);
		}
		daoReservation.deleteById(id);
	}

	
	public ReservationResponse update(Integer id, ReservationRequest dto) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new RuntimeException("Réservation non trouvée avec id : " + id));

		reservation.setDateReservation(dto.getDateReservation());
		reservation.setDuree(dto.getDuree());
		reservation.setNbPersonne(dto.getNbPersonne());

		//Client
		reservation.setClient(getClientById(dto.getIdClient()));

		//Employé
		reservation.setEmploye(getEmployeById(dto.getIdEmploye()));

		//Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new RuntimeException("Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		//Jeu 
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new RuntimeException("Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new RuntimeException("Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		//Commande
		if (dto.getIdCommande() != null) {
			reservation.setCommande(
				daoCommande.findById(dto.getIdCommande())
					.orElseThrow(() -> new RuntimeException("Commande non trouvée avec id : " + dto.getIdCommande()))
			);
		} else {
			reservation.setCommande(null);
		}

		Reservation updated = daoReservation.save(reservation);
		return ReservationResponse.convert(updated);
	}


	

	private Client getClientById(Integer id) {
		Compte compte = daoCompte.findById(id)
				.orElseThrow(() -> new RuntimeException("Client non trouvé avec id : " + id));
		if (!(compte instanceof Client)) {
			throw new RuntimeException("Le compte avec l'id " + id + " n'est pas un client.");
		}
		return (Client) compte;
	}

	private Employe getEmployeById(Integer id) {
		Compte compte = daoCompte.findById(id)
				.orElseThrow(() -> new RuntimeException("Employé non trouvé avec id : " + id));
		if (!(compte instanceof Employe)) {
			throw new RuntimeException("Le compte avec l'id " + id + " n'est pas un employé.");
		}
		return (Employe) compte;
	}
}
