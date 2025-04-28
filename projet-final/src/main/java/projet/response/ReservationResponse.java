package projet.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Client;
import projet.model.Employe;
import projet.model.Jeu;
import projet.model.Reservation;
import projet.model.Surface;

public class ReservationResponse {

	private Integer id;
	private LocalDateTime dateReservation;
	private int duree;
	private int nbPersonne;
	private Client client;
	private Employe employe;
	private Surface surface;
	private Jeu jeu;
	private List<Achat> achats;

	public ReservationResponse() {
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public List<Achat> getAchats() {
		return achats;
	}

	public void setAchats(List<Achat> achats) {
		this.achats = achats;
	}

	// Convertir une entité Reservation en DTO ReservationResponse
	public static ReservationResponse convert(Reservation reservation) {
		ReservationResponse dto = new ReservationResponse();
		BeanUtils.copyProperties(reservation, dto);

		dto.setClient(reservation.getClient());
		dto.setEmploye(reservation.getEmploye());
		dto.setSurface(reservation.getSurface());
		dto.setJeu(reservation.getJeu());
		dto.setAchats(reservation.getAchats());

		return dto;
	}

}
