package projet.response;

import java.time.LocalDateTime;

import projet.model.Reservation;

public class ReservationResponse {

	private Integer id;
	private LocalDateTime dateReservation;
	private int duree;
	private int nbPersonne;

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

	// Fabrique un DTO depuis une entité Reservation
	public static ReservationResponse fromEntity(Reservation reservation) {
		ReservationResponse dto = new ReservationResponse();
		dto.setId(reservation.getId());
		dto.setDateReservation(reservation.getDateReservation());
		dto.setDuree(reservation.getDuree());
		dto.setNbPersonne(reservation.getNbPersonne());
		return dto;
	}

}
