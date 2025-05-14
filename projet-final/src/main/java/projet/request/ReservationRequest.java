package projet.request;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import projet.model.Reservation;

public class ReservationRequest {

	private Integer id;
	private LocalDateTime debut;
	private LocalDateTime fin;
	private int nbPersonne;
	private Integer idClient;
	private Integer idEmploye;
	private Integer idSurface;
	private Integer idJeu;

	public ReservationRequest() {}

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

	// Méthode pour mapper les champs simples vers une entité Reservation
	public static Reservation convert(ReservationRequest dto) {
		Reservation reservation = new Reservation();
		BeanUtils.copyProperties(dto, reservation);

		// Les entités liées seront injectées dans le service
		return reservation;
	}
}
