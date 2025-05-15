package projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import projet.dao.IDAOCompte;
import projet.dao.IDAOProduit;
import projet.dao.IDAOReservation;
import projet.dao.IDAOSurface;
import projet.model.Achat;
import projet.model.Client;
import projet.model.Compte;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Produit;
import projet.model.Reservation;
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

	
	public List<ReservationResponse> getAll() {
		return daoReservation.findAll().stream()
				.map(ReservationResponse::convert)
				.collect(Collectors.toList());
	}

	
	public ReservationResponse getById(Integer id) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservation non trouvée avec id : " + id));
		return ReservationResponse.convert(reservation);
	}

	
	public ReservationResponse create(ReservationRequest dto) {
		Reservation reservation = ReservationRequest.convert(dto);

		//Client
		Optional<Compte> client = daoCompte.findById(dto.getIdClient());
		if (client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client avec l'id: " + dto.getIdClient() + " non trouvé");
		}
		reservation.setClient((Client) client.get());

		//Employé : vérifier s'il y a un ID d'employé
	    if (dto.getIdEmploye() != null) {
			Optional<Compte> employe = daoCompte.findById(dto.getIdEmploye());
			if (employe.isEmpty()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe avec l'id: " + dto.getIdEmploye() + " non trouvé");
			}
	        reservation.setEmploye((Employe) employe.get());
	    }

		//Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		//Jeu 
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		reservation.setAchats(new ArrayList<Achat>());

		Reservation saved = daoReservation.save(reservation);
		return ReservationResponse.convert(saved);
	}

	
	public void delete(Integer id) {
		if (!daoReservation.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservation non trouvée avec id : " + id);
		}
		daoReservation.deleteById(id);
	}

	
	public ReservationResponse update(Integer id, ReservationRequest dto) {
		Reservation reservation = daoReservation.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Réservation non trouvée avec id : " + id));

		reservation.setDebut(dto.getDebut());
		reservation.setFin(dto.getFin());
		reservation.setNbPersonne(dto.getNbPersonne());

		//Client
		Optional<Compte> client = daoCompte.findById(dto.getIdClient());
		if (client.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client avec l'id: " + dto.getIdClient() + " non trouvé");
		}
		reservation.setClient((Client) client.get());

		//Employé
		Optional<Compte> employe = daoCompte.findById(dto.getIdEmploye());
		if (employe.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employe avec l'id: " + dto.getIdEmploye() + " non trouvé");
		}
		reservation.setEmploye((Employe) employe.get());

		//Surface
		reservation.setSurface(
			daoSurface.findById(dto.getIdSurface())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Surface non trouvée avec id : " + dto.getIdSurface()))
		);

		//Jeu 
		Produit produit = daoProduit.findById(dto.getIdJeu())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Jeu non trouvé avec id : " + dto.getIdJeu()));
		if (!(produit instanceof Jeu)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Le produit avec l'id " + dto.getIdJeu() + " n'est pas un jeu.");
		}
		reservation.setJeu((Jeu) produit);

		Reservation updated = daoReservation.save(reservation);
		return ReservationResponse.convert(updated);
	}
}
