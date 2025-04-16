package projet.request;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
<<<<<<< Updated upstream
=======

import projet.model.Achat;
import projet.model.Client;
import projet.model.Commande;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Produit;
>>>>>>> Stashed changes
import projet.model.Reservation;

public class ReservationRequest {

	private Integer id;
	private LocalDateTime dateReservation;
	private int duree;
	private int nbPersonne;
	private Integer idClient;
	private Integer idEmploye;
	private Integer idSurface;
	private Integer idJeu;
	private Integer idCommande;

	public ReservationRequest() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDateTime dateReservation) {
		this.dateReservation = dateReservation;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(Integer idEmploye) {
		this.idEmploye = idEmploye;
	}

	public Integer getIdSurface() {
		return idSurface;
	}

	public void setIdSurface(Integer idSurface) {
		this.idSurface = idSurface;
	}

	public Integer getIdJeu() {
		return idJeu;
	}

	public void setIdJeu(Integer idJeu) {
		this.idJeu = idJeu;
	}

	public Integer getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Integer idCommande) {
		this.idCommande = idCommande;
	}

	// Méthode pour transformer le DTO en entité Surface
	public static Reservation convert(ReservationRequest reservationRequest) {

		Reservation reservation = new Reservation();

		BeanUtils.copyProperties(reservationRequest, reservation);

		Client client = new Client();
		client.setId(reservationRequest.getIdClient());
		reservation.setClient(client);
		
		Employe employe = new Employe();
		employe.setId(reservationRequest.getIdEmploye());
		reservation.setEmploye(employe);
		
		Surface surface = new Surface();
		surface.setId(reservationRequest.getIdSurface());
		reservation.setSurface(surface);
		
		Jeu jeu = new Jeu();
		jeu.setId(reservationRequest.getIdJeu());
		reservation.setJeu(jeu);
		
		Commande commande = new Commande();
		commande.setId(reservationRequest.getIdCommande());
		reservation.setCommande(commande);

		return reservation;
	}

}
