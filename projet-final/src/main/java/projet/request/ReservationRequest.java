package projet.request;

import java.time.LocalDateTime;
import org.springframework.beans.BeanUtils;
import projet.model.Reservation;

public class ReservationRequest {

	private Integer id;
	private LocalDateTime dateReservation;
	private int duree;
	private int nbPersonne;

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

	// Méthode pour transformer le DTO en entité Surface
	public Reservation convert() {
		Reservation reservation = new Reservation();
		BeanUtils.copyProperties(this, reservation);
		return reservation;
	}

}
