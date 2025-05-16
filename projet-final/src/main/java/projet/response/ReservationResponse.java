package projet.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;

import projet.model.Achat;
import projet.model.Reservation;

public class ReservationResponse {

	private Integer id;
	private LocalDateTime debut;
	private LocalDateTime fin;
	private int nbPersonne;
	private String client;
	private Integer idClient;
	private String employe;
	private String jeu;
	private List<Achat> achats;

	public ReservationResponse() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDebut() {
		return debut;
	}

	public void setDebut(LocalDateTime debut) {
		this.debut = debut;
	}

	public LocalDateTime getFin() {
		return fin;
	}

	public void setFin(LocalDateTime fin) {
		this.fin = fin;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getEmploye() {
		return employe;
	}

	public void setEmploye(String employe) {
		this.employe = employe;
	}

	public String getJeu() {
		return jeu;
	}

	public void setJeu(String jeu) {
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

		dto.setClient(reservation.getClient().getNom());
		dto.setIdClient(reservation.getClient().getId());

		if(reservation.getEmploye() != null){
			dto.setEmploye(reservation.getEmploye().getNom());
		}
		if(reservation.getJeu() != null){
			dto.setJeu(reservation.getJeu().getNom());
		}
		
		dto.setAchats(reservation.getAchats());
		return dto;
	}
}
