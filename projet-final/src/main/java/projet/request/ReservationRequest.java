package projet.request;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
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

	// Méthode pour mapper les champs simples vers une entité Reservation
	public static Reservation toEntity(ReservationRequest dto) {
		Reservation reservation = new Reservation();
		BeanUtils.copyProperties(dto, reservation);

		// Les entités liées seront injectées dans le service
		return reservation;
	}
}
